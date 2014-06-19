package com.bwzk.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.DataSvc;
import org.tempuri.DataSvcSoap;
import org.tempuri.ReturnData;

import ch.qos.logback.classic.Logger;

import com.bwzk.dao.JdbcDao;
import com.bwzk.dao.i.SGroupMapper;
import com.bwzk.dao.i.SUserMapper;
import com.bwzk.dao.i.SUserroleMapper;
import com.bwzk.pojo.MLDepNewDataSet;
import com.bwzk.pojo.MLFieldMapping;
import com.bwzk.pojo.MLUsrDataSet;
import com.bwzk.pojo.MLXmlResult;
import com.bwzk.pojo.MLXmlResult.Attachments.Attachment;
import com.bwzk.pojo.MLXmlResult.DocInfo.Field;
import com.bwzk.pojo.MlLams;
import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SUser;
import com.bwzk.pojo.SUserWithBLOBs;
import com.bwzk.pojo.SUserrole;
import com.bwzk.pojo.SUserroleExample;
import com.bwzk.service.BaseService;
import com.bwzk.service.i.ArcService;
import com.bwzk.util.CommonUtil;

@WebService
@Service("arcServcieImpl")
public class ArcServcieImpl  extends BaseService implements ArcService{
	@Transactional("txManager")
	public String fileRecive(String xml) {
		String resultStr = "";
		if(StringUtils.isNotEmpty(xml)){
			log.error("====================================================");//临时记录
			log.error(xml);//临时记录
			resultStr = this.add2Archive(xml);
			log.error("====================================================");//临时记录
			//throw new RuntimeException("test TX");
		}else{
			resultStr = "OA发送xml为空.";
			log.error(resultStr);
		}
		return resultStr;
	}
	
	public String initUserGroup() {
		return  initGroup()+ initUser();
	}
	
