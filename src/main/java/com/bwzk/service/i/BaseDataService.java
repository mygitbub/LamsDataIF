package com.bwzk.service.i;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "BaseDataWS", targetNamespace = "http://service.lams.cn/")
public interface BaseDataService {
	/**
	 * Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataTxt
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String addUserByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "user_zj") String user_zj);
	/**
	 *  Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataTxt
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String modifyUserByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "user_zj") String user_zj);

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByTxt(@WebParam(name = "user_zj") String user_zj);
	/**
	 *  Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
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
			@WebParam(name = "parent_org_no") String parent_org_no);
	/**
	 * Title: 修改部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * @param dataTxt
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "dept_zj") String dept_zj);

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */

	@WebMethod
	public String delOrgByTxt(@WebParam(name = "dept_zj") String dept_zj) ;

	/**
	 *  Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String addUserByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "user_zj") String user_zj) ;

	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String modifyUserByJson(
			@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "user_zj") String user_zj);

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByJson(@WebParam(name = "user_zj") String user_zj);

	/**
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
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
			@WebParam(name = "parent_org_no") String parent_org_no);

	/**
	 * Title: 修改部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "dept_zj") String dept_zj);

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod
	public String delOrgByJson(@WebParam(name = "dept_zj") String dept_zj) ;

	/**
	 * Title: 增加用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataXml
	 * @param dept_zj
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String addUserByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "dept_zj") String dept_zj,
			@WebParam(name = "user_zj") String user_zj);
	/**
	 * Title: 修改用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataXml
	 * @param esbid
	 * @return
	 */
	@WebMethod
	public String modifyUserByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "user_zj") String user_zj);

	/**
	 * <p>
	 * Title: 删除用户 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param ESBID
	 * @return
	 */
	@WebMethod
	public String delUserByXml(@WebParam(name = "user_zj") String user_zj);
	/**
	 * Title: 增加部门 返回; 业务主键：成功！ 1：不成功！[失败原因]
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
			@WebParam(name = "parent_org_no") String parent_org_no) ;
	/**
	 * Title: 修改部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataXml
	 * @param gfzj
	 * @return
	 */
	@WebMethod
	public String modifyOrgByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "dept_zj") String dept_zj);

	/**
	 * <p>
	 * Title: 删除部门 返回; 0：成功！ 1：不成功！[失败原因]
	 * </p>
	 * 
	 * @param GFZJ
	 * @return
	 */
	@WebMethod
	public String delOrgByXml(@WebParam(name = "dept_zj") String dept_zj);
	/**
	 * Title: 增加全宗（字符串形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataTxt
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String addQzhByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 * Title: 修改全宗（字符串形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataTxt
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String modifyQzhByTxt(@WebParam(name = "dataTxt") String dataTxt,
			@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 * Title: 删除全宗（字符串形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String delQzhByTxt(@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 *  Title: 增加全宗（JSON形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String addQzhByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 * Title: 修改全宗（JSON形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataJson
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String modifyQzhByJson(@WebParam(name = "dataJson") String dataJson,
			@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 * Title: 删除全宗（JSON形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String delQzhByJson(@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 * Title: 增加全宗（XML形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataXml
	 * @param qzh_zj
	 * @return
	 */
	public String addQzhByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 * Title: 修改全宗（XML形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param dataXml
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String modifyQzhByXml(@WebParam(name = "dataXml") String dataXml,
			@WebParam(name = "qzh_zj") String qzh_zj);
	/**
	 * Title: 删除全宗（XML形式） 返回; 0：成功！ 1：不成功！[失败原因]
	 * @param qzh_zj
	 * @return
	 */
	@WebMethod
	public String delQzhByXml(@WebParam(name = "qzh_zj") String qzh_zj);
}
