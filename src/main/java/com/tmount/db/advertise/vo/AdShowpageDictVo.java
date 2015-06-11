package com.tmount.db.advertise.vo;

import com.tmount.db.advertise.dto.AdShowpageDict;

public class AdShowpageDictVo extends AdShowpageDict {
	private String shopName;
	
	private String showName;
	
	private Integer showTemplateId;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Integer getShowTemplateId() {
		return showTemplateId;
	}

	public void setShowTemplateId(Integer showTemplateId) {
		this.showTemplateId = showTemplateId;
	}
	
	
}
