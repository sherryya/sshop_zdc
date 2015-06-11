package com.tmount.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class HttpSend {
	/*
	 * String url 地址
	 * Map<String,String> params 入参，类型为map,map中为字符串
	 * String charset 编码  utf-8 gbk
	 */
	
	public static String doPost(String url, Map<String,String> params, String charset) {

		StringBuffer returnStr = new StringBuffer();
		// 构造HttpClient的实例
		DefaultHttpClient client = new DefaultHttpClient();

		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= 5) {
					// Do not retry if over max retry count
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// Timeout
					return false;
				}
				if (exception instanceof UnknownHostException) {
					// Unknown host
					return false;
				}
				if (exception instanceof ConnectException) {
					// Connection refused
					return false;
				}
				if (exception instanceof SSLException) {
					// SSL handshake exception
					return false; 
				}
				HttpRequest request = (HttpRequest) context
						.getAttribute(ExecutionContext.HTTP_REQUEST);
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// Retry if the request is considered idempotent
					return true;
				}
				return false;
			}

		};

		client.setHttpRequestRetryHandler(myRetryHandler);
//		HttpHost proxy = new HttpHost("10.109.222.57", 8003);
//		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		// 创建Post法的实例
		HttpPost method = new HttpPost(url);

		method.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		method.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
		method.setHeader("Content-type",
				"application/x-www-form-urlencoded; charset="+charset);
		method.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
		//method.setHeader("Host", "www.dami88.com");
		// 设置Http Post数据
		if (params != null) {
			// 填入各个表单域的值
			List<NameValuePair> data = new ArrayList<NameValuePair>();

			Set sets = params.keySet();
			Object[] arr = sets.toArray();
			int mxsets = sets.size();

			for (int i = 0; i < mxsets; i++) {
				String key = (String) arr[i];
				String val = (String) params.get(key);
				data.add(new BasicNameValuePair(key, val));
			}
			// 将表单的值放入postMethod中
			try {
				method.setEntity(new UrlEncodedFormEntity(data, charset));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			// 执行postMethod
			HttpResponse response = client.execute(method);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				returnStr.append(EntityUtils.toString(response.getEntity()));
			}
		} catch (IOException e) {
			System.out.println("IO错误原因：" + e.getMessage());
		} finally {
			method.abort();
			client.getConnectionManager().shutdown();
		}
		return returnStr.toString();
	}
	
	public static void main(String[] args){
		Map map = new HashMap();
		map.put("a", "222222");
		System.out.println("test="
				+ HttpSend.doPost("http://116.228.70.232/eop/CommonOrderServlet.action", map,
						"gbk"));
	}
}
