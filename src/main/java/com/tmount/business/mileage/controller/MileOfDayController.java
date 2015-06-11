package com.tmount.business.mileage.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.mileage.service.ZdcCanstreamService;
import com.tmount.business.mileage.service.ZdcGpsinfoService;
import com.tmount.business.mileage.service.ZdcMileageService;
import com.tmount.db.mileage.dto.ZdcCanstream;
import com.tmount.db.mileage.dto.ZdcGpsinfo;
import com.tmount.db.mileage.dto.ZdcMileage;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.MapUtil;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 查询全天的里程
 * 
 * @author
 * 
 */
@Controller
public class MileOfDayController extends ControllerBase {
    Logger logger = Logger.getLogger(MileOfDayController.class);
    @Autowired
    private ZdcGpsinfoService zdcgpsService;
    @Autowired
    private ZdcMileageService zdcMileService;

    @RequestMapping(value = "mileAllDay.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson) throws ShopException,
	    JsonGenerationException, IOException {
	String deviceuid = ParamData.getString(requestParam.getBodyNode(),
		"deviceuid");
	String d = ParamData.getString(requestParam.getBodyNode(), "mileTime",
		null);
	int MUN = ParamData.getInt(requestParam.getBodyNode(), "pageNum", -1);
	int PAGE = ParamData.getInt(requestParam.getBodyNode(), "pageSize", -1);
	logger.info("deviceuid:" + deviceuid + "\n" + "mileTime:" + d + "\n"
		+ "pageNum:" + MUN + "\n" + "pageSize:" + PAGE);
	String date = null;
	if (d != ""&& d!=null) {
	    Date dateOne = StrToDate(d);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    date = sdf.format(dateOne);
	}
	ZdcMileage zdcMile = new ZdcMileage();
	// 设置设备id
	zdcMile.setDeviceuid(deviceuid);
	zdcMile.setDatastreamid14(date);
	zdcMile.setStartLimit((MUN - 1) * PAGE);
	zdcMile.setPageSize(PAGE);
	List<ZdcMileage> list = zdcMileService.selectAllDayMile(zdcMile);
	logger.info("~~~~~~~~MileOfDayController~~~~~~计算里程的起始点和结束点");

	// zdcgpsService.selectGpsInfoByMileSeviceList(list);
	responseBodyJson.writeArrayFieldStart("mileData");
	for (int index = 0; index < list.size(); index++) {
	    ZdcMileage zmile = list.get(index);
	    String minDateStr = zmile.getDatastreamid14();
	    responseBodyJson.writeStartObject();
	    responseBodyJson.writeNumberField("id", zmile.getId());
	    responseBodyJson
		    .writeStringField("deviceuid", zmile.getDeviceuid());
	    responseBodyJson.writeStringField("mileTime",
		    zmile.getDatastreamid1());
	    if(zmile.getDatastreamid2()==null)
	    {
	    	responseBodyJson.writeStringField("mileage","0");
	    }else
	    {
	    	responseBodyJson.writeStringField("mileage",
	    		    zmile.getDatastreamid2());
	    }
	    responseBodyJson.writeStringField("oilconsume",
		    zmile.getDatastreamid3());
	    responseBodyJson.writeStringField("waitCount",
		    zmile.getDatastreamid4());
	    responseBodyJson.writeStringField("waitTime",
		    zmile.getDatastreamid5());
	    responseBodyJson.writeStringField("speedfast",
		    zmile.getDatastreamid6());
	    responseBodyJson.writeStringField("speedQuick",
		    zmile.getDatastreamid7());
	    responseBodyJson.writeStringField("speedSlow",
		    zmile.getDatastreamid8());
	    responseBodyJson.writeStringField("tempreaturHige",
		    zmile.getDatastreamid9());
	    if(zmile.getDatastreamid10()==null)
	    {
	    	responseBodyJson.writeStringField("mileStart","0");
	    }else
	    {
	    	responseBodyJson.writeStringField("mileStart",
	    		    zmile.getDatastreamid10());
	    }
	    if(zmile.getDatastreamid11()==null)
	    {
	    	responseBodyJson.writeStringField("mileEnd","0");
	    }else
	    {
	    	 responseBodyJson.writeStringField("mileEnd",
	    			    zmile.getDatastreamid11());
	    	
	    }
	   if(zmile.getDatastreamid12()==null)
	   {
		   responseBodyJson.writeStringField("oilStart","0");
	   }else
	   {
	    responseBodyJson.writeStringField("oilStart",
		    zmile.getDatastreamid12());
	   }
	   if(zmile.getDatastreamid13()==null)
	   {
		   responseBodyJson.writeStringField("oilEnd","0");
	   }else
	   {
	    responseBodyJson.writeStringField("oilEnd",
		    zmile.getDatastreamid13());
	   }
	    responseBodyJson.writeStringField("timeStart", minDateStr);
	    responseBodyJson.writeStringField("timeEnd",
		    zmile.getDatastreamid15());
	    // 根据里程的起始和结束时间查询gps信息
	    String start_Latitude = ""; // 开始经度
	    String start_Longitude = ""; // 开始纬度
	    String end_Latitude = "";
	    String end_Longitude = "";
	    List<ZdcGpsinfo> gpsList = null;
	    if (minDateStr == null) {
		gpsList = zdcgpsService.selectGpsInfoByMileSevice(zmile);
	    } else {
		gpsList = zdcgpsService.selectGpsInfoByMileRange(zmile);
	    }

	    int size = gpsList.size();
	    if (gpsList.size() > 0) {
		ZdcGpsinfo zgf = gpsList.get(0);
		if (zgf != null) {
		    if (zgf.getLatitude() != null) {
			start_Latitude = String.valueOf(zgf.getLatitude());
		    }
		    if (zgf.getLongitude() != null) {
			start_Longitude = String.valueOf(zgf.getLongitude());
		    }
		    logger.info("开始经度---->>" + start_Latitude + "开始纬度---->"
			    + start_Longitude);
		}
		ZdcGpsinfo zgf1 = gpsList.get(size - 1);
		if (zgf1 != null) {
		    if (zgf1.getLatitude() != null) {
			end_Latitude = String.valueOf(zgf1.getLatitude());
		    }
		    if (zgf1.getLongitude() != null) {
			end_Longitude = String.valueOf(zgf1.getLongitude());
		    }
		    logger.info("结束经度---->>" + start_Latitude + "结束纬度---->"
			    + start_Longitude);
		}
	    }
	    // 获取某个里程的开始点的图吧逆地理
	    if (StringUtils.isNotEmpty(start_Latitude)
		    && StringUtils.isNotEmpty(start_Longitude)) {
		String retMsg = "";
		String param_mapbar = start_Latitude + "," + start_Longitude;
		try {
		    retMsg = MapUtil.getBaiduInverseGeographic1(param_mapbar);
		} catch (DocumentException e) {
		    e.printStackTrace();
		}
		responseBodyJson.writeStringField("startLocation", retMsg);
	    } else {
		responseBodyJson.writeStringField("startLocation", "暂无开始点位置");
	    }
	    // 图吧逆地理转换结束点位置
	    if (StringUtils.isNotEmpty(end_Latitude)
		    && StringUtils.isNotEmpty(end_Longitude)) {
		String param_mapbar = end_Latitude + "," + end_Longitude;
		String retMsg = "";
		try {
		    retMsg = MapUtil.getBaiduInverseGeographic1(param_mapbar);
		} catch (DocumentException e) {
		    e.printStackTrace();
		}
		responseBodyJson.writeStringField("endLocation", retMsg);
	    } else {
		responseBodyJson.writeStringField("endLocation", "暂无结束点位置");
	    }
	    responseBodyJson.writeEndObject();
	}
	responseBodyJson.writeEndArray();
    }

    public Date StrToDate(String str) {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date date = null;
	try {
	    date = format.parse(str);
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	return date;
    }
}
