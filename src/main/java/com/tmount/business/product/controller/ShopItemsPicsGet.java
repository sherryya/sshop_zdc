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
import com.tmount.db.product.dto.GdPicResRel;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class ShopItemsPicsGet extends ControllerBase{
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.items.pics.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(),
				"items_id"));
		
		GdPicResRel gdPicResRelIn = new GdPicResRel();
		gdPicResRelIn.setTableName("gd_items_info");
		gdPicResRelIn.setRecordId(itemsId);
		gdPicResRelIn.setStoreType(1);
		List<GdPicResRel> gdPicResRelList= goodsService.getGdPicResRelBySelective(gdPicResRelIn);
		
		responseBodyJson.writeArrayFieldStart("pic_list");
		if (gdPicResRelList.size() > 0) {
			GdPicResRel gdPicResRel;
			Iterator<GdPicResRel> it = gdPicResRelList.iterator();
			while (it.hasNext()) {
				gdPicResRel = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("pic_id", gdPicResRel.getResId());
				responseBodyJson.writeStringField("mark", "无");
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
