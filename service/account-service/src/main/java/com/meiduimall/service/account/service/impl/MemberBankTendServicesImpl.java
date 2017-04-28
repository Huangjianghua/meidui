package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.ApplicationConstant;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.model.MSBankAccount;
import com.meiduimall.service.account.model.MSBankInfo;
import com.meiduimall.service.account.service.AccountServices;
import com.meiduimall.service.account.service.BankAccountService;
import com.meiduimall.service.account.service.MemberBankTendServices;
import com.meiduimall.service.account.util.CollectionsUtil;
import com.meiduimall.service.account.util.StringUtil;

/**
 * 类名:  MemberBankTendServicesImpl<br>
 * 描述:  会员银行账户信息实现类，所有与会员银行账户信息的维护操作，都在此处定义<br>
 * 创建人: bibo.deng
 * 创建时间: 2017年3月27日
 */
@Transactional
@Component
public class MemberBankTendServicesImpl implements MemberBankTendServices {

	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private AccountServices accountServices;

	@Override
	public JSONObject addBankAccount(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");  //用户标识
		final String accountIdcard = param.getString("account_idcard");//身份证号码
		final String accountNo = param.getString("account_no");//卡号
		final String accountName = param.getString("account_name");//开户名
		final String accountBank = param.getString("account_bank");//开户行
		String accountProvince = "";
		if(param.containsKey("account_province")){
			accountProvince = param.getString("account_province");//所属省份
		}
		String accountCity = "";
		if(param.containsKey("account_city")){
			accountCity = param.getString("account_city");//所属城市
		}
		String accountArea = "";
		if(param.containsKey("account_area")){
			accountArea = param.getString("account_area");//所属地区
		}
		final String accountSubBank = param.getString("account_sub_bank");//支行名称
		final String isDefault = param.getString("is_default");//是否是默认的银行卡 0：否；1：是；
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
			/*resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		//检查银行卡是否存在
		if(bankAccountService.checkBankAccount(memId, accountNo)){
		/*	resultJson.put(SysParamsConst.STATUS_CODE, ApiStatusConst.BANK_FOUND_ERROR);
			resultJson.put(SysParamsConst.RESULT_MSG, ApiStatusConst.BANK_FOUND_ERROR_C);*/
			return resultJson;
		}
		
		MSBankAccount dto = new MSBankAccount();
		dto.setMemId(memId);
		dto.setAccountIdcard(accountIdcard);
		dto.setAccountNo(accountNo);
		dto.setAccountName(accountName);
		dto.setAccountBank(accountBank);
		dto.setAccountProvince(accountProvince);
		dto.setAccountCity(accountCity);
		dto.setAccountArea(accountArea);
		dto.setAccountSubBank(accountSubBank);
		dto.setIsDefault(isDefault);
		String id = bankAccountService.addBankAccount(dto);
		if(StringUtil.isEmptyByString(id)){
		/*	resultJson.put(SysParamsConst.STATUS_CODE, ApiStatusConst.BANK_ADD_ERROR);
			resultJson.put(SysParamsConst.RESULT_MSG, ApiStatusConst.BANK_ADD_ERROR_C);*/
			return resultJson;
		}
		
		return resultJson;
	}

	@Override
	public JSONObject changeBankAccount(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
	/*	resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");    //用户标识
		final String changeType = param.getString("change_type");//变更类型
		final String oldAccountNo = param.getString("old_account_no");//原银行卡号
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
			/*resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		//检查银行卡是否存在
		if(!bankAccountService.checkBankAccount(memId, oldAccountNo)){
		/*	resultJson.put(SysParamsConst.STATUS_CODE, ApiStatusConst.BANK_NOTFOUND_ERROR);
			resultJson.put(SysParamsConst.RESULT_MSG, ApiStatusConst.BANK_NOTFOUND_ERROR_C);*/
			return resultJson;
		}
		boolean updateBoolean = false;
		if(ApplicationConstant.CHANGE_TYPE_UPDATE.equalsIgnoreCase(changeType)){
			Map<String, String> updateMap = new HashMap<>();
			String[] keys = {"account_idcard","account_no", "account_name", "account_bank", "account_province",
					"account_city", "account_area", "account_sub_bank","is_default","remark" };
			updateMap = CollectionsUtil.convertJSONToMap(keys, param);
			//修改
			updateBoolean = bankAccountService.updateBankAccount(memId, oldAccountNo, updateMap);
		}else if(ApplicationConstant.CHANGE_TYPE_DELETE.equalsIgnoreCase(changeType)){
			//删除
			updateBoolean = bankAccountService.deleteBankAccount(memId, oldAccountNo);
		}
		if(!updateBoolean){
		/*	resultJson.put(SysParamsConst.STATUS_CODE, ApiStatusConst.BANK_CHANGE_ERROR);
			resultJson.put(SysParamsConst.RESULT_MSG, ApiStatusConst.BANK_CHANGE_ERROR_C);*/
			return resultJson;
		}
		return resultJson;
	}

	@Override
	public JSONObject getBanks(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");  //用户标识
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
			/*resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		
		List<MSBankInfo> bankInfoList = bankAccountService.getBankInfoList();
		if(bankInfoList != null){
			resultJson.put(SysParamsConst.RESULT, JSONArray.toJSON(bankInfoList).toString());
		}
		return resultJson;
	}

	@Override
	public JSONObject getMemberBankInfo(JSONObject param) throws Exception {
		final JSONObject resultJson = new JSONObject();
		/*resultJson.put(SysParamsConst.STATUS_CODE, SysConstant.ZERO);
		resultJson.put(SysParamsConst.RESULT_MSG, SysConstant.SUCCESS);*/
		
		final String userId = param.getString("user_id");  //用户标识
		
		//检查会员
		String memId = accountServices.getMemIdByUserId(userId);
		if(StringUtil.isEmptyByString(memId)){
		/*	resultJson.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
			resultJson.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);*/
			return resultJson;
		}
		
		List<MSBankAccount> bankAccountList = bankAccountService.getBankAccountList(memId);
		if(bankAccountList != null){
			resultJson.put(SysParamsConst.RESULT, JSONArray.toJSON(bankAccountList).toString());
		}
		return resultJson;
	}
	
}
