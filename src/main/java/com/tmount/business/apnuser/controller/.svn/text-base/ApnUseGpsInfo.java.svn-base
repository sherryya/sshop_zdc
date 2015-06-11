package com.tmount.business.apnuser.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.mileage.service.ZdcGpsinfoService;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 路径规划 返回车辆的终点位置、距离、用时
 * @author dell
 *
 */
@Controller
public class ApnUseGpsInfo extends ControllerBase {
	@Autowired
	private ZdcGpsinfoService zdcsService;
	@RequestMapping(value = "apnGetGpsInfo.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String deviceuid = ParamData.getString(requestParam.getBodyNode(),"deviceId");//设备号
		Double dLat = ParamData.getDouble(requestParam.getBodyNode(), "latitude");  //起点纬度
		Double dLng = ParamData.getDouble(requestParam.getBodyNode(), "longitude");  //地点经度
		String distance="";
		String duration="";
		Double dLat_end=0.0;
		Double dLng_end=0.0;
		ZdcGpsinfo gpsBean = zdcsService.selectNewGps(deviceuid);
		if(gpsBean!=null)
		{
			dLat_end=gpsBean.getLatitude();
			dLng_end=gpsBean.getLongitude();
			String url = "http://api.map.baidu.com/direction/v1?mode=walking&origin=" + dLat + "," + dLng + "&region=" + dLat + "," + dLng + "&destination="+dLat_end+","+dLng_end+"&output=json&ak=RGDE1mGyvu0TmThI7ZL5lVvc";
			String retStr = "";
			java.net.URL getUrl = new URL(url);
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
			retStr = URLDecoder.decode(retStr, "UTF-8");
			ObjectMapper objectmappercontent = new ObjectMapper();
			JsonNode jsonNodeContent = objectmappercontent.readTree(retStr);
			Iterator it = jsonNodeContent.iterator();
			int i = 0;
			while (it.hasNext()) {
				JsonNode json_sub_content = (JsonNode) it.next();
				if (i == 0) {
					if (json_sub_content.toString().equals("0")) {
						i = i + 1;
					} else {
						break;
					}
				} else {
					if (i == 4) {
						JsonNode jsonNodeContent1 = objectmappercontent.readTree(json_sub_content.toString());
						Iterator it1 = jsonNodeContent1.iterator();
						while (it1.hasNext()) {
							JsonNode json_sub_content1 = (JsonNode) it1.next();
							 distance = json_sub_content1.get(0).get("distance").toString();
							 distance=distance+"米";
							 duration = json_sub_content1.get(0).get("duration").toString();
								System.out.println(duration);
							 StringBuffer sb=new StringBuffer();
							if (duration.length() > 0) {
								long totalsec = Long.valueOf(duration);
								long hour = totalsec / 3600;
								long min = totalsec % 3600 / 60; // 消耗的分钟数
								long sec = totalsec % 3600 % 60; // 剩余秒数
								if (hour != 0) {
									sb.append(hour + "小时");
								}
								if (min != 0) {
									sb.append(min + "分钟");
								}
								if (sec != 0) {
									sb.append(sec + "秒");
								}
							}
							duration=sb.toString();
							System.out.println(distance);
							System.out.println(sb.toString());
							break;
						}
					}
					i = i + 1;
				}
			}
		}
		else
		{
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,	new Object[] { "不存在位置信息" });
		}
		responseBodyJson.writeNumberField("dLat_end", dLat_end);
		responseBodyJson.writeNumberField("dLng_end", dLng_end);
		responseBodyJson.writeStringField("distance", distance);
		responseBodyJson.writeStringField("duration", duration);
	}
	public static void main(String[] args) throws Exception {
		/* int dist = 789;
		 double dis = 0;
		//你的距离数据应该不是写死的吧，如果你是从服务器获取的距离数据，可能是String，赋值给//distance时候就要强制类型转换(Integer）,然后再执行以下四舍五入
	   dis = Math.round(dist/100d)/10d;
       System.out.println(dis);*/
		long totalsec=1800;
		long hour=totalsec/3600;
		long min = totalsec%3600/60;  //消耗的分钟数
		long sec = totalsec%3600%60;   //剩余秒数
		    System.out.println(min);
		    System.out.println(sec);
		
	}
}
