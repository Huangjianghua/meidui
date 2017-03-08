package com.meiduimall.api;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.ApiStatusConst;
import com.meiduimall.constant.HttpRequstConst;
import com.meiduimall.constant.SysParaNameConst;
import com.meiduimall.util.Logger;


/**
 * 基类控制器
 * @author chencong
 *
 */
public class BaseController {

	
	/**
	 * 验证数据格式是否是JSON
	 * @param pw
	 * @param request
	 * @return
	 */
	protected boolean validateJson(HttpServletRequest request,JSONObject jsonObject)
	{
		if(!request.getContentType().contains(HttpRequstConst.MEDIATYPE_JSON))
		{
			jsonObject.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.NOT_JSON_FORMAT_REQUEST);
			jsonObject.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.NOT_JSON_FORMAT_REQUEST_C);
			return true;
		}
		return false;
	}
	

	/**
	 * 服务器错误
	 * @param jsonObject
	 * @param e
	 */
	protected String getServerError(Exception e)
	{
		JSONObject jsonObjec=new JSONObject();
		jsonObjec.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.SERVER_ERROR);
		jsonObjec.put(SysParaNameConst.RESULT_MSG,e.getMessage());
		Logger.error("服务器错误:"+e.getMessage());
		return jsonObjec.toJSONString();
	}
	
	
}
