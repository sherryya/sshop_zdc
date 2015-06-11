package com.tmount.business.ptt.controller;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tmount.business.cloopen.restAPI.RestAPI_AgentOffwork;
import com.tmount.business.cloopen.restAPI.RestAPI_AgentOnwork;
import com.tmount.business.cloopen.restAPI.RestAPI_AgentReady;
import com.tmount.business.ptt.service.PttEmpAgentService;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class controllerAgentReady extends ControllerBase {
	Logger logger = Logger.getLogger(controllerAgentReady.class.getName());
	
	@Autowired
	private PttEmpAgentService pttEmpAgentService;
	@RequestMapping(value = "agentState.upd")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String number = new String(ParamData.getString(	requestParam.getBodyNode(), "number"));// 座席号码，手机号或座机号或voip帐号
		Integer agentid = new Integer(ParamData.getString(	requestParam.getBodyNode(), "agentid"));//  座席ID，4位正整数，由应用侧管理
		Integer agenttype = new Integer(ParamData.getString(	requestParam.getBodyNode(), "agenttype"));// 0：上班  1： 就绪   2:暂停 （下班）
		String agentType =ParamData.getString(	requestParam.getBodyNode(), "agentType");
/*		TItovPttEmpAgent  tItovPttEmpAgent= new TItovPttEmpAgent();
		tItovPttEmpAgent.setVoipaccount(number);
		tItovPttEmpAgent.setAgentId(agentid);*/
		String ret = null;
		try {
			switch (agenttype) {
			case 0://上班
				try {
					ret=AgentOnwork(number, agentid.toString(),"0",agentType);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1://就绪
				ret=AgentReady(agentid.toString());
				logger.info("!!!!!!!!!!!!!!!!!!!t_itov_ptt_emp_agent_start!!!!!!!!!!!!!!!!!!!!!!!!!");
				//更新t_itov_ptt_emp_agent表中agentstate
				TItovPttEmpAgent tItovPttEmpAgent = new TItovPttEmpAgent();
				tItovPttEmpAgent.setAgentId(agentid);
				tItovPttEmpAgent.setAgentstate("1");
				pttEmpAgentService.updateAgentStateByAgentID(tItovPttEmpAgent);
				logger.info("!!!!!!!!!!!!!!!!!!!t_itov_ptt_emp_agent_end!!!!!!!!!!!!!!!!!!!!!!!!!");
				break;
			case 2://下班
				ret=AgentOffwork(number, agentid.toString());
				logger.info("!!!!!!!!!!!!!!!!!!!t_itov_ptt_emp_agent_start!!!!!!!!!!!!!!!!!!!!!!!!!");
				//更新t_itov_ptt_emp_agent表中agentstate
				TItovPttEmpAgent tItovPttEmpAgent_new = new TItovPttEmpAgent();
				tItovPttEmpAgent_new.setAgentId(agentid);
				tItovPttEmpAgent_new.setAgentstate("2");
				pttEmpAgentService.updateAgentStateByAgentID(tItovPttEmpAgent_new);
				logger.info("!!!!!!!!!!!!!!!!!!!t_itov_ptt_emp_agent_end!!!!!!!!!!!!!!!!!!!!!!!!!");
				break;
			case 3://暂停
				try {
					ret=AgentOnwork(number, agentid.toString(),"2",agentType);//锁定用户
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ret);
		responseBodyJson.writeStringField("result", ret);
	}
  private String AgentOnwork(String number,String agentid, String agentstate,String agentType) throws KeyManagementException, NoSuchAlgorithmException, JsonProcessingException, IOException, DocumentException
  {
	    RestAPI_AgentOnwork restAPI_AgentOnwork=new  RestAPI_AgentOnwork();
		String xml = restAPI_AgentOnwork.getAgentOnWork(number, agentid.toString(),"0",agentType);
	    String ret=getXml(xml);
        return ret;
  }
  private String AgentOffwork(String number,String agentid) throws KeyManagementException, NoSuchAlgorithmException, JsonProcessingException, IOException
  {
	   RestAPI_AgentOffwork restAPI_AgentOffwork=new  RestAPI_AgentOffwork();
	   String	xml = restAPI_AgentOffwork.getAgentOffWork(number, agentid.toString());
	   String ret=getXml(xml);
       return ret;
  }
  private String AgentReady(String agentid) throws KeyManagementException, NoSuchAlgorithmException
  {
	   RestAPI_AgentReady restAPI_AgentReady=new  RestAPI_AgentReady();
	   String xml = restAPI_AgentReady.getAgentReady(agentid.toString());
	   String ret=getXml(xml);
	   return ret;
  }
  private String getXml(String xml)
  {
	  Document doc = null;
	  String ret;
		try {
			doc = DocumentHelper.parseText(xml.toString());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		Integer statusCode;
		statusCode=  Integer.valueOf(root.elementTextTrim("statusCode")) ;
		System.out.println("statusCode:"+statusCode);
	/*	switch (statusCode) {
		case 108026:
			ret="0";//已经上班
			break;
		case 000000:
			ret="0";//正常   104024 已经就绪   108027  没有上班
			break;
		default:
			ret="-1";//系统异常    
			break;
		}*/
		return statusCode.toString();
  }
}

