package com.tmount.db.order.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.order.dto.GdShopLogistics;
import com.tmount.db.order.dto.GdShopLogisticsKey;
import com.tmount.db.order.vo.GdShopLogisticsExpl;

public interface GdShopLogisticsMapper {
    Integer selectMaxIdByShopId(Long shopId);
    List<GdShopLogisticsExpl> selectItemList(Map<String, Object> paramIn);
    int selectItemListCount(Map<String, Object> paramIn);

    int deleteByPrimaryKey(GdShopLogisticsKey key);

    int insert(GdShopLogistics record);

    int insertSelective(GdShopLogistics record);

    GdShopLogistics selectByPrimaryKey(GdShopLogisticsKey key);

    int updateByPrimaryKeySelective(GdShopLogistics record);

    int updateByPrimaryKey(GdShopLogistics record);
    
    List<GdShopLogistics> selectListByShopId(Long shopId);
}