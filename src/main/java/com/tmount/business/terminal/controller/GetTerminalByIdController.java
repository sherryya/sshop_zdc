package com.tmount.business.terminal.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.business.terminal.service.TermianlService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.db.terminal.dto.TZdcTerminal;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 鏍规嵁涓绘挱id娲诲緱涓绘挱淇℃伅
 * 
 * @author
 * 
 */
@Controller
public class GetTerminalByIdController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TermianlService terminalService;

	@RequestMapping(value = "getTerminalInfoById.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		int id = ParamData.getInt(requestParam.getBodyNode(), "id"); 
		logger.info("getTerminalInfoById.get");
		//get host info
		List<TZdcTerminal> terminalList = terminalService.selectTerminalById(id);
		Type listType = new TypeToken<ArrayList<TZdcTerminal>>(){}.getType();
	    Gson gson=new Gson();
	    String json=gson.toJson(terminalList, listType);
		responseBodyJson.writeStringField("terminalList",json.replace("\"", "'"));
	}

}
