package com.tmount.db.user.dto;

import java.util.Date;

public class UsAccountAuxiliary {

	private Integer aa_id;

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	private Long account_id;

	private String account_name;

	private String account_password;

	private Integer account_role_id;

	private Date account_create_date;


	public Long getAccount_id() {
		return account_id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	public Integer getAccount_role_id() {
		return account_role_id;
	}

	public void setAccount_role_id(Integer account_role_id) {
		this.account_role_id = account_role_id;
	}

	public Date getAccount_create_date() {
		return account_create_date;
	}

	public void setAccount_create_date(Date account_create_date) {
		this.account_create_date = account_create_date;
	}

	public Integer getAa_id() {
		return aa_id;
	}

	public void setAa_id(Integer aa_id) {
		this.aa_id = aa_id;
	}

}