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
import com.tmount.db.sys.dto.SySearchWord;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.RequestParam;

/**
 * 查询所有的系统搜索词。
 * @author lugz
 *
 */
@Controller
public class SysHotSearchsGet extends ControllerBase {
	@Autowired
	private SysService sysService;

	@RequestMapping(value = "sys.searchwords.get")
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		List<SySearchWord> sySearchWordList = sysService.selectAllSySearchWordsList();

		SySearchWord sySearchWord;
		responseBodyJson.writeArrayFieldStart("word_list");
		if (sySearchWordList.size() > 0) {
			Iterator<SySearchWord> it = sySearchWordList.iterator();
			while (it.hasNext()) {
				sySearchWord = it.next();
				responseBodyJson.writeStartObject();
				responseBodyJson.writeNumberField("search_id", sySearchWord.getSearchId());
				responseBodyJson.writeStringField("search_name", sySearchWord.getSearchName());
				responseBodyJson.writeStringField("name_spr", sySearchWord.getNameSpr());
				responseBodyJson.writeNumberField("acount", sySearchWord.getAcount());
				responseBodyJson.writeEndObject();
			}
		}

		responseBodyJson.writeEndArray();
	}
}
