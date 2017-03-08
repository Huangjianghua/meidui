package com.meiduimall.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 推荐人和粉丝相关接口
 * @author chencong
 *
 */
public interface ShareMenAndFunsService {

		public JSONObject queryShareMan(String memId) throws Exception;
		
		public JSONObject queryFansDetailsList(String memId, String fansLv, int limit, int pageNum) throws Exception;
		
		public JSONObject queryFansNumber(String memId) throws Exception;
}
