package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.GdItemsPics;

public interface GdItemsPicsMapper {
	List<GdItemsPics> selectByItemsId(Long itemsId);
}