package com.tmount.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;


public class RequestParam {
	private RequestDataHeader requestDataHeader;
	private JsonNode bodyNode;
	private SimpleDateFormat sdf;
	
	public RequestParam(RequestDataHeader requestDataHeader, JsonNode bodyNode, SimpleDateFormat sdf) {
		this.setRequestDataHeader(requestDataHeader);
		this.bodyNode = bodyNode;
		this.sdf = sdf;
	}
	
	public String dateToString(Date date) {
		return sdf.format(date);
	}
	
	public Date strToDate(String date) throws ParseException {
		return sdf.parse(date);
	}

	public JsonNode getBodyNode() {
		return bodyNode;
	}
	public void setBodyNode(JsonNode bodyNode) {
		this.bodyNode = bodyNode;
	}

	public RequestDataHeader getRequestDataHeader() {
		return requestDataHeader;
	}

	public void setRequestDataHeader(RequestDataHeader requestDataHeader) {
		this.requestDataHeader = requestDataHeader;
	}
}
