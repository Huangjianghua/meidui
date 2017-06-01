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
import com.meiduimall.service.account.service.AccountTypeService;

import net.sf.json.JSONObject;

/**
 * 账户类型管理相关接口
 * @author jun.wu@meiduimall.com
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class AccountPropertyV1Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountPropertyV1Controller.class);
	
	@Autowired
	private AccountTypeService accountTypeService;
	
	@Autowired
	private MSAccountDetailService mSAccountDetailService;
	
	/**查询财务调整相关的账户类型信息*/
	@RequestMapping("/list_account_type")
	public ResBodyData getAccountTypeList() {
		List<MSAccountType> mSAccountType = null;
		try {
			mSAccountType = accountTypeService.getAccountTypeList();
		} catch (DaoException e) {
			logger.info("查询所有账户类型异常：{}", e);
			throw new ApiException(ConstApiStatus.QUERY_WALLETTYPE_EXCEPTION);
		}
		return new ResBodyData(ConstApiStatus.SUCCESS,"成功", mSAccountType);
	}
	
	/**
	 * 描述：查询交易类型
	 * @param dicparentid
	 * @return
	 * @author: jianhua.huang  2017年5月5日 下午5:30:43
	 */
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
