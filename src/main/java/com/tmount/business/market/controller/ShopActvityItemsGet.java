package com.tmount.business.market.controller;

import java.io.IOException;
import java.util.Date;
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
import com.tmount.business.market.service.ActionService;
import com.tmount.db.market.vo.AcActionItemsInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取商店活动下得商品列表
 * @author lugz
 *
 */
@Controller
public class ShopActvityItemsGet extends ControllerBase {
	@Autowired
	private ActionService actionService;

	@RequestMapping(value = "shop.activity.items.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long actionsId = new Long(ParamData.getLong(requestParam.getBodyNode(), "actions_id"));
		List<AcActionItemsInfo> acActionItemsInfoList = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actionsId", actionsId);
		if (!requestParam.getBodyNode().path("orders_time").isMissingNode()) {
			Date ordersTime = new Date(ParamData.getLong(
					requestParam.getBodyNode(), "orders_time"));
			Integer fetchRows = new Integer(ParamData.getInt(
					requestParam.getBodyNode(), "fetch_rows"));
			map.put("orders_time", ordersTime);
			map.put("fetch_rows", fetchRows);
			acActionItemsInfoList = actionService.getAcActionItemsInfoListByActionsId(map);
		} else {
			acActionItemsInfoList = actionService.getAcActionItemsInfoListByActionsId(map);
		}


		responseBodyJson.writeArrayFieldStart("item_list");
		if (acActionItemsInfoList.size() > 0) {
			AcActionItemsInfo acActionItemsInfo;
			Iterator<AcActionItemsInfo> it = acActionItemsInfoList.iterator();
			while (it.hasNext()) {
				acActionItemsInfo = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("items_id", acActionItemsInfo.getItemsId());
				responseBodyJson.writeNumberField("shop_id", acActionItemsInfo.getShopId());
				responseBodyJson.writeNumberField("show_type", acActionItemsInfo.getShowType());
				responseBodyJson.writeNumberField("data_type", acActionItemsInfo.getDataType());
				responseBodyJson.writeStringField("has_child", acActionItemsInfo.getHasChild());
				responseBodyJson.writeStringField("items_name", acActionItemsInfo.getItemsName());
				responseBodyJson.writeStringField("name_spr", acActionItemsInfo.getNameSpr());
				responseBodyJson.writeStringField("items_intro", acActionItemsInfo.getItemsIntro());
				responseBodyJson.writeNumberField("small_pic", acActionItemsInfo.getSmallPic());
				responseBodyJson.writeStringField("http_url", acActionItemsInfo.getHttpUrl());
				responseBodyJson.writeStringField("web_url", acActionItemsInfo.getWebUrl());
				responseBodyJson.writeNumberField("price", acActionItemsInfo.getPrice());
				responseBodyJson.writeStringField("aunit", acActionItemsInfo.getAunit());
				responseBodyJson.writeNumberField("discount", acActionItemsInfo.getDiscount());
				responseBodyJson.writeNumberField("member_price", acActionItemsInfo.getMemberPrice());
				responseBodyJson.writeNumberField("orders_time", acActionItemsInfo.getOrdersTime().getTime());
				responseBodyJson.writeNumberField("discuss_count", acActionItemsInfo.getDiscussCount());
				responseBodyJson.writeNumberField("comment_value", acActionItemsInfo.getCommentValue());
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
