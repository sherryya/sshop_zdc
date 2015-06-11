package com.tmount.db.user.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.sys.vo.StatisTime;
import com.tmount.db.user.dto.UsUserComment;
import com.tmount.db.user.vo.UsUserCommentUser;
import com.tmount.db.user.vo.UsUserStatis;

public interface UsUserCommentMapper {
	/**
	 * 得到用户对商品ID的评论数量
	 * @param itemsId
	 * @return
	 */
	int selectBySelectiveCount(Map<String, Object> paramMap);
	
	/**
	 * 得到用户对商品ID的评论信息
	 * @param paramMap
	 * @return
	 */
	List<UsUserCommentUser> selectBySelective(Map<String, Object> paramMap);
	
    int deleteByPrimaryKey(Long commentId);

    int insert(UsUserComment record);

    int insertSelective(UsUserComment record);

    UsUserComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(UsUserComment record);

    int updateByPrimaryKey(UsUserComment record);
    
    List<UsUserComment> selectListById(UsUserComment usUserComment);
    
    List<UsUserStatis> selectCommentByTime(StatisTime statisTime);
    
    List<UsUserStatis> selectShopCommentByTime(StatisTime statisTime);
}