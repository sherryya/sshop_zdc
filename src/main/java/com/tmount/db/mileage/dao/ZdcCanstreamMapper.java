package com.tmount.db.mileage.dao;

import java.util.List;

import com.tmount.db.mileage.dto.ZdcCanstream;

public interface ZdcCanstreamMapper {
	void insert(ZdcCanstream zdcCanstream);

	ZdcCanstream selectForID1(ZdcCanstream zdcCanstream);

	ZdcCanstream selectForID2(ZdcCanstream zdcCanstream);

	List<ZdcCanstream> selectForID3(ZdcCanstream zdcCanstream);

	int updateMileFlag(Long id);

	/**
	 * 查询最新的can盒子数据
	 * 
	 * @param deviceuid
	 * @return
	 */
	ZdcCanstream selectCanNew(String deviceuid);

	/**
	 * 查询最新车门车门状态服务接口
	 * 
	 * @param deviceuid
	 *            设备编号
	 * @return
	 */
	ZdcCanstream selectCarDoorState(String deviceuid);

	/**
	 * 查询最新车门车窗状态服务接口
	 * 
	 * @param deviceuid
	 * @return
	 */
	ZdcCanstream selectCarWindowState(String deviceuid);

	/**
	 * 查询最新车门车胎压服务状态服务接口
	 * 
	 * @param deviceuid
	 * @return
	 */
	ZdcCanstream selectCarPressureState(String deviceuid);
}
