package com.tmount.db.car.dao;

import com.tmount.db.car.dto.TZdcCarhotStrategy;

public interface TZdcCarhotStrategyMapper {
	/**
	 * 添加热车策略
	 */
	int saveCarHotStrategy(TZdcCarhotStrategy tZdcCarhotStrategy);
	/**
	 * 修改热车策略信息
	 */
	int updateCarHotStrategy(TZdcCarhotStrategy tZdcCarhotStrategy);
	/**
	 * 查询此设备号策略是否存在
	 */
	int selectStrategyCount(String id);
	/**
	 * 根据设备号查询策略信息
	 * @param device_id
	 * @return
	 */
	TZdcCarhotStrategy selectStrategyInfo(String device_id);
	/**
	 * 修改车策略的状态
	 * @param tZdcCarhotStrategy
	 * @return
	 */
	int updateCarHotStatus(TZdcCarhotStrategy tZdcCarhotStrategy);
}
