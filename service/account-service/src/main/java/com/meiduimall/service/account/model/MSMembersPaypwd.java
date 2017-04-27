package com.meiduimall.service.account.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;




/**
 * 支付密码表
 * @author chencong
 *
 */
public class MSMembersPaypwd implements Serializable{

	private static final long serialVersionUID = 678482069684999253L;
	
	/**
	 * 会员ID
	 */
	@NotEmpty(message="memId不能为空")
	private String memId;
	
	/**
	 * 原始支付密码
	 */
	@NotEmpty(message="pay_pwd不能为空")
	private String pay_pwd;
	
	/**
	 * 明文支付密码MD5
	 */
	private String md5Pwd;
	
	/**
	 * 支付密码开关状态（该字段已迁移至ms_members表）
	 */
	private String enable;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
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
	public String getMd5Pwd() {
		return md5Pwd;
	}
	public void setMd5Pwd(String md5Pwd) {
		this.md5Pwd = md5Pwd;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "MSMembersPaypwd [memId=" + memId + ", pay_pwd=" + pay_pwd + ", md5Pwd=" + md5Pwd + ", enable=" + enable
				+ ", updateDate=" + updateDate + "]";
	}
	
}