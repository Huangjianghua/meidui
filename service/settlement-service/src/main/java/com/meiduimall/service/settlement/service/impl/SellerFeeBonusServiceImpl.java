package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.meiduimall.service.settlement.common.CodeRuleUtil;
import com.meiduimall.service.settlement.common.O2oApiConstants;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfBonusMorrowDetail;
import com.meiduimall.service.settlement.model.EcmMzfSellerBonus;
import com.meiduimall.service.settlement.model.EcmMzfSellerFee;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.model.PlatformFee;
import com.meiduimall.service.settlement.model.Seller;
import com.meiduimall.service.settlement.model.SellerFeeBonus;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.MemberService;
import com.meiduimall.service.settlement.service.SellerFeeBonusService;
import com.meiduimall.service.settlement.util.DateUtil;

import com.alibaba.fastjson.JSON;


@Service
public class SellerFeeBonusServiceImpl implements SellerFeeBonusService {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerFeeBonusServiceImpl.class);
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private MemberService memberService;

	@Override
	public EcmMzfSellerFee getSellerFeeByTime(String time) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("time", time);
		return baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellerFeeByTime");
	}
	
	@Override
	public List<PlatformFee> getPlatformFee(String time, String sellerName, String startTime, String endTime) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("time", time);
		params.put("sellerName", sellerName);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getPlatformFee");
	}

	@Override
	public EcmMzfSellerFee getSellerFeeByBillId(String billId) {
		return baseMapper.selectOne(billId, "EcmMzfSellerFeeMapper.getSellerFeeByBillId");
	}

	@Override
	public EcmMzfSellerBonus getSellerBonusByBillId(String billId) {
		return baseMapper.selectOne(billId, "EcmMzfSellerFeeMapper.getSellerBonusByBillId");
	}
	
	@Override
	public int updateSellerFeePhone(String billId, String state, String remark) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("billId", billId);
		params.put("state", state);
		params.put("remark", remark);
		params.put("issueTime", DateUtil.getCurrentTimeSec());
		return baseMapper.update(params, "EcmMzfSellerFeeMapper.updateSellerFeePhone");
	}

	@Override
	public int updateSellerBonusPhone(String billId, String state, String remark) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("billId", billId);
		params.put("state", state);
		params.put("remark", remark);
		params.put("issueTime", DateUtil.getCurrentTimeSec());
		return baseMapper.update(params, "EcmMzfSellerFeeMapper.updateSellerBonusPhone");
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean sendFeeOrBonus(String billId, String type, String memId, StringBuilder errorMsg) {
		//发放服务费
		if("FW".equals(type)){
			//根据账单编号获取服务费相关信息
			EcmMzfSellerFee ecmMzfSellerFee = this.getSellerFeeByBillId(billId);
			
			if(ecmMzfSellerFee == null){
				errorMsg.append(billId+"此账单编号不存在");
				return false;
			}
			if("1".equals(ecmMzfSellerFee.getState()) || ecmMzfSellerFee.getMoney().compareTo(BigDecimal.ZERO) == 0){
				errorMsg.append("账号为"+ecmMzfSellerFee.getSellerPhone()+"服务费不可发放");
				return false;
			}
			
			//根据商家编号获取账户信息
			EcmMzfAccount ecmMzfAccount = agentService.findAccountByCode(ecmMzfSellerFee.getSellerName());
			//判断服务费相关信息和账户信息是否存在
			if(ecmMzfAccount != null){
				
				EcmMzfAccount account = new EcmMzfAccount();
				account.setCode(ecmMzfSellerFee.getSellerName());
				account.setBalance(ecmMzfSellerFee.getMoney());
				
				//插入发放服务费流水记录
				EcmMzfWater water = new EcmMzfWater();
				water.setWaterId(CodeRuleUtil.getBillFlowCode(1,ecmMzfSellerFee.getSellerName()));
				water.setRemark("服务费");
				water.setCode(ecmMzfSellerFee.getSellerName());
				water.setMoney(ecmMzfSellerFee.getMoney());
				water.setWaterType(ShareProfitConstants.WATER_TYPE_FEE);
				water.setExtId(billId);
				water.setOpTime(new Timestamp(System.currentTimeMillis()));
				water.setBalance(ecmMzfAccount.getBalance());
				
				//更新账户余额
				int accountResult = agentService.updateAccount(account);
				//插入流水
				int waterResult = agentService.insertWater(water);
				//更新商家服务费相关状态（发放成功状态改为1）
				int feeResult = this.updateSellerFeePhone(billId, "1", "服务费发放成功");
				
				if (accountResult > 0 && waterResult > 0 && feeResult > 0) {
					return true;
				}
			}
			this.updateSellerFeePhone(billId, "3", "商家账户不存在");
		}else if("JL".equals(type)){//发放奖励金
			
			//根据账单编号获取奖励金相关信息
			EcmMzfSellerBonus ecmMzfSellerBonus = this.getSellerBonusByBillId(billId);
			
			if(ecmMzfSellerBonus == null){
				errorMsg.append(billId+"此账单编号不存在");
				return false;
			}
			if("1".equals(ecmMzfSellerBonus.getState()) || ecmMzfSellerBonus.getMoney().compareTo(BigDecimal.ZERO) == 0){
				errorMsg.append("账号为"+ecmMzfSellerBonus.getSellerPhone()+"奖励金不可发放");
				return false;
			}
			
			//判断奖励金相关信息是否存在
			String waterId = CodeRuleUtil.getBillFlowCode(1,ecmMzfSellerBonus.getSellerName());
			//调用会员系统商家赠送奖励金
			boolean result = memberService.accountAdjustAmount(memId, waterId, String.valueOf(ecmMzfSellerBonus.getMoney()), "奖励金", O2oApiConstants.TRADE_TYPE_SJJL);
			if(!result){
				this.updateSellerBonusPhone(billId, "3", "奖励金发放到会员系统失败");
				return false;
			}
			//插入发放服务费流水记录
			EcmMzfWater water = new EcmMzfWater();
			water.setWaterId(waterId);
			water.setRemark("奖励金");
			water.setCode(ecmMzfSellerBonus.getSellerName());
			water.setMoney(ecmMzfSellerBonus.getMoney().multiply(new BigDecimal(-1)));
			water.setWaterType(ShareProfitConstants.WATER_TYPE_BONUS);
			water.setExtId(billId);
			water.setOpTime(new Timestamp(System.currentTimeMillis()));
			//插入流水
			int waterResult = agentService.insertWater(water);
			//更新商家奖励金相关状态（发放成功状态改为1）
			int bonusResult = this.updateSellerBonusPhone(billId, "1", "奖励金发放成功");
			if(waterResult > 0 && bonusResult > 0){
				return true;
			}
		}else if("FW-CR".equals(type)){
			EcmMzfSellerFee ecmMzfSellerFee = this.getSellerFeeMorrowByBillId(billId);
			if(ecmMzfSellerFee == null){
				errorMsg.append(billId+"此账单编号不存在");
				return false;
			}
			if("1".equals(ecmMzfSellerFee.getState()) || ecmMzfSellerFee.getMoney().compareTo(BigDecimal.ZERO) == 0){
				errorMsg.append("账号为"+ecmMzfSellerFee.getSellerPhone()+"服务费不可发放");
				return false;
			}
			
			this.issueFeeMorrow(ecmMzfSellerFee);
			return true;
		}else if("JL-CR".equals(type)){
			EcmMzfSellerBonus ecmMzfSellerBonus = this.getSellerBonusMorrowByBillId(billId);
			if(ecmMzfSellerBonus == null){
				errorMsg.append(billId+"此账单编号不存在");
				return false;
			}
			if("1".equals(ecmMzfSellerBonus.getState()) || ecmMzfSellerBonus.getMoney().compareTo(BigDecimal.ZERO) == 0){
				errorMsg.append("账号为"+ecmMzfSellerBonus.getSellerPhone()+"奖励金不可发放");
				return false;
			}
			this.issueBonusMorrow(ecmMzfSellerBonus, memId);
			return true;
		}
		return false;
	}
	
	@Override
	public int insertSellerFee(EcmMzfSellerFee ecmMzfSellerFee) {
		return baseMapper.insert(ecmMzfSellerFee, "EcmMzfSellerFeeMapper.insertSellerFee");
	}

	@Override
	public int insertSellerBonus(EcmMzfSellerBonus ecmMzfSellerBonus) {
		return baseMapper.insert(ecmMzfSellerBonus, "EcmMzfSellerFeeMapper.insertSellerBonus");
	}
	
	@Override
	public List<EcmMzfSellerFee> getSellerFeeList(Map<String, Object> params) {
		return baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellerFeeList");
	}

	@Override
	public List<EcmMzfSellerBonus> getSellerBonusList(Map<String, Object> params) {
		return baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellerBonusList");
	}

	@Override
	public int getSellerFeeCount(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellerFeeCount");
	}

	@Override
	public int getSellerBonusCount(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellerBonusCount");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void calFeeBonus(String sellers, String time, String ratio) {
		
		List<Seller> sellerList = JSON.parseArray(sellers, Seller.class);
		Map<String, String> ratioMap = SettlementUtil.jsonToMap(ratio);
		
		for (Seller seller : sellerList) {
			if("0".equals(seller.getServe())){
				logger.info("商家编号为：{}服务费标记为'关闭'不生成服务费和奖励金记录", seller.getSellerName());
				continue;
			}
			//根据time、sellerName获取每笔订单服务费相关信息
			List<PlatformFee> platformFeeList = this.getPlatformFee(time, seller.getSellerName(), null, null);
			//当前商家次月服务费总和
			BigDecimal fwMoneyTotal = BigDecimal.valueOf(0);
			//当前商家次月奖励金总和
			BigDecimal jlMoneyTotal = BigDecimal.valueOf(0);
			//当前商家次月每笔订单所获奖励金
			BigDecimal jlMoney = BigDecimal.valueOf(0);
			
			String billId = CodeRuleUtil.getBillId("HFM", seller.getSellerName());
			long tt = DateUtil.getCurrentTimeSec();
			
			if(CollectionUtils.isNotEmpty(platformFeeList)){
				for (PlatformFee platformFee : platformFeeList) {
					//通过平台服务费率获取年费率
					String rate = ratioMap.get(platformFee.getServiceFee());
					if(StringUtils.isEmpty(rate)){
						rate = "0";
					}
					
					if(platformFee.getMoney().compareTo(BigDecimal.ZERO) > 0){
						BigDecimal rates = new BigDecimal(rate).divide(new BigDecimal(100));
						BigDecimal month = new BigDecimal(360).divide(new BigDecimal(30));
						jlMoney = platformFee.getMoney().multiply(rates).divide(month, 2, BigDecimal.ROUND_HALF_EVEN);
					}
					
					//累加当前商家每笔订单服务费
					fwMoneyTotal = fwMoneyTotal.add(platformFee.getMoney());
					
					//累加通过每笔订单服务费计算出来的奖励金
					jlMoneyTotal = jlMoneyTotal.add(jlMoney);
					
					//标识已计算服务费的订单（次月）
					this.updateBillId(billId, null, platformFee.getOrderSn());
				}
			}
			
			
			EcmMzfSellerFee ecmMzfSellerFee = new EcmMzfSellerFee();
			ecmMzfSellerFee.setSellerName(seller.getSellerName());
			ecmMzfSellerFee.setMoney(fwMoneyTotal);
			ecmMzfSellerFee.setSellerPhone(seller.getSellerPhone());
			ecmMzfSellerFee.setBillId(billId);
			ecmMzfSellerFee.setMoneyType("FW");
			ecmMzfSellerFee.setRemark("服务费");
			ecmMzfSellerFee.setBillPeriod(time);
			ecmMzfSellerFee.setCreateTime(tt);
			ecmMzfSellerFee.setUpdateTime(tt);
			//插入服务费活动账单，并返回主键id 目的是生成奖励金账单时插入到extId
			this.insertSellerFee(ecmMzfSellerFee);
			
			//reward=1时，需生成当前商家奖励金活动账单
			if("1".equals(seller.getReward())){
				EcmMzfSellerBonus ecmMzfSellerBonus = new EcmMzfSellerBonus();
				billId = CodeRuleUtil.getBillId("HJM", seller.getSellerName());
				ecmMzfSellerBonus.setBillId(billId);
				ecmMzfSellerBonus.setSellerName(seller.getSellerName());
				ecmMzfSellerBonus.setSellerPhone(seller.getSellerPhone());
				ecmMzfSellerBonus.setMoney(jlMoneyTotal);
				ecmMzfSellerBonus.setMoneyType("JL");
				ecmMzfSellerBonus.setRemark("奖励金");
				ecmMzfSellerBonus.setBillPeriod(time);
				ecmMzfSellerBonus.setCreateTime(tt);
				ecmMzfSellerBonus.setUpdateTime(tt);
				ecmMzfSellerBonus.setExtId(ecmMzfSellerFee.getId());
				this.insertSellerBonus(ecmMzfSellerBonus);
			}
		}
	}

	@Override
	public List<SellerFeeBonus> getSellersFeeBonus(Map<String, Object> params) {
		return baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellersFeeBonus");
	}

	@Override
	public int getSellersFeeBonusCount(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellersFeeBonusCount");
	}

	@Override
	public int insertSellerFeeMorrow(EcmMzfSellerFee ecmMzfSellerFee) {
		return baseMapper.insert(ecmMzfSellerFee, "EcmMzfSellerFeeMapper.insertSellerFeeMorrow");
	}

	@Override
	public int insertSellerBonusMorrow(EcmMzfSellerBonus ecmMzfSellerBonus) {
		return baseMapper.insert(ecmMzfSellerBonus, "EcmMzfSellerFeeMapper.insertSellerBonusMorrow");
	}

	@Override
	public List<EcmMzfSellerFee> getSellerFeeMorrow(String previousDay) {
		return baseMapper.selectList(previousDay, "EcmMzfSellerFeeMapper.getSellerFeeMorrow");
	}

	@Override
	public List<EcmMzfSellerBonus> getSellerBonusMorrow(String previousDay) {
		return baseMapper.selectList(previousDay, "EcmMzfSellerFeeMapper.getSellerBonusMorrow");
	}

	@Override
	public int updateSellerFeeMorrow(String billId, String state, String remark) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("billId", billId);
		params.put("state", state);
		params.put("remark", remark);
		params.put("issueTime", DateUtil.getCurrentTimeSec());
		return baseMapper.update(params, "EcmMzfSellerFeeMapper.updateSellerFeeMorrow");
	}

	@Override
	public int updateSellerBonusMorrow(String billId, String state, String remark) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("billId", billId);
		params.put("state", state);
		params.put("remark", remark);
		params.put("issueTime", DateUtil.getCurrentTimeSec());
		return baseMapper.update(params, "EcmMzfSellerFeeMapper.updateSellerBonusMorrow");
	}

	@Override
	public List<EcmMzfSellerFee> getSellerFeeMorrowList(Map<String, Object> params) {
		return baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellerFeeMorrowList");
	}

	@Override
	public List<EcmMzfSellerBonus> getSellerBonusMorrowList(Map<String, Object> params) {
		return baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellerBonusMorrowList");
	}

	@Override
	public int getSellerFeeMorrowCount(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellerFeeMorrowCount");
	}

	@Override
	public int getSellerBonusMorrowCount(Map<String, Object> params) {
		return baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellerBonusMorrowCount");
	}

	@Override
	public int updateBillId(String feeMonthBillId, String feeDayBillId, String orderSn) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("feeMonthBillId", feeMonthBillId);
		params.put("feeDayBillId", feeDayBillId);
		params.put("orderSn", orderSn);
		return baseMapper.update(params, "EcmMzfSellerFeeMapper.updateBillId");
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void issueFeeMorrow(EcmMzfSellerFee ecmMzfSellerFee) {
		EcmMzfAccount ecmMzfAccount = agentService.findAccountByCode(ecmMzfSellerFee.getSellerName());
		if(ecmMzfAccount != null){
			EcmMzfAccount account = new EcmMzfAccount();
			account.setCode(ecmMzfSellerFee.getSellerName());
			account.setBalance(ecmMzfSellerFee.getMoney());
			
			//插入发放服务费流水记录
			EcmMzfWater water = new EcmMzfWater();
			water.setWaterId(CodeRuleUtil.getBillFlowCode(1,ecmMzfSellerFee.getSellerName()));
			water.setRemark("次日服务费");
			water.setCode(ecmMzfSellerFee.getSellerName());
			water.setMoney(ecmMzfSellerFee.getMoney());
			water.setWaterType(ShareProfitConstants.WATER_TYPE_FEE_MORROW);
			water.setExtId(ecmMzfSellerFee.getBillId());
			water.setOpTime(new Timestamp(System.currentTimeMillis()));
			water.setBalance(ecmMzfAccount.getBalance());
			
			//更新账户余额
			int accountResult = agentService.updateAccount(account);
			//插入流水
			int waterResult = agentService.insertWater(water);
			//更新商家服务费相关状态（发放成功状态改为1）
			int feeResult = this.updateSellerFeeMorrow(ecmMzfSellerFee.getBillId(), "1", "次日服务费发放成功");
			
			if (accountResult > 0 && waterResult > 0 && feeResult > 0) {
				logger.info("商家编号为：{}发放次日服务费金额：{}成功", ecmMzfSellerFee.getSellerName(), ecmMzfSellerFee.getMoney());
			}
		} else {
			this.updateSellerFeeMorrow(ecmMzfSellerFee.getBillId(), "3", "商家账户不存在");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void issueBonusMorrow(EcmMzfSellerBonus ecmMzfSellerBonus, String memId) {
		String waterId = CodeRuleUtil.getBillFlowCode(1,ecmMzfSellerBonus.getSellerName());
		//调用会员系统商家赠送奖励金
		boolean result = memberService.accountAdjustAmount(memId, waterId, String.valueOf(ecmMzfSellerBonus.getMoney()), "奖励金", O2oApiConstants.TRADE_TYPE_SJJL);
		if(!result){
			this.updateSellerBonusMorrow(ecmMzfSellerBonus.getBillId(), "3", "发放次日奖励金到会员系统失败");
			return;
		}
		//插入发放服务费流水记录
		EcmMzfWater water = new EcmMzfWater();
		water.setWaterId(waterId);
		water.setRemark("奖励金");
		water.setCode(ecmMzfSellerBonus.getSellerName());
		water.setMoney(ecmMzfSellerBonus.getMoney().multiply(new BigDecimal(-1)));
		water.setWaterType(ShareProfitConstants.WATER_TYPE_BONUS_MORROW);
		water.setExtId(ecmMzfSellerBonus.getBillId());
		water.setOpTime(new Timestamp(System.currentTimeMillis()));
		//插入流水
		int waterResult = agentService.insertWater(water);
		//更新商家次日奖励金相关状态（发放成功状态改为1）
		int bonusResult = this.updateSellerBonusMorrow(ecmMzfSellerBonus.getBillId(), "1", "次日奖励金发放成功");
		if(waterResult > 0 && bonusResult > 0){
			logger.info("商家编号为：{}发放次日奖励金金额：{}成功", ecmMzfSellerBonus.getSellerName(), ecmMzfSellerBonus.getMoney());
		}
	}

	@Override
	public EcmMzfSellerFee getSellerFeeMorrowByBillId(String billId) {
		return baseMapper.selectOne(billId, "EcmMzfSellerFeeMapper.getSellerFeeMorrowByBillId");
	}

	@Override
	public EcmMzfSellerBonus getSellerBonusMorrowByBillId(String billId) {
		return baseMapper.selectOne(billId, "EcmMzfSellerFeeMapper.getSellerBonusMorrowByBillId");
	}

	@Override
	public int insertBonusMorrowDetail(EcmMzfBonusMorrowDetail ecmMzfBonusMorrowDetail) {
		return baseMapper.insert(ecmMzfBonusMorrowDetail, "EcmMzfSellerFeeMapper.insertBonusMorrowDetail");
	}
	
}
