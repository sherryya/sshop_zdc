package com.tmount.db.host.dao;

import java.util.List;

import com.tmount.db.host.dto.TZdcHostStatic;
/**
 * 主播在线统计表
 * @author Administrator
 *
 */
public interface TZdcHostStaticMapper {
	/**
	 * 根据voipAccount查询班次信息
	 * @return
	 */
	List<TZdcHostStatic> selectInfoByVoipAccount (TZdcHostStatic hostStatic);
	
	int selectCountByVoipAccount(String voipAccount);
	/**
	 * 根据主键查询某个班次
	 * @param id
	 * @return
	 */
	List<TZdcHostStatic> selectByPrimaryKey(long id);
}