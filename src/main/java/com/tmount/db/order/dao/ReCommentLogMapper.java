package com.tmount.db.order.dao;

import java.util.Map;

import com.tmount.db.order.dto.ReCommentLog;
import com.tmount.db.order.dto.ReCommentLogKey;

public interface ReCommentLogMapper {
	/**
	 * 通过订单号从定单明细表（re_user_order_detail）查询记录，并插入待评论表。
	 * @param paramIn
	 * @return
	 */
	int insertFromOrderDetail(Map<String, Object> paramIn);
	
	
    int deleteByPrimaryKey(ReCommentLogKey key);

    int insert(ReCommentLog record);

    int insertSelective(ReCommentLog record);

    ReCommentLog selectByPrimaryKey(ReCommentLogKey key);

    int updateByPrimaryKeySelective(ReCommentLog record);

    int updateByPrimaryKey(ReCommentLog record);
}