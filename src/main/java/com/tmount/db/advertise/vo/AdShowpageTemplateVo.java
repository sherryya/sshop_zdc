package com.tmount.db.advertise.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.tmount.db.advertise.dto.AdShowpageTemplate;

public class AdShowpageTemplateVo extends AdShowpageTemplate {
	private String showName;
	
	private Long showId ;

	private Long shopId;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date effDate;
	
	private Integer pageNo;
	
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	
	
}