	/**
	 * 初始化部门 程序中有关于加减stepNum的主要是想利用广梦的id作为档案系统id
	 */
	private synchronized String initGroup(){
		Reader reader = null;
		Unmarshaller unmarshaller = null;
		StringBuffer sb = new StringBuffer("");
		LinkedList<MlLams> allGroupList = new LinkedList<MlLams>();
		LinkedList<MlLams> list = new LinkedList<MlLams>();
		List<MlLams> childs = new ArrayList<MlLams>();
		try {
			DataSvcSoap serviceSoap = this.getMlSoap();
			ReturnData returnData = serviceSoap.getAllData(mlSncUserPasswd, "dep");
			reader = new StringReader(returnData.getXML());  
			unmarshaller = JAXBContext.newInstance(MLDepNewDataSet.class).createUnmarshaller(); 
			MLDepNewDataSet result = (MLDepNewDataSet) unmarshaller.unmarshal(reader);   
			List<MLDepNewDataSet.Table> mlDepTableList = result.getTable();
			String pTableName = super.queryForString("SELECT TBLENNAME FROM S_ALL WHERE TBLCHNAME='"+qzhMappingTable+"'");
			if(StringUtils.isEmpty(pTableName)){//全宗对应表为空无法同步
				sb.append("获取").append(qzhMappingTable).append("发生错误").append("<br>");
			}else{
				Map<String, String> pTableMap = super.quert2Colum4Map("SELECT F2 , F4 FROM "+ pTableName, "F2", "F4");
				Set<String> qzhSet = pTableMap.keySet();
				for (String qzh : qzhSet) {
					List<MLDepNewDataSet.Table> theSubTableList = this.getSubTableByDepID(mlDepTableList , Integer.parseInt(pTableMap.get(qzh)));
					for (MLDepNewDataSet.Table rootDep : theSubTableList) {
						rootDep.setDEPPARENTID(0);//设置为根节点
						MlLams mlLams = new MlLams(rootDep, qzh);
						childs.add(mlLams);
					}
				}
				for (MlLams mllLams : childs) {
					list.add(mllLams);
				}
				//开始广度优先算法遍历多叉树
				MlLams mllandObj = null;
				while (!list.isEmpty()) { // 2. 开始迭代
					mllandObj = list.removeFirst(); // 移除并返回此列表的第一个元素
					allGroupList.add(mllandObj);
					List<MLDepNewDataSet.Table> subDepList = this.getSubTableByDepID(mlDepTableList, mllandObj.getMlObj().getDEPID());
					for (MLDepNewDataSet.Table table : subDepList) {
						MlLams innerMlLams = new MlLams(table, mllandObj.getQzh());
						list.add(innerMlLams);
					}
				}
				System.out.println(allGroupList.size());
				for (MlLams mllams : allGroupList) {
					try {
						SGroup addGroup = mlLams2Sgroup(mllams);
						if(addGroup !=  null){
							SGroup tempGroup = sGroupMapper.selectByPrimaryKey(addGroup.getDid());
							if(tempGroup == null){//add
								sGroupMapper.insert(addGroup);
								sb.append("增加一个新部门: ").append(addGroup).append("<br>"); 
							}else{//update
								tempGroup.setGname(addGroup.getGname());
								tempGroup.setPid(addGroup.getPid());
								sGroupMapper.updateByPrimaryKey(tempGroup);
								sb.append("修改一个部门: ").append(tempGroup).append("<br>");
							}
						}
					} catch (Exception exx) {
						sb.append("修改或增加部门错误: ").append(exx.getMessage()).append("<br>");
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			sb.append("初始化出现错误:").append(e.getMessage()).append(" <br>");
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return sb.toString();
	}
	
	/**
	 * 初始化用户 
	 */
	private String initUser(){
		Reader reader = null;
		MLUsrDataSet usrResultList = null;
		ReturnData returnData = null;
		Unmarshaller unmarshaller =  null;
		StringBuffer sb = new StringBuffer();
		List<SUserWithBLOBs> addUserList = new ArrayList<SUserWithBLOBs>();
		try {
			returnData = this.getMlSoap().getAllData(mlSncUserPasswd, "usr");
			reader = new StringReader(returnData.getXML());
			unmarshaller = JAXBContext.newInstance(MLUsrDataSet.class).createUnmarshaller();       
			usrResultList = (MLUsrDataSet) unmarshaller.unmarshal(reader);   
			for (MLUsrDataSet.Table usr : usrResultList.getTable()) {
				addUserList.add(mlUser2Suser(usr));
			}
			//下面开始判断插入
			for (SUserWithBLOBs sUser : addUserList) {
				try {
					SUser tempUser = sUserMapper.getUserByUsercode(sUser.getUsercode());
					if(tempUser == null){//原来系统没有需要增加 添加角色
						sUserMapper.insert(sUser);
						// 为新 用户关联角色
						SUserroleExample urEx = new SUserroleExample();
						urEx.createCriteria().andYhidEqualTo(sUser.getDid());
						List<SUserrole> roleList = sUserroleMapper.selectByExample(urEx);
						if(roleList.size() > 0){
							//已经包含了角色
						}else{
							SUserrole userrole = new SUserrole();
							userrole.setYhid(sUser.getDid());
							userrole.setJsid(defaultJsid);
							Integer maxDidUserRole = getMaxDid("S_USERROLE");
							userrole.setDid(maxDidUserRole == null ? 1 : maxDidUserRole);
							sUserroleMapper.insert(userrole);
						}
						sb.append("增加一个用户").append(sUser).append("<br>");
					}else{//如果有直接update
						tempUser.setPid(sUser.getPid());
						tempUser.setUsername(sUser.getUsername());
						sUserMapper.updateByPrimaryKey(tempUser);
						sb.append("更新一个用户").append(sUser).append("<br>");
					}
				} catch (Exception e) {
					sb.append(e.getMessage()).append("<br>");;
				}
			}
		} catch (Exception ex) {
			sb.append(ex.getMessage()).append("<br>");;
		}
		syncActivitUser();
		return sb.toString();
	}
	
	
	
	@Transactional("txManager")
	public String userSync(String xml) {
		return null;
	}
	@Transactional("txManager")
	public String groupSync(String xml) {
		return null;
	}
	@Transactional("txManager")
	public String qzhSync(String xml) {
		return null;
	}
	
	@Transactional("txManager")
	public List<SUser> listAllUser() {
		return sUserMapper.getAllUserList();
	}
	
	/**
	 * 更新代码表.置null 下次get这个fieldMapping的时候会自动更新
	 */
	public void initMlFieldMapping(){
		mlFieldMapping = null;
	}
	
	public String getBmidStrByDepCode(String depCode){
		return super.getBmidByDepCode(depCode);
	}
	
	public String getBmidStrByUserCode(String userCode){
		return super.getBmidByuserCode(userCode);
	}
	
	/**
	 * 内部得到ml对应关系表 f1是广梦的字段 f2是档案的字段
	 */
	public List<MLFieldMapping> getAndInitMLFieldMapping(){
		if(mlFieldMapping == null || mlFieldMapping.size() == 0){
			mlFieldMapping = new ArrayList<MLFieldMapping>();
			String sql = "SELECT TBLENNAME,TBLCHNAME FROM S_ALL WHERE TBLCHNAME LIKE '"+fieldStarStr+"%'";
			List<Map<String, Object>> resultList = quertListMap(sql);
			if(resultList != null && resultList.size() > 0){
				MLFieldMapping theFieldMapping = null;
				for (Map<String, Object> map : resultList) {
					theFieldMapping = new MLFieldMapping();
					if(map.get("tblchname").toString().equals(qzhMappingTable)){
						continue;
					}
					theFieldMapping.setEnName(map.get("tblenname").toString());
					theFieldMapping.setChName(map.get("tblchname").toString());
					String[] tempArray = StringUtils.split(theFieldMapping.getChName() , "[-]");
					theFieldMapping.setFileType(tempArray[1]);
					theFieldMapping.setdTableName("D_" + tempArray[2]);
					theFieldMapping.setFieldMap(quert2Colum4Map("select f1 , f2 from "+theFieldMapping.getEnName(), "f1", "f2"));
					mlFieldMapping.add(theFieldMapping);
				}
			}else{
				mlFieldMapping = null;
			}
		}
		return mlFieldMapping;
	}
	
	/**
	 * @param xml 将xml解析并且添加到档案
	 */
	private String add2Archive(String xml){
		String resultStr = "1";
		MLFieldMapping ptableMapping = null;//
		List<MLFieldMapping> fieldMappingList = getAndInitMLFieldMapping();
		//判断字段对应关系列表是否为空
		if(fieldMappingList != null){
			Reader reader = null;
			Unmarshaller unmarshaller = null;
			try {
				//进行JAXB解析XML
				reader = new StringReader(xml);
				unmarshaller =  JAXBContext.newInstance(MLXmlResult.class).createUnmarshaller();
				MLXmlResult xmlParseResult = (MLXmlResult) unmarshaller.unmarshal(reader);//解析完毕
				//ML原文列表 和字段列表
				List<Field> fieldList = xmlParseResult.getDocInfo().getField();
				List<Attachment> attachementList = xmlParseResult.getAttachments().getAttachment();
				Field doctype = getFieldByColName(fieldList, "doctype");//发文类别
				for (MLFieldMapping mlFieldMapping : fieldMappingList) {
					if(doctype.getValue().equals(mlFieldMapping.getFileType())){
						ptableMapping = mlFieldMapping;
						break;
					}
				}
				if(ptableMapping != null){//找到对应表开始转换成sqsl
					Integer dTableMaxDid = super.getMaxDid(ptableMapping.getdTableName());
					String dFileSql = getDFileSqlStr(ptableMapping , fieldList , attachementList.size() > 0 , dTableMaxDid);
					super.execSql(dFileSql);//插入DFILE
					log.error("从广梦接口中收到并且保存一条文件记录");
					//插入EFILE
					this.insertEfile(attachementList, dTableMaxDid, ptableMapping.getdTableName().replace("D_", "E_"));
				}else{
					resultStr = "档案系统没有匹配到对应关系表" + doctype.getValue();
					log.error(resultStr);
				}
				Field docCode = getFieldByColName(fieldList, "depcode");//部门code
				
			} catch (Exception e) {
				resultStr = "解析xml发生错误"+e.getMessage();
				log.error("解析xml发生错误"+e.getMessage() , e);
			}finally{
				try {
					reader.close();
				} catch (IOException e) {
					resultStr = "关闭reader发生错误"+e.getMessage();
					log.error("关闭reader发生错误"+e.getMessage() , e);
				}
			}
			
		}else{
			resultStr = "档案系统获取OA字段对应关系发生错误.";
			log.error(resultStr);
		}
        return resultStr;
	}
	
	/**
	 * 得到Dfilesql
	 * @param mlMapping 梦龙和档案系统的映射关系
	 * @param fieldList 梦龙XML解析的field
	 * @param hasEfile 是否有电子文件 true有 false 无
	 */
	private String getDFileSqlStr(MLFieldMapping mlMapping , List<Field> fieldList , Boolean hasEfile, Integer maxDid){
		//f表
		Map<String, String> fTableTypeMap = quert2Colum4Map("select fieldname,fieldtype from F_"+mlMapping.getdTableName(), "fieldname", "fieldtype");
		StringBuffer sb = new StringBuffer("insert into ");
		StringBuffer sbField = new StringBuffer(" STATUS , ATTR ,ATTREX,PID,");
		StringBuffer sbValue = new StringBuffer(dfileStatus);
		sbValue.append(" , ").append(dfileAttr).append(" , ").append(dfileAttrex).append(" , -1 ,");
		Field qzhField = getFieldByColName(fieldList, "qzh");
		Field depcodeField = getFieldByColName(fieldList, "depcode");
		String bmid = "";
		try {
			bmid = this.getBmidStrByDepCode(depcodeField.getValue());
		} catch (Exception e) {
			bmid = qzhField.getValue();
			log.error("通过depcode获取bmid发生错误"+e.getMessage() , e);
		}
		String daField = "";
		Integer dFieldType = null;//档案系统的字段类型 1  3  11
		Field valueField = null;//广梦的字段值对象
		for (String key : mlMapping.getFieldMap().keySet()) {//key是广梦字段  value 档案的字段
			daField = mlMapping.getFieldMap().get(key);
			if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(daField)){
				valueField = getFieldByColName(fieldList, key);
				if(valueField == null || StringUtils.isEmpty(valueField.getValue())){
					continue;
				}
				//查询字段的type
				try {
					dFieldType = Integer.parseInt(fTableTypeMap.get(daField));
				} catch (Exception e) {
					log.error("P代码表和F表字段匹配发生错误,请检查大小写"+e.getMessage() , e);
				}
				switch (dFieldType) {
					case 11:
						sbField.append(daField).append(",");
						sbValue.append(super.generateTimeToSQLDate(valueField.getValue())).append(",");
						break;
					case 1:
						sbField.append(daField).append(",");
						sbValue.append("'").append(valueField.getValue()).append("',");
						break;
					case 3:
						sbField.append(daField).append(",");
						sbValue.append(valueField.getValue()).append(",");
						break;
					default:
						sbField.append(daField).append(",");
						sbValue.append("'").append(valueField.getValue()).append("',");
						break;
				}
			}
		}
		sbField.append("DID,ATTACHED,BMID");
		sbValue.append(maxDid).append(",").append( hasEfile ? 1 : 0).append(",'").append(bmid).append("'");
		sb.append(mlMapping.getdTableName()).append("(").append(sbField.toString()).append(") values (");
		sb.append(sbValue.toString()).append(")");
		return sb.toString();
	}
	
	private void insertEfile(List<Attachment> attachementList ,final Integer pid , final String tableName){
		Integer eifleMaxDid = getMaxDid(tableName);
		final Date date = super.getDBDateTime();
		for (Attachment att : attachementList) {
			String[] fileNameArray =  StringUtils.split(att.getFtpdir()  , "/");
			final String efileName = fileNameArray[fileNameArray.length-1];//实际文件名 2013082248572345.docx
			final String efileTitle = att.getFilename().substring(0 , att.getFilename().lastIndexOf("."));//title 关于任命的收文
			final String efileExt = att.getFilename().substring(att.getFilename().lastIndexOf(".") +1 , att.getFilename().length());//后缀 .docx
			final String pzm = att.getPzm();
			final String pathName = att.getFtpdir().replace(efileName, "");
			final Integer fileSize = att.getFilesize();
			final Integer insertEfileDid  = eifleMaxDid++;
			PreparedStatementSetter setter = new PreparedStatementSetter(){  public void setValues(PreparedStatement ps) throws SQLException {
				int i = 1;
				//DID,PID,EFILENAME,TITLE,EXT,PZM,PATHNAME,STATUS,ATTR,ATTREX,CREATOR,CREATETIME,FILESIZE
				ps.setInt(i++ , insertEfileDid);
				ps.setInt(i++ , pid);
				ps.setString(i++, efileName);
				ps.setString(i++, efileTitle);
				ps.setString(i++, efileExt);
				ps.setString(i++, pzm);
				ps.setString(i++, pathName);
				ps.setInt(i++, 0);
				ps.setInt(i++, 0);
				ps.setInt(i++, 0);
				ps.setString(i++, "FROMOA");
				ps.setDate(i++, new java.sql.Date(date.getTime()));
				ps.setInt(i++, fileSize);
			}};
			jdbcDao.insertEfile(tableName, setter);
			log.error("从广梦接口中收到并且保存一条原文记录");
		}
	}
	
	/**
	 * 通过colname获取XML里面 field
	 */
	private Field getFieldByColName(List<Field> fieldList , String colName){
		Field returnField = null;
		for (Field field : fieldList) {
			if(field.getColName().equals(colName)){
				returnField = field;
				break;
			}
		}
		return returnField;
	}
	
	/**
	 * 得到广梦 用户机构初始化的 的服务soap
	 */
	private DataSvcSoap getMlSoap(){//mlSncUserPasswd
		URL url = null;
		QName qName = null;
		DataSvcSoap dataSvcSop = null;
		try {
			qName = new QName("http://tempuri.org/", "DataSvc");
			url = new URL(mlSyncUserWSDL);
			dataSvcSop = new DataSvc(url , qName).getDataSvcSoap();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return dataSvcSop;
	}
	
	/**
	 * 通过部门id得到相应部门
	 * @param list
	 * @param depID
	 * @return
	 */
	private MLDepNewDataSet.Table getDepEleByDepID(List<MLDepNewDataSet.Table> list , String depID){
		MLDepNewDataSet.Table returnObj = null;
		Integer theId = Integer.parseInt(depID);
		for (MLDepNewDataSet.Table table : list) {
			if(table.getDEPID().equals(theId)){
				returnObj = table;
				break;
			}
		}
		return returnObj;
	}
	
	/**
	 * 通过部门id得到相应下级部门
	 * @param list
	 * @param depID
	 */
	private List<MLDepNewDataSet.Table> getSubTableByDepID(List<MLDepNewDataSet.Table> mlDepTableList , Integer depID){
		List<MLDepNewDataSet.Table> subList = new ArrayList<MLDepNewDataSet.Table>();
		if(depID == null){
			return subList;
		}
		for (MLDepNewDataSet.Table table : mlDepTableList) {
			if(table.getDEPID().equals(table.getDEPPARENTID())){
				continue;
			}else{
				if(table.getDEPPARENTID().equals(depID)){
					subList.add(table);
				}
			}
		}
		return subList;
	}
	
	/**
	 * 广梦的usr和Lams的用户转换
	 */
	private SUserWithBLOBs mlUser2Suser(MLUsrDataSet.Table usr){
		SUserWithBLOBs sUser = new SUserWithBLOBs();
		sUser.setDid(usr.getUSRID().equals(0) ? usr.getUSRID()+1000000 : usr.getUSRID() );
		sUser.setUsername(usr.getUSRNAME());
		sUser.setUsercode(usr.getUSRCODE());
		sUser.setPid(usr.getUSRDEPID()+stepNum);
		sUser.setPasswd(defalutPasswd);
		sUser.setIslsyh(0);
		return sUser;
	}
	
	/**
	 * 广梦的dep和Lams的s_group转换
	 */
	private SGroup mlLams2Sgroup(MlLams mllams){
		SGroup sGroup = new SGroup();
		sGroup.setDid(mllams.getMlObj().getDEPID() + stepNum);
		sGroup.setPid(mllams.getMlObj().getDEPPARENTID() == 0 ? mllams.getMlObj().getDEPPARENTID() : mllams.getMlObj().getDEPPARENTID() + stepNum);
		sGroup.setQzh(mllams.getQzh());
		sGroup.setDepcode(mllams.getMlObj().getDEPCODE());
		sGroup.setGname(mllams.getMlObj().getDEPNAME());
		sGroup.setGid(0);
		return sGroup;
	}
	
	@Autowired
	private SGroupMapper sGroupMapper;
	@Autowired
	private SUserMapper sUserMapper;
	@Autowired
	private SUserroleMapper sUserroleMapper;
	@Autowired
	private JdbcDao jdbcDao;
	@Autowired
	@Value("${morrowsoft2unis.field.mapping.str}")
	private String fieldStarStr;
	@Autowired
	@Value("${lams.dfile.status}")
	private String dfileStatus;//状态
	@Autowired
	@Value("${lams.dfile.attr}")
	private String dfileAttr;//归档前后  1未归档  0已归档
	@Autowired
	@Value("${lams.dfile.attrex}")
	private String dfileAttrex;//移交
	//梦龙用户同步wsdl
	@Autowired
	@Value("${morrowsoft.usersync.wsdl}")
	private String mlSyncUserWSDL;
	//梦龙用户同步密码
	@Autowired
	@Value("${morrowsoft.usersync.passwd}")
	private String mlSncUserPasswd;
	//全宗同步表名
	@Autowired
	@Value("${morrowsoft.usersync.mapping.tablename}")
	private String qzhMappingTable;
	//2个系统id公用的补偿
	@Autowired
	@Value("${morrowsoft.2.unis.step}")
	private Integer stepNum;
	//默认角色id
	@Autowired
	@Value("${default.jsid}")
	private Integer defaultJsid;
	//档案系统新增用户密码
	@Autowired
	@Value("${default.password}")
	private String defalutPasswd;
	@Autowired
	@Value("${lams.ip}")
	private String lamsIP;
	
	private void syncActivitUser(){
		String urlStr = "http://"+lamsIP+"/Lams/activiti/syn";
		CommonUtil.doHttpGet(urlStr);
	}
	
	private List<MLFieldMapping> mlFieldMapping = null;
	private Logger log =  (Logger) LoggerFactory.getLogger(this.getClass());
}
