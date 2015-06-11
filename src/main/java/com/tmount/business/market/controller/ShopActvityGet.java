package com.tmount.business.market.controller;

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
import com.tmount.business.market.service.ActionService;
import com.tmount.business.pub.service.PubService;
import com.tmount.db.market.dto.AcActionInfo;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取商店活动列表
 * @author lugz
 *
 */
@Controller
public class ShopActvityGet extends ControllerBase {
	@Autowired
	private ActionService actionService;

	@Autowired
	private PubService pubService;

	@RequestMapping(value = "shop.activity.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Integer companyId = ParamData.getInt(requestParam.getBodyNode(), "compnay_id", -1);
		Long shopId = new Long(ParamData.getLong(requestParam.getBodyNode(),
				"shop_id", -1));
		Date beginTime = ParamData.getDate(requestParam.getBodyNode(), "begin_time", null);
		Date endTime = ParamData.getDate(requestParam.getBodyNode(), "end_time", null);
		AcActionInfo acActionInfo = new AcActionInfo();
		if (companyId != -1) {
			acActionInfo.setCompanyId(companyId);
		} else {
			if (shopId != -1) {
				acActionInfo.setShopId(shopId);
			}
		}
		acActionInfo.setBeginTime(beginTime);
		acActionInfo.setEndTime(endTime);
		List<AcActionInfo> acActionInfoList = actionService.getAcActionInfoListBySelective(acActionInfo);

		DbTime dbTime = pubService.getDbTime();
		responseBodyJson.writeArrayFieldStart("activity_list");
		if (acActionInfoList.size() > 0) {
			Iterator<AcActionInfo> it = acActionInfoList.iterator();
			while (it.hasNext()) {
				acActionInfo = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("actions_id", acActionInfo.getActionsId());
				responseBodyJson.writeStringField("actions_name", acActionInfo.getActionsName());
				responseBodyJson.writeStringField("actions_descrip", acActionInfo.getActionsDescrip());
				responseBodyJson.writeStringField("begin_time", requestParam.dateToString(acActionInfo.getBeginTime()));
				responseBodyJson.writeStringField("end_time", requestParam.dateToString(acActionInfo.getEndTime()));
				if (dbTime.getDbTime().getTime() >= acActionInfo.getEndTime().getTime()) {
					responseBodyJson.writeStringField("is_end", "Y");
				} else {
					responseBodyJson.writeStringField("is_end", "N");
				}
				responseBodyJson.writeNumberField("pic_id", acActionInfo.getPicId());
				responseBodyJson.writeStringField("http_url", acActionInfo.getHttpUrl());
				responseBodyJson.writeStringField("web_url", acActionInfo.getWebUrl());
				responseBodyJson.writeNumberField("orders", acActionInfo.getOrders());
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
