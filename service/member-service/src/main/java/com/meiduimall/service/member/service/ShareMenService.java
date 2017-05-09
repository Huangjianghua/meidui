package com.meiduimall.service.member.service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.model.MSMembersGet;

/**
 * 推荐人相关
 * @author chencong
 *
 */
public interface ShareMenService {

	/**
	 * 校验推荐人
	 * @param share_man 推荐人user_id
	 * @return 推荐人会员信息
	 * @throws MdSysException 
	 */
	public  MSMembersGet checkShareMan(String share_man) throws MdSysException;
	
	public JSONObject queryShareMan(String memId) throws Exception;
}
