package com.meiduimall.service.account.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdBizException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.MSAccountDetail;
import com.meiduimall.service.account.model.MSAccountDetailCondition;
import com.meiduimall.service.account.model.MSAccountDetailGet;
import com.meiduimall.service.account.model.MSAccountList;
import com.meiduimall.service.account.model.request.RequestMSAccountList;
import com.meiduimall.service.account.model.response.ResponseAccountBalance;
import com.meiduimall.service.account.model.response.ResponseOldAccountBalance;
import com.meiduimall.service.account.service.AccountReportService;
import com.meiduimall.service.account.service.MSAccountDetailService;
import com.meiduimall.service.account.service.MSMembersService;

/**
 * 账户信息查询相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class AccountQueryV1Controller {

	private final static Logger logger = LoggerFactory.getLogger(GciV1Controller.class);

	@Autowired
	private MSAccountDetailService mSAccountDetailService;
	
	@Autowired
	private MSMembersService mSMembersService;
	
	@Autowired
	private AccountReportService accountReportService;
	
	/**
	 * 查询当前会员可用余额
	 * @author chencong
	 */
	@GetMapping(value = "/get_available_balance")
	public ResBodyData getAvailableBalance(@RequestParam String memId ) {
		ResBodyData resBodyData=new ResBodyData(Constants.CONSTANT_INT_ZERO,null);
		Double availableBalance=accountReportService.getAvailableBalance(memId);
		ObjectNode rootNode = JsonUtils.getInstance().createObjectNode();
		rootNode.set("available_balance",new DoubleNode(availableBalance));
		resBodyData.setData(rootNode);
		return resBodyData;
	}

	/**
	 * 余额流水（分页）
	 * @author wujun
	 */
	@PostMapping(value = "/list_account_detail")
	public ResBodyData listMSAccountDetail(@RequestBody MSAccountDetailGet mSAccountDetail) throws Exception {
		List<MSAccountDetail> listMSAccountDetail = null;
		try {
			if (mSAccountDetail.getMemId() == null) {
				return new ResBodyData(1, "memId为空");
			} else if ("".equals(mSAccountDetail.getMemId())) {
				return new ResBodyData(1, "memId为空");
			} else {
				PageHelper.startPage(mSAccountDetail.getPageNum(), mSAccountDetail.getPageSize());
				PageHelper.orderBy("create_date DESC");
				listMSAccountDetail = mSAccountDetailService.listMSAccountDetail(mSAccountDetail);
			}
		} catch (Exception e) {
			logger.error("查询余额流水listMSAccountDetail服务器错误:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS,"成功",new PageInfo<>(listMSAccountDetail));
	}
 

	/**
	 * 描述：根据条件查询余额流水
	 * 
	 * @param mSAccountDetailCondition
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:30:32
	 */
	@PostMapping(value = "/list_account_condition")
	public ResBodyData listMSAccountCondition(@RequestBody MSAccountDetailCondition mSAccountDetailCondition) {
		List<MSAccountDetail> listMSAccountDetail = null;
		try {
			// 分页查询
			if (mSAccountDetailCondition.getFlg().equals(Constants.CONSTANT_STR_ONE)) {
				// 分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), mSAccountDetailCondition.getPageSize());
				PageHelper.orderBy("create_date DESC");
			} else {
				// 不分页
				PageHelper.startPage(mSAccountDetailCondition.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("create_date DESC");
			}
			listMSAccountDetail = mSAccountDetailService.listMSAccountCondition(mSAccountDetailCondition);
			for (int i = 0; i < listMSAccountDetail.size(); i++) {
				/* 账户明细表实体类有改动，所以注释了，但是代码逻辑还是需要的 */
				/*
				 * String phone=
				 * DESC.deyption(listMSAccountDetail.get(i).getPhone());
				 * listMSAccountDetail.get(i).setPhone(phone);
				 * 
				 * String loginName =
				 * DESC.deyption(listMSAccountDetail.get(i).getLoginName());
				 * listMSAccountDetail.get(i).setLoginName(loginName);
				 */
			}
		} catch (Exception e) {
			logger.error("根据条件查询余额流水listMSAccountCondition错误:{}", e);
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, new PageInfo<>(listMSAccountDetail));
	}

	/**
	 * 新需求 根据条件查询会员列表 （转移到账号服务）
	 * 
	 * @param msAccountListRequest
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:31:18
	 */
	@RequestMapping(value = "/list_account")
	public ResBodyData listMSAccount(@RequestBody RequestMSAccountList msAccountListRequest) {
		Page<MSAccountList> pageInfo=null;
		try{
			pageInfo=mSAccountDetailService.listMSAccount(msAccountListRequest);
		}catch(MdBizException e){
			throw new ApiException(e.getCode(),e.getMessage());
		}catch(Exception e){
			logger.error("查询会员列表Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,new PageInfo<>(pageInfo));
	}

	/**
	 * 查看会员余额调整明细
	 * 
	 * @param id
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:32:18
	 *//*
	@PostMapping(value = "/get_account_revision_detail")
	public ResBodyData getMSAccountRevisionDetail(@RequestBody String id) {
		AccountReviseDetail detail = null;
		try {
			detail = mSAccountDetailService.getMSAccountReviseDetail(id);
		} catch (MdBizException e) {
			throw new ApiException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("查看会员余额调整明细Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, detail);
	}

	*//**
	 * 查看会员余额调整明细集合 flg区别 分页
	 * 
	 * @param detailRequest
	 * @return
	 * @author: jianhua.huang 2017年5月5日 下午5:32:28
	 *//*
	@PostMapping(value = "/query_account_revision_detail_list")
	public ResBodyData queryMSAccountRevisionDetailList(@RequestBody RequestAccountReviseDetail detailRequest) {
		List<AccountReviseDetail> list = null;
		try {
			// 分页查询
			if (detailRequest.getFlg().equals(Constants.CONSTANT_STR_ONE)) {
				// 分页
				PageHelper.startPage(detailRequest.getPageNum(), detailRequest.getPageSize());
				PageHelper.orderBy("msard.created_date DESC");
			} else {
				// 不分页
				PageHelper.startPage(detailRequest.getPageNum(), 0, false, false, true);
				PageHelper.orderBy("msard.created_date DESC");
			}
			list = mSAccountDetailService.queryMSAccountReviseDetailList(detailRequest);
		} catch (MdBizException e) {
			throw new ApiException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error("查看会员余额调整列表Controller异常:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M, new PageInfo<>(list));
	}

	*/
	/**
	 * 根据会员memId，获取会员账户余额和积分余额---兼容旧版
	 * 
	 * @param memId 会员Id
	 * @return json串
	 * @author yangchangfu
	 * @throws MdSysException 
	 */
	@RequestMapping(value = "/getAccountBalanceForApp_old")
	public String getAccountBalanceForApp_old(String memId) throws MdSysException {
		ResponseOldAccountBalance result = new ResponseOldAccountBalance();
		if (Strings.isNullOrEmpty(memId)) {
			result.setStatusCode(String.valueOf(ConstApiStatus.REQUIRED_PARAM_EMPTY));
			result.setResultMsg(ConstApiStatus.getZhMsg(ConstApiStatus.REQUIRED_PARAM_EMPTY));
			return JsonUtils.beanToJson(result);
		}
		ResponseAccountBalance data = null;
		try {
			data = mSMembersService.getAccountBalance(memId);
		} catch (ServiceException e) {
			result.setStatusCode(String.valueOf(ConstApiStatus.USER_NOT_EXIST));
			result.setResultMsg(ConstApiStatus.getZhMsg(ConstApiStatus.USER_NOT_EXIST));
			return JsonUtils.beanToJson(result);
		}
		BeanUtils.copyProperties(data, result);
		result.setStatusCode(String.valueOf(ConstApiStatus.SUCCESS));
		result.setResultMsg(ConstApiStatus.SUCCESS_C);
		return JsonUtils.beanToJson(result);
	}

	/**
	 * 根据会员memId，获取会员账户余额和积分余额---按新接口规范
	 * @param memId 会员Id
	 * @return 结果数据
	 * @author yangchangfu
	 * @throws MdSysException 
	 */
	@RequestMapping(value = "/getAccountBalanceForApp")
	public ResBodyData getAccountBalanceForApp(String memId) throws MdSysException {
		if (Strings.isNullOrEmpty(memId)) {
			throw new ApiException(ConstApiStatus.REQUIRED_PARAM_EMPTY);
		}
		ResBodyData result = new ResBodyData();
		result.setData(mSMembersService.getAccountBalance(memId));
		result.setStatus(ConstApiStatus.SUCCESS);
		result.setMsg(ConstApiStatus.SUCCESS_C);
		return result;
	}
	
	/**
	 * 查询个人消费管理信息接口--该接口不需要做旧版兼容
	 * @param memId 会员Id
	 * @return 结果数据
	 * @author yangchangfu
	 */
	@RequestMapping(value = "/personalConsumptionPoints")
	public ResBodyData personalConsumptionPoints(String memId){
		return mSMembersService.personalConsumptionPoints(memId);
	}
}
