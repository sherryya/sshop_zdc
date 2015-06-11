package com.tmount.business.ptt.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.tmount.business.ptt.service.PttCallLogService;
import com.tmount.business.ptt.service.PttEmpAgentService;
import com.tmount.business.ptt.service.PttPersonalInfoByAgentidService;
import com.tmount.db.ptt.dto.TItovPttCallLog;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;
import com.tmount.db.user.dto.UsAccount;
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
public class startService extends ControllerBaseByHttp {
	Logger logger = Logger.getLogger(startService.class.getName());

	@Autowired
	private PttCallLogService pttCallLogService;

	@Autowired
	private PttEmpAgentService pttEmpAgentService;

	@Autowired
	private PttPersonalInfoByAgentidService pttPersonalInfoByAgentidService;

	@RequestMapping(value = "startservice")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected String doService(HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~startService is bigining");
		String callid = request.getParameter("callid");// 一个由32位数字、字符组成的唯一呼叫标识符
		String from = request.getParameter("from");// 主叫电话号码
		String to = request.getParameter("to");// 被叫电话号码
		String direction = request.getParameter("direction");// 呼叫方向，取值有0（呼入），1（呼出）
		// 判断是否注册用户，非注册用户不提供此服务
		UsAccount tItov_personal = new UsAccount();
		tItov_personal = pttPersonalInfoByAgentidService.selectByPersonTel(from);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~from:"	+ from + "");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ tItov_personal + "");
		String xml = "";
		if (tItov_personal == null) {
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Play loop=\"1\">noreg.wav</Play></Response>";
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stopService is end ");
			return xml;
		} else {

			// 1.入通话流水表t_itov_ptt_call_log
			TItovPttCallLog tItovPttCallLog = new TItovPttCallLog();
			tItovPttCallLog.setCallid(callid);
			tItovPttCallLog.setCallfrom(from);
			tItovPttCallLog.setCallto(to);
			Date date = new Date();
			tItovPttCallLog.setCalldatetime(date);
			tItovPttCallLog.setDirection(direction);
			pttCallLogService.insert(tItovPttCallLog);

			TItovPttEmpAgent tItovPttEmpAgent = new TItovPttEmpAgent();
			tItovPttEmpAgent = pttEmpAgentService.selectAgentByAgentState("1");
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~tItovPttEmpAgent:"	+ tItovPttEmpAgent);
			if (tItovPttEmpAgent != null) {
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<Response>"
						+ "<EnterCCS queuetype=\"0\" timeout=\"30\" callid=\""
						+ callid 
						+ "\">"
						/*+ " <Play loop=\"-1\">switch.wav</Play>"*/
						+ " </EnterCCS>" 
						+ " </Response>";
			} else {
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<Response>"
						+ " <Get action=\"dtmfreport\" numdigits=\"1\">"
						+ "     <Play loop=\"3\">Busy.wav</Play>"
						+ " </Get>" 
						+ "  <Play loop=\"1\">quit.wav</Play>" +
						// "  <Redirect>stopservice</Redirect>" +
						" </Response>";
			}
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"	+ xml);
			return xml;
		}
		
	}

	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
