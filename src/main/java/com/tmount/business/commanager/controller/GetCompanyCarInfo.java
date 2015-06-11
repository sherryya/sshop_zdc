package com.tmount.business.commanager.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.user.controller.UserLogin;
import com.tmount.db.commanager.dao.ComManager;
import com.tmount.db.commanager.dto.CompanyCarInfo;
import com.tmount.exception.ShopException;
import com.tmount.tools.CommonFunction;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetCompanyCarInfo  extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(UserLogin.class.getName());
	@Autowired
	private ComManager comManager;
	@RequestMapping(value = "companymanagergetcars.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson,HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		String id = new String(ParamData.getString(requestParam.getBodyNode(),"id"));//用户ID
		String query_flag = new String(ParamData.getString(requestParam.getBodyNode(),"query_flag"));//0:全部车辆（包括没有司机的车）1：只查询有司机的车2：只查询没有司机的车
		CompanyCarInfo companyCarInfo =new  CompanyCarInfo();
		companyCarInfo.setUser_id(id);
		List<CompanyCarInfo> list_com =new ArrayList<CompanyCarInfo>();
		list_com = comManager.getComanyCarInfoAll(companyCarInfo);
		responseBodyJson.writeArrayFieldStart("activity_list");
		String ret="";
		String latitude="";
		String longitude="";
		if (list_com.size() != 0) {
			Iterator<CompanyCarInfo> it = list_com.iterator();
			while (it.hasNext()) {
				CompanyCarInfo companyCarInfoT;
				CompanyCarInfo companyCarInfoT1=new CompanyCarInfo();
				companyCarInfoT = it.next();
			
			    companyCarInfoT1= comManager.getDriverInfoByCarId(companyCarInfoT);
			    if(query_flag.equals("0"))//显示全部车辆   没有驾驶员的也显示，驾驶员是从签到表里取的
				{	
			    	responseBodyJson.writeStartObject();	    		
			    	responseBodyJson.writeNumberField("id", companyCarInfoT.getId());
					responseBodyJson.writeStringField("carnum",companyCarInfoT.getCarnum());
					responseBodyJson.writeStringField("carname",companyCarInfoT.getCarname());
					responseBodyJson.writeStringField("DeviceUID",companyCarInfoT.getDeviceUID());
					//得到转换后的精度纬度
					if(companyCarInfoT.getDeviceUID()!=null)
					{
						ret=CommonFunction.getLocatin(companyCarInfoT.getDeviceUID().toString());
						if(ret.length()>0)
						{
							latitude=ret.split(",")[0].toString();
							longitude=ret.split(",")[1].toString();
							responseBodyJson.writeStringField("latitude",latitude);
							responseBodyJson.writeStringField("longitude",longitude);
							responseBodyJson.writeStringField("state",CommonFunction.getCurrentState(companyCarInfoT.getDeviceUID()));
						}
						else
						{
							responseBodyJson.writeStringField("latitude","");
							responseBodyJson.writeStringField("longitude","");
							responseBodyJson.writeStringField("state","无状态");
						}
						
					}
					else
					{
						responseBodyJson.writeStringField("latitude","");
						responseBodyJson.writeStringField("longitude","");
						responseBodyJson.writeStringField("state","无状态");
					}
					//得到驾驶员信息
			    	if(companyCarInfoT1!=null)
					{
						responseBodyJson.writeStringField("driver_name",companyCarInfoT1.getName());
						responseBodyJson.writeStringField("driver_tel",companyCarInfoT1.getTel());
					
					}
			    	else
			    	{
			    		responseBodyJson.writeStringField("driver_name","");
						responseBodyJson.writeStringField("driver_tel","");
						
			    	}
			    	responseBodyJson.writeEndObject();
				}
			    else if(query_flag.equals("1"))//只查询有驾驶员的，驾驶员是从签到表里取的
				{			    		
			    	if(companyCarInfoT1!=null)
					{   
			    		responseBodyJson.writeStartObject();	
				    	responseBodyJson.writeNumberField("id", companyCarInfoT.getId());
						responseBodyJson.writeStringField("carnum",companyCarInfoT.getCarnum());
						responseBodyJson.writeStringField("carname",companyCarInfoT.getCarname());
						responseBodyJson.writeStringField("DeviceUID",companyCarInfoT.getDeviceUID());
						if(companyCarInfoT.getDeviceUID()!=null)
						{
							ret=CommonFunction.getLocatin(companyCarInfoT.getDeviceUID().toString());
							if(ret.length()>0)
							{
								latitude=ret.split(",")[0].toString();
								longitude=ret.split(",")[1].toString();
								responseBodyJson.writeStringField("latitude",latitude);
								responseBodyJson.writeStringField("longitude",longitude);
								responseBodyJson.writeStringField("state",CommonFunction.getCurrentState(companyCarInfoT.getDeviceUID()));
							}
							else
							{
								responseBodyJson.writeStringField("latitude","");
								responseBodyJson.writeStringField("longitude","");
								responseBodyJson.writeStringField("state","无状态");
							}
							
						}
						else
						{
							responseBodyJson.writeStringField("latitude","");
							responseBodyJson.writeStringField("longitude","");
							responseBodyJson.writeStringField("state","无状态");
						}
						responseBodyJson.writeStringField("driver_name",companyCarInfoT1.getName());
						responseBodyJson.writeStringField("driver_tel",companyCarInfoT1.getTel());
						responseBodyJson.writeEndObject();
					}
				}
			    else //只查询没有驾驶员的，驾驶员是从签到表里取的
				{			    		
			    	if(companyCarInfoT1==null)
					{
			    		responseBodyJson.writeStartObject();	
				    	responseBodyJson.writeNumberField("id", companyCarInfoT.getId());
						responseBodyJson.writeStringField("carnum",companyCarInfoT.getCarnum());
						responseBodyJson.writeStringField("carname",companyCarInfoT.getCarname());
						responseBodyJson.writeStringField("DeviceUID",companyCarInfoT.getDeviceUID());
						if(companyCarInfoT.getDeviceUID()!=null)
						{
							ret=CommonFunction.getLocatin(companyCarInfoT.getDeviceUID().toString());
							if(ret.length()>0)
							{
								latitude=ret.split(",")[0].toString();
								longitude=ret.split(",")[1].toString();
								responseBodyJson.writeStringField("latitude",latitude);
								responseBodyJson.writeStringField("longitude",longitude);
								responseBodyJson.writeStringField("state",CommonFunction.getCurrentState(companyCarInfoT.getDeviceUID()));
							}
							else
							{
								responseBodyJson.writeStringField("latitude","");
								responseBodyJson.writeStringField("longitude","");
								responseBodyJson.writeStringField("state","无状态");
							}
							
						}
						else
						{
							responseBodyJson.writeStringField("latitude","");
							responseBodyJson.writeStringField("longitude","");
							responseBodyJson.writeStringField("state","无状态");
						}
						responseBodyJson.writeStringField("driver_name","");
						responseBodyJson.writeStringField("driver_tel","");
						responseBodyJson.writeEndObject();
					}
				}
			
			}
		}
		responseBodyJson.writeEndArray();
	}
	


}

