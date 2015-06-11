package com.tmount.redis.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tmount.db.apnuser.dto.TBaidu;

import redis.clients.jedis.Jedis;

public class TestJedis {
	
	
	public static void main(String[] args) {
		TBaidu tbaidu = new TBaidu();
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		tbaidu.setA("102.34");
		tbaidu.setB("41.3");
		tbaidu.setC("0.0123451");
		tbaidu.setC("0.0623551");
		List<TBaidu> list = new ArrayList<TBaidu>();
		list.add(tbaidu);
		TBaidu tbaidu2 = new TBaidu();
		tbaidu2.setA("102.21");
		tbaidu2.setB("42.5");
		tbaidu2.setC("0.0223451");
		tbaidu2.setC("0.0558391");
		list.add(tbaidu2);
		Gson gs = new Gson();
		String result = gs.toJson(list);
		/*jedis.set("teacher", "chali");
		//String value = jedis.get("teacher");
		jedis.set("teacher", " is my lover"); //拼接
		 System.out.println("hhhh--->"+jedis.get("teacher"));
		jedis.del("teacher");
	    System.out.println(jedis.get("teacher")); */
		System.out.println("value--->"+list.size());
	    
	    
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("102.96_45.2", "0.12356_0.265135");
	   map.put("101.11_12.2", "0.032156_0.21354");
	    map.put("102.90_45.1", "0.02315_0.145632");
	   jedis.del("user");
	    jedis.hmset("user",map);
	    //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
	    //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
	    System.out.println(jedis.hvals("user"));
	    System.out.println( jedis.hkeys("user"));
	    Set<String> st = new HashSet<String>();
	    st = jedis.hkeys("user");
	    if(st.contains("102.90_45.1"))
	    {
	    	//System.out.println(jedis.hvals("user"));
	    	//System.out.println(.exists("101.11_12.2"));
	    }
	    
	  /*  List<String> rsmap = jedis.hmget("user");
	    System.out.println(rsmap);
	     Iterator<String> iter=jedis.hkeys("user").iterator();  
	        while (iter.hasNext()){  
	         String key = iter.next();  
	         System.out.println(key+":"+jedis.hmget("user",key));  
	       } */
		     // jedis.del("java framework");  
		     //  System.out.println(jedis.lrange("java framework",0,-1));  
		       //先向key java framework中存放三条数据  
		     /*  jedis.del("data");
		       jedis.lpush("data",result);  
		       // jedis.lpush("java framework","struts");  
		      //  jedis.lpush("java framework","hibernate");  
		        //jedis.lpush(key, strings)
		       //再取出所有数据jedis.lrange是按范围取出，  
		         // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
		       System.out.println("lenth---->"+jedis.llen("data"));
		       System.out.println(jedis.lrange("data",0,-1));
		       List<String> ll = jedis.lrange("data",0,-1);
		       System.out.println(ll.toString().substring(1, ll.toString().length()-1));
		       ObjectMapper mapper = new ObjectMapper();
		       try {
				JsonNode jn = mapper.readTree(ll.toString().substring(1, ll.toString().length()-1));
				jn.elements();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		      // jn.get("");
		      
		         
	}
	
	/*

	
	private Jedis jedis; 
	      
	     @Before
	     public void setup() {
	          //连接redis服务器，192.168.0.100:6379
	          jedis = new Jedis("218.8.127.3", 16379);
	         //权限认证
	        //  jedis.auth("admin");  
	      }
	     
	     *//**
	      * redis存储字符串
	       *//*
	      @Test
	      public void testString() {
	          //-----添加数据----------  
	          jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
	         System.out.println(jedis.get("name"));//执行结果：xinxin  
	         
	          jedis.append("name", " is my lover"); //拼接
	         System.out.println(jedis.get("name")); 
	         
	          jedis.del("name");  //删除某个键
	          System.out.println(jedis.get("name"));
	          //设置多个键值对
	          jedis.mset("name","liuling","age","23","qq","476777389");
	          jedis.incr("age"); //进行加1操作
	          System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
	          
	     }

*/}
