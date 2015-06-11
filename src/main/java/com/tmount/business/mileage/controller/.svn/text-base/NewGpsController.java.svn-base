package com.tmount.business.mileage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.mileage.service.ZdcGpsinfoService;
import com.tmount.db.car.dto.TItovCar;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *查询全天的里程
 * 
 * @author 
 * 
 */
@Controller
public class NewGpsController extends ControllerBase {
	@Autowired
	private ZdcGpsinfoService zdcgpsService;
	
	@Autowired
	private CarInfoService carServie;
	
	@RequestMapping(value = "newGps.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
	         
	         String deviceuid = ParamData.getString(requestParam.getBodyNode(), "deviceuid");
	         String voipAccount = ParamData.getString(requestParam.getBodyNode(), "voipAccount");
	         //根据accountId 获得车的信息
	         List<TItovCar>  varList =  carServie.carList(voipAccount);
	         if(varList != null && varList.size()>0)
	         {
	        	 TItovCar car = varList.get(0);
	        	 if(car != null)
	        	 {
	        		 responseBodyJson.writeStringField("cardname", car.getCarName()); //车名
		        	 responseBodyJson.writeStringField("cardNum", car.getCarPlateNumber()); //车牌号
	        	 }
	        		
	         }
	         //获取最新的gps信息 根据车imei
			 ZdcGpsinfo gpsBean = zdcgpsService.selectNewGps(deviceuid);
			 if(gpsBean != null)
			 {
				 responseBodyJson.writeStringField("deviceuid",  gpsBean.getDeviceuid()); //设备id
				 responseBodyJson.writeNumberField("Direction", gpsBean.getDirection()); //方向
				 responseBodyJson.writeNumberField("Latitude", gpsBean.getLatitude());
				 responseBodyJson.writeNumberField("Longitude", gpsBean.getLongitude());
				 responseBodyJson.writeNumberField("Hdop", gpsBean.getHdop());
				 responseBodyJson.writeNumberField("Speed", gpsBean.getSpeed());  //速度
				 responseBodyJson.writeStringField("GPSTimeInDefaultTimeZone", gpsBean.getGpstimeindefaulttimezone());
				 
				/* //图吧坐标转化
					String xml = MapUtil.getMapBar(gpsBean.getLongitude()+","+gpsBean.getLatitude());
					System.out.println("retXml" + xml);
					Document document_mapbar = null;
					try {
						document_mapbar = DocumentHelper.parseText(xml);
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					String lonlat = "";
					Element el_req_mapbar = document_mapbar.getRootElement();
					if(el_req_mapbar!=null)
					{
						List lonlatList = el_req_mapbar.element("item").elements();
						
						if (lonlatList != null && lonlatList.size() > 0) {

							if ((Element) lonlatList.get(0) != null) {
								lonlat = ((Element) lonlatList.get(0)).getText();
							}
							System.out.println("mapbar=~~~~~~~~~~NewGpsController~~~~~~~~~~~~~~~~"
									+ lonlat);
						}
						System.out.println("~~~~~~~~~~~lonlat~~~~~NewGpsController~~~~~~~~"+lonlat);
						
					}
					
					*/
                    String lonlat = "'"+gpsBean.getLongitude()+","+gpsBean.getLatitude()+"'";
					responseBodyJson.writeStringField("lonlatMsg", "");
					responseBodyJson.writeStringField("lonlat",lonlat);
			 }
	}
}
