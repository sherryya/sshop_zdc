package com.tmount.db.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmount.db.order.dto.ReBankAcceptHis;
import com.tmount.db.order.dto.ReBankAcceptHisExample;
import com.tmount.db.order.dto.ReBankAcceptHisKey;

public interface ReBankAcceptHisMapper {
    int countByExample(ReBankAcceptHisExample example);

    int deleteByExample(ReBankAcceptHisExample example);

    int deleteByPrimaryKey(ReBankAcceptHisKey key);

    int insert(ReBankAcceptHis record);

    int insertSelective(ReBankAcceptHis record);

    List<ReBankAcceptHis> selectByExample(ReBankAcceptHisExample example);

    ReBankAcceptHis selectByPrimaryKey(ReBankAcceptHisKey key);

    int updateByExampleSelective(@Param("record") ReBankAcceptHis record, @Param("example") ReBankAcceptHisExample example);

    int updateByExample(@Param("record") ReBankAcceptHis record, @Param("example") ReBankAcceptHisExample example);

    int updateByPrimaryKeySelective(ReBankAcceptHis record);

    int updateByPrimaryKey(ReBankAcceptHis record);
}