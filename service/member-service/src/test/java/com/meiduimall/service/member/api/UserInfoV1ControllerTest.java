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

import com.meiduimall.core.Constants;



/**
 * 会员信息 

/**
 * 会员账号信息API{@link=UserInfoV1Controller}单元测试
 * @author chencong
 *
 */
public class UserInfoV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1ControllerTest.class);
	   
	   /**获取当前会员基本信息*//*
	    @Test
	    public void getMemberBaicInfo() throws Exception{
	    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/get_member_basic_info?memId="+memId))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.status",is(Constants.CONSTANT_INT_ZERO)));
	    	
	    	resultActions.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("单元测试>>获取会员基本信息API>>执行结果:{}",result.getResponse().getContentAsString());
				}
			});
	    } */
	    
}