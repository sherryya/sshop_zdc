package com.tmount.business.terminal.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.feedback.service.FeedbackService;
import com.tmount.business.terminal.service.TermianlService;
import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.terminal.dto.TZdcTerminal;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到反馈信息
 * 
 * @author qgy
 * 
 */
@Controller
public class GetItovTerminalController extends ControllerBase{
	@Autowired
	private TermianlService terminalService;
    @RequestMapping(value = "terminal.query.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		try{
		    List<TZdcTerminal> arr = new ArrayList<TZdcTerminal>();
		    TZdcTerminal tzdc_terminal = new TZdcTerminal();

		    String terminal_name = ParamData.getString(
			    requestParam.getBodyNode(), "terminal_name");// 用户名
		    String terminal_imei = ParamData.getString(requestParam.getBodyNode(),
			    "terminal_imei");// 反馈isdeal
		    String terminal_status = ParamData.getString(requestParam.getBodyNode(),
				    "terminal_status");
		    String terminal_device_type = ParamData.getString(requestParam.getBodyNode(),
				    "terminal_device_type");
		    
		    Integer pageSize = new Integer(ParamData.getInt(
			    requestParam.getBodyNode(), "page_size", -1));// 每页多少条
		    Integer startLimit = -1;
		    Integer pageNo = new Integer(ParamData.getInt(
			    requestParam.getBodyNode(), "page_num", -1));// 第几页
		    if (pageNo != -1) {
			startLimit = (pageNo - 1) * pageSize;
			tzdc_terminal.setStartLimit(startLimit);
		    }
		    if (pageSize != -1) {
		    	tzdc_terminal.setPageSize(pageSize);
		    }
		    tzdc_terminal.setTerminal_name(terminal_name);
		    tzdc_terminal.setTerminal_imei(terminal_imei);
		    tzdc_terminal.setTerminal_status_query(terminal_status);
		    tzdc_terminal.setTerminal_device_type(terminal_device_type);
		    
		    Integer recordCount = terminalService.selectSizeByWhere(tzdc_terminal);
		    Integer pageCount = 0;
		    if (pageSize != -1) {
			pageCount = (recordCount + (pageSize - 1)) / pageSize;
		    }
		    arr = terminalService.selectByWhere(tzdc_terminal);
		    Type listType = new TypeToken<ArrayList<CarModel>>() {
		    }.getType();
		    Gson gson = new Gson();
		    String json = gson.toJson(arr, listType);
	
		    responseBodyJson.writeNumberField("pageCount", pageCount);
		    responseBodyJson.writeNumberField("totalCount", recordCount);
		    responseBodyJson
			    .writeStringField("result", json.replace("\"", "'"));
		}catch (Exception e) {
		    // TODO: handle exception
			System.out.println("打印我的错误-------->");
			e.printStackTrace();
			System.out.println("打印完毕----------->");
		    throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,
			    new Object[] { null });
		}
	}
}
