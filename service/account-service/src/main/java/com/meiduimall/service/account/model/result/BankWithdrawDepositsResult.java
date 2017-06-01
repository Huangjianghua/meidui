package com.meiduimall.service.account.model.result;

import java.util.List;

import com.meiduimall.service.account.model.MSBankWithdrawDeposit;

public class BankWithdrawDepositsResult {

	// 总页数
	private int totalPage;

	// 数据列表
	private List<MSBankWithdrawDeposit> results;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<MSBankWithdrawDeposit> getResults() {
		return results;
	}

	public void setResults(List<MSBankWithdrawDeposit> results) {
		this.results = results;
	}
}
