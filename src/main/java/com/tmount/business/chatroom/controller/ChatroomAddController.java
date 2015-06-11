package com.tmount.business.chatroom.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.chatroom.service.ChatroomService;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.chatroom.dto.TZdcChatroom;
import com.tmount.db.chatroom.dto.TZdcChatroomid;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByLogin;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
//添加聊天室
@Controller
public class ChatroomAddController extends ControllerBaseByLogin {
	@Autowired
	private ChatroomService chatroomService;
	@Autowired
	private CarInfoService carInfoService;
	@RequestMapping(value = "Chatroom.Add")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson, HttpServletRequest request) throws ShopException, JsonGenerationException, IOException {
		String chatroom_name = ParamData.getString(requestParam.getBodyNode(), "chatroom_name");
		String chatroom_pwd = ParamData.getString(requestParam.getBodyNode(), "chatroom_pwd");
		int auto_dissolution = ParamData.getInt(requestParam.getBodyNode(), "auto_dissolution");
		int auto_enter = ParamData.getInt(requestParam.getBodyNode(), "auto_enter");
		int chatroom_type = ParamData.getInt(requestParam.getBodyNode(), "chatroom_type");
		int sound_pattern = ParamData.getInt(requestParam.getBodyNode(), "sound_pattern");
		long crt_owner = ParamData.getLong(requestParam.getBodyNode(), "crt_owner");
		long chatroomid = 0;
		try {
			TZdcChatroomid tZdcChatroomid = new TZdcChatroomid();
			tZdcChatroomid = chatroomService.select_chatroomid();// 得到房间号
			if (tZdcChatroomid != null) {
				//int chatroom_id = carInfoService.getCarId("chatroom_id");//得到聊天室ID
		       	//////集群 20150408
				int value = carInfoService.queryId("chatroom_id")+1;  //查询数据库序列值
				int chatroom_id = value;
				TestUpd testupd = new TestUpd();
				testupd.setName("chatroom_id");
				testupd.setValue(value);
				carInfoService.updtestupd(testupd);    //更新数据库的序列值
				//////end
				chatroomid=tZdcChatroomid.getChatroomId();
				TZdcChatroom tZdcChatroom = new TZdcChatroom();
				tZdcChatroom.setId(Long.valueOf(chatroom_id));
				tZdcChatroom.setAutoDissolution(auto_dissolution);
				tZdcChatroom.setAutoEnter(auto_enter);
				tZdcChatroom.setChatroomId(chatroomid);
				tZdcChatroom.setChatroomName(chatroom_name);
				tZdcChatroom.setChatroomPwd(chatroom_pwd);
				tZdcChatroom.setChatroomType(chatroom_type);
				tZdcChatroom.setCrtOwner(crt_owner);
				tZdcChatroom.setSoundPattern(sound_pattern);
				int ret = chatroomService.insert_chatroom(tZdcChatroom);
				if(ret>0)//添加成功
				{
					tZdcChatroomid.setChatroomId(chatroomid+1);
					chatroomService.update_chatroomid(tZdcChatroomid);//修改当前的房间号
					tZdcChatroom=null;
					//tZdcChatroom=chatroomService.select_chatroomByID(Long.valueOf(chatroom_id));
					responseBodyJson.writeStringField("result", String.valueOf(ret));
				}
				else
				{
					responseBodyJson.writeNumberField("result", 0);
				}
			}
			else
			{
				responseBodyJson.writeNumberField("result", 0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseBodyJson.writeNumberField("result", 0);
			return;
		}
	}
}
