package com.tmount.db.mark.dao;

import com.tmount.db.mark.dto.ReOrderMarkAdd;

public interface ReOrderMarkAddMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(ReOrderMarkAdd record);

    int insertSelective(ReOrderMarkAdd record);

    ReOrderMarkAdd selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(ReOrderMarkAdd record);

    int updateByPrimaryKey(ReOrderMarkAdd record);
}