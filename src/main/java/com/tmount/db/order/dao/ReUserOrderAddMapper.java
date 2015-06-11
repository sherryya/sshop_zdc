package com.tmount.db.order.dao;

import java.util.List;

import com.tmount.db.order.dto.ReUserOrderAdd;
import com.tmount.db.order.vo.LogisticInfo;

public interface ReUserOrderAddMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(ReUserOrderAdd record);

    int insertSelective(ReUserOrderAdd record);

    ReUserOrderAdd selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(ReUserOrderAdd record);

    int updateByPrimaryKey(ReUserOrderAdd record);
    
    List<LogisticInfo> selectSendOrderList();
}