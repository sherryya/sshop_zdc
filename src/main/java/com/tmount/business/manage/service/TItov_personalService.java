package com.tmount.business.manage.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.manage.dao.TItov_personalManageMapper;
import com.tmount.db.manage.dto.TItov_personal_manage;
@Service
public class TItov_personalService {
	@Autowired
	private TItov_personalManageMapper tItov_personalMapper;
	public List<TItov_personal_manage> selectByWhere(TItov_personal_manage tItov_personal)
	{
		return tItov_personalMapper.selectByWhere(tItov_personal);
	}
	public  Integer selectSizeByWhere(TItov_personal_manage tItov_personal)
	{
		return tItov_personalMapper.selectSizeByWhere(tItov_personal);
	}
	public List<TItov_personal_manage> selectByWhere1(TItov_personal_manage tItov_personal)
	{
		return tItov_personalMapper.selectByWhere1(tItov_personal);
	}
	public  Integer selectSizeByWhere1(TItov_personal_manage tItov_personal)
	{
		return tItov_personalMapper.selectSizeByWhere1(tItov_personal);
	}
	public  TItov_personal_manage selectByAccountID(TItov_personal_manage tItov_personal)
	{
		return tItov_personalMapper.selectByAccountID(tItov_personal);
	}
	public void updatePersonalByAccountID(TItov_personal_manage tItov_personal)
	{
		tItov_personalMapper.updatePersonalByAccountID(tItov_personal);
	}
}
