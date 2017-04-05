package com.meiduimall.application.search.utility;

import java.math.BigDecimal;


public class NonnegativeUtil {
	
	public static String getNonnegative(Object object, String memId) {
		if (object == null) {
			return "0";
		}
		String value = object.toString();
		if (value.equals("")) {
			return "0";
		}
		
		Double figure1 = Double.valueOf(DESC.firstDeyption(value, memId));
		//Float figure2 = (float)(Math.round(figure1*10000))/10000;
		if (figure1 > 0) {
			BigDecimal bd = new BigDecimal(figure1); 
			return DoubleCalculate.getFormalValue(bd.toPlainString());
		} else {
			return "0";
		}
	}
}
