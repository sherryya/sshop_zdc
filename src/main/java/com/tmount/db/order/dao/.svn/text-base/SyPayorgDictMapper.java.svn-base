package com.tmount.db.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmount.db.order.dto.SyPayorgDict;
import com.tmount.db.order.dto.SyPayorgDictExample;

public interface SyPayorgDictMapper {
    int countByExample(SyPayorgDictExample example);

    int deleteByExample(SyPayorgDictExample example);

    int deleteByPrimaryKey(Integer orgId);

    int insert(SyPayorgDict record);

    int insertSelective(SyPayorgDict record);

    List<SyPayorgDict> selectByExample(SyPayorgDictExample example);

    SyPayorgDict selectByPrimaryKey(Integer orgId);

    int updateByExampleSelective(@Param("record") SyPayorgDict record, @Param("example") SyPayorgDictExample example);

    int updateByExample(@Param("record") SyPayorgDict record, @Param("example") SyPayorgDictExample example);

    int updateByPrimaryKeySelective(SyPayorgDict record);

    int updateByPrimaryKey(SyPayorgDict record);
}