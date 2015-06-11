package com.tmount.business.mileage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.mileage.dao.ZdcGpsinfoMapper;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.db.mileage.dto.ZdcMileage;

@Service
public class ZdcGpsinfoService {
    @Autowired
    private ZdcGpsinfoMapper zdcGpsinfoMapper;

    public void insert(ZdcGpsinfo zdcGpsinfo) {
	zdcGpsinfoMapper.insert(zdcGpsinfo);
    }

    /**
     * 得到gps信息根据设备id里程的开始时间和结束时间
     * 
     * @param zdcMile
     * @return
     */
    public List<ZdcGpsinfo> selectGpsInfoByMileSevice(ZdcMileage zdcMile) {
	return zdcGpsinfoMapper.selectGpsInfoByMile(zdcMile);
    }

    public List<ZdcGpsinfo> selectGpsInfoByMileSeviceList(
	    List<ZdcMileage> zdcMile) {
	return zdcGpsinfoMapper.selectGpsInfoByMileList(zdcMile);
    }

    public List<ZdcGpsinfo> selectGpsInfoByMileRange(ZdcMileage zdcMile) {
	return zdcGpsinfoMapper.selectGpsInfoByMileRange(zdcMile);
    }

    public List<ZdcGpsinfo> selectGpsInfoByMileListRange(
	    List<ZdcMileage> zdcMile) {
	return zdcGpsinfoMapper.selectGpsInfoByMileListRange(zdcMile);
    }

    /**
     * 查询最新gps信息根据设备id
     * 
     * @param deviceuid
     * @return
     */
    public ZdcGpsinfo selectNewGps(String deviceuid) {
	return zdcGpsinfoMapper.selectNew(deviceuid);
    }
	/**
	 * 得到有GPS数据的时日期
	 * @param deviceuid
	 * @return
	 */
    public 	List<ZdcGpsinfo> selectDaysofGPS(ZdcGpsinfo zdcGpsinfo)
    {
    	return zdcGpsinfoMapper.selectDaysofGPS(zdcGpsinfo);
    }
}
