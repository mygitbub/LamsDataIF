package com.bwzk.dao.i;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bwzk.dao.BaseDao;
import com.bwzk.pojo.SGroup;
import com.bwzk.pojo.SGroupExample;

public interface SGroupMapper  extends BaseDao {
    int countByExample(SGroupExample example);

    int deleteByExample(SGroupExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(SGroup record);

    int insertSelective(SGroup record);

    List<SGroup> selectByExample(SGroupExample example);

    SGroup selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") SGroup record, @Param("example") SGroupExample example);

    int updateByExample(@Param("record") SGroup record, @Param("example") SGroupExample example);

    int updateByPrimaryKeySelective(SGroup record);

    int updateByPrimaryKey(SGroup record);
    
    @Select("SELECT * FROM S_GROUP WHERE DEPCODE = '${depCode}'")
    SGroup getGroupByDepcode(@Param("depCode") String depCode);
}