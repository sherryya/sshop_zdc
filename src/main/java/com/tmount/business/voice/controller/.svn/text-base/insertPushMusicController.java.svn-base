package com.tmount.business.voice.controller;
import java.io.IOException;
import java.util.Iterator;
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
 * 百度音乐下发接口插入下发信息
 * 
 * @author
 * 
 */
@Controller
public class insertPushMusicController extends ControllerBase {
	Logger logger = Logger.getLogger(insertPushMusicController.class);
	@Autowired
	private TItovMusicpushService tmusicService;
	@RequestMapping(value = "insertMusicController")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String push_info = new String(ParamData.getString(requestParam.getBodyNode(), "push_info"));// 歌手/歌曲/试听地址
		String terminal_imei = new String(ParamData.getString(requestParam.getBodyNode(), "terminal_imei"));//terminal_imei
		TItovMusicpush musicInfo = new TItovMusicpush();
		musicInfo.setPushInfo(push_info);
		musicInfo.setTerminalImei(terminal_imei);
		tmusicService.deleteByterImei(terminal_imei);  //删除推行的内容
		int count = tmusicService.insert(musicInfo);  //插入下发的音乐信息
		responseBodyJson.writeNumberField("flag", count);
	}
}
