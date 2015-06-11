package com.tmount.business.ptt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.tmount.business.ptt.service.PttCallLogService;
import com.tmount.db.ptt.dto.TItovPttCallLog;
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
public class StopService extends ControllerBaseByHttp {
	Logger logger = Logger.getLogger(StopService.class.getName());

	@Autowired
	private PttCallLogService pttCallLogService;

	@RequestMapping(value = "stopservice")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected String doService(HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stopService is bigining");
		
		String callid = request.getParameter("callid");// 一个由32位数字、字符组成的唯一呼叫标识符
		String callduration = request.getParameter("callduration");// 通话时长，单位：秒
		String recordurl = request.getParameter("recordurl");//  有录音必须 , 录音存放地址
		String recordid = request.getParameter("recordid");//  有录音必须, 录音文件Id
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~recordid~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+recordid);
		String errorcode = request.getParameter("errorcode");//   一个6位的数字串
		//1.入通话流水表t_itov_ptt_call_log
		TItovPttCallLog tItovPttCallLog =new TItovPttCallLog();
		tItovPttCallLog.setCallid(callid);
		if(!"".equals(callduration)&&(callduration!=null)){
			tItovPttCallLog.setCallduration(callduration);
		}else{
			tItovPttCallLog.setCallduration("0");
		}
		
		tItovPttCallLog.setRecordurl(recordurl);
		tItovPttCallLog.setRecordid(recordid);
		tItovPttCallLog.setErrorcode(errorcode);
		pttCallLogService.update(tItovPttCallLog);
		
		/*
		 * EnterCCS格式
		 * 参考：http://docs.cloopen.com/index.php/IVR_%E5%9D%90%E5%B8%AD
		 */
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><CmdNone/></Response>";
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stopService is end");
		return xml;
	}

	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
