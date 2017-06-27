package com.meiduimall.service.settlement.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.model.EcmMzfSellerBonus;
import com.meiduimall.service.settlement.model.EcmMzfSellerFee;
import com.meiduimall.service.settlement.service.SellerFeeBonusService;

import io.swagger.annotations.ApiOperation;

/**
 * 商家服务费、奖励金
 * @author guidl
 *
 */
@RestController
@RequestMapping("/settlementservice/sellerservice/v1")
public class SellerFeeBonusController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerFeeBonusController.class);
	
	private static final String RESULT = "result";
	private static final String TOTAL_ITEM = "totalItem";
	
	@Autowired
	private SellerFeeBonusService sellerFeeBonusService;
	
	/**
	 * 验证请求计算是否可用
	 * @param time 计算账单时间
	 * @return ResponseBodyData
	 */
	@ApiOperation(value="验证请求计算是否可用", notes="验证请求计算是否可用")
	@PostMapping("/checkcalculate")
	public ResBodyData checkCalculate(String time) {
		try {
			Map<String,Object> result = Maps.newHashMap();
			EcmMzfSellerFee ecmMzfSellerFee = sellerFeeBonusService.getSellerFeeByTime(time);
			if(ecmMzfSellerFee != null){
				result.put("canUsed", "0");
				result.put("lastTime", ecmMzfSellerFee.getCreateTime());
				return SettlementUtil.success(result);
			}
			
			ecmMzfSellerFee = sellerFeeBonusService.getSellerFeeByTime(null);
			if(ecmMzfSellerFee != null){
				result.put("lastTime", ecmMzfSellerFee.getCreateTime());
			}else{
				result.put("lastTime", "");
			}
			result.put("canUsed", "1");
			return SettlementUtil.success(result);
		} catch (DaoException e) {
			logger.error("验证请求计算是否可用异常：{}", e);
			throw new ServiceException(e.getCode());
		}
	}
	
	
	/**
	 * 计算商家服务费和奖励金
	 * @param sellers 商家信息
	 * @return ResponseBodyData
	 */
	@ApiOperation(value="计算商家服务费和奖励金", notes="计算商家服务费和奖励金")
	@PostMapping("/calfeebonus")
	public ResBodyData calFeeBonus(String sellers, String time, String ratio) {
		try {
			
			if(StringUtil.isEmpty(sellers) || StringUtil.isEmpty(time) || StringUtil.isEmpty(ratio)){
				return SettlementUtil.failure("", "参数为空");
			}
			
			EcmMzfSellerFee sellerFee = sellerFeeBonusService.getSellerFeeByTime(time);
			if(sellerFee != null){
				return SettlementUtil.failure("", "不能重复生成"+time+"的账单");
			}
			
			sellerFeeBonusService.calFeeBonus(sellers, time, ratio);
			return SettlementUtil.success("");
			
		} catch (DaoException e) {
			logger.error("计算商家服务费和奖励金异常：{}", e);
			throw new ServiceException(e.getCode());
		}
	}
	
	
	/**
	 * 发放服务费和奖励金
	 * @param billId 账单编号
	 * @param type 发放类型 FW,LJ
	 * @param msAccount 商家账号
	 * @return
	 */
	@ApiOperation(value="发放服务费和奖励金", notes="发放服务费和奖励金")
	@PostMapping("/issuefeebonus")
	public ResBodyData issueFeeBonus(String billId, String type, String memId){
		try {
			StringBuilder errorMsg = new StringBuilder();
			boolean flag = sellerFeeBonusService.sendFeeOrBonus(billId, type, memId, errorMsg);
			if(flag){
				return SettlementUtil.success("");
			}
			return SettlementUtil.failure("", errorMsg.toString());
		} catch (DaoException e) {
			logger.error("发放服务费和奖励金异常：{}", e);
			throw new ServiceException(e.getCode());
		}
	}
	
	
	/**
	 * 获取商家服务费或奖励金列表
	 * @param pageNumber 页数
	 * @param pageSize 每页显示条数
	 * @param params(serllerNames:商家编号,多个可用逗号隔开;time:计算时间;type:查询类型 服务费或奖励金,status:状态  0 全部  1 发放成功  2 未发放  3 发放失败 , 可不填,默认全部)
	 * @param loginType 登录类型(admin-运营、store-商家)
	 * @return ResponseBodyData
	 */
	@ApiOperation(value="获取商家服务费或奖励金列表", notes="获取商家服务费或奖励金列表")
	@PostMapping("/queryfeebonus")
	public ResBodyData queryFeeBonus(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize, String sellerNames, String time,
			String type, String status, String loginType) {
		try {
			Map<String, Object> params = Maps.newHashMap();
			Map<String, Object> result = Maps.newHashMap();
			params.put("time", time);
			params.put("status", status);
			params.put("loginType", loginType);
			if(!StringUtils.isEmpty(sellerNames)){//流水类型可传多个参数，用逗号隔开
				List<String> sellerNameList = Arrays.asList(sellerNames.split(","));
				params.put("sellerName", sellerNameList);
				
			}
			
			if("FW".equals(type)){//服务费列表（次月）
				PageHelper.startPage(pageNumber, pageSize);
				List<EcmMzfSellerFee> sellFeeList = sellerFeeBonusService.getSellerFeeList(params);
				int feeCount = sellerFeeBonusService.getSellerFeeCount(params);
				
				result.put(RESULT, sellFeeList);
				result.put(TOTAL_ITEM, feeCount);
			}else if("JL".equals(type)){//奖励金列表（次月）
				PageHelper.startPage(pageNumber, pageSize);
				List<EcmMzfSellerBonus> sellerBonusList = sellerFeeBonusService.getSellerBonusList(params);
				int bonusCount = sellerFeeBonusService.getSellerBonusCount(params);
				
				result.put(RESULT, sellerBonusList);
				result.put(TOTAL_ITEM, bonusCount);
			}else if("FW-CR".equals(type)){//服务费列表（次日）
				PageHelper.startPage(pageNumber, pageSize);
				List<EcmMzfSellerFee> sellFeeList = sellerFeeBonusService.getSellerFeeMorrowList(params);
				int feeCount = sellerFeeBonusService.getSellerFeeMorrowCount(params);
				
				result.put(RESULT, sellFeeList);
				result.put(TOTAL_ITEM, feeCount);
			}else if("JL-CR".equals(type)){//奖励金列表（次日）
				PageHelper.startPage(pageNumber, pageSize);
				List<EcmMzfSellerBonus> sellerBonusList = sellerFeeBonusService.getSellerBonusMorrowList(params);
				int bonusCount = sellerFeeBonusService.getSellerBonusMorrowCount(params);
				
				result.put(RESULT, sellerBonusList);
				result.put(TOTAL_ITEM, bonusCount);
			}
			
			return SettlementUtil.success(result);
		} catch (DaoException e) {
			logger.error("获取商家服务费或奖励金列表异常：{}", e);
			throw new ServiceException(e.getCode());
		}
	}
	
}
