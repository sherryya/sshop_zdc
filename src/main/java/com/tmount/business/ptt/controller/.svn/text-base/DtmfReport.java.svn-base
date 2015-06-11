package com.tmount.business.ptt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByHttp;
import com.tmount.util.ResponseData;
/**
 * 
 * 
 * @author
 * 
 */
@Controller
public class DtmfReport extends ControllerBaseByHttp {
	Logger logger = Logger.getLogger(DtmfReport.class.getName());

	@RequestMapping(value = "dtmfreport")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected String doService(HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~dtmfreport is bigining");
		String callid = request.getParameter("callid");// 一个由32位数字、字符组成的唯一呼叫标识符
		String digits = request.getParameter("digits");// 获取的按键
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:callid"+callid);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:digits"+digits);
		String xml="";
		if(digits.equals("1"))
		{
		 xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<Response>"
				+ "<EnterCCS queuetype=\"0\" timeout=\"30\" callid=\"" + callid + "\">"
				+ " <Play loop=\"-1\">switch.wav</Play>"
				+ " </EnterCCS>" + " </Response>";
		}
		else
		{
			 xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><CmdNone/></Response>";
		}
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~xml:"+xml);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~dtmfreport is end");
		return xml;
	}
	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		return null;
	}	
}
