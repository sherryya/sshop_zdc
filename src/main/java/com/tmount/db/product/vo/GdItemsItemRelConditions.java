package com.tmount.db.product.vo;

import java.util.Date;

public class GdItemsItemRelConditions {
    private Long itemsId;
    private Date ordersTime;
    private Integer fetchRows;

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

	public Date getOrdersTime() {
		return ordersTime;
	}

	public void setOrdersTime(Date ordersTime) {
		this.ordersTime = ordersTime;
	}

	public Integer getFetchRows() {
		return fetchRows;
	}

	public void setFetchRows(Integer fetchRows) {
		this.fetchRows = fetchRows;
	}
}