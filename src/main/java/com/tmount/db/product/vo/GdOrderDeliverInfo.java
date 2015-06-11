package com.tmount.db.product.vo;

import java.util.List;

public class GdOrderDeliverInfo {

	private Long orderNo;
	
	private Long shopId;
	
	private List<GdOrderDeliverComputeInfo> itemList;
	
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
    
    public List<GdOrderDeliverComputeInfo> getItemList() {
        return itemList;
    }

    public void setItemList(List<GdOrderDeliverComputeInfo> itemList) {
        this.itemList = itemList;
    }
}
