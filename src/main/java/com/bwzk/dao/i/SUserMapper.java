package com.bwzk.dao.i;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bwzk.dao.BaseDao;
import com.bwzk.pojo.SUser;
import com.bwzk.pojo.SUserExample;
import com.bwzk.pojo.SUserWithBLOBs;

public interface SUserMapper extends BaseDao{
    int countByExample(SUserExample example);

    int deleteByExample(SUserExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(SUserWithBLOBs record);

    int insertSelective(SUserWithBLOBs record);

    List<SUserWithBLOBs> selectByExampleWithBLOBs(SUserExample example);

    List<SUser> selectByExample(SUserExample example);

    SUserWithBLOBs selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") SUserWithBLOBs record, @Param("example") SUserExample example);

    int updateByExampleWithBLOBs(@Param("record") SUserWithBLOBs record, @Param("example") SUserExample example);

    int updateByExample(@Param("record") SUser record, @Param("example") SUserExample example);

    int updateByPrimaryKeySelective(SUserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SUserWithBLOBs record);

    int updateByPrimaryKey(SUser record);
    
	@Select("SELECT * FROM S_USER")
    List<SUser> getAllUserList();
	
	@Select("SELECT * FROM S_USER WHERE USERCODE = '${usercode}'")
	SUser getUserByUsercode(@Param("usercode") String usercode);
}