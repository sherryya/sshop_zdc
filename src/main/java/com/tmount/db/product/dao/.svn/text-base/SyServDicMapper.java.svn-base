package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.SyServDic;

public interface SyServDicMapper {
    int deleteByPrimaryKey(Integer servtypeId);

    int insert(SyServDic record);

    int insertSelective(SyServDic record);

    SyServDic selectByPrimaryKey(Integer servtypeId);

    int updateByPrimaryKeySelective(SyServDic record);

    int updateByPrimaryKey(SyServDic record);
    
    List<SyServDic> selectSyServDicByShopId(Long shopId);
}