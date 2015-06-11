package com.tmount.business.apnuser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.apnuser.service.ApnUserService;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *车品牌数据同步接口
 * 
 * @author 
 * 
 */
@Controller
public class ApnUserDeleteByUserName extends ControllerBase {
	@Autowired
	private ApnUserService apnUserService;
	@RequestMapping(value = "apnUserDeleteByUserName.del")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String username = ParamData.getString(requestParam.getBodyNode(),"username");//
		
		try{
			int t = apnUserService.deleteByUserName(username);
		    responseBodyJson.writeNumberField("result", t);  //1表示删除成功
		}catch(Exception e){
			responseBodyJson.writeNumberField("result", 0);  //0表示删除失败
		}
		
	}
}
