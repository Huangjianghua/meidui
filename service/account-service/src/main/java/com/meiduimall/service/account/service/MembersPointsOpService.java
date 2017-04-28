package com.meiduimall.service.account.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Title : MembersScoreOpService
 * Description : 会员积分相关业务接口
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月22日 下午5:12:36 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public interface MembersPointsOpService {
	
	public JSONObject getMemberScoreByMemId(String memId, String type) throws Exception;

}
