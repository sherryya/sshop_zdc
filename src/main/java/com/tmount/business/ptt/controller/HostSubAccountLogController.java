package com.tmount.business.ptt.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.db.ptt.vo.HostSubAccountLog;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import java.lang.reflect.Type;

@Controller
public class HostSubAccountLogController extends ControllerBaseByLogin {

	Logger logger = Logger.getLogger(HostSubAccountLogController.class.getName());
	@Autowired
	private PttSubaccountService pttService;

	@RequestMapping(value = "hostSubAccountLog.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
		
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		String voipAccount = ParamData.getString(requestParam.getBodyNode(), "voipAccount");  //voip账户
		int page_num = ParamData.getInt(requestParam.getBodyNode(), "page_num",-1);
		int page_size = ParamData.getInt(requestParam.getBodyNode(), "page_size",-1);
		String voip_status = ParamData.getString(requestParam.getBodyNode(), "voip_status");   //客服状态
		String starttimeget = ParamData.getString(requestParam.getBodyNode(), "starttime");   //客服状态
		String endtimeget = ParamData.getString(requestParam.getBodyNode(), "endtime");   //客服状态
		// TODO Auto-generated method stub
		HostSubAccountLog subAccountLog = new HostSubAccountLog();
		subAccountLog.setCalled(voipAccount);
		if(StringUtils.isNotBlank(endtimeget))
		{
			subAccountLog.setStarttime(starttimeget);
		}
		if(StringUtils.isNotBlank(endtimeget))
		{
			subAccountLog.setEndtime(endtimeget);
		}
		//设置第几页
		if(page_num!=-1)
		{
			subAccountLog.setStartLimit((page_num-1)*page_size);
		}else
		{
			subAccountLog.setStartLimit(0);
		}
		//每页多少天
		if(page_size!=-1)
		{
			subAccountLog.setPageSize(page_size);
		}else
		{
			subAccountLog.setPageSize(10);
		}
		//如果状态有入参则传入状态
		if(StringUtils.isNotBlank(voip_status))
		{
			subAccountLog.setVoip_status(voip_status);
		}
		int total = pttService.selectHostStaticCount(subAccountLog);
		List<HostSubAccountLog> subAccountList = pttService.selectHostStaticCondition(subAccountLog);
		if(subAccountList!=null && subAccountList.size()>0)
		{
			for(HostSubAccountLog subh:subAccountList)
			{
				String starttime ="";
				String endtime = "";
				if(StringUtils.isNotBlank(subh.getStarttime()))
				{
					starttime = subh.getStarttime();
				}
				if(StringUtils.isNotBlank(subh.getEndtime()))
				{
					endtime = subh.getEndtime();
				}
				SimpleDateFormat sdff =new SimpleDateFormat("yyyyMMddHHmmss");
				try {
					long onlineTime =0L;
					if(StringUtils.isNotBlank(endtime)&&StringUtils.isNotBlank(starttime))
					{
						onlineTime = sdff.parse(endtime).getTime()-sdff.parse(starttime).getTime();  //毫秒数
						onlineTime = onlineTime/1000;
						long sec = onlineTime%60;
						long hour = onlineTime/3600;
						long min = onlineTime/60-hour*60;
						subh.setOnlinetime(hour+"小时"+min+"分"+sec+"秒");
					}else
					{
						subh.setOnlinetime("");
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}
		Type listType = new TypeToken<ArrayList<HostSubAccountLog>>(){}.getType();
		Gson gson = new Gson();
		String hostStaticLogList = gson.toJson(subAccountList, listType);
		responseBodyJson.writeNumberField("totalCount", total);
		responseBodyJson.writeStringField("hostStaticLogList", hostStaticLogList.replace("\"", "'"));
	}


}
