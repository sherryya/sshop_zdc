package com.tmount.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class BaiduMap {
	protected void runTest() throws Throwable {
		 
	    try {
	        BaiduLocation bl = new BaiduLocation();
	            bl.gpsx = (float) 126.6808373;//经度
	            bl.gpsy = (float) 45.744355833333341;//纬度
	            bl.ok=true;
	            GetBaiduLocation(bl);
	            if(bl.ok) {
	                int baidux = (int)(bl.baidux*1E6);
	                int baiduy = (int)(bl.baiduy*1E6);
	                System.out.println(baidux+","+baiduy);
	                // 转换成功，这个坐标是百度专用的
	            }
	            else {
	                /// 转换失败
	            }
	    }
	    catch(Exception ex) {
	    }
	}
	 
	class BaiduLocation {
	    public float gpsx, gpsy;
	    public float baidux, baiduy;
	    public boolean ok = true;
	}
	 
	public static String GetBaiduLocation(double d, double e) throws MalformedURLException, IOException {
	    String url = String.format("http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x=%f&y=%f", d, e);
	    HttpURLConnection urlConnection = (HttpURLConnection)(new URL(url).openConnection());
	    urlConnection.connect();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	    String lines = reader.readLine();
	    reader.close(); 
	    urlConnection.disconnect();
	    return lines;
	}   
	 
	public static boolean GetBaiduLocation(BaiduLocation bl) {
	    try {
	        bl.ok = false;
	        String res = GetBaiduLocation(bl.gpsx, bl.gpsy);
	        if(res.startsWith("{") && res.endsWith("}")) {
	            res = res.substring(1, res.length() - 2).replace("\"", "");
	            String[] lines = res.split(",");
	            for(String line : lines) {
	                String[] items = line.split(":");
	                if(items.length == 2) {
	                    if("error".equals(items[0])) {
	                        bl.ok = "0".equals(items[1]);
	                    }
	                    if("x".equals(items[0])) {
	                        bl.baidux = ConvertBase64(items[1]);
	                    }
	                    if("y".equals(items[0])) {
	                        bl.baiduy = ConvertBase64(items[1]);
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        bl.ok = false;
	    } 
	    return bl.ok;   
	}
	private static float ConvertBase64(String str) {
	    byte[] bs = Base64.decode(str);       
	    return Float.valueOf(new String(bs));
		
	}
	
	
	public static void main(String[] args) throws Throwable {

BaiduMap baiduMap=new BaiduMap();
 baiduMap.runTest();
		//126.622044,45.711547
		/*try {
			BaiduMap.GetBaiduLocation( 126.622044,45.711547);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
	}
}
