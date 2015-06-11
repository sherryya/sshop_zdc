package com.tmount.business.commanager.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.commanager.dao.ComManager;
import com.tmount.db.commanager.dto.CompanyCarInfo;
import com.tmount.db.commanager.dto.User;
@Service
public class ComManagerLogin {
	@Autowired
	private ComManager comManager;
	public User comManagerLogin(User user){
		return comManager.comManagerLogin(user);
	}
	public List<CompanyCarInfo> getComanyCarInfoAll(CompanyCarInfo companyCarInfo){
		return comManager.getComanyCarInfoAll(companyCarInfo);
	}
	public List<CompanyCarInfo> getComanyCarInfoNotNULL(CompanyCarInfo companyCarInfo){
		return comManager.getComanyCarInfoNotNULL(companyCarInfo);
	}
	public List<CompanyCarInfo> getComanyCarInfoNULL(CompanyCarInfo companyCarInfo){
		return comManager.getComanyCarInfoNULL(companyCarInfo);
	}
	
	public CompanyCarInfo getDriverInfoByCarId(CompanyCarInfo companyCarInfo){
		return comManager.getDriverInfoByCarId(companyCarInfo);
	}
}
