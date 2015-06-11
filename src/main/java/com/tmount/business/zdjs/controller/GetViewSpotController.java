package com.tmount.business.zdjs.controller;
import java.io.IOException;
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
import com.tmount.business.zdjs.service.TItovViewspotService;
import com.tmount.db.zdjs.dto.TItovViewspot;
import com.tmount.db.zdjs.dto.TItovViewspotPicture;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;
@Controller
public class GetViewSpotController extends ControllerBase {
	String json = "";
	@Autowired
	private  TItovViewspotService tItovViewspotService;
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,JsonGenerationException, IOException {
         List<TItovViewspot> list_view=new ArrayList<TItovViewspot>();
         List<TItovViewspotPicture> list_view1=new ArrayList<TItovViewspotPicture>();
         list_view=tItovViewspotService.selectAll();
         if(list_view==null)
         {
        	 responseBodyJson.writeStringField("result", "0");
         }
         else
         {
        	responseBodyJson.writeStringField("result", "1");
        	responseBodyJson.writeArrayFieldStart("Data");
        	 for(TItovViewspot tItovViewspot:list_view)
        	 {
        		 responseBodyJson.writeStartObject();
        		 responseBodyJson.writeStringField("deviceuid", tItovViewspot.getDeviceuid());
        		 responseBodyJson.writeStringField("lonlat", tItovViewspot.getLonlat());
        		 responseBodyJson.writeStringField("spot_name", tItovViewspot.getSpotName());
        		 responseBodyJson.writeStringField("spot_tel", tItovViewspot.getSpotTel());
        		 responseBodyJson.writeStringField("spot_address", tItovViewspot.getSpotAddress());
        		 responseBodyJson.writeStringField("spot_manager", tItovViewspot.getSpotManager());
        		 responseBodyJson.writeStringField("spot_introduction", tItovViewspot.getSpotIntroduction());
        		 list_view1=tItovViewspotService.selectBylonlat_id(tItovViewspot.getLonlatId());
        		 if(list_view1!=null)
        		 {
        			 responseBodyJson.writeArrayFieldStart("Picture");
                	 for(TItovViewspotPicture tItovViewspotPicture:list_view1)
                	 {
                		 responseBodyJson.writeStartObject();
                		 responseBodyJson.writeStringField("path","http://125.211.221.231:10081/itovManage/styles/upload/"+ tItovViewspotPicture.getPicPathName());
                		 responseBodyJson.writeEndObject();
                	 }
                	 responseBodyJson.writeEndArray();
        		 }
        		 responseBodyJson.writeEndObject();
        	 }
           responseBodyJson.writeEndArray();
         }
	}
	@RequestMapping(value = "zdjs.viewspot.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}	 
}
