package com.tmount.db.order.dao;

import java.util.List;

import com.tmount.db.order.dto.ReLogisticsDic;

public interface ReLogisticsDicMapper {
    int deleteByPrimaryKey(Integer logisticsId);

    int insert(ReLogisticsDic record);

    int insertSelective(ReLogisticsDic record);

    ReLogisticsDic selectByPrimaryKey(Integer logisticsId);

    int updateByPrimaryKeySelective(ReLogisticsDic record);

    int updateByPrimaryKey(ReLogisticsDic record);
    
    List<ReLogisticsDic> selectLogistics();
    
    String selectClientId(Integer logisticsId);
}