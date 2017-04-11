package com.meiduimall.application.search.manage.pojo;

import java.util.Date;

public class IndexLog {

	private Integer logId;
	
	private String remark;
	
	private Date logTime;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
}
