package com.tmount.db.ptt.dao;

import java.util.List;

import com.tmount.db.ptt.dto.TItov_personal;
import com.tmount.db.user.dto.UsAccount;

public interface PttItovPersonalMapper {
	TItov_personal selectByAgentid(Long agentid);
	
	UsAccount selectByPersonTel(String personal_tel);
	void update(TItov_personal tItov_personal);
	/**
	 * 根据voip号获得用户信息
	 * @param voipAccount
	 * @return
	 */
	List<TItov_personal> selectAccountByVoipAccount(String voipAccount);
}
