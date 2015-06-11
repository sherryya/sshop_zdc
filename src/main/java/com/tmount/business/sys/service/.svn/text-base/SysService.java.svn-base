package com.tmount.business.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.product.dao.SyCityDictMapper;
import com.tmount.db.product.dto.SyCityDict;
import com.tmount.db.pub.dao.DictMapper;
import com.tmount.db.pub.dto.Dict;
import com.tmount.db.sys.dao.SyClientVerMapper;
import com.tmount.db.sys.dao.SyHotWordsMapper;
import com.tmount.db.sys.dao.SySearchWordMapper;
import com.tmount.db.sys.dto.SyClientVer;
import com.tmount.db.sys.dto.SyHotWords;
import com.tmount.db.sys.dto.SySearchWord;

@Service
public class SysService {
	@Autowired
	private SyHotWordsMapper syHotWordsMapper;

	@Autowired
	private SySearchWordMapper sySearchWordMapper;
	
	@Autowired
	private SyCityDictMapper syCityDictMapper;
	
	@Autowired
	private DictMapper dictMapper;
	
	@Autowired
	private SyClientVerMapper syClientVerMapper;
	/**
	 * 查询所有的系统热词。
	 * @return
	 */
	public List<SyHotWords> selectAllSyHotWordsList() {
		return syHotWordsMapper.selectAll();
	}

	/**
	 * 查询所有的系统搜索词。
	 * @return
	 */
	public List<SySearchWord> selectAllSySearchWordsList() {
		return sySearchWordMapper.selectAll();
	}
	/**
	 * 查询所有的省份信息。
	 * @return
	 */
	public List<SyCityDict> selectProvinceList(){
		return syCityDictMapper.selectProvinceList();
	}
	/**
	 * 查询地市信息。
	 * @param paramMap 
	 * @return
	 */
	public List<SyCityDict> selectCityList(Map<String, Object> paramMap){
		return syCityDictMapper.selectCityList(paramMap);
	}
	
	/**
	 * 查询区县信息。
	 * @param paramMap 
	 * @return
	 */
	public List<SyCityDict> selectDistrictList(Map<String, Object> paramMap){
		return syCityDictMapper.selectDistrictList(paramMap);
	}

	public List<SyCityDict> selectCityListByParentCode(int parentCode) {
		// TODO Auto-generated method stub
		return syCityDictMapper.selectChildCityList(parentCode);
	}
	
	public List<Dict> selectReArrivalTimeDic(){
		return dictMapper.re_arrival_time_dic();
		
	}
	public SyClientVer selectSyClientVerByPK(Integer clientId){
		return syClientVerMapper.selectByPrimaryKey(clientId);
		
	}
	
}
