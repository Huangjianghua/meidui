package com.first.domain;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Suggest {

	public Suggest() {
		super();
	}

	/**
	 * 关键词
	 */
	@Field
	private String kw;
	
	/**
	 * 拼音
	 */
	@Field
	private List<String> pinyin;
	
	/**
	 * 简拼
	 */
	@Field
	private List<String> abbre;
	
	/**
	 * 词频
	 */
	@Field
	private Integer kwfreq;

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public List<String> getPinyin() {
		return pinyin;
	}

	public void setPinyin(List<String> pinyin) {
		this.pinyin = pinyin;
	}

	public List<String> getAbbre() {
		return abbre;
	}

	public void setAbbre(List<String> abbre) {
		this.abbre = abbre;
	}

	public Integer getKwfreq() {
		return kwfreq;
	}

	public void setKwfreq(Integer kwfreq) {
		this.kwfreq = kwfreq;
	}
}
