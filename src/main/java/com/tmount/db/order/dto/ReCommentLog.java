package com.tmount.db.order.dto;

public class ReCommentLog extends ReCommentLogKey {
    private Long userId;

    private Long shopId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}