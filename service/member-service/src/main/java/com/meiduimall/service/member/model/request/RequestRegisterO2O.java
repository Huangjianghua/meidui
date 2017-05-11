package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * O2O注册请求映射实体
 * @author chencong
 *
 */
public class RequestRegisterO2O  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="手机号不能为空")
	@Length(min=11,max=11,message="手机号长度不正确")
	private String phone;
	
	@NotEmpty(message="登录密码不能为空")
	private String pass_word;
	
	private String tokenKey;
	
	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	@Override
	public String toString() {
		return "RequestRegisterO2O [phone=" + phone + ", pass_word=" + pass_word + ", tokenKey=" + tokenKey + "]";
	}
	
}
