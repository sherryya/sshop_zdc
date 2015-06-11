package com.tmount.jpush.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmount.jpush.JPushClient;
import com.tmount.jpush.common.DeviceEnum;
import com.tmount.jpush.push.CustomMessageParams;
import com.tmount.jpush.push.MessageResult;
import com.tmount.jpush.push.ReceiverTypeEnum;
import com.tmount.jpush.report.ReceivedsResult;

public class JPushClientExample {
    protected static final Logger LOG = LoggerFactory.getLogger(JPushClientExample.class);

    // demo App defined in resources/jpush-api.conf 
/*	private static final String appKey ="00eb7eadee4c8d07d2463da2";
	private static final String masterSecret = "46bad9c2951c239a8b8f67fe";*/
	private static final String appKey ="789d954d5e4780786a1091b6";
	private static final String masterSecret = "fab232573e869dfd45e6bde0";
	public static final String msgTitle = "Test from API example";
    public static final String msgContent = "Test111";
    public static final String registrationID = "0a0faadea1d"; 
    public static final String tag = "18646117093";
    //BT 版
    private static final String appKey_zdcservicesplatform="8fc7b89d22c9fc6a59616d81";//数据采集平台
    private static final String masterSecret_zdcservicesplatform="807138eeb1e7ea8574d9c357";
    //TTL 版
/*    private static final String appKey_zdcservicesplatform="b34a78f6f0d78ecbf295f6e0";//数据采集平台
    private static final String masterSecret_zdcservicesplatform="8c252cc2b3d88e79879d6b20";*/  
    
    private static final String appKey_zdccarplatform="095602b400e29e2c703e2284";//只点车机平台
    private static final String masterSecret_zdccarplatform="c1c0b921657d5423fe5b63c6";
    
    private static final String appKey_zdccarhotplatform="66b81fe73caca3c529b7bd21";//热车平台
    private static final String masterSecret_zdccarhotplatform="9684a33ae5781328ee0f8e66";
    
    private String appkey;
    private String appSec;
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getAppSec() {
		return appSec;
	}

	public void setAppSec(String appSec) {
		this.appSec = appSec;
	}

	
	public static void main(String[] args) {
		//http://zhangmenshiting.baidu.com/data2/music/64380827/64380827.mp3?xcode=3dd3c946b8e2f93b300e603a79afca99eaa48b2ded70843d&mid=0.43592749230365
		//testSend();
		//SendMsgRegistrationID("dd", "{\"body\":{\"Latitude\":\"126.679576\",\"Longitude\":\"45.743820\",\"Msgtype\":\"1\",\"Title\":\"下发音乐\",\"Imageurl\":\"\",\"Noticeurl\":\"\",\"Content\":\"\",\"strType\":\"0\",\"strNum\":\"\"}}");
		SendMsg_ZDCCarHot("18603650832","dd", "{\"body\":{\"Latitude\":\"126.679576\",\"Longitude\":\"45.743820\",\"Msgtype\":\"102\",\"Title\":\"下发音乐\",\"Imageurl\":\"\",\"Noticeurl\":\"\",\"Content\":\"http://zhangmenshiting.baidu.com/data2/music/64380827/64380827.mp3?xcode=3dd3c946b8e2f93b300e603a79afca99eaa48b2ded70843d&mid=0.43592749230365\",\"strType\":\"2E82828288\",\"strNum\":\"\"}}");
		//testGetReport();
		//SendMsg_ZDCService("862966024440454","444","12");
	}

	private static void testSend() {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 0, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		//params.setReceiverType(ReceiverTypeEnum.REGISTRATION_ID);
		//params.setReceiverValue(registrationID);
		params.setReceiverType(ReceiverTypeEnum.TAG);
		params.setReceiverValue(tag);
		
		MessageResult msgResult = jpushClient.sendCustomMessage(msgTitle, msgContent, params, null);
        LOG.debug("responseContent - " + msgResult.responseResult.responseContent);
		if (msgResult.isResultOK()) {
	        LOG.info("msgResult - " + msgResult);
	        LOG.info("messageId - " + msgResult.getMessageId());
		} else {
		    if (msgResult.getErrorCode() > 0) {
		        // 业务异常
		        LOG.warn("Service error - ErrorCode: "
		                + msgResult.getErrorCode() + ", ErrorMessage: "
		                + msgResult.getErrorMessage());
		    } else {
		        // 未到达 JPush 
		        LOG.error("Other excepitons - "
		                + msgResult.responseResult.exceptionString);
		    }
		}
	}
	
