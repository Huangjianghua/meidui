package com.meiduimall.payment.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("dev")
public class AlipayControllerTest {
	
	
	public MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	 
	 
	 @Before
	 public void setup() {
	  // 构建空WebApplicationContext对象, 并构建HelloController对象
	  mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	 
	 
	

	 @Test
	 public void testAlipay() throws Exception {



	 
	  
	  //String json = "{\"memId\":\"94898596\",\"payType\":1,\"orderInfo\":{\"body\":\"商品\",\"orderNo\":\"oed87878\",\"orderTime\":\"\",\"tradeNo\":\"458758gh9\",\"orderAmount\":\"\",\"payAmount\":\"700\",\"cashAmount\":\"\",\"integral\":\"\",\"merchantId\":\"\"},\"notifyUrl\":\"\"}";
	  String json = "{\"memId\":\"1234567891234654\",\"payType\":1,\"merchantId\":\"1386826202\",\"orderAmount\":\"20\",\"payAmount\":\"20\",\"cashAmount\":\"0\",\"notifyUrl\":\"http://payd.meiduimall.com/md1gwmall/md1gw_access/v1/getWXPayNotify\",\"orderNo\":\"17041116192179508837\",\"orderTime\":\"2017-04-11 16:19:21\",\"tradeNo\":\"17041116196445388371\",\"integral\":\"0\",\"accountType\":\"1\"}";
	  //创建测试请求
	 
	  String responseString = mvc.perform(post("/pay/payment-service/v1/payment").contentType(MediaType.APPLICATION_JSON).content(json)).
              andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	  
	  System.out.println(responseString);
	 
	    
	 
	
    }
	 
	 

}



