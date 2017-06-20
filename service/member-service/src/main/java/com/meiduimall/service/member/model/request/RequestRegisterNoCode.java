package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 注册请求映射实体
 * 
 * @author chencong
 * 
 */
public class RequestRegisterNoCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "手机号不能为空")
	@Length(min = 11, max = 11, message = "手机号长度不正确")
	private String phone;

	private String login_name;

	@NotEmpty(message = "登录密码不能为空")
	private String pass_word;

	private String share_man;

	private String role_type;

	@NotEmpty(message = "注册来源不能为空")
	private String source;

	private String tokenKey;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getShare_man() {
		return share_man;
	}

	public void setShare_man(String share_man) {
		this.share_man = share_man;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

	@Override
	public String toString() {
		return "RequestRegister [phone=" + phone + ", login_name=" + login_name + ", pass_word=" + pass_word
				+ ", share_man=" + share_man + ", role_type=" + role_type + ", source=" + source + ", tokenKey="
				+ tokenKey + "]";
	}

}
