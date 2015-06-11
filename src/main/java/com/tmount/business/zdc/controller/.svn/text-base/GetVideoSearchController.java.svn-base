package com.tmount.business.zdc.controller;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.cloopen.model.httprequest.AgentSysInfo;
import com.tmount.business.cloopen.util.PropertiesUtil;
import com.tmount.business.itov.platform.launch.config.LaunchConfig;
import com.tmount.business.itov.platform.launch.sign.MD5;
import com.tmount.business.itov.platform.launch.util.LaunchNotify;
import com.tmount.business.itov.platform.launch.util.UtilDate;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 得到视频频道
 * 
 * @author
 * 
 */
@Controller
public class GetVideoSearchController extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(GetVideoSearchController.class.getName());
	@RequestMapping(value = "VideoSearchController.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		filePath ="/"+filePath;
		AgentSysInfo agentSysInfo=new AgentSysInfo();
		agentSysInfo=	PropertiesUtil.getAgentSysInfo(filePath);
		String APPSECRET = agentSysInfo.getAPPSECRET();
		String APPKEY = agentSysInfo.getAPPKEY();
		String domain_65Net = agentSysInfo.getDomain_65Net();
		
		String keyword = ParamData.getString(requestParam.getBodyNode(), "keyword");
		String rows = ParamData.getString(requestParam.getBodyNode(), "num");
		String page = ParamData.getString(requestParam.getBodyNode(), "page");
		Map paramMap = new HashMap();// 传递body内容
	    keyword= URLEncoder.encode(keyword,"utf-8");
		paramMap.put("c","1");
		paramMap.put("keyword",keyword);
		paramMap.put("page",page);
		paramMap.put("rows",rows);
		paramMap.put("s","1");
		paramMap.put("t","month");
		paramMap.put("page","1");
		String str = LaunchNotify.getSignVeryfy2(paramMap);
		String datetimestamp = UtilDate.getOrderNum();
		String str1 = str + "#"+APPKEY+"#"+APPSECRET+"#" + datetimestamp;
		String sign = MD5.verify2(str1, LaunchConfig.develop_key, LaunchConfig.input_charset);
		String url=domain_65Net+"search.json?c=1&keyword="+keyword+"&page="+page+"&rows="+rows+"&s=1&t=month&sign="+sign+"&appkey="+APPKEY+"&ts="+datetimestamp+"";
		String json = LaunchNotify.checkUrl(url);
		responseBodyJson.writeStringField("PLAY", json.replace("\"", "'"));
	}
}
