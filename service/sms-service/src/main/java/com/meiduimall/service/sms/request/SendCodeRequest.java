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
	private String sysKey;// 客户端来源

	// 短信过期时间，即timeout缓存保存时长，单位:秒。传整数
	private Integer timeout;

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

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getSysKey() {
		return sysKey;
	}

	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}
}
