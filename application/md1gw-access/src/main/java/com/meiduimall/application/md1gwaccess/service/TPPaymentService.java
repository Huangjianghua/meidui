package com.meiduimall.application.md1gwaccess.service;


import com.meiduimall.application.md1gwaccess.constant.ResponseBodyData;
import com.meiduimall.application.md1gwaccess.model.PaymentTrade;
import com.meiduimall.application.md1gwaccess.model.SystradePTrade;

public interface TPPaymentService {

	
	
	ResponseBodyData Payment(PaymentTrade paymentTrade,SystradePTrade obj_p_trade_info)throws Exception;
	
}
