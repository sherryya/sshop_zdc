package com.tmount.db.product.vo;

import java.util.List;

public class GdOrderDeliverResult {

private Long orderNo;
	
	private Long shopId;
	
	private List<GdItemDeliverResultInfo> deliverList;
	
	public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    public List<GdItemDeliverResultInfo> getDeliverList() {
        return deliverList;
    }

    public void setDeliverList(List<GdItemDeliverResultInfo> deliverList) {
        this.deliverList = deliverList;
    }
}
