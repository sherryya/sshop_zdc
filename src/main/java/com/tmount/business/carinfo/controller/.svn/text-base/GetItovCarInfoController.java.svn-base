package com.tmount.business.carinfo.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.carinfo.service.CarinfoService;
import com.tmount.business.feedback.service.FeedbackService;
import com.tmount.db.carinfo.dto.TItov_car_info;
import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到car信息
 * 
 * @author qgy
 * 
 */
@Controller
public class GetItovCarInfoController extends ControllerBase{
	@Autowired
	private CarinfoService carinfoService;
    @RequestMapping(value = "carinfomation.query.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		try{
		    List<TItov_car_info> arr = new ArrayList<TItov_car_info>();
		    TItov_car_info tItov_car_info = new TItov_car_info();

		    String carType = ParamData.getString(
			    requestParam.getBodyNode(), "carType");// 车类型id
		    String carPlateNum = ParamData.getString(requestParam.getBodyNode(),
			    "carPlateNum");// 车牌号
		    String begin_time = ParamData.getString(requestParam.getBodyNode(),
				    "begin_time");// 起
		    String end_time = ParamData.getString(requestParam.getBodyNode(),
				    "end_time");// 止
		    String accountName = ParamData.getString(requestParam.getBodyNode(),
				    "accountName");// 账号名称
		    
		    Integer pageSize = new Integer(ParamData.getInt(
			    requestParam.getBodyNode(), "page_size", -1));// 每页多少条
		    Integer startLimit = -1;
		    Integer pageNo = new Integer(ParamData.getInt(
			    requestParam.getBodyNode(), "page_num", -1));// 第几页
		    if (pageNo != -1) {
			startLimit = (pageNo - 1) * pageSize;
			tItov_car_info.setStartLimit(startLimit);
		    }
		    if (pageSize != -1) {
		    	tItov_car_info.setPageSize(pageSize);
		    }
		    tItov_car_info.setCar_plate_number(carPlateNum);
		    tItov_car_info.setCar_type_query(carType);
		    tItov_car_info.setAccount_name(accountName);
		    tItov_car_info.setBegin_time(begin_time);
		    tItov_car_info.setEnd_time(end_time);
		    
		    Integer recordCount = carinfoService.selectSizeByWhere(tItov_car_info);
		    Integer pageCount = 0;
		    if (pageSize != -1) {
			pageCount = (recordCount + (pageSize - 1)) / pageSize;
		    }
		    arr = carinfoService.selectByWhere(tItov_car_info);
		    Type listType = new TypeToken<ArrayList<CarModel>>() {
		    }.getType();
		    Gson gson = new Gson();
		    String json = gson.toJson(arr, listType);
	
		    responseBodyJson.writeNumberField("pageCount", pageCount);
		    responseBodyJson.writeNumberField("totalCount", recordCount);
		    responseBodyJson
			    .writeStringField("result", json.replace("\"", "'"));
		}catch (Exception e) {
		    // TODO: handle exception
			System.out.println("打印我的错误-------->");
			e.printStackTrace();
			System.out.println("打印完毕----------->");
		    throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,
			    new Object[] { null });
		}
	}
}
