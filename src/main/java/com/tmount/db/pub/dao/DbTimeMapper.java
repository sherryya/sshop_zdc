package com.tmount.db.pub.dao;

import java.util.Date;

import com.tmount.db.pub.dto.DbTime;

public interface DbTimeMapper {
	DbTime selectDbTime();
	String selectTotalDateStr();
	String selectYesterdayStr();
	Date selectDateByAddYear(Integer addYear);
}
