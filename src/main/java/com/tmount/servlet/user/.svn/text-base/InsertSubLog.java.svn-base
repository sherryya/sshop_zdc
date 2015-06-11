package com.tmount.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.tmount.db.ptt.dto.TItovPttSubaccountLog;
import com.tmount.service.PttSubLogService;
import com.tmount.system.SpringApplicationContextFactory;
/**
 * 插入sublog日志信息
 * @author Administrator
 *
 */
public class InsertSubLog extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PttSubLogService pttsublogService;

	/**
	 * Constructor of the object.
	 */
	public InsertSubLog() {
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
		
		ApplicationContext applicationContext = SpringApplicationContextFactory.newInstance();
		pttsublogService = applicationContext.getBean(PttSubLogService.class); //获取service
		String called = request.getParameter("called");
		String caller = request.getParameter("caller");
		String callsid = request.getParameter("callsid");
		String orderid = request.getParameter("orderid");
		String subid = request.getParameter("subid");
		String type = request.getParameter("type");
		String byetype = request.getParameter("byetype");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String billdata = request.getParameter("billdata");
		String recordurl = request.getParameter("recordurl");
		String subtype = request.getParameter("subtype");
		//添加日志
		TItovPttSubaccountLog tItovPttSubaccountLog =new TItovPttSubaccountLog();
		tItovPttSubaccountLog.setCalled(called);
		tItovPttSubaccountLog.setCaller(caller);
		tItovPttSubaccountLog.setCallsid(callsid);
		tItovPttSubaccountLog.setOrderid(orderid);
		tItovPttSubaccountLog.setSubid(subid);
		tItovPttSubaccountLog.setType( Integer.valueOf(type));
		tItovPttSubaccountLog.setBilldata(billdata);
		tItovPttSubaccountLog.setByetype(byetype);
		tItovPttSubaccountLog.setStarttime(starttime);
		tItovPttSubaccountLog.setEndtime(endtime);
		tItovPttSubaccountLog.setRecordurl(recordurl);
		tItovPttSubaccountLog.setSubtype(subtype);
		int flag = pttsublogService.insertAll(tItovPttSubaccountLog);  //插入sublog信息
		String json = "";
		if(flag>0)
		{
			json = "{\"resultCode\":0}";  //插入成功
		}else
		{
			json = "{\"resultCode\":1}";
		}
		PrintWriter out = response.getWriter();
		out.println(json);  //输出json 
		out.flush();
		out.close();

	}

}
