package com.tmount.business.order.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.order.service.OrderService;
import com.tmount.business.pub.service.PubService;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;

@Controller
public class UserOrderDelete extends ControllerBase {
	@Autowired
	private PubService pubService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "user.order.delete")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	/**
	 * 可以批量取消订单
	 * 	 报文结构:
	 *     order_list 可选
	 *           order_no 订单号，必填
	 *           mark 取消说明，必填。
	 */
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {		
		orderService.removeUserOrderRequest(requestParam, responseBodyJson);
	}
}
