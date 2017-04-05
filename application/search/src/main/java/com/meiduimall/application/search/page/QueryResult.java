package com.meiduimall.application.search.page;

import java.util.List;

public class QueryResult {

	/**
	 * 分页数据列表
	 */
	@SuppressWarnings("rawtypes")
	private List dateList;

	/**
	 * 总记录数
	 */
	private int totalCount;

	@SuppressWarnings("rawtypes")
	public List getDateList() {
		return dateList;
	}

	@SuppressWarnings("rawtypes")
	public void setDateList(List dateList) {
		this.dateList = dateList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
	

}
