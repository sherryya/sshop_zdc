package com.tmount.db.product.dao;

import java.util.Date;
import java.util.List;

import com.tmount.db.product.dto.GdShopDeliver;
import com.tmount.db.product.dto.GdShopDeliverKey;

public interface GdShopDeliverMapper {
	/**
	 * 得到商店的最大模板ID。 
	 * @param shopId
	 * @return
	 */
	Long selectMaxDeliverId(Long shopId);
	
	/**
	 * 得到商店的所有运费模板
	 * @param shopId
	 * @return
	 */
	List<GdShopDeliver> selectByShopId(Long shopId);
	
    int deleteByPrimaryKey(GdShopDeliverKey key);

    int insert(GdShopDeliver record);

    int insertSelective(GdShopDeliver record);

    GdShopDeliver selectByPrimaryKey(GdShopDeliverKey key);

    int updateByPrimaryKeySelective(GdShopDeliver record);

    int updateByPrimaryKey(GdShopDeliver record);
    
    List<GdShopDeliver> selectByUpdTime(Date updateTime);
}