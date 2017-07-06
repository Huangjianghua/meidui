package com.meiduimall.service.account.api;

import java.util.List;

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
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.model.MSDict;
import com.meiduimall.service.account.model.MSAccountType;
import com.meiduimall.service.account.service.MSAccountDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.meiduimall.service.account.service.AccountTypeService;

import net.sf.json.JSONObject;


@Api(value="账户类型管理相关API接口")
@RestController
@RequestMapping("/member/account_service/v1")
public class AccountTypeV1Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountTypeV1Controller.class);
	
	@Autowired
	private AccountTypeService accountTypeService;
	
	@Autowired
	private MSAccountDetailService mSAccountDetailService;
	
	@ApiOperation(value="查询财务调整相关的账户类型信息")
	@RequestMapping("/list_account_type")
	public ResBodyData getCwtzAccountTypeList() {
		List<MSAccountType> mSAccountType = null;
		try {
			mSAccountType = accountTypeService.getCwtzAccountTypeList();
		} catch (DaoException e) {
			logger.info("查询所有账户类型异常：{}", e);
			throw new ApiException(ConstApiStatus.QUERY_WALLETTYPE_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS,"成功", mSAccountType);
	}
	

	@ApiOperation(value="查询交易类型")
	@ApiImplicitParam(name = "dicparentid", value = "字典ID", required = true, dataType = "String")
	@PostMapping(value="/list_msdict")
	public ResBodyData  listMSDict(@RequestBody String dicparentid){
		List<MSDict> listMSDict= null;
		try{
			JSONObject jb= JSONObject.fromObject(dicparentid);
			listMSDict=mSAccountDetailService.listMSDict(jb.getString("dicparentid"));
		}catch(Exception e){
			logger.error("查询交易类型listMSDict错误:{}", e.getMessage());
			throw new ApiException(ConstApiStatus.SERVER_DEAL_WITH_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS, ConstApiStatus.SUCCESS_M,listMSDict);
	}

}
