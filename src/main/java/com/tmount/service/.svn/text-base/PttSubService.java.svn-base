package com.tmount.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.host.dao.TZdcHostUserMapper;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.ptt.dao.PttSubaccountMapper;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.ptt.vo.HostSubAccountLog;

@Service
public class PttSubService {
	@Autowired
	PttSubaccountMapper pttSubaccountMapper;
	
	@Autowired
	private TZdcHostUserMapper zdcHostMapper;

	public void updateByPrimaryKeySelective(
			TItovPttSubaccount tItovPttSubaccount) {
		pttSubaccountMapper.updateByPrimaryKeySelective(tItovPttSubaccount);
	}

	public void insert(TItovPttSubaccount tItovPttSubaccount) {
		pttSubaccountMapper.insert(tItovPttSubaccount);
	}

	public TItovPttSubaccount selectByAccount_id(Long account_id) {
		return pttSubaccountMapper.selectByAccount_id(account_id);
	}

	public TItovPttSubaccount selectForZDC() {
		return pttSubaccountMapper.selectForZDC();
	}

	/* 增加查询IMEI */
	public TItovPttSubaccount selectIMEAByAccount_id(Long account_id) {
		return pttSubaccountMapper.selectForIMEI(account_id);
	}
	
	public TItovPttSubaccount selectForTEL(String terminal_imei)
	{
		return pttSubaccountMapper.selectForTEL(terminal_imei);
	}
	/**
	 * 根据账户voipaccount查询主播信息及主播的日志信息20141125 分页
	 * @param account_id
	 * @return
	 */
	public List<HostSubAccountLog> selectHostSubLogInfo(HostSubAccountLog subAccountLog)
	{
		return pttSubaccountMapper.selectHostSubLogInfo(subAccountLog);
	}
	/**
	 * 根据条件查询主播信息及主播的日志信息20141125 分页
	 * @param account_id
	 * @return
	 */
	public List<HostSubAccountLog> selectHostStaticCondition(HostSubAccountLog subAccountLog)
	{
		return pttSubaccountMapper.selectHostStaticCondition(subAccountLog);
	}
	/**
	 * 查询总条数主播在线列表页
	 * @param subAccountLog
	 * @return
	 */
	public int selectHostStaticCount(HostSubAccountLog subAccountLog)
	{
		return pttSubaccountMapper.selectHostStaticCount(subAccountLog);
	}
	/**
	 * 根据voip账号查询总的sublog信息 计算在线总时间
	 * @param subAccountLog
	 * @return
	 */
	public List<HostSubAccountLog> selectOnlineTimeTotal(HostSubAccountLog subAccountLog)
	{
		return pttSubaccountMapper.selectOnlineTimeTotal(subAccountLog);
	}
	
	////////////////////////////////////废弃
	/**
	 * 查询主播账户列表
	 * @param subAccountLog
	 * @return
	 */
	public List<HostSubAccountLog> selectHostAccount(HostSubAccountLog subAccountLog)
	{
		return pttSubaccountMapper.selectHostAccount(subAccountLog);
	}
	/**
	 * 主播信息总条数
	 * @param subAccountLog
	 * @return
	 */
	public int selectHostAccountCount(HostSubAccountLog subAccountLog)
	{
		return pttSubaccountMapper.selectHostAccountCount(subAccountLog);
	}
	/**
	 * anychat 改造用户登录
	 * @param subAccountLog
	 * @return
	 */
	public List<HostSubAccountLog> userLoginByaccountName(HostSubAccountLog subAccountLog)
	{
		return pttSubaccountMapper.userLoginByaccountName(subAccountLog);
	}
	/**
	 * 根据voipaccount更新用户状态和最后登录时间
	 * @param tItovPttSubaccount
	 * @return
	 */
	public int updateVoipStatus(TItovPttSubaccount tItovPttSubaccount)
	{
		return pttSubaccountMapper.updateVoipStatus(tItovPttSubaccount);
	}
	/**
	 * 视频在线所需服务查询全部主播信息servlet返回
	 * @param tZdcHostUser
	 * @return
	 */
	public List<TZdcHostUser> selectAll(TZdcHostUser tZdcHostUser)
	{
		 return zdcHostMapper.selectAll(tZdcHostUser);
	}
	
}
