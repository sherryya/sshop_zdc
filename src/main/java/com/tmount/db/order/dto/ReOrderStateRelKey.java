package com.tmount.db.order.dto;

public class ReOrderStateRelKey {
    private String functionCode;

    private Integer orderCondition;

    private Integer orderState;

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode == null ? null : functionCode.trim();
    }

    public Integer getOrderCondition() {
        return orderCondition;
    }

    public void setOrderCondition(Integer orderCondition) {
        this.orderCondition = orderCondition;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
}