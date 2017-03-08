package com.meiduimall.model;

import java.io.Serializable;
import java.util.Date;

public class MSMembersPaypwd implements Serializable{

	private static final long serialVersionUID = 678482069684999253L;
	//会员ID
	private String memId;
	//支付密码
	private String pwd;
	//MD5支付密码
	private String md5Pwd;
	//是否启用支付密码
	private String enable;
	//更新时间
	private Date updateDate;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
}