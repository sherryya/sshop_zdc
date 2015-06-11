package com.tmount.business.breakrules.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.breakrules.service.ZdcBreakrulesService;
import com.tmount.db.breakrules.dto.TZdcBreakrules;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 得到违章驾驶记录
 * @author dell
 *
 */
@Controller
public class GetBreakrulesListController extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(GetBreakrulesListController.class.getName());
	@Autowired
	private ZdcBreakrulesService zdcBreakrulesService;
	@RequestMapping(value = "getBreakrulesList.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		Integer account_id = ParamData.getInt(requestParam.getBodyNode(), "account_id");
		List<TZdcBreakrules> tZdcBreakrules = zdcBreakrulesService.getBreakrulesList(account_id);
		if (tZdcBreakrules != null) {
			System.out.println("******违章驾驶记录**************"+tZdcBreakrules);
			responseBodyJson.writeNumberField("Size", tZdcBreakrules.size());
			
			
			List<TZdcBreakrules> newsRollSub1=new ArrayList<TZdcBreakrules>();
			int i=1;
			for(TZdcBreakrules rule:tZdcBreakrules)
			{
				rule.setSn(i);
				newsRollSub1.add(rule);
				i=i+1;
			}
			
			
			responseBodyJson.writeFieldName("Object");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(responseBodyJson, tZdcBreakrules);
		} else {
			responseBodyJson.writeNumberField("Size", 0);
		}
	}
}
