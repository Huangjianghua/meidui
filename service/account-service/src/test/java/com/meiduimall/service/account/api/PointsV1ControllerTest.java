package com.meiduimall.service.account.api;


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
import com.meiduimall.service.account.model.request.RequestPointTransfer;


/**
 * 积分相关接口单元测试
 * @author chencong
 *
 */
public class PointsV1ControllerTest extends BaseControllerTest {
	

	private final static Logger logger=LoggerFactory.getLogger(PointsV1ControllerTest.class); 

	/**积分流水分页*/

    @Test
    public void listConsumePointsDetail() throws Exception{
    	/*MSConsumePointsDetailGet model=new MSConsumePointsDetailGet();
    	model.setMemId(memId);
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/list_consume_points_detail")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(model)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>积分流水分页API>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});*/
    }
	    
    /**
     * 查询积分转账列表
     * @throws Exception
     * @author: jianhua.huang  2017年5月18日 下午12:27:10
     */
    @Test
    public void queryTransferPointsList() throws Exception{
    	/*RequestPointTransfer model=new RequestPointTransfer();
    	model.setMthNo("4000000207675002");
    	model.setFlag("1");
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/queryPointsTransferList")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(model)))
    			.andExpect(status().isOk());
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>积分转账列表API>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});*/
    }
}