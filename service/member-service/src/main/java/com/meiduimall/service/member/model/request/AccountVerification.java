package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录请求映射实体
 *
 */
public class AccountVerification  implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="手机号码不能为空")
	@Length(max=11,message="手机号码长度不正确")
	private String phone;
	private String memId;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
}
