package com.tmount.business.host.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class SelectHostPicController extends ControllerBase {
	Logger logger = Logger.getLogger(SelectHostPicController.class);
	@Autowired
	private TItovHostUserService zdcHostService;

	@RequestMapping(value = "selectHostPic.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		int pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize"); // 每页多少条
		Integer pageNo = new Integer(ParamData.getInt(
				requestParam.getBodyNode(), "pageNum", -1));// 第几页
		System.out.println("pageSize:" + pageSize);
		System.out.println("pageNo:" + pageNo);
		logger.info("selectHostPic.get 查询主播照片列表");
		TZdcHostUser tZdcHostUser = new TZdcHostUser();
		if (pageNo != -1) {
			int startLimit = (pageNo - 1) * pageSize;
			tZdcHostUser.setStartLimit(startLimit);
		}
		tZdcHostUser.setPageSize(pageSize);
		//主播类型为导播
		tZdcHostUser.setHostType("1");
		List<TZdcHostUser> hostList = zdcHostService.selectAll(tZdcHostUser);
		//
		List<TZdcHostUser> list =new ArrayList<TZdcHostUser>() ;
		for(TZdcHostUser hostUser:hostList)
		{
			if(hostUser.getVoip_status()==0)
			{
				list.add(hostUser);
			}
		}
		for(TZdcHostUser hostUser:hostList)
		{
			if(hostUser.getVoip_status()==1)
			{
				list.add(hostUser);
			}
		}
		for(TZdcHostUser hostUser:hostList)
		{
			if(hostUser.getVoip_status()==2)
			{
				list.add(hostUser);
			}
		}
		for(TZdcHostUser hostUser:hostList)
		{
			if(hostUser.getVoip_status()==-1)
			{
				list.add(hostUser);
			}
		}
		for(TZdcHostUser hostUser:hostList)
		{
			if(hostUser.getVoip_status()!=0&&hostUser.getVoip_status()!=-1&&hostUser.getVoip_status()!=1&&hostUser.getVoip_status()!=2)
			{
				list.add(hostUser);
			}
		}
		responseBodyJson.writeArrayFieldStart("hostPicList");
		for (int t = 0; t < list.size(); t++) {
			responseBodyJson.writeStartObject();
			if (null != list.get(t).getPic()) {
				String tt = list.get(t).getPic();
				if (tt.contains(";")) {
					String[] pic = tt.split(";");
					if (pic.length > 1) {
						responseBodyJson.writeStringField("middlePic", pic[0]);// 主播照片
					} else {
						// 默认一个图片路径
						responseBodyJson.writeStringField("middlePic","meinv.png");
					}
				} else {
//					String pstr = "([^\"|[\u4e00-\u9fa5]]+)";
//					Pattern p = Pattern.compile(pstr);
//					Matcher m = p.matcher(tt);
//					if (m.find()) {
//						responseBodyJson.writeStringField("middlePic",native2Ascii(tt).replace("\\\\", "\\"));// 主播照片
//					} else {
						responseBodyJson.writeStringField("middlePic", tt);// 主播照片
//					}
				}
			}
			responseBodyJson.writeNumberField("id", list.get(t).getId());// 主播ID
			responseBodyJson.writeStringField("nickname", list.get(t).getNickname());// 主播ID
			responseBodyJson.writeNumberField("voip_status", list.get(t)
					.getVoip_status());// 主播在线状态 -1:离线 0：在线 1:通话中 2:暂停
			responseBodyJson.writeEndObject();
		}

		responseBodyJson.writeEndArray();
	}

	public String native2Ascii(String str) {
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(char2Ascii(chars[i]));
		}
		return sb.toString();
	}

	/**
	 * Native character to ascii string.
	 * 
	 * @param c
	 *            native character
	 * @return ascii string
	 */
	private String char2Ascii(char c) {
		if (c > 255) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\u");
			int code = (c >> 8);
			String tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			code = (c & 0xFF);
			tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			return sb.toString();
		} else {
			return Character.toString(c);
		}
	}
}
