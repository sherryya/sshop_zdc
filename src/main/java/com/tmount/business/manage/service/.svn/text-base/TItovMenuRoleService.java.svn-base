package com.tmount.business.manage.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.manage.dao.TItovMenuRoleMapper;
import com.tmount.db.manage.dto.TItovMenuRole;
@Service
public class TItovMenuRoleService {
	@Autowired
	private TItovMenuRoleMapper tItovMenuRoleMapper;

	public void insert(TItovMenuRole tItovMenuRole) {
		tItovMenuRoleMapper.insert(tItovMenuRole);
	}
	public void updateByPrimaryKeySelective(TItovMenuRole tItovMenuRole) {
		tItovMenuRoleMapper.updateByPrimaryKeySelective(tItovMenuRole);
	}
	public void deleteByPrimaryKey(Integer roleId) {
		tItovMenuRoleMapper.deleteByPrimaryKey(roleId);
	}
	public TItovMenuRole selectByPrimaryKey(Integer roleId) {
		return tItovMenuRoleMapper.selectByPrimaryKey(roleId);
	}
	public List<TItovMenuRole> selectAll() {
		return tItovMenuRoleMapper.selectAll();
	}
	public TItovMenuRole selectByRoleName(String roleName)
	{
	return tItovMenuRoleMapper.selectByRoleName(roleName);
	}
}
