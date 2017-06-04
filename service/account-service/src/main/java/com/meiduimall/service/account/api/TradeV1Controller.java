package com.meiduimall.service.account.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.model.request.RequestSaveOrder;
import com.meiduimall.service.account.model.request.RequestCancelOrder;
import com.meiduimall.service.account.service.TradeService;

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
	
}
