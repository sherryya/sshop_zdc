package com.tmount.business.order.controller;

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
import com.tmount.business.order.service.OrderService;
import com.tmount.db.order.vo.OrderStateStat;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 用户订单状态统计
 * @author lugz
 *
 */
@Controller
public class UserOrderStateStat extends ControllerBase {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "user.order.state.stat")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long userId = ParamData.getLong(requestParam.getBodyNode(), "user_id");
		List<OrderStateStat> orderStateStatList = orderService.orderStateStat(userId);
		
		responseBodyJson.writeArrayFieldStart("item_list");
		if (orderStateStatList.size() > 0) {
			OrderStateStat orderStateStat;
			Iterator<OrderStateStat> it = orderStateStatList.iterator();
			while(it.hasNext()) {
				orderStateStat = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("state", orderStateStat.getState());
				responseBodyJson.writeStringField("state_name", orderStateStat.getStateName());
				responseBodyJson.writeStringField("user_state_name", orderStateStat.getUserStateName());
				responseBodyJson.writeNumberField("acount", orderStateStat.getAcount());
				responseBodyJson.writeEndObject();
			}
		}
		
		responseBodyJson.writeEndArray();
	}
}
