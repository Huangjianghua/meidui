package com.meiduimall.core;
/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */


/**
 * 系统级别的错误码
 *
 * Created by simon on 14/03/2017.
 */
public enum ErrorInfoEnum implements ErrorInfo {
  SUCCESS("0", "success"),
  NOT_FOUND("-1", "service not found"),
  VALID_ERROR("-2", "validation error");

  private String code;

  private String message;

  ErrorInfoEnum(String code, String message) {
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