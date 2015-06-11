package com.tmount.db.order.dao;

import com.tmount.db.order.dto.ReLogisticsLog;

public interface ReLogisticsLogMapper {
    int deleteByPrimaryKey(Long logisticsLoginId);

    int insert(ReLogisticsLog record);

    int insertSelective(ReLogisticsLog record);

    ReLogisticsLog selectByPrimaryKey(Long logisticsLoginId);

    int updateByPrimaryKeySelective(ReLogisticsLog record);

    int updateByPrimaryKey(ReLogisticsLog record);
}