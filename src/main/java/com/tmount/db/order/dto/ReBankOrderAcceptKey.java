package com.tmount.db.order.dto;

public class ReBankOrderAcceptKey {
    private Long bankAccept;

    private Long orderNo;

    public Long getBankAccept() {
        return bankAccept;
    }

    public void setBankAccept(Long bankAccept) {
        this.bankAccept = bankAccept;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
}