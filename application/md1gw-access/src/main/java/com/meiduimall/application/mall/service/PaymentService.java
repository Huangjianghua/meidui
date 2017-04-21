package com.meiduimall.application.mall.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.model.EctoolsPayments;
import com.meiduimall.application.mall.model.EctoolsPaymentsSucc;
import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.application.mall.model.SystradePTrade;

import net.sf.json.JSONObject;




public interface PaymentService {

	/**
	 * 获取支付单信息
	 * @param paymentId
	 * @return
	 * @
	 */
	Map<String, Object> getPaymentBill(String paymentId);

	/**
	 * 更新支付单信息
	 * @param ectoolsPayments
	 * @
	 */
	Integer updateEctoolsPayments(EctoolsPayments ectoolsPayments);
	
	/**
	 * 查询第三方支付成功记录表
	 * @param ectoolsPaymentsSucc
	 * @return
	 * @
	 */
	EctoolsPaymentsSucc getEctoolsPaymentsSucc(String paymentId);
	
	/**
	 * 记录支付成功记录
	 * @param EctoolsPaymentsSucc
	 * @return
	 * @
	 */
 	Integer insertEctoolsPaymentsSucc(EctoolsPaymentsSucc ectoolsPaymentsSucc);
 	
 	/**
 	 * 支付回调
 	 * @
 	 */
 	ResponseBodyData PayCallBack(EctoolsPaymentsSucc ectoolsPaymentsSucc,String notice);  
 	
 	/**
 	 * 更新支付单错误金额
 	 * @param ep
 	 * @return
 	 * @
 	 */
 	Integer updateErrorMoney(EctoolsPayments ep);
 	
 	/**
 	 * 推送次数加1
 	 * @param paymentId
 	 * @return
 	 * @
 	 */
 	Integer updateErrorNum(String paymentId);
 	
 	/**
 	 * 支付完成
 	 * @param paymentBill
 	 * @return
 	 * @
 	 */
 	ResponseBodyData PayFinish(String paymentId);
 	
 	/**
 	 *【订单支付请求支付网关接口】
 	 * @param paymentTradePay
 	 * @param paymentBill
 	 * @param obj_p_trade_info
 	 * @return
 	 * @
 	 */
 	ResponseBodyData PaymentTrade(PaymentTrade paymentTrade,SystradePTrade obj_p_trade_info,
 			List<Object> list,JSONObject memberBasicInfo);
 	
 	/**
 	 * 回调结果 通知支付服务
 	 * @
 	 */
  	void NoticePaymentService(JSONObject json);
}
