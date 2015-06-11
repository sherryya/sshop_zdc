package com.tmount.db.ptt.dao;

import java.util.List;

import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.ptt.vo.HostSubAccountLog;

public interface PttSubaccountMapper {

	void updateByPrimaryKeySelective(TItovPttSubaccount tItovPttSubaccount);

	void insert(TItovPttSubaccount tItovPttSubaccount);

	TItovPttSubaccount selectByAccount_id(Long account_id);

	TItovPttSubaccount selectForZDC();

	/* 增加查询IMEI接口 */
	TItovPttSubaccount selectForIMEI(Long account_id);

	TItovPttSubaccount selectForTEL(String terminal_imei);
	/**
	 * 查询主播账户登录信息
	 * @param subAccountLog
	 * @return
	 */
	List<HostSubAccountLog> selectHostSubLogInfo(HostSubAccountLog subAccountLog);
	/**
	 * 根据条件查询主播账户登录信息
	 * @param subAccountLog
	 * @return
	 */
	List<HostSubAccountLog> selectHostStaticCondition(HostSubAccountLog subAccountLog);
	/**
	 * 查询主播账户列表
	 * @param subAccountLog
	 * @return
	 */
	List<HostSubAccountLog> selectHostAccount(HostSubAccountLog subAccountLog);
	/**
	 * 主播在线信息总条数
	 * @param subAccountLog
	 * @return
	 */
	int selectHostAccountCount(HostSubAccountLog subAccountLog);
	/**
	 * 主播在线列表总条数 以及点击详情时的总条数 通用
	 * @param subAccountLog
	 * @return
	 */
	int selectHostStaticCount(HostSubAccountLog subAccountLog);
	/**
	 * 根据voipaccount 查询用户的在线总时长使用 所有的结束时间减去开始时间的总和 20141126 
	 * **/
	List<HostSubAccountLog> selectOnlineTimeTotal(HostSubAccountLog subAccountLog);
	/**
	 * 用户登录使用 20141210 anychat 改造使用
	 * @param subAccountLog
	 * @return
	 */
	List<HostSubAccountLog> userLoginByaccountName(HostSubAccountLog subAccountLog);
	
	int updateVoipStatus(TItovPttSubaccount tItovPttSubaccount);
	/**
	 * 插入账户信息带有主播类型和频道类型,房间号
	 * @param tItovPttSubaccount
	 * @return
	 */
	int insertAccountByHost(TItovPttSubaccount tItovPttSubaccount);
}