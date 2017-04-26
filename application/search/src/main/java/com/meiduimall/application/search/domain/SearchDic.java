package com.meiduimall.application.search.domain;

/**
 * 
 * @author:   jianhua.huang 
 * @version:  2017年4月26日 下午2:12:07 0.1 
 * Description:搜索词典
 */
public class SearchDic {

	// 词ID
	private Integer dicId;
	
	// 词内容
	private String dicName;

	public Integer getDicId() {
		return dicId;
	}

	public void setDicId(Integer dicId) {
		this.dicId = dicId;
	}

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
}
