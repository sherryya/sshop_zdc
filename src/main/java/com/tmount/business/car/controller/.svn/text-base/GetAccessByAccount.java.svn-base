package com.tmount.business.car.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetAccessByAccount  extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	String json = "";
	String lonlat = "";
	String retMsg = "";
	@RequestMapping(value = "AccessByAccount.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String account_id = ParamData.getString(requestParam.getBodyNode(), "account_id");
		CarInfo carInfo = carInfoService.getAccessByAccount( Integer.valueOf(account_id));
		if(carInfo!=null){
			String access_id =carInfo.getAccess_id();
			String access_token =carInfo.getAccess_token();
			responseBodyJson.writeStringField("access",access_id+","+access_token);
		}else{
			responseBodyJson.writeStringField("access",",");
		}
	}
}

