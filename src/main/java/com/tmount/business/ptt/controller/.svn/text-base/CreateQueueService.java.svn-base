package com.tmount.business.ptt.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.cloopen.restAPI.RestAPI_CreateQueue;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.StringUtil;
@Controller
public class CreateQueueService extends ControllerBase {
	@RequestMapping(value = "createQueue.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String queuetype = new String(ParamData.getString(	requestParam.getBodyNode(), "queuetype"));// 呼叫ID
		String typedes = new String(ParamData.getString(	requestParam.getBodyNode(), "typedes"));//  座席ID，4位正整数，由应用侧管理
		String ret = null;
		RestAPI_CreateQueue restAPI_CreateQueue=new RestAPI_CreateQueue();
		try {
		ret=	restAPI_CreateQueue.GetAgentCallForwarding(queuetype, typedes);
		ret=StringUtil.getParmsFromXml(ret);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   responseBodyJson.writeStringField("result", ret);
	}
}
