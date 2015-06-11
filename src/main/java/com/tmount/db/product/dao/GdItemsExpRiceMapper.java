package com.tmount.db.product.dao;

import com.tmount.db.product.dto.GdItemsExpRice;

public interface GdItemsExpRiceMapper {
    int deleteByPrimaryKey(Long itemsId);

    int insert(GdItemsExpRice record);

    int insertSelective(GdItemsExpRice record);

    GdItemsExpRice selectByPrimaryKey(Long itemsId);

    int updateByPrimaryKeySelective(GdItemsExpRice record);

    int updateByPrimaryKey(GdItemsExpRice record);
}