/*package com.meiduimall.service.account.model.request;

import java.io.Serializable;

*//**
 * 校验短信验证码请求映射实体
 * @author chencong
 *
 *//*
public class RequestCheckPhoneValidateCode  implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	*//**
	 * 手机号
	 *//*
	private String phones;
	
	*//**
	 * 模板ID
	 *//*
	private String templateId;
	
	*//**
	 * 验证码，用户输入验证码校验时使用
	 *//*
	private String verificationCode;
	
	*//**
	 * 替换短信中的参数，多个参数之间用英文逗号隔开
	 *//*
	private String params;
	
	*//**
	 * 验证码过期时间
	 *//*
	private String timeout;
	
	*//**
	 * 验证码类型
	 *//*
	private String type;
	
	*//**
	 * 客户端来源/调用方
	 *//*
	private String clientId;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
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

	@Override
	public String toString() {
		return "RequestGetPhoneValidateCode [phone=" + phone + ", templateId=" + templateId + ", supplierId="
				+ supplierId + ", params=" + params + ", timeout=" + timeout + ", type=" + type + ", clientId="
				+ clientId + "]";
	}
	
}
*/