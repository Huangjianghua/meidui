package com.meiduimall.service.settlement.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.meiduimall.service.settlement.common.CodeRuleUtil;
import com.meiduimall.service.settlement.common.CronExpression;
import com.meiduimall.service.settlement.model.EcmMzfSellerBonus;
import com.meiduimall.service.settlement.model.EcmMzfSellerFee;
import com.meiduimall.service.settlement.model.PlatformFee;
import com.meiduimall.service.settlement.model.Seller;
import com.meiduimall.service.settlement.service.MemberService;
import com.meiduimall.service.settlement.service.O2oCallbackService;
import com.meiduimall.service.settlement.service.SellerFeeBonusService;
import com.meiduimall.service.settlement.util.DateUtil;

@Service
public class FeeBonusTask {
	
	private static final Logger logger = LoggerFactory.getLogger(FeeBonusTask.class);
	
	@Autowired
	private SellerFeeBonusService sellerFeeBonusService;
	
	@Autowired
	private O2oCallbackService o2oCallbackService;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 定时生成次日服务费和奖励金账单
	 * @throws Exception
	 */
	@Scheduled(cron = CronExpression.TIME_ZERO_HOUR_FIFTEEN_MIN)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void calFeeBonusMorrow() throws Exception{
		
		String previousDay = DateUtil.getPreviousDay(new Date());
		String startTime = previousDay + " " + "00:00:00";
		String endTime = previousDay + " " + "23:59:59";
		
		//判断次日服务费账单是否已生成，若已生成将不再生成账单
		Map<String, Object> params = Maps.newHashMap();
		params.put("previousDay", previousDay);
		List<EcmMzfSellerFee> sellFeeList = sellerFeeBonusService.getSellerFeeMorrowList(params);
		if(CollectionUtils.isNotEmpty(sellFeeList)){
			logger.info("{}服务费账单已生成，不再生成", previousDay);
			return;
		}
		
		//回调O2O接口，获取需要生成次日服务费、奖励金的商家信息
		Map<String, String> result = o2oCallbackService.getActiveStoreInfo();
		List<Seller> sellerList = JSON.parseArray(result.get("sellers"), Seller.class);
		if(CollectionUtils.isEmpty(sellerList)){
			logger.info("{}没有需要计算服务费、奖励金的商家信息", previousDay);
			return;
		}
		
		for (Seller seller : sellerList) {
			if("0".equals(seller.getServe())){
				logger.info("商家编号为：{}服务费标记为'关闭'不生成次日服务费记录", seller.getSellerName());
				continue;
			}
			//根据time、sellerName获取每笔订单服务费相关信息
			List<PlatformFee> platformFeeList = sellerFeeBonusService.getPlatformFee(null, seller.getSellerName(), startTime, endTime);
			//当前商家次月服务费总和
			BigDecimal fwMoneyTotal = BigDecimal.valueOf(0);
			//当前商家次月奖励金总和
			BigDecimal jlMoneyTotal = BigDecimal.valueOf(0);
			//当前商家次月每笔订单所获奖励金
			BigDecimal jlMoney = BigDecimal.valueOf(0);
			
			String feeBillId = CodeRuleUtil.getBillNum("HFD", seller.getSellerName());
			String bonusBillId = CodeRuleUtil.getBillNum("HJD", seller.getSellerName());
			long tt = DateUtil.getCurrentTimeSec();
			
			if(CollectionUtils.isNotEmpty(platformFeeList)){
				for (PlatformFee platformFee : platformFeeList) {
					//ratioWaterAmountL < money < ratioWaterAmountR
					if (platformFee.getMoney().compareTo(new BigDecimal(seller.getRatioWaterAmountL())) > 0
							&& platformFee.getMoney().compareTo(new BigDecimal(seller.getRatioWaterAmountR())) < 0) {
						BigDecimal ratio = new BigDecimal(seller.getRatioWaterAmountR()).divide(new BigDecimal(100));
						jlMoney = new BigDecimal(seller.getRatioWaterAmountL()).add(platformFee.getMoney().multiply(ratio));
					}
					
					//累加当前商家每笔订单服务费
					fwMoneyTotal = fwMoneyTotal.add(platformFee.getMoney());
					
					//累加通过每笔订单服务费计算出来的奖励金
					jlMoneyTotal = jlMoneyTotal.add(jlMoney);
					
					//标识已计算服务费的订单（次月）
					sellerFeeBonusService.updateBillId(null, feeBillId, platformFee.getOrderSn());
					
					//插入次日奖励金明细
//					if("1".equals(seller.getReward())){
//						EcmMzfBonusMorrowDetail bonusMorrowDetail = new EcmMzfBonusMorrowDetail();
//						bonusMorrowDetail.setOrderSn(platformFee.getOrderSn());
//						bonusMorrowDetail.setSellerName(bonusMorrowDetail.getSellerName());
//						bonusMorrowDetail.setSellerPhone(bonusMorrowDetail.getSellerPhone());
//						bonusMorrowDetail.setRatio(seller.getRatioWaterAmountL()+"+"+seller.getRatioWaterAmountR());
//						bonusMorrowDetail.setMoney(jlMoney);
//						bonusMorrowDetail.setCreateTime(tt);
//						bonusMorrowDetail.setBillId(bonusBillId);
//						sellerFeeBonusService.insertBonusMorrowDetail(bonusMorrowDetail);
//					}
				}
			}
			
			EcmMzfSellerFee ecmMzfSellerFee = new EcmMzfSellerFee();
			ecmMzfSellerFee.setSellerName(seller.getSellerName());
			ecmMzfSellerFee.setSellerPhone(seller.getSellerPhone());
			ecmMzfSellerFee.setMoney(fwMoneyTotal);
			ecmMzfSellerFee.setBillId(feeBillId);
			ecmMzfSellerFee.setMoneyType("FW-CR");
			ecmMzfSellerFee.setRemark("次日服务费");
			ecmMzfSellerFee.setBillPeriod(previousDay);
			ecmMzfSellerFee.setCreateTime(tt);
			ecmMzfSellerFee.setUpdateTime(tt);
			//插入次日服务费活动账单，并返回主键id 目的是生成次日奖励金账单时插入到extId
			sellerFeeBonusService.insertSellerFeeMorrow(ecmMzfSellerFee);
			
			//reward=1时，需生成当前商家次日奖励金活动账单
			if("1".equals(seller.getReward())){
				EcmMzfSellerBonus ecmMzfSellerBonus = new EcmMzfSellerBonus();
				ecmMzfSellerBonus.setBillId(bonusBillId);
				ecmMzfSellerBonus.setSellerName(seller.getSellerName());
				ecmMzfSellerBonus.setSellerPhone(seller.getSellerPhone());
				ecmMzfSellerBonus.setMoney(jlMoneyTotal);
				ecmMzfSellerBonus.setMoneyType("JL-CR");
				ecmMzfSellerBonus.setRemark("次日奖励金");
				ecmMzfSellerBonus.setBillPeriod(previousDay);
				ecmMzfSellerBonus.setCreateTime(tt);
				ecmMzfSellerBonus.setUpdateTime(tt);
				ecmMzfSellerBonus.setExtId(ecmMzfSellerFee.getId());
				sellerFeeBonusService.insertSellerBonusMorrow(ecmMzfSellerBonus);
			}
		}
	}
	
