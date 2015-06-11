package com.tmount.business.itov.platform.inter.launch;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.itov.platform.launch.config.LaunchConfig;
import com.tmount.business.itov.platform.launch.util.LaunchNotify;
import com.tmount.db.integration.dto.CarBrand;
import com.tmount.util.saveUrlUtil;
public class App_FusionServices {
	public static int getTimeStamp() {
		long longTime = System.currentTimeMillis();
		return (int) (longTime / 1000);
	}
	/**
	 * 元征用户注册接口 1049 golo_54860_1402541440
	 * 
	 * @param platform
	 * @return
	 */
	public static String Reg_User(Integer platform) {
		String app_id = "";
		if (platform == 10) {
			app_id = LaunchConfig.app_id_iso;
		} else if (platform == 11) {
			app_id = LaunchConfig.app_id_android;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_id", app_id);
		params.put("develop_id", LaunchConfig.develop_id);
		params.put("action", "develop.reg_user");
		String md5 = LaunchNotify.getSignVeryfy2(params);
		System.out.println(System.currentTimeMillis());
		String url = LaunchConfig.url_app + "action=develop.reg_user"
				+ "&app_id=" + app_id + "&develop_id="
				+ LaunchConfig.develop_id + "&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		System.out.println(json);
		return json;
	}

	public static String getReg_User(Integer platform)
			throws JsonProcessingException, IOException {
		String json_result = App_FusionServices.Reg_User(platform);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json_result);
		if (null != jsonNode) {
			String code = jsonNode.get("code").toString();
			if (code.equalsIgnoreCase("0")) {
				JsonNode reg_json = jsonNode.get("data");
				String access_id = reg_json.get("access_id").toString();
				String access_token = reg_json.get("access_token").toString();
				return access_id + "," + access_token.replace("\"", "");
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}

	/**
	 * 得到品牌信息
	 * 
	 * @param platform
	 * @return
	 */
	public static String GetBrandID(Integer platform) {
		String app_id = "";
		String action = "forjth.getbrandid";
		if (platform == 10) {
			app_id = LaunchConfig.app_id_iso;
		} else if (platform == 11) {
			app_id = LaunchConfig.app_id_android;
		}
		String access_id = "1034";
		String access_token = "golo_82017_1402650276";
		;
		String time = String.valueOf(getTimeStamp());
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_id", app_id);
		params.put("develop_id", LaunchConfig.develop_id);
		params.put("access_id", access_id);
		params.put("access_token", access_token);
		params.put("time", time);
		params.put("action", action);
		String md5 = LaunchNotify.getSignVeryfy2(params);
		System.out.println(md5);
		String url = LaunchConfig.url_app + "action=" + action + "&app_id="
				+ app_id + "&develop_id=" + LaunchConfig.develop_id
				+ "&access_id=" + access_id + "&access_token=" + access_token
				+ "&time=" + time + "&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);

		return json;
	}

	/**
	 * 返回车辆品牌接口
	 * 
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static List<CarBrand> getBarnd() throws Exception {
		String dd = App_FusionServices.GetBrandID(11);
		String carSeriesId = "";
		String carSeriesName = "";
		String url = "";
		String url_itov = "";
		String carSeriesId_sub = "";
		List<CarBrand> list = new ArrayList<CarBrand>();
		if (dd != null) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(dd);
			Iterator it_carSeries = jsonNode.iterator();
			if (it_carSeries != null) {
				while (it_carSeries.hasNext()) {
					JsonNode carSeries = (JsonNode) it_carSeries.next();
					carSeriesId = carSeries.get("carSeriesId").textValue();
					carSeriesName = carSeries.get("carSeriesName").textValue();
					url = carSeries.get("url").textValue();
					url_itov = saveUrlUtil.getImgFromUrl(url,
							"/images/car_logo/");
					CarBrand cb = new CarBrand();
					if (!carSeriesId.equalsIgnoreCase("")) {
						cb.setCarSeriesId(Long.valueOf(carSeriesId));
						cb.setCarSeriesName(carSeriesName);
						cb.setUrl(url);
						cb.setUrl_itov(url_itov);
						cb.setParentID(0L);
						list.add(cb);
						String subList = carSeries.get("subList").toString();
						if (subList != null) {
							ObjectMapper mapper_sub = new ObjectMapper();
							JsonNode jsonNode_sub = mapper_sub
									.readTree(subList);
							if (jsonNode_sub != null) {
								Iterator it_carSeries_sub = jsonNode_sub
										.iterator();
								while (it_carSeries_sub.hasNext()) {
									JsonNode carSeries_sub = (JsonNode) it_carSeries_sub
											.next();
									carSeriesId_sub = carSeries_sub.get(
											"carSeriesId").textValue();
									carSeriesName = carSeries_sub.get(
											"carSeriesName").textValue();
									url = carSeries_sub.get("url").textValue();
									url_itov = saveUrlUtil.getImgFromUrl(url,
											"/images/car_logo/");
									CarBrand sb_sub = new CarBrand();
									if (!carSeriesId_sub.equalsIgnoreCase("")) {
										sb_sub.setCarSeriesId(Long
												.valueOf(carSeriesId_sub));
										sb_sub.setCarSeriesName(carSeriesName);
										sb_sub.setUrl(url);
										sb_sub.setUrl_itov(url_itov);
										sb_sub.setParentID(Long
												.valueOf(carSeriesId));
										list.add(sb_sub);
									}
								}
							}
						}
					}
				}
			}

		}

		return list;
	}

	/**
	 * 接头激活
	 * 
	 * @param platform
	 *            平台标志
	 * @param car_carcase_num
	 *            VIN码
	 * @param mine_car_plate_num
	 *            　　车牌号
	 * @param serial_no
	 *            　　设备SN号
	 * @param access_id
	 *            　　　
	 * @param access_token
	 * @param password
	 *            　　设备激活码
	 * @param code_id
	 *            　　　品牌码表
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static String save_car(Integer platform, String car_carcase_num,
			String mine_car_plate_num, String serial_no, String access_id,
			String access_token, String password, String code_id)
			throws JsonProcessingException, IOException {
		String app_id = "";
		String action = "forjth.save_car";
		if (platform == 10) {
			app_id = LaunchConfig.app_id_iso;
		} else if (platform == 11) {
			app_id = LaunchConfig.app_id_android;
		}
		/*
		 * String car_carcase_num="LFV3A23C4D3089208"; String
		 * mine_car_plate_num="A6P771"; String serial_no="967790005197"; String
		 * access_id="1034"; String access_token="golo_82017_1402650276"; String
		 * password="41158289"; String code_id="172";
		 */
		String time = String.valueOf(getTimeStamp());
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", action);
		params.put("app_id", app_id);
		params.put("develop_id", LaunchConfig.develop_id);
		params.put("access_id", access_id);
		params.put("access_token", access_token);
		params.put("car_carcase_num", car_carcase_num);
		params.put("mine_car_plate_num", mine_car_plate_num);
		params.put("serial_no", serial_no);
		params.put("password", password);
		params.put("code_id", code_id);
		params.put("time", time);
		String md5 = LaunchNotify.getSignVeryfy2(params);
		System.out.println(md5);
		String   mine_car_plate_num1 = URLEncoder.encode(mine_car_plate_num, "UTF-8");
		String url = LaunchConfig.url_app + "action=" + action + "&app_id="
				+ app_id + "&develop_id=" + LaunchConfig.develop_id
				+ "&access_id=" + access_id + "&access_token=" + access_token
				+ "&time=" + time + "&car_carcase_num=" + car_carcase_num
				+ "&mine_car_plate_num=" + mine_car_plate_num1 + "&serial_no="
				+ serial_no + "&password=" + password + "&code_id=" + code_id
				+ "&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		ObjectMapper mapper_sub = new ObjectMapper();
		JsonNode jsonNode_sub = mapper_sub.readTree(json);
		System.out.println(jsonNode_sub.get("code").asText());
		System.out.println("json:" + json);
		return jsonNode_sub.get("code").asText();
	}

	/**
	 * 车辆检测报告 查询任意区间内的
	 * 
	 * @param platform
	 * @param devicesn
	 * @param car_id
	 * @param start_date
	 * @param end_date
	 * @param targetpage
	 * @param pagesize
	 * @return
	 */

	public static String test_report_ymd2(String devicesn, String start_date,
			String end_date, String targetpage, String pagesize) {
		String action = "vehicle_medical_report_service.get_medical_reports_by_page";
		String time = String.valueOf(getTimeStamp());
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", action);
		params.put("develop_id", LaunchConfig.develop_id);
		params.put("devicesn", devicesn);
		params.put("end_date", end_date);
		params.put("start_date", start_date);
		params.put("targetpage", targetpage);
		params.put("pagesize", pagesize);
		params.put("time", time);
		String md5 = LaunchNotify.getSignVeryfy2(params);
		System.out.println(md5);
		String url = LaunchConfig.url_app + "action=" + action +

		"&develop_id=" + LaunchConfig.develop_id + "&devicesn=" + devicesn
				+ "&end_date=" + end_date + "&start_date=" + start_date
				+ "&targetpage=" + targetpage + "&pagesize=" + pagesize
				+ "&time=" + time + "&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}

	/**
	 * 车辆检测报告 查询是最后一条 最新的
	 * 
	 * @param platform
	 * @param devicesn
	 *            设备序列号
	 * @param car_id
	 *            车牌号
	 * @return
	 */
	public static String test_report_last(String devicesn) {
		String action = "vehicle_medical_report_service.get_latest_medical_report";
		String time = String.valueOf(getTimeStamp());
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", action);
		params.put("develop_id", LaunchConfig.develop_id);
		params.put("devicesn", devicesn);
		params.put("time", time);
		String md5 = LaunchNotify.getSignVeryfy2(params);
		System.out.println(md5);
		String url = LaunchConfig.url_app + "action=" + action + "&develop_id="
				+ LaunchConfig.develop_id + "&devicesn=" + devicesn + "&time="
				+ time + "&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
    //http://golo.test.x431.com:8008/open/?action=vehicle_medical_report_service.get_medical_report_by_id&develop_id=10003&id=1&time=1407139864&sign=02fd4b82fdcf86c9b6714d853a995d54
	/**
	 * 通过检测报告得到检测报告的详细信息
	 * @param id
	 * @return
	 */
	public static String test_reportByID(String id) {
		String action = "vehicle_medical_report_service.get_medical_report_by_id";
		String time = String.valueOf(getTimeStamp());
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", action);
		params.put("develop_id", LaunchConfig.develop_id);
		params.put("id", id);
		params.put("time", time);
		String md5 = LaunchNotify.getSignVeryfy2(params);
		System.out.println(md5);
		String url = LaunchConfig.url_app + "action=" + action + "&develop_id="
				+ LaunchConfig.develop_id + "&id=" + id + "&time="
				+ time + "&sign=" + md5;
		System.out.println(url);
		String json = LaunchNotify.checkUrl(url);
		return json;
	}
	public static void main(String[] args) throws Exception {
	
	/*	// String ret=App_FusionServices.test_report_last("967790005181");
	    	String ret = App_FusionServices.test_report_ymd2("967790005181",	"2014-06-01", "2014-08-05", "1", "30");
		//String ret=App_FusionServices.test_reportByID("46864");
		 ObjectMapper objectmappercontent = new ObjectMapper();
		 JsonNode jsonNodeContent = objectmappercontent.readTree(ret);
		 System.out.println(jsonNodeContent.get("msg"));
	     System.out.println(jsonNodeContent.get("code"));
		 System.out.println(jsonNodeContent.get("data"));*/
		
		
		
/*		  String car_carcase_num="11111111111111111"; 
		  String mine_car_plate_num="黑A6P771"; 
		
		  String serial_no="967790006571"; 
		  String  access_id="19208"; 
		  String access_token="golo_46887_1408410029"; 
		  String password="45960855"; 
		  String code_id="42";
		  String ret=App_FusionServices.save_car(11,  car_carcase_num, mine_car_plate_num,  serial_no,  access_id, access_token,  password,  code_id);
		  System.out.println(ret);*/
		
		String dd= App_FusionServices.GetBrandID(11);
		System.out.println(dd);
	}

}
