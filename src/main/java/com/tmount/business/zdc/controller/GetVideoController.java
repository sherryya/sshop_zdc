package com.tmount.business.zdc.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.business.user.service.UserService;
import com.tmount.business.zdc.service.NewsService;
import com.tmount.business.zdc.service.VideoService;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.manage.dto.TItov_personal_manage;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.db.zdc.dto.NewDetailSub;
import com.tmount.db.zdc.dto.NewsRollSub;
import com.tmount.db.zdc.dto.VideoSub;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetVideoController extends ControllerBaseByLogin {
    Logger logger = Logger.getLogger(GetVideoController.class.getName());
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "Video.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson, HttpServletRequest request)
	    throws ShopException, JsonGenerationException, IOException {
	// 左侧分类参数
	String CID = ParamData.getString(requestParam.getBodyNode(), "cid",
		"NULL");
	// 上部分类参数
	String MID = ParamData.getString(requestParam.getBodyNode(), "mid",
		"NULL");
	// 关键字查询参数
	String keyword = ParamData.getString(requestParam.getBodyNode(),
		"keyword", "NULL");
	// 后台修改查询单条数据参数
	int select = ParamData.getInt(requestParam.getBodyNode(), "select", -1);
	// 页数 条数
	int MUN = Integer.parseInt(ParamData.getString(
		requestParam.getBodyNode(), "num", "-1"));// 10
	int PAGE = Integer.parseInt(ParamData.getString(
		requestParam.getBodyNode(), "page", "-1"));// 1
	//
	logger.info("开始处理服务调用");
	VideoSub videoSub = new VideoSub();
	List<VideoSub> result = null;
	if (select != -1) {
	    videoSub.setId(select);
	    result = videoService.selectSingle(videoSub);
	    Type listType = new TypeToken<ArrayList<VideoSub>>() {
	    }.getType();
	    Gson gson = new Gson();
	    String json = gson.toJson(result, listType);
	    responseBodyJson.writeStringField("list", json.replace("\"", "'"));
	} else {
	    videoSub.setStartLimit((PAGE - 1) * MUN);
	    videoSub.setPageSize(MUN);
	    if (CID.equals("NULL") && MID.equals("NULL")
		    && keyword.equals("NULL")) {
		result = videoService.selectAll(videoSub);
		Type listType = new TypeToken<ArrayList<VideoSub>>() {
		}.getType();
		Gson gson = new Gson();
		String json = gson.toJson(result, listType);
		responseBodyJson.writeStringField("list",
			json.replace("\"", "'"));
	    } else {
		if (!CID.equals("NULL")) {
		    videoSub.setType_classify_l(CID);
		    result = videoService.selectByType(videoSub);
		}
		if (!MID.equals("NULL")) {
		    videoSub.setType_category_t(MID);
		    result = videoService.selectByType(videoSub);
		}
		if (!keyword.equals("NULL")) {
		    videoSub.setTitle(keyword);
		    result = videoService.selectByTitle(videoSub);
		}
		responseBodyJson.writeFieldName("list");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(responseBodyJson, result);
	    }

	}

    }
}
