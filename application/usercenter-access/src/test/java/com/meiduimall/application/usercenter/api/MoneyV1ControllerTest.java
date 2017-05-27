//package com.meiduimall.application.usercenter.api;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.HashMap;
//import java.util.Map;
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
//import com.meiduimall.core.util.JsonUtils;
//
///**
// * 余额相关接口{@link=MoneyV1Controller}
// * @author chencong
// *
// */
//public class MoneyV1ControllerTest extends BaseControllerTest {
//	
//	private final static Logger logger=LoggerFactory.getLogger(MoneyV1ControllerTest.class);
//	 
//	/**余额流水（分页）
//	 * @throws Exception */
//    @Test
//    public void test001ListAccountDetail() throws Exception{
//    	JSONObject json=new JSONObject();
//    	json.put("token",token);
//    	json.put("pageSize","1");
//    	json.put("pageNum","10");
//    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/list_account_detail")
//    			.contentType(MediaType.APPLICATION_JSON_VALUE)
//    			.content(json.toJSONString()))
//    			.andExpect(status().isOk())
//    			.andExpect(jsonPath("$.status",is(0)));
//    	
//    	resultActions.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				logger.info("单元测试>>余额流水（分页）API>>执行结果:{}",result.getResponse().getContentAsString());;
//			}
//		});
//    }
//    
//    /**
//     * 提现申请
//     * @throws Exception
//     * @author: jianhua.huang  2017年5月4日 下午12:33:22
//     */
//    @Test
//    public void saveBankWithDraw () throws Exception{
//    	Map<String, Object> mapCondition=new HashMap<>();
//    	mapCondition.put("token","ec697a80e4a8574fda615c8c7b274796");
//    	mapCondition.put("accountNo", "1234546913454");
//    	mapCondition.put("applyCarryCash", 3.01);
//    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/save_withdraw")
//    			.contentType(MediaType.APPLICATION_JSON_UTF8)
//    			.content(JsonUtils.beanToJson(mapCondition)))
//    			.andExpect(status().isOk());
//    	
//    	resultActions.andDo(new ResultHandler() {
//			@Override
//			public void handle(MvcResult result) throws Exception {
//				logger.info("单元测试>>会员提现申请API>>执行结果:{}",result.getResponse().getContentAsString());;
//			}
//		});
//    }
//	      
//}