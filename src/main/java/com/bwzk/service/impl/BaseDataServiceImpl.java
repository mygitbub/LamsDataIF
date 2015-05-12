package com.bwzk.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SUser;
import com.bwzk.pojo.jaxb.Field;
import com.bwzk.pojo.jaxb.Table;
import com.bwzk.service.BaseService;
import com.bwzk.service.i.BaseDataService;
import com.bwzk.util.GlobalFinalAttr;
import com.bwzk.util.XmlObjUtil;

@Service("baseDataServiceImpl")
@WebService(name = "BaseDataWS", targetNamespace = "http://service.lams.cn/")
public class BaseDataServiceImpl extends BaseService implements BaseDataService {
	
	@WebMethod
	public Integer addUserByTxt(@WebParam(name = "xmlName") String xmlName, 
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "esbid") String esbid) {
		Integer maxdid = 0;
		String SQL = "";
		Integer result = 1;
		Integer pid = null;
		String xmlPath = xmlName.toUpperCase().contains(".XML") ?
				GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH + xmlName + ".XML";
		File xmlFile = new File(xmlPath);
		if(!xmlFile.exists()){
			log.error("请检查传入的xmlName");
			return result;
		}
		try {
			maxdid = getMaxDid(xmlName.toUpperCase().replace(".XML", ""));
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile , GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if(xmlfields.size() != s.length){
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {//循环字段
				String theValue = (StringUtils.isBlank(s[i])  ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''") : theValue;
				fields.append(field.getFieldname()).append(",");
				switch (field.getFieldtype()) {
					case 11:
						if(theValue.equals("")){
							values.append("null,");
						}else{
							values.append(generateTimeToSQLDate(theValue)).append(",");
						}
						break;
					case 1:
						values.append("'").append(theValue).append("',");
						break;
					case 3:
						if(StringUtils.isBlank(theValue)){
							values.append("null ,");
						}else{
							values.append(Integer.parseInt(theValue)).append(",");
						}
						break;
					default:
						values.append("'").append(theValue).append("',");
						break;
				}
				i = i+1;
			}
			SGroup group = sGroupMapper.getGroupByGfzj(dept_zj);
			if(group == null){
				pid = defaultYhGroup;
			}else{
				pid = group.getDid();
			}
			fields.append("did,pid,esbid,esbcode");
			values.append(maxdid).append(",").append(pid).append(",'").append(esbid).append("',").append("'").append(dept_zj).append("'");
			SQL = "insert into " + table.getName() + " (" + fields.toString()
					+ ") values ( " + values.toString() + " )";
			execSql(SQL);
			result = 0;
			log.error("插入一条数据成功.addUserByTxt: "+SQL);
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("插入一条数据错误.addUserByTxt:" , e);
			log.error(SQL);
		} 
		return result;
	}
	@WebMethod
	public Integer modifyUserByTxt(@WebParam(name = "xmlName") String xmlName, 
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "esbid") String esbid) {
		String SQL = "";
		Integer result = 1;
		String xmlPath = xmlName.toUpperCase().contains(".XML") ?
				GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH + xmlName + ".XML";
		File xmlFile = new File(xmlPath);
		if(!xmlFile.exists()){
			log.error("请检查传入的xmlName");
			return result;
		}
		try {
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile , GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if(xmlfields.size() != s.length){
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {//循环字段
				String theValue = (StringUtils.isBlank(s[i])  ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''") : theValue;
				fields.append(field.getFieldname()).append("=");
				switch (field.getFieldtype()) {
					case 11:
						if(theValue.equals("")){
							fields.append("null,");
						}else{
							fields.append(generateTimeToSQLDate(theValue)).append(",");
						}
						break;
					case 1:
						fields.append("'").append(theValue).append("',");
						break;
					case 3:
						if(StringUtils.isBlank(theValue)){
							fields.append("null ,");
						}else{
							fields.append(Integer.parseInt(theValue)).append(",");
						}
						break;
					default:
						fields.append("'").append(theValue).append("',");
						break;
				}
				i = i+1;
			}
			SUser user = sUserMapper.getUserByEsbid(esbid);
			if (user != null) {
			SQL = "update " + table.getName() + " set " +fields.toString().substring(0,fields.length()-1)+ " where esbid = '"+esbid+"'";
			execSql(SQL);
			result = 0;
			log.error("更新一条数据成功.ModifyUserByTxt: "+SQL);
			}else{
				log.error("修改用户:" + esbid + "不存在");
			}
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("更新一条数据错误.ModifyUserByTxt:" , e);
			log.error(SQL);
		} 
		return result;
	}
	
	@WebMethod(operationName = "DelUser")
	public String delUser(@WebParam(name = "ESBID") String ESBID) {
		String result = "0";
		try {
			sUserMapper.delUserByEsbid(ESBID);
			log.error("删除一个用户:" + ESBID);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String addOrgByTxt(@WebParam(name = "xmlName") String xmlName, 
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "gfzj") String gfzj,
			@WebParam(name = "parent_org_no") String parent_org_no) {
		Integer maxdid = 0;
		String SQL = "";
		String result = "1";
		Integer pid = null;
		String qzh = null;
		String xmlPath = xmlName.toUpperCase().contains(".XML") ?
				GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH + xmlName + ".XML";
		File xmlFile = new File(xmlPath);
		if(!xmlFile.exists()){
			log.error("请检查传入的xmlName");
			return result;
		}
		try {
			maxdid = getMaxDid(xmlName.toUpperCase().replace(".XML", ""));
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile , GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if(xmlfields.size() != s.length){
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {//循环字段
				String theValue = (StringUtils.isBlank(s[i])  ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''") : theValue;
				fields.append(field.getFieldname()).append(",");
				switch (field.getFieldtype()) {
					case 11:
						if(theValue.equals("")){
							values.append("null,");
						}else{
							values.append(generateTimeToSQLDate(theValue)).append(",");
						}
						break;
					case 1:
						values.append("'").append(theValue).append("',");
						break;
					case 3:
						if(StringUtils.isBlank(theValue)){
							values.append("null ,");
						}else{
							values.append(Integer.parseInt(theValue)).append(",");
						}
						break;
					default:
						values.append("'").append(theValue).append("',");
						break;
				}
				i = i+1;
			}
			pid = TOPGROUPPID;
			String orgSql = "select gname from s_group where gfzj = '"+gfzj+"'";
			String org_name = jdbcDao.query4String(orgSql);
			qzh = getQzh(org_name);
			if (StringUtils.isBlank(qzh)) {
				SGroup parent = sGroupMapper.getGroupByBz(parent_org_no);
				pid = (parent == null ? defaultgrouppid : parent.getDid());
				qzh = getQzhByPid(pid);
			}
			fields.append("did,pid,qzh,gfzj,depid");
			values.append(maxdid).append(",").append(pid).append(",'").append(qzh).append("','").append(gfzj).append("','").append(parent_org_no).append("'");
			SQL = "insert into " + table.getName() + " (" + fields.toString()
					+ ") values ( " + values.toString() + " )";
			execSql(SQL);
			result = gfzj;
			log.error("插入一条数据成功.addUserByTxt: "+SQL);
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("插入一条数据错误.addUserByTxt:" , e);
			log.error(SQL);
		} 
		return result;
	}
	@WebMethod
	public Integer modifyOrgByTxt(@WebParam(name = "xmlName") String xmlName, 
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "gfzj") String gfzj) {
		String SQL = "";
		Integer result = -1;
		String xmlPath = xmlName.toUpperCase().contains(".XML") ?
				GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH + xmlName + ".XML";
		File xmlFile = new File(xmlPath);
		if(!xmlFile.exists()){
			log.error("请检查传入的xmlName");
			return result;
		}
		try {
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile , GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if(xmlfields.size() != s.length){
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {//循环字段
				String theValue = (StringUtils.isBlank(s[i])  ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''") : theValue;
				fields.append(field.getFieldname()).append("=");
				switch (field.getFieldtype()) {
					case 11:
						if(theValue.equals("")){
							fields.append("null,");
						}else{
							fields.append(generateTimeToSQLDate(theValue)).append(",");
						}
						break;
					case 1:
						fields.append("'").append(theValue).append("',");
						break;
					case 3:
						if(StringUtils.isBlank(theValue)){
							fields.append("null ,");
						}else{
							fields.append(Integer.parseInt(theValue)).append(",");
						}
						break;
					default:
						fields.append("'").append(theValue).append("',");
						break;
				}
				i = i+1;
			}
			SGroup sg = sGroupMapper.getGroupByGfzj(gfzj);
			if (sg != null) {
			SQL = "update " + table.getName() +  " set " +fields.toString().substring(0,fields.length()-1)+ " where gfzj = '"+gfzj+"'";
			execSql(SQL);
			result = 0;
			log.error("更新一条数据成功.addUserByTxt: "+SQL);
			}else{
				log.error("修改部门:" + gfzj + "不存在");
			}
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("更新一条数据错误.addUserByTxt:" , e);
			log.error(SQL);
		} 
		return result;
	}
	@WebMethod(operationName = "DelOrg")
	public String delOrg(@WebParam(name = "GFZJ") String GFZJ) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_GROUP  WHERE GFZJ='" + GFZJ + "'");
			log.error("删除一个部门:" + GFZJ);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}
	@WebMethod
	public String addUserByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "ESBID") String ESBID) {
		String result = "0";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vars = null;
			vars = mapper.readValue(dataJson, Map.class);
			result = insertUser4Map(vars, tableName, dept_zj, ESBID);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}
	@WebMethod
	public String modifyUserByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "esbid") String esbid) {
		String result = "0";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vars = null;
			vars = mapper.readValue(dataJson, Map.class);
			result = updateUser4Map(vars,tableName,esbid);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String delUserByJson(@WebParam(name = "ESBID") String ESBID) {
		String result = "0";
		try {
			sUserMapper.delUserByEsbid(ESBID);
			log.error("删除一个用户:" + ESBID);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}
	@WebMethod
	public String addOrgByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "gfzj") String gfzj,
			@WebParam(name = "parent_org_no") String parent_org_no) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> vars = null;
		try {
			vars = mapper.readValue(dataJson, Map.class);
			result = insertOrg4Map(vars,tableName,gfzj,parent_org_no);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String modifyOrgByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "gfzj") String gfzj) {
		String result = "0";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vars = null;
			vars = mapper.readValue(dataJson, Map.class);
			result = updateOrg4Map(vars,tableName,gfzj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String delOrgByJson(@WebParam(name = "GFZJ") String GFZJ) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_GROUP  WHERE GFZJ='" + GFZJ + "'");
			log.error("删除一个部门:" + GFZJ);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}
	@WebMethod
	public String addUserByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "esbid") String esbid) {
		String result = "0";
		try {
			 String xmlPath = xmlName.toUpperCase().contains(".XML") ?
			 GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH +
			 xmlName + ".XML";
			File xmlFile = new File(xmlPath);
			if (!xmlFile.exists()) {
				log.error("请检查传入的xmlName");
				return "1";
			}
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			Map<String, String> vars = convertTable2Map(table);
			String tableName = xmlName.toUpperCase().contains(".XML") ? xmlName.toUpperCase().replace(".XML", "") : xmlName.toString();
			result = insertUser4Map(vars,tableName,dept_zj,esbid);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String modifyUserByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "esbid") String esbid) {
		String result = "0";
		try {
			String xmlPath = xmlName.toUpperCase().contains(".XML") ? GlobalFinalAttr.XML_PATH
					+ xmlName
					: GlobalFinalAttr.XML_PATH + xmlName + ".XML";
			File xmlFile = new File(xmlPath);
			if (!xmlFile.exists()) {
				log.error("请检查传入的xmlName");
				return "1";
			}
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			Map<String, String> vars = convertTable2Map(table);
			String tableName = xmlName.toUpperCase().contains(".XML") ? xmlName.toUpperCase().replace(".XML", "") : xmlName.toString();
			result = updateUser4Map(vars,tableName,esbid);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String delUserByXml(@WebParam(name = "ESBID") String ESBID) {
		String result = "0";
		try {
			sUserMapper.delUserByEsbid(ESBID);
			log.error("删除一个用户:" + ESBID);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param xmlName
	 * @param dataXml
	 * @return
	 */
	@WebMethod
	public String addOrgByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "gfzj") String gfzj,
			@WebParam(name = "parent_org_no") String parent_org_no) {
		String result = null;
		Map<String, String> vars = null;
		try {
			 String xmlPath = xmlName.toUpperCase().contains(".XML") ?
			 GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH +
			 xmlName + ".XML";
			File xmlFile = new File(xmlPath);
			if (!xmlFile.exists()) {
				log.error("请检查传入的xmlName");
				return "1";
			}
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			vars = convertTable2Map(table);
			String tableName = xmlName.toUpperCase().contains(".XML") ? xmlName.toUpperCase().replace(".XML", "") : xmlName.toString();
			result = insertOrg4Map(vars,tableName,gfzj,parent_org_no);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String modifyOrgByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "gfzj") String gfzj) {
		String result = "0";
		Map<String, String> vars = null;
		try {
			 String xmlPath = xmlName.toUpperCase().contains(".XML") ?
			 GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH +
			 xmlName + ".XML";
			File xmlFile = new File(xmlPath);
			if (!xmlFile.exists()) {
				log.error("请检查传入的xmlName");
				return "1";
			}
			String tableName = xmlName.toUpperCase().contains(".XML") ? xmlName.toUpperCase().replace(".XML", "") : xmlName.toString();
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			vars = convertTable2Map(table);
			result = updateOrg4Map(vars,tableName,gfzj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	@WebMethod
	public String delOrgByXml(@WebParam(name = "GFZJ") String GFZJ) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_GROUP  WHERE GFZJ='" + GFZJ + "'");
			log.error("删除一个部门:" + GFZJ);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Table2Map
	 * 
	 * @param table
	 * @return
	 */
	private Map<String, String> convertTable2Map(Table table) {
		Map<String, String> map = new HashMap<String, String>();
		List<Field> xmlfields = table.getFields();
		for (Field field : xmlfields) {
			String fieldname = field.getFieldname();
			String theValue = (StringUtils.isBlank(field.getThevalue()) ? ""
					: field.getThevalue());
			theValue = theValue.contains("'") ? theValue.replace("'", "''")
					: theValue;
			map.put(fieldname, theValue);
		}
		return map;
	}

	private static final int TOPGROUPPID = 0;// 父级id
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
}
