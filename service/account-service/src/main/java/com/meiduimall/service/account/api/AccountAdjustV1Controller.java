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

/**
 * 账户调整相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class AccountAdjustV1Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountAdjustV1Controller.class);
	
	@Autowired
	private MSAccountDetailService mSAccountDetailService;
	
	@Autowired
	private AccountAdjustService accountAdjustService;
	
	/**账户余额调增调减*/
	@PostMapping(value = "/account_adjust_amount")
	public ResBodyData accountAdjustAmount(@RequestBody @Valid RequestAccountAdjustAmount model) {
		logger.info("收到账户余额调增调减API请求  ：{}",model.toString());
		try {
			return accountAdjustService.accountAdjustAmount(model);
		} catch (MdSysException | DaoException e) {
			throw new ApiException(ConstApiStatus.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 添加会员余额调整明细  
	 * @author: jianhua.huang  2017年5月5日 下午5:31:48
	 */
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
	
	/**
	 * 修改会员余额调整明细
	 * @author: jianhua.huang  2017年5月5日 下午5:31:57
	 */
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

	/**
	 * 会员余额审核
	 * @author: jianhua.huang  2017年5月5日 下午5:32:37
	 */
	@PostMapping(value="/examine_account_revision_detail")
	public ResBodyData  examineMSAccountRevisionDetail(@RequestBody AddOrUpdateAccountReviseDetail detail){
		logger.info("会员余额审核 API请求 :{}", detail.toString());
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
