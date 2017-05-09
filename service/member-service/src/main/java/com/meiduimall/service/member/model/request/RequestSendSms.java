package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 发送短信请求映射实体
 * @author chencong
 *
 */
public class RequestSendSms  implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="模板ID不能为空")
	private String templateId;
	
	@NotEmpty(message="手机号不能为空")
	@Length(min=11,max=11,message="手机号长度不正确")
	private String phone;
	
	private String params;

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
