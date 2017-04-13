package com.meiduimall.application.md1gwaccess.model;

import java.io.Serializable;
import java.util.Date;

public class Callback implements Serializable {

	
	private static final long serialVersionUID = 7010100751565985276L;

	private String notifyType;//回调类型					
	
	private Date notifyTime;//回调时间					
												
	private NotifyData notifyData;//回调数据					
												
	private String clientId;//客户端ID 					
												
	private Long timestamp;// 时间戳 					
												
	private String sign;//签名 					

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	public NotifyData getNotifyData() {
		return notifyData;
	}

	public void setNotifyData(NotifyData notifyData) {
		this.notifyData = notifyData;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
										
	
	

}
