package com.meiduimall.service.member.service;


import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.member.model.request.MemberConsumeMessageDTO;

/**
 * 会员订单相关
 * @author wujun
 *
 */
public interface MemberOrderService {

	
	ResBodyData updateMemberOrder(MemberConsumeMessageDTO mmt, Double xfc);

	
 
}