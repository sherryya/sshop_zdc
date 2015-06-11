package com.tmount.business.user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.user.dao.UsAccountStatusMapper;
import com.tmount.db.user.dto.UsAccountStatus;



@Service
public class AccountStatusService {
	@Autowired
	private UsAccountStatusMapper usAccountStatusMapper;

	public String getUsUserAccountByUserId(Long account_id){
		return usAccountStatusMapper.selectByPrimaryKey(account_id);
	}
	
	public int insert(UsAccountStatus usAccountStatus)
	{
		return usAccountStatusMapper.insert(usAccountStatus);
	}
	public int updateByPrimaryKey(UsAccountStatus usAccountStatus)
	{
		return usAccountStatusMapper.updateByPrimaryKey(usAccountStatus);
	}

}
