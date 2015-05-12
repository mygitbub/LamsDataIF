package com.bwzk.service.i;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "BaseDataWS", targetNamespace = "http://service.lams.cn/")
public interface BaseDataService {
	/**
	 * Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataTxt
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public Integer addUserByTxt(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "esbid") String esbid);
	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataTxt
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public Integer modifyUserByTxt(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "esbid") String esbid);

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod(operationName = "DelUser")
	public String delUser(@WebParam(name = "ESBID") String ESBID);
	/**
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataTxt
	 * @param gfzj
	 * @param parent_org_no
	 * @return
	 */
	@WebMethod
	public String addOrgByTxt(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "gfzj") String gfzj,
			@WebParam(name = "parent_org_no") String parent_org_no);
	/**
	 * Title: 修改部门  返回; 0：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataTxt
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public Integer modifyOrgByTxt(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "gfzj") String gfzj);

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod(operationName = "DelOrg")
	public String delOrg(@WebParam(name = "GFZJ") String GFZJ);

	/**
	 * Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param tableName
	 * @param dept_zj
	 * @param ESBID
	 * @return
	 */
	public String addUserByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "ESBID") String ESBID);

	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param tableName
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String modifyUserByJson(
			@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "ESBID") String ESBID);

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByJson(@WebParam(name = "ESBID") String ESBID);

	/**
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param tableName
	 * @param gfzj
	 * @param parent_org_no
	 * @return
	 */
	@WebMethod
	public String addOrgByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "gfzj") String gfzj,
			@WebParam(name = "parent_org_no") String parent_org_no);

	/**
	 * Title: 修改部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param tableName
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "tableName") String tableName,
			@WebParam(name = "gfzj") String gfzj);

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod
	public String delOrgByJson(@WebParam(name = "GFZJ") String GFZJ);

	/**
	 *  Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataXml
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String addUserByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "esbid") String esbid);
	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataXml
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String modifyUserByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "esbid") String esbid);

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByXml(@WebParam(name = "ESBID") String ESBID);
	/**
	 *  Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataXml
	 * @param gfzj
	 * @param parent_org_no
	 * @return
	 */
	@WebMethod
	public String addOrgByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "gfzj") String gfzj,
			@WebParam(name = "parent_org_no") String parent_org_no);
	/**
	 * Title: 修改部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param xmlName
	 * @param dataXml
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByXml(@WebParam(name = "xmlName") String xmlName,
			@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "gfzj") String gfzj);

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod
	public String delOrgByXml(@WebParam(name = "GFZJ") String GFZJ);
}
