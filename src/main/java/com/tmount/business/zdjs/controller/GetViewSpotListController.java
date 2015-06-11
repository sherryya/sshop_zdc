package com.tmount.business.zdjs.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.zdjs.service.TItovViewspotService;
import com.tmount.db.zdjs.dto.TItovViewspot;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 根据UID得到所有的景点信息
 * @author dell
 *
 */
@Controller
public class GetViewSpotListController extends ControllerBase {
	String json = "";
	@Autowired
	private  TItovViewspotService tItovViewspotService;
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,JsonGenerationException, IOException {
		String deviceuid = new String (ParamData.getString(requestParam.getBodyNode(), "deviceuid"));
		List<TItovViewspot> list_view=new ArrayList<TItovViewspot>();
        
         list_view=tItovViewspotService.selectByUID(deviceuid);
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
        		 responseBodyJson.writeStringField("spot_note3", tItovViewspot.getSportNote3());
        		 responseBodyJson.writeEndObject();
        	 }
           responseBodyJson.writeEndArray();
         }
	}
	@RequestMapping(value = "zdjs.viewspotlist.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}	 
}
