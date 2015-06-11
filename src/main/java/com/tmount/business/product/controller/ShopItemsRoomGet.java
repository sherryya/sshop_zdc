package com.tmount.business.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdShopRoomRel;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class ShopItemsRoomGet extends ControllerBase{
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.items.room.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(),
				"items_id"));
		
		GdShopRoomRel gdShopRoomRel = goodsService.getGdShopRoomRelByItemsId(itemsId);
		
		if (gdShopRoomRel == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_ROOM, new Object[]{itemsId});
		}

		responseBodyJson.writeNumberField("shop_floor_id", gdShopRoomRel.getShopFloorId());
		responseBodyJson.writeNumberField("position_x", gdShopRoomRel.getPositionX());
		responseBodyJson.writeNumberField("position_y", gdShopRoomRel.getPositionY());
		responseBodyJson.writeNumberField("i360_pic", gdShopRoomRel.getI360Pic());
		responseBodyJson.writeNumberField("men_count", gdShopRoomRel.getMenCount());
		responseBodyJson.writeStringField("mark", gdShopRoomRel.getCanCheck());
	}
}
