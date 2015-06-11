package com.tmount.business.reserve.controller;

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
import com.tmount.business.feedback.service.FeedbackService;
import com.tmount.business.reserve.service.ReserveService;
import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.reserve.dto.TItovReserve;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到反馈信息
 * 
 * @author qgy
 * 
 */
@Controller
public class GetItovReserveController extends ControllerBase{
	@Autowired
	private ReserveService reserveService;
    @RequestMapping(value = "reserve.query.get")
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
		    List<TItovReserve> arr = new ArrayList<TItovReserve>();
		    TItovReserve tzdc_reserve = new TItovReserve();

		    String reserve_name = ParamData.getString(
			    requestParam.getBodyNode(), "reserve_name");// 预约姓名
		    String reserve_code = ParamData.getString(requestParam.getBodyNode(),
			    "reserve_code");// 预约码
		    String phone_no = ParamData.getString(requestParam.getBodyNode(),
				    "phone_no"); //预约电话
		    String is_deal = ParamData.getString(
				    requestParam.getBodyNode(), "is_deal");
		    
		    Integer pageSize = new Integer(ParamData.getInt(
			    requestParam.getBodyNode(), "page_size", -1));// 每页多少条
		    Integer startLimit = -1;
		    Integer pageNo = new Integer(ParamData.getInt(
			    requestParam.getBodyNode(), "page_num", -1));// 第几页
		    if (pageNo != -1) {
			startLimit = (pageNo - 1) * pageSize;
			tzdc_reserve.setStartLimit(startLimit);
		    }
		    if (pageSize != -1) {
		    	tzdc_reserve.setPageSize(pageSize);
		    }
		    tzdc_reserve.setRealname(reserve_name);
		    tzdc_reserve.setIsdeal_query(is_deal);
		    tzdc_reserve.setPhoneNo(phone_no);
		    tzdc_reserve.setReserveCode(reserve_code);
		    Integer recordCount = reserveService.selectReserveSizeByWhere(tzdc_reserve);
		    Integer pageCount = 0;
		    if (pageSize != -1) {
			pageCount = (recordCount + (pageSize - 1)) / pageSize;
		    }
		    arr = reserveService.selectReserveByWhere(tzdc_reserve);
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
