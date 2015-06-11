package com.tmount.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.db.ptt.vo.HostSubAccountLog;
import com.tmount.service.PttSubService;
import com.tmount.system.MD5;
import com.tmount.system.SpringApplicationContextFactory;
/**
 * 用户登录查询
 * 20141210
 * @author Administrator
 *
 */
public class Userlogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PttSubService accountService;
	/**
	 * Constructor of the object.
	 */
	public Userlogin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationContext applicationContext = SpringApplicationContextFactory.newInstance();//new ClassPathXmlApplicationContext("classpath*:springAppContext.xml");
		accountService = applicationContext.getBean(PttSubService.class);
		String account_name = request.getParameter("account_name");
		String pwd = request.getParameter("pwd");
		String hostType = request.getParameter("hostType");
		String name = new String(account_name.getBytes("ISO-8859-1"), "utf8"); 
		HostSubAccountLog subAccountLog = new HostSubAccountLog();
		subAccountLog.setAccount_name(name);
		subAccountLog.setHostType(hostType);
		subAccountLog.setAccount_password(MD5.getMD5(pwd));
		//根据用户名和密码查询用户信息
		List<HostSubAccountLog> list= accountService.userLoginByaccountName(subAccountLog);
		Type listType = new TypeToken<HostSubAccountLog>(){}.getType();
	    Gson gson=new Gson();
	    if(list!=null && list.size()>0)
	    {
	    	list.get(0).setResultStatus("1");  //如果根据用户名和密码能查到用户信息则设置状态为1
	    }else
	    {
	    	HostSubAccountLog sublog = new HostSubAccountLog();
	    	sublog.setResultStatus("0");  //如果用户不存在则json中此状态为0
	    	list.add(sublog);
	    	
	    }
	    String json=gson.toJson(list.get(0), listType);  //json 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(json);   //out json
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
