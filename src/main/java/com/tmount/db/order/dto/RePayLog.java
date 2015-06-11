package com.tmount.db.order.dto;

import java.util.Date;

public class RePayLog {
    private Long payId;

    private Long userId;

    private Integer payType;

    private Double payMoney;

    private Integer payWayId;

    private Double payEvkMoney;

    private Date payDate;

    private String payOtherId;
    
    private Integer orgId;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayWayId() {
        return payWayId;
    }

    public void setPayWayId(Integer payWayId) {
        this.payWayId = payWayId;
    }

    public Double getPayEvkMoney() {
        return payEvkMoney;
    }

    public void setPayEvkMoney(Double payEvkMoney) {
        this.payEvkMoney = payEvkMoney;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayOtherId() {
        return payOtherId;
    }

    public void setPayOtherId(String payOtherId) {
        this.payOtherId = payOtherId == null ? null : payOtherId.trim();
    }

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
}