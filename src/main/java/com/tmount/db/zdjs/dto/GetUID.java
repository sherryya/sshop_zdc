package com.tmount.db.zdjs.dto;

public class GetUID {
	String terminal_deviceuid;
	String account_name;
	String carSeriesName;
	Long account_id;

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public String getTerminal_deviceuid() {
		return terminal_deviceuid;
	}

	public void setTerminal_deviceuid(String terminal_deviceuid) {
		this.terminal_deviceuid = terminal_deviceuid;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getCarSeriesName() {
		return carSeriesName;
	}

	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}
}
