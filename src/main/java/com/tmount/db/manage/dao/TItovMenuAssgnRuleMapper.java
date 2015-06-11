package com.tmount.db.manage.dao;
import java.util.List;
import com.tmount.db.manage.dto.TItovMenuAssgnRule;
public interface TItovMenuAssgnRuleMapper {
	List<TItovMenuAssgnRule> selectByPrimaryKey(Integer roleId);
}