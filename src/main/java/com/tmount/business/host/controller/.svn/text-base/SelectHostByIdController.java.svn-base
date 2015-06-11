package com.tmount.business.host.controller;
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
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.ptt.service.PttSubaccountLogService;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.ptt.dto.TItovPttSubaccountLog;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

@Controller
public class SelectHostByIdController extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private TItovHostUserService zdcHostService;
	@Autowired
	private PttSubaccountLogService pttSubaccountLogService;
	
	@RequestMapping(value = "hostListById.get")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		int id = ParamData.getInt(requestParam.getBodyNode(), "id"); //
		int pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize"); // 每页多少条
		Integer pageNum = new Integer(ParamData.getInt(requestParam.getBodyNode(), "pageNum", -1));// 第几页
		logger.info("hostListById.get");
		List<TZdcHostUser> hostList = zdcHostService.selectByPrimaryKey(id);
		TZdcHostUser user=hostList.get(0);
		if (user != null) {
			responseBodyJson.writeNumberField("accountid", user.getAccountid());
			int accountID=Integer.parseInt(Long.toString(user.getAccountid()));
			int limit=pageSize;
			int start= (pageNum - 1) * pageSize;
			List<TItovPttSubaccountLog> radioList = pttSubaccountLogService.selectByAccountId(accountID, start,limit);
			StringBuffer bufferValue = new StringBuffer();
			for (TItovPttSubaccountLog tpl : radioList) {
				bufferValue.append(tpl.getRecordurl() + ";");
			}
			responseBodyJson.writeStringField("URLValues",bufferValue.toString());
			responseBodyJson.writeStringField("name", user.getName());
			responseBodyJson.writeStringField("nickname", user.getNickname());
			responseBodyJson.writeStringField("birthday", user.getBirthday());
			responseBodyJson.writeStringField("introduce", user.getIntroduce());
			responseBodyJson.writeStringField("voipAccount", user.getVoipAccount());
			responseBodyJson.writeStringField("voipPwd", user.getVoipPwd());
			responseBodyJson.writeNumberField("roomId", user.getRoomId());
			String tt = user.getPic();
			String[] pic = tt.split(";");
			if (pic.length > 1) {
				responseBodyJson.writeStringField("pic", pic[1]);// 主播照片
			} else if (pic.length > 0) {
				responseBodyJson.writeStringField("pic", pic[0]);
			} else if (pic.length > 0) {
				responseBodyJson.writeStringField("pic", "meinv.png");
			}
			responseBodyJson.writeNumberField("voip_status",user.getVoip_status());
		} else {
			responseBodyJson.writeNumberField("return", 0);
		}
	}
	public String native2Ascii(String str) {
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(char2Ascii(chars[i]));
		}
		return sb.toString();
	}

	/**
	 * Native character to ascii string.
	 * 
	 * @param c
	 *            native character
	 * @return ascii string
	 */
	private String char2Ascii(char c) {
		if (c > 255) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\u");
			int code = (c >> 8);
			String tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			code = (c & 0xFF);
			tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			return sb.toString();
		} else {
			return Character.toString(c);
		}
	}
}
