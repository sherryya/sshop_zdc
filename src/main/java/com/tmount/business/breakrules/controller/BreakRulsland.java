package com.tmount.business.breakrules.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.breakrules.service.BreakRulesService;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取省市级联信息
 * 
 * @author 何鑫
 * 
 */
@Controller
public class BreakRulsland extends ControllerBaseByLogin {
	@Autowired
	private BreakRulesService breakRulesService;

	@RequestMapping(value = "BreakRulesLandSelect.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		List<TZdcBreakrulescitylist> cityList = breakRulesService
				.selectbreakerLandInfo();

		Set<String> set = new HashSet<String>();
		// 获取全部省的名称
		for (TZdcBreakrulescitylist tZdcBreakrulescitylist : cityList) {
			String provinceName = tZdcBreakrulescitylist.getProvince();
			set.add(provinceName);
		}
		Map<String, List<TZdcBreakrulescitylist>> list = new HashMap<String, List<TZdcBreakrulescitylist>>();
		// 根据获取的省的数据遍历封装成整体的数据

		for (String provinceName : set) {
			List<TZdcBreakrulescitylist> cityName = new ArrayList<TZdcBreakrulescitylist>();
			for (TZdcBreakrulescitylist tZdcBreakrulescitylist : cityList) {
				String province = tZdcBreakrulescitylist.getProvince();
				if (province.equals(provinceName)) {
					if (tZdcBreakrulescitylist.getCityName() != null
							&& tZdcBreakrulescitylist.getCityName() != province) {
						cityName.add(tZdcBreakrulescitylist);
					}
				}
			}
			list.put(provinceName, cityName);
		}
		responseBodyJson.writeFieldName("Object");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(responseBodyJson, list);

	}
}
