package com.meiduimall.service.settlement.api;

import java.math.BigDecimal;
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
import com.meiduimall.service.SettlementApiCode;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.model.EcmMzfSellerBonus;
import com.meiduimall.service.settlement.model.EcmMzfSellerFee;
import com.meiduimall.service.settlement.service.SellerFeeBonusService;

/**
 * 商家服务费、奖励金
 * @author guidl
 *
 */
@RestController
@RequestMapping("/settlementservice/sellerservice/v1")
public class SellerFeeBonusController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerFeeBonusController.class);
	
	@Autowired
	private SellerFeeBonusService sellerFeeBonusService;
	
	/**
	 * 验证请求计算是否可用
	 * @param time 计算账单时间
	 * @return ResponseBodyData
	 */
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
			throw new ServiceException(e.getCode());
		}
	}
	
	/**
	 * 计算商家服务费和奖励金
	 * @param sellers 商家信息
	 * @return ResponseBodyData
	 */
	@PostMapping("/calfeebonus")
	public ResBodyData calFeeBonus(String sellers, String time) {
		try {
			
			if(StringUtil.isEmpty(sellers) || StringUtil.isEmpty(time)){
				return SettlementUtil.failure("", "参数为空");
			}
			
			EcmMzfSellerFee sellerFee = sellerFeeBonusService.getSellerFeeByTime(time);
			if(sellerFee != null){
				return SettlementUtil.failure("", "不能重复生成"+time+"的账单");
			}
			
			sellerFeeBonusService.calFeeBonus(sellers, time);
			return SettlementUtil.success("");
			
		} catch (DaoException e) {
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
	@PostMapping("/issuefeebonus")
	public ResBodyData issueFeeBonus(String billId, String type, String sellerPhone, String memId){
		try {
			//根据账单编号获取服务费相关信息
			if("FW".equals(type)){
				EcmMzfSellerFee ecmMzfSellerFee = sellerFeeBonusService.getSellerFeeByBillId(billId);
				if(ecmMzfSellerFee == null){
					throw new ServiceException(SettlementApiCode.BILL_NOT_EXIST);
				}
				if("1".equals(ecmMzfSellerFee.getState()) || ecmMzfSellerFee.getMoney().compareTo(BigDecimal.ZERO) == 0){
					throw new ServiceException(SettlementApiCode.FEE_NOT_SEND);
				}
			}else{
				EcmMzfSellerBonus ecmMzfSellerBonus = sellerFeeBonusService.getSellerBonusByBillId(billId);
				if(ecmMzfSellerBonus == null){
					throw new ServiceException(SettlementApiCode.BILL_NOT_EXIST);
				}
				if("1".equals(ecmMzfSellerBonus.getState()) || ecmMzfSellerBonus.getMoney().compareTo(BigDecimal.ZERO) == 0){
					throw new ServiceException(SettlementApiCode.BONUS_NOT_SEND);
				}
			}
			
			boolean flag = sellerFeeBonusService.sendFeeOrBonus(billId, type, sellerPhone, memId);
			if(flag){
				return SettlementUtil.success("");
			}
			return SettlementUtil.failure("", "发放失败");
		} catch (DaoException e) {
			throw new ServiceException(e.getCode());
		}
	}
	
	/**
	 * 获取商家服务费或奖励金列表
	 * @param pageNumber 页数
	 * @param pageSize 每页显示条数
	 * @param params(serllerNames:商家编号,多个可用逗号隔开;time:计算时间;type:查询类型 服务费或奖励金,status:状态  0 全部  1 发放成功  2 未发放  3 发放失败 , 可不填,默认全部)
	 * @return ResponseBodyData
	 */
	@PostMapping("/queryfeebonus")
	public ResBodyData queryFeeBonus(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize, String sellerNames, String time,
			String type, String status) {
		try {
			Map<String, Object> params = Maps.newHashMap();
			Map<String, Object> result = Maps.newHashMap();
			params.put("time", time);
			params.put("status", status);
			if(!StringUtils.isEmpty(sellerNames)){//流水类型可传多个参数，用逗号隔开
				List<String> sellerNameList = Arrays.asList(sellerNames.split(","));
				params.put("sellerName", sellerNameList);
				
			}
			
			PageHelper.startPage(pageNumber, pageSize);
			if("FW".equals(type)){
				result = sellerFeeBonusService.getSellerFeeList(params);
			}else{
				result = sellerFeeBonusService.getSellerBonusList(params);
			}
			
			return SettlementUtil.success(result);
		} catch (DaoException e) {
			throw new ServiceException(e.getCode());
		}
	}
	
	/**
	 * 商家平台登录获取服务费奖励金列表
	 * @param pageNumber 页数
	 * @param pageSize 每页显示条数
	 * @param sellerName 商家编号
	 * @param time 账单期 格式为YYYY-MM 或 YYYY
	 * @return ResponseBodyData
	 */
	@PostMapping("/sellersFeeBonus")
	public ResBodyData getSellersFeeBonus(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize, String sellerName, String time){
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("sellerName", sellerName);
			if(time.length() > 4){
				params.put("years", time);
			}else{
				params.put("year", time);
			}
			
			PageHelper.startPage(pageNumber, pageSize);
			Map<String, Object> result = sellerFeeBonusService.getSellersFeeBonus(params);
			return SettlementUtil.success(result);
		} catch (DaoException e) {
			throw new ServiceException(e.getCode());
		}
	}
	
}
