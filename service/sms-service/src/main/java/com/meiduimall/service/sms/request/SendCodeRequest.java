package com.meiduimall.service.sms.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 发送验证码短信参数模板
 * 
 * @author pc
 *
 */
public class SendCodeRequest implements Serializable {

	private static final long serialVersionUID = 7996920400702100275L;

	@NotNull
	private String phones; // 手机号
	@NotNull
	private String templateId; // 模板id
	private String supplierId;// 渠道编号
	private String params;// 替换短信中的参数
	
	@NotNull
	private String type;// 验证码类型：注册使用的验证码/找回密码使用的验证码...
	
	@NotNull
	private String clientId;// 客户端来源

	// 发动验证码短信，验证码过期时间
	/*
	 * 验证码过期时间，即timeout缓存保存时长：格式:3h, 2mn, 7s or combination 2d4h10s, 1w2d3h10s
	 */
	private String timeout;

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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
