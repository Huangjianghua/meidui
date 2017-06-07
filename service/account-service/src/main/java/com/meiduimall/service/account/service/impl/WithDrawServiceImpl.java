package com.meiduimall.service.account.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSBankWithdrawDeposit;
import com.meiduimall.service.account.model.request.RequestBankWithdrawDepositsList;
import com.meiduimall.service.account.model.response.ResponseBankWithdrawDeposit;
import com.meiduimall.service.account.model.response.ResponseBankWithdrawDepositList;
import com.meiduimall.service.account.service.MSMembersService;
import com.meiduimall.service.account.service.WithDrawService;

/**
 * 提现相关Service接口{@link=WithDrawService}实现类
 * 
 * @author chencong
 *
 */
@Service
public class WithDrawServiceImpl implements WithDrawService {

	private final static Logger logger = LoggerFactory.getLogger(WithDrawServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private MSMembersService mSMembersService;

	// @Autowired
	// private MSAccountDetailService mSAccountDetailService;

	@Override
	public ResponseBankWithdrawDepositList getBankWithdrawDepositsList(RequestBankWithdrawDepositsList model) {
		// 检查该用户是否存在
		if (!mSMembersService.checkUserIsExistByMemId(model.getMemId())) {
			logger.error("当前用户在会员系统不存在,memId: " + model.getMemId());
			throw new ServiceException(ConstApiStatus.USER_NOT_EXIST);
		}

		// 设置默认页数
		int pageNo = 1;
		if (model.getPageNo() > 0) {
			pageNo = model.getPageNo();
		}
		int pageSize = 10;
		if (model.getPageSize() > 0) {
			pageSize = model.getPageSize();
		}
		// 设置分页
		PageHelper.startPage(pageNo, pageSize);
		// 查询数据
		Map<String, String> params = new HashMap<>();
		params.put("memId", model.getMemId());
		List<MSBankWithdrawDeposit> list = baseDao.selectList(params,
				"MSBankWithdrawDepositMapper.selectBankWithdrawDeposit");

		List<ResponseBankWithdrawDeposit> results = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (MSBankWithdrawDeposit bean : list) {
				if (bean != null) {
					ResponseBankWithdrawDeposit deposit = new ResponseBankWithdrawDeposit();
					deposit.setAccountBank(bean.getAccountBank() == null ? "" : bean.getAccountBank());
					deposit.setAccountName(bean.getAccountName() == null ? "" : bean.getAccountName());
					if (bean.getActualWithdrawAmount() != null) {// 实际提现金额
						deposit.setActualCarryCash(bean.getActualWithdrawAmount());
					} else {
						deposit.setActualCarryCash(0.00);
					}
					if (bean.getApplyWithdrawAmount() != null) {// 声请提现金额
						deposit.setApplyCarryCash(bean.getApplyWithdrawAmount());
					} else {
						deposit.setApplyCarryCash(0.00);
					}
					if (bean.getApplyDate() != null) {
						deposit.setApplyDate(DateFormatUtils.format(bean.getApplyDate(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						deposit.setApplyDate("");
					}
					if (bean.getAuditDate() != null) {
						deposit.setAuditDate(DateFormatUtils.format(bean.getAuditDate(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						deposit.setAuditDate("");
					}
					deposit.setAuditState(bean.getAuditState() == null ? "" : bean.getAuditState());
					deposit.setBankCardNo(bean.getBankCardNo() == null ? "" : bean.getBankCardNo());
					deposit.setBusinessNo(bean.getBusinessNo() == null ? "" : bean.getBusinessNo());
					deposit.setCounterFee(bean.getPoundageAmount() == null ? 0.00 : bean.getPoundageAmount());
					deposit.setRemark(bean.getRemark() == null ? "" : bean.getRemark());
					deposit.setStatus(bean.getStatus() == null ? "" : bean.getStatus());
					deposit.setMemId(model.getMemId());
					deposit.setId(bean.getId());
					if (bean.getCreateDate() != null) {// 创建时间
						deposit.setCreateDate(DateFormatUtils.format(bean.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						deposit.setCreateDate("");
					}
					deposit.setBankAccountId(bean.getAccountIdcard());
					deposit.setAuditBy(bean.getAuditBy() == null ? "" : bean.getAuditBy());
					deposit.setAccountSubBank(bean.getAccountSubBank() == null ? "" : bean.getAccountSubBank());
					deposit.setAccountProvince(bean.getAccountProvince() == null ? "" : bean.getAccountProvince());
					deposit.setAccountNo(bean.getBankCardNo() == null ? "" : bean.getBankCardNo());
					deposit.setAccountIdcard(bean.getAccountIdcard() == null ? "" : bean.getAccountIdcard());
					deposit.setAccountCity(bean.getAccountCity() == null ? "" : bean.getAccountCity());
					deposit.setAccountArea(bean.getAccountArea() == null ? "" : bean.getAccountArea());

					results.add(deposit);
				}
			}
		}

		ResponseBankWithdrawDepositList data = new ResponseBankWithdrawDepositList();
		PageInfo<MSBankWithdrawDeposit> pageInfo = new PageInfo<MSBankWithdrawDeposit>(list);
		data.setTotalPage(pageInfo.getPages());
		data.setResults(results);

		return data;
	}

	// @Override
	// public String saveBankWithdrawDeposit(RequestSaveBankWithdrawDeposit
	// model) {
	// RequestMSBankWithDrawDepostie deposit = new
	// RequestMSBankWithDrawDepostie();
	// deposit.setMemId(model.getMemId());
	// deposit.setAccountNo(model.getAccountNo());
	// deposit.setApplyCarryCash(model.getApplyCarryCash());
	// return mSAccountDetailService.saveBankWithdrawDeposit(deposit);
	// }
}