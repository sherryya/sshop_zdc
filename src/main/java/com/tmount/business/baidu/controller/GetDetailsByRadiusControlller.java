package com.tmount.business.baidu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.InsertInfoByImei.controller.RelateCarAndTelController;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetDetailsByRadiusControlller extends ControllerBase {
	static Logger logger = Logger.getLogger(RelateCarAndTelController.class);


	@RequestMapping(value = "getDetailsByRadius.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		String query = ParamData.getString(
				requestParam.getBodyNode(), "query");  //查询的关键字
		String mpoint =  ParamData.getString(
				requestParam.getBodyNode(), "mpoint");
		String radius =  ParamData.getString(
				requestParam.getBodyNode(), "radius");
		String page_num =ParamData.getString(
				requestParam.getBodyNode(), "page_num");   //第几页 0表示第一页
		//String url = "http://api.map.baidu.com/place/v2/detail?uid="+uid+"&ak=E4805d16520de693a3fe707cdc962045&output=json&scope=2";
	    String url="http://api.map.baidu.com/place/v2/search?output=json&scope=2&ak=9qRu0LqnmGItj5DLk5zNcKPH&query="+java.net.URLEncoder.encode(query,"utf-8")+"&location="+mpoint+"&radius="+radius;
		//page_num 0 表示第一条
	    if(StringUtils.isNotBlank(page_num));
		{
			url = url + "&page_num="+Integer.parseInt(page_num);
		}
	    StringBuffer str = new StringBuffer();
		//拼接参数后的地址
		url = url+str.toString();
		String retStr = "";
		try {
			java.net.URL getUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("contentType", "UTF-8");
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				retStr = retStr + lines;
			}
			reader.close();
			retStr=URLDecoder.decode(retStr, "UTF-8");
			System.out.println("retStr--->>"+retStr);
			responseBodyJson.writeFieldName("list");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(responseBodyJson, retStr.replaceAll("\"","'"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
