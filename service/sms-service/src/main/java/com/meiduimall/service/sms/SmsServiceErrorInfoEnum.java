/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service.sms;

import com.meiduimall.core.ErrorInfo;

/**
 * 应用系统级别的错误码
 *
 * Created by simon on 14/03/2017.
 */
public enum SmsServiceErrorInfoEnum implements ErrorInfo {
  SUCCESS("0", "success"),
  NOT_FOUND("-1", "service not found"),
  UNKNOWN("-9", "service not found"),
  REPEATING("-2","请勿频繁重复发送短信"),
  NOT_FOUND_TEMPLATES("-3","获取短信模板列表失败"),
  PARAM_ERROR("-4","替换内容与替换参数不匹配"),
  SMS_VALID_CODE_EXPIRED("-7","验证码已过期"),
  SMS_VALID_CODE_UNMATCHED("-8","验证码不匹配"),
  NOT_FOUND_TEMPLATE("-5","获取不到模板id对应短信模板记录"),
//  SMS_VERIFICATION_CODE_EXPIRED("","验证码已过期"),
//  SMS_VERIFICATION_CODE_UNMATCH("","验证码输入错误"),
  SMS_SEND_FAILUER("-6","发送短信失败！");



  private String code;

  private String message;

  SmsServiceErrorInfoEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode(){
    return this.code;
  }

  public String getMessage(){
    return this.message;
  }
}