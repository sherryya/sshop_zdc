package com.tmount.db.breakrules.dao;

import java.util.List;

import com.tmount.db.breakrules.dto.TZdcBreakrules;


public interface TZdcBreakrulesMapper {
	
	/**
	 * 得到违章驾驶记录
	 * @param account_id  用户手机端ID
	 * @return
	 */
	List<TZdcBreakrules> getBreakrulesList(int account_id);
	/**
	 * 插入违章记录信息
	 * @param tzdcBreakRules
	 * @return
	 */
	int insert(TZdcBreakrules tzdcBreakRules);
	/**
	 * 根据车牌号码查询违章信息
	 * @param hphm
	 * @return
	 */
	List<TZdcBreakrules> selectByHphm(String hphm);
	
	int isExistBreakRule(TZdcBreakrules tzdcBreakRules);

}
