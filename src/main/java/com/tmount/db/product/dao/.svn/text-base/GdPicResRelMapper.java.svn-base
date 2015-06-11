package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.GdPicResRel;
import com.tmount.db.product.dto.GdPicResRelKey;

public interface GdPicResRelMapper {
    /**
     * 删除
     * @param record
     */
    void deleteByResId(GdPicResRel record);

    /**
     * 删除
     * @param record
     */
    void deleteByResIdAndStoreType(GdPicResRel record);
    
    /**
     * 根据指定的条件查询。
     * @param record
     * @return
     */
    List<GdPicResRel> selectSelective(GdPicResRel record);

    int deleteByPrimaryKey(GdPicResRelKey key);

    int insert(GdPicResRel record);

    int insertSelective(GdPicResRel record);

    GdPicResRel selectByPrimaryKey(GdPicResRelKey key);

    int updateByPrimaryKeySelective(GdPicResRel record);

    int updateByPrimaryKey(GdPicResRel record);
}