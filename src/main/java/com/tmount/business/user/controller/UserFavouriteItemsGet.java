package com.tmount.business.user.controller;

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
import com.tmount.business.user.service.UserService;
import com.tmount.db.product.dto.GdItemsExpRice;
import com.tmount.db.user.vo.UsFavouriteItemsDetail;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 用户收藏商品查询
 * @author lugz
 *
 */
@Controller
public class UserFavouriteItemsGet extends ControllerBase{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user.favourite.items.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = new Long(ParamData.getLong(requestParam.getBodyNode(), "user_id"));
		Long shopId = new Long(ParamData.getLong(requestParam.getBodyNode(), "shop_id", -1));
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(), "items_id", -1));
		Integer fetchRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "fetch_rows", -1));
		Integer startRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "start_rows", -1));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		if (shopId != -1) {
			paramMap.put("shopId", shopId);
		}
		if (itemsId != -1) {
			paramMap.put("itemsId", itemsId);
		}
		if (startRows != -1) {
			paramMap.put("start", startRows);
		}
		if (fetchRows != -1) {
			paramMap.put("end", fetchRows);
		}
		List<UsFavouriteItemsDetail> usShopCartItemsList = userService.getUsFavouriteItemsDetail(paramMap);
		int iCount = userService.getUsFavouriteItemsDetailCount(paramMap);
		responseBodyJson.writeNumberField("total_rows", iCount);
		responseBodyJson.writeArrayFieldStart("item_list");
		GdItemsExpRice gdItemsExpRice;
		if (usShopCartItemsList.size() > 0) {
			UsFavouriteItemsDetail usFavouriteItemsDetail;
			Iterator<UsFavouriteItemsDetail> it = usShopCartItemsList.iterator();
			while (it.hasNext()) {
				usFavouriteItemsDetail = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("user_tag_id", usFavouriteItemsDetail.getUserTagId());
				responseBodyJson.writeNumberField("discount", usFavouriteItemsDetail.getDiscount());
				responseBodyJson.writeNumberField("items_id", usFavouriteItemsDetail.getItemsId());
				responseBodyJson.writeNumberField("shop_id", usFavouriteItemsDetail.getShopId());
				responseBodyJson.writeStringField("shop_name", usFavouriteItemsDetail.getShopName());
				responseBodyJson.writeStringField("shop_web_url", usFavouriteItemsDetail.getShopWebUrl());
				responseBodyJson.writeStringField("shop_http_url", usFavouriteItemsDetail.getShopHttpUrl());
				responseBodyJson.writeNumberField("show_type", usFavouriteItemsDetail.getShowType());
				responseBodyJson.writeNumberField("data_type", usFavouriteItemsDetail.getDataType());
				responseBodyJson.writeStringField("has_child", usFavouriteItemsDetail.getHasChild());
				responseBodyJson.writeStringField("items_name", usFavouriteItemsDetail.getItemsName());
				responseBodyJson.writeStringField("name_spr", usFavouriteItemsDetail.getNameSpr());
				responseBodyJson.writeStringField("items_intro", usFavouriteItemsDetail.getItemsIntro());
				responseBodyJson.writeNumberField("small_pic", usFavouriteItemsDetail.getSmallPic());
				responseBodyJson.writeStringField("http_url", usFavouriteItemsDetail.getHttpUrl());
				responseBodyJson.writeStringField("web_url", usFavouriteItemsDetail.getWebUrl());
				responseBodyJson.writeNumberField("price", usFavouriteItemsDetail.getPrice());
				responseBodyJson.writeStringField("aunit", usFavouriteItemsDetail.getAunit());
				responseBodyJson.writeStringField("state", usFavouriteItemsDetail.getState());
				
				gdItemsExpRice = userService.getGdItemsExpRiceByItemsId(usFavouriteItemsDetail.getItemsId());
				if (gdItemsExpRice == null) {
					responseBodyJson.writeNumberField("stand_id", 0);
				} else {
					responseBodyJson.writeNumberField("stand_id", gdItemsExpRice.getStandId());
				}
				responseBodyJson.writeNumberField("discuss_count", usFavouriteItemsDetail.getDiscussCount());
				responseBodyJson.writeNumberField("comment_value", usFavouriteItemsDetail.getCommentValue());
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
}
