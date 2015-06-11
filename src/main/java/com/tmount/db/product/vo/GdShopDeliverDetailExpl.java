package com.tmount.db.product.vo;

import com.tmount.db.product.dto.GdShopDeliverDetail;

public class GdShopDeliverDetailExpl extends GdShopDeliverDetail {
    private String deliverName;
    private String provinceName;
    private String cityName;

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

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
}