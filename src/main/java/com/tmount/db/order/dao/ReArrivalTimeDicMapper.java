package com.tmount.db.order.dao;

import com.tmount.db.order.dto.ReArrivalTimeDic;

public interface ReArrivalTimeDicMapper {
    int deleteByPrimaryKey(Integer arrivalTime);

    int insert(ReArrivalTimeDic record);

    int insertSelective(ReArrivalTimeDic record);

    ReArrivalTimeDic selectByPrimaryKey(Integer arrivalTime);

    int updateByPrimaryKeySelective(ReArrivalTimeDic record);

    int updateByPrimaryKey(ReArrivalTimeDic record);
}