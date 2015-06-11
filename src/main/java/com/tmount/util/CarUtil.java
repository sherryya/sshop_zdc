package com.tmount.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.itov.platform.inter.launch.GetLaunchInfo;
import com.tmount.exception.ShopBusiException;

public class CarUtil {
	/*
	 * String deviceuid 设备ID String circleCenter 圆心 String circleRadius 半径
	 */
	private static final int DEFAULT_DIV_SCALE = 6;
	public static String  getWarningFlag(String deviceuid, String circleCenter,
			String circleRadius)
			throws JsonProcessingException, IOException, ShopBusiException,
			ParserConfigurationException, SAXException {
		String flag = "";
		// 1.根据设备id查询最新的gprs
		Map<String, String> params = new HashMap<String, String>();
		params.put("deviceuid", deviceuid);
		String json = GetLaunchInfo.getCurrentGPSInfo(params);
		json = URLDecoder.decode(json, "utf-8");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		if (null != jsonNode) {
			String status = jsonNode.get("Status").textValue();
			String Message = jsonNode.get("Message").textValue();
			String ErrorCode = jsonNode.get("ErrorCode").textValue();
			String Data = jsonNode.get("Data").textValue();
			if ("1".equals(status)) {
				JsonNode data = jsonNode.get("Data");
				if (data != null) {
					String Longitude = data.get("Longitude").textValue();// 经度
					String Latitude = data.get("Longitude").textValue();// 纬度
					// 2.将标准的经纬度，转换成图吧地图支持的经纬度
					String mapBarTude = readContentFromGet(Longitude + ","
							+ Latitude);
					// 3.比较围栏
					Double carLat = Double.parseDouble(circleCenter.split(",")[0]);
					Double carLag = Double.parseDouble(circleCenter.split(",")[1]);
					int r = Integer.parseInt(circleRadius);
					Double lat = Double.parseDouble(mapBarTude.split(",")[0]);
					Double lag = Double.parseDouble(mapBarTude.split(",")[1]);
					//false在圈外，true是在圈内
					boolean checkFlag = checkWarning(carLat, carLag, lat, lag, r);
					if(checkFlag){
						flag = "1";
					}else{
						flag ="0";
					}
				} else {
					throw new ShopBusiException(
							ShopBusiErrorBundle.LAUNCH_INTERFACE_WRONG_DATA_ISEMPTY,
							new Object[] { "CurrentGPSInfo" });
				}
			} else {
				throw new ShopBusiException(
						ShopBusiErrorBundle.LAUNCH_INTERFACE_WRONG,
						new Object[] { "CurrentGPSInfo" });
			}
		} else {
			throw new ShopBusiException(
					ShopBusiErrorBundle.LAUNCH_INTERFACE_WRONG,
					new Object[] { "CurrentGPSInfo" });
		}

		return flag;
	}

	public static String readContentFromGet(String param)
			throws java.io.IOException, ParserConfigurationException,
			SAXException {
		String url = "http://geocode.mapbar.com/Decode/encode_xml.jsp?lonlat="
				+ param;
		java.net.URL getUrl = new java.net.URL(url);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String retStr = "";
		String lines;
		while ((lines = reader.readLine()) != null) {
			retStr = retStr + lines;
		}
		reader.close();
		connection.disconnect();
		return getRetCode(retStr);
	}

	public static String getRetCode(String xmlString)
			throws ParserConfigurationException, SAXException, IOException {
		String retString = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse((new InputSource(new StringReader(
				xmlString))));
		Element root = doc.getDocumentElement();
		if (root != null) {
			NodeList nodes = root.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				NodeList itemNodes = node.getChildNodes();
				for (int j = 0; j < itemNodes.getLength(); j++) {
					node = itemNodes.item(j);
					retString = node.getTextContent();
				}
			}

		}
		return retString;
	}

	/*
	 * @param carLat
	 * @parma carLag
	 * @param lat
	 * 轨迹圆心纬度
	 * @param lag
	 * 
	 * 轨迹圆心经度
	 * 
	 * @param r
	 * 轨迹半径
	 */

	public static Boolean checkWarning(Double carLat ,Double carLag, Double lat, Double lag,
			Integer r) {
		double R = 6371;// 地球半径
		double distance = 0.0;
		double dLat = Double.valueOf(new BigDecimal(String.valueOf((carLat - lat)))
				.multiply(new BigDecimal(String.valueOf(Math.PI)))
				.divide(new BigDecimal(String.valueOf(180)), DEFAULT_DIV_SCALE,
						BigDecimal.ROUND_HALF_EVEN).toString());
		double dLon = Double.valueOf(new BigDecimal(String.valueOf((carLag - lag)))
				.multiply(new BigDecimal(String.valueOf(Math.PI)))
				.divide(new BigDecimal(String.valueOf(180)), DEFAULT_DIV_SCALE,
						BigDecimal.ROUND_HALF_EVEN).toString());
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(carLat * Math.PI / 180)
				* Math.cos(lat * Math.PI / 180) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R * 1000;
		System.out.println(distance);
		if (distance > Double.valueOf(String.valueOf(r))) {
			return false;
		}else{
			return true;
		}
	}

	
	
	/*
	 * public static void main(String[] args) throws IOException,
	 * ParserConfigurationException, SAXException { String xml =
	 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result><item count=\"2\"><lonlat id=\"0\">jingzhi</lonlat><lonlat id=\"1\">jaaaaingzhi</lonlat></item></result>"
	 * ; getRetCode(xml); }
	 */
}
