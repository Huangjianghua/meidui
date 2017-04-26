package com.meiduimall.application.search.domain;

import java.util.Date;

/**
 * 
 * @author:   jianhua.huang 
 * @version:  2017年4月26日 下午2:12:21 0.1 
 * Description:用户搜索词
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
