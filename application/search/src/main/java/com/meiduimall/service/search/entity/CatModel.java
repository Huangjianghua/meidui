package com.meiduimall.service.search.entity;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class CatModel implements Serializable {

	private static final long serialVersionUID = -3867002458833222140L;

	@Field(value = "catId")
	private int catId;
	
	@Field(value = "catName")
	private String catName;
	
	@Field(value = "parentId")
	private int parentId;
	
	@Field(value = "parentName")
	private String parentName;
	
	@Field(value = "level")
	private int level;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName == null ? null : catName.trim();
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName == null ? null : parentName.trim();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "CatModel [catId=" + catId + ", catName=" + catName
				+ ", parentId=" + parentId + ", parentName=" + parentName
				+ ", level=" + level + "]";
	}
}
