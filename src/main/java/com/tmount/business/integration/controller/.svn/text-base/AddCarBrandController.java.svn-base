package com.tmount.business.integration.controller;
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
import com.tmount.business.integration.service.AddCarBrandService;
import com.tmount.business.itov.platform.inter.launch.App_FusionServices;
import com.tmount.db.integration.dto.CarBrand;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;
@Controller
public class AddCarBrandController extends ControllerBase {
	@Autowired
	private AddCarBrandService addCarBrandService;
	@RequestMapping(value = "CarBrand.insert")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		List<CarBrand>  list=new ArrayList<CarBrand>();
		try {
			list=App_FusionServices.getBarnd();
			for(CarBrand carBrand:list)  
			 {
				addCarBrandService.insert(carBrand);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseBodyJson.writeNumberField("result", 0);
		}
		responseBodyJson.writeNumberField("result", 1);
	}
}
