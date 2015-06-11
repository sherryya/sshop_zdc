package com.tmount.db.user.dto;

import java.util.Date;

public class UsUserAccount {
    private Long userId;

    private Double balanceMoney;

    private Double pointValue;

    private Double point;

    private Double evalPoint;

    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public Double getPointValue() {
        return pointValue;
    }

    public void setPointValue(Double pointValue) {
        this.pointValue = pointValue;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Double getEvalPoint() {
        return evalPoint;
    }

    public void setEvalPoint(Double evalPoint) {
        this.evalPoint = evalPoint;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
