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
import com.tmount.db.zdjs.dto.TItovViewspotPicture;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 根据lonlatID 得到照片信息
 * @author dell
 *
 */
@Controller
public class GetViewSpotPictureListController extends ControllerBase {
	String json = "";
	@Autowired
	private  TItovViewspotService tItovViewspotService;
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException,JsonGenerationException, IOException {
		String lonlatID = new String (ParamData.getString(requestParam.getBodyNode(), "lonlatID"));
		List<TItovViewspotPicture> list_view=new ArrayList<TItovViewspotPicture>();
        
		list_view=tItovViewspotService.selectBylonlat_id(lonlatID);
         if(list_view==null)
         {
        	 responseBodyJson.writeStringField("result", "0");
         }
         else
         {
        	responseBodyJson.writeStringField("result", "1");
        	responseBodyJson.writeArrayFieldStart("Data");
        	 for(TItovViewspotPicture tItovViewspot:list_view)
        	 {
        		 responseBodyJson.writeStartObject();
        		 responseBodyJson.writeStringField("picPathName", tItovViewspot.getPicPathName());
        		 responseBodyJson.writeStringField("lonlatId", tItovViewspot.getLonlatId());
        		 responseBodyJson.writeStringField("picId", tItovViewspot.getPicId());
        		 responseBodyJson.writeEndObject();
        	 }
           responseBodyJson.writeEndArray();
         }
	}
	@RequestMapping(value = "zdjs.viewspotpicturelist.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}	 
}
