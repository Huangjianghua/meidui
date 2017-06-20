package com.meiduimall.service.search.page;

import java.util.List;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * jqGrid分页实体
 * 
 * @author Liujun
 * @date 2016-04-12
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "currentPage", "maxResults", "total", "records", "rows" })
public class JqGridTableViewPage<T> {

	/**
	 * 当前页码
	 */
	private Integer currentPage;

	/**
	 * 当前每页显示的行数
	 */
	private Integer maxResults;

	/**
	 * 总页数
	 */
	private long total;

	/**
	 * 总记录数
	 */
	private long records;

	/**
	 * 返回的数据
	 */
	private List<T> rows;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public long getTotal() {
		// this.total = (this.records % this.maxResults) > 0 ? (this.records /
		// this.maxResults) + 1 : this.records / this.maxResults;
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
