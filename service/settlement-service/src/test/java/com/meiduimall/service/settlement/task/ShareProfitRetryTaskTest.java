package com.meiduimall.service.settlement.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.service.BaseTest;

public class ShareProfitRetryTaskTest extends BaseTest {
	
	@Autowired
	private ShareProfitRetryTask shareProfitRetryTask;

	@Test
	public void testRetryOrderShareProfit() {
		shareProfitRetryTask.retryOrderShareProfit();
	}

}
