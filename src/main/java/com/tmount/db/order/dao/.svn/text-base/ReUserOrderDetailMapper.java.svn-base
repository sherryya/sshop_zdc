package com.tmount.db.order.dao;

import java.util.List;
import java.util.Map;

import com.tmount.db.order.dto.ReUserOrderDetail;
import com.tmount.db.order.dto.ReUserOrderDetailKey;
import com.tmount.db.order.vo.ProductMonthConsumerDetail;
import com.tmount.db.order.vo.ReUserOrderDetailInfo;
import com.tmount.db.order.vo.ReUserOrderStatis;
import com.tmount.db.sys.vo.StatisTime;

public interface ReUserOrderDetailMapper {
    /**
     * 通过订单获得订单明细信息。
     * @param orderNo
     * @return
     */
	List <ReUserOrderDetailInfo> selectDetailByOrderNo(Long orderNo);
	
	/**
	 * 更新订单明细状态
	 * @param record
	 */
	void updateStatusByOrderNo(ReUserOrderDetail record);

	/**
	 * 得到当月指定商品的购买情况。
	 * @param paramMap
	 * @return
	 */
	List<ProductMonthConsumerDetail> statConsumerByItemsId(Map<String, Object> paramMap);
	
	/**
	 * 得到当月指定商品的销售记录条数。
	 * @param itemsId
	 * @return
	 */
	Integer statConsumerByItemsIdCount(Map<String, Object> paramMap);
	
	int deleteByPrimaryKey(ReUserOrderDetailKey key);

    int insert(ReUserOrderDetail record);

    int insertSelective(ReUserOrderDetail record);

    ReUserOrderDetail selectByPrimaryKey(ReUserOrderDetailKey key);

    int updateByPrimaryKeySelective(ReUserOrderDetail record);

    int updateByPrimaryKey(ReUserOrderDetail record);
    
    /**
     * 鑾峰彇璁㈠崟鍟嗗搧鍒楄〃
     * @param orderNo
     * @return
     */
    List<ReUserOrderDetail> selectByOrderNo(Long orderNo);
    
    List<ReUserOrderStatis> selectByOrderTimeForStatis(StatisTime statisTime);
    
    int selectAcountByDays(StatisTime statisTime);
}