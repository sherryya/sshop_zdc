package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdShopItemsRel;
import com.tmount.db.product.dto.GdShopItemsRelKey;
import com.tmount.db.product.vo.GdShopItemsRelInfo;

public interface GdShopItemsRelMapper {
	/**
	 * 根据gdShopItemsRel中的可选字段查询关系信息。
	 * @return
	 */
	List<GdShopItemsRel> selectBySelective(GdShopItemsRel gdShopItemsRel);
	
	int deleteByShopId(Long shopId);
	int deleteByItemsId(Long itemsId);
	int deleteByMap(Map<String, Object> paramMap);
    List <GdShopItemsRelInfo> selectShopItemsRelList(Map<String, Object> paramMap);
    Integer selectShopItemsRelListCount(Map<String, Object> paramMap);
    
    GdShopItemsRelInfo selectByPKExpl(GdShopItemsRelKey key);
    
    int updateByMapSelective(Map<String, Object> paramIn);

    int deleteByPrimaryKey(GdShopItemsRelKey key);

    int insert(GdShopItemsRel record);

    int insertSelective(GdShopItemsRel record);

    GdShopItemsRel selectByPrimaryKey(GdShopItemsRelKey key);

    int updateByPrimaryKeySelective(GdShopItemsRel record);

    int updateByPrimaryKey(GdShopItemsRel record);
}