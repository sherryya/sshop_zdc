package com.tmount.db.product.vo;

public class GdItemDeliverInfo {

    private Integer firstCount;

    private Double firstMoney;

    private Integer addCount;

    private Double addMoney;
    
    private Integer deliverType;

    private String deliverName;
    
    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName == null ? null : deliverName.trim();
    }
    
    public Integer getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(Integer firstCount) {
        this.firstCount = firstCount;
    }

    public Double getFirstMoney() {
        return firstMoney;
    }

    public void setFirstMoney(Double firstMoney) {
        this.firstMoney = firstMoney;
    }

    public Integer getAddCount() {
        return addCount;
    }

    public void setAddCount(Integer addCount) {
        this.addCount = addCount;
    }

    public Double getAddMoney() {
        return addMoney;
    }

    public void setAddMoney(Double addMoney) {
        this.addMoney = addMoney;
    }
    
    public Integer getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }
}
