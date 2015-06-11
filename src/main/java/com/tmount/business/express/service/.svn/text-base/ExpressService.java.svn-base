package com.tmount.business.express.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.tmount.business.express.vo.OrderInfoVo;
import com.tmount.db.order.dto.ReOrderDeliver;
import com.tmount.db.order.vo.ReUserOrderDetailInfo;

import freemarker.template.Template;

@Service
public class ExpressService {
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;
	
	public String sendXml(String str){
		String xml="";
		try {
			Map root = new HashMap();
			OrderInfoVo orderInfoVo = new OrderInfoVo();
			root.put("orderInfo", orderInfoVo);
			orderInfoVo.setClientID("zhongmi");
			orderInfoVo.setCustomerId("test");
			orderInfoVo.setFlag(1);
			orderInfoVo.setGoodsValue(10.11);
			orderInfoVo.setInsuranceValue(20.11);
			orderInfoVo.setItemsValue(0);
			orderInfoVo.setLogisticProviderID("YTO");
			orderInfoVo.setMailNo("zhong.com");
			orderInfoVo.setOrderType(1);
			orderInfoVo.setRemark("111111");
			orderInfoVo.setSendEndTime(new Date());
			orderInfoVo.setSendStartTime(new Date());
			orderInfoVo.setServiceType(1);
			orderInfoVo.setTradeNo("11111111111");
			orderInfoVo.setTxLogisticID("22222222");
			orderInfoVo.setType(1);
			ReUserOrderDetailInfo ruodi = new ReUserOrderDetailInfo();
			ruodi.setPrice(10.00);
			ruodi.setAcount(2);
			ruodi.setItemsName("大米");
			ReUserOrderDetailInfo ruodi1 = new ReUserOrderDetailInfo();
			ruodi1.setPrice(11.00);
			ruodi1.setAcount(3);
			ruodi1.setItemsName("大米1");
			List<ReUserOrderDetailInfo> list = new ArrayList();
			list.add(ruodi);
			list.add(ruodi1);
			orderInfoVo.setItems(list);
			ReOrderDeliver ro = new ReOrderDeliver();
			ro.setUserName("user1");
			ro.setAddress("黑龙江哈恶补");
			ro.setPostCode("10000");
			ro.setMobile("13333333333");
			ro.setCityCode(1111);
			ro.setPostCode("22222");
			orderInfoVo.setReceiver(ro);
			ReOrderDeliver ro1 = new ReOrderDeliver();
			ro1.setUserName("user1");
			ro1.setAddress("黑龙江哈恶补");
			ro1.setPostCode("10000");
			ro1.setMobile("13333333333");
			ro1.setCityCode(1111);
			ro1.setPostCode("22222");
			orderInfoVo.setSender(ro1);
			
			
			Template t = freemarkerConfig.getConfiguration().getTemplate("YTO.ftl");
			StringWriter out = new StringWriter();
			t.process(root, out);
			xml = out.toString();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("aaa="+xml);
		return xml;
	}
}
