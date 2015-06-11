package com.tmount.db.user.dto;

import java.util.Date;

public class UsDriversSign {
	private Integer ds_id;
	private Integer ds_drivers_id;
	private Integer ds_car_id;
	private Integer ds_type;
	private Date  ds_time;
	private String ds_note;
	private Date ds_create_date;
	public Integer getDs_id() {
		return ds_id;
	}
	public void setDs_id(Integer ds_id) {
		this.ds_id = ds_id;
	}
	public int getDs_drivers_id() {
		return ds_drivers_id;
	}
	public void setDs_drivers_id(Integer ds_drivers_id) {
		this.ds_drivers_id = ds_drivers_id;
	}
	public int getDs_car_id() {
		return ds_car_id;
	}
	public void setDs_car_id(Integer ds_car_id) {
		this.ds_car_id = ds_car_id;
	}
	public int getDs_type() {
		return ds_type;
	}
	public void setDs_type(Integer ds_type) {
		this.ds_type = ds_type;
	}
	public Date getDs_time() {
		return ds_time;
	}
	public void setDs_time(Date ds_time) {
		this.ds_time = ds_time;
	}
	public String getDs_note() {
		return ds_note;
	}
	public void setDs_note(String ds_note) {
		this.ds_note = ds_note;
	}
	public Date getDs_create_date() {
		return ds_create_date;
	}
	public void setDs_create_date(Date ds_create_date) {
		this.ds_create_date = ds_create_date;
	}
}
