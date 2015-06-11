package com.tmount.business.manage.controller;
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
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.manage.service.TItovMenuAssgnRuleService;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.manage.dto.TItovMenuAssgnRule;
import com.tmount.db.manage.dto.TItovMenuClass;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 得到角色菜单
 * @author dell
 *
 */
@Controller
public class GetItovMenuAssgnRuleController extends ControllerBase {
	@Autowired
	private TItovMenuAssgnRuleService tItovMenuAssgnRuleService;
	@RequestMapping(value = "menuAssgnRule.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		try{
		List<TItovMenuAssgnRule> arr=new	ArrayList<TItovMenuAssgnRule>();
		Integer roleId = ParamData.getInt(requestParam.getBodyNode(), "roleId");// 角色ID
		arr=tItovMenuAssgnRuleService.selectByPrimaryKey(roleId);
        Type listType = new TypeToken<ArrayList<CarModel>>(){}.getType();
        Gson gson=new Gson();
        String json=gson.toJson(arr, listType);
		responseBodyJson.writeStringField("result", json.replace("\"", "'"));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK, new Object[] { null });
		}
	}
}
