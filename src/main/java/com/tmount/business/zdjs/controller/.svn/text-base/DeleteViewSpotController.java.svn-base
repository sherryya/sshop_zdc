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
import com.tmount.db.zdjs.dto.TItovViewspot;
import com.tmount.db.zdjs.dto.TItovViewspotPicture;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 删除景点
 * 
 * @author
 * 
 */
@Controller
public class DeleteViewSpotController extends ControllerBase {
	@Autowired
	private TItovViewspotService tItovViewspotService;

	@RequestMapping(value = "viewspot.delete")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String lonlatId = ParamData.getString(requestParam.getBodyNode(),
				"lonlatId");//

		TItovViewspot tItovViewspot = new TItovViewspot();
		tItovViewspot.setLonlatId(lonlatId);

		TItovViewspotPicture tItovViewspotPicture = new TItovViewspotPicture();
		tItovViewspotPicture.setLonlatId(lonlatId);

		tItovViewspotService.deleteViewSpotByLonlat_id(tItovViewspot);
		tItovViewspotService
				.deleteViewSpotPictureByLonlat_id(tItovViewspotPicture);
		responseBodyJson.writeStringField("result", "0");
	}
}
