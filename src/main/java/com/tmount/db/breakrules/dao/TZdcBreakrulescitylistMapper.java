package com.tmount.db.breakrules.dao;

import java.util.List;
/**
 * 车辆违章城市信息
 * 20141128
 */
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;

public interface TZdcBreakrulescitylistMapper {
	/**
	 * 插入城市列表
	 * @return
	 */
	int insert (TZdcBreakrulescitylist cityList);
	/**
	 * 根据省份代码和城市代码查询是否需要发动机号和车架号信息
	 * @param cityList
	 * @return
	 */
	List<TZdcBreakrulescitylist> selectByPcodeCity (TZdcBreakrulescitylist cityList);
	/**
	 * 根据城市名称查询是否需要发动机号和车架号信息
	 * @param city_name
	 * @return
	 */
	List<TZdcBreakrulescitylist> selectByCityName(String city_name);

}
