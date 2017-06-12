package com.meiduimall.service.account.api;


import java.math.BigDecimal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.request.MSMemberConsumeRecordsReq;

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
	 * 会员保存订单（适配旧会员系统）
	 * @author chencong
	 */
	@GetMapping(value="/save_order_old")
	ResBodyData  freezeUnfreezeOld( @Valid RequestSaveOrder model){
		logger.info("收到保存订单API请求   ：{}",model.toString());
		try {
			return tradeService.saveOrder(model);
		} catch (MdSysException e) {
			logger.error("保存订单API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 会员保存订单
	 * @author chencong
	 */
	@PostMapping(value="/save_order")
	ResBodyData  freezeUnfreeze(@RequestBody @Valid RequestSaveOrder model){
		logger.info("收到保存订单API请求   ：{}",model.toString());
		try {
			return tradeService.saveOrder(model);
		} catch (MdSysException e) {
			logger.error("保存订单API请求异常：{}",e.toString());
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 会员取消订单（适配旧会员系统）
	 * @author chencong
	 */
	@PostMapping(value="/cancel_order_old")
	ResBodyData cancelOrderOld(@Valid RequestCancelOrder model) throws MdSysException {
		logger.info("收到会员取消订单API请求 ：{}",model.toString());
		return tradeService.cancelOrder(model);
	}
	
	/**
	 * 会员取消订单
	 * @author chencong
	 */
	@PostMapping(value="/cancel_order")
	ResBodyData cancelOrder(@RequestBody @Valid RequestCancelOrder model) throws MdSysException {
		logger.info("收到会员取消订单API请求 ：{}",model.toString());
		return tradeService.cancelOrder(model);
	}

	
	/**
	 * 会员退订单接口 
	 * @author wujun
	 */
	@PostMapping(value = "/recede_order")
	ResBodyData RecedeOrder(MSMemberConsumeRecordsReq ms)   {
		
		logger.info("退订单接口请求输入参数："+ ms.toString());
		
		try {
			 
			if (StringUtils.isEmpty(ms.getMemId())) {
				logger.info("当前用户不存在");
				return new ResBodyData(ConstApiStatus.USER_NOT_EXIST, ConstApiStatus.getZhMsg(ConstApiStatus.USER_NOT_EXIST));
			}
			 
			if (!"1".equals(ms.getOrderStatus())) {
				logger.info("订单状态错误");
				return new ResBodyData(ConstApiStatus.ORDER_STATUS_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.ORDER_STATUS_ERROR));
			}
			
			if (ms.getConsumeMoney() != null && 
					Double.valueOf(ms.getConsumeMoney()) > Double.valueOf(ms.getConsumeAmount())) {
				logger.info("消费的余额不能大于消费总金额");
				return new ResBodyData(ConstApiStatus.MONEY_BIGGER_THAN_COMSUME_AMOUNT, 
						ConstApiStatus.getZhMsg(ConstApiStatus.MONEY_BIGGER_THAN_COMSUME_AMOUNT));
			}
			
			if (ms.getConsumePoints() != null && Double.valueOf(ms.getConsumePoints()) > Double.valueOf(ms.getConsumeAmount())) {
				logger.info("消费的积分不能大于消费总金额");
				return new ResBodyData(ConstApiStatus.POINTS_BIGGER_THAN_COMSUME_AMOUNT, 
						ConstApiStatus.getZhMsg(ConstApiStatus.POINTS_BIGGER_THAN_COMSUME_AMOUNT));
			}
			
			// 转换来源
			ms.setOrderSource(SerialStringUtil.getDictOrderSource(ms.getOrderSource()));
			
			 
			ResBodyData	updateMemberOrder = tradeService.recedeOrder(ms);
			 
			 
			logger.info("退会员订单接口=>请求输出参数：{}", updateMemberOrder);
			
			return updateMemberOrder;
		} catch (MdSysException e) {
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
	ResBodyData saveOrderNotoken(MSMemberConsumeRecordsReq ms) throws MdSysException{
	 
		logger.info("保存当前会员订单信息接口(免token校验)请求输入参数："+ms.toString());
		 
		try {
			 
			if (StringUtils.isEmpty(ms.getMemId())) {
				logger.info("当前用户不存在");
				return new ResBodyData(ConstApiStatus.ACCOUNT_NOT_EXIST, ConstApiStatus.getZhMsg(ConstApiStatus.ACCOUNT_NOT_EXIST));
			}
			
			if (!"0".equals(ms.getOrderStatus())) {
				logger.info("订单状态输入错误");
				return new ResBodyData(ConstApiStatus.ORDER_STATUS_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.ORDER_STATUS_ERROR));
			}
			
			//余额支付金额 2017-03-02
			if (Double.valueOf(ms.getConsumeMoney()) > Double.valueOf(ms.getConsumeAmount())) {
				logger.info("余额支付金额不能大于消费总金额");
				return new ResBodyData(ConstApiStatus.PAYMONEY_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.PAYMONEY_ERROR));
			}
			
			  //增加美兑积分逻辑  2016-10-31
			if (Double.valueOf(ms.getConsumePoints()) > Double.valueOf(ms.getConsumeAmount())) {
				logger.info("消费积分不能大于消费金额");
				return new ResBodyData(ConstApiStatus.POINTS_BIGGER_THAN_MONEY, ConstApiStatus.getZhMsg(ConstApiStatus.POINTS_BIGGER_THAN_MONEY));
			}
			  
			//如果是支付积分+余额 判断(支付积分+余额<消费总金额)
		   double count = new BigDecimal(ms.getConsumeMoney()).add(new BigDecimal(ms.getConsumePoints())).doubleValue();
		   if(count > Double.valueOf(ms.getConsumeAmount())){
				logger.info("支付积分加余额不能大于消费金额");
				return new ResBodyData(ConstApiStatus.PAY_POINTS_MONEY_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.PAY_POINTS_MONEY_ERROR));
		   }
			   
		  //增加美兑积分逻辑  2016-10-31
			if("2".equals(ms.getPayType())){
				if(Double.valueOf(ms.getConsumePoints()) <= 0 && Double.valueOf(ms.getConsumeMoney()) <= 0){
					logger.info("混合支付支付模式，支付积分+余额不能为小于或等于0");
					return new ResBodyData(ConstApiStatus.MIX_PAYTYPE_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.MIX_PAYTYPE_ERROR));
				}
			}
				
			// 来源转换
			ms.setOrderSource(SerialStringUtil.getDictOrderSource(ms.getOrderSource()));
			 
		    
			//提交订单请求
			ResBodyData saveMemberOrder = tradeService.saveMemberOrder(ms);
			
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
	/*@PostMapping(value = "/recede_order")
	ResBodyData recedeOrder(MSMemberConsumeRecordsReq ms) {
	 
		logger.info("当前会员退单信息接口请求输入参数："+ ms.toString());
		
		try {
			 
			if (StringUtils.isEmpty(ms.getMemId())) {
				logger.info("当前用户不能为空");
				return new ResBodyData(ConstApiStatus.ACCOUNT_NOT_EXIST, ConstApiStatus.getZhMsg(ConstApiStatus.ACCOUNT_NOT_EXIST));
			}
			
			if (!"1".equals(ms.getOrderStatus())) {
				logger.info("订单状态错误");
				return new ResBodyData(ConstApiStatus.ORDER_STATUS_ERROR, ConstApiStatus.getZhMsg(ConstApiStatus.ORDER_STATUS_ERROR));
			}

			 
		 
			// 来源转换
			ms.setOrderSource(SerialStringUtil.getDictOrderSource(ms.getOrderSource()));
			
			logger.info("提交退单请求");
			ResBodyData updateMemberOrder = tradeService.recedeOrder(ms);
			 
			
			logger.info("当前会员退单信息接口输出参数：{}", updateMemberOrder.toString());
			
			return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
		} catch (ServiceException e) {
			logger.info(ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION) + " :{}", e);
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION, ConstApiStatus.getZhMsg(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION));
		}		
		
		 
	}*/
	
}
