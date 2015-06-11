package com.tmount.business.carhot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.host.service.TItovHostUserService;
import com.tmount.business.itov.controller.Getlistbypage;
import com.tmount.business.manage.service.TItov_shop4sService;
import com.tmount.db.car.dto.TZdcCarhotStrategy;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.manage.dto.TItov_shop4s_manage;
import com.tmount.db.manage.dto.TItov_shop4s_user;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 更新主播信息
 * 
 * @author
 * 
 */
@Controller
public class CarStrategyInfo extends ControllerBase {
	Logger logger = Logger.getLogger(Getlistbypage.class);
	@Autowired
	private CarInfoService carInfoService;

	@RequestMapping(value = "carhot.strategy.upd")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		//String name = ParamData.getString(requestParam.getBodyNode(), "account_name");
		String device_id = ParamData.getString(requestParam.getBodyNode(), "device_id");
		String strategy_type = ParamData.getString(requestParam.getBodyNode(), "strategy_type");
		String strategy_value = ParamData.getString(requestParam.getBodyNode(), "strategy_value");
		int is_use = ParamData.getInt(requestParam.getBodyNode(), "is_use");
		if((device_id==null || ("").equals(device_id))){
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "设备号不能为空" });
		}
		if((strategy_type==null || ("").equals(strategy_type))){
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "策略类型不能为空" });
		}
		if((strategy_value==null || ("").equals(strategy_value))){
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "策略值不能为空" });
		}
		
		TZdcCarhotStrategy tZdcCarhotStrategy=new TZdcCarhotStrategy();
		tZdcCarhotStrategy.setDeviceId(device_id);
		tZdcCarhotStrategy.setStrategyType(strategy_type);
		tZdcCarhotStrategy.setStrategyValue(strategy_value);
		tZdcCarhotStrategy.setIs_use(is_use);
		try{
			int num=0;
			int count=0;
			logger.info("carhot.strategy.upd 更新热车策略开始");
			num=carInfoService.selectStrategyCount(device_id);
			if(num==0){
				count = carInfoService.saveCarHotStrategy(tZdcCarhotStrategy);
			}else{
				count = carInfoService.updateCarHotStrategy(tZdcCarhotStrategy);
			}
			logger.info("updateShop4sinfo.upd 更新热车策略结束");
			responseBodyJson.writeNumberField("result", count);
		}catch(Exception e)
		{	
			e.printStackTrace();
			logger.info("carhot.strategy.upd");
			throw new ShopBusiException(ShopBusiErrorBundle.COMMON,
					new Object[] { "系统出现异常" });
		}
		
	}
}
