package com.meiduimall.service.search.entity;

import com.meiduimall.service.search.page.PageView;

public class SearchLog extends PageView {

	private int logId;

	private String keyword;

	private String ip;

	private int resultNum;

	private String type;

	private String searchTime;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getResultNum() {
		return resultNum;
	}

	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	@Override
	public String toString() {
		return "SearchLog [logId=" + logId + ", keyword=" + keyword + ", ip=" + ip + ", resultNum=" + resultNum
				+ ", platform=" + type + ", searchTime=" + searchTime + "]";
	}
}
