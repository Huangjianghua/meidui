package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 设置支付密码请求映射实体
 * @author chencong
 *
 */
public class RequestRetrievePaypwd implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 会员ID
	 */
	@NotEmpty(message="会员ID不能为空")
	private String memId;
	
	/**
	 * 原始支付密码
	 */
	@NotEmpty(message="支付密码不能为空")
	private String pay_pwd;
	
	/**
	 * 短信验证码
	 */
	@NotEmpty(message="验证码不能为空")
	@Length(min=6,max=6,message="验证码长度不正确")
	private String validate_code;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPay_pwd() {
		return pay_pwd;
	}

	public void setPay_pwd(String pay_pwd) {
		this.pay_pwd = pay_pwd;
	}
	
	public String getValidate_code() {
		return validate_code;
	}

	public void setValidate_code(String validate_code) {
		this.validate_code = validate_code;
	}

	@Override
	public String toString() {
		return "RequestRetrievePaypwd [memId=" + memId + ", pay_pwd=" + pay_pwd + ", validate_code=" + validate_code
				+ "]";
	}
	
}
