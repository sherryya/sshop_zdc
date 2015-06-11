package com.tmount.db.user.dto;

import java.util.Date;

public class UsPointConsume implements Comparable<UsPointConsume>{
    private Long loginAccept;

    private Long userId;

    private Integer markWayId;

    private Long oriId;

    private Double usePoint;

    private Date useDate;

    public Long getLoginAccept() {
        return loginAccept;
    }

    public void setLoginAccept(Long loginAccept) {
        this.loginAccept = loginAccept;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMarkWayId() {
        return markWayId;
    }

    public void setMarkWayId(Integer markWayId) {
        this.markWayId = markWayId;
    }

    public Long getOriId() {
        return oriId;
    }

    public void setOriId(Long oriId) {
        this.oriId = oriId;
    }

    public Double getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(Double usePoint) {
        this.usePoint = usePoint;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }
    
	public int compareTo(UsPointConsume o) {
		// TODO Auto-generated method stub
		return this.getUseDate().compareTo(o.getUseDate());
	}
}
