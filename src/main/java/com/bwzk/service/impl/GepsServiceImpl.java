package com.bwzk.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;

import com.bwzk.dao.JdbcDao;
import com.bwzk.dao.MlJdbcDaoImpl;
import com.bwzk.pojo.GepsMappingBean;
import com.bwzk.service.BaseService;
import com.bwzk.service.i.GepsService;
import com.bwzk.util.GlobalFinalAttr;
@Service("gepsService")
public class GepsServiceImpl  extends BaseService  implements GepsService {
	public List<Map<String, Object>> projectList() {
		GepsMappingBean gepsMappingBean = getMappingList();

		List<Map<String, Object>> volMapList = null;
		List<Map<String, Object>> dFileMapList = null;
		List<Map<String, Object>> eFileMapList = null;
		//通过代码表的项目查询语句 查询geps中间库的 未归档的项目列表
		List<Map<String, Object>> prjMapList = mlJdbcDao.quertListMap(gepsMappingBean.getPrjSql());

		for (Map<String, Object> prjItem : prjMapList) {
			Integer xmid = getIntValue(prjItem, "XMID");
			Integer mlPrjDid = getIntValue(prjItem, "MLDID");
			// 插入prjitem 然后查询案卷
			volMapList = mlJdbcDao.quertListMap(gepsMappingBean.getVolSql()+" WHERE MLPID=" + mlPrjDid);
			
			prjItem.remove("XMID");
			prjItem.remove("MLDID");
			prjItem.put("PID", -1);
			Integer unisPrjDid = insertMap(gepsMappingBean.getPrjTableName(), prjItem);// -- 插入项目
			for (Map<String, Object> volItem : volMapList) {
				Integer mlVolDid = getIntValue(volItem, "MLDID");
				dFileMapList = mlJdbcDao.quertListMap(gepsMappingBean.getdFileSql()+" WHERE MLPID=" + mlVolDid);
				
				volItem.remove("MLDID");
				volItem.remove("MLPID");
				volItem.put("PID", unisPrjDid);
				Integer unisVolDid = insertMap(gepsMappingBean.getVolTableName(), volItem);// -- 插入案卷
				for (Map<String, Object> dFileItem : dFileMapList) {
					Integer mlDfileDid = getIntValue(dFileItem, "MLDID");
					eFileMapList = mlJdbcDao.quertListMap(gepsMappingBean.geteFileSql()+" WHERE MLPID=" + mlDfileDid);
					
					dFileItem.remove("MLDID");
					dFileItem.remove("MLPID");
					dFileItem.put("PID", unisVolDid);
					dFileItem.put("ATTACHED", eFileMapList.size() > 0 ? 1  : 0 );//判断是否有电子文件 0 没有 1有
					Integer unisDfileDid = insertMap(gepsMappingBean.getDfileTableName(), dFileItem);// -- 插入文件
					for (Map<String, Object> efileItem : eFileMapList) {
						efileItem.remove("MLDID");
						efileItem.remove("MLPID");
						efileItem.put("PID", unisDfileDid);
						insertMap(gepsMappingBean.getEfileTableName(), efileItem);// -- 插入电子文件
					}
				}
			}
			//添加一条项目 修改一条
			updateXmStatus(xmid);
		}
		
		return mlJdbcDao.quertListMap("SELECT * FROM JGZLZJ_ML");
	}
	
	/**
	 * <p>Title: 得到Lams和geps的对应代码表的map</p>
	 * <p>项目级别(0) D_PRJ_LEVEL(0)<br>案卷级别(1) D_VOL_LEVEL(1)<br> 文件级别(2) D_FILE_LEVEL(2)<br> 电子原文级别(3) E_EFILE_LEVEL(3)
	 * </p>
	 * @return
	 * @date 2014年1月20日
	*/
	private GepsMappingBean getMappingList(){
		List<Map<String, Object>> codeMappingList = null;
		if(null == gepsMapping){
			try {
				gepsMapping = new GepsMappingBean();
				codeMappingList = jdbcDao.quertListMap("SELECT F1 LEVEL, F2 LIBCODE, F3 TSQL FROM " + gepsCodeTableName);
				for (Map<String, Object> map : codeMappingList) {
					Integer theLevel = map.get("LEVEL") == null ? -1 : Integer.parseInt(map.get("LEVEL").toString());
					Integer theLibCode = map.get("LIBCODE") == null ? -1 : Integer.parseInt(map.get("LIBCODE").toString());
					String theSql = map.get("TSQL") == null ? "" : map.get("TSQL").toString();
					gepsMapping.setLibcode(theLibCode);
					switch (theLevel) {
					case 0://prj level
						gepsMapping.setPrjSql(theSql);
						break;
					case 1://vol level
						gepsMapping.setVolSql(theSql);
						break;
					case 2://dFile level
						gepsMapping.setdFileSql(theSql);
						break;
					case 3://eFile level
						gepsMapping.seteFileSql(theSql);
						break;
					default:
						log.error("初始化geps和紫光档案系统 geps对应表 错误 ");
						break;
					}
				}
			} catch (Exception e) {
				log.error("初始化geps和紫光档案系统 geps对应表 错误 " + e.getMessage());
				gepsMapping = null;
			}
		}
		return gepsMapping;
	}

