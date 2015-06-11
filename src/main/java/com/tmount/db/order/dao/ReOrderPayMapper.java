package com.tmount.db.order.dao;

import java.util.List;

import com.tmount.db.order.dto.ReOrderPay;
import com.tmount.db.order.dto.ReOrderPayKey;

public interface ReOrderPayMapper {
    int deleteByPrimaryKey(ReOrderPayKey key);

    int insert(ReOrderPay record);

    int insertSelective(ReOrderPay record);

    ReOrderPay selectByPrimaryKey(ReOrderPayKey key);

    int updateByPrimaryKeySelective(ReOrderPay record);

    int updateByPrimaryKey(ReOrderPay record);
    
    List<ReOrderPay> selectByOrderNo(Long orderNo);
}