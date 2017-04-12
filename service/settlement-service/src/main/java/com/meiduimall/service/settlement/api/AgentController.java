package com.meiduimall.service.settlement.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.model.EcmAgent;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfAgentWater;
import com.meiduimall.service.settlement.model.EcmStore;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.DepositService;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: AgentController.java
 * Author:   guidl
 * Date:     2017年3月24日 上午11:25:02
 * Description: 个代保证金分润
 */
@RestController
@RequestMapping("/settlementservice/agentservice/v1")
public class AgentController {
	
	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private AgentService agentService;
	

	/**
	 * 功能描述:  保证金分润
	 * Author: guidl
	 * Date:   2017年3月24日 下午11:25:02
	 * param   ecmAgent
	 * return  ResBodyData
	 */
	@PostMapping("/sharedeposit")
	public ResBodyData shareDeposit(@Validated EcmAgent ecmAgent) {
		try {
			
			long start = System.currentTimeMillis();
			logger.info("share profit for agent start:{}", start);
			
			//判断当前个代是否已分润过
			List<EcmMzfAgentWater> shareResults = agentService.getShareProfitResult(ecmAgent.getId(), ecmAgent.getRecNo());
			if(CollectionUtils.isNotEmpty(shareResults)){
				return SettlementUtil.failure("", "新个代"+ecmAgent.getAgentNo()+"已分润，不可重复分润");
			}
			
			//调用分润方法
			List<Map<String, Object>> resultList = depositService.shareDeposit(ecmAgent);
			
			long end = System.currentTimeMillis();
			logger.info("share profit for agent end:{}", end);
			logger.info("total time(second) for shareprofit:{}", (end - start) / 1000);
			
			if(CollectionUtils.isNotEmpty(resultList)){
				return SettlementUtil.success(resultList, "保证金分润成功");
			}
			return SettlementUtil.failure("", "保证金分润失败");

		} catch (ApiException e) {
			logger.error("保证金分润异常：{}", e);
			return SettlementUtil.failure("", "操作失败");
		}

	}
	
	
	/**
	 * 功能描述:  新商家送积分
	 * Author: guidl
	 * Date:   2017年3月24日 下午11:25:02   
	 * param   ecmStore(手机号,商家编号)
	 * return  ResBodyData
	 */
	@PostMapping("/sendscore")
	public ResBodyData sendScore(@Validated EcmStore ecmStore){
		try {
			List<Map<String,Object>> resultList = depositService.updateStoreScore(ecmStore);
			if(CollectionUtils.isNotEmpty(resultList)){
				return SettlementUtil.success(resultList, "新商家送积分成功");
			}
			return SettlementUtil.failure("", "新商家送积分失败");
			
		} catch (Exception e) {
			logger.error("新商家送积分异常：{}", e);
			return SettlementUtil.failure("", "操作失败");
		}
	}
	
	
	/**
	 * 功能描述:  创建区代、个代和商家账号
	 * Author: guidl
	 * Date:   2017年3月24日 下午11:25:02   
	 * param   ecmMzfAccount(code-代理编号)
	 * return  ResBodyData
	 */
	@PostMapping("/createaccoutbalance")
	public ResBodyData createAccoutBalance(@Validated EcmMzfAccount ecmMzfAccount) throws ServiceException {
		depositService.createAccount(ecmMzfAccount);
		return SettlementUtil.success("", "创建账户成功");
	}
	
	
}
