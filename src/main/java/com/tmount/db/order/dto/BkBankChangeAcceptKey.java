package com.tmount.db.order.dto;

public class BkBankChangeAcceptKey {
    private Long bankAccept;

    private Integer orgId;

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
}