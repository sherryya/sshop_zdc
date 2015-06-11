package com.tmount.business.product.controller;

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
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.vo.GdShopItemsRelInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取单公司某个商品分类下商品列表信息(支持分页和全部获取)
 * 
 * @author lugz
 * 
 */
@Controller
public class ShopItemstypeItemsGet extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.itemstype.items.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		List<GdShopItemsRelInfo> gdShopItemstypeDicList = null;
		Long shopId = new Long(ParamData.getLong(
				requestParam.getBodyNode(), "shop_id"));
		Integer sitemsType = new Integer(ParamData.getInt(
				requestParam.getBodyNode(), "sitems_type"));

		Map<String, Object> inparam = new HashMap<String, Object>();
		inparam.put("shopId", shopId);
		inparam.put("sitemsType", sitemsType);
		inparam.put("itemsState", "Y");
		if (!requestParam.getBodyNode().path("orders_time").isMissingNode()) {
			
			Date ordersTime = new Date(ParamData.getLong(requestParam.getBodyNode(), "orders_time"));
			Integer fetchRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "fetch_rows"));
			inparam.put("ordersTime", ordersTime);
			inparam.put("fetchRows", fetchRows);
			
			gdShopItemstypeDicList = goodsService.selectShopItemsRelList(inparam);
		} else {
			gdShopItemstypeDicList = goodsService
					.selectShopItemsRelList(inparam);
		}

		responseBodyJson.writeArrayFieldStart("item_list");
		if (gdShopItemstypeDicList.size() > 0) {
			GdShopItemsRelInfo gdShopItemsRelInfo;
			Iterator<GdShopItemsRelInfo> it = gdShopItemstypeDicList.iterator();
			while (it.hasNext()) {
				gdShopItemsRelInfo = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("items_id", gdShopItemsRelInfo.getItemsId());
				responseBodyJson.writeNumberField("shop_id", gdShopItemsRelInfo.getShopId());
				responseBodyJson.writeNumberField("show_type", gdShopItemsRelInfo.getShowType());
				responseBodyJson.writeNumberField("data_type", gdShopItemsRelInfo.getDataType());
				responseBodyJson.writeStringField("has_child", gdShopItemsRelInfo.getHasChild());
				responseBodyJson.writeStringField("items_name", gdShopItemsRelInfo.getItemsName());
				responseBodyJson.writeStringField("name_spr", gdShopItemsRelInfo.getNameSpr());
				responseBodyJson.writeStringField("items_intro", gdShopItemsRelInfo.getItemsIntro());
				responseBodyJson.writeNumberField("small_pic", gdShopItemsRelInfo.getSmallPic());
				responseBodyJson.writeStringField("http_url", gdShopItemsRelInfo.getHttpUrl());
				responseBodyJson.writeStringField("web_url", gdShopItemsRelInfo.getWebUrl());
				responseBodyJson.writeNumberField("price", gdShopItemsRelInfo.getPrice());
				responseBodyJson.writeStringField("aunit", gdShopItemsRelInfo.getAunit());
				responseBodyJson.writeNumberField("discount", gdShopItemsRelInfo.getDiscount());
				responseBodyJson.writeNumberField("member_price", gdShopItemsRelInfo.getMemberPrice());
				responseBodyJson.writeStringField("up_flag", gdShopItemsRelInfo.getUpFlag());
				responseBodyJson.writeNumberField("orders_time", gdShopItemsRelInfo.getOrdersTime().getTime());
				responseBodyJson.writeNumberField("discuss_count", gdShopItemsRelInfo.getDiscussCount());
				responseBodyJson.writeNumberField("comment_value", gdShopItemsRelInfo.getCommentValue());
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
