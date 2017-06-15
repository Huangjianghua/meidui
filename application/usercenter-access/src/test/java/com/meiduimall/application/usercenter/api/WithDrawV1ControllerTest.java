package com.meiduimall.application.usercenter.api;

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

/**
 * 提现相关
 * @author jun.wu@meiduimall.com
 *
 */
public class WithDrawV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(WithDrawV1ControllerTest.class);
	 
    /**
     * 提现明细
     * @throws Exception
     */
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
    
    
    /**
     * 
     * 提现申请查询手续费接口
     * @throws Exception
     */
    @Test
	public void testGetBankWithdrawDepositsFreeForApp() throws Exception {
    	Map<String, Object> mapCondition=new HashMap<>();
    	mapCondition.put("token","5e8ff2ab947c02d06f69f56f6bcd72d5");
    	mapCondition.put("allow_withdraw_balance", "1200");
    	String object=JsonUtils.beanToJson(mapCondition);
    		try{
    		ResultActions results = mockMvc.perform(MockMvcRequestBuilders
    				.post(baseUrl+"/get_withdraw_poundage")
    				.contentType(MediaType.APPLICATION_JSON).content(object))
    				.andExpect(status().isOk());
    		
    		results.andDo(new ResultHandler() {
    			@Override
    			public void handle(MvcResult result) throws Exception {
    				System.out.println("*********" + result.getResponse().getContentAsString());
    			}
    		});
    		}catch(Exception e){
    			System.out.println("异常*********************"+e);
    		}
    
	}
	      
}