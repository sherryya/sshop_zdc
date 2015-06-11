package com.tmount.business.host.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 更新主播信息
 * 
 * @author
 * 
 */
@Controller
public class UpdateHostInfoController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostUserService zdcHostService;

	@RequestMapping(value = "updateHostinfo.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		int id = ParamData.getInt(requestParam.getBodyNode(), "id");
		String name = ParamData.getString(requestParam.getBodyNode(), "name");
		String sex = ParamData.getString(requestParam.getBodyNode(), "sex");
		String birthday = ParamData.getString(requestParam.getBodyNode(), "birthday");
		String school = ParamData.getString(requestParam.getBodyNode(), "school");
		String specialty = ParamData.getString(requestParam.getBodyNode(), "specialty");
		String specDate = ParamData.getString(requestParam.getBodyNode(), "specDate");
		String native_place = ParamData.getString(requestParam.getBodyNode(), "native_place");
		String nation = ParamData.getString(requestParam.getBodyNode(), "nation");
		String telphone = ParamData.getString(requestParam.getBodyNode(), "telphone");
		String email = ParamData.getString(requestParam.getBodyNode(), "email");
		String address = ParamData.getString(requestParam.getBodyNode(), "address");
		String height = ParamData.getString(requestParam.getBodyNode(), "height");
		String weight = ParamData.getString(requestParam.getBodyNode(), "weight");
		String pic = ParamData.getString(requestParam.getBodyNode(), "pic");
		String createDate = ParamData.getString(requestParam.getBodyNode(), "createDate");
		String introduce = ParamData.getString(requestParam.getBodyNode(), "introduce");
		String grade = ParamData.getString(requestParam.getBodyNode(), "grade");
		String prell = ParamData.getString(requestParam.getBodyNode(), "prell");
		String nickname = ParamData.getString(requestParam.getBodyNode(), "nickname");
		TZdcHostUser tZdcHostUser = new TZdcHostUser();
		tZdcHostUser.setId(id);
		tZdcHostUser.setAddress(address);
		tZdcHostUser.setBirthday(birthday);
		tZdcHostUser.setCreatedate(createDate);
		tZdcHostUser.setEmail(email);
		tZdcHostUser.setHeight(height);
		tZdcHostUser.setName(name);
		tZdcHostUser.setNation(nation);
		tZdcHostUser.setNativePlace(native_place);
		tZdcHostUser.setPic(pic);
		tZdcHostUser.setSchool(school);
		tZdcHostUser.setSex(sex);
		tZdcHostUser.setSpecdate(specDate);
		tZdcHostUser.setSpecialty(specialty);
		tZdcHostUser.setTelphone(telphone);
		tZdcHostUser.setWeight(weight);
		tZdcHostUser.setIntroduce(introduce);
		tZdcHostUser.setGrade(grade);
		tZdcHostUser.setPrell(prell);
		tZdcHostUser.setNickname(nickname);
		try{
			logger.info("updateHostinfo.upd 更新主播开始");
			int count = zdcHostService.updateAccountId(tZdcHostUser);
			logger.info("updateHostinfo.upd 更新主播结束");
			responseBodyJson.writeNumberField("result", count);
		}catch(Exception e)
		{
			logger.info("updateHostAccountId更新主播accountid");
		}
		
	}
}
