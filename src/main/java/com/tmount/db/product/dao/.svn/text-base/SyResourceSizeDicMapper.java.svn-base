package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.SyResourceSizeDic;

public interface SyResourceSizeDicMapper {
    /**
     * 根据资源用途类型取出尺寸类型。
     * @param storeType
     * @return
     */
	List<SyResourceSizeDic> selectByStoreType(Integer storeType);
    
    int deleteByPrimaryKey(Integer picSizeId);

    int insert(SyResourceSizeDic record);

    int insertSelective(SyResourceSizeDic record);

    SyResourceSizeDic selectByPrimaryKey(Integer picSizeId);
    
    int updateByPrimaryKeySelective(SyResourceSizeDic record);

    int updateByPrimaryKey(SyResourceSizeDic record);
}