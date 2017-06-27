package com.meiduimall.service.settlement.task;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.service.BaseTest;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.service.AgentService;

public class DepositRetryTaskTest extends BaseTest {

//	@Autowired
//	private DepositRetryTask depositRetryTask;
	
	@Autowired
	private AgentService agentService;
	
	@Test
	public void testRetryShareDeposit() {
		Map<String, String> systemSetting = agentService.quertSharefit();
		int score = Integer.parseInt(systemSetting.get(ShareProfitConstants.NEWBIE_PERSON_POINT));//新加盟个代获得积分 6500
		System.out.println("&&&&&&&&&&"+score);
//		depositRetryTask.retryShareDeposit();
	}

}