package com.tmount.db.order.dto;

import java.util.Date;

public class ReBankAcceptHis extends ReBankAcceptHisKey {
    private Double price;

    private String payType;

    private Date payTime;

    private Date payCommitTime;

    private String checkType;

    private Long payId;

    private String retMsg;

    private String retAccept;

    private Date changeTime;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPayCommitTime() {
        return payCommitTime;
    }

    public void setPayCommitTime(Date payCommitTime) {
        this.payCommitTime = payCommitTime;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg == null ? null : retMsg.trim();
    }

    public String getRetAccept() {
        return retAccept;
    }

    public void setRetAccept(String retAccept) {
        this.retAccept = retAccept == null ? null : retAccept.trim();
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
}