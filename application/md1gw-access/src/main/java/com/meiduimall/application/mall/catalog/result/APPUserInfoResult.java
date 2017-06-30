package com.meiduimall.application.mall.catalog.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APPUserInfoResult {

	@JsonProperty("mem_info")
	private MemberInfoResult memInfo;

	@JsonProperty("oto_info")
	private OtoInfoResult otoInfo;

	@JsonProperty("mall_info")
	private MallInfoResult mallInfo;

	public MemberInfoResult getMemInfo() {
		return memInfo;
	}

	public void setMemInfo(MemberInfoResult memInfo) {
		this.memInfo = memInfo;
	}

	public OtoInfoResult getOtoInfo() {
		return otoInfo;
	}

	public void setOtoInfo(OtoInfoResult otoInfo) {
		this.otoInfo = otoInfo;
	}

	public MallInfoResult getMallInfo() {
		return mallInfo;
	}

	public void setMallInfo(MallInfoResult mallInfo) {
		this.mallInfo = mallInfo;
	}
}
