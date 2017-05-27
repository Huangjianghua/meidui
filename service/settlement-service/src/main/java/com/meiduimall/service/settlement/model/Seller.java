package com.meiduimall.service.settlement.model;

import java.io.Serializable;

public class Seller implements Serializable {

	private static final long serialVersionUID = 3956075766058874035L;
	
	//商家编号
	private String sellerName;
	//年华率
	private String ratio;
	//标记服务费 是否开启   0 关闭  1开启（目前一直为开启）
	private String serve;
	//标记奖励金 是否开启    0 关闭  1 开启 （关闭时，不需要计算奖励金）
	private String reward;
	//需要计算服务费和奖励金的时间
	private String time;
	
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getServe() {
		return serve;
	}
	public void setServe(String serve) {
		this.serve = serve;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
}
