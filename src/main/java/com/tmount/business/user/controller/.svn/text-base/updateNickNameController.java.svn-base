package com.tmount.business.user.controller;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fivestars.interfaces.bbs.client.Client;
import com.fivestars.interfaces.bbs.util.Base64;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.ptt.service.PttPersonalInfoByAgentidService;
import com.tmount.business.user.service.UserService;
import com.tmount.db.ptt.dto.TItov_personal;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
@Controller
public class updateNickNameController extends ControllerBase {
	@Autowired
    private UserService userService;
	@Autowired
	private PttPersonalInfoByAgentidService pttPersonalInfoByAgentidService;
	Logger logger = Logger.getLogger(UserAdd.class.getName());
	@RequestMapping(value = "updateNickName.upd")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {
		String account_id = new String(ParamData.getString(requestParam.getBodyNode(), "account_id"));
		String nickname = new String(ParamData.getString(requestParam.getBodyNode(), "nickname"));
		String password = new String(ParamData.getString(requestParam.getBodyNode(), "password"));
		String personal_sex = ParamData.getString(requestParam.getBodyNode(), "personal_sex");//  
		/*	Client uc = new Client();
			String $returns = uc.uc_user_register(nickname, password,"ucenter@discuz.com" );
			int $uid = Integer.parseInt($returns);
			if($uid <= 0) {
				if($uid == -1) {
					throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
							new Object[] { "用户名不合法" });
				} else if($uid == -2) {
					throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
							new Object[] { "包含要允许注册的词语" });
				} else if($uid == -3) {
					throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
							new Object[] { "用户名已经存在" });
				} else if($uid == -4) {
					throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
							new Object[] { "Email 格式有误" });
				} else if($uid == -5) {
					throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
							new Object[] { "Email 不允许注册" });
				} else if($uid == -6) {
					throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
							new Object[] { "该 Email 已经被注册" });
				} else {
					throw new ShopBusiException(ShopBusiErrorBundle.DISCUZ_UCENTER_ERROR,
							new Object[] { "未定义" });
				}
			} else {
				*/
				
				UsAccount usAccount = new UsAccount();
				usAccount.setAccount_id(Long.valueOf(account_id));
				usAccount.setNickname(nickname);
				userService.updateNickNameByAccount(usAccount);//修改昵称
				//logger.info("DISCUZ_CENTER REGISITE OK"+$returns);
				

				TItov_personal tTItov_personal=new TItov_personal();
				tTItov_personal.setAccount_id(Long.valueOf(account_id));
				tTItov_personal.setPersonal_sex(personal_sex);
				try
				{
				    pttPersonalInfoByAgentidService.update(tTItov_personal);//修改性别
		        	responseBodyJson.writeStringField("return", "0");
				}
				catch (Exception e) {
					// TODO: handle exception
					throw new ShopBusiException(ShopBusiErrorBundle.ERROR_REQUEST_PACK,null);
				}
				responseBodyJson.writeStringField("result", "0");
			//}
	}
}
