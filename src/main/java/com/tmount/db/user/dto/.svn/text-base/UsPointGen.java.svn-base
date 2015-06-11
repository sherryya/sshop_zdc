package com.tmount.db.user.dto;

import java.util.Date;

public class UsPointGen implements Comparable<UsPointGen>{
    private Long loginAccept;

    private Long userId;

    private Integer markWayId;

    private Long oriId;

    private String oriName;

    private Double genPoint;

    private Date genDate;

    private Date elfDate;

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

    public String getOriName() {
        return oriName;
    }

    public void setOriName(String oriName) {
        this.oriName = oriName == null ? null : oriName.trim();
    }

    public Double getGenPoint() {
        return genPoint;
    }

    public void setGenPoint(Double genPoint) {
        this.genPoint = genPoint;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    public Date getElfDate() {
        return elfDate;
    }

    public void setElfDate(Date elfDate) {
        this.elfDate = elfDate;
    }

	public int compareTo(UsPointGen o) {
		// TODO Auto-generated method stub
		return this.getGenDate().compareTo(o.getGenDate());
	}
}
