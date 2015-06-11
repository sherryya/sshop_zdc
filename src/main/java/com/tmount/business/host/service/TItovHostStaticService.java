package com.tmount.business.host.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.host.dao.TZdcHostStaticMapper;
import com.tmount.db.host.dto.TZdcHostStatic;
@Service
public class TItovHostStaticService {
	@Autowired
	private TZdcHostStaticMapper zdcHostStaticMapper;
	 
	/**
	 * 根据voipAccount查询班次信息
	 * @return
	 */
	public List<TZdcHostStatic> selectInfoByVoipAccount (TZdcHostStatic hostStatic)
	{
		return zdcHostStaticMapper.selectInfoByVoipAccount(hostStatic);
	}
	/**
	 * 查询总条数
	 * @param voipAccount
	 * @return
	 */
	public int selectCountByVoipAccount(String voipAccount)
	{
		return zdcHostStaticMapper.selectCountByVoipAccount(voipAccount);
	}
	
	/**
	 * 查询总条数
	 * @param voipAccount
	 * @return
	 */
	public List<TZdcHostStatic> selectHostStaticById(long id)
	{
		return zdcHostStaticMapper.selectByPrimaryKey(id);
	}
	
}
