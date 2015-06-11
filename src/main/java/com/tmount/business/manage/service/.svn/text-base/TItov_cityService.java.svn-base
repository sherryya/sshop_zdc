package com.tmount.business.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.manage.dao.TItov_cityManageMapper;
import com.tmount.db.manage.dao.TItov_shop4sManageMapper;
import com.tmount.db.manage.dto.TItov_city_manage;
import com.tmount.db.manage.dto.TItov_shop4s_manage;

@Service
public class TItov_cityService {
	@Autowired
	private TItov_cityManageMapper tItov_cityManageMapper;
	public List<TItov_city_manage> selectByWhere()
	{
		return tItov_cityManageMapper.selectByWhere();
	}
	public List<TItov_city_manage> selectCityByWhere(TItov_city_manage tItov_city_manage)
	{
		return tItov_cityManageMapper.selectCityByWhere(tItov_city_manage);
	}
	public List<TItov_city_manage> selectCityByWhere( )
	{
		return tItov_cityManageMapper.selectCityByWhereAll();
	}
	public List<TItov_city_manage> selectCountryByWhere(TItov_city_manage tItov_city_manage)
	{
		return tItov_cityManageMapper.selectCountryByWhere(tItov_city_manage);
	}
	public List<TItov_city_manage> selectCountryByWhere()
	{
		return tItov_cityManageMapper.selectCountryByWhereAll();
	}
	
}