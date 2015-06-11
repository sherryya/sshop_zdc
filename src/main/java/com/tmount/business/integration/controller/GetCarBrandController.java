package com.tmount.business.integration.controller;
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
import com.tmount.business.integration.service.AddCarBrandService;
import com.tmount.db.integration.dto.CarBrand;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;
@Controller
public class GetCarBrandController extends ControllerBase {
	@Autowired
	private AddCarBrandService addCarBrandService;
	@RequestMapping(value = "CarBrand.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
	
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
        String json=gson.toJson(nodeList, listType);
      //  System.out.println("json111111111111:"+json.replace("\"", "'"));
		
		//String json = App_FusionServices.GetBrandID(11);
		
		responseBodyJson.writeStringField("result", json.replace("\"", "'"));
	}
}
