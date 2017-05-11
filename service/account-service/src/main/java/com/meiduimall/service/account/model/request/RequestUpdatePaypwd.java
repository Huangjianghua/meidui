package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修改支付密码请求映射实体
 * @author chencong
 *
 */
public class RequestUpdatePaypwd extends RequestBaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="旧密码不能为空")
	private String old_pay_pwd;
	
	@NotEmpty(message="新密码不能为空")
	private String new_pay_pwd;

	public String getOld_pay_pwd() {
		return old_pay_pwd;
	}

	public void setOld_pay_pwd(String old_pay_pwd) {
		this.old_pay_pwd = old_pay_pwd;
	}

	public String getNew_pay_pwd() {
		return new_pay_pwd;
	}

	public void setNew_pay_pwd(String new_pay_pwd) {
		this.new_pay_pwd = new_pay_pwd;
	}

	@Override
	public String toString() {
		return "RequestUpdatePaypwd [old_pay_pwd=" + old_pay_pwd + ", new_pay_pwd=" + new_pay_pwd + "memId"+getMemId()+"]";
	}
	
}
