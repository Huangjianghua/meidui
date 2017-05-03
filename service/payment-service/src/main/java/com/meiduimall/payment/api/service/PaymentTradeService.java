package com.meiduimall.payment.api.service;

import com.meiduimall.exception.ServiceException;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
import com.meiduimall.payment.api.dao.DaoTemplate;
import com.meiduimall.payment.api.model.api.PaymenttTradeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   * 描述：添加流水
   *
   * @param model 流水对象
   */
  public void insertTrade(PaymenttTradeModel model) {
    try {
      daoTemplate.insert("paymentTrade.insert", model);
    } catch (Exception e) {
      throw new ServiceException(ServicePaymentApiCode.UNKNOWN_ERROR);
    }
  }


}
