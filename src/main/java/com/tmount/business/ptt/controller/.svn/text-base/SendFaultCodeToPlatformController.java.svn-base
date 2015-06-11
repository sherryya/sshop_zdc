package com.tmount.business.ptt.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.ptt.service.PttSubaccountService;
import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 车机发送故障码到平台
 * @author dell
 *
 */
@Controller
public class SendFaultCodeToPlatformController extends ControllerBase {
	@Autowired
	private PttSubaccountService pttSubaccountService;
	@RequestMapping(value = "SendFaultCodeToPlatform.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String IMEI = ParamData.getString(requestParam.getBodyNode(), "IMEI");// IMEI ID
		String faultCode = ParamData.getString(requestParam.getBodyNode(), "faultCode"); //故障码
		
		
        /*if(tItovPttSubaccount!=null)
        {
        	responseBodyJson.writeNumberField("account_id", tItovPttSubaccount.getAccountId());
        	responseBodyJson.writeStringField("agentstate", tItovPttSubaccount.getAgentstate());
        }
        else
        {
        	throw new ShopBusiException(ShopBusiErrorBundle.VOIP_ERROR,null);
        }*/
	}
}
