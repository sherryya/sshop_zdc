package com.tmount.db.terminalAccount.dao;

import java.util.List;

import com.tmount.db.terminalAccount.dto.TZdcTerminalAccount;

public interface TZdcTerminalAccountMapper {
	int insert(TZdcTerminalAccount terminalAccount);
	
	int updateByPrimaryKeySelective(TZdcTerminalAccount terminalAccount);
	
	int deleteByPrimaryKey(TZdcTerminalAccount terminalAccount);
	
	List<TZdcTerminalAccount> selectByPrimaryKey(long id);
	
	List<TZdcTerminalAccount> selectAll();
	/**
	 * 根据车机account_id删除ter_account表的数据
	 * @param account_id_ter
	 * @return
	 */
	int deleteByUserId(long account_id_ter);
	

}
