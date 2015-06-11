package com.tmount.business.apnuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.apnuser.dao.ApnUserMapper;
import com.tmount.db.apnuser.dao.TBaiduMapper;
import com.tmount.db.apnuser.dto.TBaidu;

@Service
public class ApnUserService {
	
	@Autowired
	private ApnUserMapper apnUserMapper;
	
	@Autowired
	private TBaiduMapper tbaiduMapper;
	/**
	 * 根据username删除apnuser表数据
	 * @param username
	 * @return
	 */
	public int deleteByUserName(String username)
	{
		return apnUserMapper.deleteByUserName(username);
	}
	/**
	 * 获取百度矢量值
	 * @param tbaidu
	 * @return
	 */
	public List<TBaidu> selectAll(TBaidu tbaidu)
	{
		return tbaiduMapper.selectAll(tbaidu);
	}

}
