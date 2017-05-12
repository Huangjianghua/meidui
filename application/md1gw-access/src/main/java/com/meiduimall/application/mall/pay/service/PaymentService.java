package com.meiduimall.application.mall.pay.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.pay.model.EctoolsPayments;
import com.meiduimall.application.mall.pay.model.EctoolsPaymentsSucc;
import com.meiduimall.application.mall.pay.model.PaymentTrade;
import com.meiduimall.application.mall.pay.model.SystradePTrade;

import net.sf.json.JSONObject;




public interface PaymentService {

	/**
	 * 获取支付单信息
	 * @param paymentId
	 * @return
	 */
	Map<String, Object> getPaymentBill(String paymentId);

	/**
	 * 更新支付单信息
	 * @param ectoolsPayments
	 */
	Integer updateEctoolsPayments(EctoolsPayments ectoolsPayments);
	
	/**
	 * 查询第三方支付成功记录表
	 * @param paymentId
	 * @return
	 */
	EctoolsPaymentsSucc getEctoolsPaymentsSucc(String paymentId);
	
	/**
	 * 记录支付成功记录
	 * @param ectoolsPaymentsSucc
	 * @return
	 */
 	Integer insertEctoolsPaymentsSucc(EctoolsPaymentsSucc ectoolsPaymentsSucc);
 	
 	/**
 	 * 支付回调
 	 */
 	ResponseBodyData payCallBack(EctoolsPaymentsSucc ectoolsPaymentsSucc,String notice);  
 	
 	/**
 	 * 更新支付单错误金额
 	 * @param ep
 	 * @return
 	 */
 	Integer updateErrorMoney(EctoolsPayments ep);
 	
 	/**
 	 * 推送次数加1
 	 * @param paymentId
 	 * @return
 	 */
 	Integer updateErrorNum(String paymentId);
 	
 	/**
 	 * 支付完成
 	 * @param paymentId
 	 * @return
 	 */
 	ResponseBodyData payFinish(String paymentId);
 	
 	/**
 	 *【订单支付请求支付网关接口】
 	 * @param objPTradeInfo
 	 * @return
 	 */
 	ResponseBodyData paymentTrade(PaymentTrade paymentTrade,SystradePTrade objPTradeInfo,
 			List<Object> list,JSONObject memberBasicInfo);
 	
 	/**
 	 * 回调结果 通知支付服务
 	 */
  	void noticePaymentService(JSONObject json);
}
