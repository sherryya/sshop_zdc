package com.tmount.db.advertise.vo;

import java.util.Date;

import com.tmount.db.advertise.dto.AdShowsiteDict;

public class AdShowPageSiteVo extends AdShowsiteDict {
	private String shopName;
	
	private String showName;
	
	private Integer siteCount;

	private Date pageEffDate;
	
	private Date pageExpDate;
	
	private Integer showTemplateId;
	
	private String state;
	
	private Integer pageNo;
	
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

	public Integer getSiteCount() {
		return siteCount;
	}

	public void setSiteCount(Integer siteCount) {
		this.siteCount = siteCount;
	}

	public Date getPageEffDate() {
		return pageEffDate;
	}

	public void setPageEffDate(Date pageEffDate) {
		this.pageEffDate = pageEffDate;
	}

	public Date getPageExpDate() {
		return pageExpDate;
	}

	public void setPageExpDate(Date pageExpDate) {
		this.pageExpDate = pageExpDate;
	}

	public Integer getShowTemplateId() {
		return showTemplateId;
	}

	public void setShowTemplateId(Integer showTemplateId) {
		this.showTemplateId = showTemplateId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
}
