package com.tmount.db.order.dto;

import java.util.Date;

public class ReOrderPay extends ReOrderPayKey {
    private Integer payType;

    private Double payMoney;

    private Double payEvkMoney;

    private Date payDate;

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
}