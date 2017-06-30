package com.meiduimall.service.settlement.service;

import java.util.Map;

import com.meiduimall.service.settlement.model.EcmMzfSellerBonus;
import com.meiduimall.service.settlement.model.EcmMzfSellerFee;

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
	public EcmMzfSellerFee getPlatformFee(String time, String sellerName);
	
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
	public int updateSellerFeePhone(String billId, String sellerPhone, String state, String remark);
	
	/**
	 * 更新奖励活动表中的商家账号
	 * @param billId 账单编号
	 * @param sellerPhone 商家账号
	 * @return int
	 * @throws Exception
	 */
	public int updateSellerBonusPhone(String billId, String sellerPhone, String state, String remark);
	
	/**
	 * 发送服务费或奖励
	 * @param billId 账单编号
	 * @param type 类型FW、JL
	 * @param sellerPhone 商家账号
	 * @return boolean
	 * @throws Exception
	 */
	public boolean sendFeeOrBonus(String billId, String type, String sellerPhone, String memId);
	
	/**
	 * 插入服务费活动账单
	 * @param ecmMzfSellerFee 服务费对象
	 * @return int
	 * @throws Exception
	 */
	public int insertSellerFee(EcmMzfSellerFee ecmMzfSellerFee) throws Exception;
	
	/**
	 * 插入奖励金活动账单
	 * @param ecmMzfSellerBonus 奖励金对象
	 * @return int
	 * @throws Exception
	 */
	public int insertSellerBonus(EcmMzfSellerBonus ecmMzfSellerBonus) throws Exception;
	
	/**
	 * 获取服务费活动账单列表
	 * @param params
	 * @return EcmMzfSellerFee 服务费对象列表
	 * @throws Exception
	 */
	public Map<String, Object> getSellerFeeList(Map<String, Object> params);
	
	/**
	 * 获取奖励金活动账单列表
	 * @param params
	 * @return EcmMzfSellerBonus 奖励金对象列表
	 * @throws Exception
	 */
	public Map<String, Object> getSellerBonusList(Map<String, Object> params);
	
	/**
	 * 计算服务费、奖励金
	 * @param sellers 商家信息
	 * @param time 账单期
	 * @throws Exception
	 */
	public void calFeeBonus(String sellers, String time);
	
	/**
	 * 获取商家已发的服务费和奖励金
	 * @param sellerName 商家编号
	 * @param time 账单期
	 * @return SellerFeeBonus 奖励金对象列表
	 * @throws Exception
	 */
	public Map<String, Object> getSellersFeeBonus(Map<String, Object> params);

}
