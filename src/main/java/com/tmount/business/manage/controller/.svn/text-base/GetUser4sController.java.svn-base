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
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 得到4s店信息
 * 
 * @author dell
 * 
 */
@Controller
public class GetUser4sController extends ControllerBase{
    @Autowired
    private TItov_shop4sService tItov_shop4sService;

    @RequestMapping(value = "user4slist.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
    	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson) throws ShopException,
	    JsonGenerationException, IOException {
	try {
	    List<TItov_shop4s_user> arr = new ArrayList<TItov_shop4s_user>();
	    TItov_shop4s_user tItov_shop4s_user = new TItov_shop4s_user();
	    String account_name = ParamData.getString(requestParam.getBodyNode(),
		    "accountName");// 
	    Integer pageSize = new Integer(ParamData.getInt(
		    requestParam.getBodyNode(), "pageSize", -1));// 每页多少条
	    Integer startLimit = -1;
	    Integer pageNo = new Integer(ParamData.getInt(
		    requestParam.getBodyNode(), "pageNum", -1));// 第几页
	    if (pageNo != -1) {
		startLimit = (pageNo - 1) * pageSize;
		tItov_shop4s_user.setStartLimit(startLimit);
	    }
	    if (pageSize != -1) {
	    	tItov_shop4s_user.setPageSize(pageSize);
	    }
	    tItov_shop4s_user.setAccount_name(account_name);
	    Integer recordCount = tItov_shop4sService
		    .selectUserSizeByWhere(tItov_shop4s_user);
	    Integer pageCount = 0;
	    if (pageSize != -1) {
		pageCount = (recordCount + (pageSize - 1)) / pageSize;
	    }
	    arr = tItov_shop4sService.selectUserByWhere(tItov_shop4s_user);
	    Type listType = new TypeToken<ArrayList<CarModel>>() {
	    }.getType();
	    Gson gson = new Gson();
	    String json = gson.toJson(arr, listType);

	    responseBodyJson.writeNumberField("pageCount", pageCount);
	    responseBodyJson.writeNumberField("totalCount", recordCount);
	    responseBodyJson
		    .writeStringField("result", json.replace("\"", "'"));
	} catch (Exception e) {
	    // TODO: handle exception
		e.printStackTrace();
	    throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,
		    new Object[] { null });
	}
    }
}
