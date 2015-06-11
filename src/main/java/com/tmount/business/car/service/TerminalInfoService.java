package com.tmount.business.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.car.dao.TerminalInfoMapper;
import com.tmount.db.car.dto.QRcodeTerminalInfo;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.user.dto.UsAccount;

@Service
public class TerminalInfoService {
	@Autowired
	private TerminalInfoMapper terminalInfoMapper;
	
	public String getUserIdByDeviceSn(String device_sn){
		return terminalInfoMapper.getUserIdByDeviceSn(device_sn);
	}
	public String getPwdByDeviceSn(String device_sn){
		return terminalInfoMapper.getPwdByDeviceSn(device_sn);
	}
	public void updateTerminalStatus(TerminalInfo terminalInfo)
	{
		terminalInfoMapper.updateTerminalStatus(terminalInfo);
	}
	
	public TerminalInfo getTerminalInfoByVoip(String voipAccount)
	{
		return terminalInfoMapper.getTerminalInfoByVoip(voipAccount);
	}
	
	public TItovPttSubaccount getVoipInfoByIMEI(TerminalInfo terminal_imei)
	{
		return terminalInfoMapper.getVoipInfoByIMEI(terminal_imei);
	}
	/**
	 * 根据车imei插入书库
	 * @param terminalInfo
	 * @return
	 */
	public int insertTerminalByImei(TerminalInfo terminalInfo)
	{
		return terminalInfoMapper.insertTerminalByImei(terminalInfo);
	}
	
	public List<TerminalInfo> selectUserIdByImei(String terminal_imei)
	{
		return terminalInfoMapper.selectUserIdByImei(terminal_imei);
	}
	public UsAccount selectAccountIDByIMEI(String account_name)
	{
		return terminalInfoMapper.selectAccountIDByIMEI(account_name);
	}
	/**
	 * 查询车辆信息列表
	 * @param terminalInfo
	 * @return
	 */
	public List<QRcodeTerminalInfo> selectTerminalInfo(QRcodeTerminalInfo terminalInfo)
	{
		return terminalInfoMapper.selectTerminalInfo(terminalInfo);
	}
	/**
	 * 查询总条数
	 * @param terminalInfo
	 * @return
	 */
	public int selectCountTerminalInfo(QRcodeTerminalInfo terminalInfo)
	{
		return terminalInfoMapper.selectCountTerminalInfo(terminalInfo);
	}
}
