package com.tmount.db.order.vo;

import com.tmount.db.order.dto.GdShopLogistics;

public class GdShopLogisticsExpl extends GdShopLogistics {
    private String provinceName;

    private String cityName;

    private String areaName;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}