package com.tmount.business.cloopen.restAPI;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class test {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException {
		//getAgentOffWork();
		//getAgentOnWork("0");
		getAgentReady();
	}
	//坐席上班
	private static void getAgentOnWork(String type) throws KeyManagementException, NoSuchAlgorithmException
	{
		RestAPI_AgentOnwork restAPI_AgentOnwork=new  RestAPI_AgentOnwork();
		String ret=	restAPI_AgentOnwork.getAgentOnWork("18646081890", "10013", type,"1");
		System.out.println(ret);
	}
	//坐席下班
	private static void getAgentOffWork() throws KeyManagementException, NoSuchAlgorithmException
	{
		RestAPI_AgentOffwork restAPI_AgentOffwork=new  RestAPI_AgentOffwork();
		String ret=	restAPI_AgentOffwork.getAgentOffWork("18646081890", "10013");
		System.out.println(ret);
	}
	//坐席准备就绪
	private static void getAgentReady() throws KeyManagementException, NoSuchAlgorithmException
	{
		RestAPI_AgentReady restAPI_AgentReady=new  RestAPI_AgentReady();
		String ret=	restAPI_AgentReady.getAgentReady("10013");
		System.out.println(ret);
	}
}
