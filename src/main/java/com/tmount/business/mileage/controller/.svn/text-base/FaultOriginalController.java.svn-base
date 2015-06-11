package com.tmount.business.mileage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.mileage.service.ZdcFaultOriginalService;
import com.tmount.db.mileage.dto.TZdcFaultCodeLog;
import com.tmount.db.mileage.dto.TZdcFaultOriginal;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import java.lang.StringBuilder;
/**
 * 车机上传故障码到平台
 * 
 * @author
 * 
 */
@Controller
public class FaultOriginalController extends ControllerBase {
	Logger logger = Logger.getLogger(MileOfDayController.class);
	@Autowired
	private ZdcFaultOriginalService zdcFaultOriginalService;
    private TZdcFaultCodeLog code_log;
	@RequestMapping(value = "FaultOriginal.insert")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {

		String deviceuid = ParamData.getString(requestParam.getBodyNode(), "deviceuid");
		String fault_code = ParamData.getString(requestParam.getBodyNode(), "fault_code"); // 2E5107000000F10000F23B#2014-11-12
																							// 10:10:10
		logger.info("#############IMEI信息:" + deviceuid);
		logger.info("#############故障碼信息:" + fault_code);
		TZdcFaultOriginal tZdcFaultOriginal = null;
		TZdcFaultCodeLog  tZdcFaultCodeLog=null;
		tZdcFaultOriginal = new TZdcFaultOriginal();
		tZdcFaultCodeLog=new TZdcFaultCodeLog();
		tZdcFaultOriginal.setDeviceId(deviceuid);
		tZdcFaultOriginal.setFaultCode(fault_code);
		zdcFaultOriginalService.insert(tZdcFaultOriginal);

		String type = "";// CAN类型
		Integer len = 0;// 长度
		Integer fault_type = 0;// 单元类型 0,1,2,3,4,5
		Integer toal_len = 0;// 指令总长度
		String fault = "";// 所有故障码
		Integer times = 0;// 故障码个数
		String sub_fault = "";//每个故障码
		String date = "";//故障码产生时间
		String[] arr_code = fault_code.split("#");
	
		if (arr_code.length > 1) {
			date=arr_code[arr_code.length-1];
			for (String code : arr_code) {

				if (code.trim().length() > 10) {
					if (code.contains(" ")) {
						
					} else {
						type = code.trim().substring(2, 4);
						if (type.equals("51")) {
							//len = Integer.valueOf(code.trim().substring(4, 6));
							len=getLen(code);
							toal_len = len * 2 + 8;
							if (code.trim().length() == toal_len) {
								fault_type = Integer.valueOf(code.trim().substring(6, 8));
								times = (len - 1) / 3;
								fault = (code.trim().substring(8, 8 + 2 * (len - 1)));
								for (int i = 0; i < times; i++) {
									sub_fault = fault.substring(i * 6, 6 * i + 6);
									//String l=sub_fault.substring(0, 2);
									//String m=sub_fault.substring(2, 4);
									//String h=sub_fault.substring(4, 6);
									tZdcFaultCodeLog.setDeviceid(deviceuid);
									tZdcFaultCodeLog.setFaultType(String.valueOf(fault_type));
									tZdcFaultCodeLog.setHexCode(sub_fault);
									tZdcFaultCodeLog.setMakeDate(date);
									tZdcFaultCodeLog.setFaultClear(0);
									code_log=zdcFaultOriginalService.selectByInto(tZdcFaultCodeLog);
									if(code_log==null)
									zdcFaultOriginalService.insert_fault_log(tZdcFaultCodeLog);
								}
							}
						}
					}
				}
			}
		}
		responseBodyJson.writeStringField("return", "0");
	}
	private int  getLen(String code)
	{
		//Integer.parseInt("0D", 16)
		 return Integer.parseInt(code.trim().substring(4, 6),16);
	}
	public static void main(String[] args) {
		String len = "0D";
		Integer i_len = Integer.parseInt("0D", 16);// 16进制转10进制
		System.out.println(i_len);
	}
}
