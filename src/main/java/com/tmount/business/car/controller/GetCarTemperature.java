package com.tmount.business.car.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.TZdcCarTemperatureServices;
import com.tmount.business.mileage.service.ZdcGpsinfoService;
import com.tmount.db.car.dto.TZdcCarTemperature;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetCarTemperature  extends ControllerBase {
	@Autowired
	private TZdcCarTemperatureServices tZdcCarTemperatureServices;
	@Autowired
	private ZdcGpsinfoService zdcgpsService;
	@RequestMapping(value = "CarTemperature.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
 JsonGenerationException, IOException {
		String deviceid = ParamData.getString(requestParam.getBodyNode(), "deviceid");
		TZdcCarTemperature tZdcCarTemperature = new TZdcCarTemperature();
		tZdcCarTemperature.setDeviceid(deviceid);
		TZdcCarTemperature temp = tZdcCarTemperatureServices.selectByPrimaryKey(tZdcCarTemperature);
		if (temp != null) {
			responseBodyJson.writeStringField("return", "1");
			responseBodyJson.writeStringField("temp", temp.getTemperature().toString());
			responseBodyJson.writeStringField("lasttime", temp.getLastTime());
			ZdcGpsinfo gpsBean = zdcgpsService.selectNewGps(deviceid);
			if (gpsBean != null) {
				responseBodyJson.writeNumberField("Latitude", gpsBean.getLatitude());
				responseBodyJson.writeNumberField("Longitude", gpsBean.getLongitude());
			}
		} else {
			responseBodyJson.writeStringField("return", "0");
		}
	}
}

