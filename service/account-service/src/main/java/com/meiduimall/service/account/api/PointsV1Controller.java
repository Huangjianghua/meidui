package com.meiduimall.service.account.api;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.model.MSConsumePointsDetail;
import com.meiduimall.service.account.model.MSConsumePointsDetailGet;
import com.meiduimall.service.account.service.MDMallServices;
import com.meiduimall.service.account.service.MSConsumePointsDetailService;
import com.meiduimall.service.account.service.MembersPointsOpService;

/**
 * 用户积分相关操作
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/account_service/v1")
public class PointsV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(PointsV1Controller.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private MembersPointsOpService membersScoreOpService;
	
	@Autowired
	private  MDMallServices mdmallServices;
	
	@Autowired
	private MSConsumePointsDetailService mSConsumePointsDetailService;
	
	/**
	 * 积分 流水接口  分页  
	 * @param mSConsumePointsDetail
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/list_consume_points_detail")
	public ResBodyData listConsumePointsDetail(@RequestBody MSConsumePointsDetailGet mSConsumePointsDetail) throws Exception{
		List<MSConsumePointsDetail> listMSConsumePointsDetail = null;
		try {
			if(mSConsumePointsDetail.getMemId() == null){
				return new ResBodyData(1,"memId为空");
			}else if("".equals(mSConsumePointsDetail.getMemId())){
				return new ResBodyData(1,"memId为空");
			}else{
				PageHelper.startPage(mSConsumePointsDetail.getPageNum(), mSConsumePointsDetail.getPageSize());
				listMSConsumePointsDetail = mSConsumePointsDetailService.listMSConsumePointsDetail(mSConsumePointsDetail);
			}
		} catch (Exception e) {
			logger.error("服务器错误:%s", e.getMessage());
			return new ResBodyData(1,"服务器错误");
		}
		return new ResBodyData(ApiStatusConst.SUCCESS,"成功",new PageInfo<>(listMSConsumePointsDetail));
	}
	
	
	/**内部积分转账接口*/
	@RequestMapping(value = "/internalscoretransfer",method=RequestMethod.POST)
	String internalscoretransfer() throws Exception {
		String message = null;
		try {
			JSONObject jsonObject =null;
			logger.info("站内美兑积分转账，外部请求IP=" + jsonObject.getString("ip") + "开始");
			message = mdmallServices.meiduiIntegralTransfer(jsonObject, request);
			logger.info("站内美兑积分转账，返回参数：" + message.toString());
			request.getQueryString();		
		} catch (Exception e) {
			throw e;
		}
		return  message;
	    }
	
	/**推广收益明细*/
	/*@RequestMapping(value = "/expandprofitdetail",method=RequestMethod.GET)
	String expandprofitdetail() {
		String message = null;
		try {
			JSONObject jsonObject = HttpClientUtil.readGetStringToJsonObject(request);
			jsonObject.put("ip",HttpClientUtil.getIpAddr(request));
			Logger.info("外部请求IP=" + jsonObject.getString("ip") + "开始，请求字符串："+request.getQueryString());
			message=authorizationPointsService.getPointsDetailOfToken(jsonObject, request, response); 
		} catch (Exception e) {
			message=getServerError(e);
			Logger.info("服务器错误:"+e.getMessage());
		}
		return message;
	    }*/
	
	
	
	/**增加积分（商城API需求，可根据“附近消费积分增加接口”修改）*/
	@RequestMapping(value = "/addscore",method=RequestMethod.PUT)
	void addscore() throws Exception {
		try {
			PrintWriter pw=response.getWriter();
			request.getQueryString();		
			pw.print("hello,henry");
		} catch (Exception e) {
			throw e;
		}
	    }
	
	
	/**积分汇总接口，汇总某种类型的积分总额*/
	@RequestMapping(value = "/gathrescoresumbytype",method=RequestMethod.GET)
	public void gathrescoresumbytype(String memId, String type) throws Exception {
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = membersScoreOpService.getMemberScoreByMemId(memId, type);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParamsConst.STATUS_CODE, "9999");
				json.put(SysParamsConst.RESULT_MSG, "服务器错误!");
				logger.error("服务器错误:%s", e.getMessage());
			} catch (IOException e1) {
				logger.error("服务器错误:%s", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	
	
}
