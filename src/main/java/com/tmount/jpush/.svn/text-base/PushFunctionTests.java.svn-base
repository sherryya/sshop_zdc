package com.tmount.jpush;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.tmount.jpush.common.DeviceEnum;
import com.tmount.jpush.push.IosExtras;
import com.tmount.jpush.push.MessageResult;
import com.tmount.jpush.push.NotificationParams;
import com.tmount.jpush.push.ReceiverTypeEnum;

public class PushFunctionTests {
    //private static final String appKey ="dd1066407b044738b6479275";
	
	private static final String appKey ="2e8530247ce95a0e08cddd84";
    private static final String masterSecret = "251efd43b8f57a0a677cd766";
    
    private static final String TAG = "tag_api";
    private static final String ALIAS = "alias_api";
    private static final String MSG_CONTENT = "测试用例";
    private static final int SUCCEED_RESULT_CODE = 0;
	
    private JPushClient jpushAndroid = null;
    private JPushClient jpushIos = null;
    
    
    @Before
    public void before() {
    	System.out.println("0");
        jpushAndroid = new JPushClient(masterSecret, appKey, 0, DeviceEnum.Android, false);
        jpushIos = new JPushClient(masterSecret, appKey, 0, DeviceEnum.IOS, false);
    }
	
	
	@Test
	public void sendNotificationAll_android(){
		System.out.println("1");
		MessageResult result = jpushAndroid.sendNotificationAll(MSG_CONTENT);
		assertEquals(0, result.getErrorCode());
	}
	
	@Test
	public void sendNotificationAll_ios(){
		System.out.println("2");
        HashMap<String, Object> extra = new HashMap<String, Object>();
		extra.put("jpush-key","jpush-value");
		IosExtras iosExtra = new IosExtras(1,"test.mp3");
		extra.put("ios", iosExtra);
		
		NotificationParams params = new NotificationParams();
		params.setReceiverType(ReceiverTypeEnum.APP_KEY);
		
		MessageResult result = jpushIos.sendNotification(MSG_CONTENT, params, extra);
		assertEquals(SUCCEED_RESULT_CODE, result.getErrorCode());
	}
	
	@Test
    public void sendNotificationWithAlias() {
		System.out.println("3");
        NotificationParams params = new NotificationParams();
        params.setReceiverType(ReceiverTypeEnum.ALIAS);
        params.setReceiverValue(ALIAS);
        
        MessageResult result = jpushAndroid.sendNotification(MSG_CONTENT, params, null);
		assertEquals(SUCCEED_RESULT_CODE, result.getErrorCode());
	}
	
	@Test
	public void sendNotificationWithAlias_ios(){
		System.out.println("4");
        HashMap<String, Object> extra = new HashMap<String, Object>();
        extra.put("jpush-key","jpush-value");
        IosExtras iosExtra = new IosExtras(1,"test.mp3");
        extra.put("ios", iosExtra);
		
        NotificationParams params = new NotificationParams();
        params.setReceiverType(ReceiverTypeEnum.ALIAS);
        params.setReceiverValue(ALIAS);
        
        MessageResult result = jpushIos.sendNotification(MSG_CONTENT, params, extra);
		assertEquals(SUCCEED_RESULT_CODE, result.getErrorCode());
	}
	
	@Test
	public void sendNotificationWithTag(){
        NotificationParams params = new NotificationParams();
        params.setReceiverType(ReceiverTypeEnum.TAG);
        params.setReceiverValue(TAG);
        
        MessageResult result = jpushAndroid.sendNotification(MSG_CONTENT, params, null);
		assertEquals(SUCCEED_RESULT_CODE, result.getErrorCode());
	}
	
	@Test
	public void sendNotificationWithTagByExtra(){
        HashMap<String, Object> extra = new HashMap<String, Object>();
        extra.put("jpush-key","jpush-value");
        IosExtras iosExtra = new IosExtras(1,"test.mp3");
        extra.put("ios", iosExtra);
        
        NotificationParams params = new NotificationParams();
        params.setReceiverType(ReceiverTypeEnum.ALIAS);
        params.setReceiverValue(ALIAS);
		
        MessageResult result = jpushAndroid.sendNotification(MSG_CONTENT, params, null);
		assertEquals(SUCCEED_RESULT_CODE, result.getErrorCode());
	}
}


