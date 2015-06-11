package com.tmount.business.market.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.market.dao.AcActionInfoMapper;
import com.tmount.db.market.dao.AcActionItemsMapper;
import com.tmount.db.market.dto.AcActionInfo;
import com.tmount.db.market.vo.AcActionItemsInfo;

@Service
public class ActionService {
	@Autowired
	private AcActionInfoMapper acActionInfoMapper;
	
	@Autowired
	private AcActionItemsMapper acActionItemsMapper;

	public List<AcActionInfo> getAcActionInfoListBySelective(AcActionInfo record){
		return acActionInfoMapper.selectBySelective(record);
	}

	/**
	 * 得到商店活动下的商品列表。
	 * @param actionsId
	 * @return
	 */
	public List<AcActionItemsInfo> getAcActionItemsInfoListByActionsId(Map<String, Object> map){
		return acActionItemsMapper.selectActionItemsInfoList(map);
	}
}
