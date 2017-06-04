package com.meiduimall.service.account.api;



import java.math.BigDecimal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
 
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstDataAppSource;
import com.meiduimall.service.account.model.request.MemberConsumeMessageReq;

import com.meiduimall.service.account.model.request.RequestSaveOrder;
import com.meiduimall.service.account.model.request.RequestCancelOrder;
import com.meiduimall.service.account.service.TradeService;
import com.meiduimall.service.account.util.SerialStringUtil;

/**
 * 订单交易相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class TradeV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(TradeV1Controller.class);
	
	@Autowired
	private TradeService tradeService;
	
	/**
	 * 会员保存订单
	 * @author chencong
	 */
	@PostMapping(value="/save_order")
	ResBodyData  freezeUnfreeze(@Valid RequestSaveOrder model){
		logger.info("收到保存订单API请求   ：{}",model.toString());
		return tradeService.saveOrder(model);
	}
	
	/**
	 * 会员取消订单
	 * @param model
	 * @return
	 * @throws MdSysException
	 */
	@PostMapping(value="/cancel_order")
	ResBodyData unfreezeDeduct(@Valid RequestCancelOrder model) throws MdSysException {
		logger.info("收到会员取消订单API请求 ：{}",model.toString());
		return tradeService.cancelOrder(model);
	}
	

