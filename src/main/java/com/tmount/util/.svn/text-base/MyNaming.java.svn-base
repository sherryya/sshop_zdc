package com.tmount.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class MyNaming extends PropertyNamingStrategy {
	@Override
	public String nameForGetterMethod(MapperConfig<?> config,
	         AnnotatedMethod method, String defaultName)
    {
      // Replace underscore+letter with upper-case(letter)
      // (left as exercise to reader!)
      return convertName(defaultName);
    }
	
	private String convertName(String defaultName){
		//System.out.println("@:***"+defaultName+"***BEGIN");
		defaultName = defaultName.replaceAll("A", "_a");
		defaultName = defaultName.replaceAll("B", "_b");
		defaultName = defaultName.replaceAll("C", "_c");
		defaultName = defaultName.replaceAll("D", "_d");
		defaultName = defaultName.replaceAll("E", "_e");
		defaultName = defaultName.replaceAll("F", "_f");
		defaultName = defaultName.replaceAll("G", "_g");
		defaultName = defaultName.replaceAll("H", "_h");
		defaultName = defaultName.replaceAll("I", "_i");
		defaultName = defaultName.replaceAll("J", "_j");
		defaultName = defaultName.replaceAll("K", "_k");
		defaultName = defaultName.replaceAll("L", "_l");
		defaultName = defaultName.replaceAll("M", "_m");
		defaultName = defaultName.replaceAll("N", "_n");
		defaultName = defaultName.replaceAll("O", "_o");
		defaultName = defaultName.replaceAll("P", "_p");
		defaultName = defaultName.replaceAll("Q", "_q");
		defaultName = defaultName.replaceAll("R", "_r");
		defaultName = defaultName.replaceAll("S", "_s");
		defaultName = defaultName.replaceAll("T", "_t");
		defaultName = defaultName.replaceAll("U", "_u");
		defaultName = defaultName.replaceAll("V", "_v");
		defaultName = defaultName.replaceAll("W", "_w");
		defaultName = defaultName.replaceAll("X", "_x");
		defaultName = defaultName.replaceAll("Y", "_y");
		defaultName = defaultName.replaceAll("Z", "_z");
		//System.out.println("@:***"+defaultName+"***END");
		return defaultName;
	}


}
