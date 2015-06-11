package com.tmount.db.order.dao;

import com.tmount.db.order.dto.RePayLog;

public interface RePayLogMapper {
    int deleteByPrimaryKey(Long payId);

    int insert(RePayLog record);

    int insertSelective(RePayLog record);

    RePayLog selectByPrimaryKey(Long payId);

    int updateByPrimaryKeySelective(RePayLog record);

    int updateByPrimaryKey(RePayLog record);
    
}