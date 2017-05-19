package com.meiduimall.service.account.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.model.MSWalletType;
import com.meiduimall.service.account.service.MSWalletTypeService;

/**
 * 钱包类型
 * @author jun.wu@meiduimall.com
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class WalletTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(WalletTypeController.class);
	
	@Autowired
	private MSWalletTypeService mSWalletTypeService;
	
	 
	/**
	 * 查询所有钱包类型
	 * @return
	 */
	@PostMapping("/listWalletType")
	public ResBodyData listWalletType() {
		List<MSWalletType> listWalletTypeInfo = null;
		try {
			 listWalletTypeInfo = mSWalletTypeService.listWalletType();
		} catch (DaoException e) {
			logger.info("查询所有钱包类型异常：{}", e);
			throw new ApiException(ApiStatusConst.QUERY_WALLETTYPE_EXCEPTION);
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,"成功", listWalletTypeInfo);
	}

}
