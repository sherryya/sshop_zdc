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
import com.tmount.db.product.dto.GdInvoiceDic;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class ShopInvoiceGet extends ControllerBase{
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "shop.invoice.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		List<GdInvoiceDic> gdInvoiceDicList = goodsService
					.getGdInvoiceDicByShopId(new Long(ParamData.getLong(
							requestParam.getBodyNode(), "shop_id")));

		responseBodyJson.writeArrayFieldStart("subject_list");
		if (gdInvoiceDicList.size() > 0) {
			GdInvoiceDic gdInvoiceDic;
			Iterator<GdInvoiceDic> it = gdInvoiceDicList.iterator();
			while (it.hasNext()) {
				gdInvoiceDic = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeStringField("subject_name", gdInvoiceDic.getSubjectName());
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
}
