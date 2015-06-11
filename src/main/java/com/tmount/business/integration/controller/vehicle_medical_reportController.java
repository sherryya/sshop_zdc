package com.tmount.business.integration.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.itov.platform.inter.launch.App_FusionServices;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/*
 * 
 * 得到任意时间段内的检测报告
 */
@Controller
public class vehicle_medical_reportController extends ControllerBase {
	@RequestMapping(value = "launch.get_medical_report")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String device_sn = ParamData.getString(requestParam.getBodyNode(), "device_sn");// 
		String start_date = ParamData.getString(requestParam.getBodyNode(), "start_date");// 
		String end_date = ParamData.getString(requestParam.getBodyNode(), "end_date");// 
		String targetpage = ParamData.getString(requestParam.getBodyNode(), "targetpage");// 
		String pagesize = ParamData.getString(requestParam.getBodyNode(), "pagesize");// 
	    String ret=App_FusionServices.test_report_ymd2(device_sn,start_date,end_date,targetpage,pagesize);
	    // 调用元征车辆检测报告接口    
	    ObjectMapper objectmappercontent = new ObjectMapper();
		JsonNode jsonNodeContent = objectmappercontent.readTree(ret);
		System.out.println(jsonNodeContent.get("code"));
		if (jsonNodeContent.get("code").toString().equals("0")) {//成功
			responseBodyJson.writeStringField("data", jsonNodeContent.get("data").toString());
		} 
		else if(jsonNodeContent.get("code").equals(""))//没有数据
		{
			throw new ShopBusiException(ShopBusiErrorBundle.VMR_NULL,
					new Object[] {  });
		}
		else {
			throw new ShopBusiException(ShopBusiErrorBundle.VMR_ERROR,
					new Object[] {  });
		}

	}
}
