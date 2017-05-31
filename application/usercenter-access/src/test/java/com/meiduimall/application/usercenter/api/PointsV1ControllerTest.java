//package com.meiduimall.application.usercenter.api;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.ResultHandler;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.alibaba.fastjson.JSONObject;
//
///**
// * 积分相关接口{@link=PointsV1Controller}
// * @author chencong
// *
// */
//public class PointsV1ControllerTest extends BaseControllerTest {
//	
//	private final static Logger logger=LoggerFactory.getLogger(PointsV1ControllerTest.class);
//	 
//	/**积分流水（分页）
//	 * @throws Exception */
//    @Test
//    public void test001ListConsumePointsDetail() throws Exception{
//    	JSONObject json=new JSONObject();
//    	json.put("token",token);
//    	json.put("pageSize","1");
//    	json.put("pageNum","10");
//    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/list_consume_points_detail")
//    			.contentType(MediaType.APPLICATION_JSON_VALUE)
//    			.content(json.toJSONString()))
//    			.andExpect(status().isOk())
//    			.andExpect(jsonPath("$.status",is(0)));
//    	
//    	resultActions.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				logger.info("单元测试>>积分流水（分页）API>>执行结果:{}",result.getResponse().getContentAsString());;
//			}
//		});
//    }
//	      
//}