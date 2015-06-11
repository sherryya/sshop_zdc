package com.tmount.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tmount.db.product.dao.SyResourceSizeDetailMapper;
import com.tmount.db.product.dto.SyResourceSizeDetail;


public class DataDictionary {

	@Autowired
	private SyResourceSizeDetailMapper syResourceSizeDetailMapper;

	private static List<SyResourceSizeDetail> syResourceSizeDetailList;
	
	public static List<SyResourceSizeDetail> getSyResourceSizeDetailList() {
		return syResourceSizeDetailList;
	}
	public static void setSyResourceSizeDicList(List<SyResourceSizeDetail> syResourceSizeDetailList) {
		DataDictionary.syResourceSizeDetailList = syResourceSizeDetailList;
	}
	
	public void init() {
		syResourceSizeDetailList = syResourceSizeDetailMapper.selectAll();
		System.out.println("syResourceSizeDetailList successful ..............." + syResourceSizeDetailList.size());
	}
}
