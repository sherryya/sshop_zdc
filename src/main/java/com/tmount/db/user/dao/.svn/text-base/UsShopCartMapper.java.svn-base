package com.tmount.db.user.dao;

import java.util.List;

import com.tmount.db.user.dto.UsShopCart;
import com.tmount.db.user.dto.UsShopCartKey;
import com.tmount.db.user.vo.UsShopCartItems;

public interface UsShopCartMapper {
	/**
	 * 根据指定的条件查询购物车中的内容。
	 * @param key
	 * @return
	 */
	List<UsShopCartItems> selectBySelective(UsShopCartKey key);
	
	/**
	 * 得到购物车中商品的数量
	 * @param key
	 * @return
	 */
	Integer selectItemsCount(UsShopCartKey key);

    int deleteByPrimaryKey(UsShopCartKey key);

    int insert(UsShopCart record);

    int insertSelective(UsShopCart record);

    UsShopCart selectByPrimaryKey(UsShopCartKey key);

    int updateByPrimaryKeySelective(UsShopCart record);

    int updateByPrimaryKey(UsShopCart record);
}