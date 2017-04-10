package com.meiduimall.service.catalog.test;

import java.util.Date;

import org.junit.Test;

import com.meiduimall.core.util.DateUtils;

public class DateTest {

	@Test
	public void dateTest(){
		
		long l = 1491796230 * 1000l;
		
		Date date = new Date(l);
		
		String datime = DateUtils.formatDatime(date);
		
		System.out.println(datime);
	}
}
