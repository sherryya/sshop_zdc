package com.tmount.business.zdjs.controller;
import java.io.IOException;
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
 * 添加景点细表
 * 
 * @author 
 * 
 */
@Controller
public class AddViewSpotPictureController extends ControllerBase {
	@Autowired
	private TItovViewspotService tItovViewspotService;
	@RequestMapping(value = "viewspotpicture.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String picId =ParamData.getString(requestParam.getBodyNode(), "picId");//设备UID
		String lonlatId=ParamData.getString(requestParam.getBodyNode(),	"lonlatId");//经纬度ID 
		String picPathName=ParamData.getString(requestParam.getBodyNode(),	"picPathName");//
		String picIntroduction=ParamData.getString(requestParam.getBodyNode(),	"picIntroduction");//
		String picNote=ParamData.getString(requestParam.getBodyNode(),	"picNote");//
		String picName=ParamData.getString(requestParam.getBodyNode(),	"picName");//
        TItovViewspotPicture tItovViewspotPicture=new  TItovViewspotPicture();
        tItovViewspotPicture.setLonlatId(lonlatId);
        tItovViewspotPicture.setPicId(picId);
        tItovViewspotPicture.setPicIntroduction(picIntroduction);
        tItovViewspotPicture.setPicName(picName);
        tItovViewspotPicture.setPicNote(picNote);
        tItovViewspotPicture.setPicPathName(picPathName);
        tItovViewspotService.insert_viewspot_picture(tItovViewspotPicture);
		responseBodyJson.writeStringField("result", "0");
	}
}
