package com.meiduimall.service.sms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.meiduimall.redis.util.RedisUtils;
import com.meiduimall.service.sms.constant.SysConstant;
import com.meiduimall.service.sms.request.SendCodeRequest;
import com.meiduimall.service.sms.request.SendMessageRequest;
import com.meiduimall.service.sms.service.SmsService;

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
	
	private final String sysKey = "junit";
	
	@Autowired
	private SmsService smsService;
	
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

	// 阿里大于模板未注册，通过漫道发送普通短信
	@Test
	public void testSendSmsMessage_01() throws Exception {
		
		String params = "DW1234567800";
		String templateId = "1GW_1003";
		
		SendMessageRequest model = new SendMessageRequest();
		model.setPhones(phone);
		model.setTemplateId(templateId);
		model.setSysKey(sysKey);
		model.setTimeout(90);
		model.setParams(params);
		long ttl = smsService.getSmsMessageTTL(model);
		if(ttl > 0){
			// 重复发送
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("sysKey", sysKey)
					.param("timeout", "90")
					.param("params", params))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsMessage_01*********" + result.getResponse().getContentAsString());
				}
			});
		} else{
			// 正常发送
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("sysKey", sysKey)
					.param("timeout", "90")
					.param("params", params))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(0)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsMessage_01*****results****" + result.getResponse().getContentAsString());
				}
			});
			assertTrue(smsService.getSmsMessageTTL(model) > 80);
			
			// 重复发送
			ResultActions results2 = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("sysKey", sysKey)
					.param("timeout", "90")
					.param("params", params))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results2.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsMessage_01****results2重复发送*****" + result.getResponse().getContentAsString());
				}
			});
		}
		
	}
	
	// 发送普通短信，模板ID不存在
	@Test
	public void testSendSmsMessage_02() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", phone)
				.param("templateId", "O2O_1111")
				.param("sysKey", "junit")
				.param("params", "DD201704271103111,666.66"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status",is(7102)));

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testSendSmsMessage_02*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 指定发送渠道---使用漫道发送短信
	@Test
	public void testSendSmsMessage_03() throws Exception {
		
		String params = "88.88,66元购物券";
		String templateId = "1GW_1004";
		String supplierId = "2";
		
		SendMessageRequest model = new SendMessageRequest();
		model.setPhones(phone);
		model.setSysKey(sysKey);
		model.setParams(params);
		model.setTemplateId(templateId);
		model.setSupplierId(supplierId);
		
		long ttl = smsService.getSmsMessageTTL(model);
		if(ttl > 0){
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
					.param("phones", phone)
					.param("sysKey", sysKey)
					.param("templateId", templateId)
					.param("supplierId", supplierId)
					.param("params", params))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsMessage_03*********" + result.getResponse().getContentAsString());
				}
			});
		} else{
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
					.param("phones", phone)
					.param("sysKey", sysKey)
					.param("templateId", templateId)
					.param("supplierId", supplierId)
					.param("params", params))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(0)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsMessage_03*********" + result.getResponse().getContentAsString());
				}
			});
			assertTrue(smsService.getSmsMessageTTL(model) > 50);
		}
	}
	
	// 指定发送渠道---使用阿里大于发送短信
	@Test
	public void testSendSmsMessage_04() throws Exception {
		
		String params = "88.88,66元购物券";
		String templateId = "1GW_1004";
		String supplierId = "1";
		
		SendMessageRequest model = new SendMessageRequest();
		model.setPhones(phone);
		model.setSysKey(sysKey);
		model.setParams(params);
		model.setTemplateId(templateId);
		model.setSupplierId(supplierId);
		
		long ttl = smsService.getSmsMessageTTL(model);
		if(ttl > 0){
			// 重复发送
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
					.param("phones", phone)
					.param("sysKey", sysKey)
					.param("templateId", templateId)
					.param("supplierId", supplierId)
					.param("params", params))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsMessage_04*********" + result.getResponse().getContentAsString());
				}
			});
		} else{
			// 单元测试使用的阿里地址为本地地址，所有会发送失败
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
					.param("phones", phone)
					.param("sysKey", sysKey)
					.param("templateId", templateId)
					.param("supplierId", supplierId)
					.param("params", params))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7002)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsMessage_04*********" + result.getResponse().getContentAsString());
				}
			});
		}
	}
	
	// 不指定发送渠道---手机号带86
	@Test
	public void testSendSmsMessage_05() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", "86188000001")
				.param("sysKey", "junit")
				.param("templateId", "1GW_1004")
				.param("params", "88.88,66元购物券"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testSendSmsMessage_05*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 不指定发送渠道---手机号带+86
	@Test
	public void testSendSmsMessage_06() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_common_sms_message")
				.param("phones", "+86188000002")
				.param("sysKey", "junit")
				.param("templateId", "1GW_1004")
				.param("params", "88.88,66元购物券"))
				.andExpect(status().isOk());
		
		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testSendSmsMessage_06*********" + result.getResponse().getContentAsString());
			}
		});
	}
		
	// 阿里大于模板未注册，通过漫道发送验证码
	@Test
	public void testSendSmsVerificationCode_01() throws Exception {
		
		String templateId = "MEM_1002";
		String type = "findPwd";
		
		SendCodeRequest model = new SendCodeRequest();
		model.setPhones(phone);
		model.setTemplateId(templateId);
		model.setType(type);
		model.setSysKey(sysKey);
		long ttl = smsService.getSmsVerificationCodeTTL(model);
		if(ttl > 540){
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("type", type)
					.param("sysKey", sysKey))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsVerificationCode_01*********" + result.getResponse().getContentAsString());
				}
			});
		} else{
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("type", type)
					.param("sysKey", sysKey))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(0)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsVerificationCode_01****results*****" + result.getResponse().getContentAsString());
				}
			});
			assertTrue(smsService.getSmsVerificationCodeTTL(model) > 590);
			
			// 重复发送
			ResultActions results2 = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("type", type)
					.param("sysKey", sysKey))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results2.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsVerificationCode_01****results2重复发送*****" + result.getResponse().getContentAsString());
				}
			});
		}
	}
	
	// 通过阿里大于发送验证码
	@Test
	public void testSendSmsVerificationCode_02() throws Exception {
		
		String templateId = "O2O_1002";
		String type = "findPwd";
		
		SendCodeRequest model = new SendCodeRequest();
		model.setPhones(phone);
		model.setTemplateId(templateId);
		model.setType(type);
		model.setSysKey(sysKey);
		long ttl = smsService.getSmsVerificationCodeTTL(model);
		if(ttl > 540){
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("type", type)
					.param("sysKey", sysKey))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsVerificationCode_02*********" + result.getResponse().getContentAsString());
				}
			});
		} else{
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("type", type)
					.param("sysKey", sysKey))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(0)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsVerificationCode_02*********" + result.getResponse().getContentAsString());
				}
			});
			assertTrue(smsService.getSmsVerificationCodeTTL(model) > 590);
		}
	}
	
	// 指定发送渠道---通过阿里发送验证码
	@Test
	public void testSendSmsVerificationCode_03() throws Exception {
		
		String templateId = "O2O_1002";
		String type = "findPwd";
		String supplierId = "1";
		
		SendCodeRequest model = new SendCodeRequest();
		model.setPhones(phone);
		model.setTemplateId(templateId);
		model.setType(type);
		model.setSysKey(sysKey);
		model.setSupplierId(supplierId);
		long ttl = smsService.getSmsVerificationCodeTTL(model);
		if(ttl > 540){
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("type", type)
					.param("supplierId", supplierId)
					.param("sysKey", sysKey))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7008)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsVerificationCode_03*********" + result.getResponse().getContentAsString());
				}
			});
		} else{
			// 单元测试使用的阿里地址为本地地址，所有会发送失败
			ResultActions results = mockMvc.perform(
					MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
					.param("phones", phone)
					.param("templateId", templateId)
					.param("supplierId", supplierId)
					.param("type", type)
					.param("sysKey", sysKey))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.status",is(7002)));
			
			results.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					System.out.println("testSendSmsVerificationCode_03*********" + result.getResponse().getContentAsString());
				}
			});
		}
	}
	
	// 校验验证码--验证码正确
	@Test
	public void testCheckSmsVerificationCode_01() throws Exception {
		String templateId = "O2O_1002";
		String type = "findPwd";
		
		// 1、发送验证码
		ResultActions results1 = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
				.param("phones", phone)
				.param("templateId", templateId)
				.param("type", type)
				.param("sysKey", sysKey))
				.andExpect(status().isOk());
		results1.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testCheckSmsVerificationCode_01****results1*****" + result.getResponse().getContentAsString());
			}
		});
		
		// 2、取出验证码
		SendCodeRequest model = new SendCodeRequest();
		model.setPhones(phone);
		model.setTemplateId(templateId);
		model.setType(type);
		model.setSysKey(sysKey);
		String redisKey = model.getPhones() + SysConstant.MESSAGE_CODE_KEY + model.getTemplateId() + model.getType()
		+ model.getSysKey();
		String verificationCode = RedisUtils.get(redisKey);
		// 切割字符串，取出验证码 916817##1494323395427
		String[] split = verificationCode.split(SysConstant.CODE_SPLIT_KEY);
		if (split != null && split.length > 0) {
			verificationCode = split[0];
		}
		
		// 3、校验验证码
		ResultActions results2 = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone)
				.param("templateId", templateId)
				.param("sysKey", sysKey)
				.param("type", type)
				.param("verificationCode", verificationCode))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status",is(0)));

		results2.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testCheckSmsVerificationCode_01****results2*****" + result.getResponse().getContentAsString());
			}
		});
		
		// 4、第二次校验校验验证码--验证码已过期
		ResultActions results3 = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone)
				.param("templateId", templateId)
				.param("sysKey", sysKey)
				.param("type", type)
				.param("verificationCode", verificationCode))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status",is(7005)));

		results3.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testCheckSmsVerificationCode_01****results3*****" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 校验验证码--验证码错误
	@Test
	public void testCheckSmsVerificationCode_02() throws Exception {
		String templateId = "O2O_1002";
		String type = "findPwd";
		
		// 1、发送验证码
		ResultActions results1 = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/send_sms_verification_code")
				.param("phones", phone)
				.param("templateId", templateId)
				.param("type", type)
				.param("sysKey", sysKey))
				.andExpect(status().isOk());
		results1.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testCheckSmsVerificationCode_01****results1*****" + result.getResponse().getContentAsString());
			}
		});
		
		// 2、不用取出验证码
		
		// 3、校验验证码
		ResultActions results2 = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone)
				.param("templateId", templateId)
				.param("sysKey", sysKey)
				.param("type", type)
				.param("verificationCode", "00000"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status",is(7006)));

		results2.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testCheckSmsVerificationCode_01****results2*****" + result.getResponse().getContentAsString());
			}
		});
	}

	// 校验验证码--验证码已过期(未发送该验证码)
	@Test
	public void testCheckSmsVerificationCode_03() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone)
				.param("templateId", "templateId")
				.param("sysKey", sysKey)
				.param("type", "type")
				.param("verificationCode", "111222"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status",is(7005)));

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testCheckSmsVerificationCode_03*********" + result.getResponse().getContentAsString());
			}
		});
	}
	
	// 校验验证码--请求参数错误
	@Test
	public void testCheckSmsVerificationCode_04() throws Exception {
		ResultActions results = mockMvc.perform(
				MockMvcRequestBuilders.post("/notify/short_msg_service/v1/new/check_sms_verification_code")
				.param("phones", phone))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status",is(7105)));

		results.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("testCheckSmsVerificationCode_04*********" + result.getResponse().getContentAsString());
			}
		});
	}
}