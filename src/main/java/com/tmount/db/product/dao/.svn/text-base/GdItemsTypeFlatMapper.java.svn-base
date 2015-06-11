package com.tmount.db.product.dao;

import com.tmount.db.product.dto.GdItemsTypeFlat;
import com.tmount.db.product.dto.GdItemsTypeFlatKey;

public interface GdItemsTypeFlatMapper {
	
	/**
	 * 删除子节点childItems的所有关联父级节点。
	 * @param childItems
	 * @return
	 */
	int deleteByChildItems(Integer childItems);
	
    int deleteByPrimaryKey(GdItemsTypeFlatKey key);

    int insert(GdItemsTypeFlat record);

    int insertSelective(GdItemsTypeFlat record);

    GdItemsTypeFlat selectByPrimaryKey(GdItemsTypeFlatKey key);

    int updateByPrimaryKeySelective(GdItemsTypeFlat record);

    int updateByPrimaryKey(GdItemsTypeFlat record);
}