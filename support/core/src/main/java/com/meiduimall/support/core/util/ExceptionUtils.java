package com.meiduimall.support.core.util;

import org.apache.commons.lang3.StringUtils;

public class ExceptionUtils {
	
	
    public static String getFullStackTrace(Throwable throwable){
    	 StackTraceElement[] stackElements = throwable.getStackTrace();
    	 StringBuffer sb=new StringBuffer("[");
         if (stackElements != null) {            
             for (int i = 0; i < stackElements.length; i++) {
            	 sb.append("[");
            	 sb.append("\""+stackElements[i].getClassName()).append("\",");
            	 sb.append("\""+stackElements[i].getFileName()).append("\",");
            	 sb.append("\""+stackElements[i].getLineNumber()).append("\",");
            	 sb.append("\""+stackElements[i].getMethodName()).append("\"],");
             }
         }
         String str=StringUtils.removeEnd(sb.toString(), ",");
         return str+"]";
    }

}
