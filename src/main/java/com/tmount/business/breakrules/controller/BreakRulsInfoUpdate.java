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
import com.tmount.business.breakrules.service.BreakRulesService;
import com.tmount.business.zdc.controller.GetNewsRollController;
import com.tmount.business.zdc.service.NewTotalService;
import com.tmount.db.breakrules.dto.TZdcBreakrules;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;
import com.tmount.db.zdc.dto.NewDetailSub;
import com.tmount.db.zdc.dto.NewsRollSub;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 违章信息处理
 * 
 * @authoe 何鑫
 * 
 */
@Controller
public class BreakRulsInfoUpdate extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(BreakRulsInfoUpdate.class.getName());
	@Autowired
	private BreakRulesService breakRulesService;

	@RequestMapping(value = "BreakRulesUpdate.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		String ID = ParamData.getString(requestParam.getBodyNode(), "id");
		breakRulesService.updatebreakerInfo(ID.split(","));
	}
}
