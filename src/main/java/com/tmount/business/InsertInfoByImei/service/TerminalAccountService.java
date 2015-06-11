package com.tmount.business.InsertInfoByImei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.terminalAccount.dao.TZdcTerminalAccountMapper;
import com.tmount.db.terminalAccount.dto.TZdcTerminalAccount;
@Service
public class TerminalAccountService {
	
	@Autowired
	private TZdcTerminalAccountMapper terminalAccountMapper;

    public int insert(TZdcTerminalAccount terminalAccount)
    {
    	return terminalAccountMapper.insert(terminalAccount);
    }
    public List<TZdcTerminalAccount> selectByterImei(long terId)
    {
    	return terminalAccountMapper.selectByPrimaryKey(terId);
    }
    /**
	 * 根据车机account_id删除ter_account表的数据
	 * @param account_id_ter
	 * @return
	 */
    public int delete(long account_id_ter)
    {
    	return terminalAccountMapper.deleteByUserId(account_id_ter);
    }

}
