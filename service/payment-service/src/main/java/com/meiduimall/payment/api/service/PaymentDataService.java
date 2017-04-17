package com.meiduimall.payment.api.service;

import com.google.gson.Gson;
import com.meiduimall.exception.ApiException;
import com.meiduimall.payment.api.controller.PaymentController;
import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.enums.CacheKey;
import com.meiduimall.payment.api.model.api.PaymentNotifyModel;
import com.meiduimall.payment.api.model.api.PaymentResultModel;
import com.meiduimall.payment.api.model.api.PaymenttTradeModel;


import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * Created by nico on 2017/2/20.
 */
@Service
public class PaymentDataService {

	 private static Logger log = LoggerFactory.getLogger(PaymentDataService.class);
	 
    @Autowired
    DaoTemplate daoTemplate;
   
    
    /**
     * 描述：插入日志、回调数据
     */
     public void updTrade(PaymentResultModel resultModel)throws ApiException{
    	
    	
    	try{
    		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		resultModel.setCreateTime(new Date());
    		// 写入日志表
    		log.info("开始写入日志表-------");
        	daoTemplate.insert("paymentLogs.addlog",resultModel);
        	log.info("成功写入日志表-------");
        	PaymentNotifyModel  paymentNotifyModel = new PaymentNotifyModel();
        	paymentNotifyModel.setOrderId(resultModel.getOrderNo());
        	paymentNotifyModel.setNotifyData(resultModel.getNotifyData());
        	paymentNotifyModel.setNotifyStatus(Integer.valueOf(resultModel.getNotifyStatus()));
        	paymentNotifyModel.setNotifyTime(sdf.parse(resultModel.getNotifyTime()));
        	//写入回调数据
        	log.info("开始写入回调数据-------");
        	daoTemplate.insert("paymentNotify.insert",paymentNotifyModel);
        	log.info("成功写入回调数据-------");
        	
    	}catch(Exception e){
    		log.error("回调写入数据错误！", e);
    		throw new ApiException(e);

    	}
    	
    	
    }

}
