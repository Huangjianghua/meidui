package com.meiduimall.exception;


import com.meiduimall.core.BaseApiCode;

public class MdBizException extends RuntimeException {

  private int code = 0;
  private String[] params = null;
  private Throwable cause = null;

  protected MdBizException() {
  }


  public MdBizException(Throwable cause) {
    this(cause, BaseApiCode.EXCEPTION_UNKNOWN);
    if (cause instanceof MdBizException || cause instanceof MdSysException) {
      setCode(((MdBizException) cause).getCode());

    }
  }

  public MdBizException(Throwable cause, int code, String... params) {
    this.code = code;
    this.cause = cause;
    this.params = params.clone();
  }

  public MdBizException(int code, String... params) {
    this.code = code;
    this.params = params.clone();
  }

  public int getCode() {
    return code;
  }


  public void setCode(int code) {
    this.code = code;
  }

  @Override
  public Throwable getCause() {
    return cause;
  }

  public void setCause(Throwable cause) {
    this.cause = cause;
  }

  public String[] getParams() {
    return params;
  }

  public void setParams(String[] params) {
    this.params = params;
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }

  @Override
  public String getLocalizedMessage() {
    return super.getLocalizedMessage();
  }
}
