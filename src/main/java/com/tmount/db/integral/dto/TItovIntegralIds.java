package com.tmount.db.integral.dto;

import java.util.Date;
public class TItovIntegralIds {
	private Integer id;

	private Date newuser;

	private Date voip;

	private Date ivr;

	private Date login;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getNewuser() {
		return newuser;
	}

	public void setNewuser(Date newuser) {
		this.newuser = newuser;
	}

	public Date getVoip() {
		return voip;
	}

	public void setVoip(Date voip) {
		this.voip = voip;
	}

	public Date getIvr() {
		return ivr;
	}

	public void setIvr(Date ivr) {
		this.ivr = ivr;
	}

	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}

}
