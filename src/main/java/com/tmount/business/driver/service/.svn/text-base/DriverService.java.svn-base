package com.tmount.business.driver.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.driver.dao.DriverMapper;
import com.tmount.db.driver.dto.User;
import com.tmount.db.user.dto.UsDriversInfo;
import com.tmount.db.user.dto.UsDriversSign;
import com.tmount.db.util.dto.CommonBean;
@Service
public class DriverService {
	@Autowired
	private DriverMapper driverMapper;
	public List<User> getUser(User user){
		return driverMapper.getUser(user);
	}
	public List<UsDriversSign> getDriverSignInfo(User user)
	{
		return driverMapper.getDriverSignInfo(user);
	}
	public UsDriversInfo getCarInfoByDriverID(UsDriversInfo usDriversInfo)
	{
		return driverMapper.getCarInfoByDriverID(usDriversInfo);
	}
	public CarInfo getCarInfoByCarID(String cars)
	{
		return driverMapper.getCarInfoByCarID(cars);
	}
	public CommonBean getDeviceUID(Integer car_id)
	{
		return driverMapper.getDeviceUID(car_id);
	}
	
}
