package com.tmount.business.integration.controller;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.integration.service.AddCarBrandService;
import com.tmount.db.integration.dto.CarBrand;
import com.tmount.db.integration.dto.SoftVersion;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 4s店使用查找品牌的服务
 * @author Administrator
 *
 */
@Controller
public class GetCarBrandForFshopController extends ControllerBase {
	@Autowired
	private AddCarBrandService addCarBrandService;
	@RequestMapping(value = "CarBrandFshop.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		String str_version = new String(ParamData.getString(requestParam.getBodyNode(), "version"));//版本json
		Map<String, Object> map = new HashMap<String, Object>();
		Type mapType_v = new TypeToken<HashMap<String, Object>>(){}.getType();
		Gson gson_v=new Gson();
		map=gson_v.fromJson(str_version, mapType_v);//需要显示的版本信息
		List<SoftVersion> list_version=	new	ArrayList<SoftVersion>();
		list_version=addCarBrandService.getSoftVersion();//系统中现在的所有版本信息
		//responseBodyJson.writeArrayFieldStart("DataList");
		for(SoftVersion sv:list_version)
		{
			String ver_name=sv.getVer_name();
			String ver_value=sv.getVer_value();
			for (String key : map.keySet()) {
				
                 String ver_key=key;
                 String ver_value1= map.get(key).toString();
                 if(ver_key.equalsIgnoreCase(ver_name))
                 { 
                	 //responseBodyJson.writeStartObject();
                	 if(!ver_value.equalsIgnoreCase(ver_value1))//如果版本号不同才开始返回数据
                	 {
                		 String json="";
                		 if(ver_name.equalsIgnoreCase("carbrand"))//车品牌
                		 {
                				List<CarBrand> arr=new	ArrayList<CarBrand>();
                				List<CarBrand> nodeList=new	ArrayList<CarBrand>();
                				arr=addCarBrandService.getCarBrand();
                		        for(CarBrand node1 : arr){  
                		            boolean mark = false;  
                		            for(CarBrand node2 : arr){  
                		                if(!node1.getParentID().equals("0") && node1.getParentID().equals(node2.getCarSeriesId())){  
                		                    mark = true;  
                		                    if(node2.getSubList() == null)  
                		                        node2.setSubList(new ArrayList<CarBrand>());  
                		                    node2.getSubList().add(node1);   
                		                    break;  
                		                }  
                		            }  
                		            if(!mark){  
                		                nodeList.add(node1);   
                		            }  
                		        }
                		        Type listType = new TypeToken<ArrayList<CarBrand>>(){}.getType();
                		        Gson gson=new Gson();
                		        json=gson.toJson(nodeList, listType);
                		 } 
                		
             			//responseBodyJson.writeStringField("Ver_Name", ver_name);
             			//responseBodyJson.writeStringField("Ver_Value", ver_value);
         				responseBodyJson.writeStringField("Data", json.replace("\"", "'"));
                	 }
                	 
                	// responseBodyJson.writeEndObject();
                 }
              
			}
		}
		//responseBodyJson.writeEndArray();
	}
}
