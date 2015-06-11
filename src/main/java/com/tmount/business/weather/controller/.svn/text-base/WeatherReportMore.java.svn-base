package com.tmount.business.weather.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 天气预报接口，参数，经纬度
 * 
 * @author
 * 
 */
@Controller
public class WeatherReportMore extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(WeatherReportMore.class.getName());

	@RequestMapping(value = "moreWeatherReport.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		String address = null;
		try {
			address = MapUtil.getBaiduInverseGeographic1(new String(ParamData
					.getString(requestParam.getBodyNode(), "pointMessage")));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		if (StringUtils.isNotEmpty(address)) {
			String cityName=address.substring(address.indexOf("省")+1,address.indexOf("市"));
			String urlstr = "http://api.map.baidu.com/telematics/v3/weather?location="
					+  java.net.URLEncoder.encode(cityName, "utf-8")
					+ "&output=json&ak=B122767f9cf32ad2c5a17d97835d053e";
			logger.info("~~~~~~~~~~~~~~~~~~urlstr~~~~~~~~~~~~~~~~~~~~~~~~~"+ urlstr);
			String retStr = "";
			java.net.URL getUrl = new URL(urlstr);
			connection = (HttpURLConnection) getUrl.openConnection();
			// connection.setConnectTimeout(5 * 1000);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("contentType", "UTF-8");
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				retStr = retStr + lines;
			}
			reader.close();
			logger.info("~~~~~~~~~~~~~~~~~~retStr~~~~~~~~~~~~~~~~~~~~~~~~~"
					+ retStr);
			// 4.解析json
			if (StringUtils.isNotEmpty(retStr)) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonNodeAll = mapper.readTree(retStr);
				// System.out.println("jsonNodeAll----->>"+jsonNodeAll);
				// JsonNode jsonNode = jsonNodeAll.get("results");
				// 将全部json返回
				responseBodyJson.writeFieldName("weatherJson");
				mapper.writeValue(responseBodyJson, jsonNodeAll);
			}
		}
	}
}
