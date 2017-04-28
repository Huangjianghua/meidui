package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.ResBodyData;
import com.meiduimall.service.account.service.PointsService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.DoubleCalculate;

@Service
public class PointsServiceImpl implements PointsService {
	
	private final static Logger logger=LoggerFactory.getLogger(PointsServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public ResBodyData freezePointsAndAddRecord(String memId, Double consumePoints, String orderId, String orderSource) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		
		Double availablePoints=getAvailablePointsByMemId(memId);
		Double balancePoints=DoubleCalculate.sub(availablePoints,consumePoints);
		
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("freezeType", ApplicationConstant.POINTS_FREEZE_TYPE_DJ);
		paramsMap.put("freezePoints", ("-"+consumePoints));
		paramsMap.put("balancePoints", balancePoints);
		
		try {
			baseDao.insert(paramsMap, "MSConsumePointsFreezeInfoMapper.insertPointsFreezeUnFreezeRecord");
		} catch (Exception e) {
			logger.info("exec PointsServiceImpl freezePointsAndAddRecord() error:{}",e.toString());
			resBodyData.setStatus(ApiStatusConst.OPERATION_DB_EX);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.OPERATION_DB_EX));
		}
		return resBodyData;
	}
	
	/**
	 * 根据会员ID查询当前可用积分
	 * 计算公式：  会员当前可用积分=冻结解冻积分的总和+会员当前积分（会员表mem_basic_account_total_quantity字段）
	 * @param memId 会员ID
	 * @return 会员当前可用积分
	 */
	private Double getAvailablePointsByMemId(String memId) {
		Double realPoints = Double.valueOf("0");
		try{
			realPoints = DoubleCalculate.add(getFreezeUnFreezePointsSumByMemId(memId), 
					getCurrentPointsByMemId(memId));
		}catch(Exception e){
			realPoints = Double.valueOf("0");
		}
		logger.info("会员：{}当前可用积分为：{}",memId,realPoints);
		return realPoints;
	}
	

	/**
	 * 根据会员ID查询当前冻结解冻积分的总和
	 * @param memId 会员ID
	 * @return 冻结解冻积分的总和
	 */
	private Double getFreezeUnFreezePointsSumByMemId(String memId) {
		Double realPoints = Double.valueOf("0");
		try{
			Double result=baseDao.selectOne(memId, "MSConsumePointsFreezeInfoMapper.getFreezeUnFreezePointsSumByMemId");
			if(result!=null){
				realPoints=result;
			}
		}catch(Exception e){
			realPoints = Double.valueOf("0");
		}
		logger.info("会员：{}当前冻结解冻的积分总和为：{}",memId,realPoints);
		return realPoints;
	}
	

	/**
	 * 根据会员ID查询当前积分
	 * @param memId 会员ID
	 * @return 当前积分总额（会员表mem_basic_account_total_quantity字段）
	 */
	private Double getCurrentPointsByMemId(String memId) {
		Double realPoints = Double.valueOf("0");
		try{
			String accountPoint = baseDao.selectOne(memId, "MSAccountMapper.getCurrentPointsByMemId");
			realPoints = Double.valueOf(DESC.deyption(accountPoint,memId));
		}catch(Exception e){
			realPoints = Double.valueOf("0");
		}
		logger.info("会员：{}当前积分为：{}",memId,realPoints);
		return realPoints;
	}
	
	@Override
	public boolean getFreezeUnfreezeRecordByOrderId(String orderId) {
		try {
			int check=baseDao.selectOne(orderId, "MSConsumePointsFreezeInfoMapper.getRecordByOrderId");
			if(check > 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ResBodyData unFreezePointsAndAddRecord(String memId, Double consumePoints, String orderId, String orderSource,Map<String,Object>  dataMap) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		
		Double availablePoints=getAvailablePointsByMemId(memId);
		Double balancePoints=DoubleCalculate.sub(availablePoints,consumePoints);
		
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("freezeType",ApplicationConstant.POINTS_FREEZE_TYPE_JD);
		paramsMap.put("freezePoints",consumePoints);
		paramsMap.put("balancePoints", balancePoints);
		try {
			baseDao.insert(paramsMap, "MSConsumePointsFreezeInfoMapper.insertPointsFreezeUnFreezeRecord");
			dataMap.put("before_total_points",getAvailablePointsByMemId(memId));
		} catch (Exception e) {
			logger.error("写入积分冻结明细出现错误-2002，会员编号：{}，订单编号：{}，错误信息：{}", memId, orderId, e.getMessage());
		}
		return resBodyData;
	}

	@Override
	public ResBodyData deductPointsAndAddRecord(String memId, Double consumePoints, String orderId, String orderSource,Map<String,Object>  dataMap) throws SystemException {
		double newCurrentPoints = DoubleCalculate.sub(this.getAvailablePointsByMemId(memId),Double.valueOf(consumePoints));
		boolean flag=this.calculateAndUpdateCurrentPoints(memId,consumePoints,false);
		dataMap.put("now_total_points",getAvailablePointsByMemId(memId));
		if(flag){
			this.insertConsumePointsDetail(memId,orderId, orderSource,0.00, consumePoints,newCurrentPoints,"","","","");
		}
		return null;
	}
	
	private boolean calculateAndUpdateCurrentPoints(String memId, Double consumePoints, boolean isLock) throws SystemException {
		boolean returnBool = false;
		double newCurrentPoints = DoubleCalculate.sub(this.getAvailablePointsByMemId(memId),Double.valueOf(consumePoints));
		if(updateCurrentPointsByMemId(memId,newCurrentPoints)){
			returnBool = true;
		}
		return returnBool;
	}
	
	
	private boolean updateCurrentPointsByMemId(String memId, Double newCurrentPoints) throws SystemException{
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("memId", memId);
		paramsMap.put("newCurrentPoints", DESC.encryption(String.valueOf(newCurrentPoints),memId));
		try {
			int effectCount=baseDao.update(paramsMap, "MSAccountMapper.updateCurrentPointsByMemId");
			if(effectCount <= 0){
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("修改会员积分出现错误，会员编号：{}，错误信息：{]", memId, e.getMessage());
			return false;
		}
	}
	
	private void insertConsumePointsDetail(String memId, String orderId,String orderSource, Double inConsumePoints,Double outConsumePoints, Double balancePoints, String operatorType,String operator, String remark, String calcFlag) {
		Map<String,Object> paramsMap=new HashMap<>();
		paramsMap.put("id", UUID.randomUUID().toString());
		paramsMap.put("memId", memId);
		paramsMap.put("orderId", orderId);
		paramsMap.put("operatorType", operatorType);
		paramsMap.put("orderSource", orderSource);
		paramsMap.put("inConsumePoints", inConsumePoints);
		paramsMap.put("outConsumePoints", outConsumePoints);
		paramsMap.put("balancePoints", balancePoints);
		paramsMap.put("operator", operator);
		paramsMap.put("remark", remark);
		
		try {
			baseDao.insert(paramsMap, "MSConsumePointsDetailMapper.insertAccountPointDetail");
		} catch (Exception e) {
			logger.error("写入积分变动明细出现错误-{}，会员编号：{}，订单编号：{}，错误信息：{}", 
					calcFlag, memId, orderId, e.getMessage());
		}
	}

}