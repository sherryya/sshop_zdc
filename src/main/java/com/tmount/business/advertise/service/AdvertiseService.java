package com.tmount.business.advertise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.advertise.dao.AdShowbusiAccMapper;
import com.tmount.db.advertise.dao.AdShowcaseBusiMapper;
import com.tmount.db.advertise.dao.AdShowcaseDictMapper;
import com.tmount.db.advertise.dto.AdShowbusiAcc;
import com.tmount.db.advertise.dto.AdShowcaseBusi;
import com.tmount.db.advertise.dto.AdShowcaseDict;
import com.tmount.db.advertise.vo.AdvertiseInfo;

@Service
public class AdvertiseService {

	@Autowired
	private AdShowcaseBusiMapper adShowcaseBusiMapper;

	@Autowired
	private AdShowcaseDictMapper adShowcaseDictMapper;
	
	@Autowired
	private AdShowbusiAccMapper adShowbusiAccMapper;
	
	public List<AdvertiseInfo> getAdShowBusiByShopShowId(AdShowcaseBusi adShowcaseBusi){
		return adShowcaseBusiMapper.selectAdListByShopShowId(adShowcaseBusi);
	}
	
	public List<AdShowcaseBusi> getAdPageListByShopShowId(AdShowcaseBusi adShowcaseBusi){
		return adShowcaseBusiMapper.selectAdPageCountByShopShowId(adShowcaseBusi);
	}
	
	public List<AdShowcaseDict> getAdShowCaseByShowAreaId(Integer showareaId){
		return adShowcaseDictMapper.selectAll(showareaId);
	}
	public List<AdShowbusiAcc> selectAccByPageNo(AdShowbusiAcc adShowbusiAcc){
		return adShowbusiAccMapper.selectAccByPageNo(adShowbusiAcc);
	}
	public List<AdShowbusiAcc> selectAccByShopShowId(AdShowbusiAcc adShowbusiAcc){
		return adShowbusiAccMapper.selectAccByShopShowId(adShowbusiAcc);
	}
}
