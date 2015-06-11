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
 * 删除景点 通过pic_id
 * 
 * @author
 * 
 */
@Controller
public class DeleteViewSpotPictureByIDController extends ControllerBase {
	@Autowired
	private TItovViewspotService tItovViewspotService;
	@RequestMapping(value = "viewspotpictureByID.delete")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String pic_id = ParamData.getString(requestParam.getBodyNode(),"pic_id");//
		TItovViewspotPicture tItovViewspotPicture = new TItovViewspotPicture();
		tItovViewspotPicture.setPicId(pic_id);
		tItovViewspotService.deleteByPic_id(tItovViewspotPicture);
		responseBodyJson.writeStringField("result", "0");
	}
}