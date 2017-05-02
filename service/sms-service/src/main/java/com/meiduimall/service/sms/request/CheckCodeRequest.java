package com.meiduimall.service.sms.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 校验验证码参数模板
 * 
 * @author pc
 *
 */
public class CheckCodeRequest implements Serializable {

	private static final long serialVersionUID = 7996920400702100275L;

	/* 公共参数 */
	@NotNull
	private String phones;

	@NotNull
	private String templateId; // 模板id

	@NotNull
	private String verificationCode; // 验证码，用户输入验证码校验时使用
	
	@NotNull
	private String type;// 验证码类型：注册使用的验证码/找回密码使用的验证码...
	
	@NotNull
	private String clientId;// 客户端来源

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
