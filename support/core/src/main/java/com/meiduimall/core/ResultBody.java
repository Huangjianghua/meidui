/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.core;

/**
 * 返回体
 *
 * Created by simon on 14/03/2017.
 */
public class ResultBody {
  /**
   * 响应代码
   */
  private String code;

  /**
   * 响应消息
   */
  private String msg;

  /**
   * 响应结果
   */
  private Object result;

  public ResultBody(ErrorInfo errorInfo,Object result) {
    this.code = errorInfo.getCode();
    this.msg = errorInfo.getMessage();
    this.result = result;
  }

  public ResultBody() {
    this.code = ErrorInfoEnum.SUCCESS.getCode();
    this.msg = ErrorInfoEnum.SUCCESS.getMessage();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }


}


