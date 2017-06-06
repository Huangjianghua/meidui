package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.SettlementApiCode;
import com.meiduimall.service.settlement.common.CodeRuleUtil;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfSellerBonus;
import com.meiduimall.service.settlement.model.EcmMzfSellerFee;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.model.Seller;
import com.meiduimall.service.settlement.model.SellerFeeBonus;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.MemberService;
import com.meiduimall.service.settlement.service.SellerFeeBonusService;
import com.meiduimall.service.settlement.util.DateUtil;

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
	public EcmMzfSellerFee getPlatformFee(String time, String sellerName) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("time", time);
		params.put("sellerName", sellerName);
		return baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getPlatformFee");
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
	public int updateSellerFeePhone(String billId, String sellerPhone, String state, String remark) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("billId", billId);
		params.put("sellerPhone", sellerPhone);
		params.put("state", state);
		params.put("remark", remark);
		params.put("issueTime", DateUtil.getCurrentTimeSec());
		return baseMapper.update(params, "EcmMzfSellerFeeMapper.updateSellerFeePhone");
	}

	@Override
	public int updateSellerBonusPhone(String billId, String sellerPhone, String state, String remark) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("billId", billId);
		params.put("sellerPhone", sellerPhone);
		params.put("state", state);
		params.put("remark", remark);
		params.put("issueTime", DateUtil.getCurrentTimeSec());
		return baseMapper.update(params, "EcmMzfSellerFeeMapper.updateSellerBonusPhone");
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean sendFeeOrBonus(String billId, String type, String sellerPhone, String memId) {
		//发放服务费
		if("FW".equals(type)){
			//根据账单编号获取服务费相关信息
			EcmMzfSellerFee ecmMzfSellerFee = this.getSellerFeeByBillId(billId);
			//根据商家编号获取账户信息
			EcmMzfAccount ecmMzfAccount = agentService.findAccountByCode(ecmMzfSellerFee.getSellerName());
			//判断服务费相关信息和账户信息是否存在
			if(ecmMzfSellerFee != null && ecmMzfAccount != null){
				
				EcmMzfAccount account = new EcmMzfAccount();
				account.setCode(ecmMzfSellerFee.getSellerName());
				account.setBalance(ecmMzfSellerFee.getMoney());
				
				//插入发放服务费流水记录
				EcmMzfWater water = new EcmMzfWater();
				water.setWaterId(CodeRuleUtil.getBillFlowCode(1,ecmMzfSellerFee.getSellerName()));
				water.setRemark("服务费");
				water.setCode(ecmMzfSellerFee.getSellerName());
				water.setMoney(ecmMzfSellerFee.getMoney());
				water.setWaterType("6");
				water.setExtId(billId);
				water.setOpTime(new Timestamp(System.currentTimeMillis()));
				water.setBalance(ecmMzfAccount.getBalance());
				
				//更新账户余额
				int accountResult = agentService.updateAccount(account);
				//插入流水
				int waterResult = agentService.insertWater(water);
				//更新商家服务费相关状态（发放成功状态改为1）
				int feeResult = this.updateSellerFeePhone(billId, sellerPhone, "1", "服务费发放成功");
				
				if (accountResult > 0 && waterResult > 0 && feeResult > 0) {
					return true;
				}
			}
			this.updateSellerFeePhone(billId, sellerPhone, "3", "商家账户不存在");
		}else{//发放奖励金
			
			//根据账单编号获取奖励金相关信息
			EcmMzfSellerBonus ecmMzfSellerBonus = this.getSellerBonusByBillId(billId);
			//判断奖励金相关信息是否存在
			if(ecmMzfSellerBonus != null){
				String waterId = CodeRuleUtil.getBillFlowCode(1,ecmMzfSellerBonus.getSellerName());
				//调用会员系统商家赠送奖励金
				boolean result = memberService.accountAdjustAmount(memId, waterId, String.valueOf(ecmMzfSellerBonus.getMoney()), "奖励金");
				if(!result){
					this.updateSellerBonusPhone(billId, sellerPhone, "3", "奖励金发放到会员系统失败");
					return false;
				}
				//插入发放服务费流水记录
				EcmMzfWater water = new EcmMzfWater();
				water.setWaterId(waterId);
				water.setRemark("奖励金");
				water.setCode(ecmMzfSellerBonus.getSellerName());
				water.setMoney(ecmMzfSellerBonus.getMoney().multiply(new BigDecimal(-1)));
				water.setWaterType("7");
				water.setExtId(billId);
				water.setOpTime(new Timestamp(System.currentTimeMillis()));
				//插入流水
				int waterResult = agentService.insertWater(water);
				//更新商家奖励金相关状态（发放成功状态改为1）
				int bonusResult = this.updateSellerBonusPhone(billId, sellerPhone, "1", "奖励金发放成功");
				if(waterResult > 0 && bonusResult > 0){
					return true;
				}
			}
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
	public Map<String, Object> getSellerFeeList(Map<String, Object> params){
		List<EcmMzfSellerFee> sellFeeList = baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellerFeeList");
		int feeCount = baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellerFeeCount");
		Map<String, Object> result = Maps.newHashMap();
		result.put("result", sellFeeList);
		result.put("totalItem", feeCount);
		return result;
	}

	@Override
	public Map<String, Object> getSellerBonusList(Map<String, Object> params){
		List<EcmMzfSellerBonus> sellerBonusList = baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellerBonusList");
		int bonusCount = baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellerBonusCount");
		Map<String, Object> result = Maps.newHashMap();
		result.put("result", sellerBonusList);
		result.put("totalItem", bonusCount);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public void calFeeBonus(String sellers, String time) {
		
		List<Seller> sellerList = JsonUtils.jsonToList(sellers, Seller.class);
		
		for (Seller seller : sellerList) {
			if("0".equals(seller.getServe())){
				logger.info("商家编号为：{}服务费标记为'关闭'不生成服务费和奖励金记录", seller.getSellerName());
				continue;
			}
			//根据time、sellerName获取服务费获取服务费money
			EcmMzfSellerFee ecmMzfSellerFee = this.getPlatformFee(time, seller.getSellerName());
			
			if(ecmMzfSellerFee == null){
				ecmMzfSellerFee = new EcmMzfSellerFee();
				ecmMzfSellerFee.setSellerName(seller.getSellerName());
				ecmMzfSellerFee.setMoney(BigDecimal.valueOf(0));
			}
			
			String billId = CodeRuleUtil.getBillId("MS", seller.getSellerName());
			long tt = DateUtil.getCurrentTimeSec();
			ecmMzfSellerFee.setBillId(billId);
			ecmMzfSellerFee.setMoneyType("FW");
			ecmMzfSellerFee.setRemark("服务费");
			ecmMzfSellerFee.setBillPeriod(time);
			ecmMzfSellerFee.setCreateTime(tt);
			ecmMzfSellerFee.setUpdateTime(tt);
			//插入服务费活动账单，并返回主键id 目的是生成奖励金账单时插入到extId
			try {
				this.insertSellerFee(ecmMzfSellerFee);
			} catch (DaoException e) {
				throw new ServiceException(SettlementApiCode.INSERT_SELLER_FEE_FAIL);
			}
			
			
			//reward=1时，需生成当前商家奖励金活动账单
			if("1".equals(seller.getReward())){
				BigDecimal money = BigDecimal.valueOf(0);
				if(ecmMzfSellerFee.getMoney().compareTo(BigDecimal.ZERO) > 0){
					BigDecimal ratio = new BigDecimal(seller.getRatio()).divide(new BigDecimal(100));
					BigDecimal month = new BigDecimal(360).divide(new BigDecimal(30));
					money = ecmMzfSellerFee.getMoney().multiply(ratio).divide(month, 2, BigDecimal.ROUND_HALF_EVEN);
				}
				
				EcmMzfSellerBonus ecmMzfSellerBonus = new EcmMzfSellerBonus();
				ecmMzfSellerBonus.setBillId(billId);
				ecmMzfSellerBonus.setSellerName(seller.getSellerName());
				ecmMzfSellerBonus.setMoney(money);
				ecmMzfSellerBonus.setMoneyType("JL");
				ecmMzfSellerBonus.setRemark("奖励金");
				ecmMzfSellerBonus.setBillPeriod(time);
				ecmMzfSellerBonus.setCreateTime(tt);
				ecmMzfSellerBonus.setUpdateTime(tt);
				ecmMzfSellerBonus.setExtId(ecmMzfSellerFee.getId());
				try {
					this.insertSellerBonus(ecmMzfSellerBonus);
				} catch (DaoException e) {
					throw new ServiceException(SettlementApiCode.INSERT_SELLER_BONUS_FAIL);
				}
			}
		}
	}

	@Override
	public Map<String, Object> getSellersFeeBonus(Map<String, Object> params){
		List<SellerFeeBonus> sellerFeeBonusList = baseMapper.selectList(params, "EcmMzfSellerFeeMapper.getSellersFeeBonus");
		int totalItem = baseMapper.selectOne(params, "EcmMzfSellerFeeMapper.getSellersFeeBonusCount");
		Map<String, Object> result = Maps.newHashMap();
		result.put("result", sellerFeeBonusList);
		result.put("totalItem", totalItem);
		return result;
	}


}
