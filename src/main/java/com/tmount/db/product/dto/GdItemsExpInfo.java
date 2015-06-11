package com.tmount.db.product.dto;

public class GdItemsExpInfo {
    private Long itemsId;

    private Integer foodTypeId;

    private String doFood;

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getDoFood() {
        return doFood;
    }

    public void setDoFood(String doFood) {
        this.doFood = doFood == null ? null : doFood.trim();
    }
}