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
public class GetViewSpotByLotnlatController extends ControllerBase {
	String json = "";
	@Autowired
	private  TItovViewspotService tItovViewspotService;
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,JsonGenerationException, IOException {
		String lonlatId = new String (ParamData.getString(requestParam.getBodyNode(), "lonlat"));
		TItovViewspot tItovViewspot=new TItovViewspot();
        
		 tItovViewspot=tItovViewspotService.selectByLonlat_id(lonlatId);
	     if(tItovViewspot==null)
	     {
	    	 responseBodyJson.writeStringField("result", "0");
	     }
	     else
	     {
	    	 responseBodyJson.writeStringField("result", "1");
	       	 responseBodyJson.writeStringField("deviceuid", tItovViewspot.getDeviceuid());
			 responseBodyJson.writeStringField("lonlat", tItovViewspot.getLonlat());
			 responseBodyJson.writeStringField("spot_name", tItovViewspot.getSpotName());
			 responseBodyJson.writeStringField("spot_tel", tItovViewspot.getSpotTel());
			 responseBodyJson.writeStringField("spot_address", tItovViewspot.getSpotAddress());
			 responseBodyJson.writeStringField("spot_manager", tItovViewspot.getSpotManager());
			 responseBodyJson.writeStringField("spot_introduction", tItovViewspot.getSpotIntroduction());
			 responseBodyJson.writeStringField("spot_note1", tItovViewspot.getSportNote1());
			 responseBodyJson.writeStringField("spot_note2", tItovViewspot.getSportNote2());
			 responseBodyJson.writeStringField("spot_note3", tItovViewspot.getSportNote3());
			 responseBodyJson.writeStringField("spot_note", tItovViewspot.getSpotNote());
	     }
	}
	@RequestMapping(value = "zdjs.viewspotbylonlat.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}	 
}
