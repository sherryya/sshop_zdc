package test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.tmount.business.ptt.service.StringUtil;

public class FileTest {
	public FileTest()
	{
		
	}
	public void FileTest1() {
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod("http://218.8.127.3:10089/androidpn/servlet/sendNotificationAPI"); 			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		int mobile_code = (int)((Math.random()*9+1)*100000);
		//您预约车载设备的短信验证码为：【变量】，请于【变量】分钟内正确输入。
		String minute="2";
		 String content = new String("您预约车载设备的短信验证码为："+mobile_code+"，请于"+minute+"分钟内正确输入。"); 
			NameValuePair[] data = {//提交短信
				    new NameValuePair("apiKey", "1234567890"), 
				    new NameValuePair("title", "xiaocai"), 
				    new NameValuePair("message", content),
				    new NameValuePair("uri", ""), 
				    new NameValuePair("sendtype", "1"),
				    new NameValuePair("username",  "782567b86a4045dca5526fe2a0bc481e"),
			};		
		method.setRequestBody(data);				
			try {
				client.executeMethod(method);
				String SubmitResult =method.getResponseBodyAsString();	
				System.out.println(SubmitResult);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	public static void main(String[] args) {
		new FileTest().FileTest1();
	}
}
	







