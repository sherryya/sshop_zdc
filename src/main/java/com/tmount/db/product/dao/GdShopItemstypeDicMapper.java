package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdShopItemstypeDic;
import com.tmount.db.product.vo.GdItemstypeDicInfo;
import com.tmount.db.product.vo.GdShopItemstypeDicExpl;

public interface GdShopItemstypeDicMapper {
	List<GdShopItemstypeDic> selectSelective(GdShopItemstypeDic gdShopItemstypeDic);
	List<GdShopItemstypeDic> selectTopItemsTypeByShopId(GdShopItemstypeDic gdShopItemstypeDic);
	List<GdShopItemstypeDicExpl> selectTypeList(GdShopItemstypeDic gdShopItemstypeDic);
	GdShopItemstypeDicExpl selectByPKExpl(Integer sitemsType);
	
	int deleteByshopId(Long shopId);
	int deleteByPrimaryKey(Integer sitemsType);

    int insert(GdShopItemstypeDic record);

    int insertSelective(GdShopItemstypeDic record);

    GdShopItemstypeDic selectByPrimaryKey(Integer sitemsType);

    int updateByPrimaryKeySelective(GdShopItemstypeDic record);

    int updateByPrimaryKey(GdShopItemstypeDic record);
    
    List<GdItemstypeDicInfo> selectItemTypeListByShopId(Long shopId);
    
    List<GdItemstypeDicInfo> selectItemTypeListByShopItemIdList(Map<String,Object> gdItemstypeDicMap);
}