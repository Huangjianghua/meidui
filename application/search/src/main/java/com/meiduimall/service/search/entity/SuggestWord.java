package com.meiduimall.service.search.entity;

/**
 * 类名: SuggestWord
 * 描述: 搜索提示词
 * 编写者: pjl 
 * 版权: Copyright (C) 2016 first版权所有
 * 创建时间: 2016年6月16日
 */
public class SuggestWord {

	private Integer keyId;
	
	private String kw;
	
	private String pinyin;
	
	private String abbre;
	
	private Integer kwfreq;
	
	private Integer updateTime;

	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getAbbre() {
		return abbre;
	}

	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}

	public Integer getKwfreq() {
		return kwfreq;
	}

	public void setKwfreq(Integer kwfreq) {
		this.kwfreq = kwfreq;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SuggestWord [keyId=" + keyId + ", kw=" + kw + ", pinyin="
				+ pinyin + ", abbre=" + abbre + ", kwfreq=" + kwfreq
				+ ", updateTime=" + updateTime + "]";
	}

}
