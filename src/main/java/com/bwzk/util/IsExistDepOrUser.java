package com.bwzk.util;

import org.apache.commons.lang3.StringUtils;

import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SQzh;
import com.bwzk.pojo.SUser;
import com.bwzk.service.BaseService;

public class IsExistDepOrUser extends BaseService {

    /**
     * 部门不存在时异常
     *
     * @param group
     * @throws ExceptionThrows
     */
    public void isDepNotExist(SGroup group, String primaryKey) throws ExceptionThrows

    {
        if (group == null) {
            throw new ExceptionThrows("主键"+primaryKey+"：部门不存在");
        }
    }

    /**
     * 用户不存时异常
     *
     * @param user
     * @throws ExceptionThrows
     */
    public void isUserNotExist(SUser user, String primaryKey) throws ExceptionThrows

    {
        if (user == null) {
            throw new ExceptionThrows("主键"+primaryKey+"：用户不存在！");
        }
    }

    /**
     * 用户存在时异常
     *
     * @param user
     * @throws ExceptionThrows
     */
    public void isUserExist(SUser user) throws ExceptionThrows

    {
        if (user != null) {
            throw new ExceptionThrows("主键"+user.getEsbid()+"：用户已存在！");
        }
    }

    /**
     * 部门存在时异常
     *
     * @param group
     * @throws ExceptionThrows
     */
    public void isDeptExist(SGroup group) throws ExceptionThrows

    {
        if (group != null) {
            throw new ExceptionThrows("主键"+group.getGfzj()+":部门已存在！");
        }
    }

    /**
     * 全宗存在时异常
     *
     * @param sqh
     * @throws ExceptionThrows
     */
    public void isQzhExist(SQzh sqh) throws ExceptionThrows

    {
        if (sqh != null) {
            throw new ExceptionThrows("主键"+sqh.getPrimarykey()+":全宗已存在！");
        }
    }

    /**
     * 全宗不存在时异常
     *
     * @param sqh
     * @throws ExceptionThrows
     */
    public void isQzhNotExist(SQzh sqh, String primaryKey) throws ExceptionThrows

    {
        if (sqh == null) {
            throw new ExceptionThrows("主键"+primaryKey+":全宗不存在！");
        }
    }
    /**
     * 全宗主键不存在异常
    * @Title: isQzhNotExist4Key  
    * @Description: TODO
    * @author mazhongrui 
    * @date 2017年8月2日 上午11:11:34
     */
    public void isQzhNotExist4Key(String qzh, String orgPk) throws ExceptionThrows{
    	if(StringUtils.isBlank(qzh)){
    		throw new ExceptionThrows("主键"+orgPk+":全宗不存在！");
    	}
    }
}
