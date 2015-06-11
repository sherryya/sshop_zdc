package com.tmount.db.manage.dto;

public class TItov_shop4s_user {
	
	private long account_id;
	private String account_password; 
	private String account_name;                                                         
	private int account_type;             
	private long company_id;         
	private String nickname;
	private String company_name;
	private String real_name;
	private String person_sex;
	private String person_tel;
	private String person_email;
	
	
	private Integer pageSize;
	private Integer startLimit;

	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public int getAccount_type() {
		return account_type;
	}
	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Integer getStartLimit() {
		return startLimit;
	}
	public void setStartLimit(Integer startLimit) {
		this.startLimit = startLimit;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getPerson_sex() {
		return person_sex;
	}
	public void setPerson_sex(String person_sex) {
		this.person_sex = person_sex;
	}
	public String getPerson_tel() {
		return person_tel;
	}
	public void setPerson_tel(String person_tel) {
		this.person_tel = person_tel;
	}
	public String getPerson_email() {
		return person_email;
	}
	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
	
}
