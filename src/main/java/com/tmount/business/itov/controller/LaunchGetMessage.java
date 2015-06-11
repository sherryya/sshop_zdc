package com.tmount.business.itov.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LaunchGetMessage {
	String json = "";

	@RequestMapping(value = "launchmessage.get")
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int contentLength = request.getContentLength();
		if (contentLength > 0) {
			try {
				byte[] bufIn = new byte[contentLength];
				request.getInputStream().read(bufIn);
				// 得到GBK编码格式的字符串:
				String gbkStr = new String(bufIn, "UTF-8");

				System.out.println("request utf8Str:" + new String(bufIn));
				System.out.println("request gbkStr:" + gbkStr);
				byte[] utf8Str = gbkStr.getBytes("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control", "no-cache, must-revalidate");
				response.setContentType("application/octet-stream;charset=UTF-8");
				response.setContentLength(utf8Str.length);
				response.getOutputStream().write(utf8Str);
				response.flushBuffer();
			}catch(Exception e){
				e.printStackTrace();	
			}
		}	

	}

}
