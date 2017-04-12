/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.service;

//import com.meiduimall.core.ErrorInfo;

/**
 * Created by hadoop on 2017/4/11.
 */
public enum SettlementServiceErrorInfo {//implements ErrorInfo
//  SUCCESS("0", "success"),
  DEDUCT_DEPOSIT_FAILED("-1","区代30%代理费抵扣保证金失败"),
  UPD_BALANCE_FAILD("-2","更新账户余额失败"),
  AGENCY_ACCOUNT_NOT_FOUND("-3","代理账户不存在,无法更新账户余额"),
  CALLBACK_O2O_UPD_BALANCE_FAILD("-5","回调o2o更新余款、抵扣保证金插入缴费记录失败");

  private String code;

  private String message;

  SettlementServiceErrorInfo(String code, String message) {
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
