package com.tmount.db.breakrules.dao;

import java.util.List;

import com.tmount.db.breakrules.dto.TZdcBreakrules;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;

public interface BreakRulesServiceMapper {

	void update(String[] ids);

	List<TZdcBreakrules> select(String[] ids);

	List<TZdcBreakrulescitylist> selectbreakerLandInfo();

}
