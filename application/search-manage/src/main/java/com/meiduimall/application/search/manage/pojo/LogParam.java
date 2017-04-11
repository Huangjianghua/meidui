package com.meiduimall.application.search.manage.pojo;

import com.meiduimall.application.search.manage.domain.SearchLog;

public class LogParam extends SearchLog {

	private String startTime;
	
	private String endTime;

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
