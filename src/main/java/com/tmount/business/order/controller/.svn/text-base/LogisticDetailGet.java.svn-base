package com.tmount.business.order.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.sf.module.serviceprovide.service.SFLogisticClient;
import com.tmount.bundle.LogisticReason;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.order.service.OrderService;
import com.tmount.db.order.vo.LogisticInfo;
import com.tmount.db.order.vo.LogisticOrderResponse;
import com.tmount.db.order.vo.LogisticOrderResponseSF;
import com.tmount.db.order.vo.LogisticQueryResponse;
import com.tmount.db.order.vo.LogisticQueryResponseSF;
import com.tmount.db.order.vo.LogisticResponse;
import com.tmount.db.order.vo.LogisticResponseSF;
import com.tmount.db.order.vo.LogisticStepResponse;
import com.tmount.db.order.vo.LogisticStepResponseSF;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.HttpSend;
import com.tmount.util.LogisticTransXML;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
import com.tmount.util.TransXML;

import freemarker.template.Template;

@Controller
public class LogisticDetailGet extends ControllerBase{
	
	private String dataxml = "<?xml version=\"1.0\" encoding=\"gbk\"?>";
	private Boolean successFlag = true;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;
	
	@RequestMapping(value = "order.logisticdetail.get")
	
	@Override
    public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam,
			JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {
		Long orderNo = ParamData.getLong(requestParam.getBodyNode(),"order_no");
		
		/*根据orderNo查找物流运单号*/
		LogisticInfo logisticInfo = orderService.getLogisticsInfo(orderNo);
		
		try{
			if(logisticInfo.getLogisticProviderId().equals("YTO")){/*圆通查询*/
				YTQuery(logisticInfo,responseBodyJson);
			}else if(logisticInfo.getLogisticProviderId().equals("SF")){/*顺丰查询*/
				SFQuery(logisticInfo,responseBodyJson);
			}else{
				
			}
			
			if(successFlag==false){/*如果物流返回失败，也统一返回前台相同的提示*/
				throw new ShopBusiException(ShopBusiErrorBundle.ORDER_DETAIL_GET_ERROR, new Object[]{orderNo.toString()});
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new ShopBusiException(ShopBusiErrorBundle.ORDER_DETAIL_GET_ERROR, new Object[]{orderNo.toString()});
		}
	}
	
	private void YTQuery(LogisticInfo logisticInfo,JsonGenerator responseBodyJson) throws Exception {
		String recvXml="";
		LogisticQueryResponse logisticQueryResponse = new LogisticQueryResponse();
		logisticQueryResponse=null;
		Map<String,Object> queryeMap = new HashMap<String,Object>();
		queryeMap.put("clientID",logisticInfo.getClientId());
		queryeMap.put("logisticProviderID",logisticInfo.getLogisticProviderId());
//			queryeMap.put("mailNo", logisticInfo.getLogisticsNo());
		queryeMap.put("mailNo","1111111111");
		Template t = freemarkerConfig.getConfiguration().getTemplate("YTOQuery.ftl");
		StringWriter out = new StringWriter();
		t.process(queryeMap, out);
		String data = out.toString();
		out.flush();
		
        Map<String,String> sendMap = new HashMap<String,String>();
        sendMap.put("logistics_interface",data);
        sendMap.put("data_digest",LogisticTransXML.transStringYTO(data,"123456"));
        if(logisticInfo.getSendOnline().equals("Y"))
        	sendMap.put("type","online");
        else
        	sendMap.put("type","offline");
        sendMap.put("clientId",logisticInfo.getClientId());
        if(logisticInfo.getLogisticProviderId().equals("YTO")){/*圆通发送接口*/
        	String sendUrl="http://116.228.70.232/eop/CommonOrderServlet.action";
			String charset="UTF-8";
			recvXml=HttpSend.doPost(sendUrl, sendMap, charset);
		}

        /*返回报文为两种格式，成功直接返回结果信息，失败返回通用失败报文*/
        if(recvXml.indexOf("<Response>")!=-1){/*错误信息*/
        	successFlag = false;
        	LogisticResponse logisticResponse = (LogisticResponse) TransXML.xml2OBJ(recvXml, LogisticResponse.class);
        	System.out.println("send xml error rcvcode is "+logisticResponse.getReason()+" rcverrmsg is "+
        			LogisticReason.getSysErrMsg(logisticResponse.getReason()));
        }
        if(recvXml.indexOf("<BatchQueryResponse>")!=-1){/*成功结果信息*/
        	logisticQueryResponse = (LogisticQueryResponse) TransXML.xml2OBJ(dataxml+recvXml, LogisticQueryResponse.class);
        }
        
        String dataFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dFormat = new SimpleDateFormat(dataFormat);
		/*由于xml格式特殊，这里不直接转换对象，按照格式拆解对象进行json报文拼装*/
		if(logisticQueryResponse !=null){
			responseBodyJson.writeStringField("logisticsName",logisticInfo.getLogisticName());
			responseBodyJson.writeNumberField("orderNo",logisticInfo.getOrderNo());
			responseBodyJson.writeArrayFieldStart("orders");
			/*取出orders内容信息*/
			LogisticOrderResponse[] logisticOrderResponse = logisticQueryResponse.getOrders().getOrder();
			for(int i=0;i<logisticOrderResponse.length;i++){
				responseBodyJson.writeStartObject();
//				responseBodyJson.writeStringField("txLogisticID",logisticOrderResponse[i].getTxLogisticID());
				responseBodyJson.writeStringField("mailNo",logisticOrderResponse[i].getMailNo());
//				responseBodyJson.writeStringField("mailType",logisticOrderResponse[i].getMailType());
//				responseBodyJson.writeStringField("orderStatus",LogisticReason.getStatusMsg(logisticOrderResponse[i].getOrderStatus()));
				LogisticStepResponse[] steps = logisticOrderResponse[i].getSteps().getStep();
				responseBodyJson.writeArrayFieldStart("steps");
				for(int j=0;j<steps.length;j++){
					responseBodyJson.writeStartObject();
					responseBodyJson.writeStringField("acceptTime",dFormat.format(steps[j].getAcceptTime()));
					responseBodyJson.writeStringField("acceptAddress",steps[j].getAcceptAddress());
//					responseBodyJson.writeStringField("name",steps[j].getName());
//					responseBodyJson.writeStringField("status",steps[j].getStatus());
					responseBodyJson.writeStringField("remark",steps[j].getRemark());
					responseBodyJson.writeEndObject();
				}
				responseBodyJson.writeEndArray();
				responseBodyJson.writeEndObject();
			}
			responseBodyJson.writeEndArray();
		}
	}
	
	private void SFQuery(LogisticInfo logisticInfo,JsonGenerator responseBodyJson) throws Exception {
		String CHECKWORD="12345";
		String CUSTID="12345";
		String recvXml="";
		LogisticQueryResponseSF logisticQueryResponseSF = new LogisticQueryResponseSF();
		logisticQueryResponseSF=null;
		Map<String,Object> queryeMap = new HashMap<String,Object>();
		queryeMap.put("trackingType",1);
		queryeMap.put("custId", CUSTID);/*客户卡号，统一写商城对应的客户卡号*/
		queryeMap.put("checkWord",CHECKWORD);
		queryeMap.put("trackNumber",logisticInfo.getLogisticsNo());
		Template t = freemarkerConfig.getConfiguration().getTemplate("SFQuery.ftl");
		StringWriter out = new StringWriter();
		t.process(queryeMap, out);
		String data = out.toString();
		out.flush();
		
		recvXml = SFLogisticClient.SFCallService(data, "query");
		
		System.out.println("recvXml====================="+recvXml);

        /*返回报文为两种格式，成功直接返回结果信息，失败返回通用失败报文*/
        if(recvXml.indexOf("<mailnoResponseFail>")!=-1){/*错误信息*/
        	successFlag = false;
        	LogisticResponseSF logisticResponseSF = (LogisticResponseSF) TransXML.xml2OBJ(recvXml, LogisticResponseSF.class);
        	System.out.println("SF send xml error rcvcode is "+logisticResponseSF.getReasonCode()+" rcverrmsg is "+
        			LogisticReason.getSFErrMsg(logisticResponseSF.getReasonCode()));
        }
        if(recvXml.indexOf("<mailnoResponse>")!=-1){/*成功结果信息*/
        	logisticQueryResponseSF = (LogisticQueryResponseSF) TransXML.xml2OBJ(dataxml+recvXml, LogisticQueryResponseSF.class);
        }
        
        String dataFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dFormat = new SimpleDateFormat(dataFormat);
		/*由于xml格式特殊，这里不直接转换对象，按照格式拆解对象进行json报文拼装*/
		if(logisticQueryResponseSF !=null){
			responseBodyJson.writeStringField("logisticsName",logisticInfo.getLogisticName());
			responseBodyJson.writeNumberField("orderNo",logisticInfo.getOrderNo());
			responseBodyJson.writeArrayFieldStart("orders");
			/*取出orders内容信息*/
			LogisticOrderResponseSF[] logisticOrderResponseSF = logisticQueryResponseSF.getOrders().getOrder();
			for(int i=0;i<logisticOrderResponseSF.length;i++){
				responseBodyJson.writeStartObject();
				responseBodyJson.writeStringField("mailNo",logisticOrderResponseSF[i].getMailNo());
				LogisticStepResponseSF[] stepsSF = logisticOrderResponseSF[i].getSteps().getStep();
				responseBodyJson.writeArrayFieldStart("steps");
				for(int j=0;j<stepsSF.length;j++){
					responseBodyJson.writeStartObject();
					responseBodyJson.writeStringField("acceptTime",dFormat.format(stepsSF[j].getAcceptTime()));
					responseBodyJson.writeStringField("acceptAddress",stepsSF[j].getAcceptAddress());
					responseBodyJson.writeStringField("remark",stepsSF[j].getRemark());
					responseBodyJson.writeEndObject();
				}
				responseBodyJson.writeEndArray();
				responseBodyJson.writeEndObject();
			}
			responseBodyJson.writeEndArray();
		}
	}
}
