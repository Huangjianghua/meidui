package com.meiduimall.service.member.api;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.model.MSMemberMobileArea;
import com.meiduimall.service.member.model.request.MemberConsumeMessageDTO;
import com.meiduimall.service.member.model.request.RequestGetMemberBasicInfo;
import com.meiduimall.service.member.service.MemberOrderService;
import com.meiduimall.service.member.service.UserInfoService;

/**
 * 会员订单相关
 * @author wujun
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class MemberOrderV1Controller{
	
	private final static Logger logger=LoggerFactory.getLogger(MemberOrderV1Controller.class);
	
	@Autowired
	private MemberOrderService memberOrderService;
	
	/**
	 * 当前商家退会员订单信息接口  http://IP:PORT/Authorized/BusinessRecedeOrder
	 */
	@PostMapping(value = "/business_recede_order")
	ResBodyData businessRecedeOrder(@Validated JSONObject json)   {
		logger.info("接收 当前商家退会员订单信息接口 数据：{}", json);
		logger.info("当前商家退会员订单信息接口  请求结果：{}", "");
		try {
		} catch (Exception e) {
			 
		}
		return null;
	} 
	
	
	 
	@PostMapping(value = "/save_order_notoken")
	ResBodyData saveOrderNotoken(@Validated JSONObject json) throws MdSysException{
		logger.info("接收到数据：memId={}, phone={}", "");
		logger.info("注册时记录会员手机对应的区域请求结果：{}", "");
		return null;
    }
 
	@GetMapping(value = "/recede_order")
	ResBodyData updateMemberArea() {
		logger.info("更新开始");
		MemberConsumeMessageDTO mmt = new MemberConsumeMessageDTO();
		Double xfc = new Double(0);
		memberOrderService.updateMemberOrder(mmt, xfc);
		logger.info("更新结果  ：{}", "");
		logger.info("更新结束");
		return null;
	}
	
}
