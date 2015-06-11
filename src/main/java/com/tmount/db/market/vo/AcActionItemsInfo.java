package com.tmount.db.market.vo;

import java.util.Date;

import com.tmount.db.product.dto.GdItemsInfo;

public class AcActionItemsInfo extends GdItemsInfo {
    private Long actionsId;

    private Integer dataType;

    private Date ordersTime;

    private Integer orders;

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

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

	public Long getActionsId() {
		return actionsId;
	}

	public void setActionsId(Long actionsId) {
		this.actionsId = actionsId;
	}
}
