package com.tmount.db.order.dao;

import java.util.List;

import com.tmount.db.order.dto.ReOrderDeliverAdd;
import com.tmount.db.order.dto.ReOrderDeliverAddHis;
import com.tmount.db.order.vo.LogisticInfo;
import com.tmount.db.sys.vo.StatisTime;

public interface ReOrderDeliverAddMapper {
    int insert(ReOrderDeliverAdd record);

    int insertSelective(ReOrderDeliverAdd record);
    
    int deleteByPrimaryKey(ReOrderDeliverAdd record);
    
    List<ReOrderDeliverAdd> selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(ReOrderDeliverAdd record);

    int updateByPrimaryKey(ReOrderDeliverAdd record);
    
    List<LogisticInfo> selectSendOrderList(StatisTime statisTime);
    
    ReOrderDeliverAddHis selectByTxLogisticID(String txLogisticID);
    
    ReOrderDeliverAdd selectByStateType(ReOrderDeliverAdd record);
    
    List<ReOrderDeliverAdd> selectDeliveryHis(Long orderNo);
}