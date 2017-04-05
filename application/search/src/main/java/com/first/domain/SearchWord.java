package com.first.domain;

import java.util.Date;

/**
 * 用户搜索词
 * @date 2016年5月4日
 */
public class SearchWord {

	private Integer wordId;
	
	private String word;
	
	private Date searchTime;

	public Integer getWordId() {
		return wordId;
	}

	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Date getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}
}
