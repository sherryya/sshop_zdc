package com.tmount.db.user.dao;

import com.tmount.db.user.dto.UsAccount;

public interface UsUserInfoMapper {
	/**
	 * 根据账号名称查询用户信息
	 * @param userAccount
	 * @return
	 */
	UsAccount selectByUserAccount(String userAccount);
	 void updateLoginStatus(UsAccount usUserAccount);
	/**
	 * 根据用户账号查询用户信息
	 * @param userAccount
	 * @return
	 */
	UsAccount getUsUserInfoByAccountId(int accountId);
	
	UsAccount getTradeUserInfoByUserAccount(int userAccount);
	
    UsAccount getUsUserInfo(UsAccount usUserAccount);
	
	
}