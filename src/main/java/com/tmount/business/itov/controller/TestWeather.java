package com.tmount.business.itov.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBasePlatform;
import com.tmount.util.RequestParam;
import com.tmount.util.ResponseData;

public class TestWeather {
	public static void main(String[] args) {
		//String urlstr = "http://m.weather.com.cn/data/101050101.html";
		String urlstr = "http://www.weather.com.cn/data/sk/101050101.html";
		//String urlstr ="http://www.weather.com.cn/data/cityinfo/101050101.html";
		String retStr = "";
		try {
			java.net.URL getUrl = new URL(urlstr);
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
			//retStr=URLDecoder.decode(retStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(retStr);
	}

}
