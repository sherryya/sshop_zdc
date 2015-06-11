package com.tmount.db.product.vo;

public class GdItemDeliverComputeInfo extends GdOrderDeliverComputeInfo {
	
	private String isUseMode;
	
	private Long deliverId;
	
	private Integer cityCode;
	
	private Long shopId;
	
	private Double postFee;

    private Double fastPostFee;

    private Double emsFee;
    
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
    
    public String getIsUseMode() {
        return isUseMode;
    }

    public void setIsUseMode(String isUseMode) {
        this.isUseMode = isUseMode == null ? null : isUseMode.trim();
    }

    public Long getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    public Double getPostFee() {
        return postFee;
    }

    public void setPostFee(Double postFee) {
        this.postFee = postFee;
    }

    public Double getFastPostFee() {
        return fastPostFee;
    }

    public void setFastPostFee(Double fastPostFee) {
        this.fastPostFee = fastPostFee;
    }

    public Double getEmsFee() {
        return emsFee;
    }

    public void setEmsFee(Double emsFee) {
        this.emsFee = emsFee;
    }
    
}
