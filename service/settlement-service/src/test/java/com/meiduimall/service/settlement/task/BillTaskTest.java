package com.meiduimall.service.settlement.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.service.BaseTest;

public class BillTaskTest extends BaseTest {
    
	@Autowired
	private BillTask billTask;
	
	@Test
	public void testCreateBill() {
		billTask.createBill();
	}

	@Test
	public void testMergeBilledWaters() {
		billTask.mergeBilledWaters();
	}

}
