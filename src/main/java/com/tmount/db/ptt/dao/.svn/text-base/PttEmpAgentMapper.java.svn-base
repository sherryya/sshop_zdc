package com.tmount.db.ptt.dao;

import java.util.List;

import com.tmount.db.ptt.dto.TItovPttEmpAgent;

public interface PttEmpAgentMapper {
	/**
	 * 根据账号id，查询坐席信息
	 * @param userAccount
	 * @return
	 */
	TItovPttEmpAgent selectByPrimaryKey(Long account_id);
	
	/**
	 * 修改坐席状态    默认 -1:未上班   0： 上班  1： 就绪  
	 * @param account_id
	 * @return
	 */
	TItovPttEmpAgent updateByPrimaryKeySelective(Long account_id ,Integer agentstate);
	
	
	void insert(TItovPttEmpAgent tItovPttEmpAgent);
	
	TItovPttEmpAgent  selectBySubToken(String subToken);
	void updateAgentStateByAgentID(TItovPttEmpAgent tItovPttEmpAgent);
	
	List<TItovPttEmpAgent>  selectAgentByAgentType(TItovPttEmpAgent tItovPttEmpAgent);
	List<TItovPttEmpAgent>  selectAgentByAgentTypeForLD(TItovPttEmpAgent tItovPttEmpAgent);
	TItovPttEmpAgent  selectAgentByAgentState(String agentstate);
}