package com.tmount.db.integral.dao;

import java.util.List;

import com.tmount.db.integral.dto.TItovIntegral;
import com.tmount.db.integral.dto.TItovIntegralRule;
import com.tmount.db.integral.dto.TItovIntegralTotal;
import com.tmount.db.user.dto.UsAccount;

public interface IntegralMapper {
	void insert_integral(TItovIntegral tItovIntegral);

	void insert_integral_total(TItovIntegralTotal tItovIntegralTotal);

	TItovIntegralTotal selectTotalByAccount(	TItovIntegralTotal tItovIntegralTotal);

	void update_integral_total(TItovIntegralTotal tItovIntegralTotal);

	TItovIntegralRule selectIntegralByType(TItovIntegralRule tItovIntegralRule);
	
	List<UsAccount>  selectNewUser(UsAccount usAccount);
	List<UsAccount>  selectIVR(UsAccount usAccount);
	List<UsAccount>  selectVOIP(UsAccount usAccount);
}