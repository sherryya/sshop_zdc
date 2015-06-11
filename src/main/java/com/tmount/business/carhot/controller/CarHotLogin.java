package com.tmount.business.carhot.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.util.dto.CommonBean;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class CarHotLogin extends ControllerBaseByLogin {
	Logger logger = Logger.getLogger(CarHotLogin.class.getName());
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalInfoService terminalService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "carHotLogin.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {	
		String server_url = "http://" + request.getServerName() + ":"+ request.getServerPort() +  "/";
		logger.info("~~~~~~~~~~~~~~~~server_url~~~~~~~~~~~~~~~~~~~~"+server_url);
		int platform = requestParam.getRequestDataHeader().getClientPlatform();// ios---10,android----11平台标示
		// 0.根据平台标示，获取最新版本号和url路径
		String telphone = ParamData.getString(requestParam.getBodyNode(),"telphone");//手机号
		UsAccount usAccout = terminalService.selectAccountIDByIMEI(telphone);   //根据手机号查询是否存在此用户
		if(usAccout ==null)
		{
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,	new Object[] { "用户不存在" });
		}
		CommonBean commonBean1 =new CommonBean();
		commonBean1.setPlatform(platform);
		commonBean1.setVer_type(Integer.valueOf(2));
		CommonBean commonBeanVersion = userService.getVersion_ter(commonBean1);
		if(null!=commonBeanVersion)
		{
		responseBodyJson.writeStringField("version", commonBeanVersion.getVersion());
		responseBodyJson.writeStringField("version_url", server_url+ commonBeanVersion.getVersionurl());
		responseBodyJson.writeNumberField("ver_important", commonBeanVersion.getVer_important());
		}
		responseBodyJson.writeNumberField("account_id", usAccout.getAccount_id());
		List<TItovCarVo>  list = carInfoService.qryCarInfoByAccountID(usAccout.getAccount_id());
		if (!list.isEmpty()) {
			for (TItovCarVo l : list) {
				responseBodyJson.writeStringField("terminal_imei", l.getTerminal_imei());
				responseBodyJson.writeStringField("url_itov", l.getUrl_itov());
				responseBodyJson.writeStringField("carPlateNumber", l.getCarPlateNumber());
				responseBodyJson.writeNumberField("is_use", l.getIs_use());
				responseBodyJson.writeStringField("strategy_type", l.getStrategyType()==null?"":l.getStrategyType());
				responseBodyJson.writeStringField("strategy_value", l.getStrategyValue()==null?"":l.getStrategyValue());
			}
		} else {
			responseBodyJson.writeStringField("terminal_imei", "");
			responseBodyJson.writeStringField("url_itov", "");
			responseBodyJson.writeStringField("carPlateNumber", "");
		}

/*		responseBodyJson.writeFieldName("carList");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(responseBodyJson, list);*/
	}
}
