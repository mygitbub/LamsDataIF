package com.bwzk.service.i;

import java.util.List;
import java.util.Map;

import com.bwzk.pojo.SBacklog;

public interface NoticeService {
	/**
	 * 发送信息 到GXT 广梦的即时通讯软件
	 * @param vars
	 * @param usercodess
	 */
	public void sendMsg( String  userCodes , String varsJson) ;
	/**
	 * 得到个人待办
	 * @param usercode
	 */
	public String toDoList(String usercode);
	/**
	 * 测试 得到用户代办列表
	 * @param usercode
	 */
	public List<SBacklog> allBacklog(String usercode);
}
