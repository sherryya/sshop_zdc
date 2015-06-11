package com.tmount.db.user.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.sys.vo.StatisTime;
import com.tmount.db.user.dto.UsFavouriteItems;
import com.tmount.db.user.dto.UsFavouriteItemsKey;
import com.tmount.db.user.vo.UsFavouriteItemsDetail;
import com.tmount.db.user.vo.UsUserStatis;

public interface UsFavouriteItemsMapper {
	
	List<UsFavouriteItemsDetail> selectBySelective(Map<String, Object> paramMap);
	int selectBySelectiveCount(Map<String, Object> paramMap);
    int deleteByPrimaryKey(UsFavouriteItemsKey key);

    int insert(UsFavouriteItems record);

    int insertSelective(UsFavouriteItems record);

    UsFavouriteItems selectByPrimaryKey(UsFavouriteItemsKey key);

    int updateByPrimaryKeySelective(UsFavouriteItems record);

    int updateByPrimaryKey(UsFavouriteItems record);
    
    List<UsUserStatis> selectFavouriteByTime(StatisTime statisTime);
}