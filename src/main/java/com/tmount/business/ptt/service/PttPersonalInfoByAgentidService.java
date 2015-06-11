package com.tmount.business.ptt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.ptt.dao.PttItovPersonalMapper;
import com.tmount.db.ptt.dto.TItov_personal;
import com.tmount.db.user.dto.UsAccount;

@Service
public class PttPersonalInfoByAgentidService {
	@Autowired
	PttItovPersonalMapper pttItovPersonalMapper;
	public TItov_personal selectByAgentid(Long agentid)
	{
	 return pttItovPersonalMapper.selectByAgentid(agentid);
	}
	
	public UsAccount selectByPersonTel(String personal_tel)
	{
	
	  return pttItovPersonalMapper.selectByPersonTel( personal_tel);
	}
	
	public void update(TItov_personal tItov_personal)
	{
		pttItovPersonalMapper.update(tItov_personal);
	}
	/**
	 * 根据voip号获得用户信息
	 * @param voipAccount
	 * @return
	 */
	public List<TItov_personal> selectAccountByVoipAccount(String voipAccount)
	{
		return pttItovPersonalMapper.selectAccountByVoipAccount(voipAccount);
	}
}
