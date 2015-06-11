package com.sf.module.serviceprovide.service;

import java.net.URL;

import javax.xml.namespace.QName;

public class SFLogisticClient {
	private static final QName SERVICE_NAME = new QName("http://service.serviceprovide.module.sf.com/", "CustomerServiceService");

    public SFLogisticClient(String xmlStr) {
    }

    public static String SFCallService(String xmlStr,String serviceType){
        URL wsdlURL = CustomerServiceService.WSDL_LOCATION;
        String returnStr = "";
        System.out.println("xmlStr is ===="+xmlStr);
        System.out.println("serviceType is ===="+serviceType);
        CustomerServiceService ss = new CustomerServiceService(wsdlURL, SERVICE_NAME);
        ICustomerService port = ss.getCustomerServicePort();
        
        if(serviceType.equals("query")){
        	returnStr=port.logisticQueryStandard(xmlStr);
        }else if(serviceType.equals("create")){
        	returnStr=port.orderService(xmlStr);
        }else if(serviceType.equals("cancel")){
        	returnStr=port.cancelOrderService(xmlStr);
        }else if(serviceType.equals("bindNo")){
        	returnStr=port.orderMailBindingService(xmlStr);
        }else if(serviceType.equals("queryDetail")){
        	returnStr=port.queryMailnoDetailService(xmlStr);
        }

        return returnStr;
    }

}
