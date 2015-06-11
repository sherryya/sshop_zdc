package com.tmount.business.order.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.business.order.service.OrderService;
import com.tmount.business.order.vo.OneOrder;
import com.tmount.business.pub.service.PubService;
import com.tmount.config.SyDataTypeDicStatic;
import com.tmount.db.pub.dto.DbTime;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 发送订单，支持发送单个订单也支持发送多个订单。
 * @author lugz
 *
 */
@Controller
public class UserOrderAdd extends ControllerBase {
	@Autowired
	private PubService pubService;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "user.order.add")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		DbTime dbTime = pubService.getDbTime();
		Date opTime = dbTime.getDbTime();
		Long loginAccept = pubService.updateSeqByName(SyDataTypeDicStatic.LoginAcceptSeq);
		Long userId =requestParam.getRequestDataHeader().getUserId();

		List<OneOrder> orderList = new LinkedList<OneOrder>();
		JsonNode bodyNode = requestParam.getBodyNode();
		JsonNode orderListNode = bodyNode.get("order_list");
		if (orderListNode != null) {	//多个订单
			Iterator<JsonNode> detailIt = orderListNode.elements();
			JsonNode orderJson;
			responseBodyJson.writeArrayFieldStart("order_list");
			while(detailIt.hasNext()) {
				orderJson = detailIt.next();
				responseBodyJson.writeStartObject();
				Long orderNo = pubService.updateSeqByName(SyDataTypeDicStatic.OrderNoSeq);
				responseBodyJson.writeNumberField("order_no", orderNo);
				responseBodyJson.writeNumberField("shop_id", ParamData.getLong(orderJson, "shop_id"));//商店ID
				orderList.add(OneOrder.addOneOrder(opTime, loginAccept, orderNo, userId, orderJson));
				responseBodyJson.writeEndObject();
			}
			responseBodyJson.writeEndArray();

			//orderService.insertUserOrder(orderList);
			responseBodyJson.writeNumberField("login_accept", loginAccept);
		} else {	//单个订单
			Long orderNo = pubService.updateSeqByName(SyDataTypeDicStatic.OrderNoSeq);
			JsonNode orderNode = bodyNode;
			orderList.add(OneOrder.addOneOrder(opTime, loginAccept, orderNo, userId, orderNode));
			//orderService.insertUserOrder(orderList);
			responseBodyJson.writeNumberField("order_no", orderNo);
			responseBodyJson.writeNumberField("login_accept", loginAccept);
		}		
	}
}
