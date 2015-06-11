package com.tmount.business.product.controller;

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
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdShopFloorRelInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class ShopFloorGet extends ControllerBase{
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.floor.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		List<GdShopFloorRelInfo> gdShopFloorRelList = goodsService
					.getGdShopFloorRelByShopId(new Long(ParamData.getLong(
							requestParam.getBodyNode(), "shop_id")));

		responseBodyJson.writeArrayFieldStart("floor_list");
		if (gdShopFloorRelList.size() > 0) {
			GdShopFloorRelInfo gdShopFloorRelInfo;
			Iterator<GdShopFloorRelInfo> it = gdShopFloorRelList.iterator();
			while (it.hasNext()) {
				gdShopFloorRelInfo = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("shop_id", gdShopFloorRelInfo.getFloorId());
				responseBodyJson.writeStringField("floor_name", gdShopFloorRelInfo.getFloorName());
				responseBodyJson.writeNumberField("play_pic", gdShopFloorRelInfo.getPlayPic());
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
}
