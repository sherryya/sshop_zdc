package com.tmount.db.order.dao;

import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.vo.LogisticInfo;

public interface ReOrderDeliverMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(ReOrderDeliver record);

    int insertSelective(ReOrderDeliver record);

    ReOrderDeliver selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(ReOrderDeliver record);

    int updateByPrimaryKey(ReOrderDeliver record);
    
    LogisticInfo selectLogisticInfo(Long orderNo);
}