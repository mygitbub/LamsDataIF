package com.bwzk.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.bwzk.pojo.FDTable;
import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SQzh;
import com.bwzk.pojo.SUser;
import com.bwzk.pojo.jaxb.Field;
import com.bwzk.pojo.jaxb.Table;
import com.bwzk.service.BaseService;
import com.bwzk.service.i.BaseDataService;
import com.bwzk.util.CommonUtil;
import com.bwzk.util.GlobalFinalAttr;
import com.bwzk.util.XmlObjUtil;

@Service("baseDataServiceImpl")
@WebService(name = "BaseDataWS", targetNamespace = "http://service.lams.cn/")
public class BaseDataServiceImpl extends BaseService implements BaseDataService {

	/**
	 * Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataTxt
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String addUserByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "user_zj") String user_zj) {
		Integer maxdid = 0;
		String SQL = "";
		String result = "1";
		Integer pid = null;
		StringBuffer fields = new StringBuffer();
		StringBuffer values = new StringBuffer();
		String xmlPath = GlobalFinalAttr.XML_PATH + "S_USER.XML";
		File xmlFile = new File(xmlPath);
		if (!xmlFile.exists()) {
			log.error("XML文件不存在");
			return result;
		}
		try {
			maxdid = getMaxDid("s_user");
			String xmlString = FileUtils.readFileToString(xmlFile,
					GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if (xmlfields.size() != s.length) {
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {// 循环字段
				String theValue = (StringUtils.isBlank(s[i]) ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''")
						: theValue;
				fields.append(field.getFieldname()).append(",");
				switch (field.getFieldtype()) {
				case 11:
					if (theValue.equals("")) {
						values.append("null,");
					} else {
						values.append(generateTimeToSQLDate(theValue)).append(
								",");
					}
					break;
				case 1:
					values.append("'").append(theValue).append("',");
					break;
				case 3:
					if (StringUtils.isBlank(theValue)) {
						values.append("null ,");
					} else {
						values.append(Integer.parseInt(theValue)).append(",");
					}
					break;
				default:
					values.append("'").append(theValue).append("',");
					break;
				}
				i = i + 1;
			}
			String esbidListSql = "select esbid from s_user ";
			List<String> esbidList = jdbcDao.quert4List(esbidListSql);
			if (esbidList.contains(user_zj)) {
				modifyUserByTxt(dataTxt, user_zj);
			} else {
				SGroup group = sGroupMapper.getGroupByGfzj(dept_zj);
				if (group == null) {
					pid = defaultYhGroup;
				} else {
					pid = group.getDid();
				}
				fields.append("did,pid,esbid,esbcode");
				values.append(maxdid).append(",").append(pid).append(",'")
						.append(user_zj).append("',").append("'")
						.append(dept_zj).append("'");
				SQL = "insert into " + table.getName() + " ("
						+ fields.toString() + ") values ( " + values.toString()
						+ " )";
				execSql(SQL);
				result = "0";
				log.error("插入一条数据成功.addUserByTxt: " + SQL);
			}
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("插入一条数据错误.addUserByTxt:", e);
			log.error(SQL);
		}

		return result;
	}

	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataTxt
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String modifyUserByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "user_zj") String user_zj) {
		String SQL = "";
		String result = "1";
		String xmlPath = GlobalFinalAttr.XML_PATH + "S_USER.XML";
		File xmlFile = new File(xmlPath);
		if (!xmlFile.exists()) {
			log.error("XML文件不存在");
			return result;
		}
		try {
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile,
					GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if (xmlfields.size() != s.length) {
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {// 循环字段
				String theValue = (StringUtils.isBlank(s[i]) ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''")
						: theValue;
				fields.append(field.getFieldname()).append("=");
				switch (field.getFieldtype()) {
				case 11:
					if (theValue.equals("")) {
						fields.append("null,");
					} else {
						fields.append(generateTimeToSQLDate(theValue)).append(
								",");
					}
					break;
				case 1:
					fields.append("'").append(theValue).append("',");
					break;
				case 3:
					if (StringUtils.isBlank(theValue)) {
						fields.append("null ,");
					} else {
						fields.append(Integer.parseInt(theValue)).append(",");
					}
					break;
				default:
					fields.append("'").append(theValue).append("',");
					break;
				}
				i = i + 1;
			}
			SUser user = sUserMapper.getUserByEsbid(user_zj);
			if (user != null) {
				SQL = "update " + table.getName() + " set "
						+ fields.toString().substring(0, fields.length() - 1)
						+ " where esbid = '" + user_zj + "'";
				execSql(SQL);
				result = "0";
				log.error("更新一条数据成功.ModifyUserByTxt: " + SQL);
			} else {
				log.error("修改用户:" + user_zj + "不存在");
			}
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("更新一条数据错误.ModifyUserByTxt:", e);
			log.error(SQL);
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByTxt(@WebParam(name = "user_zj") String user_zj) {
		String result = "0";
		try {
			sUserMapper.delUserByEsbid(user_zj);
			log.error("删除一个用户:" + user_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataTxt
	 * @param gfzj
	 * @param org_name
	 * @param parent_org_no
	 * @return
	 */
	@WebMethod
	public String addOrgByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "org_name") String org_name,
			@WebParam(name = "parent_org_no") String parent_org_no) {
		Integer maxdid = 0;
		String SQL = "";
		String result = "1";
		Integer pid = null;
		String qzh = null;
		String xmlPath = GlobalFinalAttr.XML_PATH + "S_GROUP.XML";
		File xmlFile = new File(xmlPath);
		if (!xmlFile.exists()) {
			log.error("XML文件不存在");
			return result;
		}
		try {
			maxdid = getMaxDid("s_group");
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile,
					GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if (xmlfields.size() != s.length) {
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {// 循环字段
				String theValue = (StringUtils.isBlank(s[i]) ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''")
						: theValue;
				fields.append(field.getFieldname()).append(",");
				switch (field.getFieldtype()) {
				case 11:
					if (theValue.equals("")) {
						values.append("null,");
					} else {
						values.append(generateTimeToSQLDate(theValue)).append(
								",");
					}
					break;
				case 1:
					values.append("'").append(theValue).append("',");
					break;
				case 3:
					if (StringUtils.isBlank(theValue)) {
						values.append("null ,");
					} else {
						values.append(Integer.parseInt(theValue)).append(",");
					}
					break;
				default:
					values.append("'").append(theValue).append("',");
					break;
				}
				i = i + 1;
			}
			String esbidListSql = "select gfzj from s_group ";
			List<String> gfzhList = jdbcDao.quert4List(esbidListSql);
			if (gfzhList.contains(dept_zj)) {
				modifyUserByTxt(dataTxt, dept_zj);
			} else {
				pid = TOPGROUPPID;
				qzh = getQzh(org_name);
				if (StringUtils.isBlank(qzh)) {
					SGroup parent = sGroupMapper.getGroupByBz(parent_org_no);
					pid = (parent == null ? defaultgrouppid : parent.getDid());
					qzh = getQzhByPid(pid);
				}
				fields.append("did,pid,qzh,gfzj,depid");
				values.append(maxdid).append(",").append(pid).append(",'")
						.append(qzh).append("','").append(dept_zj)
						.append("','").append(parent_org_no).append("'");
				SQL = "insert into " + table.getName() + " ("
						+ fields.toString() + ") values ( " + values.toString()
						+ " )";
				execSql(SQL);
				result = dept_zj;
				log.error("插入一条数据成功.addUserByTxt: " + SQL);
				fields.setLength(0);
				values.setLength(0);
			}
		} catch (Exception e) {
			log.error("插入一条数据错误.addUserByTxt:", e);
			log.error(SQL);
		}
		return result;
	}

	/**
	 * Title: 修改部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataTxt
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "dept_zj") String dept_zj) {
		String SQL = "";
		String result = "1";
		String xmlPath = GlobalFinalAttr.XML_PATH + "S_GROUP.XML";
		File xmlFile = new File(xmlPath);
		if (!xmlFile.exists()) {
			log.error("");
			return result;
		}
		try {
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile,
					GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if (xmlfields.size() != s.length) {
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {// 循环字段
				String theValue = (StringUtils.isBlank(s[i]) ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''")
						: theValue;
				fields.append(field.getFieldname()).append("=");
				switch (field.getFieldtype()) {
				case 11:
					if (theValue.equals("")) {
						fields.append("null,");
					} else {
						fields.append(generateTimeToSQLDate(theValue)).append(
								",");
					}
					break;
				case 1:
					fields.append("'").append(theValue).append("',");
					break;
				case 3:
					if (StringUtils.isBlank(theValue)) {
						fields.append("null ,");
					} else {
						fields.append(Integer.parseInt(theValue)).append(",");
					}
					break;
				default:
					fields.append("'").append(theValue).append("',");
					break;
				}
				i = i + 1;
			}
			SGroup sg = sGroupMapper.getGroupByGfzj(dept_zj);
			if (sg != null) {
				SQL = "update " + table.getName() + " set "
						+ fields.toString().substring(0, fields.length() - 1)
						+ " where gfzj = '" + dept_zj + "'";
				execSql(SQL);
				result = "0";
				log.error("更新一条数据成功.addUserByTxt: " + SQL);
			} else {
				log.error("修改部门:" + dept_zj + "不存在");
			}
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("更新一条数据错误.addUserByTxt:", e);
			log.error(SQL);
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod(operationName = "DelOrg")
	public String delOrgByTxt(@WebParam(name = "dept_zj") String dept_zj) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_GROUP  WHERE GFZJ='" + dept_zj + "'");
			log.error("删除一个部门:" + dept_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataJson
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String addUserByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "user_zj") String user_zj) {
		String result = "0";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vars = null;
			vars = mapper.readValue(dataJson, Map.class);
			result = insertUser4Map(vars, dept_zj, user_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataJson
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String modifyUserByJson(
			@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "user_zj") String user_zj) {
		String result = "0";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vars = null;
			vars = mapper.readValue(dataJson, Map.class);
			result = updateUser4Map(vars, user_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByJson(@WebParam(name = "user_zj") String user_zj) {
		String result = "0";
		try {
			sUserMapper.delUserByEsbid(user_zj);
			log.error("删除一个用户:" + user_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataJson
	 * @param gfzj
	 * @param org_name
	 * @param parent_org_no
	 * @return
	 */
	@WebMethod
	public String addOrgByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "org_name") String org_name,
			@WebParam(name = "parent_org_no") String parent_org_no) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> vars = null;
		try {
			vars = mapper.readValue(dataJson, Map.class);
			result = insertOrg4Map(vars, dept_zj, org_name, parent_org_no);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 修改部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataJson
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "dept_zj") String dept_zj) {
		String result = "0";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vars = null;
			vars = mapper.readValue(dataJson, Map.class);
			result = updateOrg4Map(vars, dept_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod
	public String delOrgByJson(@WebParam(name = "dept_zj") String dept_zj) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_GROUP  WHERE GFZJ='" + dept_zj + "'");
			log.error("删除一个部门:" + dept_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataXml
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String addUserByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "user_zj") String user_zj) {
		String result = "0";
		try {
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			Map<String, String> vars = convertTable2Map(table);
			result = insertUser4Map(vars, dept_zj, user_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataXml
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String modifyUserByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "user_zj") String user_zj) {
		String result = "0";
		try {
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			Map<String, String> vars = convertTable2Map(table);
			result = updateUser4Map(vars, user_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByXml(@WebParam(name = "user_zj") String user_zj) {
		String result = "0";
		try {
			sUserMapper.delUserByEsbid(user_zj);
			log.error("删除一个用户:" + user_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataXml
	 * @param gfzj
	 * @param org_name
	 * @param parent_org_no
	 * @return
	 */
	@WebMethod
	public String addOrgByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "org_name") String org_name,
			@WebParam(name = "parent_org_no") String parent_org_no) {
		String result = null;
		Map<String, String> vars = null;
		try {
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			vars = convertTable2Map(table);
			result = insertOrg4Map(vars, dept_zj, org_name, parent_org_no);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 修改部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataXml
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "dept_zj") String dept_zj) {
		String result = "0";
		Map<String, String> vars = null;
		try {
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			vars = convertTable2Map(table);
			result = updateOrg4Map(vars, dept_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod
	public String delOrgByXml(@WebParam(name = "dept_zj") String dept_zj) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_GROUP  WHERE GFZJ='" + dept_zj + "'");
			log.error("删除一个部门:" + dept_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加全宗（字符串形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataTxt
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String addQzhByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "qzh_zj") String qzh_zj) {
		Integer maxdid = 0;
		String SQL = "";
		String result = "1";
		// String xmlPath = xmlName.toUpperCase().contains(".XML") ?
		// GlobalFinalAttr.XML_PATH + xmlName : GlobalFinalAttr.XML_PATH +
		// xmlName + ".XML";
		String xmlPath = GlobalFinalAttr.XML_PATH + "S_QZH.XML";
		File xmlFile = new File(xmlPath);
		if (!xmlFile.exists()) {
			log.error("XML文件不存在");
			return result;
		}
		try {
			maxdid = getMaxDid("s_qzh");
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile,
					GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if (xmlfields.size() != s.length) {
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {// 循环字段
				String theValue = (StringUtils.isBlank(s[i]) ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''")
						: theValue;
				fields.append(field.getFieldname()).append(",");
				switch (field.getFieldtype()) {
				case 11:
					if (theValue.equals("")) {
						values.append("null,");
					} else {
						values.append(generateTimeToSQLDate(theValue)).append(
								",");
					}
					break;
				case 1:
					values.append("'").append(theValue).append("',");
					break;
				case 3:
					if (StringUtils.isBlank(theValue)) {
						values.append("null ,");
					} else {
						values.append(Integer.parseInt(theValue)).append(",");
					}
					break;
				default:
					values.append("'").append(theValue).append("',");
					break;
				}
				i = i + 1;
			}
			String keyListSql = "select primarykey from s_qzh ";
			List<String> keyList = jdbcDao.quert4List(keyListSql);
			if (keyList.contains(qzh_zj)) {
				modifyQzhByTxt(dataTxt, qzh_zj);
			} else {
				fields.append("did,primarykey");
				values.append(maxdid).append(",'").append(qzh_zj).append("'");
				SQL = "insert into " + table.getName() + " ("
						+ fields.toString() + ") values ( " + values.toString()
						+ " )";
				execSql(SQL);
				result = "0";
				log.error("插入一条数据成功.addQzhByTxt: " + SQL);
				fields.setLength(0);
				values.setLength(0);
			}
		} catch (Exception e) {
			log.error("插入一条数据错误.addQzhByTxt:", e);
			log.error(SQL);
		}
		return result;
	}

	/**
	 * Title: 修改全宗（字符串形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataTxt
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String modifyQzhByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "qzh_zj") String qzh_zj) {
		String SQL = "";
		String result = "1";
		String xmlPath = GlobalFinalAttr.XML_PATH + "S_USER.XML";
		File xmlFile = new File(xmlPath);
		if (!xmlFile.exists()) {
			log.error("XML文件不存在");
			return result;
		}
		try {
			StringBuffer fields = new StringBuffer();
			StringBuffer values = new StringBuffer();
			String xmlString = FileUtils.readFileToString(xmlFile,
					GlobalFinalAttr.PRJ_CHAR_CODE);
			Table table = XmlObjUtil.xml2Obj(xmlString, Table.class);
			List<Field> xmlfields = table.getFields();// 读取xml配置信息获取要写入的各个字段
			String[] s = dataTxt.split(GlobalFinalAttr.SEPARATOR);
			if (xmlfields.size() != s.length) {
				log.error("传入的值于xml的数量不符合");
				return result;
			}
			int i = 0;
			for (Field field : xmlfields) {// 循环字段
				String theValue = (StringUtils.isBlank(s[i]) ? "" : s[i]);
				theValue = theValue.contains("'") ? theValue.replace("'", "''")
						: theValue;
				fields.append(field.getFieldname()).append("=");
				switch (field.getFieldtype()) {
				case 11:
					if (theValue.equals("")) {
						fields.append("null,");
					} else {
						fields.append(generateTimeToSQLDate(theValue)).append(
								",");
					}
					break;
				case 1:
					fields.append("'").append(theValue).append("',");
					break;
				case 3:
					if (StringUtils.isBlank(theValue)) {
						fields.append("null ,");
					} else {
						fields.append(Integer.parseInt(theValue)).append(",");
					}
					break;
				default:
					fields.append("'").append(theValue).append("',");
					break;
				}
				i = i + 1;
			}
			SQzh sqh = sGroupMapper.getQzhByQzzj(qzh_zj);
			if (sqh != null) {
				SQL = "update " + table.getName() + " set "
						+ fields.toString().substring(0, fields.length() - 1)
						+ " where primarykey = '" + qzh_zj + "'";
				execSql(SQL);
				result = "0";
				log.error("更新一条数据成功.modifyQzhByTxt: " + SQL);
			} else {
				log.error("修改全宗:" + qzh_zj + "不存在");
			}
			fields.setLength(0);
			values.setLength(0);
		} catch (Exception e) {
			log.error("更新一条数据错误.modifyQzhByTxt:", e);
			log.error(SQL);
		}
		return result;
	}

	/**
	 * Title: 删除全宗（字符串形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String delQzhByTxt(@WebParam(name = "qzh_zj") String qzh_zj) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_QZH  WHERE primarykey='" + qzh_zj + "'");
			log.error("删除一个全宗:" + qzh_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加全宗（JSON形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataJson
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String addQzhByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "qzh_zj") String qzh_zj) {
		String archKey = "";
		String archVal = "";
		String result = "1";
		FDTable fDtable = null;
		List<FDTable> fDTableList = null;
		Map<String, String> map = null;
		StringBuffer fields = new StringBuffer();
		StringBuffer values = new StringBuffer();
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(dataJson, Map.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		if (null != map && null != map.keySet() && map.keySet().size() > 0) {
			try {
				Integer maxdid = getMaxDid("s_qzh");
				fDTableList = sGroupMapper.getFtableList("F_S_GROUP");
				Set<String> fieldSet = map.keySet();
				for (String outSysField : fieldSet) {
					archKey = outSysField;
					archVal = map.get(outSysField);
					if (StringUtils.isNotBlank(archVal)
							&& StringUtils.isNotBlank(archKey)) {
						archVal = (StringUtils.isBlank(archVal) ? "" : archVal);
						archVal = (archVal.contains("'") ? archVal.replace("'",
								"''") : archVal);// 兼容单引号
						fDtable = CommonUtil.getFDtable(fDTableList, archKey);
						fields.append(fDtable.getFieldname()).append(",");
						switch (fDtable.getFieldtype()) {
						case 11:
							if (archVal.equals("")) {
								values.append("sysdate,");
							} else {
								values.append(generateTimeToSQLDate(archVal))
										.append(",");
							}
							break;
						case 1:
							values.append("'").append(archVal).append("',");
							break;
						case 3:
							if (StringUtils.isBlank(archVal)) {
								values.append("null ,");
							} else {
								values.append(Integer.parseInt(archVal))
										.append(",");
							}
							break;
						default:
							values.append("'").append(archVal).append("',");
							break;
						}
					}
				}
				String keyListSql = "select primarykey from s_qzh ";
				List<String> keyList = jdbcDao.quert4List(keyListSql);
				if (keyList.contains(qzh_zj)) {
					modifyQzhByJson(dataJson, qzh_zj);
				} else {
					fields.append("did,primarykey");
					values.append(maxdid).append(",'").append(qzh_zj)
							.append("'");
					String SQL = "insert into s_qzh (" + fields.toString()
							+ ") values ( " + values.toString() + " )";
					execSql(SQL);
					result = "0";
					log.error("插入一条数据成功.addQzhByTxt: " + SQL);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("插入一条数据失败.insertOrg4Map: " + e.getMessage());
			}
		} else {
			result = "1";
		}
		fields.setLength(0);
		values.setLength(0);
		return result;

	}

	/**
	 * Title: 修改全宗（JSON形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataJson
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String modifyQzhByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "qzh_zj") String qzh_zj) {
		String archKey = "";
		String archVal = "";
		String result = "1";
		FDTable fDtable = null;
		List<FDTable> fDTableList = null;
		Map<String, String> map = null;
		StringBuffer fields = new StringBuffer();
		StringBuffer values = new StringBuffer();
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(dataJson, Map.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		if (null != map && null != map.keySet() && map.keySet().size() > 0) {
			try {
				fDTableList = sGroupMapper.getFtableList("F_S_GROUP");
				Set<String> fieldSet = map.keySet();
				for (String outSysField : fieldSet) {
					archKey = outSysField;
					archVal = map.get(outSysField);
					if (StringUtils.isNotBlank(archVal)
							&& StringUtils.isNotBlank(archKey)) {
						archVal = (StringUtils.isBlank(archVal) ? "" : archVal);
						archVal = (archVal.contains("'") ? archVal.replace("'",
								"''") : archVal);// 兼容单引号
						fDtable = CommonUtil.getFDtable(fDTableList, archKey);
						fields.append(fDtable.getFieldname()).append("=");
						switch (fDtable.getFieldtype()) {
						case 11:
							if (archVal.equals("")) {
								fields.append("sysdate,");
							} else {
								fields.append(generateTimeToSQLDate(archVal))
										.append(",");
							}
							break;
						case 1:
							fields.append("'").append(archVal).append("',");
							break;
						case 3:
							if (StringUtils.isBlank(archVal)) {
								fields.append("null ,");
							} else {
								fields.append(Integer.parseInt(archVal))
										.append(",");
							}
							break;
						default:
							fields.append("'").append(archVal).append("',");
							break;
						}
					}
				}
				SQzh sqh = sGroupMapper.getQzhByQzzj(qzh_zj);
				if (sqh != null) {
					String SQL = "update s_qzh set "
							+ fields.toString().substring(0,
									fields.length() - 1)
							+ " where primarykey = '" + qzh_zj + "'";
					System.out.println(SQL);
					execSql(SQL);
					result = "0";
					log.error("更新一条数据成功.updateOrg4Map: " + SQL);
				} else {
					result = "1";
					log.error("修改全宗:" + qzh_zj + "不存在");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("更新一条数据失败.updateOrg4Map: " + e.getMessage());
			}
		} else {
			result = "1";
		}
		fields.setLength(0);
		values.setLength(0);
		return result;
	}

	/**
	 * Title: 删除全宗（JSON形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String delQzhByJson(@WebParam(name = "qzh_zj") String qzh_zj) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_QZH  WHERE primarykey='" + qzh_zj + "'");
			log.error("删除一个全宗:" + qzh_zj);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		return result;
	}

	/**
	 * Title: 增加全宗（XML形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param dataXml
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String addQzhByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "qzh_zj") String qzh_zj) {
		String archKey = "";
		String archVal = "";
		String result = "1";
		FDTable fDtable = null;
		List<FDTable> fDTableList = null;
		Map<String, String> map = null;
		StringBuffer fields = new StringBuffer();
		StringBuffer values = new StringBuffer();
		try {
			String xmlPath = GlobalFinalAttr.XML_PATH + "S_USER.XML";
			File xmlFile = new File(xmlPath);
			if (!xmlFile.exists()) {
				log.error("XML文件不存在");
				return "1";
			}
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			map = convertTable2Map(table);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		if (null != map && null != map.keySet() && map.keySet().size() > 0) {
			try {
				Integer maxdid = getMaxDid("s_qzh");
				fDTableList = sGroupMapper.getFtableList("F_S_QZH");
				Set<String> fieldSet = map.keySet();
				for (String outSysField : fieldSet) {
					archKey = outSysField;
					archVal = map.get(outSysField);
					if (StringUtils.isNotBlank(archVal)
							&& StringUtils.isNotBlank(archKey)) {
						archVal = (StringUtils.isBlank(archVal) ? "" : archVal);
						archVal = (archVal.contains("'") ? archVal.replace("'",
								"''") : archVal);// 兼容单引号
						fDtable = CommonUtil.getFDtable(fDTableList, archKey);
						fields.append(fDtable.getFieldname()).append(",");
						switch (fDtable.getFieldtype()) {
						case 11:
							if (archVal.equals("")) {
								values.append("sysdate,");
							} else {
								values.append(generateTimeToSQLDate(archVal))
										.append(",");
							}
							break;
						case 1:
							values.append("'").append(archVal).append("',");
							break;
						case 3:
							if (StringUtils.isBlank(archVal)) {
								values.append("null ,");
							} else {
								values.append(Integer.parseInt(archVal))
										.append(",");
							}
							break;
						default:
							values.append("'").append(archVal).append("',");
							break;
						}
					}
				}
				String keyListSql = "select primarykey from s_qzh ";
				List<String> keyList = jdbcDao.quert4List(keyListSql);
				if (keyList.contains(qzh_zj)) {
					modifyQzhByXml(dataXml, qzh_zj);
				} else {
					fields.append("did,primarykey");
					values.append(maxdid).append(",'").append(qzh_zj)
							.append("'");
					String SQL = "insert into s_qzh (" + fields.toString()
							+ ") values ( " + values.toString() + " )";
					execSql(SQL);
					result = "0";
					log.error("插入一条数据成功.addQzhByTxt: " + SQL);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("插入一条数据失败.insertOrg4Map: " + e.getMessage());
			}
		} else {
			result = "1";
		}
		fields.setLength(0);
		values.setLength(0);
		return result;

	}

	@WebMethod
	public String modifyQzhByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "qzh_zj") String qzh_zj) {
		String archKey = "";
		String archVal = "";
		String result = "1";
		FDTable fDtable = null;
		List<FDTable> fDTableList = null;
		Map<String, String> map = null;
		StringBuffer fields = new StringBuffer();
		StringBuffer values = new StringBuffer();
		try {
			String xmlPath = GlobalFinalAttr.XML_PATH + "S_USER.XML";
			File xmlFile = new File(xmlPath);
			if (!xmlFile.exists()) {
				log.error("XML文件不存在");
				return "1";
			}
			Table table = XmlObjUtil.xml2Obj(dataXml, Table.class);
			map = convertTable2Map(table);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "1[" + e.getMessage() + "]";
		}
		if (null != map && null != map.keySet() && map.keySet().size() > 0) {
			try {
				fDTableList = sGroupMapper.getFtableList("F_S_QZH");
				Set<String> fieldSet = map.keySet();
				for (String outSysField : fieldSet) {
					archKey = outSysField;
					archVal = map.get(outSysField);
					if (StringUtils.isNotBlank(archVal)
							&& StringUtils.isNotBlank(archKey)) {
						archVal = (StringUtils.isBlank(archVal) ? "" : archVal);
						archVal = (archVal.contains("'") ? archVal.replace("'",
								"''") : archVal);// 兼容单引号
						fDtable = CommonUtil.getFDtable(fDTableList, archKey);
						fields.append(fDtable.getFieldname()).append("=");
						switch (fDtable.getFieldtype()) {
						case 11:
							if (archVal.equals("")) {
								fields.append("sysdate,");
							} else {
								fields.append(generateTimeToSQLDate(archVal))
										.append(",");
							}
							break;
						case 1:
							fields.append("'").append(archVal).append("',");
							break;
						case 3:
							if (StringUtils.isBlank(archVal)) {
								fields.append("null ,");
							} else {
								fields.append(Integer.parseInt(archVal))
										.append(",");
							}
							break;
						default:
							fields.append("'").append(archVal).append("',");
							break;
						}
					}
				}
				SQzh sqh = sGroupMapper.getQzhByQzzj(qzh_zj);
				if (sqh != null) {
					String SQL = "update s_qzh set "
							+ fields.toString().substring(0,
									fields.length() - 1)
							+ " where primarykey = '" + qzh_zj + "'";
					System.out.println(SQL);
					execSql(SQL);
					result = "0";
					log.error("更新一条数据成功.updateOrg4Map: " + SQL);
				} else {
					result = "1";
					log.error("修改全宗:" + qzh_zj + "不存在");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("更新一条数据失败.updateOrg4Map: " + e.getMessage());
			}
		} else {
			result = "1";
		}
		fields.setLength(0);
		values.setLength(0);
		return result;
	}

	/**
	 * Title: 删除全宗（XML形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * 
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String delQzhByXml(@WebParam(name = "qzh_zj") String qzh_zj) {
		String result = "0";
		try {
			jdbcDao.excute("DELETE S_QZH  WHERE primarykey='" + qzh_zj + "'");
			log.error("删除一个全宗:" + qzh_zj);
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