	public static void testGetReport() {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		ReceivedsResult receivedsResult = jpushClient.getReportReceiveds("1708010723,1774452771");
        LOG.debug("responseContent - " + receivedsResult.responseResult.responseContent);
		if (receivedsResult.isResultOK()) {
		    LOG.info("Receiveds - " + receivedsResult);
		} else {
            if (receivedsResult.getErrorCode() > 0) {
                // 业务异常
                LOG.warn("Service error - ErrorCode: "
                        + receivedsResult.getErrorCode() + ", ErrorMessage: "
                        + receivedsResult.getErrorMessage());
            } else {
                // 未到达 JPush
                LOG.error("Other excepitons - "
                        + receivedsResult.responseResult.exceptionString);
            }
		}
	}
	/**
	 * 推送客户端信息
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public static String SendMsg(String tag,String msgTitle,String msgContent) {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 0, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		//params.setReceiverType(ReceiverTypeEnum.REGISTRATION_ID);
		//params.setReceiverValue(registrationID);
		params.setReceiverType(ReceiverTypeEnum.TAG);
		params.setReceiverValue(tag);
	    LOG.info("msgTitle - " + msgTitle);
	    LOG.info("msgContent - " + msgContent);
	    LOG.info("TAG - " + ReceiverTypeEnum.TAG);
	    LOG.info("tag - " + tag);
	    LOG.info("################################################################");
		MessageResult msgResult = jpushClient.sendCustomMessage(msgTitle, msgContent, params, null);
        LOG.debug("responseContent - " + msgResult.responseResult.responseContent);
		if (msgResult.isResultOK()) {
	        LOG.info("msgResult - " + msgResult);
	        LOG.info("messageId - " + msgResult.getMessageId());
	        return "1";
		} else {
		    if (msgResult.getErrorCode() > 0) {
		        // 业务异常
		        LOG.warn("Service error - ErrorCode: "
		                + msgResult.getErrorCode() + ", ErrorMessage: "
		                + msgResult.getErrorMessage());
		        return "0";
		    } else {
		        // 未到达 JPush 
		        LOG.error("Other excepitons - "
		                + msgResult.responseResult.exceptionString);
		        return "-1";
		    }
		}
	}
	
	
	
	public static String SendMsgRegistrationID(String msgTitle,String msgContent) {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 0, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		params.setReceiverType(ReceiverTypeEnum.APP_KEY);
		params.setReceiverValue(appKey);
		//params.setReceiverType(ReceiverTypeEnum.TAG);
		//params.setReceiverValue(tag);
	    LOG.info("msgTitle - " + msgTitle);
	    LOG.info("msgContent - " + msgContent);
	    LOG.info("TAG - " + ReceiverTypeEnum.TAG);
	    LOG.info("################################################################");
		MessageResult msgResult = jpushClient.sendCustomMessage(msgTitle, msgContent, params, null);
        LOG.debug("responseContent - " + msgResult.responseResult.responseContent);
		if (msgResult.isResultOK()) {
	        LOG.info("msgResult - " + msgResult);
	        LOG.info("messageId - " + msgResult.getMessageId());
	        return "1";
		} else {
		    if (msgResult.getErrorCode() > 0) {
		        // 业务异常
		        LOG.warn("Service error - ErrorCode: "
		                + msgResult.getErrorCode() + ", ErrorMessage: "
		                + msgResult.getErrorMessage());
		        return "0";
		    } else {
		        // 未到达 JPush 
		        LOG.error("Other excepitons - "
		                + msgResult.responseResult.exceptionString);
		        return "-1";
		    }
		}
	}
	
	
	
	/**
	 * 数据采集程序
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public static String SendMsg_ZDCService(String tag,String msgTitle,String msgContent) {
        JPushClient jpushClient = new JPushClient(masterSecret_zdcservicesplatform, appKey_zdcservicesplatform, 0, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		//params.setReceiverType(ReceiverTypeEnum.REGISTRATION_ID);
		//params.setReceiverValue(registrationID);
		params.setReceiverType(ReceiverTypeEnum.TAG);
		params.setReceiverValue(tag);
	    LOG.info("msgTitle - " + msgTitle);
	    LOG.info("msgContent - " + msgContent);
	    LOG.info("TAG - " + ReceiverTypeEnum.TAG);
	    LOG.info("tag - " + tag);
	    LOG.info("################################################################");
		MessageResult msgResult = jpushClient.sendCustomMessage(msgTitle, msgContent, params, null);
        LOG.debug("responseContent - " + msgResult.responseResult.responseContent);
		if (msgResult.isResultOK()) {
	        LOG.info("msgResult - " + msgResult);
	        LOG.info("messageId - " + msgResult.getMessageId());
	        return "1";
		} else {
		    if (msgResult.getErrorCode() > 0) {
		        // 业务异常
		        LOG.warn("Service error - ErrorCode: "
		                + msgResult.getErrorCode() + ", ErrorMessage: "
		                + msgResult.getErrorMessage());
		        return "0";
		    } else {
		        // 未到达 JPush 
		        LOG.error("Other excepitons - "
		                + msgResult.responseResult.exceptionString);
		        return "-1";
		    }
		}
	}
	/**
	 * 只点车机程序
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public static String SendMsg_ZDCCar(String tag,String msgTitle,String msgContent) {
        JPushClient jpushClient = new JPushClient(masterSecret_zdccarplatform, appKey_zdccarplatform, 0, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		//params.setReceiverType(ReceiverTypeEnum.REGISTRATION_ID);
		//params.setReceiverValue(registrationID);
		params.setReceiverType(ReceiverTypeEnum.TAG);
		params.setReceiverValue(tag);
	    LOG.info("msgTitle - " + msgTitle);
	    LOG.info("msgContent - " + msgContent);
	    LOG.info("TAG - " + ReceiverTypeEnum.TAG);
	    LOG.info("tag - " + tag);
	    LOG.info("################################################################");
		MessageResult msgResult = jpushClient.sendCustomMessage(msgTitle, msgContent, params, null);
        LOG.debug("responseContent - " + msgResult.responseResult.responseContent);
		if (msgResult.isResultOK()) {
	        LOG.info("msgResult - " + msgResult);
	        LOG.info("messageId - " + msgResult.getMessageId());
	        return "1";
		} else {
		    if (msgResult.getErrorCode() > 0) {
		        // 业务异常
		        LOG.warn("Service error - ErrorCode: "
		                + msgResult.getErrorCode() + ", ErrorMessage: "
		                + msgResult.getErrorMessage());
		        return "0";
		    } else {
		        // 未到达 JPush 
		        LOG.error("Other excepitons - "
		                + msgResult.responseResult.exceptionString);
		        return "-1";
		    }
		}
	}

	
	
	
	/**
	 * 只点热车平台
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public static String SendMsg_ZDCCarHot(String tag,String msgTitle,String msgContent) {
        JPushClient jpushClient = new JPushClient(masterSecret_zdccarhotplatform, appKey_zdccarhotplatform, 0, DeviceEnum.Android, false);
		CustomMessageParams params = new CustomMessageParams();
		//params.setReceiverType(ReceiverTypeEnum.REGISTRATION_ID);
		//params.setReceiverValue(registrationID);
		params.setReceiverType(ReceiverTypeEnum.TAG);
		params.setReceiverValue(tag);
	    LOG.info("msgTitle - " + msgTitle);
	    LOG.info("msgContent - " + msgContent);
	    LOG.info("TAG - " + ReceiverTypeEnum.TAG);
	    LOG.info("tag - " + tag);
	    LOG.info("################################################################");
		MessageResult msgResult = jpushClient.sendCustomMessage(msgTitle, msgContent, params, null);
        LOG.debug("responseContent - " + msgResult.responseResult.responseContent);
		if (msgResult.isResultOK()) {
	        LOG.info("msgResult - " + msgResult);
	        LOG.info("messageId - " + msgResult.getMessageId());
	        return "1";
		} else {
		    if (msgResult.getErrorCode() > 0) {
		        // 业务异常
		        LOG.warn("Service error - ErrorCode: "
		                + msgResult.getErrorCode() + ", ErrorMessage: "
		                + msgResult.getErrorMessage());
		        return "0";
		    } else {
		        // 未到达 JPush 
		        LOG.error("Other excepitons - "
		                + msgResult.responseResult.exceptionString);
		        return "-1";
		    }
		}
	}
}

