package com.tmount.business.sys.controller;

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
import com.tmount.business.sys.service.SysService;
import com.tmount.db.sys.dto.SyHotWords;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;

/**
 * 查询所有的系统热词。
 * @author lugz
 *
 */
@Controller
public class SysHotWordsGet extends ControllerBase {
	@Autowired
	private SysService sysService;

	@RequestMapping(value = "sys.hotwords.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		List<SyHotWords> syHotWordsList = sysService.selectAllSyHotWordsList();

		SyHotWords syHotWords;
		responseBodyJson.writeArrayFieldStart("word_list");
		if (syHotWordsList.size() > 0) {
			Iterator<SyHotWords> it = syHotWordsList.iterator();
			while (it.hasNext()) {
				syHotWords = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("hotwords_id", syHotWords.getHotwordsId());
				responseBodyJson.writeStringField("hotwords_name", syHotWords.getHotwordsName());
				responseBodyJson.writeNumberField("acount", syHotWords.getAcount());
				responseBodyJson.writeStringField("http_url", syHotWords.getHttpUrl());
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
