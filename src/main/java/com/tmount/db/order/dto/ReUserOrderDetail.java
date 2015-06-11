package com.tmount.db.order.dto;

import java.util.Date;

public class ReUserOrderDetail extends ReUserOrderDetailKey {
	
    private Integer dataType;

    private Integer itemsType;

    private Integer acount;

    private Double price;

    private Double allPrice;

    private Date opTime;

    private Integer getPoint;

    private String giftStatus;

    private Long actionsId;

    private Integer state;

    public Integer getItemsType() {
        return itemsType;
    }

    public void setItemsType(Integer itemsType) {
        this.itemsType = itemsType;
    }

    public Integer getAcount() {
        return acount;
    }

    public void setAcount(Integer acount) {
        this.acount = acount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Double allPrice) {
        this.allPrice = allPrice;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Integer getGetPoint() {
        return getPoint;
    }

    public void setGetPoint(Integer getPoint) {
        this.getPoint = getPoint;
    }

    public String getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(String giftStatus) {
        this.giftStatus = giftStatus == null ? null : giftStatus.trim();
    }

    public Long getActionsId() {
        return actionsId;
    }

    public void setActionsId(Long actionsId) {
        this.actionsId = actionsId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
}