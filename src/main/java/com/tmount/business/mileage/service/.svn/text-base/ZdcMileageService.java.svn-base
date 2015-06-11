package com.tmount.business.mileage.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.mileage.dao.ZdcMileageMapper;
import com.tmount.db.mileage.dto.ZdcMileage;
@Service
public class ZdcMileageService{
	@Autowired
	private ZdcMileageMapper  zdcMileageMapper;
	public void insert(ZdcMileage zdcMileage)
	{
		zdcMileageMapper.insert(zdcMileage);
	}
	/**
	 * 里程信息根据设备id和主键id如果主键id不为空
	 * @param zdcMile
	 * @return
	 */
	public  List<ZdcMileage> selectMileInfoServ(ZdcMileage zdcMile)
	{
		return zdcMileageMapper.selectMileInfo(zdcMile);
	}
	

	/**
	 * 根据主键id获得里程信息
	 * @param id
	 * @return
	 */
	public List<ZdcMileage>  selectByPrimaryKey(long id)
	{
		return zdcMileageMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询某一天的里程信息
	 * @param zdcMile
	 * @return
	 */
	public List<ZdcMileage> selectAllDayMile(ZdcMileage zdcMile)
	{
		return zdcMileageMapper.selectAllMileByTime(zdcMile);
	}

}
