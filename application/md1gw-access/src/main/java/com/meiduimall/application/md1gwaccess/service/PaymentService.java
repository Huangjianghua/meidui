package com.meiduimall.application.md1gwaccess.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.application.md1gwaccess.constant.ResponseBodyData;
import com.meiduimall.application.md1gwaccess.model.EctoolsPayments;
import com.meiduimall.application.md1gwaccess.model.EctoolsPaymentsSucc;
import com.meiduimall.application.md1gwaccess.model.PaymentTrade;
import com.meiduimall.application.md1gwaccess.model.SystradePTrade;

import net.sf.json.JSONObject;




public interface PaymentService {

	/**
	 * 获取支付单信息
	 * @param paymentId
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getPaymentBill(String paymentId) throws Exception;

	/**
	 * 更新支付单信息
	 * @param ectoolsPayments
	 * @throws Exception
	 */
	Integer updateEctoolsPayments(EctoolsPayments ectoolsPayments)throws Exception;
	
	/**
	 * 查询第三方支付成功记录表
	 * @param ectoolsPaymentsSucc
	 * @return
	 * @throws Exception
	 */
	EctoolsPaymentsSucc getEctoolsPaymentsSucc(String paymentId) throws Exception;
	
	/**
	 * 记录支付成功记录
	 * @param EctoolsPaymentsSucc
	 * @return
	 * @throws Exception
	 */
 	Integer insertEctoolsPaymentsSucc(EctoolsPaymentsSucc EctoolsPaymentsSucc)throws Exception;
 	
 	/**
 	 * 支付回调
 	 * @throws Exception
 	 */
 	ResponseBodyData PayCallBack(EctoolsPaymentsSucc ectoolsPaymentsSucc,String notice)throws Exception;  
 	
 	/**
 	 * 更新支付单错误金额
 	 * @param ep
 	 * @return
 	 * @throws Exception
 	 */
 	Integer updateErrorMoney(EctoolsPayments ep)throws Exception;
 	
 	/**
 	 * 推送次数加1
 	 * @param paymentId
 	 * @return
 	 * @throws Exception
 	 */
 	Integer updateErrorNum(String paymentId)throws Exception;
 	
 	/**
 	 * 支付完成
 	 * @param paymentBill
 	 * @return
 	 * @throws Exception
 	 */
 	ResponseBodyData PayFinish(String paymentId)throws Exception;
 	
 	/**
 	 *【订单支付请求支付网关接口】
 	 * @param paymentTradePay
 	 * @param paymentBill
 	 * @param obj_p_trade_info
 	 * @return
 	 * @throws Exception
 	 */
 	ResponseBodyData PaymentTrade(PaymentTrade paymentTrade,SystradePTrade obj_p_trade_info,
 			List<Object> list,JSONObject memberBasicInfo)throws Exception;
 	
 	/**
 	 * 回调结果 通知支付服务
 	 * @throws Exception
 	 */
  	void NoticePaymentService(JSONObject json)throws Exception;
}
