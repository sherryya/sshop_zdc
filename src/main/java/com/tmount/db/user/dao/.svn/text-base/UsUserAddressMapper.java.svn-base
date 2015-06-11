package com.tmount.db.user.dao;

import java.util.List;

import com.tmount.db.user.dto.UsUserAddress;
import com.tmount.db.user.dto.UsUserAddressKey;
import com.tmount.db.user.vo.UsUserAddressExpl;

public interface UsUserAddressMapper {
	/**
	 * 设置所有的用户地址都为非默认状态。 
	 * @param userId
	 * @return
	 */
	int updateUndefaultPostAddr(Long userId);
	
	/**
	 * 得到用户下得所有地址信息。
	 * @param userId
	 * @return
	 */
	List<UsUserAddressExpl> selectBySelective(UsUserAddress usUserAddress);
	
	/**
	 * 得到最大序号值。
	 * @param userId
	 * @return
	 */
	Integer selectMaxOrders(Long userId);
	
    int deleteByPrimaryKey(UsUserAddressKey key);

    int insert(UsUserAddress record);

    int insertSelective(UsUserAddress record);

    UsUserAddress selectByPrimaryKey(UsUserAddressKey key);

    int updateByPrimaryKeySelective(UsUserAddress record);

    int updateByPrimaryKey(UsUserAddress record);

	Integer selectOrdersByUserId(Long userId);
}