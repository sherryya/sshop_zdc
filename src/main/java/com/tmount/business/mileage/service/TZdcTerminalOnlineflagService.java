package com.tmount.business.mileage.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.mileage.dao.TZdcTerminalOnlineflagMapper;
import com.tmount.db.mileage.dto.TZdcTerminalOnlineflag;

@Service
public class TZdcTerminalOnlineflagService {
	@Autowired
	private TZdcTerminalOnlineflagMapper terminalOnFlagMapper;
	
	/**
	 * 根据车机imei查询在线状态
	 * @param itemId
	 * @return
	 */
	public TZdcTerminalOnlineflag selectInfoByaccountIdTel(long account_id_tel)
	{
		return terminalOnFlagMapper.selectInfoByaccountIdTel(account_id_tel);
	}


}
