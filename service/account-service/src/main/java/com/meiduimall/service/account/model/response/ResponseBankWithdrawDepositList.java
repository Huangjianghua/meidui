package com.meiduimall.service.account.model.response;

import java.util.List;

public class ResponseBankWithdrawDepositList {

	// 总页数
	private int totalPage;

	// 数据列表
	private List<ResponseBankWithdrawDeposit> results;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<ResponseBankWithdrawDeposit> getResults() {
		return results;
	}

	public void setResults(List<ResponseBankWithdrawDeposit> results) {
		this.results = results;
	}
}
