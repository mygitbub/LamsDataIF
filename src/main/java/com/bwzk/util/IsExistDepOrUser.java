package com.bwzk.util;

import java.util.List;

import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SQzh;
import com.bwzk.pojo.SUser;
import com.bwzk.service.BaseService;

public class IsExistDepOrUser extends BaseService {

	public void isDepNotExist(SGroup group) throws ExceptionThrows

	{
		if (group == null) {
			throw new ExceptionThrows("部门不存在");
		}
	}

	public void isUserNotExist(SUser user) throws ExceptionThrows

	{
		if (user == null) {
			throw new ExceptionThrows("用户不存在！");
		}
	}

	public void isUserExist(SUser user) throws ExceptionThrows

	{
		if (user != null) {
			throw new ExceptionThrows("用户已存在！");
		}
	}

	public void isDeptExist(SGroup group) throws ExceptionThrows

	{
		if (group != null) {
			throw new ExceptionThrows("部门已存在！");
		}
	}
	public void isQzhExist(SQzh sqh) throws ExceptionThrows

	{
		if (sqh != null) {
			throw new ExceptionThrows("全宗已存在！");
		}
	}
	public void isQzhNotExist(SQzh sqh) throws ExceptionThrows

	{
		if (sqh == null) {
			throw new ExceptionThrows("全宗不存在！");
		}
	}
	public static void main(String[] args) {
		try {
			new IsExistDepOrUser().isUserExist(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
