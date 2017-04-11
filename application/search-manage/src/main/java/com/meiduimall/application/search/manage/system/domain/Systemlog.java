package com.meiduimall.application.search.manage.system.domain;

import java.io.Serializable;

import com.meiduimall.application.search.manage.page.PageView;

/**
 * 登入日志信息表
 * 
 * @author Administrator
 *
 */
public class Systemlog extends PageView implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer logId;
	private String account;
	private String logintime;
	private String loginIP;
	private String remark;
	private String startTime;
	private String endTime;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
