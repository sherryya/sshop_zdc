package com.tmount.db.user.dto;

import java.util.Date;

public class UsUserComment {
    private Long commentId;

    private Long userId;

    private Long shopId;

    private Long itemsId;

    private String itemsName;

    private Integer commentLevel;

    private Integer itemsDesc;

    private Integer deliveSpeed;

    private Integer serverAttitude;

    private Integer goodsSpeed;

    private Integer averageValue;

    private String commentContent;

    private Date commentTime;

    private Long orderNo;

    private Date endTime;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

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

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName == null ? null : itemsName.trim();
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public Integer getItemsDesc() {
        return itemsDesc;
    }

    public void setItemsDesc(Integer itemsDesc) {
        this.itemsDesc = itemsDesc;
    }

    public Integer getDeliveSpeed() {
        return deliveSpeed;
    }

    public void setDeliveSpeed(Integer deliveSpeed) {
        this.deliveSpeed = deliveSpeed;
    }

    public Integer getServerAttitude() {
        return serverAttitude;
    }

    public void setServerAttitude(Integer serverAttitude) {
        this.serverAttitude = serverAttitude;
    }

    public Integer getGoodsSpeed() {
        return goodsSpeed;
    }

    public void setGoodsSpeed(Integer goodsSpeed) {
        this.goodsSpeed = goodsSpeed;
    }

    public Integer getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Integer averageValue) {
        this.averageValue = averageValue;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}