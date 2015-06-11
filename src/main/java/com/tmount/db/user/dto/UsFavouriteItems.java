package com.tmount.db.user.dto;

import java.util.Date;

public class UsFavouriteItems extends UsFavouriteItemsKey {
    private Long userTagId;

    private Double discount;

    private Date createTime;

    public Long getUserTagId() {
        return userTagId;
    }

    public void setUserTagId(Long userTagId) {
        this.userTagId = userTagId;
    }

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