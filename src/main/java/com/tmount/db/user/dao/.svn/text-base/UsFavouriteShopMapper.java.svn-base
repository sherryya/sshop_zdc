package com.tmount.db.user.dao;

import java.util.List;

import com.tmount.db.user.dto.UsFavouriteShop;
import com.tmount.db.user.dto.UsFavouriteShopKey;
import com.tmount.db.user.vo.UsFavouriteShopInput;

public interface UsFavouriteShopMapper {
    int deleteByPrimaryKey(UsFavouriteShopKey key);

    int insert(UsFavouriteShop record);

    int insertSelective(UsFavouriteShop record);

    UsFavouriteShop selectByPrimaryKey(UsFavouriteShopKey key);

    int updateByPrimaryKeySelective(UsFavouriteShop record);

    int updateByPrimaryKey(UsFavouriteShop record);

	List<UsFavouriteShop> selectByUserId(Long userId);

	List<UsFavouriteShop> selectBySelective(UsFavouriteShopInput usFavouriteShopInput);
	
	int selectCountBySelective(UsFavouriteShopInput usFavouriteShopInput);
}