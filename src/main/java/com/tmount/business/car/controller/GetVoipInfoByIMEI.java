package com.tmount.business.car.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.dto.TerminalInfo;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetVoipInfoByIMEI extends ControllerBaseByLogin {
	@Autowired
	private TerminalInfoService terminalInfoService;
	
	@Autowired
	private PttSubaccountService pttSubaccountService;
	
	@Autowired
	private UserService userService;
	Logger logger = Logger.getLogger(GetVoipInfoByIMEI.class.getName());

	@RequestMapping(value = "VoipInfoByIMEI.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		String terminal_imei = ParamData.getString(requestParam.getBodyNode(), "terminal_imei");
		//String account_id = ParamData.getString(requestParam.getBodyNode(), "account_id");
		String ter_type = ParamData.getString(requestParam.getBodyNode(), "ter_type");//0:手机  1：车机
		
		UsAccount usAccount =new UsAccount();
		long account_id=0;
		usAccount=terminalInfoService.selectAccountIDByIMEI(terminal_imei);
		if(usAccount!=null)
		{
			account_id=usAccount.getAccount_id();
		}
		
		TerminalInfo terminalInfo=new TerminalInfo();
		terminalInfo.setTerminal_imei(terminal_imei);
		terminalInfo.setAccount_id(account_id);
		TItovPttSubaccount tItovPttSubaccount = terminalInfoService.getVoipInfoByIMEI(terminalInfo);
		if (tItovPttSubaccount != null) {

			responseBodyJson.writeStringField("Subaccountsid", tItovPttSubaccount.getSubaccountsid());
			responseBodyJson.writeStringField("Subtoken", tItovPttSubaccount.getSubtoken());
			responseBodyJson.writeStringField("Voipaccount", tItovPttSubaccount.getVoipaccount());
			responseBodyJson.writeStringField("Voippwd", tItovPttSubaccount.getVoippwd());
			String server_url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/zdc";
			logger.info("~~~~~~~~~~~~~~~~server_url~~~~~~~~~~~~~~~~~~~~" + server_url);
			Integer platform = requestParam.getRequestDataHeader().getClientPlatform();// ios---10,android----11平台标示
			// 0.根据平台标示，获取最新版本号和url路径
			CommonBean commonBean =new CommonBean();
			commonBean.setPlatform(platform);
			commonBean.setVer_type(Integer.valueOf(ter_type));
			CommonBean commonBeanVersion = userService.getVersion_ter(commonBean);
			
			if(commonBeanVersion!=null)
			{
			responseBodyJson.writeStringField("version", commonBeanVersion.getVersion());
			responseBodyJson.writeStringField("version_url", server_url + commonBeanVersion.getVersionurl());
			responseBodyJson.writeNumberField("ver_important", commonBeanVersion.getVer_important());
			
			}
			TItovPttSubaccount tItovPttSubaccount1=pttSubaccountService.selectForTEL(terminal_imei);
			if(tItovPttSubaccount1!=null)
			{
				responseBodyJson.writeStringField("Account_name",tItovPttSubaccount1.getAccount_name());
			
			}
			UsAccount usAccount1=new UsAccount();
			usAccount1.setAccount_name(terminal_imei);
			usAccount1=	userService.getUsUserInfo(usAccount1);
			if(usAccount1!=null)
			{
				responseBodyJson.writeStringField("pic_name",usAccount1.getPic_name());
				responseBodyJson.writeStringField("nickname",usAccount1.getNickname());
			}
		} else {
			responseBodyJson.writeStringField("Subaccountsid", ",");
		}
	}
}
