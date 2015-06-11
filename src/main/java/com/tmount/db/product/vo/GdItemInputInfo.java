package com.tmount.db.product.vo;

public class GdItemInputInfo {
	
	private Long itemsId;
	
	private Long shopId;
	
    private Integer itemsType;

    private String itemsName;

    private Double priceBegin;
    
    private Double priceEnd;

    private String deliverFlag;
    
    private Integer cityCode;
    
    private Integer provinceCode;
    
    private Integer pageNo;
    
    private Integer pageSize;
    
    private Integer startLimit;
    
    private Integer orderPrice;
    
    private Integer orderShopLevel;
    
    private Integer orderValue;
    
    private Integer orderSaleCount;
    
    private Integer qualityId;
    
    private Integer standId;
    
    private Integer textureId;
    
    private Integer packId;
    
    private Integer orderByFlag;
    
    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }
    
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    public Integer getItemsType() {
        return itemsType;
    }

    public void setItemsType(Integer itemsType) {
        this.itemsType = itemsType;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName == null ? null : itemsName.trim();
    }

    public Double getPriceBegin() {
        return priceBegin;
    }
    
    public void setPriceBegin(Double priceBegin) {
        this.priceBegin = priceBegin;
    }

    public void setPriceEnd(Double priceEnd) {
        this.priceEnd = priceEnd;
    }
    
    public Double getPriceEnd() {
        return priceEnd;
    }

    public String getDeliverFlag() {
        return deliverFlag;
    }

    public void setDeliverFlag(String deliverFlag) {
        this.deliverFlag = deliverFlag == null ? null : deliverFlag.trim();
    }
    
    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    
    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }
    
    public Integer getOrderShopLevel() {
        return orderShopLevel;
    }

    public void setOrderShopLevel(Integer orderShopLevel) {
        this.orderShopLevel = orderShopLevel;
    }
    
    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }
    
    public Integer getOrderSaleCount() {
        return orderSaleCount;
    }

    public void setOrderSaleCount(Integer orderSaleCount) {
        this.orderSaleCount = orderSaleCount;
    }
    
    public Integer getStartLimit() {
        return startLimit;
    }

    public void setStartLimit(Integer startLimit) {
        this.startLimit = startLimit;
    }
    
    public Integer getQualityId() {
        return qualityId;
    }

    public void setQualityId(Integer qualityId) {
        this.qualityId = qualityId;
    }
    public Integer getStandId() {
        return standId;
    }

    public void setStandId(Integer standId) {
        this.standId = standId;
    }
    
    public Integer getTextureId() {
        return textureId;
    }

    public void setTextureId(Integer textureId) {
        this.textureId = textureId;
    }
    
    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }
    
    public Integer getOrderByFlag() {
        return orderByFlag;
    }

    public void setOrderByFlag(Integer orderByFlag) {
        this.orderByFlag = orderByFlag;
    }
}
