package com.tmount.db.market.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.market.dto.AcActionInfo;

public interface AcActionInfoMapper {
	
	 List<AcActionInfo> selectActionList(Map<String, Object> paramMap);
	 Integer selectActionListCount(Map<String, Object> paramMap);
	
	/**
	 * 根据给出的条件查询活动信息。
	 * @param shopId
	 * @return
	 */
    List<AcActionInfo> selectBySelective(AcActionInfo record);

    int deleteByPrimaryKey(Long actionsId);

    int insert(AcActionInfo record);

    int insertSelective(AcActionInfo record);

    AcActionInfo selectByPrimaryKey(Long actionsId);

    int updateByPrimaryKeySelective(AcActionInfo record);

    int updateByPrimaryKey(AcActionInfo record);
}