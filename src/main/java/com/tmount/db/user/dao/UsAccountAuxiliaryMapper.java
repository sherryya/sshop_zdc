package com.tmount.db.user.dao;

import com.tmount.db.user.dto.UsAccountAuxiliary;

public interface UsAccountAuxiliaryMapper {
	/**
	 * 根据用户账号查询从账号信息
	 * @param userAccount
	 * @return
	 */
	UsAccountAuxiliary getUsAccountAuxiliary(String userAccount);
    
}