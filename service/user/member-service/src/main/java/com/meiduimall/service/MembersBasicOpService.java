package com.meiduimall.service;

import java.util.Map;


import com.alibaba.fastjson.JSONObject;

/**
 * 用户基本操作，登录，注册，退出，检查token
 * @author chencong
 *
 */
public interface MembersBasicOpService {

	//临时接口
	public Map<String, Object> getput(JSONObject jsonObject) throws Exception;
	public Map<String, Object> handlesignout(JSONObject jsonObject) throws Exception;
	
	public Map<String, Object> register(JSONObject jsonObject);
	public Map<String, Object> login(JSONObject jsonObject);
	public Map<String, Object> exit(JSONObject jsonObject);
	public Map<String, Object> checktoken(JSONObject jsonObject);
	public Map<String, Object> createValidateCode(JSONObject jsonObject) throws Exception;

}
