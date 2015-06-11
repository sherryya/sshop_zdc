package com.tmount.db.order.dao;

import java.util.List;

import com.tmount.db.order.dto.ReUserOrderLog;
import com.tmount.db.order.dto.ReUserOrderLogKey;

public interface ReUserOrderLogMapper {

    List<ReUserOrderLog> selectByOrderNo(Long orderNo);

    int deleteByPrimaryKey(ReUserOrderLogKey key);

    int insert(ReUserOrderLog record);

    int insertSelective(ReUserOrderLog record);

    ReUserOrderLog selectByPrimaryKey(ReUserOrderLogKey key);

    int updateByPrimaryKeySelective(ReUserOrderLog record);

    int updateByPrimaryKey(ReUserOrderLog record);
}