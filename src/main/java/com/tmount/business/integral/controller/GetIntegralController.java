package com.tmount.business.integral.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.integral.service.AddIntegralService;
import com.tmount.db.integral.dto.TItovIntegral;
import com.tmount.db.integral.dto.TItovIntegralRule;
import com.tmount.db.integral.dto.TItovIntegralTotal;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetIntegralController extends ControllerBase {
	@Autowired
	AddIntegralService addIntegralService;
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		Long account_id = ParamData.getLong(requestParam.getBodyNode(),"account_id");// 用户ID
		TItovIntegralTotal tItovIntegralTotal = new TItovIntegralTotal();
		tItovIntegralTotal.setAccount_id(account_id);
		TItovIntegralTotal tItovIntegralTotal1 = new TItovIntegralTotal();
		try {
			tItovIntegralTotal1 = addIntegralService.selectTotalByAccount(tItovIntegralTotal);
		} catch (Exception e) {
			e.printStackTrace();
			responseBodyJson.writeNumberField("result", 0);
			responseBodyJson.writeNumberField("Integral", 0);
			responseBodyJson.writeNumberField("Account_id", account_id);
		}
		responseBodyJson.writeNumberField("result", 1);
		responseBodyJson.writeNumberField("Integral", tItovIntegralTotal1.getIntegral());
		responseBodyJson.writeNumberField("Account_id", tItovIntegralTotal1.getAccount_id());
	}
	@RequestMapping(value = "Integral.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);

	}

}
