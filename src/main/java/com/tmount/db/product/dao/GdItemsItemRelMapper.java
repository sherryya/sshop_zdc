package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.GdSubItemInfo;
import com.tmount.db.product.vo.GdItemsItemRelConditions;

public interface GdItemsItemRelMapper {
    List<GdSubItemInfo> selectByItemsId(GdItemsItemRelConditions gdItemsItemRelConditions);
    List<GdSubItemInfo> selectByItemsIdLimit(GdItemsItemRelConditions gdItemsItemRelConditions);
}