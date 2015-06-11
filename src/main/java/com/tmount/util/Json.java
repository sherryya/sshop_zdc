package com.tmount.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Json {
	private ObjectMapper objectMapper;

	public static Json me() {
		return new Json();
	}

	public Json() {
		objectMapper = new ObjectMapper();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属
		//objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		//objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		
	}

	public Json filter(String filterName, String properties) {
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName,
				SimpleBeanPropertyFilter.serializeAllExcept(properties));
		objectMapper.setFilters(filterProvider);
		return this;
	}

	public Json addMixInAnnotations(Class<?> target, Class<?> mixinSource) {
		//objectMapper.getSerializationConfig().addMixInAnnotations(target, mixinSource);
		//objectMapper.getDeserializationConfig().addMixInAnnotations(target, mixinSource);
		return this;
	}

	public Json setDateFormate(DateFormat dateFormat) {
		objectMapper.setDateFormat(dateFormat);
		return this;
	}

	public <T> T json2Obj(String json, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析json错误");
		}
	}

	public String readAsString(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析对象错误");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> json2List(String json) {
		try {
			return objectMapper.readValue(json, List.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析json错误");
		}
	}
	
}
