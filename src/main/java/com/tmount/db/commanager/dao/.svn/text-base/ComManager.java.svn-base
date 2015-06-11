package com.tmount.db.commanager.dao;
import java.util.List;

import com.tmount.db.commanager.dto.CompanyCarInfo;
import com.tmount.db.commanager.dto.DriversListInfoByCarID;
import com.tmount.db.commanager.dto.User;
public interface ComManager {
	public User comManagerLogin(User user);
	public List<CompanyCarInfo> getComanyCarInfoAll(CompanyCarInfo companyCarInfo);
	public List<CompanyCarInfo> getComanyCarInfoNotNULL(CompanyCarInfo companyCarInfo);
	public List<CompanyCarInfo> getComanyCarInfoNULL(CompanyCarInfo companyCarInfo);
	public CompanyCarInfo getDriverInfoByCarId(CompanyCarInfo companyCarInfo);
	//<!-- 得到驾驶过本车的所有驾驶员信息（签到过的） -->
	public List<DriversListInfoByCarID> getDriverListInfoByCarId(DriversListInfoByCarID driversListInfoByCarID);
	
}
