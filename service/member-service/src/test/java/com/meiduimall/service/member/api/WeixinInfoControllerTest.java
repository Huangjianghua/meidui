package com.meiduimall.service.member.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class WeixinInfoControllerTest extends BaseControllerTest {
	
	/**
	 * 已绑定
	 * @throws Exception
	 */
	@Test
	public void bindingWeixinOpenID_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/member_service/v1/bindingWeixinOpenID")
				.param("phone", "13418786965")
				.param("openID", "openID-1111"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("bindingWeixinOpenID_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}

	/**
	 * 用户未注册
	 * @throws Exception
	 */
	@Test
	public void bindingWeixinOpenID_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/member_service/v1/bindingWeixinOpenID")
				.param("phone", "13501236547")
				.param("openID", "openID-1111"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("bindingWeixinOpenID_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 绑定成功
	 * @throws Exception
	 */
	@Test
	public void bindingWeixinOpenID_test_3() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/member_service/v1/bindingWeixinOpenID")
				.param("phone", "13429832838")
				.param("openID", "openID-1111"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("bindingWeixinOpenID_test_3*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 获取正常
	 * @throws Exception
	 */
	@Test
	public void getOpenIDByPhone_test_1() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/member_service/v1/getOpenIDByPhone")
				.param("phone", "13429832838"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getOpenIDByPhone_test_1*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	/**
	 * 用户未绑定openID
	 * @throws Exception
	 */
	@Test
	public void getOpenIDByPhone_test_2() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/member/member_service/v1/getOpenIDByPhone")
				.param("phone", "13429830038"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("getOpenIDByPhone_test_2*********" + result.getResponse().getContentAsString());
			}
		});
	}
}
