package com.tmount.db.manage.dao;

import java.util.List;

import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dto.TItov_personal_manage;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
public interface TItov_shop4sManageMapper {
	List<TItov_shop4s_manage> selectByWhere(TItov_shop4s_manage tItov_shop4s_manage);
    Integer selectSizeByWhere(TItov_shop4s_manage tItov_shop4s_manage);

    
    List<TItov_shop4s_user> selectUserByWhere(TItov_shop4s_user tItov_shop4s_user);
    Integer selectUserSizeByWhere(TItov_shop4s_user tItov_shop4s_user);
    
	/**
	 * 查询lookup的4s店信息
	 * @param 
	 * @return
	 */
	List<TItov_shop4s_manage> selectShop4sByUser(TItov_shop4s_manage tItov_shop4s_manage);
	
	
	/**
	 * 查询lookup的4s数量
	 * @param 
	 * @return
	 */
	Integer selectShop4sSizeByUser(TItov_shop4s_manage tItov_shop4s_manage);
	
	

	/**
	 * 插入4s店数据信息
	 * @param tZdcHostUser
	 * @return
	 */
	int insert(TItov_shop4s_manage tItov_shop4s_manage);
	
	
	int saveShop4sUser(TItov_shop4s_user tItov_shop4s_user);

	
	
	/**
	 * 根据主键id获取4s information
	 * @param id
	 * @return
	 */
	List<TItov_shop4s_manage> selectById(int id);
	
	/**
	 * 根据4s_id 查询4s information
	 * @param shop4s_id
	 * @return
	 */
	TItov_shop4s_manage selectShop4sInfo(int shop4s_id);
	
	/**
	 * 根据4s_id 修改4s信息 information
	 * @param shop4s_id
	 * @return
	 */
	int updateByPrimaryKeySelective(TItov_shop4s_manage tItov_shop4s_manage);
	
	/**
	 * 根据account_id 修改4s用户 information
	 * @param shop4s_id
	 * @return
	 */
	
	int updateByPrimaryKeySelective2(TItov_shop4s_user tItov_shop4s_user);
	
	/**
	 * 根据account_id 查询4suser information
	 * @param shop4s_id
	 * @return
	 */
	List<TItov_shop4s_user> selectUser4sById(int id);
	
}