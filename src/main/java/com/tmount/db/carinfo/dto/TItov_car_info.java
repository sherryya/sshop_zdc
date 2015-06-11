package com.tmount.db.carinfo.dto;

import java.util.Date;

public class TItov_car_info {

	private long car_id;
	private String car_name;
	private String car_plate_number;
	private Integer car_type;
	private String car_type_name;//车型名称
	private String car_engine_num; //发动机号
	private String car_carcase_num;//车架号
	private String car_driving_license_date;//行驶证号期限
	private String car_factory_date;//出厂时间
	private Integer pageSize;
	private Integer startLimit;
	private String car_type_query;
	private String account_name;
	private Date binding_date;
	private String begin_time;
	private String end_time;
	
	public long getCar_id() {
		return car_id;
	}
	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getCar_plate_number() {
		return car_plate_number;
	}
	public void setCar_plate_number(String car_plate_number) {
		this.car_plate_number = car_plate_number;
	}
	public Integer getCar_type() {
		return car_type;
	}
	public void setCar_type(Integer car_type) {
		this.car_type = car_type;
	}
	public String getCar_type_name() {
		return car_type_name;
	}
	public void setCar_type_name(String car_type_name) {
		this.car_type_name = car_type_name;
	}
	public String getCar_engine_num() {
		return car_engine_num;
	}
	public void setCar_engine_num(String car_engine_num) {
		this.car_engine_num = car_engine_num;
	}
	public String getCar_carcase_num() {
		return car_carcase_num;
	}
	public void setCar_carcase_num(String car_carcase_num) {
		this.car_carcase_num = car_carcase_num;
	}
	public String getCar_driving_license_date() {
		return car_driving_license_date;
	}
	public void setCar_driving_license_date(String car_driving_license_date) {
		this.car_driving_license_date = car_driving_license_date;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStartLimit() {
		return startLimit;
	}
	public void setStartLimit(Integer startLimit) {
		this.startLimit = startLimit;
	}
	public String getCar_type_query() {
		return car_type_query;
	}
	public void setCar_type_query(String car_type_query) {
		this.car_type_query = car_type_query;
	}
	public String getCar_factory_date() {
		return car_factory_date;
	}
	public void setCar_factory_date(String car_factory_date) {
		this.car_factory_date = car_factory_date;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public Date getBinding_date() {
		return binding_date;
	}
	public void setBinding_date(Date binding_date) {
		this.binding_date = binding_date;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
