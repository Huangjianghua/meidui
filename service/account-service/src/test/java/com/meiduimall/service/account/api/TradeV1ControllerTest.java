package com.meiduimall.service.account.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.model.request.RequestCancelOrder;
import com.meiduimall.service.account.model.request.RequestSaveOrder;

/**
 * 订单交易相关API{@link=TradeV1Controller}单元测试
 * @author chencong
 *
 */
public class TradeV1ControllerTest extends BaseControllerTest {

	private final static Logger logger=LoggerFactory.getLogger(TradeV1ControllerTest.class);
	
	private final static String orderId=UUID.randomUUID().toString();
	
	 /**
     * 会员保存订单
     * @author chencong
     * @throws Exception
     */
    @Test
    public void saveOrder() throws Exception{
    	RequestSaveOrder requestSaveOrder=new RequestSaveOrder();
    	requestSaveOrder.setMemId(memId);
    	requestSaveOrder.setOrderId(orderId);
    	requestSaveOrder.setConsumeAmount(50.00);
    	requestSaveOrder.setProductName("苹果7");
    	requestSaveOrder.setOrderSource("1gw");
    	requestSaveOrder.setPayType(2);
    	requestSaveOrder.setConsumeMoney(30.00);
    	requestSaveOrder.setConsumePoints(20.00);
    	
    	//下单未支付
    	requestSaveOrder.setOrderStatus(1);
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/save_order")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestSaveOrder)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>保存订单API>>下单未支付>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    	
    	//订单已支付
    	requestSaveOrder.setOrderStatus(2);
    	postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/save_order")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestSaveOrder)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>保存订单API>>订单已支付>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    }
    
    /**
     * 会员取消订单
     * @author chencong
     * @throws Exception
     */
    @Test
    public void cancelOrder() throws Exception{
    	RequestCancelOrder requestCancelOrder=new RequestCancelOrder();
    	requestCancelOrder.setMemId(memId);
    	requestCancelOrder.setOrderId(orderId);
    	
    	//取消上面已支付的订单
    	requestCancelOrder.setOrderStatus(2);
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/cancel_order")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestCancelOrder)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>取消订单API>>取消上面已支付的订单>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    	
    	//未支付取消订单
    	String newOrderId=UUID.randomUUID().toString();
    	//先下一个未支付的单
    	RequestSaveOrder requestSaveOrder=new RequestSaveOrder();
    	requestSaveOrder.setMemId(memId);
    	requestSaveOrder.setOrderId(newOrderId);
    	requestSaveOrder.setConsumeAmount(50.00);
    	requestSaveOrder.setProductName("苹果8");
    	requestSaveOrder.setOrderSource("1gw");
    	requestSaveOrder.setPayType(2);
    	requestSaveOrder.setConsumeMoney(30.00);
    	requestSaveOrder.setConsumePoints(20.00);
    	requestSaveOrder.setOrderStatus(1);
    	postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/save_order")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestSaveOrder)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>取消订单API>>未支付取消订单>>先下一个未支付的单>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    	
    	//取消上面未支付的订单
    	RequestCancelOrder requestCancelOrder2=new RequestCancelOrder();
    	requestCancelOrder2.setMemId(memId);
    	requestCancelOrder2.setOrderId(newOrderId);
    	requestCancelOrder2.setOrderStatus(1);
    	postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/cancel_order")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtils.beanToJson(requestCancelOrder2)))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>取消订单API>>未支付取消订单>>取消上面未支付的订单>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});

    }
    
    
    
    
    
    
    /**
	 * 当前商家退会员订单信息接口  
	 * @author wujun
	 */
    @Test
    public void businessRecedeOrder() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/business_recede_order")
    			.param("memId", "72063681-7408-435c-88fd-cd837c95c66e")
    			.param("orderId", "1")
				.param("consumeAmount", "2.00")
				.param("consumeMoney", "1.00")
				.param("consumePoints", "1")
				.param("productName", "单元测试")
				.param("orderSource", "1gw")
				.param("payType", "2")
				.param("orderStatus", "2"))
    			.andExpect(status().isOk());
    	
    	postResultAction.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				logger.info("单元测试>>当前商家退会员订单信息API>>执行结果:{}",result.getResponse().getContentAsString());
			}
		});
    }
    
    
	/**
	 * 保存当前会员订单信息接口(免token校验) 
	 * @author wujun
	 */
    @Test
    public void saveOrderNotoken() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/save_order_notoken")
    			.param("memId", "72063681-7408-435c-88fd-cd837c95c66e")
    			.param("orderId", "1")
				.param("consumeAmount", "2.00")
				.param("consumeMoney", "1.00")
				.param("consumePoints", "1")
				.param("productName", "单元测试")
				.param("orderSource", "1gw")
				.param("payType", "2")
				.param("orderStatus", "1"))
    			.andExpect(status().isOk());
    	
    	postResultAction.andDo(new ResultHandler() {
    		@Override
    		public void handle(MvcResult result) throws Exception {
    			logger.info("单元测试>>保存当前会员订单信息接口(免token校验) API>>执行结果:{}",result.getResponse().getContentAsString());
    		}
    	});
    }
    
    
	/**
	 * 当前会员退单信息接口  
	 * @author wuun
	 */
    @Test
    public void recedeOrder() throws Exception{
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/recede_order")
    			.param("memId", "72063681-7408-435c-88fd-cd837c95c66e")
    			.param("orderId", "1")
				.param("consumeAmount", "2.00")
				.param("consumeMoney", "1.00")
				.param("consumePoints", "1")
				.param("productName", "单元测试")
				.param("orderSource", "1gw")
				.param("payType", "2")
				.param("orderStatus", "2"))
    			.andExpect(status().isOk());
    	
    	postResultAction.andDo(new ResultHandler() {
    		@Override
    		public void handle(MvcResult result) throws Exception {
    			logger.info("单元测试>>当前会员退单信息API>>执行结果:{}",result.getResponse().getContentAsString());
    		}
    	});
    }
}
