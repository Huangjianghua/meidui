package com.meiduimall.payment.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.model.api.PaymentNotifyModel;

/**
 * Created by nico on 2017/2/20.
 */
public class PaymentNotifyService {

    @Autowired
    DaoTemplate daoTemplate;

    /**
     * 添加回调数据
     *
     * @param orderId
     * @param notifyData
     * @param notifyStatus
     * @throws Exception 
     */
    public void insertNotify(String orderId, String notifyData, Integer notifyStatus) throws Exception {

        PaymentNotifyModel model = new PaymentNotifyModel();
        model.setOrderId(orderId);
        model.setNotifyData(notifyData);
        model.setNotifyStatus(notifyStatus);

        daoTemplate.insert("paymentNotify.insert", model);
    }

    /**
     * 更新回调数据的状态
     *
     * @param model
     * @throws Exception 
     */
    public void updateStatus(PaymentNotifyModel model) throws Exception {
        daoTemplate.update("paymentNotify.updateStatus", model);
    }

}
