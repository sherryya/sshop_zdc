package com.tmount.business.breakrules.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.breakrules.platform.GetPlatformInfo;
import com.tmount.business.breakrules.service.TzdcBreakRuleCityService;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 
 * @author Administrator
 *
 */
@Controller
public class GetBreakRulesProCityList extends ControllerBase {
	Logger logger = Logger.getLogger(GetBreakRulesProCityList.class);
	String json = "";
	@Autowired
    private TzdcBreakRuleCityService zdcBreakCityListService;
	@RequestMapping(value = "breakrulesprocitylist.get")
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
		String []str = {"BJ","SH","SC","JL","LN","SD","HN","JS","HUN","SX","QH","GD","ZJ","FB","HLJ","AH","YN","XS","HAN","GZ","XJ","GS","NX","XZ","CQ"};
		// 调用聚合数据的接口
		json = GetPlatformInfo.getAllCityList();
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
			//JsonNode jsonNodeResult = jsonNode.get("result");
			if ("200".equals(jsonNode.get("resultcode").textValue())) {
				for(int j=0;j<str.length;j++)
				{
					JsonNode jsonNodeResultcity = jsonNode.get("result").get(str[j]);
					
					if (jsonNodeResultcity != null) {
						String province = jsonNodeResultcity.get("province").textValue();
						String province_code = jsonNodeResultcity.get("province_code").textValue();
						JsonNode citysJsonNode = jsonNodeResultcity.get("citys");
						TZdcBreakrulescitylist proList = new TZdcBreakrulescitylist();
						proList.setProvince(province);
						proList.setProvinceCode(province_code);
						zdcBreakCityListService.insert(proList);  //插入省份代码

						for (int i = 0; i < citysJsonNode.size(); i++) {
							JsonNode json_sub_content = citysJsonNode.get(i);
							        String city_name = json_sub_content.get("city_name")
									.textValue();
							
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
									TZdcBreakrulescitylist cityList = new TZdcBreakrulescitylist();
									cityList.setCityCode(city_code);
									cityList.setCityName(city_name);
									cityList.setClassa(Integer.valueOf(classa));
									cityList.setClassno(Integer.valueOf(classno));
									cityList.setClasst(Integer.valueOf(classname));
									cityList.setEngine(Integer.valueOf(engine));
									cityList.setEngineno(Integer.valueOf(engineno));
									cityList.setRegist(Integer.valueOf(regist));
									cityList.setRegistno(Integer.valueOf(registno));
									cityList.setProvince(province);
									cityList.setProvinceCode(province_code);
									try{
									int count = zdcBreakCityListService.insert(cityList);
									}catch(Exception e)
									{
										throw new ShopBusiException(
												ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
												new Object[] { "插入城市违规列表信息出错，城市代码为:"+ city_code});
									}
						}
				} else
				{
					logger.info(str[j]+"的列表为空");
				}

			}
			}else {
				throw new ShopBusiException(
						ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
						new Object[] { "调用违章接口获取城市列表出错" });
			}
		}
	}

}
