package com.tmount.business.util.format;

import java.text.DecimalFormat;

public class DecimalFormatUtil {
	private static final DecimalFormat df = new DecimalFormat("#.00");
	
    public static String decimal2String(Double d) {
    	if(d<1&&d>-1){
    		return 0+df.format(d);
    	}else{
    		return df.format(d);
    	}
       
    }
    
    public static void main(String [] args){
    	System.out.println(decimal2String(0.01));
    	System.out.println(decimal2String(0.01));
    }
}
