package com.tmount.business.itov.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBasePlatform;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.ResponseData;

@Controller
public class GetGPSInfo extends ControllerBasePlatform {
	String json = "";
	@Override
	protected String doService(RequestParam requestParam) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String (ParamData.getString(requestParam.getBodyNode(), "deviceuid"));
		String date = new String (ParamData.getString(requestParam.getBodyNode(), "date"));
		String starttime = new String (ParamData.getString(requestParam.getBodyNode(), "starttime"));
		String endtime = new String (ParamData.getString(requestParam.getBodyNode(), "endtime"));
		//1.比较开始时间和结束时间，如果开始时间大于结束时间，则要分两次查询
		try{
			if(compareToDate(date,starttime,endtime)){
				params.put("deviceuid", deviceuid);
				params.put("date", date);
				params.put("starttime", starttime);
				params.put("endtime", endtime);
				json= GetLaunchInfo.getGPSInfo(params);
				json = URLDecoder.decode(json,"utf-8");
				return json;
			}else{
				// 如果开始时间大于结束时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date current_date = sdf.parse(date);
				Calendar c = Calendar.getInstance();
				c.setTime(current_date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day - 1);
				String dayBefore = new SimpleDateFormat("yyyy-MM-dd")
						.format(c.getTime());
				//取第一天数据
				params.put("deviceuid", deviceuid);
				params.put("date", dayBefore);
				params.put("starttime", starttime);
				params.put("endtime", "23:59:59");
				json= GetLaunchInfo.getGPSInfo(params);
				json = URLDecoder.decode(json,"utf-8");
				System.out.println("firstDayJson="+json);
				//取第二天数据
				params.put("deviceuid", deviceuid);
				params.put("date", date);
				params.put("starttime", "00:00:00");
				params.put("endtime", endtime);
				String jsonSecondDay= GetLaunchInfo.getGPSInfo(params);
				jsonSecondDay = URLDecoder.decode(jsonSecondDay,"utf-8");
				System.out.println("secondDayJson="+jsonSecondDay);
				int bodyIndex = jsonSecondDay.indexOf("\"Data\":["); 
				if (bodyIndex == -1) {
					throw new Exception("未找到Data!");
				}
				String bodyStr = jsonSecondDay.substring(bodyIndex + 8,
						jsonSecondDay.length() - 2); // 只验证body里两个大括号中的内容，包括大括号。
				System.out.println("secondDayJsonStr="+bodyStr);
				//将bodyStr拼入json
				json =json.substring(0, json.length()-2)+","+bodyStr+"]}";
				System.out.println("json="+json);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
		
	}
	
	@RequestMapping(value = "gpsinfo.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

	}
	
	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ResponseData rd = mapper.readValue(json, ResponseData.class);
		return rd;
	}
	
	public boolean compareToDate(String date,String starttime,String endtime){
		boolean flag =false;
		try{
			SimpleDateFormat sdf_all = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sdf_all.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			Date startTime = sdf_all.parse(date + " " + starttime);
			Date endTime = sdf_all.parse(date + " " + endtime);
			if(startTime.before(endTime)){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
}