	/**
	 * 定时发放次日服务费和奖励金
	 * @throws Exception
	 */
	@Scheduled(cron = CronExpression.TIME_ZERO_HOUR_TWENTY_MIN)
	public void issueFeeBonusMorrow() throws Exception{
		String previousDay = DateUtil.getPreviousDay(new Date());
		//获取未发放的次日服务费列表
		List<EcmMzfSellerFee> sellerFeeList = sellerFeeBonusService.getSellerFeeMorrow(previousDay);
		//获取未发放的次日奖励金列表
		List<EcmMzfSellerBonus> sellerBonusList = sellerFeeBonusService.getSellerBonusMorrow(previousDay);
		
		//发放次日服务费
		for (EcmMzfSellerFee ecmMzfSellerFee : sellerFeeList) {
			sellerFeeBonusService.issueFeeMorrow(ecmMzfSellerFee);
		}
		//发放次日奖励金
		for (EcmMzfSellerBonus ecmMzfSellerBonus : sellerBonusList) {
			String memId = memberService.getMemId(ecmMzfSellerBonus.getSellerPhone());
			if(StringUtils.isEmpty(memId)){
				sellerFeeBonusService.updateSellerBonusMorrow(ecmMzfSellerBonus.getBillId(), "3", "会员不存在，发放次日奖励金到会员系统失败");
				continue;
			}
			sellerFeeBonusService.issueBonusMorrow(ecmMzfSellerBonus, memId);
		}
		
	}

	
	
}
