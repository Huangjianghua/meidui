package com.meiduimall.service.settlement.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.service.BaseTest;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.model.SmsReqDTO;

public class SmsServiceTest extends BaseTest {
	
	@Autowired
	private SmsService smsService;

	@Test
	public void testSendMessage() {
		SmsReqDTO smsReqDTO = new SmsReqDTO("13798431927",
				ShareProfitUtil.TEMPLATE_ID_O2O_1008,  "15111612373,6500", "");
//		smsService.sendMessage(smsReqDTO);
	}

}
