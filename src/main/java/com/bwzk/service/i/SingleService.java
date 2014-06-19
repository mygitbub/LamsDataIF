package com.bwzk.service.i;

import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService(name="ArcDataWsSingle" , targetNamespace = "http://service.unis.com/")
public interface SingleService {
	/**
	 * <p>Title: fileReciveTxt</p>
	 * <p>Description: 接受数据服务 数据使用 txt 传递,utf编码, 用&;分割 </p>
	 * @param tableName 要传递的xml名称, 不带xml
	 * @param dataTxt   txt 传递的值集合,utf编码, 用&;分割
	 * @param gdrCode   数据的所属人 usercode
	 * @date 2014年6月19日
	*/
	@WebMethod
	public Integer fileReciveTxt(String xmlName , String dataTxt , String gdrCode);
	/**
	 * <p>Title: fileReciveTxt</p>
	 * <p>Description: 接受数据服务 数据使用 xml 传递,utf编码 </p>
	 * @param tableName 要传递的xml名称, 不带xml
	 * @param dataTxt   xm来传递
	 * @param gdrCode   数据的所属人 usercode
	 * @date 2014年6月19日
	*/
	@WebMethod
	public Integer fileReciveXml(String xmlName , String dataXml , String gdrCode) ;
	@WebMethod
	public Integer fileReciveJson(String xmlName , String dataJson , String gdrCode) ;
}
