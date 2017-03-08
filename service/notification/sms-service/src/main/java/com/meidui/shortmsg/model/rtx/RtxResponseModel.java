package com.meidui.shortmsg.model.rtx;

import java.io.Serializable;

public class RtxResponseModel implements Serializable {

	private static final long serialVersionUID = -491524344630192140L;

	private String statusCode;

	private String statusMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

}
