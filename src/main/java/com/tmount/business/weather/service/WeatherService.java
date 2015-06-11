package com.tmount.business.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.weather.dao.WeatherReportMapper;
import com.tmount.db.weather.dto.TItovWeathercitycode;

@Service
public class WeatherService {
	@Autowired
	private WeatherReportMapper weatherReportMapper;

	public TItovWeathercitycode getWeatherCityCodeByCityName(TItovWeathercitycode tItovWeathercitycode){
		return weatherReportMapper.getWeatherCityCodeByCityName(tItovWeathercitycode);
	}
}
