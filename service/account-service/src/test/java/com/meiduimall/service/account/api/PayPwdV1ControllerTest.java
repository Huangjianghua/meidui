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
import com.meiduimall.service.account.model.request.RequestUpdatePaypwd;

/**
 * 鏀粯瀵嗙爜鐩稿叧鎺ュ彛鍗曞厓娴嬭瘯
 * @author chencong
 *
 */
public class PayPwdV1ControllerTest extends BaseControllerTest {
	
	private final static Logger logger=LoggerFactory.getLogger(PayPwdV1ControllerTest.class);
	
	/**楠岃瘉鏀粯瀵嗙爜*/
    @Test
    public void validePaypwd() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/valide_pay_pwd")
    			.param("memId",memId)
    			.param("pay_pwd",payPwd))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("鍗曞厓娴嬭瘯>>楠岃瘉鏀粯瀵嗙爜API>>鎵ц缁撴灉:{}",result.getResponse().getContentAsString());;
			}
		});
    }
    
	/**璁剧疆鏀粯瀵嗙爜*/
    @Test
    public void setPaypwd() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/set_pay_pwd")
    			.param("memId",memId)
    			.param("pay_pwd",payPwd))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("鍗曞厓娴嬭瘯>>璁剧疆鏀粯瀵嗙爜API>>鎵ц缁撴灉:{}",result.getResponse().getContentAsString());;
			}
		});
    }
	   
	/**淇敼鏀粯瀵嗙爜*/
    @Test
    public void setPaypwdStatus() throws Exception{
    	RequestUpdatePaypwd requestUpdatePaypwd=new RequestUpdatePaypwd();
    	requestUpdatePaypwd.setMemId(memId);
    	requestUpdatePaypwd.setOld_paypwd("123456");
    	requestUpdatePaypwd.setNew_paypwd("123456");
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/update_pay_pwd")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestUpdatePaypwd)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("鍗曞厓娴嬭瘯>>淇敼鏀粯瀵嗙爜API>>鏃у瘑鐮佹纭殑鎯呭喌>>鎵ц缁撴灉:{}",result.getResponse().getContentAsString());;
			}
		});
    	
    	requestUpdatePaypwd.setMemId(memId);
    	requestUpdatePaypwd.setOld_paypwd("1233456");
    	requestUpdatePaypwd.setNew_paypwd("123456");
        postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/update_pay_pwd")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestUpdatePaypwd)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(7016)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("鍗曞厓娴嬭瘯>>淇敼鏀粯瀵嗙爜API>>鏃у瘑鐮佷笉姝ｇ‘鐨勬儏鍐�>>鎵ц缁撴灉:{}",result.getResponse().getContentAsString());;
			}
		});
    }
    
    
	      
}