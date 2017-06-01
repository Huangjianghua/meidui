package com.meiduimall.service.account.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.request.RequestMSBankWithDrawDepostie;
import com.meiduimall.service.account.model.response.BankWithdrawDepositsResult;
import com.meiduimall.service.account.model.response.OldBankWithdrawDepositsResult;
import com.meiduimall.service.account.service.BankWithdrawDepositService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.service.WithDrawService;

/**
 * 提现相关接口
 * 
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class WithDrawV1Controller {

	private static final Logger logger = LoggerFactory.getLogger(WithDrawV1Controller.class);

	@Autowired
	private BankWithdrawDepositService bankWithdrawDepositService;

	@Autowired
	private MSAccountDetailService mSAccountDetailService;

	@Autowired
	private WithDrawService withDrawService;

	/**
	 * 描述：提现记录查询接口实现
	 * 
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:31:38
	 */
	@PostMapping(value = "/list_withdraw_condition")
	public ResBodyData listWithDrawCondition(@RequestBody MSAccountDetailCondition mSAccountDetailCondition) {
		List<MSBankWithdrawDeposit> listMSBankWithdrawDeposit = null;
		try {
			// 分页查询
			if (mSAccountDetailCondition.getFlg().equals(Constants.CONSTANT_INT_ONE)) {
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
		try {
			// stpe2 执行提现申请
			mSAccountDetailService.saveBankWithdrawDeposit(deposit);
		} catch (MdBizException e) {
			logger.error("余额提现申请操作Controller异常:{}", e.getMessage());
			throw new ApiException(e.getCode(), e.getMessage());
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
	 * 提现申请列表---兼容旧版
	 * 
	 * @param memId
	 *            会员ID
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	@PostMapping(value = "/getBankWithdrawDepositsForApp_old")
	public String getBankWithdrawDepositsForApp_old(String memId,
			@RequestParam(value = "current_page") String currentPage,
			@RequestParam(value = "page_size") String pageSize) {

		int intPgeNo = 1;
		int intPageSize = 20;

		OldBankWithdrawDepositsResult result = new OldBankWithdrawDepositsResult();
		if (Strings.isNullOrEmpty(memId)) {
			// 请求参数错误
			result.setResultMsg(ConstApiStatus.getZhMsg(ConstApiStatus.REQUIRED_PARAM_EMPTY));
			result.setStatusCode(String.valueOf(ConstApiStatus.REQUIRED_PARAM_EMPTY));
			result.setTotalPage("0");
			return JsonUtils.beanToJson(result);
			// throw new ApiException(ConstApiStatus.REQUIRED_PARAM_EMPTY);
		}

		// ***************** 设置默认当前页和每页数量 begin *****************
		try {
			intPgeNo = Integer.parseInt(currentPage);
		} catch (NumberFormatException e) {
			logger.error("current_page 参数错误： " + e);
		}
		try {
			intPageSize = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			logger.error("page_size 参数错误： " + e);
		}
		// ***************** 设置默认当前页和每页数量 end *****************
		PageHelper.startPage(intPgeNo, intPageSize);
		List<MSBankWithdrawDeposit> list = null;
		try {
			list = withDrawService.getBankWithdrawDepositsList(memId);
		} catch (ServiceException e) {
			logger.error("用户不存在，service抛出异常： ");
			// 返回用户不存在
			result.setResultMsg(ConstApiStatus.getZhMsg(ConstApiStatus.USER_NOT_EXIST));
			result.setStatusCode(String.valueOf(ConstApiStatus.USER_NOT_EXIST));
			result.setTotalPage("0");
			return JsonUtils.beanToJson(result);
		}

		// 开始封装数据
		PageInfo<MSBankWithdrawDeposit> pageInfo = new PageInfo<MSBankWithdrawDeposit>(list);
		result.setStatusCode(String.valueOf(ConstApiStatus.SUCCESS));
		result.setResultMsg(ConstApiStatus.SUCCESS_C);
		result.setTotalPage(String.valueOf(pageInfo.getPages()));
		result.setResults(list);

		return JsonUtils.beanToJson(result);
	}

	/**
	 * 提现申请列表---新接口规范
	 * 
	 * @param memId
	 *            会员ID
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	@PostMapping(value = "/getBankWithdrawDepositsForApp")
	public ResBodyData getBankWithdrawDepositsForApp(String memId, int pageNo, int pageSize) {
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 20;
		}
		PageHelper.startPage(pageNo, pageSize);
		List<MSBankWithdrawDeposit> list = withDrawService.getBankWithdrawDepositsList(memId);
		PageInfo<MSBankWithdrawDeposit> pageInfo = new PageInfo<MSBankWithdrawDeposit>(list);
		BankWithdrawDepositsResult data = new BankWithdrawDepositsResult();
		data.setTotalPage(pageInfo.getPages());
		data.setResults(list);

		ResBodyData result = new ResBodyData();
		result.setStatus(ConstApiStatus.SUCCESS);
		result.setMsg(ConstApiStatus.SUCCESS_C);
		result.setData(data);
		return result;
	}
}