/*	@RequestMapping(value="/accountTradeCancel",method=RequestMethod.POST)
	public void accountTradeCancel(){
		final String title = "交易取消[accountTradeCancel] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(orderService.accountTradeCancel(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	*//**
	 * 会员发起退款交易申请 <br/>
	 * 当前业务系统库表未支持此类业务申请
	 *//*
	@RequestMapping(value="/accountTradeRefundApply",method=RequestMethod.POST)
	public void accountTradeRefundApply(){
		final String title = "会员发起退款交易申请[accountTradeRefundApply] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(orderService.accountTradeRefundAffirm(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	*//**
	 * 会员发起退款交易确认，退款成功后增加相应退款的积分与余额
	 *//*
	@RequestMapping(value="/accountTradeRefundAffirm",method=RequestMethod.POST)
	public void accountTradeRefundAffirm(){
		final String title = "退款交易确认[accountTradeRefundAffirm] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(orderService.accountTradeRefundAffirm(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	*//**
	 * 会员现金余额提现申请
	 * <li>冻结提现的现金余额
	 * <li>记录冻结明细
	 *//*
	@RequestMapping(value="/bankWithdrawDepositApply",method=RequestMethod.POST)
	public void bankWithdrawDepositApply(){
		final String title = "现金余额提现申请[bankWithdrawDepositApply] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(orderService.bankWithdrawDepositApply(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}
	
	*//**
	 * 会员提现申请查询
	 *//*
	@RequestMapping(value="/getBankWithdrawDeposits",method=RequestMethod.POST)
	public void getBankWithdrawDeposits(){
		final String title = "现金余额提现申请查询[getBankWithdrawDeposits] ";
		final JSONObject resultJson = new JSONObject();
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			
			JSONObject requestJson = HttpClientUtil.readStreamToJsonObject(request);
			Logger.info(inputMsgStr, title ,requestJson.toJSONString());
			
			resultJson.putAll(orderService.getBankWithdrawDeposits(requestJson));
			
			Logger.info(outputMsgStr, title ,resultJson.toJSONString());
			
			appendReturnJSON(resultJson);
			
			writer.print(resultJson.toJSONString());
		} catch (Exception e) {
			Logger.error(errMsgStr, title , e.getMessage());
			writer.println(RuntimeExceptionProcess(e).toJSONString());
		}
	}*/
	
	
	
	
	/**
	 * 当前商家退会员订单信息接口  http://IP:PORT/Authorized/BusinessRecedeOrder
	 * @author wujun
	 */
	@PostMapping(value = "/business_recede_order")
	ResBodyData businessRecedeOrder(@Validated JSONObject obj)   {
		String memId = obj.getString("mem_id");
		String orderId = obj.getString("order_id");// 用户产品的订单号
		String consumerMoney = obj.getString("consumer_money");// 消费金额
		String productName = obj.getString("product_name");// 消费项目名称
		String backIntegral = obj.getString("back_integral");// 消费返还积分数量
		String orderSource = obj.getString("order_source");// 订单来源如果是O2O平台请直接填写O2O,如果是1gw(商城)就直接填写1gw
		String orginalUserId = obj.getString("orginal_user_id");// O2O或1gw系统会员ID
		String mchPayType = obj.getString("pay_type");// 支付类型
		String mchStatus = obj.getString("status");
		String consumeCouponCount = obj.getString("consume_coupon_count");// 消费劵支付金额(必须小于等于消费金额)
		String shoppingCouponCount = obj.getString("shopping_coupon_count"); 
		logger.info("BusinessRecedeOrder接口请求输入参数："+ obj.toString());
		
		try {
			logger.info("用户产品的订单号="+orderId+";消费金额="+consumerMoney);
			logger.info("消费项目名称="+productName+";消费返还积分数量="+backIntegral);
			logger.info("订单来源="+orderSource+";系统会员ID="+orginalUserId);
			logger.info("支付类型="+mchPayType+";消费劵支付金额="+consumeCouponCount);
			 
		 
			//增加美兑积分逻辑  2016-10-31
			String consumePointsCount = null; //美兑积分
			if(obj.containsKey("consume_points_count")) {
				consumePointsCount = obj.getString("consume_points_count");
			}
			
			 
			if (StringUtils.isEmpty(memId)) {
				logger.info("当前用户不存在");
				return new ResBodyData(ConstApiStatus.USER_NOT_EXIST, ConstApiStatus.getZhMsg(ConstApiStatus.USER_NOT_EXIST));
			}
			 
			if (!"2".equals(mchStatus)) {
				logger.info("订单状态输入错误");
				return new ResBodyData(ConstApiStatus.ORDER_STATUS_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.ORDER_STATUS_ERROR));
			}
			
			if (shoppingCouponCount != null && 
					Double.valueOf(shoppingCouponCount) > Double.valueOf(consumerMoney)) {
				logger.info("消费的余额不能大于消费总金额");
				return new ResBodyData(ConstApiStatus.MONEY_BIGGER_THAN_COMSUME_AMOUNT, 
						ConstApiStatus.getZhMsg(ConstApiStatus.MONEY_BIGGER_THAN_COMSUME_AMOUNT));
			}
			
			if (consumePointsCount != null && Double.valueOf(consumePointsCount) > Double.valueOf(consumerMoney)) {
				logger.info("消费的积分不能大于消费总金额");
				return new ResBodyData(ConstApiStatus.POINTS_BIGGER_THAN_COMSUME_AMOUNT, 
						ConstApiStatus.getZhMsg(ConstApiStatus.POINTS_BIGGER_THAN_COMSUME_AMOUNT));
			}
			
			// 支付类型
			MemberConsumeMessageReq mp = new MemberConsumeMessageReq();
			mp.setOrderId(orderId);
			mp.setProductName(productName);
			mp.setBackIntegral(backIntegral);
			mp.setConsumerMoney(consumerMoney);
			//增加美兑积分逻辑  2016-10-31
			mp.setOrderSource(SerialStringUtil.getDictOrderSource(orderSource));
			mp.setOrginalUserId(orginalUserId);
			mp.setMemId(memId);
			mp.setPayType(mchPayType);
			mp.setMchStatus(mchStatus);
			mp.setConsumeCouponCount(consumeCouponCount);
			
			if(shoppingCouponCount != null && !"".equals(shoppingCouponCount)) {
				mp.setShoppingCouponCount(shoppingCouponCount);
			}
			//增加美兑积分逻辑  2016-10-31
			if(consumePointsCount != null && !"".equals(consumePointsCount)) {
				mp.setConsumePointsCount(consumePointsCount);
			}
			ResBodyData updateMemberOrder = tradeService.updateMemberOrder(mp);
			 
			logger.info("BusinessRecedeOrder接口请求输出参数：{}", updateMemberOrder);
			
			return updateMemberOrder;
		} catch (ServiceException e) {
			logger.error("服务异常: {}", e);
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		 
		}
		
		
	} 
	
	
	/**
	 * 保存当前会员订单信息接口(免token校验) http://IP:PORT/Authorized/saveOrderNotoken
	 * @return ResBodyData
	 * @author wujun
	 */
	@PostMapping(value = "/save_order_notoken")
	ResBodyData saveOrderNotoken(@Validated JSONObject obj) throws MdSysException{
	 
		logger.info("保存当前会员订单信息接口(免token校验)请求输入参数："+obj.toString());
		String memId = obj.getString("mem_id");
		String orderId = obj.getString("order_id");// 用户产品的订单号
		String consumerMoney = obj.getString("consumer_money");// 消费金额
		String productName = obj.getString("product_name");// 消费项目名称
		String backIntegral = obj.getString("back_integral");// 消费返还积分数量
		String orderSource = obj.getString("order_source");// 订单来源如果是O2O平台请直接填写O2O,如果是1gw(商城)就直接填写1gw
		String orginalUserId = obj.getString("orginal_user_id");// O2O或1gw系统会员ID
		String mchPayType = obj.getString("pay_type");// 支付类型
		String mchStatus = obj.getString("status");
		String consumeCouponCount = "0";// 消费劵支付金额(必须小于等于消费金额)
		String shoppingCouponCount="0";  //余额
		String consumePointsCount = "0"; //美兑积分
		try {
			logger.info("用户产品的订单号="+orderId+";消费金额="+consumerMoney);
			logger.info("消费项目名称="+productName+";消费返还积分数量="+backIntegral);
			logger.info("订单来源="+orderSource+";系统会员ID="+orginalUserId);
			logger.info("支付类型="+mchPayType+";消费劵支付金额="+consumeCouponCount);
			
			if(obj.containsKey("consume_coupon_count")){
				consumeCouponCount = obj.getString("consume_coupon_count");
				 
			}
			
			if(obj.containsKey("shopping_coupon_count")){
				shoppingCouponCount = obj.getString("shopping_coupon_count");
			 
			}
			
			//增加美兑积分逻辑  2016-10-31
			if(obj.containsKey("consume_points_count")){
				consumePointsCount = obj.getString("consume_points_count");
			 
			}
			 
			if (StringUtils.isEmpty(memId)) {
				logger.info("当前用户不存在: {}",memId);
				return new ResBodyData(ConstApiStatus.ACCOUNT_NOT_EXIST, ConstApiStatus.getZhMsg(ConstApiStatus.ACCOUNT_NOT_EXIST));
			}
			
			if (!"1".equals(mchStatus)) {
				logger.info("订单状态输入错误");
				return new ResBodyData(ConstApiStatus.ORDER_STATUS_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.ORDER_STATUS_ERROR));
			}
			
			//余额支付金额 2017-03-02
			if(obj.containsKey("shopping_coupon_count")){
				if (Double.valueOf(shoppingCouponCount) > Double.valueOf(consumerMoney)) {
					logger.info("余额支付金额不能大于消费金额");
					return new ResBodyData(ConstApiStatus.PAYMONEY_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.PAYMONEY_ERROR));
				}
			}
			
			  //增加美兑积分逻辑  2016-10-31
			  if(obj.containsKey("consume_points_count")){
					if (Double.valueOf(consumePointsCount) > Double.valueOf(consumerMoney)) {
						logger.info("消费积分不能大于消费金额");
						return new ResBodyData(ConstApiStatus.POINTS_BIGGER_THAN_MONEY, ConstApiStatus.getZhMsg(ConstApiStatus.POINTS_BIGGER_THAN_MONEY));
					}
			   }
			  
			//如果是支付积分+余额 判断(支付积分+余额<消费总金额)
			  if(obj.containsKey("shopping_coupon_count") && obj.containsKey("consume_points_count")){
				  double count = new BigDecimal(shoppingCouponCount).add(new BigDecimal(consumePointsCount)).doubleValue();
				   if(Double.valueOf(count) > Double.valueOf(consumerMoney)){
						logger.info("支付积分加余额不能大于消费金额");
						return new ResBodyData(ConstApiStatus.PAY_POINTS_MONEY_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.PAY_POINTS_MONEY_ERROR));
				   }
			  }  
			   
			//增加美兑积分逻辑  2016-10-31
		  	if ("1".equals(mchPayType)) {
		  		logger.info("支付类型输入错误");
		  		return new ResBodyData(ConstApiStatus.PAYTYPE_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.PAYTYPE_ERROR));
			}
		  
		  //增加美兑积分逻辑  2016-10-31
			if("2".equals(mchPayType)){
				if(Double.valueOf(consumePointsCount) <= 0 && Double.valueOf(shoppingCouponCount) <= 0){
					logger.info("混合支付支付模式，支付积分+余额不能为小于或等于0");
					return new ResBodyData(ConstApiStatus.MIX_PAYTYPE_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.MIX_PAYTYPE_ERROR));
				}
			}
				
			// 支付类型
			MemberConsumeMessageReq mp = new MemberConsumeMessageReq();
			mp.setOrderId(orderId);
			mp.setProductName(productName);
			mp.setBackIntegral(backIntegral);
			mp.setConsumerMoney(consumerMoney);
			//增加美兑积分逻辑  2016-10-31
			mp.setOrderSource(SerialStringUtil.getDictOrderSource(orderSource));
			mp.setOrginalUserId(orginalUserId);
			mp.setMemId(memId);
			mp.setPayType(mchPayType);
			mp.setMchStatus(mchStatus);
			mp.setConsumeCouponCount(consumeCouponCount);
			mp.setShoppingCouponCount(shoppingCouponCount);
			mp.setConsumePointsCount(consumePointsCount);
			//增加美兑积分逻辑  2016-10-31
		    
			//提交订单请求
			ResBodyData saveMemberOrder = tradeService.saveMemberOrder(mp);
			
			logger.info("保存当前会员订单信息接口(免token校验)输出参数：{}", saveMemberOrder.toString());
			return saveMemberOrder;
		} catch (ServiceException e) {
			logger.info(ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION) + " :{}", e);
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION, ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION));
		}
		
    }
 
	/**
	 * 当前会员退单信息接口  http://IP:PORT/Authorized/RecedeOrder
	 * @return ResBodyData
	 * @author wuun
	 */
	@PostMapping(value = "/recede_order")
	ResBodyData recedeOrder(JSONObject obj) {
	 
		String memId = obj.getString("mem_id");
		String orderId = obj.getString("order_id");// 用户产品的订单号
		String consumerMoney = obj.getString("consumer_money");// 消费金额
		String productName = obj.getString("product_name");// 消费项目名称
		String backIntegral = obj.getString("back_integral");// 消费返还积分数量
		String orderSource = obj.getString("order_source");// 订单来源如果是O2O平台请直接填写O2O,如果是1gw(商城)就直接填写1gw
		String orginalUserId = obj.getString("orginal_user_id");// O2O或1gw系统会员ID
		String mchPayType = obj.getString("pay_type"); // 支付类型
		String mchStatus = obj.getString("status");
		String consumeCouponCount = obj.getString("consume_coupon_count");// 消费劵支付金额(必须小于等于消费金额)
		String shoppingCouponCount = obj.getString("shopping_coupon_count"); // 消费劵支付金额(必须小于等于消费金额)

		logger.info("当前会员退单信息接口请求输入参数："+ obj.toString());
		
		try {
			logger.info("用户产品的订单号="+orderId+";消费金额="+consumerMoney);
			logger.info("消费项目名称="+productName+";消费返还积分数量="+backIntegral);
			logger.info("订单来源="+orderSource+";系统会员ID="+orginalUserId);
			logger.info("支付类型="+mchPayType+";消费劵支付金额="+consumeCouponCount);
			
			 
			if (StringUtils.isEmpty(memId)) {
				logger.info("当前用户不存在: {}",memId);
				return new ResBodyData(ConstApiStatus.ACCOUNT_NOT_EXIST, ConstApiStatus.getZhMsg(ConstApiStatus.ACCOUNT_NOT_EXIST));
			}
			
			if (!"2".equals(mchStatus)) {
				logger.info("外部请求订单状态输入错误");
				return new ResBodyData(ConstApiStatus.ORDER_STATUS_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.ORDER_STATUS_ERROR));
			}

			 
			// 支付类型
			MemberConsumeMessageReq mp = new MemberConsumeMessageReq();
			mp.setOrderId(orderId);
			mp.setProductName(productName);
			mp.setBackIntegral(backIntegral);
			mp.setConsumerMoney(consumerMoney);
			if ("O2O".equals(orderSource)) {
				mp.setOrderSource(ConstDataAppSource.ORDER_SOURCE_O2O);
			}
			if ("1gw".equals(orderSource)) {
				mp.setOrderSource(ConstDataAppSource.ORDER_SOURCE_1GW);
			}
			mp.setOrginalUserId(orginalUserId);
			mp.setMemId(memId);
			mp.setPayType(mchPayType);
			mp.setMchStatus(mchStatus);
			mp.setConsumeCouponCount(consumeCouponCount);
			
			if(obj.containsKey("shopping_coupon_count")) {
				mp.setShoppingCouponCount(shoppingCouponCount);
			}
			
			logger.info("提交退单请求");
			ResBodyData updateMemberOrder = tradeService.updateMemberOrder(mp);
			 
			
			logger.info("当前会员退单信息接口输出参数：{}", updateMemberOrder.toString());
			
			return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
		} catch (ServiceException e) {
			logger.info(ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION) + " :{}", e);
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION, ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION));
		}		
		
		 
	}
	
}
