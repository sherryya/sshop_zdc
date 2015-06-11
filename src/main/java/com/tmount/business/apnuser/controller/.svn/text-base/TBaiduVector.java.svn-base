package com.tmount.business.apnuser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.tmount.business.apnuser.service.ApnUserService;
import com.tmount.db.apnuser.dto.TBaidu;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;

/**
 *获取百度矢量值
 * 
 * @author 
 * 
 */
@Controller
public class TBaiduVector extends ControllerBase {
	@Autowired
	private ApnUserService apnUserService;
	@RequestMapping(value = "tbaiduVector.get")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException,
			JsonGenerationException, IOException {	
		//int pageNum = ParamData.getInt(requestParam.getBodyNode(),"pageNum");//第多少页
		//int pageSize = ParamData.getInt(requestParam.getBodyNode(),"pageSize");//每页多少条
		
		/*DBCollection collection = Initialization.getInstance().getDb().getCollection("t_zdc_baidu" );
		Mongo mongo = new Mongo("172.16.1.101", 30000);
		DB db = mongo.getDB("cardb");*/
		DB db;
		List<ServerAddress> addresses = new ArrayList<ServerAddress>();
		ServerAddress address1 = new ServerAddress("172.16.1.101", 27017);
		ServerAddress address2 = new ServerAddress("172.16.1.102", 27017);	
		addresses.add(address1);
		addresses.add(address2);
		MongoClient client = new MongoClient(addresses);
		db = client.getDB("zdc_db");
		DBCollection collection = db.getCollection("t_zdc_baidu" );
		for(int i=1;i<1415;i++)
		{
			TBaidu tbaidu = new TBaidu();
			tbaidu.setNum(1000);
			tbaidu.setStartLimit((i-1)*1000);
			//System.out.println(111111);
			List<TBaidu> tbaiduList = apnUserService.selectAll(tbaidu);
			if(null!=tbaiduList&&tbaiduList.size()>0)
			{
			for(TBaidu tbai:tbaiduList)
			{
				BasicDBObject bo = new BasicDBObject();
				bo.put("id", tbai.getId());
				bo.put("A", tbai.getA());
				bo.put("B", tbai.getB());
				bo.put("C", tbai.getC());
				bo.put("D", tbai.getD());
				collection.insert(bo);
			}
			}
		}
		
		

		
	}
}
