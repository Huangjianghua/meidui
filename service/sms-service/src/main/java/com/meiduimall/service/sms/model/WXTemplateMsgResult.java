package com.meiduimall.service.sms.model;

/**
 * Created by yangchang on 2017/5/16.
 */
public class WXTemplateMsgResult {

	private int errcode;
	private String errmsg;
	private int msgid;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
}
