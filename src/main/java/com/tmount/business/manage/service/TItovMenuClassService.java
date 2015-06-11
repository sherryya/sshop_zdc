package com.tmount.business.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.manage.dao.TItovMenuClassMapper;
import com.tmount.db.manage.dto.TItovMenuClass;

@Service
public class TItovMenuClassService {
	@Autowired
	private TItovMenuClassMapper tItovMenuClassMapper;

	public List<TItovMenuClass> selectMenuFirst(TItovMenuClass tItovMenuClass) {
		return tItovMenuClassMapper.selectMenuFirst(tItovMenuClass);
	}

	public List<TItovMenuClass> selectMenuSecond(TItovMenuClass tItovMenuClass) {
		return tItovMenuClassMapper.selectMenuSecond(tItovMenuClass);
	}
	
	public List<TItovMenuClass> selectMenuClassFirst( ) {
		return tItovMenuClassMapper.selectMenuClassFirst();
	}

	public List<TItovMenuClass> selectMenuClassSecond(TItovMenuClass tItovMenuClass) {
		return tItovMenuClassMapper.selectMenuClassSecond(tItovMenuClass);
	}
}
