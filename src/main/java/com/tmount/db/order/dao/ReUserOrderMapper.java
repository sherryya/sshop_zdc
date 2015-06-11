package com.tmount.db.order.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.order.dto.ReUserOrder;
import com.tmount.db.order.vo.OrderStateStat;

public interface ReUserOrderMapper {
	/**
	 * 用户订单状态统计
	 * @param userId
	 * @return
	 */
	List<OrderStateStat> selectorderStateStat(Long userId);
	
	/**
	 * 查询用户的订单列表。
	 * @param userId
	 * @return
	 */
    List<ReUserOrder> selectBySelective(Map<String, Object> paramIn);
    
    /**
     * 查询用户的订单列表的数量。
     * @param paramIn
     * @return
     */
    int selectBySelectiveCount(Map<String, Object> paramIn);
	
    /**
     * 获取待评论的订单信息
     * @param paramIn
     * @return
     */
	List<ReUserOrder> selectUserOrderBySelective(Map<String, Object> paramIn);
	
	/**
	 * 获取待评论订单的数量
	 * @param paramIn
	 * @return
	 */
	int selectUserOrderBySelectiveCount(Map<String, Object> paramIn);

    int deleteByPrimaryKey(Long orderNo);

    int insert(ReUserOrder record);

    int insertSelective(ReUserOrder record);

    ReUserOrder selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(ReUserOrder record);

    int updateByPrimaryKey(ReUserOrder record);
    
    List<ReUserOrder> selectOrderList();
    
    int selectOrderListCount();
    
    /**
     * 更新订单状态
     * @param record
     * @return
     */
    int updateOrderStatus(ReUserOrder record);
    
    int updateOrderStatusByLogis(ReUserOrder record);
}
