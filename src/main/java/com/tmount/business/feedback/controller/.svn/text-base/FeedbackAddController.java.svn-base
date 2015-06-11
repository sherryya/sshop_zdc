package com.tmount.business.feedback.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.feedback.service.FeedbackService;
import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
//添加车主的意见反馈
@Controller
public class FeedbackAddController extends ControllerBaseByLogin {
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(value = "feedback.Add")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		
		long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");//用户ID
		int feedback_type = ParamData.getInt(requestParam.getBodyNode(), "feedback_type");//类型
		String feedback_info = ParamData.getString(requestParam.getBodyNode(), "feedback_info");//反馈信息
		String file_path = ParamData.getString(requestParam.getBodyNode(), "file_path");//反馈信息录音文件路径
		TZdcFeedback tZdcFeedback=new TZdcFeedback();
		try {
			tZdcFeedback.setAccountId(account_id);
			tZdcFeedback.setFeedbackInfo(feedback_info);
			tZdcFeedback.setFeedbackType(feedback_type);
			tZdcFeedback.setIsDeal(0);
			tZdcFeedback.setFile_path(file_path);
			int ret=feedbackService.insert(tZdcFeedback);
			responseBodyJson.writeNumberField("result", ret);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseBodyJson.writeNumberField("result", 0);
			return;
		}
	}
}
