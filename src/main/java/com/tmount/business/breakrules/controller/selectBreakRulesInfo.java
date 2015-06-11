package com.tmount.business.breakrules.controller;
import com.tmount.util.ParamData;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.tmount.business.breakrules.platform.BreakCityListPlat;
import com.tmount.business.breakrules.platform.GetPlatformInfo;
import com.tmount.business.breakrules.service.TzdcBreakRuleCityService;
import com.tmount.business.breakrules.service.TzdcBreakRulesService;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.db.breakrules.dto.TZdcBreakruleqryFlag;
import com.tmount.db.breakrules.dto.TZdcBreakrules;
import com.tmount.db.breakrules.dto.TZdcBreakrulescitylist;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 插入违章记录信息
 * 20141128
 * @author Administrator
 *
 */
@Controller
public class selectBreakRulesInfo extends ControllerBase {
	Logger logger = Logger.getLogger(selectBreakRulesInfo.class);
	String json = "";
    @Autowired
    private TzdcBreakRulesService tbreakService;
    
    @Autowired
    private TzdcBreakRuleCityService tbreakcityService;
	@RequestMapping(value = "selectBreakRulesInfo.get")
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
		//long account_id = ParamData.getLong(requestParam.getBodyNode(), "account_id");
		String car_plate_num = ParamData.getString(requestParam.getBodyNode(), "car_plate_num");
		String car_carcase_num = ParamData.getString(requestParam.getBodyNode(), "car_carcase_num","");
		String car_engine_num = ParamData.getString(requestParam.getBodyNode(), "car_engine_num","");
		String city_name = ParamData.getString(requestParam.getBodyNode(), "city_name","");
		//查询全部车辆信息
		TZdcBreakruleqryFlag carvo= tbreakService.qryBreak(car_plate_num);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		if(null != carvo)
		{
			String updateTime = carvo.getUpdatetime();
			try {
				
				long time1 = sdf.parse(updateTime).getTime();
				long time2 = sdf.parse(sdf.format(new Date())).getTime();
				//表示此车牌号当天没有被查询过插入完成后更新车牌查询的时间
				if(time1<time2)
				{

							//根据省份和城市名称获取是否需要发动机参数或者车架号蚕食
							List<TZdcBreakrulescitylist> cityList = tbreakcityService.selectByCityName(city_name);
							//List<TZdcBreakrulescitylist> cityList = tbreakcityService.selectByPcodeCity(breakCity);
							if(null !=cityList&&cityList.size()>0)
							{
								TZdcBreakrulescitylist breakCityList=  cityList.get(0);
								String engineno = "";  //发动机号
								String classno = "";   //车架号
								if(null !=breakCityList)
								{
									
									int engine = breakCityList.getEngine();  //是否需要发动机
									int carnume = breakCityList.getClasst();   //是否需要车架号
									int engineno1 = breakCityList.getEngineno();  //需要几位发动机号
									int classno1 = breakCityList.getClassno();  //需要几位车架号
									if(engine==1)
									{
										if(engineno1==0)
										{
											engineno = car_engine_num;
											
										}else
										{
											engineno = car_engine_num.substring(car_engine_num.length()-engineno1, car_engine_num.length());
										}
										
									}
									if(carnume ==1)
									{
										if(classno1==0)
										{
											classno = car_carcase_num;
										}
										else
										{
											classno = car_carcase_num.substring(car_carcase_num.length()-classno1, car_carcase_num.length());
										}
										
									}
									
								}
								
								//调用api
								String jsonResult = GetPlatformInfo.getInfo(breakCityList.getCityCode(),engineno, classno, car_plate_num);
								ObjectMapper objectMapper = new ObjectMapper();
								if (StringUtils.isNotEmpty(jsonResult)) {
									JsonNode jsonNode = objectMapper.readTree(jsonResult);
									if ("200".equals(jsonNode.get("resultcode").textValue())) {
										JsonNode jsonNodeResult = jsonNode.get("result");
										if (jsonNodeResult != null) {
											String provincet = jsonNodeResult.get("province").textValue();
											String city = jsonNodeResult.get("city").textValue();
											String hphmt =  jsonNodeResult.get("hphm").asText();
											JsonNode jsonNodeList= jsonNodeResult.get("lists");
											responseBodyJson.writeArrayFieldStart("breakRuleAll");
											for (int i = 0; i < jsonNodeList.size(); i++) {
												responseBodyJson.writeStartObject();
												JsonNode jsonList = jsonNodeList.get(i);
												String date = jsonList.get("date").textValue();
												String area = jsonList.get("area").textValue();
												String act = jsonList.get("act").textValue();
												String code = jsonList.get("code").textValue();
												String fen = jsonList.get("fen").textValue();
												String money = jsonList.get("money").textValue();
												String handled = jsonList.get("handled").textValue();
												responseBodyJson.writeStringField("act", act);
												responseBodyJson.writeStringField("area", area);
												responseBodyJson.writeStringField("city", city);
												responseBodyJson.writeStringField("code", code);
												responseBodyJson.writeStringField("date", date);
												responseBodyJson.writeStringField("fen", fen);
												responseBodyJson.writeStringField("handled", handled);
												responseBodyJson.writeStringField("hphmt", hphmt);
												responseBodyJson.writeStringField("money", money);
												TZdcBreakrules tzdcBreakRules = new TZdcBreakrules();
												tzdcBreakRules.setAct(act);
												tzdcBreakRules.setArea(area);
												tzdcBreakRules.setCity(city);
												tzdcBreakRules.setCode(code);
												tzdcBreakRules.setDate(date);
												tzdcBreakRules.setFen(fen);
												tzdcBreakRules.setHandled(handled);
												tzdcBreakRules.setHphm(hphmt);
												tzdcBreakRules.setMoney(money);
												tzdcBreakRules.setProvince(provincet);
												int num = tbreakService.isExistBreakRule(tzdcBreakRules);
												if(num==0){
												try{
													//插入违章信息
													int count = tbreakService.insert(tzdcBreakRules);
													
												}catch(Exception e)
												{
													throw new ShopBusiException(
															ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
															new Object[] { "违规信息插入失败" });
													
												}
												}
												responseBodyJson.writeEndObject();
											}
											responseBodyJson.writeEndArray();
										
										} else {
											throw new ShopBusiException(
													ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
													new Object[] { "得不到result列表" });
										}

									} else {
										throw new ShopBusiException(
												ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
												new Object[] { jsonNode.get("reason") });
									}
								}
							
							}
							
					//已经查询过则更新此车牌查询的信息
					TZdcBreakruleqryFlag breakQryFlag = new TZdcBreakruleqryFlag();
					breakQryFlag.setCarPalteNum(car_plate_num);
					breakQryFlag.setUpdatetime(sdf.format(new Date()));
					tbreakService.updateQryRecord(breakQryFlag);
				}else
				{
					List<TZdcBreakrules> ruleList = tbreakService.selectByHphm(car_plate_num);
					
					responseBodyJson.writeArrayFieldStart("breakRuleAll");
					for(TZdcBreakrules breakrule:ruleList)
					{
						responseBodyJson.writeStartObject();
						responseBodyJson.writeStringField("act", breakrule.getAct());
						responseBodyJson.writeStringField("area", breakrule.getArea());
						responseBodyJson.writeStringField("city", breakrule.getCity());
						responseBodyJson.writeStringField("code", breakrule.getCode());
						responseBodyJson.writeStringField("date", breakrule.getDate());
						responseBodyJson.writeStringField("fen", breakrule.getFen());
						responseBodyJson.writeStringField("handled", breakrule.getHandled());
						responseBodyJson.writeStringField("hphmt", breakrule.getHphm());
						responseBodyJson.writeStringField("money", breakrule.getMoney());
						responseBodyJson.writeEndObject();
						
					}
					responseBodyJson.writeEndArray();
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else
		{

					//根据省份和城市名称获取是否需要发动机参数或者车架号蚕食
					List<TZdcBreakrulescitylist> cityList = tbreakcityService.selectByCityName(city_name);
					//List<TZdcBreakrulescitylist> cityList = tbreakcityService.selectByPcodeCity(breakCity);
					if(null !=cityList&&cityList.size()>0)
					{
						TZdcBreakrulescitylist breakCityList=  cityList.get(0);
						String engineno = "";  //发动机号
						String classno = "";   //车架号
						if(null !=breakCityList)
						{
							
							int engine = breakCityList.getEngine();  //是否需要发动机
							int carnume = breakCityList.getClasst();   //是否需要车架号
							int engineno1 = breakCityList.getEngineno();  //需要几位发动机号
							int classno1 = breakCityList.getClassno();  //需要几位车架号
							if(engine==1)
							{
								if(engineno1==0)
								{
									engineno =car_engine_num;
									
								}else
								{
									engineno = car_engine_num.substring(car_engine_num.length()-engineno1, car_engine_num.length());
								}
								
							}
							if(carnume ==1)
							{
								if(classno1==0)
								{
									classno = car_carcase_num;
								}
								else
								{
									classno = car_carcase_num.substring(car_carcase_num.length()-classno1, car_carcase_num.length());
								}
								
							}
							
						}
						
						//调用api
						String jsonResult = GetPlatformInfo.getInfo(breakCityList.getCityCode(),engineno, classno, car_plate_num);
						ObjectMapper objectMapper = new ObjectMapper();
						if (StringUtils.isNotEmpty(jsonResult)) {
							JsonNode jsonNode = objectMapper.readTree(jsonResult);
							if ("200".equals(jsonNode.get("resultcode").textValue())) {
								JsonNode jsonNodeResult = jsonNode.get("result");
								if (jsonNodeResult != null) {
									String provincet = jsonNodeResult.get("province").textValue();
									String city = jsonNodeResult.get("city").textValue();
									String hphmt =  jsonNodeResult.get("hphm").asText();
									JsonNode jsonNodeList= jsonNodeResult.get("lists");

									
									responseBodyJson.writeArrayFieldStart("breakRuleAll");
									for (int i = 0; i < jsonNodeList.size(); i++) {
										responseBodyJson.writeStartObject();
										JsonNode jsonList = jsonNodeList.get(i);
										String date = jsonList.get("date").textValue();
										String area = jsonList.get("area").textValue();
										String act = jsonList.get("act").textValue();
										String code = jsonList.get("code").textValue();
										String fen = jsonList.get("fen").textValue();
										String money = jsonList.get("money").textValue();
										String handled = jsonList.get("handled").textValue();
										responseBodyJson.writeStringField("act", act);
										responseBodyJson.writeStringField("area", area);
										responseBodyJson.writeStringField("city", city);
										responseBodyJson.writeStringField("code", code);
										responseBodyJson.writeStringField("date", date);
										responseBodyJson.writeStringField("fen", fen);
										responseBodyJson.writeStringField("handled", handled);
										responseBodyJson.writeStringField("hphmt", hphmt);
										responseBodyJson.writeStringField("money", money);
										TZdcBreakrules tzdcBreakRules = new TZdcBreakrules();
										tzdcBreakRules.setAct(act);
										tzdcBreakRules.setArea(area);
										tzdcBreakRules.setCity(city);
										tzdcBreakRules.setCode(code);
										tzdcBreakRules.setDate(date);
										tzdcBreakRules.setFen(fen);
										tzdcBreakRules.setHandled(handled);
										tzdcBreakRules.setHphm(hphmt);
										tzdcBreakRules.setMoney(money);
										tzdcBreakRules.setProvince(provincet);
										//根据车牌号和时间查询是否存在此违章信息如果存在则不插入如果不存在则插入
										int num = tbreakService.isExistBreakRule(tzdcBreakRules);
										if(num==0)
										{
										try{
											//插入违章信息
											int count = tbreakService.insert(tzdcBreakRules);
											
										}catch(Exception e)
										{
											throw new ShopBusiException(
													ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
													new Object[] { "违规信息插入失败" });
											
										}
										}
										responseBodyJson.writeEndObject();
									}
									responseBodyJson.writeEndArray();
								} else {
									throw new ShopBusiException(
											ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
											new Object[] { "得不到result列表" });
								}

							} else {
								throw new ShopBusiException(
										ShopBusiErrorBundle.BREAKRULESPlATFORM_ERROR,
										new Object[] { jsonNode.get("reason") });
							}
						}
					
					}
					
					
			
		    //如果此车牌在表中没有记录过则插入
			TZdcBreakruleqryFlag breakQryFlag = new TZdcBreakruleqryFlag();
			breakQryFlag.setCarPalteNum(car_plate_num);
			breakQryFlag.setUpdatetime(sdf.format(new Date()));
			tbreakService.insertqryBreak(breakQryFlag);
		}
	}

}
