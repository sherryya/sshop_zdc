package com.tmount.db.car.dao;

import java.util.List;

import com.tmount.db.car.dto.QRcodeTerminalInfo;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.user.dto.UsAccount;

public interface TerminalInfoMapper {
	String getUserIdByDeviceSn(String device_sn);

	String getPwdByDeviceSn(String device_sn);

	void updateTerminalStatus(TerminalInfo terminalInfo);
	
	TerminalInfo getTerminalInfoByVoip(String voipAccount);
	
	
	TItovPttSubaccount getVoipInfoByIMEI(TerminalInfo terminal_imei);
	/**
	 * 根据imei插入信息
	 * @param terminalInfo
	 * @return
	 */
	int insertTerminalByImei (TerminalInfo terminalInfo);
	
	List<TerminalInfo> selectUserIdByImei(String terminal_imei);
	
	UsAccount selectAccountIDByIMEI(String account_name);
	/**
	 * 查询车辆信息列表
	 * @param terminalInfo
	 * @return
	 */
	List<QRcodeTerminalInfo> selectTerminalInfo(QRcodeTerminalInfo terminalInfo);
	/**
	 * 查询总条数
	 * @param terminalInfo
	 * @return
	 */
	int selectCountTerminalInfo(QRcodeTerminalInfo terminalInfo);
}
