package com.meiduimall.service.settlement.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.base.Strings;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.OrderService;
import com.meiduimall.service.settlement.service.WaterService;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: WaterController.java
 * Author:   guidl
 * Date:     2017年3月24日 下午14:14:28
 * Description: 流水管理
 */
@RestController
@RequestMapping("/settlementservice/revenueservice/v1")
public class WaterController {
	
	private static final Logger logger = LoggerFactory.getLogger(WaterController.class);
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private WaterService waterService;
	
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 功能描述:  获取流水列表
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * param   params(waterId,code,waterType,opTimeStart,opTimeEnd)
	 * param   type:list,export 主要用于获取流水列表和导出功能（导出流水列表不需要分页）
	 * return  ResBodyData
	 */
	@PostMapping("/querywater")
	public ResBodyData queryWater(
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "type", defaultValue = "list") String type,
			@RequestParam HashMap<String, Object> params, String waterType) {
		try {
			if("list".equals(type)){
				PageHelper.startPage(pageNumber, pageSize);
			}
			
			if(!Strings.isNullOrEmpty(waterType)){//流水类型可传多个参数，用逗号隔开
				List<String> waterTypeList = Arrays.asList(waterType.split(","));
				params.put("waterType", waterTypeList);
			}
			
			List<EcmMzfWater> waterList = agentService.getWaterList(params);
			int total = agentService.getWaterCount(params);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("waterList", waterList);
			map.put("total", total);

			return SettlementUtil.success(map, "获取流水列表成功");

		} catch (Exception e) {
			logger.error("获取流水列表异常：{}", e);
			return SettlementUtil.failure("", "操作失败");
		}
	}
	

	/**
	 * 功能描述:  根据流水编号获取流水详情
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * param   waterId、waterType
	 * param   loginType、code、pageNumber、pageSize 查询账单流水详情数据才需要的参数
	 * return  ResBodyData
	 */
	@PostMapping("/querywaterbyid")
	public ResBodyData queryWaterById(String waterId, String waterType, Integer loginType, String code,
			Integer pageNumber, Integer pageSize) {
		
		if(StringUtil.isEmpty(waterId) || StringUtil.isEmpty(waterType)){
			return SettlementUtil.buildReponseData("", ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE, "流水编号或流水类型不能为空!");
		}
		/** 1：代理 2：商家 3：其他(比如admin) **/
		if(ShareProfitConstants.WATER_TYPE_BILL.equals(waterType)){
			if(loginType==null){
				return SettlementUtil.buildReponseData("", ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE, "查询账单流水详情，登陆类型不能为空!");
			}else if(loginType==1 && StringUtil.isEmpty(code)){
				return SettlementUtil.buildReponseData("", ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE, "查询账单流水详情，登陆类型为代理，代理编号不能为空!");
			}
		}

		try {
			
			if(ShareProfitConstants.WATER_TYPE_DRAW_CASH.equals(waterType)){//提现流水详情
				
				Map<String, Object> result = waterService.getWaterType1Detail(waterId, waterType);
				return SettlementUtil.success(result, "获取流水详情成功");
				
			}else if(ShareProfitConstants.WATER_TYPE_BILL.equals(waterType)){//账单流水详情
				
				Integer count = orderService.queryProfitCountByWaterId(waterId);
				
				List<EcmMzfShareProfit> shareProfitList=orderService.queryProfitByWaterByType(waterId,loginType,code,pageNumber,pageSize);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shareProfitList", shareProfitList);
				map.put("total", count);
				
				return SettlementUtil.buildReponseData(map, 0, "获取账单流水详情成功!");
				
			}else if(ShareProfitConstants.WATER_TYPE_AGENT_PROFIT.equals(waterType) || ShareProfitConstants.WATER_TYPE_DEPOSIT.equals(waterType)){//代理费流水详情,保证金流水详情
				
				Map<String, Object> result = waterService.getWaterDetail(waterId, waterType);
				return SettlementUtil.success(result, "获取流水详情成功");
			}
		} catch (Exception e) {
			logger.error("获取流水详情异常：{}", e);
			return SettlementUtil.failure("", "操作失败");
		}
		return null;
	}
	
	
	/**
	 * 功能描述:  获取推荐人推荐费
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * param   code-推荐人编号,recNo-推荐单号
	 * return  ResBodyData
	 */
	@PostMapping("/getrecmoney")
	public ResBodyData getRecMoney(@RequestParam HashMap<String, Object> params){
		try {
			String money = agentService.getRecommenderMoney(params);
			if(!money.isEmpty()){
				return SettlementUtil.success(money, "获取成功");
			}
			return SettlementUtil.failure("", "无数据");
		} catch (Exception e) {
			logger.error("获取推荐人推荐费异常：{}", e);
			return SettlementUtil.failure("", "操作失败");
		}
	}
	
	
}
