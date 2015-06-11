package com.tmount.db.host.dao;

import java.util.List;

import com.tmount.db.host.dto.TZdcHostUser;

public interface TZdcHostUserMapper {
	/**
	 * 插入主播数据信息
	 * @param tZdcHostUser
	 * @return
	 */
	int insert(TZdcHostUser tZdcHostUser);
	
	int updateByPrimaryKeySelective(TZdcHostUser tZdcHostUser);
	/**
	 * 查询全部的主播信息
	 * @return
	 */
	List<TZdcHostUser> selectAll(TZdcHostUser tZdcHostUser);
	/**
	 * 查询主播信息部标记主播状态
	 * @param tZdcHostUser
	 * @return
	 */
	List<TZdcHostUser> selectHostInfo(TZdcHostUser tZdcHostUser);
	/**
	 * 根据主键id获取主播信息
	 * @param id
	 * @return
	 */
	List<TZdcHostUser> selectByPrimaryKey(int id);
	/**
	 * 根据主键id获取主播信息不带状态
	 * @param id
	 * @return
	 */
	List<TZdcHostUser> selectById(int id);
	/**
	 * 查询主播总条数
	 * @return
	 */
	int selectCount();
	/**
	 * 根据id查询accountid判断此用户是否已经设置了账户
	 * @param id
	 * @return
	 */
	int selectAccountIdById(int id);
	/**
	 * 根据状态搜索主播
	 * @param voip_status
	 * @return
	 */
	List<TZdcHostUser> selectHostByStatus(TZdcHostUser tuser);
	/**
	 * 根据坐席状态查询总条数
	 * @param voip_status
	 * @return
	 */
	int selectCountByStatus(TZdcHostUser tuser);
	/**
	 * 根据account_id 查询账号信息 ,房间号等
	 * @param account_id
	 * @return
	 */
	TZdcHostUser selectAccountInfo(long account_id);
}