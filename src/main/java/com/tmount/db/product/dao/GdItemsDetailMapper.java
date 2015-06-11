package com.tmount.db.product.dao;

import com.tmount.db.product.dto.GdItemsDetail;

public interface GdItemsDetailMapper {
    GdItemsDetail selectByPrimaryKey(Long itemsId);
}