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
import com.tmount.business.product.vo.ProductMonthConsumer;
import com.tmount.db.order.vo.ProductMonthConsumerDetail;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 统计当月指定商品，各个买家的购买情况。
 * 
 * @author lugz
 * 
 */
@Controller
public class ProductMonthConsumerStat extends ControllerBase {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "product.month.consumer.stat")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long itemsId = new Long(ParamData.getLong(requestParam.getBodyNode(), "items_id"));
		String state  = ParamData.getString(requestParam.getBodyNode(), "state", null);
		Integer fetchRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "fetch_rows"));
		Integer startRows = new Integer(ParamData.getInt(requestParam.getBodyNode(), "start_rows"));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemsId", itemsId);
		if (state != null) {
			paramMap.put("state", state);
		}
		paramMap.put("start", startRows);
		paramMap.put("end", fetchRows);

		ProductMonthConsumer productMonthConsumer = goodsService.getProductMonthConsumer(paramMap);
		responseBodyJson.writeNumberField("sale_count", productMonthConsumer.getSaleCount());
		responseBodyJson.writeNumberField("discuss30_count", productMonthConsumer.getDiscuss30Count());
		responseBodyJson.writeStringField("items_name", productMonthConsumer.getItemsName());
		responseBodyJson.writeNumberField("total_rows", productMonthConsumer.getTotalRows());
		List<ProductMonthConsumerDetail> itemList = productMonthConsumer.getItemList();

		responseBodyJson.writeArrayFieldStart("item_list");
		if (itemList != null) {
			if (itemList.size() > 0) {
				ProductMonthConsumerDetail productMonthConsumerDetail;
				Iterator<ProductMonthConsumerDetail> it = itemList.iterator();
				while (it.hasNext()) {
					productMonthConsumerDetail = it.next();
					responseBodyJson.writeStartObject();
					responseBodyJson.writeNumberField("user_id", productMonthConsumerDetail.getUserId());
					responseBodyJson.writeStringField("user_name", productMonthConsumerDetail.getUserName());
					responseBodyJson.writeStringField("user_account", productMonthConsumerDetail.getUserAccount());
					responseBodyJson.writeNumberField("all_price", productMonthConsumerDetail.getAllPrice());
					responseBodyJson.writeNumberField("acount", productMonthConsumerDetail.getAcount());
					responseBodyJson.writeStringField("create_date", requestParam.dateToString(productMonthConsumerDetail.getCreateDate()));
					responseBodyJson.writeNumberField("state", productMonthConsumerDetail.getState());
					responseBodyJson.writeStringField("state_name", productMonthConsumerDetail.getStateName());
					responseBodyJson.writeEndObject();
				}
			}
		}

		responseBodyJson.writeEndArray();
	}
}
