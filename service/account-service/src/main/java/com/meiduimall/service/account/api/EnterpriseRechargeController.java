package com.meiduimall.service.account.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.Constant;
import com.meiduimall.service.account.model.AccountFlowEntity;
import com.meiduimall.service.account.model.AccountRechargeApply;
import com.meiduimall.service.account.model.BusinessManagementEntity;
import com.meiduimall.service.account.model.MSRechargeApply;
import com.meiduimall.service.account.model.RefundRequestEntity;
import com.meiduimall.service.account.service.IEnterpriseRechargeService;


 
/**
 * 企业充值相关操作
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class EnterpriseRechargeController {
	
	private final static Logger logger=LoggerFactory.getLogger(GciV1Controller.class);
	
	@Autowired
	private IEnterpriseRechargeService enterpriseRechargeService;
	/**
	 * 外部充值申请
	 */
	@PostMapping(value="/rechargeApply")
	public ResBodyData rechargeApply(@RequestBody MSRechargeApply deposit){
		try {
			enterpriseRechargeService.rechargeApply(deposit);
		} catch (MdBizException e) {
			logger.error("申请充值操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 外部充值申请列表
	 */
	@RequestMapping(value="/findExternalRechargeList")
	public ResBodyData  findExternalRechargeList(@RequestBody MSRechargeApply mSRechargeApply){
		List<AccountRechargeApply> accountRechargeApply = null;
		try{
			//分页查询
			if(mSRechargeApply.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(mSRechargeApply.getPageNum(), mSRechargeApply.getPageSize());
				PageHelper.orderBy("rechargeDate DESC");
			}else{
				//不分页
				PageHelper.startPage(mSRechargeApply.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("rechargeDate DESC");
			}
			accountRechargeApply = enterpriseRechargeService.queryExternalList(mSRechargeApply);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询充值列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(accountRechargeApply));
		return res;
	}
	/**
	 * 更新充值状态
	 */
	@PostMapping(value="/updateRechargeStatus")
	public ResBodyData updateRechargeStatus(@RequestBody MSRechargeApply mSRechargeApply){
		try {
			enterpriseRechargeService.updateRechargeStatus(mSRechargeApply);
		} catch (MdBizException e) {
			logger.error("更新充值状态操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 企业管理插入数据
	 */
	@PostMapping(value="/insertBusinessManagement")
	public ResBodyData  insertBusinessManagement(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			enterpriseRechargeService.insertBusinessManagement(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("企业管理插入数据操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 企业管理详情插入数据
	 */
	@PostMapping(value="/insertTripartiteEnterpriseDetail")
	public ResBodyData  insertTripartiteEnterpriseDetail(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			enterpriseRechargeService.insertTripartiteEnterpriseDetail(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("企业管理详情插入数据操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 企业管理帐户更新
	 */
	@PostMapping(value="/updateEnterprise")
	public ResBodyData  updateEnterprise(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			enterpriseRechargeService.updateEnterprise(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("企业管理帐户更新操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 调整授信,帐户充值
	 */
	@PostMapping(value="/updateEnterpriseAccount")
	public ResBodyData  updateEnterpriseAccount(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			enterpriseRechargeService.updateEnterpriseAccount(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("调整授信,帐户充值更新操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 帐户最大的充值上限（现金余额+授信）
	 */
	@PostMapping(value="/rechargeCeiling")
	public ResBodyData rechargeCeiling(@RequestBody BusinessManagementEntity businessManagementEntity){
		BusinessManagementEntity buMana = new BusinessManagementEntity();
		try {
			buMana = enterpriseRechargeService.rechargeCeiling(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("帐户最大的充值上限操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,buMana);
	}
	/**
	 * 企业管理查询列表
	 */
	@RequestMapping(value="/findBusinessManagementList")
	public ResBodyData  findBusinessManagementList(@RequestBody BusinessManagementEntity businessManagementEntity){
		List<BusinessManagementEntity> buManagementEntity = null;
		try{
			//分页查询
			if(businessManagementEntity.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(businessManagementEntity.getPageNum(), businessManagementEntity.getPageSize());
				//PageHelper.orderBy("memRegTime DESC");
			}else{
				//不分页
				PageHelper.startPage(businessManagementEntity.getPageNum(), 0, false, false, true);
				//PageHelper.orderBy("memRegTime DESC");
			}
			buManagementEntity = enterpriseRechargeService.findBusinessManagementList(businessManagementEntity);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询企业管理列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(buManagementEntity));
		return res;
	}
	/**
	 * 企业管理详情查询列表
	 */
	@RequestMapping(value="/findTripartiteEnterpriseDetailList")
	public ResBodyData  findTripartiteEnterpriseDetailList(@RequestBody BusinessManagementEntity businessManagementEntity){
		List<BusinessManagementEntity> buManagementEntity = null;
		try{
			//分页查询
			if(businessManagementEntity.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(businessManagementEntity.getPageNum(), businessManagementEntity.getPageSize());
				//PageHelper.orderBy("memRegTime DESC");
			}else{
				//不分页
				PageHelper.startPage(businessManagementEntity.getPageNum(), 0, false, false, true);
				//PageHelper.orderBy("memRegTime DESC");
			}
			buManagementEntity = enterpriseRechargeService.findTripartiteEnterpriseDetailList(businessManagementEntity);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询企业管理详情查询列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(buManagementEntity));
		return res;
	}
	/**
	 * 账户名称查询列表
	 */
	@RequestMapping(value="/findAccountNameList")
	public ResBodyData  findAccountNameList(@RequestBody BusinessManagementEntity businessManagementEntity){
		List<BusinessManagementEntity> buManagementEntity = null;
		try{
			buManagementEntity = enterpriseRechargeService.findAccountNameList(businessManagementEntity);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询账户名称查询列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(buManagementEntity));
		return res;
	}
	/**
	 * 帐户流水插入数据
	 */
	@PostMapping(value="/insertAccountFlow")
	public ResBodyData  insertAccountFlow(@RequestBody AccountFlowEntity accountFlowEntity){
		try {
			enterpriseRechargeService.insertAccountFlow(accountFlowEntity);
		} catch (MdBizException e) {
			logger.error("帐户流水插入数据操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 帐户流水查询列表
	 */
	@RequestMapping(value="/findAccountFlowList")
	public ResBodyData  findAccountFlowList(@RequestBody AccountFlowEntity accountFlowEntity){
		List<AccountFlowEntity> accoFlowEntity = null;
		try{
			//分页查询
			if(accountFlowEntity.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(accountFlowEntity.getPageNum(), accountFlowEntity.getPageSize());
				PageHelper.orderBy("createdDate DESC");
			}else{
				//不分页
				PageHelper.startPage(accountFlowEntity.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("createdDate DESC");
			}
			accoFlowEntity = enterpriseRechargeService.findAccountFlowList(accountFlowEntity);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询帐户流水列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(accoFlowEntity));
		return res;
	}
	/**
	 * 退款申请插入数据 
	 */
	@PostMapping(value="/insertRefundRequest")
	public ResBodyData  insertRefundRequest(@RequestBody RefundRequestEntity refundRequestEntity){
		try {
			enterpriseRechargeService.insertRefundRequest(refundRequestEntity);
		} catch (MdBizException e) {
			logger.error("退款申请插入数据 操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M);
	}
	/**
	 * 退款申请查询列表
	 */
	@RequestMapping(value="/findRefundRequestList")
	public ResBodyData  findRefundRequestList(@RequestBody RefundRequestEntity refundRequestEntity){
		List<RefundRequestEntity> refRequestEntity = null;
		try{
			//分页查询
			if(refundRequestEntity.getFlg().equals(Constant.ONE)){
				//分页
				PageHelper.startPage(refundRequestEntity.getPageNum(), refundRequestEntity.getPageSize());
				//PageHelper.orderBy("memRegTime DESC");
			}else{
				//不分页
				PageHelper.startPage(refundRequestEntity.getPageNum(), 0, false, false, true);
				//PageHelper.orderBy("memRegTime DESC");
			}
			refRequestEntity = enterpriseRechargeService.findRefundRequestList(refundRequestEntity);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询充值列表Controller异常:{}", e.getMessage());
			throw new ApiException(ApiStatusConst.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ApiStatusConst.SUCCESS, ApiStatusConst.SUCCESS_M,new PageInfo<>(refRequestEntity));
		return res;
	}
}
