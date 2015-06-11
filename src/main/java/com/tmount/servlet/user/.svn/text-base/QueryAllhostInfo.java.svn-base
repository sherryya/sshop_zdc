package com.tmount.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.db.host.dto.TZdcHostUser;
import com.tmount.db.ptt.vo.HostSubAccountLog;
import com.tmount.service.PttSubService;
import com.tmount.system.SpringApplicationContextFactory;
import com.tmount.util.ParamData;
/**
 * 视频在线需要servlet 查询全部主播信息page
 * @author Administrator
 *
 */
public class QueryAllhostInfo extends HttpServlet {

	private PttSubService accountService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		accountService = applicationContext.getBean(PttSubService.class);
		//int pageSize = ParamData.getInt(requestParam.getBodyNode(), "pageSize"); // 每页多少条
		//Integer pageNo = new Integer(ParamData.getInt(
		//		requestParam.getBodyNode(), "pageNum", -1));// 第几页
		
		String pageSize =request.getParameter("pageSize");
		String  pageNum = request.getParameter("pageNum");
		String voip_status = request.getParameter("flag");
		int page_size =10;
		int pageNo = 1;
		if(null != pageSize && !"".equals(pageSize))
		{
			page_size = Integer.parseInt(pageSize);
		}else
		{
			page_size = 10;
		}
		TZdcHostUser tZdcHostUser = new TZdcHostUser();
		//查询主播
		tZdcHostUser.setHostType("0");
		if(StringUtils.isNotBlank(voip_status))
		{
			tZdcHostUser.setVoip_status(Integer.valueOf(voip_status));
		}
		
		if(null != pageNum && !"".equals(pageNum))
		{
			pageNo =  Integer.parseInt(pageNum);
			
		}else
		{
			pageNo = 1;
			
		}
		tZdcHostUser.setPageSize(page_size);
		int startLimit = (pageNo - 1) * page_size;
		tZdcHostUser.setStartLimit(startLimit);
		List<TZdcHostUser> hostList = accountService.selectAll(tZdcHostUser);
		String resultCode = "";
		if(hostList!=null && hostList.size()>0)
		{
			resultCode = "{\"reslutCode\":0,\"list\":";  //成功
		}else
		{
			resultCode = "{\"reslutCode\":1,\"list\":";  //失败
		}
		Type listType = new TypeToken<ArrayList<TZdcHostUser>>(){}.getType();
	    Gson gson=new Gson();
	    String json = gson.toJson(hostList, listType);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//out.println(resultCode);
		out.println(resultCode+json+"}");
		out.flush();
		out.close();
	}

}
