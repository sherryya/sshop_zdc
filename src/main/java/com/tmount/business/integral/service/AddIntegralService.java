package com.tmount.business.integral.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.integral.dao.IntegralMapper;
import com.tmount.db.integral.dto.TItovIntegral;
import com.tmount.db.integral.dto.TItovIntegralRule;
import com.tmount.db.integral.dto.TItovIntegralTotal;
import com.tmount.db.user.dto.UsAccount;

@Service
public class AddIntegralService {
	@Autowired
	private IntegralMapper integralMapper;
    
	public void insert_integral(TItovIntegral tItovIntegral)
	{
		integralMapper.insert_integral(tItovIntegral);
	}
	
	public void insert_integral_total(TItovIntegralTotal tItovIntegralTotal)
	{
		integralMapper.insert_integral_total(tItovIntegralTotal);
	}
	
	public void update_integral_total(TItovIntegralTotal tItovIntegralTotal)
	{
		integralMapper.update_integral_total(tItovIntegralTotal);
	}
	
	public TItovIntegralTotal selectTotalByAccount(TItovIntegralTotal tItovIntegralTotal)
	{
		return integralMapper.selectTotalByAccount(tItovIntegralTotal);
	}
	
	public TItovIntegralRule selectIntegralByType(TItovIntegralRule tItovIntegralRule)
	{
		return integralMapper.selectIntegralByType(tItovIntegralRule);
	}
	public List<UsAccount>  selectNewUser(UsAccount usAccount)
	{
		return integralMapper.selectNewUser(usAccount);
	}
	public List<UsAccount>  selectIVR(UsAccount usAccount)
	{
	   return integralMapper.selectIVR(usAccount);
	}
	public List<UsAccount>  selectVOIP(UsAccount usAccount)
	{
		  return integralMapper.selectVOIP(usAccount);
	}
}
