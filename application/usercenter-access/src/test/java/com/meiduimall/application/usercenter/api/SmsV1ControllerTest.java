package com.meiduimall.application.usercenter.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 短信相关API{@link=SmsV1Controller}}单元测试
 * @author chencong
 *
 */
public class SmsV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(SmsV1ControllerTest.class);
	 

    
    /**获取短信验证码
	 * @throws Exception */
//    @Test
//    public void test001GetValidateCode() throws Exception{
//    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/get_validate_code?type=2&token="+token+"&phone="+phone))
//    			.andExpect(status().isOk())
//    			.andExpect(jsonPath("$.status",is(0)));
//    	
//    	resultActions.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				logger.info("单元测试>>获取短信验证码API>>执行结果:{}",result.getResponse().getContentAsString());;
//			}
//		});
//    }
    
    /**获取短信验证码，不需要token
	 * @throws Exception */
//    @Test
//    public void test002GetValidateCodeNoToken() throws Exception{
//    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/get_validate_code_notoken?type=1&phone="+phone))
//    			.andExpect(status().isOk())
//    			.andExpect(jsonPath("$.status",is(0)));
//    	
//    	resultActions.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				logger.info("单元测试>>获取短信验证码（不需要token）API>>执行结果:{}",result.getResponse().getContentAsString());;
//			}
//		});
//    }
	      
}