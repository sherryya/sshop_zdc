package com.tmount.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.tmount.config.AppPropertiesConfig;
import com.tmount.exception.ShopException;
import com.tmount.system.dal.DataSourceContextHolder;

/**
 * 打包生成servlet的JSON格式数据
 * 
 * @author lugz
 * 
 */
public abstract class ControllerBaseByHttp {
	@Autowired
	private AppPropertiesConfig appPropertiesConfig;

	/**
	 * 
	 * @param node
	 * @return 响应的字符串。
	 */
	protected abstract String doService(HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException;

	protected abstract ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException;

	public abstract void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * 路由函数。
	 */
	private void routing(RequestParam requestParam) {
		// 等待实现。
	}

	public void sendData(HttpServletRequest request,
			HttpServletResponse response){
		String bodyStr= "";
		try{
			Long startTime = System.currentTimeMillis();
			// 清除数据源的设置。
			DataSourceContextHolder.clearDataSource();
			Logger logger = Logger.getLogger(ControllerBaseByHttp.class);
			bodyStr = doService(request);	
			byte[] utf8Str = bodyStr.getBytes("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setContentLength(bodyStr.getBytes("UTF-8").length);
			response.getOutputStream().write(utf8Str);
			response.flushBuffer();
		}catch(Exception e){
			e.printStackTrace();
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setContentType("application/octet-stream;charset=UTF-8");
			try {
				response.getOutputStream().write("系统报错".getBytes());
				response.flushBuffer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	protected String doService(RequestParam requestParam) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
