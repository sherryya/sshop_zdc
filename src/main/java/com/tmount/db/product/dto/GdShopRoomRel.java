package com.tmount.db.product.dto;

public class GdShopRoomRel {
    private Long itemsId;

    private Integer shopFloorId;

    private Integer positionX;

    private Integer positionY;

    private Long i360Pic;

    private Integer menCount;

    private String canCheck;

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

    public Integer getShopFloorId() {
        return shopFloorId;
    }

    public void setShopFloorId(Integer shopFloorId) {
        this.shopFloorId = shopFloorId;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Long getI360Pic() {
        return i360Pic;
    }

    public void setI360Pic(Long i360Pic) {
        this.i360Pic = i360Pic;
    }

    public Integer getMenCount() {
        return menCount;
    }

    public void setMenCount(Integer menCount) {
        this.menCount = menCount;
    }

    public String getCanCheck() {
        return canCheck;
    }

    public void setCanCheck(String canCheck) {
        this.canCheck = canCheck == null ? null : canCheck.trim();
    }
}