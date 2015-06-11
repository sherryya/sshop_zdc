package com.tmount.db.mileage.dao;
import java.util.List;

import com.tmount.db.mileage.dto.ZdcMileage;

public interface ZdcMileageMapper {
	void insert(ZdcMileage zdcMileage);
	/**
	 * 里程信息根据设备id和主键id如果主键id不为空
	 * @param zdcMile
	 * @return
	 */
	List<ZdcMileage> selectMileInfo(ZdcMileage zdcMile);
	/**
	 * 根据主键id获得里程信息
	 * @param id
	 * @return
	 */
	List<ZdcMileage>  selectByPrimaryKey(long id);
	/**
	 * 查询某一天的里程信息
	 * @param zdcMile
	 * @return
	 */
	List<ZdcMileage> selectAllMileByTime(ZdcMileage zdcMile);

}
