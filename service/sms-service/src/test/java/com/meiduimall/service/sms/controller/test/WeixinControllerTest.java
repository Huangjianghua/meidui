package com.meiduimall.service.sms.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.service.sms.test.BaseTest;

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
				.param("phone", "13429832838")
				.param("orderTime", "1486532542")
				.param("coupon", "66.66")
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
