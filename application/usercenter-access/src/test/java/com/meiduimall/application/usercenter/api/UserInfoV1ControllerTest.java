/*package com.meiduimall.application.usercenter.api;

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

*//**
 * 会员信息相关API{@link=UserInfoV1Controller}}单元测试
 * @author chencong
 *
 *//*
public class UserInfoV1ControllerTest extends BaseOpV1ControllerTest{

	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1ControllerTest.class);
	
	*//**获取会员基本信息
	 * @throws Exception *//*
    @Test
    public void test001GetMemberBasciInfo() throws Exception{
    	*//**正确的token*//*
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/get_member_basic_info?token="+token))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>获取会员基本信息API>>正确的token>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    	*//**错误的token*//*
    	resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/get_member_basic_info?token=1212121212121"))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(9013)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>获取会员基本信息API>>错误的token>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    }
}
*/