package com.tmount.db.order.dto;

import java.util.Date;

public class BkBankChangeAccept extends BkBankChangeAcceptKey {
    private Double price;

    private Date payTime;

    private Integer changeType;

    private Double changeFee;

    private Long payId;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Double getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(Double changeFee) {
        this.changeFee = changeFee;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
}