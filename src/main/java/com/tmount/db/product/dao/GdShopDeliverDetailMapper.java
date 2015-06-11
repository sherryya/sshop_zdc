package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.GdShopDeliver;
import com.tmount.db.product.dto.GdShopDeliverDetail;
import com.tmount.db.product.dto.GdShopDeliverDetailKey;
import com.tmount.db.product.vo.GdItemDeliverInfo;
import com.tmount.db.product.vo.GdShopDeliverDetailExpl;

public interface GdShopDeliverDetailMapper {
	/**
	 * 得到商店运费模板配置的最大序号。
	 * @param key
	 * @return
	 */
	Integer selectMaxOrders(GdShopDeliverDetailKey key);
	
	/**
	 * 得到商店下得所有运费模板配置
	 * @param shopId
	 * @return
	 */
	List<GdShopDeliverDetailExpl> selectByShopId(Long shopId);
	
    
    List<GdItemDeliverInfo> selectByCityCode(GdShopDeliverDetail gdShopDeliverDetail);
    
    List<Integer> selectCityCodeByDeliverId(GdShopDeliver gdShopDeliver);

    int deleteByPrimaryKey(GdShopDeliverDetailKey key);

    int insert(GdShopDeliverDetail record);

    int insertSelective(GdShopDeliverDetail record);

    GdShopDeliverDetail selectByPrimaryKey(GdShopDeliverDetailKey key);

    int updateByPrimaryKeySelective(GdShopDeliverDetail record);

    int updateByPrimaryKey(GdShopDeliverDetail record);
    
}