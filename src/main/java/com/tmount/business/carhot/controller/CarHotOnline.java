package com.tmount.business.carhot.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.TerminalInfoService;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class CarHotOnline extends ControllerBase {
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private TerminalInfoService terminalService;
	@RequestMapping(value = "carHotOnline.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		String telphone = ParamData.getString(requestParam.getBodyNode(),"telphone");//手机号
		UsAccount usAccout = terminalService.selectAccountIDByIMEI(telphone);   //根据手机号查询是否存在此用户
		if(usAccout ==null)
		{
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,	new Object[] { "用户不存在" });
		}
		// 1:准备热车  2:热车中  3：离线   
		responseBodyJson.writeNumberField("result", 1);
/*		List<TItovCarVo>  list = carInfoService.qryCarInfoByAccountID(usAccout.getAccount_id());
		responseBodyJson.writeFieldName("carList");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(responseBodyJson, list);*/
	}
}
