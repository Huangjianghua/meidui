package com.meiduimall.service.search.entity;

public class Brand {

	private String brandId;
	
	private String brand;
	
	private long count;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", brand=" + brand + ", count="
				+ count + "]";
	}
}
