package com.tmount.business.voice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 语音识别
 * 
 * @author
 * 
 */

@Controller
public class VoiceMainController extends ControllerBase {
	Logger logger = Logger.getLogger(VoiceMainController.class);
	
	@RequestMapping(value = "voicemains.get")

	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String operation = new String(ParamData.getString(requestParam.getBodyNode(), "operation"));// 语音动作类型
		String text = new String(ParamData.getString(requestParam.getBodyNode(), "text"));// 事件文本
		String singer = new String(ParamData.getString(requestParam.getBodyNode(), "singer"));// 歌手	名
	
		
		//text = new String("大约在冬季".getBytes(),"gb2312");
		//text = URLEncoder.encode(text);
		String interfaeceurl = "";
		String first_name ="";
		String real_url="";
		text = URLEncoder.encode(text, "UTF-8");
		singer = URLEncoder.encode(singer, "UTF-8");
		try {
			if ("PLAY".equals(operation)) {// 听音乐
				// 调用听音乐接口
				String res="";
				if (StringUtils.isEmpty(text) && StringUtils.isEmpty(singer)) {
					res = "any$$";
				} else {
					if (StringUtils.isEmpty(text)&& StringUtils.isNotEmpty(singer)) {
						res = singer + "$$";
					} else if (StringUtils.isEmpty(singer)&& StringUtils.isNotEmpty(text)) {
						res = text + "$$";
					} else {
						res = text + "$$" + singer + "$$";
					}
				}

				interfaeceurl="http://box.zhangmen.baidu.com/x?op=12&count=1&title="+res;

				BufferedReader reader = null;
				String result = null;
				StringBuffer sbf = new StringBuffer();
				String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
				URL url = new URL(interfaeceurl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setReadTimeout(30000);
				connection.setConnectTimeout(30000);
				connection.setRequestProperty("User-agent", userAgent);
				connection.connect();
				InputStream is = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
				}
				reader.close();
				result = sbf.toString();
				
				logger.info("~~~~~~~~result~~~~~~~~~~~"+result);
				// 解析接口返回xml
				Document document = DocumentHelper.parseText(result);
				Element el_req = document.getRootElement();
				if (el_req.element("count") != null) {
					String count = el_req.element("count").getText();
					Iterator it = el_req.elementIterator("url");
					if (it.hasNext()) {
						Element el_req_elment = (Element) it.next();
						String first_url = el_req_elment.element("encode").getText();
						first_name = el_req_elment.element("decode").getText();
						real_url = first_url.substring(0, first_url.lastIndexOf("/") + 1);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseBodyJson.writeStringField("PLAY", real_url	+ first_name);
	}
}
