package com.meiduimall.service.account.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.AccountFlowEntity;
import com.meiduimall.service.account.model.AccountRechargeApply;
import com.meiduimall.service.account.model.BusinessManagementEntity;
import com.meiduimall.service.account.model.MSRechargeApply;
import com.meiduimall.service.account.model.RefundRequestEntity;
import com.meiduimall.service.account.service.IEnterpriseRechargeService;
@Transactional
@Component
public class EnterpriseRechargeServiceImpl implements IEnterpriseRechargeService {

	private final static Logger logger=LoggerFactory.getLogger(EnterpriseRechargeServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
    /**
     * 外部充值申请
     */
	@Override
	public void rechargeApply(MSRechargeApply dto) throws MdBizException {
		try{
			String id = UUID.randomUUID().toString();
			dto.setRecId(id);
			String orderId = this.getOrderNo(dto);
			logger.info("生成的单号为："+orderId);
			dto.setOrderId(orderId);
			dto.setRechargeDate(new Date());
			baseDao.insert(dto, "MSRechargeApplyMapper.insertRechargeApply");
		}catch(Exception e){
			logger.error("外部充值申请异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	
	}
	private String getOrderNo(MSRechargeApply dto){
		DateFormat dateTimeFormat_input = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = dateTimeFormat_input.format(new Date());
		int machineId = 1; // 最大支持到9  
		int hashCodeV = UUID.randomUUID().toString().hashCode(); 
		if(hashCodeV < 0) {      
			hashCodeV = -hashCodeV; 
		}  
		return "CZ"+dto.getExtCompanyCode()+dateStr + machineId + String.format("%015d", hashCodeV); // %015d：0表示前面补0，15表示长度为15，d表示参数为正整数 
	 }
	/**
	 * 外部充值列表
	 */
	@Override
	public List<AccountRechargeApply> queryExternalList(MSRechargeApply MSRechargeApply) throws MdBizException {
		List<AccountRechargeApply> selectList=null;
		try {
			selectList=baseDao.selectList(MSRechargeApply, "MSRechargeApplyMapper.queryExternalList");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("查询外部充值列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_MEMBER_LIST_ERROR);
		}
		return selectList;
	}
	/**
	 * 更新充值状态
	 */
	public ResBodyData updateRechargeStatus (MSRechargeApply MSRechargeApply) throws MdBizException {
		ResBodyData resBodyData = new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		try{
			MSRechargeApply.setRealityRechargeDate(new Date());
			baseDao.update(MSRechargeApply, "MSRechargeApplyMapper.updateRechargeStatus");
		}catch(Exception e){
			logger.error("更新充值状态异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
		return resBodyData;
		
	}
	/**
	 * 企业管理插入数据
	 */
	@Override
	public void insertBusinessManagement(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		try{
			String id = UUID.randomUUID().toString();
			businessManagementEntity.setEntId(id);
			baseDao.insert(businessManagementEntity, "MSRechargeApplyMapper.insertBusinessManagement");
		}catch(Exception e){
			logger.error("企业管理插入数据异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	
	}
	/**
	 * 企业管理详情插入数据
	 */
	@Override
	public void insertTripartiteEnterpriseDetail(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		try{
			String id = UUID.randomUUID().toString();
			businessManagementEntity.setDetId(id);
			baseDao.insert(businessManagementEntity, "MSRechargeApplyMapper.insertTripartiteEnterpriseDetail");
		}catch(Exception e){
			logger.error("企业管理详情插入数据异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	
	}
	/**
	 * 企业管理帐户更新
	 */
	@Override
	public void updateEnterprise(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		try{
			baseDao.insert(businessManagementEntity, "MSRechargeApplyMapper.updateEnterprise");
		}catch(Exception e){
			logger.error("企业管理帐户更新异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	}
	/**
	 * 调整授信,帐户充值
	 */
	@Override
	public void updateEnterpriseAccount(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		try{
			baseDao.update(businessManagementEntity, "MSRechargeApplyMapper.updateEnterpriseAccount");
		}catch(Exception e){
			logger.error("企业管理帐户更新异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	}
	/**
	 * 帐户最大的充值上限
	 */
	@Override
	public BusinessManagementEntity rechargeCeiling(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		try{
			return baseDao.selectOne(businessManagementEntity, "MSRechargeApplyMapper.findRechargeCeiling");
		}catch(Exception e){
			logger.error("企业管理帐户更新异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	}
	/**
	 * 查询企业管理列表
	 */
	@Override
	public List<BusinessManagementEntity> findBusinessManagementList(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		List<BusinessManagementEntity> selectList=null;
		try {
			selectList=baseDao.selectList(businessManagementEntity, "MSRechargeApplyMapper.findBusinessManagementList");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("查询企业管理列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_MEMBER_LIST_ERROR);
		}
		return selectList;
	}
	/**
	 * 企业管理详情查询列表
	 */
	@Override
	public List<BusinessManagementEntity> findTripartiteEnterpriseDetailList(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		List<BusinessManagementEntity> selectList=null;
		try {
			selectList=baseDao.selectList(businessManagementEntity, "MSRechargeApplyMapper.findTripartiteEnterpriseDetailList");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("查询企业管理详情查询列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_MEMBER_LIST_ERROR);
		}
		return selectList;
	}
	/**
	 * 账户名称查询列表
	 */
	@Override
	public List<BusinessManagementEntity> findAccountNameList(BusinessManagementEntity businessManagementEntity) throws MdBizException {
		List<BusinessManagementEntity> selectList=null;
		try {
			selectList=baseDao.selectList(businessManagementEntity, "MSRechargeApplyMapper.findAccountNameList");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("账户名称查询列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_MEMBER_LIST_ERROR);
		}
		return selectList;
	}
	/**
	 * 帐户流水插入数据
	 */
	@Override
	public void insertAccountFlow(AccountFlowEntity accountFlowEntity) throws MdBizException {
		try{
			String id = UUID.randomUUID().toString();
			accountFlowEntity.setFlowId(id);
			accountFlowEntity.setCreatedDate(new Date());
			baseDao.insert(accountFlowEntity, "MSRechargeApplyMapper.insertAccountFlow");
		}catch(Exception e){
			logger.error("帐户流水插入数据异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	
	}
	/**
	 * 查询帐户流水列表
	 */
	@Override
	public List<AccountFlowEntity> findAccountFlowList(AccountFlowEntity accountFlowEntity) throws MdBizException {
		List<AccountFlowEntity> selectList=null;
		try {
			selectList=baseDao.selectList(accountFlowEntity, "MSRechargeApplyMapper.findAccountFlowList");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("查询帐户流水列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_MEMBER_LIST_ERROR);
		}
		return selectList;
	}
	/**
	 * 退款申请插入数据
	 */
	@Override
	public void insertRefundRequest(RefundRequestEntity refundRequestEntity) throws MdBizException {
		try{
			String id = UUID.randomUUID().toString();
			refundRequestEntity.setRefId(id);
			baseDao.insert(refundRequestEntity, "MSRechargeApplyMapper.insertRefundRequest");
		}catch(Exception e){
			logger.error("退款申请插入数据异常:{}",e);
			throw new MdBizException(ApiStatusConst.INSERT_WITHDRAW_ERROR);
		}
	
	}
	/**
	 * 查询退款申请列表
	 */
	@Override
	public List<RefundRequestEntity> findRefundRequestList(RefundRequestEntity refundRequestEntity) throws MdBizException {
		List<RefundRequestEntity> selectList=null;
		try {
			selectList=baseDao.selectList(refundRequestEntity, "MSRechargeApplyMapper.findRefundRequestList");
			if(!CollectionUtils.isEmpty(selectList)) return selectList;
		}catch(Exception e){
			logger.error("查询退款申请列表出现错误,错误信息:{}", e.getMessage());
			throw new MdBizException(ApiStatusConst.QUERY_MEMBER_LIST_ERROR);
		}
		return selectList;
	}
}
