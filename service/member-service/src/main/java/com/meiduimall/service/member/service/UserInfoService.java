package com.meiduimall.service.member.service;


import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 会员信息操作接口
 * @author chencong
 *
 */
public interface UserInfoService {

	/**
	 * 根据memId获取会员基本信息
	 * @param memId 会员ID
	 * @return 统一数据返回格式
	 */
	 ResBodyData getBasicInfoByMemId(String memId);
	 
	 /**
	  * 根据memId获取user_id（手机号或登录名）
	  * @param memId 会员ID
	  * @return user_id（手机号或登录名）
	  * @throws MdSysException
	  */
	 String getUserIdByMemId(String memId) throws MdSysException;
	 
	 /**
	  * 更新当前会员基本总额
	  * @param memId 会员ID
	  * @param currentPoint 当前积分
	  * @param addPoint 更新的积分
	 * @throws MdSysException 
	  */
	 void updateCurrentPointByMemId(String memId,String currentPoint,String addPoint) throws MdSysException;
	 
	 public JSONObject getMemberInfoByPhone(String phone) throws Exception;
	 
	 public JSONObject saveMemberBasicInfo(String token, String mem_sex, String mem_birthday, String mem_address_area, String mem_address, String mem_pic, String nick_name) throws Exception;
}
