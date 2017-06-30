package com.meiduimall.service.account.service;

import java.util.List;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.model.AccountFlowEntity;
import com.meiduimall.service.account.model.AccountRechargeApply;
import com.meiduimall.service.account.model.BusinessManagementEntity;
import com.meiduimall.service.account.model.MSRechargeApply;
import com.meiduimall.service.account.model.RefundRequestEntity;

import com.meiduimall.service.account.model.TripartiteLog;

public interface IEnterpriseRechargeService {
	/**
	 * 插入日志信息
	 */
	public void insertLog(TripartiteLog tripartiteLog)throws MdBizException;
	/**
	 * 外部充值申请
	 */
	public void rechargeApply(MSRechargeApply deposit)throws MdBizException;
	/**
	 * 外部充值列表
	 */
	public List<AccountRechargeApply> queryExternalList(MSRechargeApply MSRechargeApply)throws MdBizException;
	/**
	 * 更新充值状态
	 */
	public ResBodyData updateRechargeStatus(MSRechargeApply MSRechargeApply)throws MdBizException;
	/**
	 * 企业管理插入数据
	 */
	public void insertBusinessManagement(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 企业管理详情插入数据
	 */
	public void insertTripartiteEnterpriseDetail(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 企业管理帐户更新
	 */
	public void updateEnterprise(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 调整授信,帐户充值
	 */
	public void updateEnterpriseAccount(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 帐户最大的充值上限
	 */
	public BusinessManagementEntity rechargeCeiling(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 企业管理查询列表
	 */
	public List<BusinessManagementEntity> findBusinessManagementList(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 企业管理详情查询列表
	 */
	public List<BusinessManagementEntity> findTripartiteEnterpriseDetailList(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 账户名称查询列表
	 */
	public List<BusinessManagementEntity> findAccountNameList(BusinessManagementEntity businessManagementEntity)throws MdBizException;
	/**
	 * 帐户流水插入数据
	 */
	public void insertAccountFlow(AccountFlowEntity accountFlowEntity)throws MdBizException;
	/**
	 * 帐户流水查询列表
	 */
	public List<AccountFlowEntity> findAccountFlowList(AccountFlowEntity accountFlowEntity)throws MdBizException;
	/**
	 * 退款申请插入数据 
	 */
	public void insertRefundRequest(RefundRequestEntity refundRequestEntity)throws MdBizException;
	/**
	 * 退款申请查询列表
	 */
	public List<RefundRequestEntity> findRefundRequestList(RefundRequestEntity refundRequestEntity)throws MdBizException;
}
