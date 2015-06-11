package com.tmount.business.advertise.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.advertise.service.AdvertiseService;
import com.tmount.db.advertise.dto.AdShowcaseBusi;
import com.tmount.db.advertise.vo.AdvertiseInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 商店内商品人气检索，按照商品人气排序，返回指定的前N条信息
 * 
 * @author rendi
 * 
 */
@Controller
public class AdvertiseGet extends ControllerBase {
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@RequestMapping(value = "advertise.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		AdShowcaseBusi adShowcaseBusi = new AdShowcaseBusi();
		ObjectMapper objectMapper = new ObjectMapper();
		adShowcaseBusi.setShopId(ParamData.getLong(requestParam.getBodyNode(), "shopId"));
		adShowcaseBusi.setShowId(ParamData.getInt(requestParam.getBodyNode(), "showId"));
		
		/*获取给定商店下的给定区域，有多少页需要展示*/
		List<AdShowcaseBusi> adShowPageList = advertiseService.getAdPageListByShopShowId(adShowcaseBusi);
		Iterator<AdShowcaseBusi> adShowPageIterator = adShowPageList.iterator();
        while(adShowPageIterator.hasNext())
        {
        	AdShowcaseBusi adShowcaseBusiPage = adShowPageIterator.next();
        	/*赋值页数*/
        	adShowcaseBusi.setShowPageNo(adShowcaseBusiPage.getShowPageNo());
        	System.out.println("adShowcaseBusiPage.getShowPageNo()============="+adShowcaseBusiPage.getShowPageNo());
        	System.out.println("adShowcaseBusi.getShowPageNo()============="+adShowcaseBusi.getShowPageNo());
        	/*取出此页包含的广告列表*/
        	List<AdvertiseInfo> advertiseInfo = advertiseService.getAdShowBusiByShopShowId(adShowcaseBusi);
        	System.out.println("advertiseInfo.size()============="+advertiseInfo.size());
        	responseBodyJson.writeFieldName("page"+adShowcaseBusiPage.getShowPageNo());
            objectMapper.writeValue(responseBodyJson, advertiseInfo);
        }
        if (adShowPageList.size() == 0) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_AD, new Object[]{adShowcaseBusi.getShowId()});
		}
	}
}


