package com.tmount.business.breakrules.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.breakrules.platform.GetPlatformInfo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class GetBreakRulesCityList extends ControllerBase {
	Logger logger = Logger.getLogger(GetBreakRulesCityList.class);
	String json = "";

	@RequestMapping(value = "breakrulescitylist.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		String province = ParamData.getString(requestParam.getBodyNode(),
				"province");// 省份简写，如：ZJ、JS
		String city = ParamData.getString(requestParam.getBodyNode(), "city");// 地市名称
		// 调用聚合数据的接口
		json = GetPlatformInfo.getCityList(province);
		System.out.println("~~~" + json);
		// 解析json，看看是否有对应的地市名称。如果有则返回对应的json值
		ObjectMapper objectMapper = new ObjectMapper();

		String city_code = "";
		String abbr = "";
		String engine = "";
		String engineno = "";
		String classa = "";
		String classname = "";
		String classno = "";
		String regist = "";
		String registno = "";

		if (StringUtils.isNotEmpty(json)) {
			JsonNode jsonNode = objectMapper.readTree(json);
			if ("200".equals(jsonNode.get("resultcode").textValue())) {
				JsonNode jsonNodeResult = jsonNode.get("result").get(province);
				if (jsonNodeResult != null) {
					JsonNode citysJsonNode = jsonNodeResult.get("citys");

					for (int i = 0; i < citysJsonNode.size(); i++) {
						JsonNode json_sub_content = citysJsonNode.get(i);
						String city_name = json_sub_content.get("city_name")
								.textValue();
						if (StringUtils.isNotEmpty(city)
								&& (StringUtils.isNotEmpty(city_name))) {
							if (city.equals(city_name)) {
								city_code = json_sub_content.get("city_code")
										.textValue();
								abbr = json_sub_content.get("abbr")
										.textValue();
								engine = json_sub_content.get("engine")
										.textValue();
								engineno = json_sub_content.get("engineno")
										.textValue();
								classa = json_sub_content.get("classa")
										.textValue();
								classname = json_sub_content.get("class")
										.textValue();
								classno = json_sub_content.get("classno")
										.textValue();
								regist = json_sub_content.get("regist")
										.textValue();
								registno = json_sub_content.get("registno")
										.textValue();
								break;
							}
						}
					}
					responseBodyJson.writeStringField("city_code", city_code);
					responseBodyJson.writeStringField("abbr", abbr);
					responseBodyJson.writeStringField("engine", engine);
					responseBodyJson.writeStringField("engineno", engineno);
					responseBodyJson.writeStringField("classa", classa);
					responseBodyJson.writeStringField("classname", classname);
					responseBodyJson.writeStringField("classno", classno);
					responseBodyJson.writeStringField("regist", regist);
					responseBodyJson.writeStringField("registno", registno);
					/*
					 * Iterator it = citysJsonNode.iterator(); while
					 * (it.hasNext()) { JsonNode json_sub_content = (JsonNode)
					 * it.next(); String city_name =
					 * json_sub_content.get("city_name").textValue();
					 * if(StringUtils.isNotEmpty(city)&&(StringUtils.isNotEmpty(city_name))){
					 * if(city.equals(city_name)){ String city_code =
					 * json_sub_content.get("city_code").textValue(); String
					 * abbr = json_sub_content.get("city_code").textValue();
					 * String engine =
					 * json_sub_content.get("engine").textValue(); String
					 * engineno = json_sub_content.get("engineno").textValue();
					 * String classa =
					 * json_sub_content.get("classa").textValue(); String Class =
					 * json_sub_content.get("class").textValue(); String classno =
					 * json_sub_content.get("classno").textValue(); String
					 * regist = json_sub_content.get("regist").textValue();
					 * String registno =
					 * json_sub_content.get("registno").textValue();
					 *  } } }
					 */
				} else {
					throw new ShopBusiException(
							ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
							new Object[] { "调用违章接口未获取到对应的省市列表" });
				}

			} else {
				throw new ShopBusiException(
						ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
						new Object[] { "调用违章接口获取城市列表出错" });
			}
		}
	}

}
