package com.meiduimall.application.search.manage.page;

import java.util.List;

public class PageView {
	
	/**
	 * 分页数据集合
	 */
	@SuppressWarnings("rawtypes")
	private List  dateList ;
	
	/**
	 * 每页显示记录数
	 */
	private Integer  pageSize=30;
	
	/**
	 * 总页数
	 */
	private int  totalPage;
	
	/**
	 * 页码数量
	 */
	private int pagecode = 5;
	
	/**
	 * 当前页
	 */
	private int currentPage = 1;

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 页码开始索引和结束索引
	 */
	private PageIndex pageindex;
	
	/**
	 * 开始记录数
	 */
	private  int offset ;
	
	public PageView(){};
	
	public  PageView(int currentPage){
		//this.pageSize = pageSize;
		this.currentPage = currentPage;
	}
	
	public int getFirstIndex() {
		return (currentPage-1)*pageSize;
	}

	
	public void setQueryResult(QueryResult result){
		setTotalCount(result.getTotalCount());
		setDateList(result.getDateList());
	}
	
	@SuppressWarnings("rawtypes")
	public List getDateList() {
		return dateList;
	}

	@SuppressWarnings("rawtypes")
	public void setDateList(List dateList) {
		this.dateList = dateList;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		this.pageindex = PageIndex.getPageIndex(pagecode, currentPage, totalPage);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//计算总页数 (取模等于0 不等于0)
	    setTotalPage(this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1);
	}

	public int getOffset() {
		offset = (currentPage-1)*pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPagecode() {
		return pagecode;
	}


	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}


	public PageIndex getPageindex() {
		return pageindex;
	}


	public void setPageindex(PageIndex pageindex) {
		this.pageindex = pageindex;
	}
	

}
