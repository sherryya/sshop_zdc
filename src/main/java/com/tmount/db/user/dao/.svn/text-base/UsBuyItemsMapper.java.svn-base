package com.tmount.db.user.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.user.dto.UsBuyItems;
import com.tmount.db.user.dto.UsBuyItemsKey;
import com.tmount.db.user.vo.UsBuyItemsInput;
import com.tmount.db.user.vo.UsUserBuyItemsView;

public interface UsBuyItemsMapper {
	/**
	 * 通过订单号从定单明细表（re_user_order_detail）查询记录，并插入已购商品表
	 * @param paramIn
	 * @return
	 */
	int insertFromOrderDetail(Map<String, Object> paramIn);
	
    int deleteByPrimaryKey(UsBuyItemsKey key);

    int insert(UsBuyItems record);

    int insertSelective(UsBuyItems record);

    UsBuyItems selectByPrimaryKey(UsBuyItemsKey key);

    int updateByPrimaryKeySelective(UsBuyItems record);

    int updateByPrimaryKey(UsBuyItems record);
    
    List<UsUserBuyItemsView> selectBySelective(UsBuyItemsInput sBuyItemsInput);
    
    List<UsUserBuyItemsView> selectBuyShopBySelective(UsBuyItemsKey key);

	int selectCountBySelective(UsBuyItemsInput sBuyItemsInput);
    
}