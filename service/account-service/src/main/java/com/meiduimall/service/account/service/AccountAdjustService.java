package com.meiduimall.service.account.service;

import com.meiduimall.core.ResBodyData;

/**
 * 账户调整相关接口
 * @author chencong
 *
 */
public interface AccountAdjustService {
	
	ResBodyData accountAdjustAmount(RequestAccountAdjustAmount model);
}
