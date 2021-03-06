package com.meiduimall.application.usercenter.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;


/**
 * 会员基本操作
 * @author chencong
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseOpV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(BaseOpV1ControllerTest.class);
	 


    
    /**会员登出 
	 * @throws Exception */
    @Test
    public void exit () throws Exception{
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("token","ec697a80e4a8574fda615c8c7b274796");
    /*	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/exit")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(mapCondition)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>会员登陆退出API>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});*/
    }
    
    
    /**
     * 提现申请
     * @throws Exception
     * @author: jianhua.huang  2017年5月4日 下午12:33:22
     */
    @Test
    public void saveBankWithDraw () throws Exception{
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
    }
    /**
     * 外部充值
     */
    @Test
    public void externalRecharge () throws   Exception{
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("md_user","18898447755");
    	mapCondition.put("client_id", "KFJT");
    	mapCondition.put("biz_id", "201707161019554832697");
    	mapCondition.put("recharge_amout","233.2");
    	mapCondition.put("recharge_type", "KFCZ1");
    	mapCondition.put("req_time", "1497844432438");
    	mapCondition.put("sign", "4DA2F6746CE61A5B5BAF6BD77741E555");
    	mapCondition.put("callback_url", "http://xsb001kf171.abc123rt.com/recharge_receive.php");
    	ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/externalRecharge")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(mapCondition)))
    			.andExpect(status().isOk());
    	
    	resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>>执行结果:{}",result.getResponse().getContentAsString());;
			}
		});
    }       
}