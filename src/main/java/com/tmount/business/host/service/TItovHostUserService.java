package com.tmount.business.host.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.host.dao.TZdcHostUserMapper;
import com.tmount.db.host.dto.TZdcHostUser;
@Service
public class TItovHostUserService {
	@Autowired
	private TZdcHostUserMapper zdcHostMapper;
	/**
	 * 插入主播数据信息
	 * @param tZdcHostUser
	 * @return
	 */
	public int insertHost(TZdcHostUser tZdcHostUser)
	{
		return zdcHostMapper.insert(tZdcHostUser);
	}
	/**
	 * 查询主播总条数
	 * @param name
	 * @return
	 */
	public int selectCount()
	{
		return zdcHostMapper.selectCount();
	}
	/**
	 * 设置用户账户后更新host表中的accountid信息
	 * @param tZdcHostUser
	 * @return
	 */
	public int updateAccountId(TZdcHostUser tZdcHostUser)
	{
		return zdcHostMapper.updateByPrimaryKeySelective(tZdcHostUser);
	}

	/**
	 * 查询全部主播信息带分页
	 * @return
	 */
	public List<TZdcHostUser> selectAll(TZdcHostUser tZdcHostUser)
	{
		return zdcHostMapper.selectAll(tZdcHostUser);
	}
	/**
	 * 根据主键id获取主播信息
	 * @param id
	 * @return
	 */
	public List<TZdcHostUser> selectByPrimaryKey(int id)
	{
		return zdcHostMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据主键id获取主播信息不带主播状态
	 * @param id
	 * @return
	 */
	public List<TZdcHostUser> selectById(int id)
	{
		return zdcHostMapper.selectById(id);
	}
	/**
	 * 查询全部主播不标记主播状态
	 * @return
	 */
	public List<TZdcHostUser> selectHostInfo(TZdcHostUser tZdcHostUser)
	{
		return zdcHostMapper.selectHostInfo(tZdcHostUser);
	}
	/**
	 * 根据状态搜索主播
	 * @param voip_status
	 * @return
	 */
	public List<TZdcHostUser> selectHostByStatus(TZdcHostUser tuser)
	{
		return zdcHostMapper.selectHostByStatus(tuser);
	}
	/**
	 * 根据坐席状态查询总条数
	 * @param voip_status
	 * @return
	 */
	public 	int selectCountByStatus(TZdcHostUser tuser)
	{
		return zdcHostMapper.selectCountByStatus(tuser);
	}
	/**
	 * 根据account_id 查询账号信息 ,房间号等
	 * @param account_id
	 * @return
	 */
	public TZdcHostUser selectAccountInfo(long account_id)
	{
		return zdcHostMapper.selectAccountInfo(account_id);
	}
}
