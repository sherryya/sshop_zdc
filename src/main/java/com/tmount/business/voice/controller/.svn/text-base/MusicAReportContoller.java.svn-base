package com.tmount.business.voice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.voice.bean.MusicBeanT;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 天气预报接口，参数，经纬度
 * 
 * @author
 * 
 */
@Controller
public class MusicAReportContoller extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(MusicAReportContoller.class.getName());
	

	@RequestMapping(value = "musicAll.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {

		HttpURLConnection connection = null;
		BufferedReader reader  = null;
		
		
		String keyWords = new String(ParamData.getString(
				requestParam.getBodyNode(), "keyWords"));// 经纬度
		try{
		    //String musicName = "光辉岁月";
			String newName = java.net.URLEncoder.encode(keyWords,"utf-8");
			String urlstr = "http://www.ip62.com/qqmusic/index.php?gequ="+newName;
			logger.info("~~~~~~~~~~~~~~~~~~urlstr~~~~~~~~~~~~~~~~~~~~~~~~~"+urlstr);
			String retStr = "";
			java.net.URL getUrl = new URL(urlstr);
			connection = (HttpURLConnection) getUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.connect();
			reader= new BufferedReader(
					new InputStreamReader(connection.getInputStream(),
							"utf-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				retStr = retStr + lines;
			}
			reader.close();
			logger.info("~~~~~~~~~~~~~~~~~~retStr~~~~~~~~~~~~~~~~~~~~~~~~~"+retStr);
			//4.解析json
			if(StringUtils.isNotEmpty(retStr)){
				 //第一个table的位置
				 int num = retStr.indexOf("<table");
			     int endnum = retStr.indexOf("</table>");
			     //最后一个table的索引位置
			     int lastEnd = retStr.indexOf("</table>", endnum+1);
			     //截取第二个table里的内容
			     String newStr = retStr.substring(retStr.indexOf("<table", num+1),lastEnd+8);
			     //匹配所有td里的内容
			     Pattern p1 = Pattern.compile("<td[^>]*>(.*?)</td>");
			     Matcher m = p1.matcher(newStr);
			     List<String> list = new ArrayList<String>();
			     int temp=0;
				 while(m.find())
				 {
					 //前5条歌曲等词汇不存入list
					 if(temp<5)
					 {
						 temp++;
					 }else
					 {
						//将音乐的url提取出来
						 Pattern p = Pattern.compile("<a[^>]*>(.*?)</a>");
						 Matcher mat = p.matcher(m.group(1));
						 if(mat.find())
						 {
							 String stt= mat.group(1);
							 list.add(stt);
						 }else
						 {
							 list.add(m.group(1));
						 }
					 }
					 //取前10条
					 if(list.size()>49)
					     break;
				 }
				 int size = list.size();  //list的大小
				 List<MusicBeanT> msBeanList = new ArrayList<MusicBeanT>();  //新的list
				 MusicBeanT ms = null;
				 for(int i=0;i<size;i++)
				 {
					 String value = list.get(i);
					 if((i+1)%5==1)
					 {
						 ms = new MusicBeanT();
						 ms.setMusicId(value);
						 
					 }
					 if((i+1)%5==2)
					 {
						 ms.setMusicName(value);
					 }else if((i+1)%5==3)
					 {
						 ms.setSinger(value);
					 }else if((i+1)%5==4)
					 {
						 ms.setAlbum(value);
					 }else if((i+1)%5==0)
					 {
						 ms.setMusicUrl(value);
					 }
					 if(i>3&&(i+1)%5==0)
					 {
						 msBeanList.add(ms);
					 }
				 }
				    Iterator<MusicBeanT> it = msBeanList.iterator();
				    responseBodyJson.writeArrayFieldStart("item_list");  //歌曲列表
					while (it.hasNext()) {
						MusicBeanT mbt = it.next();
						responseBodyJson.writeStartObject();
						responseBodyJson.writeStringField("musicId",mbt.getMusicId());
						responseBodyJson.writeStringField("musicName",mbt.getMusicName());
						responseBodyJson.writeStringField("singer",mbt.getSinger());
						responseBodyJson.writeStringField("album",mbt.getAlbum());
						responseBodyJson.writeStringField("musicUrl",mbt.getMusicUrl());
						responseBodyJson.writeEndObject();
					}
					responseBodyJson.writeEndArray();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		}

}

