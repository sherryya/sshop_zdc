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
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdSubItemInfo;
import com.tmount.db.product.vo.GdItemsItemRelConditions;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到商品下级商品列表。
 * 
 * @author lugz
 * 
 */
@Controller
public class ShopItemsSubitemsGet extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.items.subitems.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(),
				"items_id"));
		
		GdItemsItemRelConditions gdItemsItemRelConditions = new GdItemsItemRelConditions();
		gdItemsItemRelConditions.setItemsId(itemsId);
		
		List<GdSubItemInfo> gdGdItemsItemRelList = null;

		if (!requestParam.getBodyNode().path("orders_time").isMissingNode()) {
			Date ordersTime = new Date(ParamData.getLong(
					requestParam.getBodyNode(), "orders_time"));
			Integer fetchRows = new Integer(ParamData.getInt(
					requestParam.getBodyNode(), "fetch_rows"));
			gdItemsItemRelConditions.setOrdersTime(ordersTime);
			gdItemsItemRelConditions.setFetchRows(fetchRows);
			gdGdItemsItemRelList = goodsService
					.getGdItemsItemRelByItemsIdLimit(gdItemsItemRelConditions);
		} else {
			gdGdItemsItemRelList = goodsService.getGdItemsItemRelByItemsId(gdItemsItemRelConditions);
		}

		responseBodyJson.writeArrayFieldStart("item_list");
		if (gdGdItemsItemRelList.size() > 0) {
			GdSubItemInfo gdSubItemInfo;
			Iterator<GdSubItemInfo> it = gdGdItemsItemRelList.iterator();
			while (it.hasNext()) {
				gdSubItemInfo = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("items_id", gdSubItemInfo.getItemsId());
				responseBodyJson.writeNumberField("shop_id", gdSubItemInfo.getShopId());
				responseBodyJson.writeNumberField("show_type", gdSubItemInfo.getShowType());
				responseBodyJson.writeNumberField("data_type", gdSubItemInfo.getDataType());
				responseBodyJson.writeStringField("has_child", gdSubItemInfo.getHasChild());
				responseBodyJson.writeStringField("items_name", gdSubItemInfo.getItemsName());
				responseBodyJson.writeStringField("name_spr", gdSubItemInfo.getNameSpr());
				responseBodyJson.writeStringField("items_intro", gdSubItemInfo.getItemsIntro());
				responseBodyJson.writeNumberField("small_pic", gdSubItemInfo.getSmallPic());
				responseBodyJson.writeStringField("http_url", gdSubItemInfo.getHttpUrl());
				responseBodyJson.writeStringField("web_url", gdSubItemInfo.getWebUrl());
				responseBodyJson.writeNumberField("price", gdSubItemInfo.getPrice());
				responseBodyJson.writeStringField("aunit", gdSubItemInfo.getAunit());
				responseBodyJson.writeNumberField("discount", gdSubItemInfo.getDiscount());
				responseBodyJson.writeNumberField("member_price", gdSubItemInfo.getMemberPrice());
				responseBodyJson.writeNumberField("orders_time", gdSubItemInfo.getOrdersTime().getTime());
				responseBodyJson.writeNumberField("discuss_count", gdSubItemInfo.getDiscussCount());
				responseBodyJson.writeNumberField("comment_value", gdSubItemInfo.getCommentValue());
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
}