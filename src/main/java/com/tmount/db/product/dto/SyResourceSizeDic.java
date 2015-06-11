package com.tmount.db.product.dto;

public class SyResourceSizeDic {
    private Integer picSizeId;

    private String picSizeType;

    private Integer storeType;

    private String picSizeName;

    public Integer getPicSizeId() {
        return picSizeId;
    }

    public void setPicSizeId(Integer picSizeId) {
        this.picSizeId = picSizeId;
    }

    public String getPicSizeType() {
        return picSizeType;
    }

    public void setPicSizeType(String picSizeType) {
        this.picSizeType = picSizeType == null ? null : picSizeType.trim();
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getPicSizeName() {
        return picSizeName;
    }

    public void setPicSizeName(String picSizeName) {
        this.picSizeName = picSizeName == null ? null : picSizeName.trim();
    }
}