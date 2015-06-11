package com.tmount.business.mileage.util;

public class OBDBean {
	private String type;  //名称符号
	private String stream; //解析公式
	private String exp;  //表达式
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}

}
