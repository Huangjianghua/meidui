package com.meiduimall.payment.api.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.payment.api.model.api.PaymentParamModel;
import com.meiduimall.payment.api.model.api.PaymentResultModel;

/**
 * @author nico
 * @since 2017/3/3.
 */
public interface PaymentService {

    /**
     * 支付
     *
     * @param paramModel
     * @return
     * @throws ApiException
     */
	ResBodyData payService(PaymentParamModel paramModel) throws ServiceException;

    /**
     * 支付后处理
     *
     * @param paramModel
     * @return
     * @throws ApiException
     */
	ResBodyData payAfterService(PaymentResultModel paramModel) throws ServiceException;

}
