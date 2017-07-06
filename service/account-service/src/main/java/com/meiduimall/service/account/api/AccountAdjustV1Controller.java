package com.meiduimall.service.account.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.AddOrUpdateAccountReviseDetail;
import com.meiduimall.service.account.model.request.RequestAccountAdjustAmount;
import com.meiduimall.service.account.service.AccountAdjustService;
import com.meiduimall.service.account.service.MSAccountDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Api(value="账户变动相关API接口")
@RestController
@RequestMapping("/member/account_service/v1")
public class AccountAdjustV1Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountAdjustV1Controller.class);
	
	@Autowired
	private MSAccountDetailService mSAccountDetailService;
	
	@Autowired
	private AccountAdjustService accountAdjustService;
	
	@ApiOperation(value="当前会员账户余额调增调减")
	@ApiImplicitParam(name = "model", value = "账户余额调增调减API请求实体", required = true, dataType = "RequestAccountAdjustAmount")
	@PostMapping(value = "/account_adjust_amount")
	public ResBodyData accountAdjustAmount(@RequestBody @Valid RequestAccountAdjustAmount model) {
		logger.info("收到账户余额调增调减API请求  ：{}",model.toString());
		try {
			return accountAdjustService.accountAdjustAmount(model);
		} catch (MdSysException | DaoException e) {
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
	

	@ApiOperation(value="添加会员余额调整明细")
	@ApiImplicitParam(name = "detail", value = "添加会员余额调整明细API请求实体", required = true, dataType = "AddOrUpdateAccountReviseDetail")
	@PostMapping(value="/add_account_revision_detail")
	public ResBodyData  addMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		try{
			logger.info("添加会员余额调整明细 API请求 :{}", detail);
			mSAccountDetailService.addMSAccountReviseDetail(detail);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("添加会员余额调整明细Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}
	

	@ApiOperation(value="修改会员余额调整明细")
	@ApiImplicitParam(name = "detail", value = "修改会员余额调整明细API请求实体", required = true, dataType = "AddOrUpdateAccountReviseDetail")
	@PostMapping(value="/update_account_revision_detail")
	public ResBodyData  updateMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		logger.info("修改会员余额调整明细 API请求 :{}", detail);
		try{
		  mSAccountDetailService.updateMSAccountReviseDetail(detail);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("修改会员余额调整明细Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}


	@ApiOperation(value="会员余额审核")
	@ApiImplicitParam(name = "detail", value = "会员余额审核API请求实体", required = true, dataType = "AddOrUpdateAccountReviseDetail")
	@PostMapping(value="/examine_account_revision_detail")
	public ResBodyData  examineMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		logger.info("会员余额审核 API请求 :{}", detail);
		ResBodyData resBodyData=null;
		try {
			resBodyData=mSAccountDetailService.examineMSAccountReviseDetail(detail);
		} catch (MdBizException e) {
			logger.error("会员余额审核Controller异常:{}", e.getMessage());
			throw new ApiException(e.getCode(), e.getMessage());
		}
		return resBodyData;
	}
}
