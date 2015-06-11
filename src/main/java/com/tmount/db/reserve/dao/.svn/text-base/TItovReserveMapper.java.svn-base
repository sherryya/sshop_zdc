package com.tmount.db.reserve.dao;

import java.util.List;

import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.db.manage.dto.TItov_city_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.db.reserve.dto.TItovReserve;
import com.tmount.db.reserve.dto.TItovReservePlan;

public interface TItovReserveMapper {
	
	void insert(TItovReserve tItovReserve);
	
	int getCountByPhoneNo(String phoneNo);
	
	List<TItov_city_manage> selectByWhere();
	List<TItov_city_manage> selectCityByWhere(TItov_city_manage tItov_city_manage);
	List<TItov_city_manage> selectCountryByWhere(TItov_city_manage tItov_city_manage);
	List<TItov_city_manage> selectCityByWhereAll();
	List<TItov_city_manage> selectCountryByWhereAll();
	
	List<TItovReserve> selectReserveByWhere(TItovReserve tItovReserve);
    Integer selectReserveSizeByWhere(TItovReserve tItovReserve);
    /**
     * 预约信息查询
     */
	List<TItovReservePlan> selectReservePlanByWhere(TItovReservePlan tItovReservePlan);
    Integer selectReservePlanSizeByWhere(TItovReservePlan tItovReservePlan);
    
    /**
     * 预约计划保存
     */
    
	int saveReservePlan(TItovReservePlan tItovReservePlan);
    
    
}