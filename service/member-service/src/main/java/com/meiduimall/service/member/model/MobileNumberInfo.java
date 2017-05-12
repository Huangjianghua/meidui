package com.meiduimall.service.member.model;

import java.io.Serializable;

public class MobileNumberInfo implements Serializable{

	 
	private static final long serialVersionUID = 4922250063468583580L;

	
	private Long id;  //bigint(10) NOT NULL主键
	private String Mobile;	//varchar(17) NULL手机号码
	private String ProvinceId;	//varchar(10) NULL省份编号
	private String PostCode;	//varchar(6) NULL邮编
	private String Crop;		//varchar(10) NULL
	private String ProvinceName;//varchar(20) NULL省份名称
	private String CityId; 		//varchar(10) NULL城市编号
	private String CityName; 	// varchar(20) NULL城市名称
	private String AreaCode; 	//varchar(4) NULL区号
	private String To; 			//varchar(10) NULL
	private String VNO;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getProvinceId() {
		return ProvinceId;
	}
	public void setProvinceId(String provinceId) {
		ProvinceId = provinceId;
	}
	public String getPostCode() {
		return PostCode;
	}
	public void setPostCode(String postCode) {
		PostCode = postCode;
	}
	public String getCrop() {
		return Crop;
	}
	public void setCrop(String crop) {
		Crop = crop;
	}
	public String getProvinceName() {
		return ProvinceName;
	}
	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getAreaCode() {
		return AreaCode;
	}
	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public String getVNO() {
		return VNO;
	}
	public void setVNO(String vNO) {
		VNO = vNO;
	}
	@Override
	public String toString() {
		return "Mobile [id=" + id + ", Mobile=" + Mobile + ", ProvinceId=" + ProvinceId + ", PostCode=" + PostCode
				+ ", Crop=" + Crop + ", ProvinceName=" + ProvinceName + ", CityId=" + CityId + ", CityName=" + CityName
				+ ", AreaCode=" + AreaCode + ", To=" + To + ", VNO=" + VNO + "]";
	}
	
	
	
}
