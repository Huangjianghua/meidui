package com.meiduimall.service.member.api;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.member.constant.ConstSysParamsDefination;
import com.meiduimall.service.member.service.FunsService;
import com.meiduimall.service.member.service.ShareMenService;

/**
 * 推荐人和被推荐人相关API
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class ShareManAndFunsV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(ShareManAndFunsV1Controller.class);
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private ShareMenService smfs;
	
	@Autowired
	private FunsService funsService;
	
	/**
	 * 获取会员二级推荐人接口 http://IP:PORT/Authorized/querySecondLevelShareMem
	 * @param memId
	 * @throws Exception
	 */
	@RequestMapping(value = "/get_twolevel_sharemen",method=RequestMethod.GET)
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
				json.put(ConstSysParamsDefination.STATUS, "9999");
				json.put(ConstSysParamsDefination.MSG, "服务器错误!");
				logger.error("服务器错误:{}", e.getMessage());
			} catch (IOException e1) {
				logger.error("服务器错误:{}", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	
	/**
	 * 粉丝明细接口 http://IP:PORT/ AuthorizationMembers/fansDetailList
	 * @param memId
	 * @param levelNum
	 * @param limit
	 * @param pageNo
	 * @throws Exception
	 */
	@RequestMapping(value = "/funs_detail_list",method=RequestMethod.GET)
	public void funsdetaillist(String memId, String levelNum, int limit, int pageNo) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = funsService.queryFansDetailsList(memId, levelNum, limit, pageNo);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(ConstSysParamsDefination.STATUS, "9999");
				json.put(ConstSysParamsDefination.MSG, "服务器错误!");
			} catch (IOException e1) {
			}
		}
		out.print(json.toString());
	}
	
	/**
	 * 粉丝数量接口 http://IP:PORT/AuthorizationMembers/funscountbylevel
	 * @param memId
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/funs_count_group_level",method=RequestMethod.GET)
	public void funscountbylevel(String memId) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = funsService.queryFansNumber(memId);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(ConstSysParamsDefination.STATUS, "9999");
				json.put(ConstSysParamsDefination.MSG, "服务器错误!");
			} catch (IOException e1) {
			}
		}
		out.print(json.toString());
	}
	
	
}