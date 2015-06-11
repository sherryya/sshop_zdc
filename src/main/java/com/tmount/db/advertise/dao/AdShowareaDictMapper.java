package com.tmount.db.advertise.dao;

import java.util.List;

import com.tmount.db.advertise.dto.AdShowareaDict;

public interface AdShowareaDictMapper {
    int deleteByPrimaryKey(Integer showareaId);

    int insert(AdShowareaDict record);

    int insertSelective(AdShowareaDict record);

    AdShowareaDict selectByPrimaryKey(Integer showareaId);

    int updateByPrimaryKeySelective(AdShowareaDict record);

    int updateByPrimaryKey(AdShowareaDict record);
    
    List<AdShowareaDict> selectAreaAll();
}