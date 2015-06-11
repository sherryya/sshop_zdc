package com.tmount.business.product.controller;

import java.io.IOException;
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
import com.tmount.db.product.vo.GdItemstypeRelInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取取平台目录下商品列表信息
 * 
 * @author lugz
 * 
 */
@Controller
public class ProductCatalogItemsGet extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "product.catalog.items.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Integer itemsType = new Integer(ParamData.getInt(requestParam.getBodyNode(), "items_type"));
		Integer fetchRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "fetch_rows", -1));
		Integer startRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "start_rows", -1));
		String orderName = ParamData.getString(requestParam.getBodyNode(), "order_name", null);
		String orderValue = ParamData.getString(requestParam.getBodyNode(), "order_value", null);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemsType", itemsType);
		if (startRows != -1) {
			paramMap.put("start", startRows);
			paramMap.put("end", fetchRows);
		}
		paramMap.put("orderName", orderName);
		paramMap.put("orderValue", orderValue);
		paramMap.put("state", "Y");	//只取状态为正常的。
		List <GdItemstypeRelInfo> relList = goodsService.selectItemstypeRelList(paramMap);

		responseBodyJson.writeArrayFieldStart("item_list");
		if (relList.size() > 0) {
			GdItemstypeRelInfo gdItemstypeRelInfo;
			Iterator<GdItemstypeRelInfo> it = relList.iterator();
			while (it.hasNext()) {
				gdItemstypeRelInfo = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("items_id", gdItemstypeRelInfo.getItemsId());
				responseBodyJson.writeNumberField("shop_id", gdItemstypeRelInfo.getShopId());
				responseBodyJson.writeNumberField("show_type", gdItemstypeRelInfo.getShowType());
				responseBodyJson.writeNumberField("data_type", gdItemstypeRelInfo.getDataType());
				responseBodyJson.writeStringField("has_child", gdItemstypeRelInfo.getHasChild());
				responseBodyJson.writeStringField("items_name", gdItemstypeRelInfo.getItemsName());
				responseBodyJson.writeStringField("name_spr", gdItemstypeRelInfo.getNameSpr());
				responseBodyJson.writeStringField("items_intro", gdItemstypeRelInfo.getItemsIntro());
				responseBodyJson.writeNumberField("small_pic", gdItemstypeRelInfo.getSmallPic());
				responseBodyJson.writeStringField("http_url", gdItemstypeRelInfo.getHttpUrl());
				responseBodyJson.writeStringField("web_url", gdItemstypeRelInfo.getWebUrl());
				responseBodyJson.writeNumberField("price", gdItemstypeRelInfo.getPrice());
				responseBodyJson.writeStringField("aunit", gdItemstypeRelInfo.getAunit());
				responseBodyJson.writeNumberField("discount", gdItemstypeRelInfo.getDiscount());
				responseBodyJson.writeNumberField("member_price", gdItemstypeRelInfo.getMemberPrice());
				responseBodyJson.writeStringField("up_flag", gdItemstypeRelInfo.getUpFlag());
				responseBodyJson.writeNumberField("orders_time", gdItemstypeRelInfo.getOrdersTime().getTime());
				responseBodyJson.writeNumberField("discuss_count", gdItemstypeRelInfo.getDiscussCount());
				responseBodyJson.writeNumberField("comment_value", gdItemstypeRelInfo.getCommentValue());
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
