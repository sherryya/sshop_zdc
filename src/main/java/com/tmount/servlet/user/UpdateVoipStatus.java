package com.tmount.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.tmount.db.ptt.dto.TItovPttSubaccount;
import com.tmount.service.PttSubService;
import com.tmount.system.SpringApplicationContextFactory;

public class UpdateVoipStatus extends HttpServlet {

	private PttSubService accountService;
	/**
	 * Constructor of the object.
	 */
	public UpdateVoipStatus() {
		super();
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
		String voip_status = request.getParameter("voip_status");
		String voipAccount = request.getParameter("voipAccount");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String lastLoginTime = sdf.format(new Date());
		ApplicationContext applicationContext = SpringApplicationContextFactory.newInstance();
		accountService = applicationContext.getBean(PttSubService.class);
		TItovPttSubaccount tItovPttSubaccount = new TItovPttSubaccount();
		tItovPttSubaccount.setVoip_status(Integer.valueOf(voip_status));
		tItovPttSubaccount.setVoipaccount(voipAccount);
		tItovPttSubaccount.setLastLoginTime(lastLoginTime);
		int flag = accountService.updateVoipStatus(tItovPttSubaccount); //更新voip_status和lastLoginTime
        String json="";
        if(flag>0)
        {
        	json = "{\"reslutCode\":0}";  //更新成功
        }else
        {
        	json = "{\"reslutCode\":1}";   //更新失败
        }
		PrintWriter out = response.getWriter();
		out.println(json);  //输出json 
		out.flush();
		out.close();
	}

}
