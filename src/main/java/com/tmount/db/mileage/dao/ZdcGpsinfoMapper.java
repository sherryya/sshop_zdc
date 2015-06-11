package com.tmount.db.mileage.dao;

import java.util.List;

import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.db.mileage.dto.ZdcMileage;


public interface ZdcGpsinfoMapper {
	
	void  insert(ZdcGpsinfo zdcGpsinfo);
	/**
	 * 得到gps信息根据设备id里程的开始时间和结束时间
	 * @return
	 */
	List<ZdcGpsinfo> selectGpsInfoByMile(ZdcMileage zdcMile);
	
	List<ZdcGpsinfo> selectGpsInfoByMileList(List<ZdcMileage> zdcMile);
	
	
	List<ZdcGpsinfo> selectGpsInfoByMileRange(ZdcMileage zdcMile);
	
	List<ZdcGpsinfo> selectGpsInfoByMileListRange(List<ZdcMileage> zdcMile);
	
	/**
	 * 查询最新gps信息根据设备id
	 * @param deviceuid
	 * @return
	 */
	ZdcGpsinfo selectNew(String deviceuid);
	
	/**
	 * 得到有GPS数据的时日期
	 * @param deviceuid
	 * @return
	 */
	List<ZdcGpsinfo> selectDaysofGPS(ZdcGpsinfo zdcGpsinfo);
}
