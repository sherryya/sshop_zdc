package com.tmount.db.zdc.dto;

public class TrafficRollSub {
    private int id;
    private String region;
    private String type;
    private String direction;
    private String detail;
    private String time;
    private String pointid;

    public TrafficRollSub() {
	super();
    }

    public TrafficRollSub(int id, String region, String type, String direction,
	    String detail, String time, String pointid) {
	super();
	this.id = id;
	this.region = region;
	this.type = type;
	this.direction = direction;
	this.detail = detail;
	this.time = time;
	this.pointid = pointid;
    }

    public String getPointid() {
	return pointid;
    }

    public void setPointid(String pointid) {
	this.pointid = pointid;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getRegion() {
	return region;
    }

    public void setRegion(String region) {
	this.region = region;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getDirection() {
	return direction;
    }

    public void setDirection(String direction) {
	this.direction = direction;
    }

    public String getDetail() {
	return detail;
    }

    public void setDetail(String detail) {
	this.detail = detail;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

}
