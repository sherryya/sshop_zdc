package com.tmount.db.driver.dao;

import java.util.List;

import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.driver.dto.User;
import com.tmount.db.user.dto.UsDriversInfo;
import com.tmount.db.user.dto.UsDriversSign;
import com.tmount.db.util.dto.CommonBean;
public interface DriverMapper {

	public List<User> getUser(User user);
	public List<UsDriversSign> getDriverSignInfo(User user);
	public UsDriversInfo getCarInfoByDriverID(UsDriversInfo usDriversInfo);
	public CarInfo getCarInfoByCarID(String cars);
	public CommonBean getDeviceUID(Integer car_id);
}
