package com.tmount.db.product.dao;

import com.tmount.db.product.dto.SyFloorDic;

public interface SyFloorDicMapper {
    SyFloorDic selectByPrimaryKey(Integer floorId);
}