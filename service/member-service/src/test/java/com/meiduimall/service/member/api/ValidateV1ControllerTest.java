package com.meiduimall.service.member.api;

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
 * 校验相关接口单元测试
 * @author chencong
 *
 */
public class ValidateV1ControllerTest extends BaseControllerTest {

	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1ControllerTest.class);
	   
	   /**校验userId在库中是否存在*/
	    @Test
	    public void validateUserIdExists() throws Exception{
	    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/check_userid_exists?userid="+phone))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.status",is(8031)));
	    	
	    	resultActions.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("单元测试>>获取会员基本信息API>>正确的userId>>执行结果:{}",result.getResponse().getContentAsString());
				}
			});
	    	
	    	resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/check_userid_exists?userid=11111111"))
	    	    	.andExpect(status().isOk())
	    	    	.andExpect(jsonPath("$.status",is(0)));
	    	    	
	    	    	resultActions.andDo(new ResultHandler() {
	    				@Override
	    				public void handle(MvcResult result) throws Exception {
	    					logger.info("单元测试>>获取会员基本信息API>>错误的userId>>执行结果:{}",result.getResponse().getContentAsString());
	    				}
	    			});

	    } 
}
