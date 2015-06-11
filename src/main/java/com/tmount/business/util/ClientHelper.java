package com.tmount.business.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class ClientHelper {
	
	/**
	 * 	�����ַ
	 */
	public static final String LOGON_SITE_LOGIN = "10.110.0.206";
	/**
	 * �˿ں�
	 */
	public static final int LOGON_PORT = 28001;

	public static HttpClient client = null;
	
	public static String cook = "";

	public ClientHelper(String LOGON_SITE,int LOGON_PORT) {
		super();
		client = new HttpClient();
		client.getParams().setContentCharset("utf-8");
		client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT, "http");
		//client.getHostConfiguration().setProxy("10.110.0.250", 8080);
	}

	public HttpClient getClient() {
		return client;
	}

	public int post(String target, NameValuePair[] paraList) throws HttpException, IOException {
		int statusCD;
	    //Inner class for UTF-8 support    
	   
		PostMethod authpost = new PostMethod(target);
		authpost.setRequestBody(paraList);
		client.executeMethod(authpost);
		statusCD = authpost.getStatusCode();
		authpost.releaseConnection();
		return statusCD;
	}
	//�ύ�?����ȡ������Ϣ
	public String postToGetString(ClientHelper clientHelper,String target, NameValuePair[] paraList) throws Exception {
		int statusCD;
		PostMethod authpost = new PostMethod(target);
		authpost.setRequestBody(paraList);
		client.executeMethod(authpost);
		statusCD = authpost.getStatusCode();
		System.out.println(statusCD);
		InputStream is =authpost.getResponseBodyAsStream(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
		String temp; 
		StringBuffer htmlBuffer=new StringBuffer(); 
		while((temp=br.readLine())!=null){ 
		htmlBuffer.append(temp);
		}
		String content= htmlBuffer.toString();
		authpost.releaseConnection();
		/*if(statusCD!=200){
			return "";
		}*/
		return content;
	}
	public void setCookies() {
		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
		cookiespec.match(LOGON_SITE_LOGIN, LOGON_PORT, "/",
				false, client.getState().getCookies());
	}
	
	public InputStream getStream(String target) throws Exception {
		GetMethod get = new GetMethod(target);
		client.executeMethod(get);
		InputStream is = get.getResponseBodyAsStream();
		get.releaseConnection();
		return is;
	}
	
	public String get(String target) throws Exception {
		GetMethod get = new GetMethod(target);
		//get.setRequestHeader("Cookie", Cookies);
		client.executeMethod(get);
		String content = get.getResponseBodyAsString();
		get.releaseConnection();
		return content;
	}
	public String get(String target,String Cookies) throws Exception {
		GetMethod get = new GetMethod(target);
		get.setRequestHeader("Cookie", Cookies);
		client.executeMethod(get);
		String content = get.getResponseBodyAsString();
		get.releaseConnection();
		return content;
	}

	//*�Զ��庯����
	/**
	 * ��ȡ��֤��
	 * @param target
	 * @throws Exception
	 */
	public Image getVeryfiy(String target) throws Exception {
		GetMethod get = new GetMethod(target);
		get.setRequestHeader("Referer","http://login.sina.com.cn/signup/signup.php");
		client.executeMethod(get);
		File storeFile = new File("verify.gif");   
		FileOutputStream output = new FileOutputStream(storeFile);   
		//�õ�������Դ���ֽ�����,��д���ļ�   
		output.write(get.getResponseBody());   
		output.close();   
		Image image=Toolkit.getDefaultToolkit().createImage("verify.gif");
		get.releaseConnection();
		return image;
	}
	public Image getVeryfiy2(String target) throws Exception {
		GetMethod get = new GetMethod(target);
		get.setRequestHeader("Referer","http://login.sina.com.cn/signup/signup1.php");
		get.setRequestHeader("Cookie", cook);
		get.getParams().setParameter(HttpMethodParams.SINGLE_COOKIE_HEADER, true);  
		client.executeMethod(get);
		File storeFile = new File("verify.gif");   
		FileOutputStream output = new FileOutputStream(storeFile);   
		//�õ�������Դ���ֽ�����,��д���ļ�   
		output.write(get.getResponseBody());   
		output.close();   
		Image image=Toolkit.getDefaultToolkit().createImage("verify.gif");
		get.releaseConnection();
		return image;
	}
	//ע���˺ż���Ƿ��ظ�
	public String register(ClientHelper clientHelper,String loginNo,String From) throws Exception{
		
		String loginTarget = "/signup/check_user.php";
		NameValuePair user = new NameValuePair("name", loginNo);
		NameValuePair from = new NameValuePair("from", From);
		String content = "";
		content = clientHelper.postToGetString(clientHelper,loginTarget, new NameValuePair[] {
					user, from});
		System.out.println("�����Ƿ��ͻ"+content);
		
		return content;
	}
	/*act	2 //�����ǵڶ�������˼
	after	on //ͬ������
	corp	
	door	exych //��֤�� ����
	entry	sso
	mailType	cn //����.cn
	mcheck	61a869f4e28cd687daa08c4318b7a50f //����
	nick	hello //�ǳ� �����
	otherQid	//
	password	soji214 //����1 �����
	password2	soji214 //����2 
	pwdA	123456 //�ܱ� �ֻ����λ
	referer	980efe521296927968a67d0c98c2efa1 //����
	returnURL	//����
	selectQid	//���ֻ����ĺ�6λ?
	username	soji214 //����*/ 
/*
 * ע��ڶ�����ע��ɹ����һ��
 */
public static String sinaSencond(ClientHelper clientHelper,String username,
						String door,String nick,String password) throws Exception{
		
		String loginTarget = "/signup/signup1.php";
		NameValuePair v1 = new NameValuePair("act", "2");
		NameValuePair v2 = new NameValuePair("after", "on");
		NameValuePair v3 = new NameValuePair("corp", "");
		NameValuePair v4 = new NameValuePair("door", door);
		System.out.println("door"+door.trim());
		NameValuePair v5 = new NameValuePair("entry", "freemail");
		NameValuePair v17 = new NameValuePair("from", "regmail");
		NameValuePair v6 = new NameValuePair("mailType", "cn");
		NameValuePair v7 = new NameValuePair("mcheck", "");
		NameValuePair v8 = new NameValuePair("notifyType", "4");
		NameValuePair v9 = new NameValuePair("otherQid", "");
		NameValuePair v10 = new NameValuePair("password", password);
		NameValuePair v11 = new NameValuePair("password2", password);
		NameValuePair v12 = new NameValuePair("pwdA", "123456");
		NameValuePair v13 = new NameValuePair("referer", "980efe521296927968a67d0c98c2efa1");
		NameValuePair v14 = new NameValuePair("returnURL", "");
		NameValuePair v15 = new NameValuePair("selectQid", "���ֻ����ĺ�6λ��");
		NameValuePair v16 = new NameValuePair("username",username);
		NameValuePair v18 = new NameValuePair("user_name",username);
		
		System.out.println(v16);
		
		String content = "";
		content = clientHelper.postToGetString(clientHelper,loginTarget, new NameValuePair[] {
					v1, v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18});
		
		System.out.println(content);
		
		return content;
	}
	
	//sina��¼
	public String LoginIn(ClientHelper clientHelper,String username,String password) throws Exception{
		
		String loginTarget = "/sso/login.php?client=ssologin.js(v1.3.7)";
		NameValuePair user = new NameValuePair("username", username);
		NameValuePair pwd = new NameValuePair("password", password);
		NameValuePair callback = new NameValuePair("callback", "parent.sinaSSOController.loginCallBack");
		NameValuePair client = new NameValuePair("client", "ssologin.js(v1.3.7)");
		NameValuePair encoding = new NameValuePair("encoding", "GB2312");
		NameValuePair entry = new NameValuePair("entry", "blog");
		NameValuePair from = new NameValuePair("from", "referer:blog.sina.com.cn/");
		NameValuePair gateway = new NameValuePair("gateway", "1");
		NameValuePair returntype = new NameValuePair("returntype","IFRAME"); 
		NameValuePair savestate = new NameValuePair("savestate", "0");
		NameValuePair service = new NameValuePair("service", "blog");
		NameValuePair setdomain = new NameValuePair("setdomain", "1");
		NameValuePair useticket = new NameValuePair("useticket", "0");
		String content = "";
		content = clientHelper.postToGetString(clientHelper,loginTarget, new NameValuePair[] {
				user, pwd,callback,client,encoding,entry,from,gateway,returntype,savestate,service,setdomain,useticket});
		return content;
	}
	//�漰��¼ ������P3P
	public String getP3P(String target,String Cookies) throws Exception {
	GetMethod get = new GetMethod(target);
	get.setRequestHeader("Cookie", Cookies);
	get.setRequestHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
	client.executeMethod(get);
	String content = get.getResponseBodyAsString();
	get.releaseConnection();
	return content;
}

	public static void main(String[] args) throws Exception{
		
		ClientHelper ch=new ClientHelper(LOGON_SITE_LOGIN,LOGON_PORT);
		int i=0;
		//while(true)
		//{
			System.out.println(ch.get("http://api.map.baidu.com/ag/coord/convert?from=2&to=4&mode=1&x=116.3786889372559,116.38632786853031,116.39534009082035,116.40624058825688,116.41413701159672&y=39.90762965106183,39.90795884517671,39.907432133833574,39.90789300648029,39.90795884517671&callback=callback"));
		String x = "MTE2LjM4NTEyODk3MDM2";
		//	++i;
		//	System.out.println(i);
		//}
		/*//sinaע�� 
		/*System.setProperty("apache.commons.httpclient.cookiespec", "COMPATIBILITY");
		ClientHelper ch=new ClientHelper(LOGON_SITE_LOGIN,LOGON_PORT);
		//��֤ע���˺�
		System.out.println(ch.get("/signup/signup.php?entry=freemail"));
		
		String s=ch.register(ch,"aabbcc123xx3x","cnmail");
		if("no".equals(s.trim())){
			System.out.println("���˺�δ��ռ��");
		}else if("yes".equals(s.trim())){
			System.out.println("���˺��ѱ�ռ��");
		}
		else{
			System.out.println("sina�Ѿ����£������Ҫ������޷�ʹ��");
		}
		
		//���ͼƬ
		Scanner in1=new Scanner(System.in);
		
		ch.getVeryfiy("/cgi/pin.php?r=19639259");
		
		String sg = in1.next();
		String s1 = sinaSencond(ch,"aabbcc123xx3x",sg,"nihaoa","ssssss");
		System.out.println(s1);*/
		
		//sina��¼
		/*System.setProperty("apache.commons.httpclient.cookiespec", "COMPATIBILITY");
		ClientHelper ch=new ClientHelper(LOGON_SITE_LOGIN,LOGON_PORT);
		client.getParams().setContentCharset("utf-8");
		System.out.println(ch.LoginIn(ch, "songjianixd@sina.cn", "songjia"));
		Cookie[] cookies = client.getState().getCookies();
        String cks="";
		for (int i = 0; i < cookies.length; i++) 
        {
            cks=cks+cookies[i].toString()+";";
        	System.out.println("cookie:" + cookies[i].toString());
     
        }*/
		//���������ǹؼ�
		//System.out.println(ch.getP3P("http://control.blog.sina.com.cn/blogprofile/index.php",cks));
		
		/*//��¼�ɹ�+cookie��֤.
		String content = ch.get("/cgi/login/home.php?menu=",cks);

		String user_id= ContentHelper.getMidString(content, "http://portrait3.sinaimg.cn/", "/blog/180");
		System.out.println(user_id);
		
		// http://blog.sina.com.cn/ �������ӣ�
		ch.get("http://blog.sina.com.cn/u/"+user_id);
		*/
		
		//System.out.println(ch.get("http://blog.sina.com.cn/"));
		//System.out.println(ch.get("http://control.blog.sina.com.cn/myblog/htmlsource/blog_notopen.php?uid=1865263090&version=7",cks));
		//http://control.blog.sina.com.cn/myblog/htmlsource/blog_notopen.php?uid=1865263090&version=7
       // System.out.println(ch.get("http://control.blog.sina.com.cn/admin/article/article_add.php?index",cks));
        
        	
       



	}
}
