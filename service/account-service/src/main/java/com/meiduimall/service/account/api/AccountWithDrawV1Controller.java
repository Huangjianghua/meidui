package com.meiduimall.service.account.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.request.RequestBankWithdrawDepositsList;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostie;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostieFree;
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.service.WithDrawService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 提现相关接口
 * 
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class AccountWithDrawV1Controller {

	private static final Logger logger = LoggerFactory.getLogger(AccountWithDrawV1Controller.class);

	@Autowired
	private BankWithdrawDepositService bankWithdrawDepositService;

	@Autowired
	private MSAccountDetailService mSAccountDetailService;

	@Autowired
	private WithDrawService withDrawService;
	
	private static Lock lock=new ReentrantLock(); 

	/**
	 * 描述：提现记录查询接口实现
	 * 
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:31:38
	 */
	@ApiOperation(value="查询提现记录", notes="查询提现记录")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "mSAccountDetailCondition", value = "提现查询实体", required = true, dataType = "MSAccountDetailCondition"),})
	@PostMapping(value = "/list_withdraw_condition")
	public ResBodyData listWithDrawCondition(@RequestBody MSAccountDetailCondition mSAccountDetailCondition) {
		List<MSBankWithdrawDeposit> listMSBankWithdrawDeposit = null;
		try {
			// 分页查询
			if (mSAccountDetailCondition.getFlg().equals(Constants.CONSTANT_STR_ONE)) {
				// 分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), mSAccountDetailCondition.getPageSize());
				PageHelper.orderBy("apply_date DESC");
			} else {
				// 不分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("apply_date DESC");
			}
			listMSBankWithdrawDeposit = bankWithdrawDepositService.getBankWithDrawConditon(mSAccountDetailCondition);
		} catch (Exception e) {
			logger.error("查询提现记录列表Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,
				new PageInfo<>(listMSBankWithdrawDeposit));
	}

	/**
	 * 余额提现申请
	 * 
	 * @param deposit
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:33:20
	 */
	@PostMapping(value = "/save_withdraw")
	public ResBodyData saveBankWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit) {
		// step1 检查参数
		this.checkSaveBankWithDrawParam(deposit);
		lock.lock();
		try {
			//stpe2 执行提现申请
			mSAccountDetailService.saveBankWithdrawDeposit(deposit);
		} catch (MdBizException e) {
			logger.error("余额提现申请操作Controller异常:{}", e.getMessage());
			throw new ApiException(e.getCode(), e.getMessage());
		}finally {
			lock.unlock();
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}

	/**
	 * 检查提现参数
	 * 
	 * @param deposit
	 * @throws ApiException
	 * @author: jianhua.huang 2017年5月2日 上午10:37:09
	 */
	private void checkSaveBankWithDrawParam(RequestMSBankWithDrawDepostie deposit) throws ApiException {
		if (deposit == null) {
			throw new ApiException(ConstApiStatus.PARAMETER_IS_NULL);
		} else if (StringUtils.isBlank(deposit.getMemId())) {
			throw new ApiException(ConstApiStatus.PARAMETER_MEMID_IS_NULL);
		} else if (StringUtils.isBlank(deposit.getAccountNo())) {
			throw new ApiException(ConstApiStatus.PARAMETER_ACCOUNTNO_IS_NULL);
		} else if (StringUtils.isBlank(deposit.getApplyCarryCash())) {
			throw new ApiException(ConstApiStatus.PARAMETER_APPLYCARRYCASH_IS_NULL);
		}
	}

	/**
	 * 提现结算
	 * 
	 * @param deposit
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:33:13
	 */
	@PostMapping(value = "/settlement_withdraw")
	public ResBodyData settlementWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit) {
		try {
			mSAccountDetailService.settlementWithDraw(deposit);
		} catch (MdBizException e) {
			logger.error("提现结算操作Controller异常:{}", e.getMessage());
			throw new ApiException(e.getCode(), e.getMessage());
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}

	/**
	 * 修改提现记录(审核同意)
	 * 
	 * @param deposit
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:32:47
	 */
	@PostMapping(value = "/update_withdraw")
	public ResBodyData updateWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit) {
		try {
			mSAccountDetailService.updateWithDraw(deposit);
		} catch (MdBizException e) {
			throw new ApiException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("审核同意操作修改提现记录Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}

	/**
	 * 修改提现记录(审核驳回)
	 * 
	 * @param deposit
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:32:55
	 */
	@PostMapping(value = "/reject_withdraw")
	public ResBodyData rejectWithDraw(@RequestBody RequestMSBankWithDrawDepostie deposit) {
		try {
			mSAccountDetailService.rejectWithDraw(deposit);
		} catch (MdBizException e) {
			throw new ApiException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("审核驳回操作修改提现记录Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
	}

	/**
	 * 查看提现明细
	 * 
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:33:05
	 */
	@PostMapping(value = "/query_withdraw_detail")
	public ResBodyData queryWithDrawDetail(@RequestBody MSAccountDetailCondition mSAccountDetailCondition) {
		ResBodyData resultData = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
		try {
			MSBankWithdrawDeposit deposit = mSAccountDetailService
					.queryMSBankWithdrawDepositDetail(mSAccountDetailCondition.getId());
			resultData.setData(deposit);
		} catch (MdBizException e) {
			throw new ApiException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("查看提现明细Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return resultData;
	}

	/**
	 * 提现申请查询接口--该接口不需要做旧版兼容
	 * 
	 * @param memId
	 *            会员ID
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页数量
	 * @author yangchangfu
	 * @return 结果数据
	 */
	@RequestMapping(value = "/getBankWithdrawDepositsForApp")
	public ResBodyData getBankWithdrawDepositsForApp(@Validated RequestBankWithdrawDepositsList model) {
		ResBodyData result = new ResBodyData();
		result.setData(withDrawService.getBankWithdrawDepositsList(model));
		result.setStatus(ConstApiStatus.SUCCESS);
		result.setMsg(ConstApiStatus.SUCCESS_C);
		return result;
	}

	/**
	 * 查询提现手续费
	 * 
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:33:05
	 */
	@GetMapping(value = "/get_withdraw_poundage")
	public ResBodyData getWithDrawFreeForApp(@Valid RequestMSBankWithDrawDepostieFree depostie) {
		ResBodyData resultData = new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M);
		try {
			Double free=withDrawService.getWithDrawFree(depostie);
			Map<String, Object> returnMap=new HashMap<>();
			returnMap.put("withdraw_poundage", free);
			resultData.setData(returnMap);
		} catch (MdBizException e) {
			throw new ApiException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("查看提现手续费Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return resultData;
	}
	
//	/**
//	 * 账户余额提现申请接口--该接口给旧会员系统使用(但是旧会员系统的接口没人用？)
//	 * 
//	 * @param model 提现相关信息(这里只需要银行卡号、会员memId、提现金额)
//	 * @return 结果数据
//	 * @author yangchangfu
//	 */
//	@RequestMapping(value = "/saveBankWithdrawDeposit")
//	public ResBodyData saveBankWithdrawDeposit(@Validated RequestSaveBankWithdrawDeposit model) {
//		String businessNo = withDrawService.saveBankWithdrawDeposit(model);
//		ResBodyData result = new ResBodyData();
//		result.setStatus(ConstApiStatus.SUCCESS);
//		result.setMsg(ConstApiStatus.SUCCESS_C);
//		ObjectNode objectNode = JsonUtils.getInstance().createObjectNode().put("businessNo", businessNo);
//		result.setData(objectNode);
//		return result;
//	}
}
