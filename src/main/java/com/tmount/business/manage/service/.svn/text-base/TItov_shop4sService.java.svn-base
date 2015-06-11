package com.tmount.business.manage.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dao.TItov_personalManageMapper;
import com.tmount.db.manage.dao.TItov_shop4sManageMapper;
import com.tmount.db.manage.dto.TItov_personal_manage;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
@Service
public class TItov_shop4sService {
	@Autowired
	private TItov_shop4sManageMapper tItov_shop4sManageMapper;
	
	/**
	 * 查询lookup的4s店信息
	 * @param TItov_shop4s_manage
	 * @return
	 */
	public List<TItov_shop4s_manage> selectShop4sByUser(TItov_shop4s_manage tItov_shop4s_manage)
	{
		return tItov_shop4sManageMapper.selectShop4sByUser(tItov_shop4s_manage);
	}
	/**
	 * 查询lookup的4s店数量
	 * @param TItov_shop4s_manage
	 * @return
	 */
	public  Integer selectShop4sSizeByUser(TItov_shop4s_manage tItov_shop4s_manage)
	{
		return tItov_shop4sManageMapper.selectShop4sSizeByUser(tItov_shop4s_manage);
	}
	
	
	
	
	public List<TItov_shop4s_manage> selectByWhere(TItov_shop4s_manage tItov_shop4s_manage)
	{
		return tItov_shop4sManageMapper.selectByWhere(tItov_shop4s_manage);
	}
	
	public List<TItov_shop4s_user> selectUserByWhere(TItov_shop4s_user tItov_shop4s_user)
	{
		return tItov_shop4sManageMapper.selectUserByWhere(tItov_shop4s_user);
	}
	
	public  Integer selectSizeByWhere(TItov_shop4s_manage tItov_shop4s_manage)
	{
		return tItov_shop4sManageMapper.selectSizeByWhere(tItov_shop4s_manage);
	}
	
	public  Integer selectUserSizeByWhere(TItov_shop4s_user tItov_shop4s_user)
	{
		return tItov_shop4sManageMapper.selectUserSizeByWhere(tItov_shop4s_user);
	}
	
	
	/**
	 * 插入4s店数据信息
	 * @param TItov_shop4s_manage
	 * @return
	 */
	public int insertShops4(TItov_shop4s_manage tItov_shop4s_manage)
	{
		return tItov_shop4sManageMapper.insert(tItov_shop4s_manage);
	}
	
	
	/**
	 * 导入4s店用户数据信息
	 * @param TItov_shop4s_manage
	 * @return
	 */
	public int saveShop4sUser(TItov_shop4s_user tItov_shop4s_user)
	{
		return tItov_shop4sManageMapper.saveShop4sUser(tItov_shop4s_user);
	}
	
	
	/**
	 * 根据主键id获取4s information
	 * @param id
	 * @return
	 */
	public List<TItov_shop4s_manage> selectById(int id)
	{
		return tItov_shop4sManageMapper.selectById(id);
	}
	
	/**
	 * 根据4s_id 查询4s information
	 * @param shop4s_id
	 * @return
	 */
	public TItov_shop4s_manage selectShop4sInfo(int shop4s_id)
	{
		return tItov_shop4sManageMapper.selectShop4sInfo(shop4s_id);
	}
	
	/**
	 * update save shop4s information
	 * @param TItov_shop4s_manage
	 * @return
	 */
	public int updateShop4sId(TItov_shop4s_manage tItov_shop4s_manage)
	{
		return tItov_shop4sManageMapper.updateByPrimaryKeySelective(tItov_shop4s_manage);
	}
	
	
	/**
	 * 根据account_id 查询4s 用户 information
	 * @param account_id
	 * @return
	 */
	public List<TItov_shop4s_user> selectUser4sById(int account_id)
	{
		return tItov_shop4sManageMapper.selectUser4sById(account_id);
	}
	
	
	/**
	 * update save User4s information
	 * @param TItov_shop4s_user
	 * @return
	 */
	public int updateUser4sId(TItov_shop4s_user tItov_shop4s_user)
	{
		return tItov_shop4sManageMapper.updateByPrimaryKeySelective2(tItov_shop4s_user);
	}

}
