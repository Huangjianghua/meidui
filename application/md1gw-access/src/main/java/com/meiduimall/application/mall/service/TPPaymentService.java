package com.meiduimall.application.mall.service;


import com.meiduimall.application.mall.constant.ResponseBodyData;
import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.application.mall.model.SystradePTrade;

public interface TPPaymentService {

	
	
	ResponseBodyData Payment(PaymentTrade paymentTrade,SystradePTrade obj_p_trade_info);
	
}
