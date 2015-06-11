package com.tmount.business.carinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.carinfo.dao.TZdcCarinfoMapper;
import com.tmount.db.carinfo.dto.TItov_car_info;
@Service
public class CarinfoService {
	@Autowired
	private TZdcCarinfoMapper tZdcCarinfoMapper;
	
	public List<TItov_car_info> selectByWhere(TItov_car_info tItov_car_info)
	{
		return tZdcCarinfoMapper.selectByWhere(tItov_car_info);
	}
	
	public  Integer selectSizeByWhere(TItov_car_info tItov_car_info)
	{
		return tZdcCarinfoMapper.selectSizeByWhere(tItov_car_info);
	}
}
