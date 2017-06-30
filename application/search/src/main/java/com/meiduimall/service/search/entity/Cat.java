package com.meiduimall.service.search.entity;

public class Cat {

	private String catId;
	
	private String catName;
	
	private String catParentId;
	
	private String catParentName;
	
	private String catGrdParentId;
	
	private String catGrdParentName;
	
	private long count;

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatParentId() {
		return catParentId;
	}

	public void setCatParentId(String catParentId) {
		this.catParentId = catParentId;
	}

	public String getCatParentName() {
		return catParentName;
	}

	public void setCatParentName(String catParentName) {
		this.catParentName = catParentName;
	}

	public String getCatGrdParentId() {
		return catGrdParentId;
	}

	public void setCatGrdParentId(String catGrdParentId) {
		this.catGrdParentId = catGrdParentId;
	}

	public String getCatGrdParentName() {
		return catGrdParentName;
	}

	public void setCatGrdParentName(String catGrdParentName) {
		this.catGrdParentName = catGrdParentName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Cat [catId=" + catId + ", catName=" + catName
				+ ", catParentId=" + catParentId + ", catParentName="
				+ catParentName + ", catGrdParentId=" + catGrdParentId
				+ ", catGrdParentName=" + catGrdParentName + ", count=" + count
				+ "]";
	}
}
