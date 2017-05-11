package com.meiduimall.application.mall.pay.service;


import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.pay.model.PaymentTrade;
import com.meiduimall.application.mall.pay.model.SystradePTrade;

public interface TPPaymentService {

	
	/**
	 * 第三方支付
	 * @param paymentTrade
	 * @param objPTradeInfo
	 * @return
	 */
	ResponseBodyData payment(PaymentTrade paymentTrade,SystradePTrade objPTradeInfo);
	
}
