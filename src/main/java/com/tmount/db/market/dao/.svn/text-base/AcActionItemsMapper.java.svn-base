package com.tmount.db.market.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.market.dto.AcActionItems;
import com.tmount.db.market.dto.AcActionItemsKey;
import com.tmount.db.market.vo.AcActionItemsInfo;

public interface AcActionItemsMapper {
	/**
	 * 得到活动下的商品列表。
	 * @param actionsId
	 * @return
	 */
	List<AcActionItemsInfo> selectActionItemsInfoList(Map<String, Object> map);
	
	Integer selectActionItemsInfoListCount(Map<String, Object> map);
	
	AcActionItemsInfo selectByPKExp(AcActionItemsKey key);
	
    int deleteByPrimaryKey(AcActionItemsKey key);

    int insert(AcActionItems record);

    int insertSelective(AcActionItems record);

    AcActionItems selectByPrimaryKey(AcActionItemsKey key);

    int updateByPrimaryKeySelective(AcActionItems record);

    int updateByPrimaryKey(AcActionItems record);
}