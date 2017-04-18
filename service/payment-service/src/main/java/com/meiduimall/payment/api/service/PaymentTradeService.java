package com.meiduimall.payment.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.model.api.PaymenttTradeModel;

/**
 * 支付流水表
 *
 * @author nico
 * @since 2017.1.16
 */
@Service
public class PaymentTradeService {

    @Autowired
    DaoTemplate daoTemplate;

    /**
     * 添加流水
     *
     * @param model
     * @throws Exception 
     */
    public void insertTrade(PaymenttTradeModel model) throws Exception {
        daoTemplate.insert("paymentTrade.insert", model);
    }




}
