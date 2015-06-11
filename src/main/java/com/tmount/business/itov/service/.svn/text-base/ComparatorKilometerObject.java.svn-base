package com.tmount.business.itov.service;

import java.util.Comparator;

import com.tmount.db.itov.dto.MessageDriverKilometer;

public class ComparatorKilometerObject implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		MessageDriverKilometer driverKilometer1=(MessageDriverKilometer)o1;
		MessageDriverKilometer driverKilometer2=(MessageDriverKilometer)o2;

		   //比较开始时间
		String startTime1 = driverKilometer1.getStartTimeStr().replaceAll(":", "").replaceAll("-", "").replaceAll(" ", "");
		String startTime2 = driverKilometer2.getStartTimeStr().replaceAll(":", "").replaceAll("-", "").replaceAll(" ", "");
		
		startTime1 = startTime1.substring(startTime1.length()-9,startTime1.length()-1);
		startTime2 = startTime2.substring(startTime2.length()-9,startTime2.length()-1);
		
		Integer integer1 = Integer.parseInt(startTime1);
		Integer integer2 = Integer.parseInt(startTime2);
		return integer2.compareTo(integer1);
	}
	
}
