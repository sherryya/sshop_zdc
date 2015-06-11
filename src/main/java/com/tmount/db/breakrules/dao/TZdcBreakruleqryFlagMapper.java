package com.tmount.db.breakrules.dao;

import com.tmount.db.breakrules.dto.TZdcBreakruleqryFlag;

public interface TZdcBreakruleqryFlagMapper {
	/**
	 * 根据车牌号查询 上次违章查询的时间
	 * @param car_plate_num
	 * @return
	 */
	TZdcBreakruleqryFlag selectInfoByPlateNum(String car_plate_num);
	int insert(TZdcBreakruleqryFlag breakqryflag);
	int updateQryRecord(TZdcBreakruleqryFlag breakqryflag);


}
