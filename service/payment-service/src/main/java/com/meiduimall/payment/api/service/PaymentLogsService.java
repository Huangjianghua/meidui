package com.meiduimall.payment.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.model.api.PaymentLogsModel;

/**
 * 操作日志
 *
 * @author Nico.Jiang
 * @since 2017.1.9
 */
@Service
public class PaymentLogsService {

    @Autowired
    private DaoTemplate daoTemplate;

    /**
     * 添加日志
     *
     * @param tradeNo
     * @param payChannel
     * @param payType
     * @param payTotal
     * @param payData
     * @param orderId
     * @param paymentId
     * @throws Exception 
     */
    public void insertLog(PaymentLogsModel model) throws Exception {
        
        daoTemplate.insert("paymentLogs.insert", model);
    }


}
