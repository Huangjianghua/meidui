package com.meiduimall.service.sms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "junit")
@Transactional
public class WeixinControllerTest extends BaseTest {

	/**
	 * 用户未绑定openID
	 * @throws Exception
	 */
	@Test
	public void sendTemplateMessageOnPaySuccess_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_weixin_msg_on_pay")
				.param("phone", "188000000")
				.param("orderTime", "1485632123")
				.param("coupon", "66.66")
				.param("sysKey", "o2o_php")
				.param("storeName", "测试商家")
				.param("addPoint", "88"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendTemplateMessageOnPaySuccess_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 已绑定openID
	 * @throws Exception
	 */
	@Test
	public void sendTemplateMessageOnPaySuccess_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_weixin_msg_on_pay")
				.param("phone", "188000000")
				.param("orderTime", "1486532542")
				.param("coupon", "66.66")
				.param("sysKey", "o2o_php")
				.param("storeName", "测试商家")
				.param("addPoint", "88"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendTemplateMessageOnPaySuccess_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
