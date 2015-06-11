package com.tmount.business.product.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.product.service.GoodsService;
import com.tmount.db.product.dto.GdItemsDeliver;
import com.tmount.db.product.dto.GdShopDeliverDetail;
import com.tmount.db.product.vo.GdItemDeliverInfo;
import com.tmount.db.product.vo.GdItemDeliverResultInfo;
import com.tmount.exception.ShopException;
import com.tmount.redis.product.dao.GdItemRedis;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取商品详细信息中的运费信息
 * 
 * @author rendi
 * 
 */
@Controller
public class ShopItemsdeliverGet extends ControllerBase {
	
	private static int FASTPOSTFLAG = 1;
	private static int POSTFLAG = 2;
	private static int EMSFLAG = 3;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GdItemRedis gdItemRedis;

	@RequestMapping(value = "shop.itemsdeliver.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		GdShopDeliverDetail gdShopDeliverDetail = new GdShopDeliverDetail();
		Long itemsId = ParamData.getLong(requestParam.getBodyNode(), "itemsId",-1);
		Long shopId = ParamData.getLong(requestParam.getBodyNode(), "shopId",-1);
		Integer cityCode = ParamData.getInt(requestParam.getBodyNode(), "cityCode",-1);
		String isUseMode = ParamData.getString(requestParam.getBodyNode(), "isUseMode","N");
		Long deliverId = ParamData.getLong(requestParam.getBodyNode(), "deliverId",-1);
		Integer itemsCount = ParamData.getInt(requestParam.getBodyNode(), "itemsCount",0);
		Double itemsWeight = ParamData.getDouble(requestParam.getBodyNode(), "itemsWeight",0.0);
		
		gdShopDeliverDetail.setShopId(shopId);
		gdShopDeliverDetail.setCityCode(cityCode);
		gdShopDeliverDetail.setDeliverId(deliverId);
		
		
		List<GdItemDeliverResultInfo> gdItemDeliverResultListInfo = new ArrayList<GdItemDeliverResultInfo>();
		/*判断商品是否使用了模板id*/
		/*正常前台不会请求无模板商品的运费信息，因为在商品详细信息中已经返回，由前台自行获取和判断*/
		GdItemsDeliver gdItemsDeliver =  goodsService.getItemsDeleverByItemsId(itemsId);
		if(!gdItemsDeliver.getIsUseMode().equals(isUseMode))
		{
			isUseMode=gdItemsDeliver.getIsUseMode();
			gdShopDeliverDetail.setDeliverId(gdItemsDeliver.getDeliverId());
		}
		if(isUseMode.equals("Y"))/*使用模板的商品*/
		{
			/*设定标识位，如果redis读取成功则置为false，否则需要读取数据库*/
			List<GdItemDeliverInfo> gdItemDeliverInfoList = null;
			String deliverStrJson = null;
			Boolean getFlagFromDb = false;
			/*商品总重量*/
			Double totalKG=itemsWeight * itemsCount;
			/*从redis中获取*/
			/*判断redis是否可用，如果可用从redis中读取*/
			if(gdItemRedis.checkRedis())
			{
				List<String> deliverStrList = gdItemRedis.getItemDeliverByCityCode(gdShopDeliverDetail);
				Iterator<String> strIterator = deliverStrList.iterator();
		        while(strIterator.hasNext())
		        {
		        	deliverStrJson = strIterator.next();
		        }
			}
			/*只有运费信息特殊，当redis中查找不到运费信息时，需要到数据库中进行二次检索*/
	        if(deliverStrJson != null)
	        {
				gdItemDeliverInfoList = (List<GdItemDeliverInfo>)objectMapper.readValue(deliverStrJson, new TypeReference<List<GdItemDeliverInfo>>(){});
	        }else
	        {
	        	/*如果redis中没有查到，默认到库中进行二次检索*/
	        	getFlagFromDb=true;
	        }
			/*从数据库中获取*/
			if(getFlagFromDb)
			{
				gdItemDeliverInfoList = goodsService.getGdShopDeliverByCityCode(gdShopDeliverDetail);
			}
			/*如果没有找到商品对应模版的配置记录，就应该取得商品默认运费，在下面计算*/
			if(gdItemDeliverInfoList.size()==0)
				isUseMode="N";
			Iterator<GdItemDeliverInfo> gdItemDeliverIterator = gdItemDeliverInfoList.iterator();
	        while(gdItemDeliverIterator.hasNext())
	        {
	        	Double fee = new Double(0);
	        	GdItemDeliverInfo gdItemDeliverInfo = gdItemDeliverIterator.next();
	        	GdItemDeliverResultInfo gdItemDeliverResultInfo = new GdItemDeliverResultInfo();
	        	gdItemDeliverResultInfo.setDeliverType(gdItemDeliverInfo.getDeliverType());
	        	gdItemDeliverResultInfo.setDeliverName(gdItemDeliverInfo.getDeliverName());
	        	if(totalKG<=gdItemDeliverInfo.getFirstCount())/*如果总重量小于模板中配置的首重*/
	        	{
	        		gdItemDeliverResultInfo.setDeliverFee(gdItemDeliverInfo.getFirstMoney());
	        	}else
	        	{
	        		/*首重价格+(总重量-首重)/每增加重量*每增加价格*/
	        		fee+= gdItemDeliverInfo.getFirstMoney();
	        		if((totalKG-gdItemDeliverInfo.getFirstCount())%gdItemDeliverInfo.getAddCount()==0)
	        			fee+=((totalKG-gdItemDeliverInfo.getFirstCount())/gdItemDeliverInfo.getAddCount())*
	        					gdItemDeliverInfo.getAddMoney();
	        		else
	        			fee+=((totalKG-gdItemDeliverInfo.getFirstCount())/gdItemDeliverInfo.getAddCount()+1)*
	        					gdItemDeliverInfo.getAddMoney();
	        		gdItemDeliverResultInfo.setDeliverFee(fee);
	        	}
	        	gdItemDeliverResultListInfo.add(gdItemDeliverResultInfo);
	        }
		}
		/*如果isUseMode为N证明，模版运费并未找到，需要取得商品默认运费*/
		if(isUseMode.equals("N"))
		{
			gdItemsDeliver.getEmsFee();
			gdItemsDeliver.getPostFee();
			gdItemsDeliver.getFastPostFee();
			/*需要将3个费用循环加入到返回信息的list中，否则返回结果为空*/
			for(int i=1;i<4;i++)
			{
				GdItemDeliverResultInfo gdItemDeliverResultInfoNo = new GdItemDeliverResultInfo();
				if(i==POSTFLAG){
					gdItemDeliverResultInfoNo.setDeliverType(POSTFLAG);
					gdItemDeliverResultInfoNo.setDeliverName("平邮费");
					gdItemDeliverResultInfoNo.setDeliverFee(gdItemsDeliver.getPostFee()*itemsCount);
				}
				if(i==FASTPOSTFLAG){
					gdItemDeliverResultInfoNo.setDeliverType(FASTPOSTFLAG);
					gdItemDeliverResultInfoNo.setDeliverName("快递费");
					gdItemDeliverResultInfoNo.setDeliverFee(gdItemsDeliver.getFastPostFee()*itemsCount);
				}
				if(i==EMSFLAG){
					gdItemDeliverResultInfoNo.setDeliverType(EMSFLAG);
					gdItemDeliverResultInfoNo.setDeliverName("EMS费");
					gdItemDeliverResultInfoNo.setDeliverFee(gdItemsDeliver.getEmsFee()*itemsCount);
				}
				gdItemDeliverResultListInfo.add(gdItemDeliverResultInfoNo);
			}
		}
		responseBodyJson.writeFieldName("deliverNode");
		objectMapper.writeValue(responseBodyJson, gdItemDeliverResultListInfo);
	}
}
