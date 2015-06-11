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
import com.tmount.db.integration.dto.CarModel;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;
@Controller
public class GetCarModelController extends ControllerBase {
	@Autowired
	private AddCarBrandService addCarBrandService;
	@RequestMapping(value = "CarModel.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		List<CarModel> arr=new	ArrayList<CarModel>();
		arr=addCarBrandService.getCarModel();
        Type listType = new TypeToken<ArrayList<CarModel>>(){}.getType();
        Gson gson=new Gson();
        String json=gson.toJson(arr, listType);
		responseBodyJson.writeStringField("result", json.replace("\"", "'"));
	}
}
