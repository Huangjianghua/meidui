package com.first.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.common.JunitTests;
import com.first.pojo.MeiduiPoint;

public class MeiduiPointServiceTest extends JunitTests {

	@Autowired
	private MeiduiPointService meiduiPointService;
	
	@Test
	public void testQueryMeiduiPoint() {
		MeiduiPoint meiduiPoint = meiduiPointService.queryMeiduiPoint();
		System.out.println(meiduiPoint);
	}

}
