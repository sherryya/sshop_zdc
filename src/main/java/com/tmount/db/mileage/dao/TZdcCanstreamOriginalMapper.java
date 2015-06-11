package com.tmount.db.mileage.dao;

import java.util.List;

import com.tmount.db.mileage.dto.TZdcCanstreamOriginal;

public interface TZdcCanstreamOriginalMapper {

	/**
	 * 根据设备id查询开始或者结束标识
	 * @param deviceid
	 * @return
	 */
	TZdcCanstreamOriginal selectCanOriginal(String deviceid);
	/**
	 * 插入信息
	 * @param canorignal
	 * @return
	 */
	int insertValues(TZdcCanstreamOriginal canorignal);
}
