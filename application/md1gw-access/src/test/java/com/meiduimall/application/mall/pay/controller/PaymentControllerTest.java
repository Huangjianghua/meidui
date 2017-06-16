package com.meiduimall.application.mall.pay.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.application.mall.catalog.BaseTest;

public class PaymentControllerTest extends BaseTest {

	/**
	 * 测试订单号不能为空
	 * @throws Exception
	 */
	@Test
	public void testPayJudgment_test_01() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.get("/md1gwmall/md1gw_access/v1/PayJudgment"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testPayJudgment_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试token不能为空
	 */
	@Test
	public void testPayJudgment_test_02() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.get("/md1gwmall/md1gw_access/v1/PayJudgment")
						.param("token", "")
						.param("user_id", "")
						.param("payment_id", "15110914078241014411")
						.param("use_point", "")
						.param("use_money", "")
						.param("pay_type", "")
						)
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testPayJudgment_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 测试 tokenTOmemId 失败
	 * @throws Exception
	 */
	@Test
	public void testPayJudgment_test_03() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.get("/md1gwmall/md1gw_access/v1/PayJudgment")
						.param("token", "8791413989843219898321")
						.param("user_id", "")
						.param("payment_id", "15110914078241014411")
						.param("use_point", "")
						.param("use_money", "")
						.param("pay_type", "")
						)
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testPayJudgment_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	
	/**
	 * 测试 用户订单金额不一致
	 * @throws Exception
	 */
	@Test
	public void testPayJudgment_test_04() throws Exception {
		ResultActions results = mockMvc
				.perform(MockMvcRequestBuilders.get("/md1gwmall/md1gw_access/v1/PayJudgment")
						.param("token", "8791413989843219898321")
						.param("user_id", "")
						.param("payment_id", "15110914078241014411")
						.param("use_point", "2")
						.param("use_money", "1.00")
						.param("pay_type", "")
						)
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testPayJudgment_test_04*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
