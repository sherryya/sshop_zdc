package com.tmount.business.product.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdCompanyDic;
import com.tmount.db.product.dto.GdShopInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取公司信息
 * @author lugz
 *
 */
@Controller
public class CompanyGet extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "company.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Date updateTime = ParamData.getDate(requestParam.getBodyNode(), "update_time");
		Integer companyId = new Integer(ParamData.getInt(requestParam.getBodyNode(), "company_id"));
		GdCompanyDic gdCompanyDic = goodsService.getGdCompanyDicByCompanyId(companyId);
		if (gdCompanyDic ==  null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_COMPANY, new Object[]{companyId});
		}
		
		responseBodyJson.writeStringField("update_time", requestParam.dateToString(gdCompanyDic.getUpdateTime()));
		responseBodyJson.writeStringField("company_name", gdCompanyDic.getCompanyName());
		responseBodyJson.writeStringField("company_intro", gdCompanyDic.getCompanyIntro());
		responseBodyJson.writeArrayFieldStart("shop_list");
		GdShopInfo gdShopInfoIn = new GdShopInfo();
		gdShopInfoIn.setCompanyId(gdCompanyDic.getCompanyId());
		gdShopInfoIn.setUpdateTime(updateTime);
		List<GdShopInfo> gdShopInfoList = goodsService.getGdShopInfoByCompanyId(gdShopInfoIn);
		GdShopInfo gdShopInfo;
		Iterator<GdShopInfo> it = gdShopInfoList.iterator();
		while (it.hasNext()) {
			gdShopInfo = it.next();
			responseBodyJson.writeStartObject();
			responseBodyJson.writeNumberField("shop_id", gdShopInfo.getShopId());
			responseBodyJson.writeStringField("shop_name", gdShopInfo.getShopName());
			responseBodyJson.writeNumberField("city_code", gdShopInfo.getCityCode());
			responseBodyJson.writeNumberField("option_x", gdShopInfo.getOptionX());
			responseBodyJson.writeNumberField("option_y", gdShopInfo.getOptionY());
			responseBodyJson.writeStringField("shop_intro", gdShopInfo.getShopIntro());
			responseBodyJson.writeStringField("name_spr", gdShopInfo.getNameSpr());
			responseBodyJson.writeNumberField("scan_pic", gdShopInfo.getScanPic());
			responseBodyJson.writeStringField("http_url", gdShopInfo.getHttpUrl());
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();
	}
}
