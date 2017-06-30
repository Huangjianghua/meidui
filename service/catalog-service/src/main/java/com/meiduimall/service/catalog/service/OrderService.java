package com.meiduimall.service.catalog.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.catalog.entity.SysuserAccount;

public interface OrderService {

	/**
	 * 获取各种状态的订单数量
	 * @param sysuserAccount 用户信息
	 * @return 数据对象
	 */
	ResBodyData getEveryOrderTypeCount(SysuserAccount sysuserAccount);

}
