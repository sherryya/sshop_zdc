package com.tmount.business.breakrules.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.breakrules.dao.TZdcBreakrulescitylistMapper;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;
/**
 * 车辆违规城市列表
 * @author Administrator
 *
 */
@Service
public class TzdcBreakRuleCityService {

	@Autowired
	public TZdcBreakrulescitylistMapper tcitylistMapper;
	/**
	 * 根据省份代码和城市代码查询是否需要发动机号和车架号信息
	 * @param cityList
	 * @return
	 */
	public List<TZdcBreakrulescitylist> selectByPcodeCity (TZdcBreakrulescitylist cityList)
	{
		return tcitylistMapper.selectByPcodeCity(cityList);
	}
	/**
	 * 插入城市列表
	 * @param cityList
	 * @return
	 */
	public int insert(TZdcBreakrulescitylist cityList)
	{
		return tcitylistMapper.insert(cityList);
	}
	/**
	 * 根据城市名称查询是否需要发动机号和车架号信息
	 * @param city_name
	 * @return
	 */
	public List<TZdcBreakrulescitylist> selectByCityName(String city_name)
	{
		return tcitylistMapper.selectByCityName(city_name);
	}
}
