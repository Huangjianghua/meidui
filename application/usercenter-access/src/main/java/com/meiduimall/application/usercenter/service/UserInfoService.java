package com.meiduimall.application.usercenter.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;

/**
 * 会员信息操作接口
 * @author chencong
 *
 */
public interface UserInfoService {

	/**
	 * 根据memdId查询会员基本信息
	 * @param 请求的数据
	 * @return 统一数据返回格式
	 */
	 ResBodyData getmemberbasicinfo(JSONObject reqJson);
	
}
