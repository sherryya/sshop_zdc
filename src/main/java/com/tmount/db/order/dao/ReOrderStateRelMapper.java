package com.tmount.db.order.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.order.dto.ReOrderStateRel;
import com.tmount.db.order.dto.ReOrderStateRelKey;

public interface ReOrderStateRelMapper {
	List<ReOrderStateRel> selectStateRelList(Map<String, Object> paramIn);
	
    int deleteByPrimaryKey(ReOrderStateRelKey key);

    int insert(ReOrderStateRel record);

    int insertSelective(ReOrderStateRel record);

    ReOrderStateRel selectByPrimaryKey(ReOrderStateRelKey key);

    int updateByPrimaryKeySelective(ReOrderStateRel record);

    int updateByPrimaryKey(ReOrderStateRel record);
}