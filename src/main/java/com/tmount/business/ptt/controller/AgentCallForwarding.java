package com.tmount.business.ptt.controller;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.cloopen.restAPI.RestAPI_AgentCallForwarding;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.StringUtil;
@Controller
public class AgentCallForwarding extends ControllerBase {
	Logger logger = Logger.getLogger(startService.class.getName());
		@RequestMapping(value = "agentCallForwarding.get")
		@Override
		public void doRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			sendData(request, response);
		}
		@Override
		protected void doService(RequestParam requestParam,
				JsonGenerator responseBodyJson) throws ShopException,
				JsonGenerationException, IOException {
			String callid = new String(ParamData.getString(	requestParam.getBodyNode(), "callid"));// 呼叫ID
			Integer agentid = new Integer(ParamData.getString(	requestParam.getBodyNode(), "agentid"));//  座席ID，4位正整数，由应用侧管理
			String ret = null;
			RestAPI_AgentCallForwarding restAPI_AgentReady=new RestAPI_AgentCallForwarding();
			try {
			ret=	restAPI_AgentReady.GetAgentCallForwarding(agentid.toString(),callid);
		
			ret=StringUtil.getParmsFromXml(ret);
			logger.info("GetAgentCallForwarding##########agentid:"+agentid+",callid:"+callid+",ret:"+ret);
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
