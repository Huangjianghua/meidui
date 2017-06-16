package com.meiduimall.service.search.entity;

public class StatisticLog {

	private Integer logId;
	
	private String keyword;
	
	private Long num;
	
	private String type;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "StatisticLog [keyword=" + keyword + ", num=" + num + ", type="
				+ type + "]";
	}
}
