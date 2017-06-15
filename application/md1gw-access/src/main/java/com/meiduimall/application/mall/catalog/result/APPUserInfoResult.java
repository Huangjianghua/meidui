package com.meiduimall.application.mall.catalog.result;

public class APPUserInfoResult {

	private MemberInfoResult memInfo;

	private OtoInfoResult otoInfo;

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
