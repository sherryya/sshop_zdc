package com.tmount.db.user.dto;

import java.util.Date;

public class UsUser {

	private Integer user_id;

	private String user_name;

	private String user_name_other;

	private Integer account_id;

	private String terminal_id;

	private Integer ter_tel_id;

	private Date user_create_date;

	
	

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Date getUser_create_date() {
		return user_create_date;
	}

	public void setUser_create_date(Date user_create_date) {
		this.user_create_date = user_create_date;
	}

	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_name_other() {
		return user_name_other;
	}

	public void setUser_name_other(String user_name_other) {
		this.user_name_other = user_name_other;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public Integer getTer_tel_id() {
		return ter_tel_id;
	}

	public void setTer_tel_id(Integer ter_tel_id) {
		this.ter_tel_id = ter_tel_id;
	}

}