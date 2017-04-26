/*package com.meiduimall.service.member.api;

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
 * 用户信息相关接口单元测试
 * @author chencong
 *
 *//*
public class UserInfoV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1ControllerTest.class);
	   
	   private final String url="/member/account_service/v1/valide_pay_pwd";
	   private final String payPwd="123456";
	   
	   *//**获取用户信息*//*
	    @Test
	    private void setPaypwd() throws Exception{
	    	//get
	    	ResultActions getResultAction=mockMvc.perform(MockMvcRequestBuilders.get(url)
	    			.param("memId",memId).param("pay_pwd",payPwd))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.status",is(0)));
	    	
	    	getResultAction.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					logger.info("exec setPaypwd get result:{}",result.getResponse().getContentAsString());;
				}
			});

	    }
	    
	      
}*/