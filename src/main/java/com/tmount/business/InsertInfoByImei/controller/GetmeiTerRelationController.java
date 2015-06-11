package com.tmount.business.InsertInfoByImei.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.InsertInfoByImei.service.TZdcImeiterRelationService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.user.service.UserService;
import com.tmount.db.terminalAccount.dto.TZdcImeiterRelation;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 判断终端和车机 是否有绑定关系，如果没有就添加，如果有返回 终端ID
 * 
 * @author
 * 
 */
@Controller
public class GetmeiTerRelationController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TZdcImeiterRelationService tZdcImeiterRelationService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "GetImeiTerRelation.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String imei = ParamData.getString(requestParam.getBodyNode(), "imei");
		try {
				List<TZdcImeiterRelation> terminalList = tZdcImeiterRelationService.selectByIMEI(imei);
				if (null == terminalList || (null != terminalList && terminalList.size() <= 0)) {
					responseBodyJson.writeNumberField("return", 0);
				} else {
					responseBodyJson.writeNumberField("return", 1);
					responseBodyJson.writeStringField("ter", terminalList.get(0).getTerminal());
					UsAccount sAccount=new UsAccount();
					sAccount.setAccount_name(terminalList.get(0).getTerminal());
					sAccount=userService.getUsUserInfo(sAccount);
					if(sAccount!=null)
					{
						responseBodyJson.writeNumberField("account_id", sAccount.getAccount_id());
					}
				}
		} catch (Exception e) {
			responseBodyJson.writeNumberField("return", 0);
		}
	}
}
