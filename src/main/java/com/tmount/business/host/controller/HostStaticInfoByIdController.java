package com.tmount.business.host.controller;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.host.service.TItovHostStaticService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.db.host.dto.TZdcHostStatic;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 根据id查询主播统计信息对象
 * 
 * @author
 * 
 */
@Controller
public class HostStaticInfoByIdController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostStaticService zdcHostStaticService;

	@RequestMapping(value = "hostStaticById.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		long id = ParamData.getLong(requestParam.getBodyNode(), "id"); //
		List<TZdcHostStatic> hostS = zdcHostStaticService.selectHostStaticById(id);
		logger.info("gethostinfoById.get");
		Type listType = new TypeToken<ArrayList<TZdcHostStatic>>(){}.getType();
	    Gson gson=new Gson();
	    String json=gson.toJson(hostS, listType);
		responseBodyJson.writeStringField("hostStatic",json.replace("\"", "'"));
		
	}

}
