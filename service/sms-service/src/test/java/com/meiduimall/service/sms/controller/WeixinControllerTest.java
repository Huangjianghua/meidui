package com.meiduimall.service.sms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.meiduimall.service.sms.request.WXMsgOnPaySuccessRequest;
import com.meiduimall.service.sms.service.WeixinService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "junit")
@Transactional
public class WeixinControllerTest extends BaseTest {
	
	@Autowired
	private WeixinService weixinService;

	/**
	 * 用户未绑定openID
	 * @throws Exception
	 */
	@Test
	public void testSendTemplateMessageOnPaySuccess_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_weixin_msg_on_pay")
				.param("phone", "188000000")
				.param("orderTime", "1485632123")
				.param("coupon", "66.66")
				.param("sysKey", "o2o_php")
				.param("storeName", "测试商家")
				.param("addPoint", "88"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status",is(7017)));

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testSendTemplateMessageOnPaySuccess_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 已绑定openID
	 * @throws Exception
	 */
	@Test
	public void testSendTemplateMessageOnPaySuccess_02() throws Exception {
		
		WXMsgOnPaySuccessRequest model = new WXMsgOnPaySuccessRequest();
		model.setPhone("13418786965");
		model.setOrderTime(1486532542l);
		model.setCoupon("66.66");
		model.setSysKey("o2o_php");
		model.setStoreName("测试商家");
		model.setAddPoint("88");
		try {
			weixinService.getMemberInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!Strings.isNullOrEmpty(model.getOpenID())){
			// 已绑定OpenID，但是openID不匹配
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_weixin_msg_on_pay")
					.param("phone", model.getPhone())
					.param("orderTime", String.valueOf(model.getOrderTime()))
					.param("coupon", model.getCoupon())
					.param("sysKey", model.getSysKey())
					.param("storeName", model.getStoreName())
					.param("addPoint", model.getAddPoint()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7018)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendTemplateMessageOnPaySuccess_02*********" + result.getResponse().getContentAsString());
				}
			});
		} else{
			// 未绑定OpenID
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/send_weixin_msg_on_pay")
					.param("phone", model.getPhone())
					.param("orderTime", String.valueOf(model.getOrderTime()))
					.param("coupon", model.getCoupon())
					.param("sysKey", model.getSysKey())
					.param("storeName", model.getStoreName())
					.param("addPoint", model.getAddPoint()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7017)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendTemplateMessageOnPaySuccess_02*********" + result.getResponse().getContentAsString());
				}
			});
		}
		
	}
}
