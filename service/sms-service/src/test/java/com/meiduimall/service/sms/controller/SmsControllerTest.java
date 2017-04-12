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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.SmsApiCode;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.sms.model.message.CommonShortMessageModel;

/**
 * Created by hadoop on 2017/4/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "dev")
public class SmsControllerTest {

  @Autowired
  private SmsController smsController;

  CommonShortMessageModel model = new CommonShortMessageModel();
  String code = "";

  //阿里大于模板未注册，通过漫道发送短信
  @Test
  public void sendSmsMessage_01() throws Exception {
    model.setPhones("18621580756");
    model.setTemplateId("O2O_1004");
    model.setParams("2012年01月01日 12:22:32,333");
    ResBodyData result = smsController.sendSmsMessage(model);
    Assert.assertEquals(BaseApiCode.SUCCESS,result.getStatus());
  }

  //阿里大于模板注册，通过阿里大于发送短信
  @Test
  public void sendSmsMessage_02() throws Exception {
    model.setPhones("18621580756");
    model.setTemplateId("O2O_1001");
    model.setParams("2012年01月01日 12:22:32,333");
    ResBodyData result = smsController.sendSmsMessage(model);
    Assert.assertEquals(BaseApiCode.SUCCESS,result.getStatus()+"");
  }

  //阿里大于模板注册，通过漫道发送验证码
  @Test
  public void sendSmsVerificationCode_01() throws Exception {
    model.setPhones("18621580756");
    model.setTemplateId("MEM_1002");
    ResBodyData result = smsController.sendSmsVerificationCode(model);
    Assert.assertEquals(BaseApiCode.SUCCESS,result.getStatus()+"");
  }

  //阿里大于模板注册，通过阿里大于发送验证码
  @Test
  public void sendSmsVerificationCode_02() throws Exception {
    model.setPhones("18621580756");
    model.setTemplateId("O2O_1002");
    ResBodyData result = smsController.sendSmsVerificationCode(model);
    Assert.assertEquals(BaseApiCode.SUCCESS, result.getStatus()+"");
  }

  //重复发送验证码
  @Test
  public void sendSmsVerificationCode_03() throws Exception {
    model.setPhones("18621580756");
    model.setTemplateId("O2O_1002");
    try {
      smsController.sendSmsVerificationCode(model);
    } catch (ServiceException e) {
      Assert.assertEquals(SmsApiCode.REPEATING, e.getCode());
    }
  }

  @Test
  public void checkSmsVerificationCode_01() throws Exception {
    model.setPhones("18621580756");
    model.setTemplateId("O2O_1002");
    model.setVerificationCode(code);
    boolean result = false;
    try {
      result = Boolean.parseBoolean(smsController.checkSmsVerificationCode(model).toString());
      Assert.assertEquals(true, result);
    } catch (ServiceException e) {
      Assert.assertEquals(false, result);
      Assert.assertEquals(SmsApiCode.REPEATING, e.getCode());
    }
  }

}