package com.tmount.db.order.dto;

public class ReBankOrderAcceptHis {
    private Long bankAccept;

    private Integer orgId;

    private Long orderNo;

    public Long getBankAccept() {
        return bankAccept;
    }

    public void setBankAccept(Long bankAccept) {
        this.bankAccept = bankAccept;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
}