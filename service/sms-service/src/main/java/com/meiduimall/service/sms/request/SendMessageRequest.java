package com.meiduimall.service.sms.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 发送普通短信参数模板
 * 
 * @author pc
 *
 */
public class SendMessageRequest implements Serializable {

	private static final long serialVersionUID = 7996920400702100275L;

	// 手机号
	@NotNull
	private String phones;

	// 模板id
	@NotNull
	private String templateId;

	// 渠道编号
	private String supplierId;

	// 替换短信中的参数
	private String params;

	// 短信过期时间，即timeout缓存保存时长，单位:秒。传整数
	private Integer timeout;

	@NotNull
	private String sysKey;// 客户端来源

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
