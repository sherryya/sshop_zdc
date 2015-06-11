package com.tmount.business.voice.controller;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.launch.util.LaunchNotify;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 百度音乐下发接口
 * 
 * @author
 * 
 */
@Controller
public class MusicVoiceMainController extends ControllerBase {
	Logger logger = Logger.getLogger(MusicVoiceMainController.class);
	@RequestMapping(value = "musicVoiceMainController.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String keywords = new String(ParamData.getString(requestParam.getBodyNode(), "keywords"));// 歌手/歌曲
		String newName = java.net.URLEncoder.encode(keywords,"utf-8");
		String main_url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.search.catalogSug&format=json&callback=&query=" + newName + "&_=1413017198449";
		String getDetailBysongIds = "http://ting.baidu.com/data/music/links?songIds=";
		String main_json = LaunchNotify.checkUrl(main_url);
		ObjectMapper objectmapper = new ObjectMapper();
		JsonNode dbcursorJson = objectmapper.readTree(main_json);
		Iterator it_data = dbcursorJson.get("song").iterator();
		responseBodyJson.writeArrayFieldStart("Data");
		while (it_data.hasNext()) {
			JsonNode json_sub = (JsonNode) it_data.next();
			String songid = json_sub.get("songid").asText();
			String songname = json_sub.get("songname").asText();
			String artistname = json_sub.get("artistname").asText();
			getDetailBysongIds = "http://ting.baidu.com/data/music/links?songIds=" + songid;
			String getDetailBysongIds_json = LaunchNotify.checkUrl(getDetailBysongIds);
			ObjectMapper objectmapper1 = new ObjectMapper();
			JsonNode dbcursorJson1 = objectmapper1.readTree(getDetailBysongIds_json);
			String dd = dbcursorJson1.get("data").toString();
			String cc = dd.split("\\[")[1].split("\\]")[0];
			cc = "{\"song\":[" + cc + "]}";
			ObjectMapper objectmapper2 = new ObjectMapper();
			JsonNode dbcursorJson2 = objectmapper2.readTree(cc);
			Iterator it_data2 = dbcursorJson2.get("song").iterator();
			while (it_data2.hasNext()) {
				JsonNode json_sub2 = (JsonNode) it_data2.next();
				String songLink = json_sub2.get("songLink").asText();
				String songPicSmall=json_sub2.get("songPicSmall").asText();
			    long l=	songLink.split("\\?")[1].toString().length();//xcode=45c055789324270f2398456caf8d7b8e64153bea1b294dc2
				if(l>54)
				{
					continue;
				}
				responseBodyJson.writeStartObject();
				String albumName = json_sub2.get("albumName").asText();
				String lrcLink = json_sub2.get("lrcLink").asText();
				String time = json_sub2.get("time").asText();//秒
				String size = json_sub2.get("size").asText();//kb
				responseBodyJson.writeStringField("songLink", songLink);
				responseBodyJson.writeStringField("albumName", albumName);
				responseBodyJson.writeStringField("lrcLink", lrcLink);
				responseBodyJson.writeStringField("time", time);
				responseBodyJson.writeStringField("size", size);
				responseBodyJson.writeStringField("songid", songid);
				responseBodyJson.writeStringField("songname", songname);
				responseBodyJson.writeStringField("artistname", artistname);
				String [] arr2=songPicSmall.trim().split("http:");
				if(arr2.length==2)
				{
					responseBodyJson.writeStringField("songPicSmall", songPicSmall);
				}
				else if(arr2.length==3)
				{
					String song=arr2[2].split(".jpg")[0].toString();
					song="http:"+song+".jpg";
					responseBodyJson.writeStringField("songPicSmall", song);
				}
				responseBodyJson.writeEndObject();
			}
		}
		responseBodyJson.writeEndArray();
	}
}
