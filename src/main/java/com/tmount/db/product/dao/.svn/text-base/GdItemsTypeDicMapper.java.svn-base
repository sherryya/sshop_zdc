package com.tmount.db.product.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.product.dto.GdItemsTypeDic;
import com.tmount.db.product.vo.GdItemListInfo;
import com.tmount.db.product.vo.GdItemstypeDicInfo;

public interface GdItemsTypeDicMapper {
	/**
	 * 返回平台目录列表
	 * @param gdItemsTypeDic
	 * @return
	 */
	List<GdItemsTypeDic> selectItemsList(GdItemsTypeDic gdItemsTypeDic);
	
	/**
	 * 修改平台分类中商品的数量。
	 * @param paramIn
	 * @return
	 */
	int addItemsCount(Map paramIn);
		
    int deleteByPrimaryKey(Integer itemsType);

    int insert(GdItemsTypeDic record);

    int insertSelective(GdItemsTypeDic record);

    GdItemsTypeDic selectByPrimaryKey(Integer itemsType);

    int updateByPrimaryKeySelective(GdItemsTypeDic record);

    int updateByPrimaryKey(GdItemsTypeDic record);
    
    List<GdItemstypeDicInfo> selectByItemIdList(List<GdItemListInfo> gdItemList);
    
    List<GdItemstypeDicInfo> selectAllItemsTypeList();
    

}