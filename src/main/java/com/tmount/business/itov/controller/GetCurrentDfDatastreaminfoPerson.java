package com.tmount.business.itov.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetCurrentDfDatastreaminfoPerson extends ControllerBase {
	String json = "";

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// 根据应用ID号查询获得对应设备列表信息
		Map<String, String> params = new HashMap<String, String>();
		String deviceuid = new String (ParamData.getString(requestParam.getBodyNode(), "deviceuid"));
		params.put("deviceuid", deviceuid);
		json= GetLaunchInfo.GetCurrentDFDataStreamInfo(params);
	//	json = URLDecoder.decode(json,"utf-8");
		//1.解析元征返回数据
		ObjectMapper om = new ObjectMapper();
		JsonNode js = om.readTree(json);
		//如果是一个里程已经结束，则全部数据为0，如何判断是否结束，目前只能根据json中是否有JsonContent这个属组，如果有的话，则数据没有结束
		JsonNode jsContent = js.get("Data").get("DFJsonContent");
		int j = 0;
		if(jsContent!=null){
			String jsContentStr = jsContent.textValue();
			ObjectMapper omNode = new ObjectMapper();
			JsonNode jsonNode = omNode.readTree(jsContentStr);
			Iterator it = jsonNode.iterator();
			
			while(it.hasNext()){
				JsonNode json_sub_content = (JsonNode) it.next();
				
				String DFDataStreamID = json_sub_content.get(
						"DFDataStreamID").textValue();
				//if("00000303".equals(DFDataStreamID)||"0000030B".equals(DFDataStreamID)||"00000300".equals(DFDataStreamID)||"0000041F".equals(DFDataStreamID)||"00000421".equals(DFDataStreamID)||"00000422".equals(DFDataStreamID)||"00000305".equals(DFDataStreamID)){
					j++;
					if ("0000030B".equals(DFDataStreamID)) {
						// 车速
						System.out.println("~~~~~~~~~~~~~~chesu~~~~~~"+json_sub_content.get("DFDataStreamValue").textValue());
						responseBodyJson.writeNumberField("speed",
								Double.parseDouble(json_sub_content.get("DFDataStreamValue").textValue()));
					}else if("00000300".equals(DFDataStreamID)) {
						// 发动机转速
						System.out.println("~~~~~~~~~~~~~~zhuansu~~~~~~"+json_sub_content.get("DFDataStreamValue").textValue());
						responseBodyJson.writeNumberField("rotationlSpeed",
								Double.parseDouble(json_sub_content.get("DFDataStreamValue")
										.textValue()));
					}else if("00000305".equals(DFDataStreamID)) {
						//发动机冷却液温度
						responseBodyJson.writeNumberField("waterTemperature",
								Double.parseDouble(json_sub_content.get("DFDataStreamValue")
										.textValue()));
					}else if("00000290".equals(DFDataStreamID)) {
						System.out.println("~~~~~~~~~~~~~~zzonglicheng~~~~~~"+json_sub_content.get("DFDataStreamValue").textValue());
						//总里程
						responseBodyJson.writeNumberField("driverKilometerTotal",
								Double.parseDouble(json_sub_content.get("DFDataStreamValue")
										.textValue()));
					}else if("00000512".equals(DFDataStreamID)) {
						System.out.println("~~~~~~~~~~~~~~zongyouhao~~~~~~"+json_sub_content.get("DFDataStreamValue").textValue());
						//总油耗
						responseBodyJson.writeNumberField("oilDepleteTotal",
								Double.parseDouble(json_sub_content.get("DFDataStreamValue")
										.textValue()));
					}else if("0000040F".equals(DFDataStreamID)) {
						System.out.println("~~~~~~~~~~~~~~pingjuyouhao~~~~~~"+json_sub_content.get("DFDataStreamValue").textValue());
						//平均油耗
						responseBodyJson.writeNumberField("averageOilDepletetotal",
								Double.parseDouble(json_sub_content.get("DFDataStreamValue")
										.textValue()));
					}	
				//}
			}
		}else{
			throw new ShopBusiException(ShopBusiErrorBundle.WRONGMESSAGEBYDEVICEID,
					new Object[] { deviceuid });
		}
		if(j==0){
			responseBodyJson.writeNumberField("speed",0.0);
			responseBodyJson.writeNumberField("rotationlSpeed",0);
			responseBodyJson.writeNumberField("waterTemperature",0.0);
			responseBodyJson.writeNumberField("driverKilometerTotal",0);
			responseBodyJson.writeNumberField("oilDepleteTotal",0);
			responseBodyJson.writeNumberField("averageOilDepletetotal",0);
		}
		responseBodyJson.writeNumberField("voltage",0);		
	}

	@RequestMapping(value = "currentdfdatastreaminfoperson.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);

	}
}
