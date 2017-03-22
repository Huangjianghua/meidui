package com.meiduimall.service.settlement.service;

import java.io.IOException;
import java.util.Collection;

import org.apache.http.client.ClientProtocolException;

import com.meiduimall.service.settlement.model.EcmAgent;

public interface O2oCallbackService {
	
	/**
	 * 知订单结算各阶段状态给O2O
	 * @param orderSns
	 * @param statusCode
	 * @return
	 */
	public boolean informSettlementStatus(Collection<String> orderSns,Integer statusCode);
	
	/**
	 * 回调php接口 更新代理表中区代的余款  插入抵扣保证金到缴费记录表中
	 * @param areaAgent
	 * @param amount
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	public String addProxyFee(EcmAgent areaAgent, double amount) throws Exception;

}
