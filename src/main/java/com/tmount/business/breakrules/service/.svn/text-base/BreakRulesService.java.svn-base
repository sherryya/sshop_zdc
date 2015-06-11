package com.tmount.business.breakrules.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.breakrules.dao.BreakRulesServiceMapper;
import com.tmount.db.breakrules.dao.TZdcBreakrulescitylistMapper;
import com.tmount.db.breakrules.dto.TZdcBreakrules;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;

/**
 * 车辆违规城市列表
 * 
 * @author Administrator
 * 
 */
@Service
public class BreakRulesService {

	@Autowired
	public BreakRulesServiceMapper breakRulesServiceMapper;

	public void updatebreakerInfo(String[] ids) {
		breakRulesServiceMapper.update(ids);
	}

	public List<TZdcBreakrules> selectbreakerInfo(String[] ids) {
		return breakRulesServiceMapper.select(ids);
	}

	public List<TZdcBreakrulescitylist> selectbreakerLandInfo() {
  	return breakRulesServiceMapper.selectbreakerLandInfo();
	}

}
