package com.meiduimall.service.member.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;

/**
 * 会员信息操作接口
 * 
 * @author chencong
 *
 */
public interface UserInfoService {

	/**
	 * 根据memId获取会员基本信息
	 * @param memId     会员ID
	 * @return 统一数据返回格式
	 * @throws MdSysException 
	 */
	 ResBodyData getBasicInfoByMemId(String memId) throws MdSysException;
	

	/**
	 * 根据memId获取user_id（手机号或登录名）
	 * 
	 * @param memId
	 *            会员ID
	 * @return user_id（手机号或登录名）
	 * @throws MdSysException
	 */
	String getUserIdByMemId(String memId) throws MdSysException;

	/**
	 * 更新当前会员基本总额
	 * 
	 * @param memId
	 *            会员ID
	 * @param currentPoint
	 *            当前积分
	 * @param addPoint
	 *            更新的积分
	 * @throws MdSysException
	 */
	void updateCurrentPointByMemId(String memId, String currentPoint, String addPoint) throws MdSysException;

	public JSONObject getMemberInfoByPhone(String phone) throws Exception;

	public JSONObject saveMemberBasicInfo(String token, String mem_sex, String mem_birthday, String mem_address_area,
			String mem_address, String mem_pic, String nick_name) throws Exception;

	/**
	 * 注册时记录会员手机对应的区域
	 * 
	 * @param memId
	 *            会员id
	 * @param phone
	 *            手机号
	 * @return ResBodyData
	 * @throws MdSysException
	 * @author wujun
	 */
	public ResBodyData recordArea(String memId, String phone) throws MdSysException;

	/**
	 * 
	 * @return
	 * @throws MdSysException
	 */
	public ResBodyData updateMemberArea();

	/**
	 * 根据会员memId获取会员简单的信息
	 * @param memId 会员ID
	 * @return 数据对象
	 */
	ResBodyData getSimpleInfoByMemId(String memId);
}