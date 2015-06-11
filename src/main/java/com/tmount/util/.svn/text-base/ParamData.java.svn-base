package com.tmount.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.tmount.bundle.ShopParamErrorBundle;
import com.tmount.exception.ShopParamException;

public class ParamData {
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static int getInt(JsonNode jsonNode, String fieldName) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{fieldName});
		} else {
			try {
				return leafNode.intValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "int"});
			}
		}
	}

	public static int getInt(JsonNode jsonNode, String fieldName, int defaultValue) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			return defaultValue;
		} else {
			try {
				return leafNode.intValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "int"});
			}
		}
	}

	public static long getLong(JsonNode jsonNode, String fieldName) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{fieldName});
		} else {
			try {
				return leafNode.longValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "long"});
			}
		}
	}

	public static long getLong(JsonNode jsonNode, String fieldName, long defaultValue) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			return defaultValue;
		} else {
			try {
				return leafNode.longValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "long"});
			}
		}
	}

	public static double getDouble(JsonNode jsonNode, String fieldName) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{fieldName});
		} else {
			try {
				return leafNode.doubleValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "double"});
			}
		}
	}

	public static double getDouble(JsonNode jsonNode, String fieldName, Double defaultValue) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			return defaultValue;
		} else {
			try {
				return leafNode.doubleValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "double"});
			}
		}
	}

	public static String getString(JsonNode jsonNode, String fieldName) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{fieldName});
		} else {
			try {
				return leafNode.textValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "string"});
			}
		}
	}

	public static String getString(JsonNode jsonNode, String fieldName, String defaultValue) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			return defaultValue;
		} else {
			try {
				return leafNode.textValue();
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "string"});
			}
		}
	}

	public static Date getDate(JsonNode jsonNode, String fieldName) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{fieldName});
		} else {
			try {
				return new SimpleDateFormat(DATE_FORMAT).parse(leafNode.textValue());
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "date"});
			}
		}
	}

	public static Date getDate(JsonNode jsonNode, String fieldName, Date defaultValue) throws ShopParamException {
		JsonNode leafNode = jsonNode.get(fieldName);
		if (leafNode == null) {
			return defaultValue;
		} else {
			try {
				return new SimpleDateFormat(DATE_FORMAT).parse(leafNode.textValue());
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.FORMAT_ERROR_IN_PARAM, new Object[]{fieldName, "date"});
			}
		}
	}

	public static JsonNode getJsonNode(JsonNode jsonNode, String fieldName) throws ShopParamException {
		JsonNode node = jsonNode.get(fieldName);
		if (node == null) {
			throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{fieldName});
		} else {
			return node;
		}
	}
	
	public static JsonNode getJsonNode(JsonNode jsonNode, String fieldName,JsonNode defaultvalue) throws ShopParamException {
		JsonNode node = jsonNode.get(fieldName);
		if (node == null) {
			return defaultvalue;
		} else {
			try
			{
				return node;
			}catch(Exception e) {
				throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{fieldName});
			}
			
		}
	}
}
