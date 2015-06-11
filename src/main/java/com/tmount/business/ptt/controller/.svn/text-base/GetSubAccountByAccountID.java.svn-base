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
 * 得到VOIP账号的当前
 * @author dell
 *
 */
@Controller
public class GetSubAccountByAccountID extends ControllerBase {
	@Autowired
	private PttSubaccountService pttSubaccountService;
	@RequestMapping(value = "subAccountByAccountID.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String account_id = ParamData.getString(requestParam.getBodyNode(), "account_id");//  账号ID
		TItovPttSubaccount tItovPttSubaccount=new TItovPttSubaccount();
		tItovPttSubaccount=pttSubaccountService.selectByAccount_id(Long.valueOf(account_id));
        if(tItovPttSubaccount!=null)
        {
        	responseBodyJson.writeNumberField("account_id", tItovPttSubaccount.getAccountId());
        	responseBodyJson.writeStringField("agentstate", tItovPttSubaccount.getAgentstate());
        }
        else
        {
        	throw new ShopBusiException(ShopBusiErrorBundle.VOIP_ERROR,null);
        }
	}
}
