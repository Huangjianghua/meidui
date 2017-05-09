package com.meiduimall.application.mall.pay.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.meiduimall.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.constant.SysParaNameConst;
import com.meiduimall.application.mall.pay.model.EctoolsTradePaybill;
import com.meiduimall.application.mall.pay.model.PaymentTrade;
import com.meiduimall.application.mall.pay.model.SystradePTrade;
import com.meiduimall.application.mall.pay.service.PaymentService;
import com.meiduimall.application.mall.pay.service.TradeService;
import com.meiduimall.application.mall.pay.service.UserService;
import com.meiduimall.application.mall.util.Des;
import com.meiduimall.application.mall.util.Logger;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1")
public class PaymentController { 

	@Autowired
	private PaymentService paymentService;   
	
	@Autowired 
	private TradeService tradeService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 支付之前判断
	 * @param paymentId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/PayJudgment")
	public JSONObject payJudgment(PaymentTrade  paymentTrade){
		//验证支付密码是否正确，用户输的支付密码拼接userPayKey(商城配置为'{liangping}')与pay_password比较。
		//注：选择支付方式的时候，余额、积分2种方式只要有选择任何一个，都需要验证支付密码，
		//没有需要提示用户设置支付密码(商城sysuser_account表pay_password字段,有需求正在将支付密码迁移至会员系统)
		Logger.info("app传过来的数据:%s", paymentTrade.toString());	
		JSONObject json = new JSONObject();
		try {
			//1.获取支付单信息
			Map<String, Object> paymentBill = paymentService.getPaymentBill(paymentTrade.getPayment_id());
			Logger.info("支付单信息:%s", paymentBill);
			if(paymentBill == null){
				Logger.info("支付单信息为空!");
				json.put("status", 11);
				json.put("msg", "支付单信息为空!");
				return json;
			} 
				
			//2.获取平台订单表信息
			SystradePTrade objPTradeInfo = tradeService.getSystradePTrade(new BigInteger(paymentBill.get("platformId").toString()));
			if(objPTradeInfo == null){
				Logger.info("平台订单为空!");
				json.put("status", 11);
				json.put("msg", "平台订单为空!");
				return json;
			} 
			
			//计算出要支付的第三方的金额
		    double money = objPTradeInfo.getTotalMoney().subtract(new BigDecimal(paymentTrade.getUse_money())).doubleValue() - Double.valueOf(paymentTrade.getUse_point()); 
		    paymentTrade.setMoney(String.valueOf(money));
		    Logger.info("订单总金额(包括运费):%s,用户提交的余额:%s", objPTradeInfo.getTotalMoney(),paymentTrade.getUse_money());
		    Logger.info("计算要支付的第三方金额:%s", paymentTrade.getMoney());
			
			
			if(null == paymentTrade.getToken() || "".equals(paymentTrade.getToken())){
				Logger.info("token不能为空!");
				json.put("status", 11);
				json.put("msg", "token不能为空!");
				return json;
			}
			//token转换memId
			JSONObject tokenTOmemId = userService.tokenTOmemId(paymentTrade.getToken());
			if(tokenTOmemId.getInt("status") != 0){
				Logger.info("tokenTOmemId 失败! %s", tokenTOmemId.getString("msg"));
				json.put("status", 11);
				json.put("msg", tokenTOmemId.getString("msg"));
				return json;
			}
			JSONObject object = (JSONObject) tokenTOmemId.get("data");
			paymentTrade.setMemId(object.getString("memId"));
			
			//请求会员中心【getMemberBasicInfo】获取用户信息积分等等
			JSONObject memberBasicInfo = userService.getMemberBasicInfo(paymentTrade.getToken());
			if(memberBasicInfo.getInt("status") != 0) {
				Logger.info("获取用户信息 失败! %s", memberBasicInfo.getString("msg"));
				json.put("status", 11);
				json.put("msg", memberBasicInfo.getString("msg"));
				return json;
			}
			JSONObject  object2 = (JSONObject) memberBasicInfo.get("data");
			//验证支付密码
		    Logger.info("支付密码:%s", paymentTrade.getPay_password());
		    if("1".equals(object2.get("paypwd_isopen"))){//1开 0关
		    	if(objPTradeInfo.getIsPaying() == 0){       //第一次支付
		    		JSONObject validePayPwd = userService.validePayPwd(paymentTrade.getMemId(), Des.appdecrypt(paymentTrade.getPay_password(), SysParaNameConst.APP_ENCRYPT_KEY));
		    		if(validePayPwd.getInt("status") != 0){
		    			Logger.info("验证支付密码 失败! %s", validePayPwd.getString("msg"));
		    			json.put("status", 11);
		    			json.put("msg", validePayPwd.getString("msg"));
		    			return json;
		    		}
		    	}
	    	}  
		 
		   
			/** 判断支付单paymentBill['status'] == 'succ' || $paymentBill['status'] == 'progress' //提示订单已经支付完成; **/
			if(paymentBill.get("status").equals(SysParaNameConst.SUCC)||paymentBill.get("status").equals(SysParaNameConst.PROGRESS)){
				Logger.info("订单已经支付完成!");
				json.put("status", 11);
				json.put("msg", "订单已经支付完成!");
				return json;
			}
				
			 
			/** paymentBill['status']值是否为'ready',否则提示'请勿重复发起支付; **/
			if(!paymentBill.get("status").equals(SysParaNameConst.READY)){
 				Logger.info("请勿重复发起支付!");
 				json.put("status", 11);
				json.put("msg", "请勿重复发起支付!");
				return json;
			}
			
			/** 判断平台信息订单状态是否为待付款objPTradeInfo['status']!= 'WAIT_BUYER_PAY'  //提示订单已经支付完成; **/
			if(!objPTradeInfo.getStatus().equals(SysParaNameConst.WAIT_BUYER_PAY)){
				Logger.info("平台订单为待付款,订单已经支付完成!");
				json.put("status", 11);
				json.put("msg", "平台订单为待付款,订单已经支付完成!");
				return json;
			}
					
			
			
			
			BigDecimal totalmoney = new BigDecimal(object2.getString("totalmoney")) ;     //总金额
			BigDecimal totalpoints = new BigDecimal(object2.getString("totalpoints")) ;  //当前可用积分
				 
			//仅余额支付判断余额是否足够 
			if(new BigDecimal(paymentTrade.getUse_money()).compareTo(totalmoney) > 0){
				Logger.info("用户余额不足!");
				json.put("status", 11);
				json.put("msg", "用户余额不足!");
				return json;
			}
			
			//根据paymentBill_2['trade']获取所有商家订单号(tid)的集合以逗号拼接为字符串
			List<EctoolsTradePaybill> tradePaybill = tradeService.listEctoolsTradePaybill(paymentTrade.getPayment_id());
			if(tradePaybill==null){
				Logger.info("子订单获取所有商家订单为空!");
				json.put("status", 11);
				json.put("msg", "子订单获取所有商家订单为空!");
				return json;
			} 
			List<Object> list = new ArrayList<>();
			tradePaybill.forEach(tids->{
				if(!"".equals(tids.getTid())) //过滤掉tid为空的
					list.add(tids.getTid());
			});
			//【获取订单金额接口】获取systrade_trade表信息
			List<Map<String, Object>> ordersMoney = tradeService.getTradeMoney(list);
			if(ordersMoney == null){
				Logger.info("获取订单金额 为空!");
				json.put("status", 11);
				json.put("msg", "获取订单金额 为空!");
				return json;
			} 
			
			//计算订单总金额payment、总运费postFee
			BigDecimal postFee = new BigDecimal(0);
			BigDecimal payment = new BigDecimal(0);
			for (Map<String, Object> map : ordersMoney) { 
				postFee = postFee.add(new BigDecimal(map.get("postFee").toString()));
				payment = payment.add(new BigDecimal(map.get("payment").toString()));
			}
		    Logger.info("总运费:%s", postFee);
		    Logger.info("订单总金额:%s", payment);
		    //订单总金额与平台订单表objPTradeInfo['total_money']比较
		    if(payment.compareTo(objPTradeInfo.getTotalMoney()) != 0){
		    	Logger.info("订单金额不一致!");
		    	json.put("status", 11);
				json.put("msg", "订单金额不一致!");
				return json;
		    }
		    
		    
		    //订单总金额与支付单支付货币金额paymentBill['curMoney']比较
		    if(objPTradeInfo.getTotalMoney().compareTo(new BigDecimal(paymentBill.get("curMoney").toString())) !=0){
		    	Logger.info("需要支付的货币金额不一致!");
		    	json.put("status", 11);
				json.put("msg", "需要支付的货币金额不一致!");
				return json;
		    }
		    
		    //订单总金额与用户提交的积分+余额+现金支付比较
		    Logger.info("用户提交的积分:%s + 余额:%s +现金:%s", paymentTrade.getUse_point(),paymentTrade.getUse_money(),paymentTrade.getMoney());
		    BigDecimal add = new BigDecimal(paymentTrade.getUse_point()).add(new BigDecimal(paymentTrade.getUse_money())).add(new BigDecimal(paymentTrade.getMoney()));
		    Logger.info("订单总金额与用户提交的积分+余额+现金支付比较:%s,%s", objPTradeInfo.getTotalMoney(),add);
		    if(objPTradeInfo.getTotalMoney().compareTo(add) != 0){
		    	Logger.info("订单总金额与用户提交的积分+余额+现金支付比较,订单金额不一致!");
		    	json.put("status", 11);
				json.put("msg", "订单总金额与用户提交的积分+余额+现金支付比较,订单金额不一致!");
				return json;
		    }
		    
		    
		  //第二次支付判断开始objPTradeInfo['is_paying'] == 1
			if(objPTradeInfo.getIsPaying() == 1){
				//判断objPTradeInfo['wallet_pay'] != 用户提交的余额pay_money //与第一次金额不等，不让支付，报错
				if(objPTradeInfo.getWalletPay().compareTo(new BigDecimal(paymentTrade.getUse_money())) !=0){
					Logger.info("与第一次使用余额不等,不让支付!");
					json.put("status", 11);
					json.put("msg", "与第一次使用余额不等,不让支付!");
					return json;
				}
				
				//objPTradeInfo['point_pay'] != 用户提交的point_pay || objPTradeInfo['total_point'] < 用户提交的point_pay 
				//如果这次支付的积分跟冻结的积分不相等，报错
				if(objPTradeInfo.getPointPay() != Integer.valueOf(paymentTrade.getUse_point()) || objPTradeInfo.getTotalPoint() < Integer.valueOf(paymentTrade.getUse_point())){
					Logger.info("支付的积分跟冻结的积分不相等!");
					json.put("status", 11);
					json.put("msg", "支付的积分跟冻结的积分不相等!");
					return json;
				}  
				Logger.info("平台订单表里面的第三方支付金额:%s ;; 计算出来的第三方支付金额:%s", objPTradeInfo.getCashPay(),paymentTrade.getMoney());
				//objPTradeInfo['cash_pay'] != 用户提交的money //第三方支付金额不相等，报错
				if(objPTradeInfo.getCashPay().compareTo(new BigDecimal(paymentTrade.getMoney())) != 0){
					Logger.info("第三方支付金额不相等!");
					json.put("status", 11);
					json.put("msg", "第三方支付金额不相等!");
					return json;
				}
				
				
			
			}else{
				//第一次支付判断开始objPTradeInfo['is_paying'] == 0第一次过来验证，第二次不需要验证,因为冻结消费卷之后，消费卷的可用金额就会减少	
				
				//(忽略)***（当前没有消费券，可忽略）验证用户提交的使用消费券cpn_money与会员系统可用消费券consume_coupon，本次最多可使用消费券（order_percentage计算）比较***(忽略)
			    //验证用户提交的使用积分point_pay与会员系统totalpoints字段、商城objPTradeInfo['total_point']字段比较是否在使用范围，是否合法
			    if(new BigDecimal(paymentTrade.getUse_point()).compareTo(totalpoints) > 0){
			    	Logger.info("积分不够!");
			    	json.put("status", 11);
					json.put("msg", "积分不够!");
					return json;
			    }
			    	
		    	if(Integer.valueOf(paymentTrade.getUse_point()) > objPTradeInfo.getTotalPoint()){
		    		Logger.info("积分不在使用范围!");
		    		json.put("status", 11);
					json.put("msg", "积分不在使用范围!");
					return json;
		    	}
		    	
			}
		    
			//【订单支付请求支付网关接口】
			ResponseBodyData paymentTradePays = paymentService.paymentTrade(paymentTrade,objPTradeInfo,list,memberBasicInfo);
		    
			//有第三方支付  调用微信支付/支付宝支付
			if (paymentTradePays.getStatus() == 3) {
				Logger.info("第三方支付成功!");
				String encrypt = Des.appencrypt(paymentTradePays.getData().toString(),SysParaNameConst.APP_ENCRYPT_KEY);
				json.put("status", 0);
				json.put("data", encrypt);
				json.put("msg", "success");
				if(paymentTrade.getIs_new() == null){
					json.put("status", 1);
					json.put("type", 1);
				}
				return json;
			} else if(paymentTradePays.getStatus() == 0) {
				// 没有第三方 调用【支付完成】方法,退出程序
				ResponseBodyData payFinish = paymentService.payFinish(paymentTrade.getPayment_id());
					Logger.info("没有第三方支付完成! ==> %s", payFinish.getData());
					json.put("status", 0);
					json.put("data", payFinish.getData());
					json.put("msg", "success");
					if(paymentTrade.getIs_new() == null){
						json.put("status", 1);
						json.put("type", 1);
					}
					return json;
				 
			}else{
				Logger.info("支付失败! ==> %s",paymentTradePays.getMsg());
				json.put("status", 11);
				json.put("msg", paymentTradePays.getMsg());
				return json;
			}
	   
		} catch (ServiceException e) {
			Logger.error("system error: %s",e);
			json.put("status", 11);
			json.put("msg", "系统错误!" + e.getMessage());
			return json;
		}
	}
	
	
	
	
	
	
	
	 
	
 
}
