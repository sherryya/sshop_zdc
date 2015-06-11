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
import com.tmount.business.manage.service.TItov_personalService;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.manage.dto.TItov_personal_manage;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 得到坐席信息
 * 
 * @author dell
 * 
 */
@Controller
public class GetItovPersonalController extends ControllerBase {
    @Autowired
    private TItov_personalService tItov_personalService;

    @RequestMapping(value = "personal.manage.get")
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
	    List<TItov_personal_manage> arr = new ArrayList<TItov_personal_manage>();
	    TItov_personal_manage tItov_personal = new TItov_personal_manage();
	    Integer account_type = ParamData.getInt(requestParam.getBodyNode(),
		    "account_type");// 账号类型
	    String account_name = ParamData.getString(
		    requestParam.getBodyNode(), "account_name");// 用户名
	    String agentstate = ParamData.getString(requestParam.getBodyNode(),
		    "agentstate");// 用户当前VOIP状态
	    Integer pageSize = new Integer(ParamData.getInt(
		    requestParam.getBodyNode(), "pageSize", -1));// 每页多少条
	    Integer startLimit = -1;
	    Integer pageNo = new Integer(ParamData.getInt(
		    requestParam.getBodyNode(), "pageNum", -1));// 第几页
	    if (pageNo != -1) {
		startLimit = (pageNo - 1) * pageSize;
		tItov_personal.setStartLimit(startLimit);
	    }
	    if (pageSize != -1) {
		tItov_personal.setPageSize(pageSize);
	    }
	    tItov_personal.setAccount_type(account_type);
	    tItov_personal.setAccount_name(account_name);
	    tItov_personal.setAgentstate(agentstate);
	    Integer recordCount = tItov_personalService
		    .selectSizeByWhere(tItov_personal);
	    Integer pageCount = 0;
	    if (pageSize != -1) {
		pageCount = (recordCount + (pageSize - 1)) / pageSize;
	    }
	    arr = tItov_personalService.selectByWhere(tItov_personal);
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
	    throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,
		    new Object[] { null });
	}
    }
}
