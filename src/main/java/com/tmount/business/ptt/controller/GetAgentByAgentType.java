package com.tmount.business.ptt.controller;

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
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.ptt.service.PttEmpAgentService;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetAgentByAgentType extends ControllerBase {
	@Autowired
	private PttEmpAgentService pttEmpAgentService;
	@RequestMapping(value = "AgentByAgentType.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String agentType = ParamData.getString(requestParam.getBodyNode(), "agentType");//  坐席队列类型
		String agentstate = ParamData.getString(requestParam.getBodyNode(), "agentstate");//  坐席当前状态

		TItovPttEmpAgent tItovPttEmpAgent1=new TItovPttEmpAgent();
		tItovPttEmpAgent1.setAgentType(agentType);
		tItovPttEmpAgent1.setAgentstate(agentstate);
		List<TItovPttEmpAgent> ds=null;
		if(agentType.equalsIgnoreCase("0"))
		{
	    ds=pttEmpAgentService.selectAgentByAgentType(tItovPttEmpAgent1);
		}
		else
		{
		ds=pttEmpAgentService.selectAgentByAgentType(tItovPttEmpAgent1);
		}
        if(ds!=null)
        {
        	if (ds.size() != 0) {
        		responseBodyJson.writeArrayFieldStart("activity_list");
				Iterator<TItovPttEmpAgent> it = ds.iterator();
				while (it.hasNext()) {
					TItovPttEmpAgent tItovPttEmpAgent;
					tItovPttEmpAgent = it.next();
					responseBodyJson.writeStartObject();
					responseBodyJson.writeNumberField("agent_id", tItovPttEmpAgent.getAgentId());
					responseBodyJson.writeStringField("agent_name", tItovPttEmpAgent.getAgent_name());
					responseBodyJson.writeEndObject();
				}
				responseBodyJson.writeEndArray();
        	}
		//responseBodyJson.writeNumberField("agent_id", tItovPttEmpAgent.getAgentId());
		//responseBodyJson.writeStringField("agent_name", tItovPttEmpAgent.getAgent_name());
        }
        else
        {
        	throw new ShopBusiException(
					ShopBusiErrorBundle.VOIP_ERROR,null);
        }
	}
 


}
