package com.tmount.business.breakrules.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.breakrules.dao.TZdcBreakruleqryFlagMapper;
import com.tmount.db.breakrules.dao.TZdcBreakrulesMapper;
import com.tmount.db.breakrules.dto.TZdcBreakruleqryFlag;
import com.tmount.db.breakrules.dto.TZdcBreakrules;
/**
 * 违章记录service 20141128
 * @author Administrator
 *
 */
@Service
public class TzdcBreakRulesService {
	@Autowired
	private TZdcBreakrulesMapper tBreakRules;  //违章记录mapper
	
	@Autowired
	private TZdcBreakruleqryFlagMapper tBreakRuleflag;  //违章记录mapper
	
	public int insert (TZdcBreakrules tzdcBreakRules)
	{
		return tBreakRules.insert(tzdcBreakRules);
	}
	/**
	 * 根据车牌号查询车辆是否查询
	 * @param car_plate_num
	 * @return
	 */
	public TZdcBreakruleqryFlag qryBreak(String car_plate_num)
	{
		return tBreakRuleflag.selectInfoByPlateNum(car_plate_num);
	}
	/**
	 * 插入车辆信息
	 * @param breakQryFlag
	 * @return
	 */
	public int insertqryBreak(TZdcBreakruleqryFlag breakQryFlag)
	{
		return tBreakRuleflag.insert(breakQryFlag);
	}
	/**
	 * 更新车牌查询违章的时间
	 * @param breakqryflag
	 * @return
	 */
	public int updateQryRecord(TZdcBreakruleqryFlag breakqryflag)
	{
		return tBreakRuleflag.updateQryRecord(breakqryflag);
	}
	/**
	 * 根据车牌号码查询车辆信息
	 * @param hphm
	 * @return
	 */
	public List<TZdcBreakrules> selectByHphm(String hphm)
	{
		return tBreakRules.selectByHphm(hphm);
	}
	/**
	 * 查询是否存在车辆违章记录根据车牌号和违章时间
	 * @return
	 */
	public int isExistBreakRule(TZdcBreakrules tzdcBreakRules)
	{
		return tBreakRules.isExistBreakRule(tzdcBreakRules);
	}

}
