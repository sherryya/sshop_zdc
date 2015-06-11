package com.tmount.db.product.dao;

import java.util.List;

import com.tmount.db.product.dto.GdDeliverDic;

public interface GdDeliverDicMapper {
	/**
	 * 选锟斤拷锟斤拷锟叫碉拷锟剿凤拷锟斤拷锟酵★拷
	 * @return
	 */
	List<GdDeliverDic> selectAll();
	
    int deleteByPrimaryKey(Integer deliverType);

    int insert(GdDeliverDic record);

    int insertSelective(GdDeliverDic record);

    GdDeliverDic selectByPrimaryKey(Integer deliverType);

    int updateByPrimaryKeySelective(GdDeliverDic record);

    int updateByPrimaryKey(GdDeliverDic record);
}