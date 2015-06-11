package com.tmount.db.integral.dao;
import com.tmount.db.integral.dto.TItovIntegralIds;
public interface TItovIntegralIdsMapper {
	void insert(TItovIntegralIds tItovIntegralIds);
	void updateByPrimaryKeySelective(TItovIntegralIds tItovIntegralIds);
	TItovIntegralIds selectAll();
}