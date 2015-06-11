package com.tmount.db.ptt.dto;

import java.util.Date;

public class TItov_personal {

	String personal_real_name;
	String personal_sex;
	Long personal_id;
	String personal_tel;
	Date persion_create_date;
	Long account_id;
	String callid;
	String num;
	Integer personal_age;
	public Integer getPersonal_age() {
		return personal_age;
	}
	public void setPersonal_age(Integer personal_age) {
		this.personal_age = personal_age;
	}
	public String getPersonal_email() {
		return personal_email;
	}
	public void setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
	}
	public String getPersonal_qq() {
		return personal_qq;
	}
	public void setPersonal_qq(String personal_qq) {
		this.personal_qq = personal_qq;
	}
	String personal_email;
	String personal_qq;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCallid() {
		return callid;
	}
	public void setCallid(String callid) {
		this.callid = callid;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public Date getPersion_create_date() {
		return persion_create_date;
	}
	public void setPersion_create_date(Date persion_create_date) {
		this.persion_create_date = persion_create_date;
	}
	public String getPersonal_tel() {
		return personal_tel;
	}
	public void setPersonal_tel(String personal_tel) {
		this.personal_tel = personal_tel;
	}
	public Long getPersonal_id() {
		return personal_id;
	}
	public void setPersonal_id(Long personal_id) {
		this.personal_id = personal_id;
	}
	public String getPersonal_real_name() {
		return personal_real_name;
	}
	public void setPersonal_real_name(String personal_real_name) {
		this.personal_real_name = personal_real_name;
	}
	public String getPersonal_sex() {
		return personal_sex;
	}
	public void setPersonal_sex(String personal_sex) {
		this.personal_sex = personal_sex;
	}
}
