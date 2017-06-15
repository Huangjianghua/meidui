package com.meiduimall.payment.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
import com.meiduimall.payment.api.model.api.PaymentParamModel;
import com.meiduimall.payment.api.model.api.PaymentResultModel;
import com.meiduimall.payment.api.service.PaymentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
     * 
     * @param model 支付接收对象
     * @return resultBody 支付结果返回对象
     */
    @ApiOperation(value="支付方法", notes="支付方法")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "model", value = "支付参数", required = true, dataType = "PaymentParamModel"),
	})
    @PostMapping(value ="/payment")
    public ResBodyData pay(@RequestBody PaymentParamModel model) {
    	 ResBodyData resultBody = new ResBodyData(ServicePaymentApiCode.OPERAT_SUCCESS, ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.OPERAT_SUCCESS));
         try{
        	 resultBody = paymentService.payService(model);
         }catch(MdBizException e){
        	 log.error("Handle app payment error.", e);
        	 throw new ApiException(ServicePaymentApiCode.OPERAT_FAIL);
         }
            
        
        return resultBody;
    }

    @PostMapping(value ="/paynotify")
    public ResBodyData  payNotify(@RequestBody PaymentResultModel model){
    	ResBodyData result = null;
    	try{
    		log.info("支付回调处理开始-----");
    		result=paymentService.payAfterService(model);
    	}catch(MdBizException e){
    		log.error("回调处理出错！",e);
    		 throw new ApiException(ServicePaymentApiCode.OPERAT_FAIL);
    	}
    	
    	return  result;
    	
    }
}
