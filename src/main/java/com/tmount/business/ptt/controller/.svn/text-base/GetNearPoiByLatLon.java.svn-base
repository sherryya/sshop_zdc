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
public class GetNearPoiByLatLon extends ControllerBase {
	/*
	 * @Autowired private DriverService driverService;
	 */
	static Logger logger = Logger.getLogger(startService.class.getName());
	@RequestMapping(value = "getNearPoiByLatLon.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,	JsonGenerationException, IOException {
		logger.info("test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		String strlatlon=new String(ParamData.getString(requestParam.getBodyNode(), "strlatlon"));//经纬度
		strlatlon=URLEncoder.encode(strlatlon, "GBK");
		Double lat=ParamData.getDouble(requestParam.getBodyNode(), "lat");//纬度
		logger.info("lat~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+lat);
		Double lon=ParamData.getDouble(requestParam.getBodyNode(), "lon");//经度
		String city = new String(ParamData.getString(requestParam.getBodyNode(), "city"));//城市名称
		city=URLEncoder.encode(city, "GBK");
		Integer width=600;//new String(ParamData.getString(requestParam.getBodyNode(), "width"));//地图宽度 默认600
		Integer height=400;//new String(ParamData.getString(requestParam.getBodyNode(), "height"));//地图宽度 默认400
		String poitype=new String(ParamData.getString(requestParam.getBodyNode(), "poitype"));//关键字类型
		poitype=URLEncoder.encode(poitype, "GBK");
		String keytype=new String(ParamData.getString(requestParam.getBodyNode(), "poitype"));//查询关键字
		keytype=URLEncoder.encode(keytype, "GBK");
		String searchtype=new String(ParamData.getString(requestParam.getBodyNode(), "searchtype"));//搜索类型
		searchtype=URLEncoder.encode(searchtype, "GBK");
	    Integer range=  ParamData.getInt(requestParam.getBodyNode(), "range");//查询范围
	    Integer nlimit=  ParamData.getInt(requestParam.getBodyNode(), "nlimit");//输出结果带有高亮的name字段的最长限制 默认 100
	    Integer pn=  ParamData.getInt(requestParam.getBodyNode(), "pn");//页码 默认 1
	    Integer rn=  ParamData.getInt(requestParam.getBodyNode(), "rn");//每页结果数 10  最大 20
	    Integer customer=  2;
	   //String multiType =new String(ParamData.getString(requestParam.getBodyNode(), "multiType"));//多类型查询  一般不会用到
        StringBuffer sb=new StringBuffer();
		String GetPoiNameByKeyword_Url = "http://mapx.mapbar.com/dynamic/search/getNearPoiByLatLon.jsp?";
		sb.append("strlatlon").append("=").append(strlatlon).append("&");
		sb.append("lat").append("=").append(lat).append("&");
		sb.append("lon").append("=").append(lon).append("&");
		sb.append("city").append("=").append(city).append("&");
		sb.append("width").append("=").append(width).append("&");
		sb.append("height").append("=").append(height).append("&");
		sb.append("poitype").append("=").append(poitype).append("&");
		sb.append("keytype").append("=").append(keytype).append("&");
		sb.append("searchtype").append("=").append(searchtype).append("&");
		sb.append("range").append("=").append(range).append("&");
		sb.append("nlimit").append("=").append(nlimit).append("&");
		sb.append("pn").append("=").append(pn).append("&");
		sb.append("rn").append("=").append(rn).append("&");
		//sb.append("encode").append("=").append("UTF-8").append("&");
		sb.append("customer").append("=").append(customer);
		String url1 = GetPoiNameByKeyword_Url + sb.toString();
		//String r="http://mapx.mapbar.com/dynamic/search/getNearPoiByLatLon.jsp?strlatlon=116.38749%2C39.90515&lat=39.90515&lon=116.38749&city=%B1%B1%BE%A9&width=600&height=400&poitype=%B4%F3%D1%A7&keytype=%B4%F3%D1%A7&searchtype=&range=1000&nlimit=100&pn=1&rn=10&customer=2";
		//String	url1="http://mapx.mapbar.com/dynamic/search/getNearPoiByLatLon.jsp?&city=%E5%8C%97%E4%BA%AC%E5%B8%82&strlatlon=116.38673%2C39.93552&keytype=%E5%A4%A7%E5%8E%A6&range=100&width=500&height=430&pn=1&encode=UTF-8&customer=2";
		String retStr = "";
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+url1);
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
