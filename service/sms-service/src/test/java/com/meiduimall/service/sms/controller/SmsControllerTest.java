package com.meiduimall.service.sms.controller;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "junit")
@Transactional
public class SmsControllerTest {

	public MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Rule
	public MockServerRule server1 = new MockServerRule(this, 9901);
	
	@Rule
	public MockServerRule server2 = new MockServerRule(this, 9902);
	
	private String aliSuccess = "{ \"success\":true }";
	@SuppressWarnings("unused")
	private String aliFail = "{ \"sub_code\":\"isv.BUSINESS_LIMIT_CONTROL\" }";
	
	private String mandaoSuccess = "<soap:Body><mdSmsSend_uResult>888888888888</mdSmsSend_uResult></soap:Body>";
	@SuppressWarnings("unused")
	private String mandaoFail = "<soap:Body><mdSmsSend_uResult>-2</mdSmsSend_uResult></soap:Body>";
	
	private final String phone = "188000000";
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		// 模拟阿里大于发送
		MockServerClient mockClient = new MockServerClient("localhost", 9901);
		
		mockClient.when(
		        request()
		            .withPath("/aliyun/test")
		    ).respond(
		        response()
		            .withStatusCode(200)
		            .withBody(aliSuccess)
		    );
		
		// 模拟漫道短信发送
		MockServerClient mockClient2 = new MockServerClient("localhost", 9902);
		mockClient2.when(
		        request()
		            .withPath("/mandao/test")
		    ).respond(
		        response()
		            .withStatusCode(200)
		            .withBody(mandaoSuccess)
		    );
	}

	// 阿里大于模板未注册，通过漫道发送短信
	@Test
	public void sendSmsMessage_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", phone)
				.param("templateId", "1GW_1003")
				.param("sysKey", "junit")
				.param("timeout", "90")
				.param("params", "DW1234567899"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 发送普通短信，模板ID不存在
	@Test
	public void sendSmsMessage_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", phone)
				.param("templateId", "O2O_1111")
				.param("sysKey", "junit")
				.param("params", "DD201704271103111,666.66"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 通过阿里大于发送短信
	@Test
	public void sendSmsMessage_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", phone)
				.param("sysKey", "junit")
				.param("templateId", "1GW_1004")
				.param("params", "88.88,66元购物券"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 指定发送渠道---使用阿里大于发送短信
	@Test
	public void sendSmsMessage_test_04() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", phone)
				.param("sysKey", "junit")
				.param("supplierId", "1")
				.param("templateId", "1GW_1004")
				.param("params", "88.88,66元购物券"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 指定发送渠道---使用漫道发送短信
	@Test
	public void sendSmsMessage_test_05() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", phone)
				.param("sysKey", "junit")
				.param("supplierId", "2")
				.param("templateId", "1GW_1004")
				.param("params", "88.88,66元购物券"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsMessage_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 阿里大于模板未注册，通过漫道发送验证码
	@Test
	public void sendSmsVerificationCode_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "MEM_1002")
				.param("type", "regist")
				.param("sysKey", "junit")
				.param("timeout", "60"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 通过阿里大于发送验证码
	@Test
	public void sendSmsVerificationCode_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "O2O_1002")
				.param("sysKey", "junit")
				.param("type", "regist")
				.param("timeout", "180"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 重复发送验证码
	@Test
	public void sendSmsVerificationCode_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "O2O_1002")
				.param("type", "regist")
				.param("sysKey", "junit")
				.param("timeout", "180"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 指定发送渠道---通过阿里大于发送验证码
	@Test
	public void sendSmsVerificationCode_test_04() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "O2O_1002")
				.param("sysKey", "junit")
				.param("supplierId", "1")
				.param("type", "regist")
				.param("timeout", "180"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 指定发送渠道---通过漫道发送验证码
	@Test
	public void sendSmsVerificationCode_test_05() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "O2O_1002")
				.param("sysKey", "junit")
				.param("supplierId", "2")
				.param("type", "regist")
				.param("timeout", "180"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("sendSmsVerificationCode_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 校验验证码--验证码错误
	@Test
	public void checkSmsVerificationCode_test_01() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "MEM_1002")
				.param("sysKey", "junit")
				.param("type", "regist")
				.param("verificationCode", "165412"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkSmsVerificationCode_test_01*********" + result.getResponse().getContentAsString());
			}
		});
	}

	// 校验验证码--验证码已过期
	@Test
	public void checkSmsVerificationCode_test_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "O2O_1111")
				.param("sysKey", "junit")
				.param("type", "regist")
				.param("verificationCode", "111222"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkSmsVerificationCode_test_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 校验验证码--缺少请求参数
	@Test
	public void checkSmsVerificationCode_test_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone)
				.param("type", "regist")
				.param("sysKey", "junit")
				.param("templateId", "O2O_1002"))
				.andExpect(status().isOk());

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("checkSmsVerificationCode_test_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
}