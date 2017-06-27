package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.model.EcmMzfBonusMorrowDetail;
import com.meiduimall.service.settlement.model.EcmMzfSellerBonus;
import com.meiduimall.service.settlement.model.EcmMzfSellerFee;
import com.meiduimall.service.settlement.model.PlatformFee;
import com.meiduimall.service.settlement.model.SellerFeeBonus;

/**
 * 商家服务费、奖励金service
 * @author guidl
 *
 */
public interface SellerFeeBonusService {
	
	/**
	 * 根据时间获取商家服务费列表
	 * @param time 账单期 格式为YYYY-MM
	 * @return EcmMzfSellerFee
	 */
	public EcmMzfSellerFee getSellerFeeByTime(String time);
	
	/**
	 * 获取商家服务费
	 * @param time 账单期 格式为YYYY-MM
	 * @param sellerName 商家编号
	 * @return EcmMzfSellerFee
	 * @throws Exception
	 */
	public List<PlatformFee> getPlatformFee(String time, String sellerName, String startTime, String endTime);
	
	/**
	 * 根据账单编号获取服务费活动信息
	 * @param billId 账单编号
	 * @return EcmMzfSellerFee 服务费对象
	 * @throws Exception
	 */
	public EcmMzfSellerFee getSellerFeeByBillId(String billId);
	
	/**
	 * 根据账单编号获取奖励金活动信息
	 * @param billId 账单编号
	 * @return EcmMzfSellerBonus 奖励金对象
	 * @throws Exception
	 */
	public EcmMzfSellerBonus getSellerBonusByBillId(String billId);
	
	/**
	 * 更新服务费活动表中的商家账号
	 * @param billId 账单编号
	 * @param sellerPhone 商家账号
	 * @return int
	 * @throws Exception
	 */
	public int updateSellerFeePhone(String billId, String state, String remark);
	
	/**
	 * 更新奖励活动表中的商家账号
	 * @param billId 账单编号
	 * @param sellerPhone 商家账号
	 * @return int
	 * @throws Exception
	 */
	public int updateSellerBonusPhone(String billId, String state, String remark);
	
	/**
	 * 发送服务费或奖励
	 * @param billId 账单编号
	 * @param type 类型FW、JL
	 * @param sellerPhone 商家账号
	 * @return boolean
	 * @throws Exception
	 */
	public boolean sendFeeOrBonus(String billId, String type, String memId, StringBuilder errorMsg);
	
	/**
	 * 插入服务费活动账单
	 * @param ecmMzfSellerFee 服务费对象
	 * @return int
	 * @throws Exception
	 */
	public int insertSellerFee(EcmMzfSellerFee ecmMzfSellerFee);
	
	/**
	 * 插入奖励金活动账单
	 * @param ecmMzfSellerBonus 奖励金对象
	 * @return int
	 * @throws Exception
	 */
	public int insertSellerBonus(EcmMzfSellerBonus ecmMzfSellerBonus);
	
	/**
	 * 获取服务费活动账单列表
	 * @param params
	 * @return EcmMzfSellerFee 服务费对象列表
	 * @throws Exception
	 */
	public List<EcmMzfSellerFee> getSellerFeeList(Map<String, Object> params);
	
	/**
	 * 获取奖励金活动账单列表
	 * @param params
	 * @return EcmMzfSellerBonus 奖励金对象列表
	 * @throws Exception
	 */
	public List<EcmMzfSellerBonus> getSellerBonusList(Map<String, Object> params);
	
	/**
	 * 根据条件获取服务费总数
	 * @param params
	 * @return int
	 * @throws Exception
	 */
	public int getSellerFeeCount(Map<String, Object> params);
	
	/**
	 * 根据条件获取奖励金总数
	 * @param params
	 * @return int
	 * @throws Exception
	 */
	public int getSellerBonusCount(Map<String, Object> params);
	
	/**
	 * 计算服务费、奖励金
	 * @param sellers 商家信息
	 * @param time 账单期
	 * @throws Exception
	 */
	public void calFeeBonus(String sellers, String time, String ratio);
	
