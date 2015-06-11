package com.tmount.business.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.car.dao.TZdcCarTemperatureMapper;
import com.tmount.db.car.dto.TZdcCarTemperature;



@Service
public class TZdcCarTemperatureServices {
	@Autowired
	private TZdcCarTemperatureMapper tZdcCarTemperatureMapper;  //违章记录mapper
	
	public int insert(TZdcCarTemperature tZdcCarTemperature)
	{
		return tZdcCarTemperatureMapper.insert(tZdcCarTemperature);
	}

	public int updateByPrimaryKeySelective(TZdcCarTemperature tZdcCarTemperature)
	{
		return tZdcCarTemperatureMapper.updateByPrimaryKeySelective(tZdcCarTemperature);
	}

	public TZdcCarTemperature selectByPrimaryKey(TZdcCarTemperature tZdcCarTemperature)
	{
		return tZdcCarTemperatureMapper.selectByPrimaryKey(tZdcCarTemperature);
	}
}
