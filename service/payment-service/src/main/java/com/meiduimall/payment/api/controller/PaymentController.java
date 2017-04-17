package com.meiduimall.payment.api.controller;

import com.meiduimall.payment.api.model.api.PaymentParamModel;
import com.meiduimall.payment.api.model.api.PaymentResultModel;
import com.meiduimall.payment.api.service.PaymentService;
import com.meiduimall.core.ResBodyData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nico
 * @since 2017/3/3.
 */
@RestController
@RequestMapping("/pay/payment-service/v1")
public class PaymentController {
	
	 private static Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    PaymentService paymentService;
    
    

    /**
     * 支付
     *
     * @param model
     * @return
     */
    @PostMapping(value ="/payment")
    public ResBodyData pay(@RequestBody PaymentParamModel model) {
    	ResBodyData result;
       
            result = paymentService.payService(model);
        
        return result;
    }

    @PostMapping(value ="/paynotify")
    public ResBodyData   payNotify(@RequestBody PaymentResultModel model){
    	ResBodyData result = null;
    	try{
    		log.info("支付回调处理开始-----");
    		result=paymentService.payAfterService(model);
    	}catch(Exception e){
    		result = new ResBodyData(1, e.getMessage());
    		log.error("回调处理出错！",e);
    	}
    	
    	return  result;
    	
    }
}
