package com.tmount.business.res.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tmount.bundle.ShopParamErrorBundle;
import com.tmount.business.res.service.ResService;
import com.tmount.exception.ShopBusiException;

/**
 * 资源上传
 * @author lugz
 *
 */
@Controller
public class ResUpload{
	@Autowired
	private ResService resService;
	
	private ServletContext servletContext;
	
	public void setServletContext(ServletContext servletContext){
		this.servletContext = servletContext;
	}
	
	/**
	 * 资源上传
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "res.upload")
    public void doRequest(@RequestParam("pic_file")CommonsMultipartFile picFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		if (picFile == null) {
			throw new ShopBusiException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{"pic_file"});
		}

		resService.saveResUpload(request, response, picFile, servletContext);
	}
}