	@Transactional("txManager_ML")
	public Boolean updateXmStatus(Integer xmid){// test  1749501150
		Boolean flag = false;
		try {
			mlJdbcDao.update("UPDATE JGZLZJ_XM SET OTHERSTATE="+getsState+" WHERE XMID=" + xmid);// JGZLZJ_XM
			mlJdbcDao.update("UPDATE JGZLZJ_DWGC SET OTHERSTATE="+getsState+" WHERE XMID=" + xmid);// JGZLZJ_DWGC
			List<Map<String, Object>> dwgcList = mlJdbcDao.quertListMap("SELECT XMID,DWGCID FROM JGZLZJ_DWGC WHERE XMID=" + xmid);
			for (Map<String, Object> dwgc : dwgcList) {
				Integer dwgcId = dwgc.get("DWGCID") == null ? -1 : Integer.parseInt(dwgc.get("DWGCID").toString());
				mlJdbcDao.update("UPDATE JGZLZJ_ML SET OTHERSTATE="+getsState+" WHERE DWGCID=" + dwgcId);// JGZLZJ_ML
				List<Map<String, Object>> mlList = mlJdbcDao.quertListMap("SELECT MLID,DWGCID FROM JGZLZJ_ML WHERE DWGCID=" + dwgcId);
				for (Map<String, Object> ml : mlList) {
					Integer mlid = ml.get("MLID") == null ? -1 : Integer.parseInt(ml.get("MLID").toString());
					mlJdbcDao.update("UPDATE JGZLZJ_ZL SET OTHERSTATE="+getsState+" WHERE MLID=" + mlid);// JGZLZJ_ZL
					List<Map<String, Object>> zlList = mlJdbcDao.quertListMap("SELECT ZLID,MLID FROM JGZLZJ_ZL WHERE MLID=" + mlid);
					for (Map<String, Object> zl : zlList) {
						Integer zlid = zl.get("ZLID") == null ? -1 : Integer.parseInt(zl.get("ZLID").toString());
						mlJdbcDao.update("UPDATE JGZLZJ_FILE SET OTHERSTATE="+getsState+" WHERE ZLID=" + zlid);// JGZLZJ_FILE
					}
					
				}
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
			log.error(e.getMessage() , e);
		}
		return flag;
	}
	
	/**
	 * <p>通过key的到map里面的值并且返回int</p>
	 * <p>Description: </p>
	 * @param mapItem 一个map
	 * @param key 
	 * @return 一个 int值 返回 -1 表示错误
	 * @date 2014年2月13日
	*/
	private Integer getIntValue(Map<String , Object> mapItem , String key){
		Integer tempInt = -1;
		if(mapItem != null && mapItem.get(key) != null && mapItem.get(key) instanceof Integer){
			try {
				tempInt = Integer.parseInt(mapItem.get(key).toString());
			} catch (Exception e) {
				tempInt = -1;
				log.error(e.getMessage() , e);
			}
		}
		return tempInt;
	}

	/**
	 * <p>将map插入 指定的表内</p>
	 * <p>Description: </p>
	 * @param tableName 表名
	 * @param valueMap Map对象
	 * @return
	 * 
	 * @date 2014年2月16日
	*/
	private Integer insertMap(String tableName , Map<String , Object> valueMap){
		Integer maxDid = -1;
		if(StringUtils.isNotEmpty(tableName) && valueMap != null){
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			StringBuffer insertSql = new StringBuffer("INSERT INTO ").append(tableName);
			maxDid = getMaxDid(tableName);
			try {
				Set<String> keySet = valueMap.keySet();
				for (String key : keySet) {
					Object theValue = valueMap.get(key);
					if(theValue != null){
//						System.out.println(theValue.getClass());
						fields.append(key).append(",");
						if(theValue instanceof Timestamp){
							values.append(GlobalFinalAttr.DatabaseType.SQLSERVER.generateTimeToSQLDate(theValue))
								.append(",");
						}else if(theValue instanceof Integer){
							values.append(theValue).append(",");
						}else{
							values.append("'").append(theValue).append("'").append(",");
						}
					}
				}
				fields.append("DID");
				values.append(maxDid);
				insertSql.append("(").append(fields.toString()).append(") VALUES (").append(values.toString()).append(")");
				System.out.println(insertSql.toString());
				jdbcDao.insert(insertSql.toString());
			} catch (Exception e) {
				log.error(e.getMessage() , e);
			}
			
		}
		return maxDid;
	}
	
	@Autowired
	private JdbcDao jdbcDao;
	@Autowired
	private MlJdbcDaoImpl mlJdbcDao;
	
	@Autowired
	@Value("${unis.geps.codetable}")
	private String gepsCodeTableName;
	
	/** -1：本记录被删除；0：本记录是新增记录；>1：本记录更新；默认为0*/
	@Autowired
	@Value("${geps.gepsstate}")
	private Integer getsState;
	
	/** 对应描述字段*/
	private GepsMappingBean gepsMapping = null;
	private Logger log =  (Logger) LoggerFactory.getLogger(this.getClass());
}
