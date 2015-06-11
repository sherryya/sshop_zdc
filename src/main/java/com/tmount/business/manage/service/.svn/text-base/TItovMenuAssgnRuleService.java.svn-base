package com.tmount.business.manage.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.manage.dao.TItovMenuAssgnRuleMapper;
import com.tmount.db.manage.dto.TItovMenuAssgnRule;
@Service
public class TItovMenuAssgnRuleService {
	@Autowired
	private TItovMenuAssgnRuleMapper tItovMenuAssgnRuleMapper;

	public 	List<TItovMenuAssgnRule> selectByPrimaryKey(Integer roleId) {
		return tItovMenuAssgnRuleMapper.selectByPrimaryKey(roleId);
	}

}
