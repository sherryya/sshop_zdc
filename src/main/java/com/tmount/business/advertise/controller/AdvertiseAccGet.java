package com.tmount.business.advertise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.advertise.service.AdvertiseService;
import com.tmount.db.advertise.dto.AdShowbusiAcc;
import com.tmount.db.advertise.dto.AdShowcaseDict;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class AdvertiseAccGet extends ControllerBase {
	@Autowired
	private AdvertiseService advertiseService;

	@RequestMapping(value = "advertiseacc.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		AdShowbusiAcc adShowbusiAcc = new AdShowbusiAcc();

		ObjectMapper objectMapper = new ObjectMapper();

		Long shopId = ParamData.getLong(requestParam.getBodyNode(), "shopId");

		Integer showareaId = ParamData.getInt(requestParam.getBodyNode(),
				"showareaId");

		adShowbusiAcc.setShopId(shopId);

		adShowbusiAcc.setShowareaId(showareaId);
		
		List<AdShowcaseDict> adShowcaseDictList = advertiseService
				.getAdShowCaseByShowAreaId(showareaId);

		Iterator<AdShowcaseDict> adShowcaseDictIterator = adShowcaseDictList
				.iterator();
		while (adShowcaseDictIterator.hasNext()) {
			AdShowcaseDict adShowcaseDict = adShowcaseDictIterator.next();
			Long showId = adShowcaseDict.getShowId();
			adShowbusiAcc.setShowId(showId);
			responseBodyJson.writeObjectFieldStart("show"+adShowcaseDict.getOrderBy());
			//responseBodyJson.writeNumberField("itemsType"+adShowcaseDict.getOrderBy(), adShowcaseDict.getItemsType());
			responseBodyJson.writeNumberField("itemsType", adShowcaseDict.getItemsType());
			List<AdShowbusiAcc> adShowbusiAccList = advertiseService
					.selectAccByPageNo(adShowbusiAcc);
			Iterator<AdShowbusiAcc> adShowbusiAccPageNoIterator = adShowbusiAccList
					.iterator();
			while (adShowbusiAccPageNoIterator.hasNext()) {
				AdShowbusiAcc adShowbusiAccPageNo = adShowbusiAccPageNoIterator
						.next();
				adShowbusiAcc
						.setShowPageNo(adShowbusiAccPageNo.getShowPageNo());
				adShowbusiAcc.setIsDefault("N"); //先查询非默认广告如果查询不到再查询默认广告
				List<AdShowbusiAcc> adShowbusiSiteAccList = advertiseService
						.selectAccByShopShowId(adShowbusiAcc);
				adShowbusiAcc.setIsDefault("Y"); //先查询非默认广告如果查询不到再查询默认广告
				List<AdShowbusiAcc> adShowbusiSiteAccListDefault = advertiseService
						.selectAccByShopShowId(adShowbusiAcc);
				List<AdShowbusiAcc> adShowbusiSiteAccListFinal = new ArrayList<AdShowbusiAcc>();
				Map<Integer,AdShowbusiAcc> adShowbusiSiteAccMap = new HashMap<Integer,AdShowbusiAcc>();
				/*
				 * 默认广告列表与广告列表进行合并，如果没有广告则使用默认广告
				 * yanpx@20121213 22:29:00
				 * */
				for(AdShowbusiAcc adShowbusiAc :adShowbusiSiteAccList){
					if(!adShowbusiSiteAccMap.containsKey(adShowbusiAc.getShowsiteId())){
						adShowbusiSiteAccMap.put(adShowbusiAc.getShowsiteId(), adShowbusiAc);
					}
				}
				for(AdShowbusiAcc adShowbusiAcDefault : adShowbusiSiteAccListDefault){
					if(!adShowbusiSiteAccMap.containsKey(adShowbusiAcDefault.getShowsiteId())){
						adShowbusiSiteAccMap.put(adShowbusiAcDefault.getShowsiteId(), adShowbusiAcDefault);
					}
				}
				adShowbusiSiteAccListFinal.addAll(adShowbusiSiteAccMap.values());
				Iterator<AdShowbusiAcc> it = adShowbusiSiteAccListFinal.iterator();
				responseBodyJson.writeArrayFieldStart("page" + adShowbusiAccPageNo.getShowPageNo());
				while (it.hasNext()) {
					AdShowbusiAcc adShowbusiSiteAccListFinalIt = it.next();
					responseBodyJson.writeStartObject();
					responseBodyJson.writeNumberField("showLineAxis",adShowbusiSiteAccListFinalIt.getShowLineAxis());
					responseBodyJson.writeNumberField("showRowAxis",adShowbusiSiteAccListFinalIt.getShowRowAxis());
					responseBodyJson.writeNumberField("showsiteWidth",adShowbusiSiteAccListFinalIt.getShowsiteWidth());
					responseBodyJson.writeNumberField("showsiteHigh",adShowbusiSiteAccListFinalIt.getShowsiteHigh());
					responseBodyJson.writeNumberField("showsiteUnitId",adShowbusiSiteAccListFinalIt.getShowsiteUnitId());
					responseBodyJson.writeStringField("showsiteUnitUrl",adShowbusiSiteAccListFinalIt.getShowsiteUnitUrl());
					responseBodyJson.writeNumberField("showsitePicId",adShowbusiSiteAccListFinalIt.getShowsitePicId());
					responseBodyJson.writeNumberField("showsitePicWidth",adShowbusiSiteAccListFinalIt.getShowsitePicWidth());
					responseBodyJson.writeNumberField("showsitePicHigh",adShowbusiSiteAccListFinalIt.getShowsitePicHigh());
					responseBodyJson.writeStringField("showsiteAdText",adShowbusiSiteAccListFinalIt.getShowsiteAdText());
					responseBodyJson.writeEndObject();
				}
				responseBodyJson.writeEndArray();
			}
			responseBodyJson.writeEndObject();
		}
	}
}
