package com.tmount.business.zdjs.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.zdjs.dao.GetUIDMapper;
import com.tmount.db.zdjs.dto.GetUID;
@Service
public class GetUIDService {
	@Autowired
	private GetUIDMapper getUIDMapper;
	public List<GetUID> selectAll()
	{
		return getUIDMapper.selectAll();
	}
}
