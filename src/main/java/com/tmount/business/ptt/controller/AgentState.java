package com.tmount.business.ptt.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.business.cloopen.restAPI.RestAPI_AgentReady;
import com.tmount.business.ptt.service.PttCallLogService;
import com.tmount.business.ptt.service.PttEmpAgentService;
import com.tmount.db.ptt.dto.TItovPttCallLog;
import com.tmount.db.ptt.dto.TItovPttEmpAgent;
import com.tmount.exception.ShopBusiException;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBaseByHttp;
import com.tmount.util.ResponseData;

/**
 * 座席状态通知 当座席的状态发生变化后，云平台会向应用服务器侧发送此请求消息，其中“agentstate”相对url地址段是固定内容不可变。
 * 
 * @author
 * 
 */
@Controller
public class AgentState extends ControllerBaseByHttp {
	Logger logger = Logger.getLogger(AgentState.class.getName());

	@Autowired
	private PttCallLogService pttCallLogService;
	
	@Autowired
	private PttEmpAgentService pttEmpAgentService;
	
	@RequestMapping(value = "agentstate")
	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected String doService(HttpServletRequest request)
			throws ShopException, JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~agentstate is bigining");
		int  agentid = Integer.parseInt(request.getParameter("agentid"));// 座席ID，4位正整数，由应用侧管理。
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~agentid~~~~~~~~~~~~~~~~~~~~~~"+agentid);
		int  agentstate = Integer.parseInt(request.getParameter("agentstate"));// 座席状态：0准备中 1准备就绪 2用户锁定 3咨询通话中,4坐席拒接。默认值为0。
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~agentstate~~~~~~~~~~~~~~~~~~~~~~"+agentstate);
		String callid = request.getParameter("callid");// 呼叫id，当状态为1时，此值不为空。默认值为空。
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~callid~~~~~~~~~~~~~~~~~~~~~~"+callid);
		String time = request.getParameter("time");// 状态切换时间，yyyymmddHHmiss。
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~time~~~~~~~~~~~~~~~~~~~~~~"+time);
		//int queuetype = Integer.parseInt(request.getParameter("queuetype"));// 用户排队类型，当agentstate变为2、3时此值有效。
		//logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~queuetype~~~~~~~~~~~~~~~~~~~~~~"+queuetype);
		//不能直接就用callid因为有空值，首次一定是空值
		if(callid!=null){
			//1.根据callid寻找原来坐席的状态,
			TItovPttCallLog tItovPttCallLog =new TItovPttCallLog();
			tItovPttCallLog.setCallid(callid);
			tItovPttCallLog = pttCallLogService.selectByCallId(tItovPttCallLog);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = null;
			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(tItovPttCallLog!=null){
				if(tItovPttCallLog.getAgentstate()!=null){
					int old_agent_state = tItovPttCallLog.getAgentstate();
					//2.根据agentstate状态来判断流程。来修改通话流水表t_itov_ptt_call_log的状态
					logger.info("!!!!!!!!!!!!!!!!!!!agentstate!!!!!!!!!!!!!!!!!!!!!!!!!"+agentstate);
					if(agentstate==0){
						//0准备中
						logger.info("!!!!!!!!!!!!!!!!!!!old_agent_state!!!!!!!!!!!!!!!!!!!!!!!!!"+old_agent_state);
						if(old_agent_state==3||old_agent_state==2||old_agent_state==4){
							tItovPttCallLog.setAgentid(agentid);
							tItovPttCallLog.setAgentstate(1);//1----准备就绪
							if(old_agent_state!=4){
								tItovPttCallLog.setEndettime(date);
							}
							pttCallLogService.update(tItovPttCallLog);
							//调用坐席状态改变接口，将当前状态变为1----准备就绪
							RestAPI_AgentReady restAPI_AgentReady=new  RestAPI_AgentReady();
							String ret = "";
							try {
								ret = restAPI_AgentReady.getAgentReady(String.valueOf(agentid));
							} catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println(ret);
						}else if(old_agent_state==1){
							tItovPttCallLog.setAgentid(agentid);
							tItovPttCallLog.setAgentstate(agentstate);
							pttCallLogService.update(tItovPttCallLog);
							//调用坐席状态改变接口，将当前状态变为1----准备就绪
							RestAPI_AgentReady restAPI_AgentReady=new  RestAPI_AgentReady();
							String ret = "";
							try {
								ret = restAPI_AgentReady.getAgentReady(String.valueOf(agentid));
							} catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println(ret);
						}else{
							tItovPttCallLog.setAgentid(agentid);
							tItovPttCallLog.setAgentstate(agentstate);
							pttCallLogService.update(tItovPttCallLog);
						}
					}else if(agentstate==1){
						//1准备就绪
						tItovPttCallLog.setAgentid(agentid);
						tItovPttCallLog.setAgentstate(agentstate);
						pttCallLogService.update(tItovPttCallLog);
					}else if(agentstate==2){
						//已经进入队列，客服状态为2--------------锁定中
						tItovPttCallLog.setAgentid(agentid);
						tItovPttCallLog.setStarttime(date);
						tItovPttCallLog.setAgentstate(agentstate);
						pttCallLogService.update(tItovPttCallLog);
					}else if(agentstate==3){
						//通话中，客户状态为3 -------通话中
						tItovPttCallLog.setAgentid(agentid);
						
						tItovPttCallLog.setStarttime(date);
						tItovPttCallLog.setAgentstate(agentstate);
						pttCallLogService.update(tItovPttCallLog);
					}
				}else{
					if(agentstate==2){
						//已经进入队列，客服状态为2--------------锁定中
						tItovPttCallLog.setAgentid(agentid);
						tItovPttCallLog.setStarttime(date);
						tItovPttCallLog.setAgentstate(agentstate);
						pttCallLogService.update(tItovPttCallLog);
					}else{
						tItovPttCallLog.setAgentid(agentid);
						tItovPttCallLog.setAgentstate(agentstate);
						pttCallLogService.update(tItovPttCallLog);
					}
				}
			}else{
				throw new ShopBusiException(ShopBusiErrorBundle.ERR_PTT_CALLID, new Object[]{callid});
			}
			
		}
		logger.info("!!!!!!!!!!!!!!!!!!!t_itov_ptt_emp_agent_start!!!!!!!!!!!!!!!!!!!!!!!!!");
		//更新t_itov_ptt_emp_agent表中agentstate
		TItovPttEmpAgent tItovPttEmpAgent = new TItovPttEmpAgent();
		tItovPttEmpAgent.setAgentId(agentid);
		tItovPttEmpAgent.setAgentstate(agentstate+"");
		pttEmpAgentService.updateAgentStateByAgentID(tItovPttEmpAgent);
		logger.info("!!!!!!!!!!!!!!!!!!!t_itov_ptt_emp_agent_end!!!!!!!!!!!!!!!!!!!!!!!!!");
		/*
		 * 格式
		 * 参考：http://docs.cloopen.com/index.php/IVR_%E5%9D%90%E5%B8%AD%E9%80%9A%E7%9F%A5%E6%B6%88%E6%81%AF
		 */
		String xml = "";
		logger.info("xml"+xml);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~agentstate is end");
		return xml;
	}

	@Override
	protected ResponseData doGetErrorInfo() throws ShopException,
			JsonGenerationException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
