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
import com.tmount.business.ptt.service.PttSubaccountLogService;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.db.ptt.dto.TItovPttSubaccountLog;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class updSubAccountState extends ControllerBase {
	    Logger logger = Logger.getLogger(startService.class.getName());
		@Autowired
		private PttSubaccountService pttSubaccountService;
		
		@Autowired
		private PttSubaccountLogService pttSubaccountLogService;
		
		@RequestMapping(value = "subAccountState.upd")
		@Override
		public void doRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			sendData(request, response);
		}
		@Override
		protected void doService(RequestParam requestParam,
				JsonGenerator responseBodyJson) throws ShopException,
				JsonGenerationException, IOException {
			String voipaccount = new String(ParamData.getString(	requestParam.getBodyNode(), "voipaccount"));//VOIP 账号
			String agentstate = new String(ParamData.getString(	requestParam.getBodyNode(), "agentstate"));//VOIP 账号
		    TItovPttSubaccount  tItovPttSubaccount =new TItovPttSubaccount();
		    tItovPttSubaccount.setVoipaccount(voipaccount);
		    tItovPttSubaccount.setAgentstate(agentstate);
			//pttSubaccountService.updateByPrimaryKeySelective(tItovPttSubaccount);
		    //responseBodyJson.writeStringField("result", "");
		    
		    
		    
			TItovPttSubaccountLog tItovPttSubaccountLog =new TItovPttSubaccountLog();
			tItovPttSubaccountLog.setBilldata("22222222333");
			tItovPttSubaccountLog.setByetype("2");
			tItovPttSubaccountLog.setEndtime("22222222333");
			tItovPttSubaccountLog.setStarttime("22222222333");
			tItovPttSubaccountLog.setRecordurl("22222222333");
			tItovPttSubaccountLog.setSubtype("11");
			tItovPttSubaccountLog.setOrderid("222222");
			
			System.out.println(tItovPttSubaccountLog);
			
			pttSubaccountLogService.updateByPrimaryKeySelective(tItovPttSubaccountLog);
			responseBodyJson.writeStringField("result", "");
		}
}
