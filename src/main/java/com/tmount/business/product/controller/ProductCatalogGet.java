package com.tmount.business.product.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.tmount.db.product.dao.GdItemsTypeDicMapper;
import com.tmount.db.product.dto.GdItemsTypeDic;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 * 获取平台目录信息
 * @author lugz
 *
 */
@Controller
public class ProductCatalogGet extends ControllerBase {
	@Autowired
	private GdItemsTypeDicMapper gdItemsTypeDicMapper;

	@RequestMapping(value = "product.catalog.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}
	
	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Integer dataType = new Integer(ParamData.getInt(requestParam.getBodyNode(), "data_type", -1));
		Integer companyId = new Integer(ParamData.getInt(requestParam.getBodyNode(), "company_id"));
		Integer levelNo = new Integer(ParamData.getInt(requestParam.getBodyNode(), "level_no", -1));
		
		GdItemsTypeDic gdItemsTypeDic = new GdItemsTypeDic();
		gdItemsTypeDic.setCompanyId(companyId);
		if (dataType != -1) {
			gdItemsTypeDic.setDataType(dataType);
		}
		if (levelNo != -1) {
			gdItemsTypeDic.setLevelNo(levelNo);
		}
		List<GdItemsTypeDic> gdItemsTypeDicList = gdItemsTypeDicMapper.selectItemsList(gdItemsTypeDic);
		Iterator<GdItemsTypeDic> it = gdItemsTypeDicList.iterator();
		responseBodyJson.writeArrayFieldStart("catalog_list");
		while (it.hasNext()) {
			gdItemsTypeDic = it.next();
			responseBodyJson.writeStartObject();
			responseBodyJson.writeNumberField("items_type", gdItemsTypeDic.getItemsType());
			responseBodyJson.writeNumberField("orders", gdItemsTypeDic.getOrders());
			responseBodyJson.writeNumberField("parent_id", gdItemsTypeDic.getParentId());
			responseBodyJson.writeNumberField("level_no", gdItemsTypeDic.getLevelNo());
			responseBodyJson.writeNumberField("show_type", gdItemsTypeDic.getShowType());
			responseBodyJson.writeNumberField("data_type", gdItemsTypeDic.getDataType());
			responseBodyJson.writeStringField("type_name", gdItemsTypeDic.getTypeName());
			responseBodyJson.writeStringField("type_exp", gdItemsTypeDic.getTypeExp());
			responseBodyJson.writeStringField("type_desc", gdItemsTypeDic.getTypeDesc());
			responseBodyJson.writeNumberField("small_pic", gdItemsTypeDic.getSmallPic());
			responseBodyJson.writeNumberField("big_pic", gdItemsTypeDic.getBigPic());
			responseBodyJson.writeStringField("state", gdItemsTypeDic.getState());
			responseBodyJson.writeNumberField("items_count", gdItemsTypeDic.getItemsCount());
			responseBodyJson.writeStringField("items_tag", gdItemsTypeDic.getItemsTag());
			responseBodyJson.writeEndObject();
		}
		responseBodyJson.writeEndArray();
	}
}