	/**
	 * 获取商家已发的服务费和奖励金
	 * @param sellerName 商家编号
	 * @param time 账单期
	 * @return SellerFeeBonus 奖励金对象列表
	 * @throws Exception
	 */
	public List<SellerFeeBonus> getSellersFeeBonus(Map<String, Object> params);
	
	/**
	 * 获取商家已发的服务费和奖励金总数
	 * @param params
	 * @return int
	 * @throws Exception
	 */
	public int getSellersFeeBonusCount(Map<String, Object> params);
	
	/**
	 * 插入次日服务费活动账单
	 * @param ecmMzfSellerFee
	 * @return
	 * @throws Exception
	 */
	public int insertSellerFeeMorrow(EcmMzfSellerFee ecmMzfSellerFee);
	
	/**
	 * 插入次日奖励金活动账单
	 * @param ecmMzfSellerFee
	 * @return
	 * @throws Exception
	 */
	public int insertSellerBonusMorrow(EcmMzfSellerBonus ecmMzfSellerBonus);
	
	/**
	 * 获取未发放次日服务费的商家列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfSellerFee> getSellerFeeMorrow(String previousDay);
	
	/**
	 * 获取未发放次日奖励金的商家列表 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfSellerBonus> getSellerBonusMorrow(String previousDay);
	
	/**
	 * 更新次日服务费账单状态
	 * @param billId
	 * @param state
	 * @param remark
	 * @return
	 * @throws Exception
	 */
	public int updateSellerFeeMorrow(String billId, String state, String remark);
	
	/**
	 * 更新次日奖励金账单状态
	 * @param billId
	 * @param state
	 * @param remark
	 * @return
	 * @throws Exception
	 */
	public int updateSellerBonusMorrow(String billId, String state, String remark);
	
	/**
	 * 获取次日服务费活动账单列表
	 * @param params
	 * @return EcmMzfSellerFee 服务费对象列表
	 * @throws Exception
	 */
	public List<EcmMzfSellerFee> getSellerFeeMorrowList(Map<String, Object> params);
	
	/**
	 * 获取次日奖励金活动账单列表
	 * @param params
	 * @return EcmMzfSellerBonus 奖励金对象列表
	 * @throws Exception
	 */
	public List<EcmMzfSellerBonus> getSellerBonusMorrowList(Map<String, Object> params);
	
	/**
	 * 根据条件获取次日服务费总数
	 * @param params
	 * @return int
	 * @throws Exception
	 */
	public int getSellerFeeMorrowCount(Map<String, Object> params);
	
	/**
	 * 根据条件获取次日奖励金总数
	 * @param params
	 * @return int
	 * @throws Exception
	 */
	public int getSellerBonusMorrowCount(Map<String, Object> params);
	
	/**
	 * 标识已计算服务费的订单（次月、次日）
	 * @param feeMonthBillId
	 * @param feeDayBillId
	 * @return
	 * @throws Exception
	 */
	public int updateBillId(String feeMonthBillId, String feeDayBillId, String orderSn);
	
	/**
	 * 发放次日服务费
	 * @param ecmMzfSellerFee
	 * @throws Exception
	 */
	public void issueFeeMorrow(EcmMzfSellerFee ecmMzfSellerFee);
	
	/**
	 * 发放次日奖励金
	 * @param ecmMzfSellerBonus
	 * @throws Exception
	 */
	public void issueBonusMorrow(EcmMzfSellerBonus ecmMzfSellerBonus, String memId);
	
	/**
	 * 根据账单编号获取服务费活动信息
	 * @param billId 账单编号
	 * @return EcmMzfSellerFee 服务费对象
	 * @throws Exception
	 */
	public EcmMzfSellerFee getSellerFeeMorrowByBillId(String billId);
	
	/**
	 * 根据账单编号获取奖励金活动信息
	 * @param billId 账单编号
	 * @return EcmMzfSellerBonus 奖励金对象
	 * @throws Exception
	 */
	public EcmMzfSellerBonus getSellerBonusMorrowByBillId(String billId);
	
	/**
	 * 插入次日奖励金明细
	 * @param ecmMzfBonusMorrowDetail
	 * @return
	 * @throws Exception
	 */
	public int insertBonusMorrowDetail(EcmMzfBonusMorrowDetail ecmMzfBonusMorrowDetail);

}
