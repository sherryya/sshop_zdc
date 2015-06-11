package com.tmount.business.zdjs.controller;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.zdjs.service.TItovViewspotService;
import com.tmount.db.zdjs.dto.TItovViewspot;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 添加景点主表
 * 
 * @author 
 * 
 */
@Controller
public class AddViewSpotController extends ControllerBase {
	@Autowired
	private TItovViewspotService tItovViewspotService;
	@RequestMapping(value = "viewspot.add")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		Integer accountId = ParamData.getInt(requestParam.getBodyNode(),	"accountId");//账号ID
		String deviceuid =ParamData.getString(requestParam.getBodyNode(), "deviceuid");//设备UID
		String lonlat=ParamData.getString(requestParam.getBodyNode(),	"lonlat");//经纬度值
		String spotName=ParamData.getString(requestParam.getBodyNode(),	"spotName");//
		String spotTel=ParamData.getString(requestParam.getBodyNode(),	"spotTel");//
		String spotAddress=ParamData.getString(requestParam.getBodyNode(),	"spotAddress");//
		String spotManager=ParamData.getString(requestParam.getBodyNode(),	"spotManager");//
		String spotIntroduction=ParamData.getString(requestParam.getBodyNode(),	"spotIntroduction");//
		String lonlatId=ParamData.getString(requestParam.getBodyNode(),	"lonlatId");//
		String sportNote1=ParamData.getString(requestParam.getBodyNode(),	"sportNote1");//
		String sportNote2=ParamData.getString(requestParam.getBodyNode(),	"sportNote2");//
		String sportNote3=ParamData.getString(requestParam.getBodyNode(),	"sportNote3");//
		String spotNote=ParamData.getString(requestParam.getBodyNode(),	"spotNote");//
		if (StringUtils.isEmpty(spotName)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "景点名称" });
		}
		if (StringUtils.isEmpty(lonlat)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "经纬度" });
		}
		if (StringUtils.isEmpty(spotAddress)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "详细地址" });
		}
		if (StringUtils.isEmpty(spotIntroduction)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "景点介绍" });
		}	
		if (StringUtils.isEmpty(lonlatId)) {
			throw new ShopBusiException(ShopBusiErrorBundle.NO_NULL_VALUES,
					new Object[] { "经纬度ID" });
		}	
        TItovViewspot tItovViewspot=new  TItovViewspot();
        tItovViewspot.setAccountId(Long.valueOf(accountId));
        tItovViewspot.setDeviceuid(deviceuid);
        tItovViewspot.setLonlat(lonlat);
        tItovViewspot.setLonlatId(lonlatId);
        tItovViewspot.setSportNote1(sportNote1);
        tItovViewspot.setSportNote2(sportNote2);
        tItovViewspot.setSportNote3(sportNote3);
        tItovViewspot.setSpotAddress(spotAddress);
        tItovViewspot.setSpotIntroduction(spotIntroduction);
        tItovViewspot.setSpotManager(spotManager);
        tItovViewspot.setSpotName(spotName);
        tItovViewspot.setSpotNote(spotNote);
        tItovViewspot.setSpotTel(spotTel);
        TItovViewspot spot=new TItovViewspot();
        spot=tItovViewspotService.selectByLonlat_id(lonlatId);
        if(spot==null)
        {
            tItovViewspotService.insert_viewspot(tItovViewspot);
        }
        else
        {
        	  tItovViewspotService.updateByPrimaryKeySelective(tItovViewspot);
        }
		responseBodyJson.writeStringField("result", "0");
	}
}
