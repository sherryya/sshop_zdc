package com.tmount.business.res.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmount.business.res.service.ResService;
import com.tmount.config.AppPropertiesConfig;

/**
 * 图片资源请求
 * @author lugz
 *
 */
@Controller
public class Res{
	@Autowired
	private ResService resService;
	
	@Autowired
	AppPropertiesConfig appPropertiesConfig;

	@RequestMapping(value = "res")
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		resService.resRequest(request, response);
	}
}
