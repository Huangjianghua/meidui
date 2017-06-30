package com.meiduimall.service.member.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 登录请求映射实体
 * @author chencong
 *
 */
public class RequestLogin  implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="用户名不能为空")
	@Length(max=100,message="用户名长度不正确")
	@JsonProperty("user_name")
	private String userName;

	@NotEmpty(message="密码不能为空")
	@JsonProperty("password")
	private String passWord;
	
	private String ip;
	
	private String tokenKey;

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
