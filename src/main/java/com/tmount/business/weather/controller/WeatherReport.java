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
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.user.controller.UserLogin;
import com.tmount.business.weather.service.WeatherService;
import com.tmount.db.weather.dto.TItovWeathercitycode;
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
public class WeatherReport extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(WeatherReport.class.getName());
	
	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "weatherReport.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {

		String param = "detail=1&zoom=11&road=1&customer=2&latlon=";
		String pointMessage = new String(ParamData.getString(
				requestParam.getBodyNode(), "pointMessage"));// 经纬度
		// 1.根据经纬度来得到对应的城市名称
		String xml = MapUtil.readContentFromGet(param + pointMessage);
		HttpURLConnection connection = null;
		HttpURLConnection connectionSecond = null;
		BufferedReader reader  = null;
		BufferedReader readerSecond = null;
		if (StringUtils.isNotEmpty(xml)) {
			try {
				Document document = DocumentHelper.parseText(xml);
				Element el_req = document.getRootElement();
				String retMsg = "";
				// 市
				if (el_req.element("city") != null) {
					retMsg += el_req.element("city").getText();
				}
				logger.info("~~~~~~~~~~~~~~~~~~retMsg~~~~~~~~~~~~~~~~~~~~~~~~~"+retMsg);
				//responseBodyJson.writeStringField("retMsg",retMsg.substring(0,retMsg.length()-1));
				// 2.根据城市名称，到t_itov_weathercity表中找到对应的citycode
				TItovWeathercitycode tItovWeathercitycode = new TItovWeathercitycode();
				if (retMsg.trim().length() > 1) {
					tItovWeathercitycode.setCityName(retMsg.substring(0, retMsg.length() - 1));
				} else {
					tItovWeathercitycode.setCityName("无名");
				}
				tItovWeathercitycode = weatherService.getWeatherCityCodeByCityName(tItovWeathercitycode);
				String cityCode = tItovWeathercitycode.getCityCode();
				// 3.根据城市code，调用中央气象局接口,返回json："city":"哈尔滨","cityid":"101050101","temp":"25","WD":"南风","WS":"3级","SD":"14%","WSE":"3"
				String urlstr = "http://www.weather.com.cn/data/sk/" + cityCode+ ".html";
				logger.info("~~~~~~~~~~~~~~~~~~urlstr~~~~~~~~~~~~~~~~~~~~~~~~~"+urlstr);
				String retStr = "";
				java.net.URL getUrl = new URL(urlstr);
				connection = (HttpURLConnection) getUrl
						.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept-Charset", "UTF-8");
				connection.setRequestProperty("contentType", "UTF-8");
				connection.connect();
				reader= new BufferedReader(
						new InputStreamReader(connection.getInputStream(),
								"UTF-8"));
				String lines;
				while ((lines = reader.readLine()) != null) {
					retStr = retStr + lines;
				}
				reader.close();
				logger.info("~~~~~~~~~~~~~~~~~~retStr~~~~~~~~~~~~~~~~~~~~~~~~~"+retStr);
				//4.解析json
				if(StringUtils.isNotEmpty(retStr)){
					ObjectMapper mapper = new ObjectMapper();
					JsonNode jsonNodeAll = mapper.readTree(retStr);
					JsonNode jsonNode = jsonNodeAll.get("weatherinfo");
					responseBodyJson.writeStringField("city",jsonNode.get("city").textValue());// 城市、
					responseBodyJson.writeStringField("temp",jsonNode.get("temp").textValue());// 当前气温
					responseBodyJson.writeStringField("WD",jsonNode.get("WD").textValue());// 风向
					responseBodyJson.writeStringField("WS",jsonNode.get("WS").textValue());// 风力
					responseBodyJson.writeStringField("SD",jsonNode.get("SD").textValue());// 湿度
					
				}
				// 5.根据城市code，调用中央气象局接口,返回json："city":"哈尔滨","cityid":"101050101","temp1":"28℃","temp2":"13℃","weather":"晴"
				//{"city":"北京","cityid":"101010100","temp1":"28℃","temp2":"15℃","weather":"晴","img1":"d0.gif","img2":"n0.gif","ptime":"11:00"}}
				urlstr = "http://www.weather.com.cn/data/cityinfo/"+ cityCode+".html";
				logger.info("~~~~~~~~~~~~~~~~~~urlstr~~~~~~~~~~~~~~~~~~~~~~~~~"+urlstr);
				java.net.URL getUrlSecond = new URL(urlstr);
				connectionSecond= (HttpURLConnection) getUrlSecond
						.openConnection();
				connectionSecond.setDoOutput(true);
				connectionSecond.setRequestMethod("GET");
				connectionSecond.setRequestProperty("Accept-Charset", "UTF-8");
				connectionSecond.setRequestProperty("contentType", "UTF-8");
				connectionSecond.connect();
				readerSecond = new BufferedReader(
						new InputStreamReader(connectionSecond.getInputStream(),
								"UTF-8"));
				String linesSecode;
				retStr = "";
				while ((linesSecode = readerSecond.readLine()) != null) {
					retStr = retStr + linesSecode;
				}
				reader.close();
				logger.info("~~~~~~~~~~~~~~~~~~retStr~~~~~~~~~~~~~~~~~~~~~~~~~"+retStr);
				//6.解析json
				if(StringUtils.isNotEmpty(retStr)){
					ObjectMapper mapper = new ObjectMapper();
					JsonNode jsonNodeAll = mapper.readTree(retStr);
					JsonNode jsonNode = jsonNodeAll.get("weatherinfo");
					responseBodyJson.writeStringField("temp1",jsonNode.get("temp1").textValue());// 最高气温
					responseBodyJson.writeStringField("temp2",jsonNode.get("temp2").textValue());// 最低气温
					responseBodyJson.writeStringField("weather",jsonNode.get("weather").textValue());// 天气描述
					responseBodyJson.writeStringField("img1",jsonNode.get("img1").textValue());// 天气图片描述
					responseBodyJson.writeStringField("img2",jsonNode.get("img2").textValue());// 天气图片描述
					//responseBodyJson.writeStringField("imgurl","http://m.weather.com.cn/img/");// 天气图片路径
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
