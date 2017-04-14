/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.sms.SmsApiCode;
import com.meiduimall.service.sms.model.message.CommonShortMessageModel;
import com.meiduimall.service.sms.service.SmsService;


/**
 * 公共短信发送服务
 *
 * @author lin
 * @date 2017-01-12
 */

@RestController
@RequestMapping("notify/short_msg_service/v1")
public class SmsController {

  private static Logger logger = LoggerFactory.getLogger(SmsController.class);

  @Autowired
  private SmsService smsService;

  /**
   * 发送短消息
   *
   * @param model
   * @return
   */
  @RequestMapping("send_common_sms_message")
  public ResBodyData sendSmsMessage(CommonShortMessageModel model) {
    logger.info("进入发短信程序,parama参数值为：" + model.getParams());
    ResBodyData result = new ResBodyData();
    try {
      smsService.sendSmsMessage(model);
    } catch (Exception e) {
      throw new ServiceException(SmsApiCode.NOT_FOUND,SmsApiCode.getZhMsg(SmsApiCode.NOT_FOUND));
    }
    logger.info("结束发普通短信程序");

    return result;
  }

  /**
   * 发送短信验证码
   *
   * @param model
   * @return
   */
  @RequestMapping("send_sms_verification_code")
  public ResBodyData sendSmsVerificationCode(CommonShortMessageModel model) {
	  ResBodyData result = new ResBodyData();

    if (null == model) {
      return result;
    }

    String code = smsService.sendSmsVerificationCode(model);
    result.setStatus(Integer.valueOf(code));
    return result;
  }

  /**
   * 校验短信验证码
   *
   * @param model
   * @return
   */
  @RequestMapping("check_sms_verification_code")
  public ResBodyData checkSmsVerificationCode(@Valid CommonShortMessageModel model) {
    switch  (smsService.checkSmsVerificationCode(model)) {
      case 0:
    	ResBodyData result = new ResBodyData();
    	result.setData(true);
        logger.info("结束校验短信验证码程序");
        return result;
      case -2:
        throw new ServiceException(SmsApiCode.SMS_VALID_CODE_EXPIRED,BaseApiCode.getZhMsg(SmsApiCode.SMS_VALID_CODE_EXPIRED));
      case -1:
        throw new ServiceException(SmsApiCode.SMS_VALID_CODE_UNMATCHED,BaseApiCode.getZhMsg(SmsApiCode.SMS_VALID_CODE_UNMATCHED));
      default:
        throw new ServiceException(SmsApiCode.UNKNOWN,BaseApiCode.getZhMsg(SmsApiCode.UNKNOWN));
    }
  }

}
