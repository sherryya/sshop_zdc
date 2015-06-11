package com.tmount.business.ptt.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;
/*
 * 得到在线坐席VOIP信息
 */
@Controller
public class GetSubAccountForZDC extends ControllerBase {
	Logger logger = Logger.getLogger(startService.class.getName());
	@Autowired
	private PttSubaccountService pttSubaccountService;

	@RequestMapping(value = "subAccountForZDC.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		TItovPttSubaccount tItovPttSubaccount = new TItovPttSubaccount();
		tItovPttSubaccount = pttSubaccountService.selectForZDC();
		logger.info("=========== 查找坐席对列===================");
		if (tItovPttSubaccount == null) {
			logger.info("=========== 没有在线坐席 ===================");
			responseBodyJson.writeStringField("result", "0");
		} else {
			logger.info("=========== 得到在线坐席 ===================");
			logger.info("=========== subAccountSid:"+tItovPttSubaccount.getSubaccountsid()+",subToken:"+tItovPttSubaccount.getSubtoken()+",voipAccount:"+tItovPttSubaccount.getVoipaccount()+",voipPwd:"+tItovPttSubaccount.getVoippwd());
			responseBodyJson.writeStringField("result", "1");
			responseBodyJson.writeStringField("subAccountSid", tItovPttSubaccount.getSubaccountsid());
			responseBodyJson.writeStringField("subToken", tItovPttSubaccount.getSubtoken());
			responseBodyJson.writeStringField("voipAccount", tItovPttSubaccount.getVoipaccount());
			responseBodyJson.writeStringField("voipPwd", tItovPttSubaccount.getVoippwd());
			responseBodyJson.writeNumberField("roomId", tItovPttSubaccount.getRoomId());
		}
	}
}
