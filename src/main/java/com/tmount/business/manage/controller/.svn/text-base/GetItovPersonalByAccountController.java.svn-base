package com.tmount.business.manage.controller;
import java.io.IOException;
import java.lang.reflect.Type;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.manage.service.TItov_personalService;
import com.tmount.db.manage.dto.TItov_personal_manage;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class GetItovPersonalByAccountController extends ControllerBase {
	@Autowired
	private TItov_personalService tItov_personalService;
	@RequestMapping(value = "personal.info.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,	JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		try{
		TItov_personal_manage tItov_personal=new  TItov_personal_manage();
		Integer account_id = ParamData.getInt(requestParam.getBodyNode(), "account_id");//  账号ID
		tItov_personal.setAccount_id(Long.valueOf(account_id));
		tItov_personal=tItov_personalService.selectByAccountID(tItov_personal);
        Type listType = new TypeToken<TItov_personal_manage>(){}.getType();
        Gson gson=new Gson();
        String json=gson.toJson(tItov_personal, listType);
		responseBodyJson.writeStringField("result", json.replace("\"", "'"));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK, new Object[] { null });
		}
	}
}
