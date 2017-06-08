/*package com.meiduimall.application.usercenter.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.meiduimall.core.util.JsonUtils;

*//**
 * 提现相关
 * @author jun.wu@meiduimall.com
 *
 *//*
public class WithDrawV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(WithDrawV1ControllerTest.class);
	 
    *//**
     * 提现明细
     * @throws Exception
     *//*
    @Test
    public void queryWithDrawDetail () throws Exception{
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("token","ec697a80e4a8574fda615c8c7b274796");
    	mapCondition.put("id", "00623cf9-a2c8-4995-b914-ff37d52822ea");
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/query_withdraw_detail")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(mapCondition)))
    			.andExpect(status().isOk());
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>提现明细API>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    }
	      
}*/