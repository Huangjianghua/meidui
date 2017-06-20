package com.meiduimall.service.account.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.request.RequestSaveOrder;
import com.meiduimall.service.account.service.TradeService;

/**
 * 订单交易相关API{@link=TradeV1Controller}单元测试
 * @author chencong
 *
 */
public class TradeV1ControllerTest extends BaseControllerTest {

	private final static Logger logger=LoggerFactory.getLogger(TradeV1ControllerTest.class);
	
	private final static String orderId=UUID.randomUUID().toString();
	
	@Autowired
	private TradeService tradeService;
	
	 /**
     * 会员保存订单
     * @author chencong
     * @throws Exception
     */
    @Test
    public void saveOrder() throws Exception{
    	/*RequestSaveOrder requestSaveOrder=new RequestSaveOrder();
    	requestSaveOrder.setMemId(memId);
    	requestSaveOrder.setOrderId(orderId);
    	requestSaveOrder.setConsumeAmount(1200.00);
    	requestSaveOrder.setProductName("苹果7");
    	requestSaveOrder.setOrderSource("1gw");
    	requestSaveOrder.setConsumeMoney(1100.00);
    	requestSaveOrder.setConsumePoints(100.00);
    	
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
    */}
    
    /**
     * 会员取消订单
     * @author chencong
     * @throws Exception
     */
    @Test
    public void cancelOrder() throws Exception{
    	/*RequestCancelOrder requestCancelOrder=new RequestCancelOrder();
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
*/
    }
    
    
    
    
    
    
    
    
	/**
	 * 保存当前会员订单信息接口(免token校验)   --正确的测试
	 * @author wujun
	 */
    @Test
    public void testSaveOrderNotoken_01() throws Exception{
    	/*ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/save_order_notoken")
    			.param("memId", "0000eb24-c1f7-41a3-b7b8-b957078c5da0")
    			.param("orderId", "3")
				.param("consumeAmount", "3.00")
				.param("consumeMoney", "1.00")
				.param("consumePoints", "2")
				.param("productName", "单元测试")
				.param("orderSource", "1gw")
				.param("payType", "2")
				.param("orderStatus", "0"))
    			.andExpect(status().isOk());
    	
    	postResultAction.andDo(new ResultHandler() {
    		@Override
    		public void handle(MvcResult result) throws Exception {
    			logger.info("单元测试>>保存当前会员订单信息接口(免token校验) API>>执行结果:{}",result.getResponse().getContentAsString());
    		}
    	});
    */}
    
    
	/**
	 * 会员退单信息接口    -- 正确的测试
	 * @author wuun
	 */
    @Test
    public void testRecedeOrderOld_01() throws Exception{
    	RequestSaveOrder requestSaveOrder = new RequestSaveOrder();
    	requestSaveOrder.setMemId("48d98556-cc3a-4e41-83d8-8cb2ab14c2d3"); //谢铭澄的memId
    	requestSaveOrder.setOrderId(orderId);
    	requestSaveOrder.setOrderSource("1gw");
    	requestSaveOrder.setOrderStatus(1);
    	requestSaveOrder.setPayType(2);
    	requestSaveOrder.setProductName("测试");
    	requestSaveOrder.setConsumeAmount(3.00);
    	requestSaveOrder.setConsumeMoney(1.00);
    	requestSaveOrder.setConsumePoints(2.00);
        //冻结,待付款
    	tradeService.saveOrder(requestSaveOrder);
    	requestSaveOrder.setOrderStatus(2);
    	//解冻扣减支付成功
    	tradeService.saveOrder(requestSaveOrder);
    	
    	//退款
    	ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.get(baseUrl+"/v1/recede_order_old")
    			.param("memId", requestSaveOrder.getMemId())
    			.param("orderId", requestSaveOrder.getOrderId())
				.param("consumeAmount", requestSaveOrder.getConsumeAmount().toString())
				.param("consumeMoney", requestSaveOrder.getConsumeMoney().toString())
				.param("consumePoints", requestSaveOrder.getConsumePoints().toString())
				.param("orderSource", requestSaveOrder.getOrderSource())
				.param("payType", requestSaveOrder.getPayType().toString())
				.param("orderStatus", requestSaveOrder.getOrderStatus().toString()))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.status",is(0)));
    	
    	postResultAction.andDo(new ResultHandler() {
    		@Override
    		public void handle(MvcResult result) throws Exception {
    			logger.info("单元测试>>当前会员退单信息API>>执行结果:{}",result.getResponse().getContentAsString());
    		}
    	});
    }
    
    /**
	 * 会员退单信息接口   --测试订单状态不正确
	 * @author wuun
	 */
    @Test
    public void testRecedeOrder_02() throws Exception{
    	/*ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/recede_order")
    			.param("memId", "0000eb24-c1f7-41a3-b7b8-b957078c5da0")
    			.param("orderId", "2")
				.param("consumeAmount", "3.00")
				.param("consumeMoney", "1.00")
				.param("consumePoints", "2")
				.param("productName", "单元测试")
				.param("orderSource", "1gw")
				.param("payType", "2")
				.param("orderStatus", "0"))
    			.andExpect(status().isOk());
    	
    	postResultAction.andDo(new ResultHandler() {
    		@Override
    		public void handle(MvcResult result) throws Exception {
    			logger.info("单元测试>>当前会员退单信息API>>执行结果:{}",result.getResponse().getContentAsString());
    		}
    	});
    */}
    
    
    /**
	 * 会员退单信息接口   --测试订单金额不正确
	 * @author wuun
	 */
    @Test
    public void testRecedeOrder_03() throws Exception{
    	/*ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/recede_order")
    			.param("memId", "0000eb24-c1f7-41a3-b7b8-b957078c5da0")
    			.param("orderId", "2")
				.param("consumeAmount", "9.00")
				.param("consumeMoney", "8.00")
				.param("consumePoints", "2")
				.param("productName", "单元测试")
				.param("orderSource", "1gw")
				.param("payType", "2")
				.param("orderStatus", "0"))
    			.andExpect(status().isOk());
    	
    	postResultAction.andDo(new ResultHandler() {
    		@Override
    		public void handle(MvcResult result) throws Exception {
    			logger.info("单元测试>>当前会员退单信息API>>执行结果:{}",result.getResponse().getContentAsString());
    		}
    	});
    */}
    
    
    
    /**
   	 * 会员退单信息接口   --测试订单不存在
   	 * @author wuun
   	 */
       @Test
       public void testRecedeOrder_04() throws Exception{
       	/*ResultActions postResultAction=mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/v1/recede_order")
       			.param("memId", "0000eb24-c1f7-41a3-b7b8-b957078c5da0")
       			.param("orderId", "29854390")
   				.param("consumeAmount", "9.00")
   				.param("consumeMoney", "8.00")
   				.param("consumePoints", "2")
   				.param("productName", "单元测试")
   				.param("orderSource", "1gw")
   				.param("payType", "2")
   				.param("orderStatus", "0"))
       			.andExpect(status().isOk());
       	
       	postResultAction.andDo(new ResultHandler() {
       		@Override
       		public void handle(MvcResult result) throws Exception {
       			logger.info("单元测试>>当前会员退单信息API>>执行结果:{}",result.getResponse().getContentAsString());
       		}
       	});
       */}
    
}
