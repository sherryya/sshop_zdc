package com.tmount.business.mileage.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.mileage.service.ZdcCanstreamService;
import com.tmount.db.mileage.dto.TZdcCanstreamOriginal;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 插入canstreamoriginal 数据
 * 
 * @author
 * 
 */
@Controller
public class InsertCanStreamOriginalController extends ControllerBase implements Runnable {
    Logger logger = Logger.getLogger(InsertCanStreamOriginalController.class);
    @Autowired
	private ZdcCanstreamService zdcCanService;
    private String deviceid;
    @RequestMapping(value = "insertCanOriginal")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson) throws ShopException,
	    JsonGenerationException, IOException {
	    deviceid = ParamData.getString(requestParam.getBodyNode(),
		"deviceuid");
	TZdcCanstreamOriginal canOriginal = zdcCanService.selectCanOriginal(deviceid);
	if(null != canOriginal)
	{
		String stream = canOriginal.getStream();
		String streamFlag = stream.substring(0, 12);
		//标识停止
		if("2E41020500B7".equals(streamFlag))
		{
			run(); //睡眠30s
			TZdcCanstreamOriginal canorignal = new TZdcCanstreamOriginal();
			canorignal.setDeviceid(deviceid);
			canorignal.setIsDeal(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowtime = sdf.format(new Date());
			String str = "2E41020501B6#"+nowtime+";";
			canorignal.setStream(str);
			//插入一条启动标识的数据
			int count= zdcCanService.insertValues(canorignal);
			System.out.println(count);
			responseBodyJson.writeNumberField("flag", count);
		}else
		{
			responseBodyJson.writeNumberField("flag", 0);
		}
	}
	
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(30000); //睡眠30s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
}
