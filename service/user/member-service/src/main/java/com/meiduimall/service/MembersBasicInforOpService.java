package com.meiduimall.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户基本信息操作接口
 * @author chencong
 *
 */
public interface MembersBasicInforOpService {

	 Map<String, Object> getmemberbasicinfo(JSONObject jsonObject);
	 
	 public JSONObject updateMemberPhone(String token, String newPhone, String code, String password) throws Exception;
	 
	 public JSONObject getMemberInfoByPhone(String phone) throws Exception;
	 
	 public JSONObject saveMemberBasicInfo(String token, String mem_sex, String mem_birthday, String mem_address_area, String mem_address, String mem_pic, String nick_name) throws Exception;
}
