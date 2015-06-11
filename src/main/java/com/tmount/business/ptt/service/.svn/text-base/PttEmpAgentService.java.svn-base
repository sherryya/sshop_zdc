package com.tmount.business.ptt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.ptt.dao.PttEmpAgentMapper;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;

@Service
public class PttEmpAgentService {
	@Autowired
	private PttEmpAgentMapper pttEmpAgentMapper;

	/**
	 * 根据账号id，查询坐席信息
	 * @param userAccount
	 * @return
	 */
	public TItovPttEmpAgent selectByPrimaryKey(Long account_id){
		return pttEmpAgentMapper.selectByPrimaryKey(account_id);
	}
	
	public void insert(TItovPttEmpAgent tItovPttEmpAgent){
		pttEmpAgentMapper.insert(tItovPttEmpAgent);
	}
	
	public TItovPttEmpAgent selectBySubToken(String subToken){
		return pttEmpAgentMapper.selectBySubToken(subToken);
	}

	
	public void updateAgentStateByAgentID(TItovPttEmpAgent tItovPttEmpAgent){
		 pttEmpAgentMapper.updateAgentStateByAgentID(tItovPttEmpAgent);
	}
	
	public List<TItovPttEmpAgent> selectAgentByAgentType(TItovPttEmpAgent tItovPttEmpAgent){
		return pttEmpAgentMapper.selectAgentByAgentType(tItovPttEmpAgent);
	}
	
	public List<TItovPttEmpAgent> selectAgentByAgentTypeForLD(TItovPttEmpAgent tItovPttEmpAgent){
		return pttEmpAgentMapper.selectAgentByAgentTypeForLD(tItovPttEmpAgent);
	}
	
	public TItovPttEmpAgent selectAgentByAgentState(String agentstate){
		return pttEmpAgentMapper.selectAgentByAgentState(agentstate);
	}
	
}
