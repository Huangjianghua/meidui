package com.meiduimall.service.account.model.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修改支付密码请求映射实体
 * @author chencong
 *
 */
public class RequestGetValidateCode extends RequestBaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="旧密码不能为空")
	private String old_paypwd;
	
	@NotEmpty(message="新密码不能为空")
	private String new_paypwd;

	public String getOld_paypwd() {
		return old_paypwd;
	}

	public void setOld_paypwd(String old_paypwd) {
		this.old_paypwd = old_paypwd;
	}

	public String getNew_paypwd() {
		return new_paypwd;
	}

	public void setNew_paypwd(String new_paypwd) {
		this.new_paypwd = new_paypwd;
	}

	@Override
	public String toString() {
		return "RequestUpdatePaypwd [memId="+getMemId()+",old_paypwd=" + old_paypwd + ", new_paypwd=" + new_paypwd + "]";
	}
	
	
}
