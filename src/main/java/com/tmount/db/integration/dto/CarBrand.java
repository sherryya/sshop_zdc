package com.tmount.db.integration.dto;

import java.util.Date;
import java.util.List;

public class CarBrand {

	Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	Long carSeriesId;
	String carSeriesName;
	String url;
	Long parentID;
	Date last_time;
	Integer is_hot;
	Integer is_stop;
	String url_itov;
	public String getUrl_itov() {
		return url_itov;
	}
	public void setUrl_itov(String url_itov) {
		this.url_itov = url_itov;
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
	public List<CarBrand> getSubList() {
		return subList;
	}
	public void setSubList(List<CarBrand> subList) {
		this.subList = subList;
	}
	Date crt_time;
    List<CarBrand> subList; 
	public Date getLast_time() {
		return last_time;
	}
	public void setLast_time(Date last_time) {
		this.last_time = last_time;
	}
	public Date getCrt_time() {
		return crt_time;
	}
	public void setCrt_time(Date crt_time) {
		this.crt_time = crt_time;
	}
	public Long getCarSeriesId() {
		return carSeriesId;
	}
	public void setCarSeriesId(Long carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	public String getCarSeriesName() {
		return carSeriesName;
	}
	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getParentID() {
		return parentID;
	}
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}
	
}
