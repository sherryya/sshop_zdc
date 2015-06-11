package com.tmount.business.ptt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetPoiNameByKeyword extends ControllerBase {
	/*
	 * @Autowired private DriverService driverService;
	 */
	static Logger logger = Logger.getLogger(startService.class.getName());
	@RequestMapping(value = "poiNameByKeyword.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String city = new String(ParamData.getString(	requestParam.getBodyNode(), "city"));//
		city=URLEncoder.encode(city, "GBK");
		String keyword = new String(ParamData.getString(	requestParam.getBodyNode(), "keyword"));//
		keyword=URLEncoder.encode(keyword, "GBK");
		String route = new String(ParamData.getString(requestParam.getBodyNode(), "route"));//
		String GetPoiNameByKeyword_Url = "http://mapx.mapbar.com/dynamic/search/getPoiNameByKeyword.jsp?";
		String parms="city="+city+"&keyword="+keyword+"&customer=2&searchType="+route;
		String url1 = GetPoiNameByKeyword_Url + parms;
		String retStr = "";
		try {
			java.net.URL getUrl = new URL(url1);
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
			responseBodyJson.writeStringField("result:", retStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
