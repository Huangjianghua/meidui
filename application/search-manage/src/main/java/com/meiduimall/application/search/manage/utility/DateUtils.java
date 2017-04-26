package com.meiduimall.application.search.manage.utility;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtils {



	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}



}
