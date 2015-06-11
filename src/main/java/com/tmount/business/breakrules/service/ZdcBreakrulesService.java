package com.tmount.business.breakrules.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.breakrules.dao.TZdcBreakrulesMapper;
import com.tmount.db.breakrules.dto.TZdcBreakrules;

@Service
public class ZdcBreakrulesService {

	@Autowired
	private TZdcBreakrulesMapper tZdcBreakrulesMapper;
	/**
	 * 得到违章驾驶记录
	 * @author dell
	 *
	 */
	public List<TZdcBreakrules> getBreakrulesList(int account_id) {
		return tZdcBreakrulesMapper.getBreakrulesList(account_id);
	}

}
