package com.tmount.db.commanager.dto;

public class CompanyCarInfo {
	///tc.id,tc.carname,tc.carnum,ttu.DeviceUID
	Integer  id;
	String carname;
	String carnum;
	String DeviceUID;
	String driver_name;
	String driver_tel;
	String latitude;
	String longitude;
	String state;
	String car_logo_url;
	String query_flag;
	String user_id;
	String name;
	String tel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getDeviceUID() {
		return DeviceUID;
	}
	public void setDeviceUID(String deviceUID) {
		DeviceUID = deviceUID;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getDriver_tel() {
		return driver_tel;
	}
	public void setDriver_tel(String driver_tel) {
		this.driver_tel = driver_tel;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCar_logo_url() {
		return car_logo_url;
	}
	public void setCar_logo_url(String car_logo_url) {
		this.car_logo_url = car_logo_url;
	}
	public String getQuery_flag() {
		return query_flag;
	}
	public void setQuery_flag(String query_flag) {
		this.query_flag = query_flag;
	}
}
