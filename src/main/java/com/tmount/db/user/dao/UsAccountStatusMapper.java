package com.tmount.db.user.dao;


import com.tmount.db.user.dto.UsAccountStatus;


public interface UsAccountStatusMapper {

	String selectByPrimaryKey(Long account_id);

	int insert(UsAccountStatus usAccountStatus);

	int updateByPrimaryKey(UsAccountStatus usAccountStatus);

}