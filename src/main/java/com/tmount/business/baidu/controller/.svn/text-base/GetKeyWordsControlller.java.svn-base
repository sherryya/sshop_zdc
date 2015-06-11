package com.tmount.business.baidu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
public class GetKeyWordsControlller extends ControllerBase {
	static Logger logger = Logger.getLogger(RelateCarAndTelController.class);


	@RequestMapping(value = "baiduAPISearch.get")
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
		String tag = ParamData.getString(requestParam.getBodyNode(), "tag");
		String region = ParamData.getString(requestParam.getBodyNode(), "region");
		String scope =ParamData.getString(requestParam.getBodyNode(), "scope");  //取值为1 或空，则返回基本信息；取值为2 不传默认为1
		String filter = ParamData.getString(
				requestParam.getBodyNode(), "filter");  //过滤
		String page_size =ParamData.getString(
				requestParam.getBodyNode(), "page_size");  //每页多少条
		String page_num =ParamData.getString(
				requestParam.getBodyNode(), "page_num");   //第几页 0表示第一页
		//String region = "哈尔滨";
		region = java.net.URLEncoder.encode(region,"utf-8");
		//baidu api address 
		String url = "http://api.map.baidu.com/place/v2/search?ak=9qRu0LqnmGItj5DLk5zNcKPH&output=json&region="+region;
	    StringBuffer str = new StringBuffer();
	    //判断关键字是否为空
		if(StringUtils.isNotBlank(query))
	    {
			query = java.net.URLEncoder.encode(query,"utf-8");
			str.append("&query=");
			str.append(query);
	    }else
	    {
	    	query = "天安门";
	    	query = java.net.URLEncoder.encode(query,"utf-8");
	    	str.append("&query=");
			str.append(query);
	    }
		if(StringUtils.isNotBlank(filter))
		{
			filter = java.net.URLEncoder.encode(filter,"utf-8");;
			str.append("&filter=");
			str.append(filter);
		}
		if(StringUtils.isNotBlank(scope))
		{
			str.append("&scope=");
			str.append(scope);
		}else
		{
			str.append("&scope=2");
		}
		if(StringUtils.isNotBlank(tag))
		{
			str.append("&tag");
			str.append(tag);
		}
		if(StringUtils.isNotBlank(page_size))
		{
			str.append("&page_size=");
			str.append(Integer.parseInt(page_size));
		}else
		{
			str.append("&page_size=10");
		}
		if(StringUtils.isNotBlank(page_num))
		{
			int pageNum= Integer.parseInt(page_num)-1;
			str.append("&page_num=");
			str.append(pageNum);
		}else
		{
			str.append("&page_num=0");
		}
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
