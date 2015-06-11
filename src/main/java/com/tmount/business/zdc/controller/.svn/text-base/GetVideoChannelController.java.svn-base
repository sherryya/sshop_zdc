package com.tmount.business.zdc.controller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
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
public class GetVideoChannelController extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(GetVideoChannelController.class.getName());
	@RequestMapping(value = "VideoChannelController.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		
/*		try{ 
			String title="This is Title"; 
			String content="This is Content Area"; 
			String editer="LaoMao"; 
			String filePath1 = ""; 
			filePath1 = request.getRealPath("/")+"template.html"; 
			String templateContent=""; 
			FileInputStream fileinputstream = new FileInputStream(filePath1);//读取模块文件 
			int lenght = fileinputstream.available(); 
			byte bytes[] = new byte[lenght]; 
			fileinputstream.read(bytes); 
			fileinputstream.close(); 
			templateContent = new String(bytes); 
		
			templateContent=templateContent.replaceAll("###title###",title); 
			templateContent=templateContent.replaceAll("###content###",content); 
			templateContent=templateContent.replaceAll("###author###",editer);//替换掉模块中相应的地方 
	
			Calendar calendar = Calendar.getInstance(); 
			String fileame = String.valueOf(calendar.getTimeInMillis()) +".html"; 
			fileame = request.getRealPath("/")+fileame;//生成的html文件保存路径 
			FileOutputStream fileoutputstream = new FileOutputStream(fileame);//建立文件输出流 
			byte tag_bytes[] = templateContent.getBytes(); 
			fileoutputstream.write(tag_bytes); 
			fileoutputstream.close(); 
			} 
			catch(Exception e){ 
			}
		*/
		
		
		
		
		
		
		
		
		
		
		String filePath = this.getClass().getClassLoader().getResource("config.properties").toString().substring(6);
		filePath ="/"+filePath;
		AgentSysInfo agentSysInfo=new AgentSysInfo();
		agentSysInfo=	PropertiesUtil.getAgentSysInfo(filePath);
		String APPSECRET = agentSysInfo.getAPPSECRET();
		String APPKEY = agentSysInfo.getAPPKEY();
		String domain_65Net = agentSysInfo.getDomain_65Net();
		
		String cid = ParamData.getString(requestParam.getBodyNode(), "cid");// 频道分类
		String num = ParamData.getString(requestParam.getBodyNode(), "num");
		String page = ParamData.getString(requestParam.getBodyNode(), "page");
		Map paramMap = new HashMap();// 传递body内容
		paramMap.put("cid", cid);
		paramMap.put("num", num);
		paramMap.put("page", page);
		String str = LaunchNotify.getSignVeryfy2(paramMap);
		String datetimestamp = UtilDate.getOrderNum();
		String str1 = str + "#"+APPKEY+"#"+APPSECRET+"#" + datetimestamp;
		String sign = MD5.verify2(str1, LaunchConfig.develop_key, LaunchConfig.input_charset);
		String url = domain_65Net+"channel.json?cid=" + cid + "&num=" + num + "&page=" + page + "&sign=" + sign + "&appkey="+APPKEY+"&ts=" + datetimestamp + "";
		String json = LaunchNotify.checkUrl(url);
		responseBodyJson.writeStringField("PLAY", json.replace("\"", "'"));
		
		
		
		

		
		 

	}
}
