package com.tmount.tools;

import java.io.IOException;
import java.net.URLEncoder;

public class test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	/*	JsonNode jsonNodeMap = MapUtil.getMap("126.62161305869","45.719279096385");
		Iterator it = jsonNodeMap.iterator();
		while(it.hasNext()){
			JsonNode js = (JsonNode)it.next();
			String LatitudeBase64 = js.get("x").textValue();
			String LongitudeBase64 = js.get("y").textValue();
			//解析base64串
			String LongitudeStr  = new String (new BASE64Decoder().decodeBuffer(LatitudeBase64));
			String   LatitudeStr= new String (new BASE64Decoder().decodeBuffer(LongitudeBase64));
			System.out.println(LongitudeStr+","+LatitudeStr);  
			String param = "";
			param += "&output=json&pois=1&location=";
			param += LatitudeStr + "," + LongitudeStr;
			System.out.println("param" + param);
			String json_map = MapUtil.getBaiduInverseGeographic(param);
			System.out.println(json_map);
		}*/
		String city=URLEncoder.encode( "哈尔滨","UTF-8");
		String parms="city="+city+"&keyword=黑龙江大学&searchType=route&customer=2";
	   // String ret=	MapUtil.getPoiNameByKeyword(parms );
	    //System.out.println(ret);
	}

}
