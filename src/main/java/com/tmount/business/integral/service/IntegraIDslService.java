package com.tmount.business.integral.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.integral.dao.TItovIntegralIdsMapper;
import com.tmount.db.integral.dto.TItovIntegralIds;
@Service
public class IntegraIDslService {
	@Autowired
	private TItovIntegralIdsMapper TItovIntegralIdsMapper;
    
	public void insert(TItovIntegralIds tItovIntegralIds)
	{
		TItovIntegralIdsMapper.insert(tItovIntegralIds);
	}
	public	void updateByPrimaryKeySelective(TItovIntegralIds tItovIntegralIds)
	{
		TItovIntegralIdsMapper.updateByPrimaryKeySelective(tItovIntegralIds);
	}
	public TItovIntegralIds selectAll()
	{
		return TItovIntegralIdsMapper.selectAll();
	}
}
