package com.tmount.business.voice.controller;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.launch.util.LaunchNotify;
import com.tmount.business.voice.service.TItovMusicpushService;
import com.tmount.db.voice.dto.TItovMusicpush;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 查询下发的音乐信息然后再全部删除
 * 
 * @author
 * 
 */
@Controller
public class queryPushMusicController extends ControllerBase {
	Logger logger = Logger.getLogger(queryPushMusicController.class);
	@Autowired
	private TItovMusicpushService tmusicService;
	@RequestMapping(value = "queryMusicController.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String terminal_imei = new String(ParamData.getString(requestParam.getBodyNode(), "terminal_imei"));//terminal_imei
		TItovMusicpush musicInfo = new TItovMusicpush();
		musicInfo.setTerminalImei(terminal_imei);
		List<TItovMusicpush> list = tmusicService.selectByterImei(terminal_imei); //查询推行的音乐信息
		ObjectMapper om = new ObjectMapper();
		responseBodyJson.writeFieldName("pushList");
		om.writeValue(responseBodyJson, list);
		tmusicService.deleteByterImei(terminal_imei);  //删除推行的内容
		
		
}
}
