package com.meiduimall.service.account.model.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;

public class OldBankWithdrawDepositsResult {

	// 返回标志，0-成功，其他失败
	@JsonProperty("status_code")
	private String statusCode;
	
	// 返回消息，status_code="0"时返回"Success";status_code!="0"时返回失败消息
	@JsonProperty("result_msg")
	private String resultMsg;
	
	// 总页数
	@JsonProperty("total_page")
	private String totalPage;
	
	// 数据列表
	@JsonProperty("RESULTS")
	private List<MSBankWithdrawDeposit> results;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public List<MSBankWithdrawDeposit> getResults() {
		return results;
	}

	public void setResults(List<MSBankWithdrawDeposit> results) {
		this.results = results;
	}
}
