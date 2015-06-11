package com.tmount.db.user.dto;

import java.util.Date;

public class UsBuyItems extends UsBuyItemsKey {
    private Double discount;

    private Date createTime;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}