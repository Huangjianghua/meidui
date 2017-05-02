/*package com.meiduimall.service.account.api;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.model.MSConsumePointsDetailGet;

*//**
 * 积分相关接口单元测试
 * @author chencong
 *
 *//*
public class PointsV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(PointsV1ControllerTest.class); 
	
	/**积分流水分页
    @Test
    public void listConsumePointsDetail() throws Exception{
    	MSConsumePointsDetailGet model=new MSConsumePointsDetailGet();
    	model.setMemId(memId);
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/list_consume_points_detail")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(model)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>积分流水分页API>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    }
	      
}*/