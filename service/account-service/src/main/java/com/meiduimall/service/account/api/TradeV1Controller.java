package com.meiduimall.service.account.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.request.MemberConsumeMessageReq;
import com.meiduimall.service.account.model.request.RequestFreezeUnFreeze;
import com.meiduimall.service.account.model.request.RequestUnfreezeDecut;
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
	private HttpServletRequest request;
	
	@Autowired
	private TradeService orderService;
	
	/**会员发起交易申请，冻结积分和余额/会员发起退单，解冻积分和余额*/
	@RequestMapping(value="/freeze_unfreeze",method=RequestMethod.POST)
	ResBodyData  freezeUnfreeze(@RequestBody RequestFreezeUnFreeze param){
		logger.info("收到冻结解冻API请求  URL: {}  DATA: {}",request.getRequestURL(),param.toString());
		ResBodyData resBodyData=orderService.freezeUnfreeze(param);
		logger.info("冻结解冻API请求结果：{}",resBodyData.toString());
		return resBodyData;
	}
	
	/**会员支付成功，解冻并扣减积分和余额
	 * @throws MdSysException */
	@RequestMapping(value="/unfreeze_deduct",method=RequestMethod.POST)
	ResBodyData unfreezeDeduct(@RequestBody RequestUnfreezeDecut param) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		logger.info("收到解冻并扣减积分和余额API请求  URL: {}  DATA: {}",request.getRequestURL(),param.toString());
		orderService.unfreezeDeduct(param);
		logger.info("解冻并扣减积分和余额API请求结果：{}",resBodyData.toString());
		return resBodyData;
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
	 */
	@PostMapping(value = "/business_recede_order")
	ResBodyData businessRecedeOrder(@Validated JSONObject json)   {
		logger.info("接收 当前商家退会员订单信息接口 数据：{}", json);
		logger.info("当前商家退会员订单信息接口  请求结果：{}", "");
		try {
		} catch (Exception e) {
			 
		}
		return null;
	} 
	
	
	/**
	 * 保存当前会员订单信息接口(免token校验) http://IP:PORT/Authorized/saveOrderNotoken
	 * @return ResBodyData
	 */
	@PostMapping(value = "/save_order_notoken")
	ResBodyData saveOrderNotoken(@Validated JSONObject json) throws MdSysException{
		logger.info("接收到数据：memId={}, phone={}", "");
		logger.info("注册时记录会员手机对应的区域请求结果：{}", "");
		return null;
    }
 
	/**
	 * 当前会员退单信息接口  http://IP:PORT/Authorized/RecedeOrder
	 * @return ResBodyData
	 */
	@PostMapping(value = "/recede_order")
	ResBodyData recedeOrder() {
		logger.info("更新开始");
		MemberConsumeMessageReq  mmt = new MemberConsumeMessageReq();
		Double xfc = new Double(0);
		orderService.updateMemberOrder(mmt, xfc);
		logger.info("更新结果  ：{}", "");
		logger.info("更新结束");
		return null;
	}
	
}
