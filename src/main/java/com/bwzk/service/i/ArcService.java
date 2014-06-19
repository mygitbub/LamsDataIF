package com.bwzk.service.i;

import java.util.List;

import javax.jws.WebService;

import com.bwzk.pojo.MLFieldMapping;
import com.bwzk.pojo.SUser;
@WebService
public interface ArcService {
	/**
	 * 收发文接受服务
	 * @param xml
	 */
	public String fileRecive(String xml);
	/**
	 *同步用户和部门 
	 */
	public String initUserGroup();
	/**
	 * 用户同步服务
	 * @param xml
	 */
	public String userSync(String xml);
	/**
	 * 部门同步服务
	 * @param xml
	 */
	public String groupSync(String xml);
	/**
	 * 全宗同步服务
	 * @param xml
	 */
	public String qzhSync(String xml);
	
	/**
	 * 列出所有用户
	 */
	public List<SUser> listAllUser();
	
	/**
	 * 更新代码表.置null 下次自动更新
	 */
	public void initMlFieldMapping();
	
	/**
	 * 内部得到ml对应关系表
	 */
	public List<MLFieldMapping> getAndInitMLFieldMapping();
	/**
	 * 得到bmid
	 * @param depCode 梦龙的部门code
	 * @return BMID str
	 */
	public String getBmidStrByDepCode(String depCode);
	/**
	 * 得到bmid
	 * @param usercode 
	 * @return BMID str
	 */
	public String getBmidStrByUserCode(String userCode);
}
