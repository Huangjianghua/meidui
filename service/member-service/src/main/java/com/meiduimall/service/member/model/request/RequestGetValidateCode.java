package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取短信验证码请求映射实体
 * @author chencong
 *
 */
public class RequestGetValidateCode  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String memId;
	
	@NotEmpty(message="手机号不能为空")
	@Length(min=11,max=11,message="手机号长度不正确")
	private String phone;
	
	@NotNull(message="短信验证码类型不能为空")
	private Integer type;
	
	private Integer timeout;

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RequestGetValidateCode [memId=" + memId + ", phone=" + phone + ", type=" + type + ", timeout=" + timeout
				+ "]";
	}
	
}
