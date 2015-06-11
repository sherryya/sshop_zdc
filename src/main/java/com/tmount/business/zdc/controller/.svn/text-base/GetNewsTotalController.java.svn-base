package com.tmount.business.zdc.controller;

import java.io.IOException;
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
import com.tmount.business.zdc.service.NewTotalService;
import com.tmount.business.zdc.service.NewsService;
import com.tmount.db.zdc.dto.NewDetailSub;
import com.tmount.db.zdc.dto.NewsRollSub;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetNewsTotalController extends ControllerBaseByLogin {
    Logger logger = Logger.getLogger(GetNewsRollController.class.getName());
    private final static String[] keys = { "新闻中心", "博客频道", "体育频道", "科技频道",
	    "财经频道", "影音娱乐", "读书频道", "女性频道", "男性频道", "军事频道", "汽车新闻", "教育频道",
	    "地产频道", "游戏频道", "星座频道" };
    @Autowired
    private NewTotalService newTotalService;

    @RequestMapping(value = "NewsTotal.get")
    @Override
    public void doRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	sendData(request, response);
    }

    @Override
    protected void doService(RequestParam requestParam,
	    JsonGenerator responseBodyJson, HttpServletRequest request)
	    throws ShopException, JsonGenerationException, IOException {
	String ID = ParamData.getString(requestParam.getBodyNode(), "CID");
	if (ID.contains("F")) {
	    int id = Integer.parseInt(ID.substring(1));
	    String type = keys[id];
	    List<NewsRollSub> newsRollSub = newTotalService
		    .selectNewInfoSimple(type);
	    responseBodyJson.writeNumberField("Size", newsRollSub.size());
	    responseBodyJson.writeFieldName("Object");
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.writeValue(responseBodyJson, newsRollSub);
	    // 根据传递的ID判断需要的新闻类型
	} else {
	    // 根据传递的ID判断获取新闻的位置
	    List<NewDetailSub> newsRollSub = newTotalService
		    .selectNewInfo(Integer.parseInt(ID));
	    responseBodyJson.writeFieldName("Object");
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.writeValue(responseBodyJson, newsRollSub);
	}
    }

}
