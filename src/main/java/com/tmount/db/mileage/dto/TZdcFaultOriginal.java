package com.tmount.db.mileage.dto;

import java.util.Date;
public class TZdcFaultOriginal {
	private Long id;

	private String deviceId;

	private String faultCode;

	private String falutMakeDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFalutMakeDate() {
		return falutMakeDate;
	}

	public void setFalutMakeDate(String falutMakeDate) {
		this.falutMakeDate = falutMakeDate;
	}

}
