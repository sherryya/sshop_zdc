package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.SyResourceSizeDetail;
import com.tmount.db.product.dto.SyResourceSizeDetailKey;

public interface SyResourceSizeDetailMapper {
    int deleteByPrimaryKey(SyResourceSizeDetailKey key);

    int insert(SyResourceSizeDetail record);

    int insertSelective(SyResourceSizeDetail record);

    SyResourceSizeDetail selectByPrimaryKey(SyResourceSizeDetailKey key);

    int updateByPrimaryKeySelective(SyResourceSizeDetail record);

    int updateByPrimaryKey(SyResourceSizeDetail record);
    
    /**
     * 获取某个类型的图片需要压缩的配置列表
     * @param picSizeId
     * @return
     */
    List<SyResourceSizeDetail> selectByPicSizeId(Integer picSizeId);

    /**
     * 得到所有图片明细类型信息。
     * @return
     */
    List<SyResourceSizeDetail> selectAll();
}