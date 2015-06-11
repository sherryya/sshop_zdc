package com.tmount.db.product.dto;

import java.util.Date;

public class GdItemstypeRel extends GdItemstypeRelKey {
    private Integer dataType;

    private Date ordersTime;

    private Integer orders;

    private String upFlag;

    private Date updateTime;

    private String state;

    private Integer updateNo;

    public Date getOrdersTime() {
        return ordersTime;
    }

    public void setOrdersTime(Date ordersTime) {
        this.ordersTime = ordersTime;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getUpFlag() {
        return upFlag;
    }

    public void setUpFlag(String upFlag) {
        this.upFlag = upFlag == null ? null : upFlag.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(Integer updateNo) {
        this.updateNo = updateNo;
    }

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
}