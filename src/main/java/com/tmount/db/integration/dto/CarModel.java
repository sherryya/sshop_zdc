package com.tmount.db.integration.dto;

import java.util.Date;


public class CarModel {

	Integer id;
	Integer car_model_id_golo;
	String car_model_name;
	String car_url;
	Integer car_brand_id;
	Integer car_brand_id_golo;
	Date last_time;
	Integer is_hot;
	Integer is_stop;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCar_model_id_golo() {
		return car_model_id_golo;
	}
	public void setCar_model_id_golo(Integer car_model_id_golo) {
		this.car_model_id_golo = car_model_id_golo;
	}
	public String getCar_model_name() {
		return car_model_name;
	}
	public void setCar_model_name(String car_model_name) {
		this.car_model_name = car_model_name;
	}
	public String getCar_url() {
		return car_url;
	}
	public void setCar_url(String car_url) {
		this.car_url = car_url;
	}
	public Integer getCar_brand_id() {
		return car_brand_id;
	}
	public void setCar_brand_id(Integer car_brand_id) {
		this.car_brand_id = car_brand_id;
	}
	public Integer getCar_brand_id_golo() {
		return car_brand_id_golo;
	}
	public void setCar_brand_id_golo(Integer car_brand_id_golo) {
		this.car_brand_id_golo = car_brand_id_golo;
	}
	public Date getLast_time() {
		return last_time;
	}
	public void setLast_time(Date last_time) {
		this.last_time = last_time;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Integer getIs_stop() {
		return is_stop;
	}
	public void setIs_stop(Integer is_stop) {
		this.is_stop = is_stop;
	}

}
