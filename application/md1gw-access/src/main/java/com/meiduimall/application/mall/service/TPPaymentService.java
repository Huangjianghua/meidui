package com.meiduimall.application.mall.service;


import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.application.mall.model.SystradePTrade;

public interface TPPaymentService {

	
	
	ResponseBodyData payment(PaymentTrade paymentTrade,SystradePTrade objPTradeInfo);
	
}
