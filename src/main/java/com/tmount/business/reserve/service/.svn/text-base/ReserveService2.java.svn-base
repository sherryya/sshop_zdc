package com.tmount.business.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.manage.dao.TItov_cityManageMapper;
import com.tmount.db.manage.dao.TItov_shop4sManageMapper;
import com.tmount.db.manage.dto.TItov_city_manage;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.reserve.dao.ReserveManageMapper;

@Service
public class ReserveService2 {
	@Autowired
	private ReserveManageMapper reserveManageMapper;
	public List<TItov_city_manage> selectByWhere()
	{
		return reserveManageMapper.selectByWhere();
	}
	public List<TItov_city_manage> selectCityByWhere(TItov_city_manage tItov_city_manage)
	{
		return reserveManageMapper.selectCityByWhere(tItov_city_manage);
	}
	public List<TItov_city_manage> selectCityByWhere( )
	{
		return reserveManageMapper.selectCityByWhereAll();
	}
	public List<TItov_city_manage> selectCountryByWhere(TItov_city_manage tItov_city_manage)
	{
		return reserveManageMapper.selectCountryByWhere(tItov_city_manage);
	}
	public List<TItov_city_manage> selectCountryByWhere()
	{
		return reserveManageMapper.selectCountryByWhereAll();
	}
	
}