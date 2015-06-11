package com.tmount.business.reserve.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.cloopen.restAPI.RestAPI;
import com.tmount.business.cloopen.restAPI.RestExamples;
import com.tmount.business.ptt.controller.startService;
import com.tmount.business.reserve.service.ReserveService;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.reserve.dto.TItovReserve;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.StringUtil;
import com.tmount.util.XmlUitl;

/**
 * 增加预约记录
 * 
 * @author
 * 
 */
@Controller
public class ReserveAdd extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private ReserveService reserveService;
	Logger logger = Logger.getLogger(startService.class.getName());
	@RequestMapping(value = "reserve.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String phone = new String(ParamData.getString(requestParam
				.getBodyNode(), "phone"));// 手机号
		// 0.判断该手机号是否已经预约
		int count = reserveService.getCountByPhoneNo(phone);
		if(count>0){

			throw new ShopBusiException(
					ShopBusiErrorBundle.RESERVER_ORDER_ISEXIT, null);
		}
		// 1.得到对应的预约码
		//int reserver_int_code = carInfoService.getCarId("reserve_code");
		int value = carInfoService.queryId("reserve_code")+1;  //查询数据库序列值
		int reserver_int_code = value;
		TestUpd testupd = new TestUpd();
		testupd.setName("reserve_code");
		testupd.setValue(value);
		carInfoService.updtestupd(testupd);    //更新数据库的序列值
		StringUtil stringUtil = new StringUtil();
		String reserve_code = stringUtil.lpadFormat(reserver_int_code, 8, "0");
		// 2.插入预约表
		TItovReserve tItovReserve = new TItovReserve();
		tItovReserve.setPhoneNo(phone);
		tItovReserve.setReserveCode(reserve_code);
		reserveService.insert(tItovReserve);
		// 3.调用下发短信接口
		RestAPI rest = new RestAPI();
		// 4.调用发送短信接口
		String result="";
		try {
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~reserve.add is bigining  phone:"+phone);
			result = rest.SendSMS(phone, reserve_code, "0", "2");
			result = XmlUitl.xml(result,"statusCode");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopBusiException(
					ShopBusiErrorBundle.SEND_MESSAGE_ERROR, null);
		}
		
		if (!result.equalsIgnoreCase("000000")) {
			responseBodyJson.writeStringField("result:", "0");
		} else{
			responseBodyJson.writeStringField("result:", "000000");
		}
	}
}
