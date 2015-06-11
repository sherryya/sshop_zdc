package com.tmount.business.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.db.manage.dto.TItov_city_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.db.reserve.dao.TItovReserveMapper;
import com.tmount.db.reserve.dto.TItovReserve;
import com.tmount.db.reserve.dto.TItovReservePlan;

@Service
public class ReserveService {
	@Autowired
	private TItovReserveMapper tItovReserveMapper;
	
	public void insert(TItovReserve tItovReserve){
		tItovReserveMapper.insert(tItovReserve);
	}
	public int getCountByPhoneNo(String phoneNo){
		return tItovReserveMapper.getCountByPhoneNo(phoneNo);
	}
	
	public List<TItov_city_manage> selectByWhere()
	{
		return tItovReserveMapper.selectByWhere();
	}
	public List<TItov_city_manage> selectCityByWhere(TItov_city_manage tItov_city_manage)
	{
		return tItovReserveMapper.selectCityByWhere(tItov_city_manage);
	}

	public List<TItov_city_manage> selectCountryByWhere(TItov_city_manage tItov_city_manage)
	{
		return tItovReserveMapper.selectCountryByWhere(tItov_city_manage);
	}
	
	
	public List<TItovReserve> selectReserveByWhere(TItovReserve tItovReserve)
	{
		return tItovReserveMapper.selectReserveByWhere(tItovReserve);
	}
	
	public  Integer selectReserveSizeByWhere(TItovReserve tItovReserve)
	{
		return tItovReserveMapper.selectReserveSizeByWhere(tItovReserve);
	}
	/**
	 * 预约计划查询
	 */
	public List<TItovReservePlan> selectReservePlanByWhere(TItovReservePlan tItovReservePlan)
	{
		return tItovReserveMapper.selectReservePlanByWhere(tItovReservePlan);
	}
	
	public  Integer selectReservePlanSizeByWhere(TItovReservePlan tItovReservePlan)
	{
		return tItovReserveMapper.selectReservePlanSizeByWhere(tItovReservePlan);
	}
	/**
	 * 预约计划保存
	 */
	public int saveReservePlan(TItovReservePlan tItovReservePlan)
	{
		return tItovReserveMapper.saveReservePlan(tItovReservePlan);
	}
}
