package com.meiduimall.api;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.api.BaseController;
import com.meiduimall.constant.SysParaNameConst;
import com.meiduimall.service.ShareMenAndFunsService;
import com.meiduimall.util.Logger;

/**
 * 推荐人和粉丝相关
 * @author chencong
 *
 */
@RestController
@RequestMapping("/sharemenandfunsinfo")
public class ShareMenAndFunsController extends BaseController{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private ShareMenAndFunsService smfs;
	
	/**
	 * 获取会员二级推荐人接口 http://IP:PORT/Authorized/querySecondLevelShareMem
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getsecondlevelsharemen",method=RequestMethod.GET)
	public void getsecondlevelsharemen(String memId) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = smfs.queryShareMan(memId);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParaNameConst.STATUS_CODE, "9999");
				json.put(SysParaNameConst.RESULT_MSG, "服务器错误!");
				Logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				Logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	
	/**
	 * 粉丝明细接口 http://IP:PORT/ AuthorizationMembers/fansDetailList
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/funsdetaillist",method=RequestMethod.GET)
	public void funsdetaillist(String memId, String levelNum, int limit, int pageNo) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = smfs.queryFansDetailsList(memId, levelNum, limit, pageNo);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParaNameConst.STATUS_CODE, "9999");
				json.put(SysParaNameConst.RESULT_MSG, "服务器错误!");
				Logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				Logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	/**
	 * 粉丝数量接口 http://IP:PORT/AuthorizationMembers/funscountbylevel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/funscountbylevel",method=RequestMethod.GET)
	public void funscountbylevel(String memId) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = smfs.queryFansNumber(memId);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParaNameConst.STATUS_CODE, "9999");
				json.put(SysParaNameConst.RESULT_MSG, "服务器错误!");
				Logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				Logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
}