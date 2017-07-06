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
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.AccountFlowEntity;
import com.meiduimall.service.account.model.AccountRechargeApply;
import com.meiduimall.service.account.model.BusinessManagementEntity;
import com.meiduimall.service.account.model.MSRechargeApply;
import com.meiduimall.service.account.model.RefundRequestEntity;
import com.meiduimall.service.account.model.TripartiteLog;
import com.meiduimall.service.account.service.IEnterpriseRechargeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


 
/**
 * 企业充值相关 操作
 *
 */
@Api(value = "第三方企业充值相关操作", description = "第三方企业充值相关操作接口")
@RestController
@RequestMapping("/member/account_service/v1")
public class EnterpriseRechargeController {
	
	private final static Logger logger=LoggerFactory.getLogger(EnterpriseRechargeController.class);
	
	@Autowired
	private IEnterpriseRechargeService enterpriseRechargeService;
	/**
	 * 插入日志信息
	 */
	@ApiOperation(value="插入日志信息", notes="插入日志信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "tripartiteLog", value = "插入日志信息实体", required = true, dataType = "TripartiteLog"),
	})
	@PostMapping(value="/insertLog")
	public ResBodyData insertLog(@RequestBody TripartiteLog tripartiteLog){
		try {
			enterpriseRechargeService.insertLog(tripartiteLog);
		} catch (MdBizException e) {
			logger.error("插入日志信息操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 外部充值申请
	 */
	@ApiOperation(value="外部充值申请", notes="外部充值申请")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "deposit", value = "外部充值申请实体", required = true, dataType = "MSRechargeApply"),
	})
	@PostMapping(value="/rechargeApply")
	public ResBodyData rechargeApply(@RequestBody MSRechargeApply deposit){
		try {
			enterpriseRechargeService.rechargeApply(deposit);
		} catch (MdBizException e) {
			logger.error("申请充值操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 外部充值申请列表
	 */
	@ApiOperation(value="外部充值申请列表", notes="外部充值申请列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "mSRechargeApply", value = "外部充值申请列表实体", required = true, dataType = "MSRechargeApply"),
	})
	@PostMapping(value="/findExternalRechargeList")
	public ResBodyData  findExternalRechargeList(@RequestBody MSRechargeApply mSRechargeApply){
		List<AccountRechargeApply> accountRechargeApply = null;
		try{
			//分页查询
			if(mSRechargeApply.getFlg().equals("1")){
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
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(accountRechargeApply));
		return res;
	}
	/**
	 * 更新充值状态
	 */
	@ApiOperation(value="更新充值状态", notes="更新充值状态")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "mSRechargeApply", value = "更新充值状态实体", required = true, dataType = "MSRechargeApply"),
	})
	@PostMapping(value="/updateRechargeStatus")
	public ResBodyData updateRechargeStatus(@RequestBody MSRechargeApply mSRechargeApply){
		try {
			enterpriseRechargeService.updateRechargeStatus(mSRechargeApply);
		} catch (MdBizException e) {
			logger.error("更新充值状态操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 第三方企业管理插入数据
	 */
	@ApiOperation(value="第三方企业管理插入数据", notes="第三方企业管理插入数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "第三方企业管理插入数据实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/insertBusinessManagement")
	public ResBodyData  insertBusinessManagement(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			enterpriseRechargeService.insertBusinessManagement(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("企业管理插入数据操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 第三方企业管理详情插入数据
	 */
	@ApiOperation(value="第三方企业管理详情插入数据", notes="第三方企业管理详情插入数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "第三方企业管理详情插入数据实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/insertTripartiteEnterpriseDetail")
	public ResBodyData  insertTripartiteEnterpriseDetail(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			enterpriseRechargeService.insertTripartiteEnterpriseDetail(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("企业管理详情插入数据操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 第三方企业管理帐户更新
	 */
	@ApiOperation(value="第三方企业管理帐户更新", notes="第三方企业管理帐户更新")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "第三方企业管理帐户更新实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/updateEnterprise")
	public ResBodyData  updateEnterprise(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			enterpriseRechargeService.updateEnterprise(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("企业管理帐户更新操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 调整授信,帐户充值
	 */
	@ApiOperation(value="调整授信,帐户充值", notes="调整授信,帐户充值")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "调整授信,帐户充值实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/updateEnterpriseAccount")
	public ResBodyData  updateEnterpriseAccount(@RequestBody BusinessManagementEntity businessManagementEntity){
		try {
			logger.info("调整授信,帐户充值>>>>>>");
			enterpriseRechargeService.updateEnterpriseAccount(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("调整授信,帐户充值更新操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 帐户最大的充值上限（现金余额+授信）
	 */
	@ApiOperation(value="帐户最大的充值上限（现金余额+授信）", notes="帐户最大的充值上限（现金余额+授信）")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "帐户最大的充值上限（现金余额+授信）实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/rechargeCeiling")
	public ResBodyData rechargeCeiling(@RequestBody BusinessManagementEntity businessManagementEntity){
		BusinessManagementEntity buMana = new BusinessManagementEntity();
		try {
			buMana = enterpriseRechargeService.rechargeCeiling(businessManagementEntity);
		} catch (MdBizException e) {
			logger.error("帐户最大的充值上限操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,buMana);
	}
	/**
	 * 企业管理查询列表
	 */
	@ApiOperation(value="第三方企业管理查询列表", notes="第三方企业管理查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "第三方企业管理查询列表实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/findBusinessManagementList")
	public ResBodyData  findBusinessManagementList(@RequestBody BusinessManagementEntity businessManagementEntity){
		List<BusinessManagementEntity> buManagementEntity = null;
		try{
			//分页查询
			if(businessManagementEntity.getFlg().equals("1")){
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
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(buManagementEntity));
		return res;
	}
	/**
	 * 第三方企业管理详情查询列表
	 */
	@ApiOperation(value="第三方企业管理详情查询列表", notes="第三方企业管理详情查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "第三方企业管理详情查询列表实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/findTripartiteEnterpriseDetailList")
	public ResBodyData  findTripartiteEnterpriseDetailList(@RequestBody BusinessManagementEntity businessManagementEntity){
		List<BusinessManagementEntity> buManagementEntity = null;
		try{
			//分页查询
			if(businessManagementEntity.getFlg().equals("1")){
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
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(buManagementEntity));
		return res;
	}
	/**
	 * 账户名称查询列表
	 */
	@ApiOperation(value="账户名称查询列表", notes="账户名称查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "businessManagementEntity", value = "账户名称查询列表实体", required = true, dataType = "BusinessManagementEntity"),
	})
	@PostMapping(value="/findAccountNameList")
	public ResBodyData  findAccountNameList(@RequestBody BusinessManagementEntity businessManagementEntity){
		List<BusinessManagementEntity> buManagementEntity = null;
		try{
			buManagementEntity = enterpriseRechargeService.findAccountNameList(businessManagementEntity);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询账户名称查询列表Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(buManagementEntity));
		return res;
	}
	/**
	 * 帐户流水插入数据
	 */
	@ApiOperation(value="帐户流水插入数据", notes="帐户流水插入数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "accountFlowEntity", value = "帐户流水插入数据实体", required = true, dataType = "AccountFlowEntity"),
	})
	@PostMapping(value="/insertAccountFlow")
	public ResBodyData  insertAccountFlow(@RequestBody AccountFlowEntity accountFlowEntity){
		try {
			enterpriseRechargeService.insertAccountFlow(accountFlowEntity);
		} catch (MdBizException e) {
			logger.error("帐户流水插入数据操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 帐户流水查询列表
	 */
	@ApiOperation(value="帐户流水查询列表", notes="帐户流水查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "accountFlowEntity", value = "帐户流水查询列表实体", required = true, dataType = "AccountFlowEntity"),
	})
	@PostMapping(value="/findAccountFlowList")
	public ResBodyData  findAccountFlowList(@RequestBody AccountFlowEntity accountFlowEntity){
		List<AccountFlowEntity> accoFlowEntity = null;
		try{
			//分页查询
			if(accountFlowEntity.getFlg().equals("1")){
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
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(accoFlowEntity));
		return res;
	}
	/**
	 * 退款申请插入数据 
	 */
	@ApiOperation(value="退款申请插入数据 ", notes="退款申请插入数据 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "refundRequestEntity", value = "退款申请插入数据实体", required = true, dataType = "RefundRequestEntity"),
	})
	@PostMapping(value="/insertRefundRequest")
	public ResBodyData  insertRefundRequest(@RequestBody RefundRequestEntity refundRequestEntity){
		try {
			enterpriseRechargeService.insertRefundRequest(refundRequestEntity);
		} catch (MdBizException e) {
			logger.error("退款申请插入数据 操作异常:{}", e.getMessage());
			throw new ApiException(e.getCode(),e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	/**
	 * 退款申请查询列表
	 */
	@ApiOperation(value="退款申请查询列表", notes="退款申请查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "refundRequestEntity", value = "退款申请查询列表实体", required = true, dataType = "RefundRequestEntity"),
	})
	@PostMapping(value="/findRefundRequestList")
	public ResBodyData  findRefundRequestList(@RequestBody RefundRequestEntity refundRequestEntity){
		List<RefundRequestEntity> refRequestEntity = null;
		try{
			//分页查询
			if(refundRequestEntity.getFlg().equals("1")){
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
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		ResBodyData res = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(refRequestEntity));
		return res;
	}
}
