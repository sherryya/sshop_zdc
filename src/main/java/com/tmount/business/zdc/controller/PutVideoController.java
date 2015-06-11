package com.tmount.business.zdc.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class PutVideoController extends ControllerBaseByLogin {
    Logger logger = Logger.getLogger(PutVideoController.class.getName());
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "Video.put")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson, HttpServletRequest request)
	    throws ShopException, JsonGenerationException, IOException {
	VideoSub sub = new VideoSub();
	//时长
	sub.setDuration(ParamData.getString(requestParam.getBodyNode(), "VideoSize"));
	sub.setPoint("0");
	//增加时间(获取当前时间的字符串)
	sub.setTime_add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	//标题
	sub.setTitle(ParamData.getString(requestParam.getBodyNode(),"VideoTitle"));
	//
	sub.setType_classify_l(ParamData.getString(requestParam.getBodyNode(),"leftType"));
	sub.setType_category_t(ParamData.getString(requestParam.getBodyNode(),"topType"));
	sub.setUrl_file(ParamData.getString(requestParam.getBodyNode(),"VideoUrl"));
	sub.setUrl_image(ParamData.getString(requestParam.getBodyNode(),"VideoPictureUr"));
	videoService.insertVideo(sub);
	responseBodyJson.writeStringField("result", "SUCCESS");
    }
}
