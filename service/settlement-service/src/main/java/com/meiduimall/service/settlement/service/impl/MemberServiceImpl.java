package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.pagehelper.StringUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.util.JsonUtils;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.common.ShareProfitUtil;
import com.meiduimall.service.settlement.context.MemberSystemDataContext;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.CreateBillLog;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.SmsReqDTO;
import com.meiduimall.service.settlement.service.MemberService;
import com.meiduimall.service.settlement.service.O2oCallbackService;
import com.meiduimall.service.settlement.service.OrderStatusService;
import com.meiduimall.service.settlement.service.ShareProfitLogService;
import com.meiduimall.service.settlement.service.SmsService;
import com.meiduimall.service.settlement.util.ConnectionUrlUtil;
import com.meiduimall.service.settlement.util.DateUtil;

@Service
public class MemberServiceImpl implements MemberService {

	protected final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private ShareProfitLogService shareProfitLogService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private O2oCallbackService o2oCallbackService;
	
	@Autowired
	private SmsService smsService;

	
	@Override
	public Boolean addConsumePoints(String phone, String credit,String source,String orderId){
		
		if("0".equals(credit)){
			log.info("积分为0,userId:{},忽略该积分的发送。",phone);
			return true;
		}
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("user_id", phone);
		hashMap.put("consume_points_count",credit);
		hashMap.put("order_source",source);
		hashMap.put("url","Authorized/addConsumePoints");
		hashMap.put("order_id",orderId);
		String resultJsonStr = ConnectionUrlUtil.httpRequest(ShareProfitUtil.belongInfoUrl(hashMap), ShareProfitUtil.REQUEST_METHOD_POST, null);
		ResBodyData resultJson = JsonUtils.jsonToBean(resultJsonStr, ResBodyData.class);
		// 判断返回是否成功,如果不成功则不理会
		if (resultJson.getStatus()==0) {
			 return true;
		} else {
			log.error("errcode:" + resultJson.getStatus() + ";errmsg:" + resultJson.getMsg()+ ";userId:"+phone);
			return false;
		}
		
	}
	
	public Boolean updateAmout2MemberSystem(MemberSystemDataContext ctx) {
		
		String userId=ctx.getUserId();
		Boolean isUpdated=Boolean.FALSE;
 		if(!Strings.isNullOrEmpty(userId)){

			String resultJsonStr = ConnectionUrlUtil.httpRequest(ShareProfitUtil.buildMemberSystemAmoutUrl(ctx), ShareProfitUtil.REQUEST_METHOD_POST, null);
			
			ResBodyData resultJson= JsonUtils.jsonToBean(resultJsonStr, ResBodyData.class);
			
			String statusCode = resultJson.getStatus() == null ? "" : resultJson.getStatus().toString();
			// 判断返回是否成功,如果不成功则不理会
			if ("0".equals(statusCode)) {
				isUpdated=Boolean.TRUE;
			} else {
				log.error("errcode:" + resultJson.getStatus() + ";errmsg:" + resultJson.getMsg() + ";userId:"+userId);
			}
		}
		
		return isUpdated;

	}

	@Override
	public List<String> sendScore(EcmMzfShareProfit shareProfit){
		
		final List<String> errors = new ArrayList<>();
		
		log.info("Update Score Start:Current Date:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " for orderSn:"+shareProfit.getOrderSn());

		if (StringUtil.isEmpty(shareProfit.getPhone()) || StringUtil.isEmpty(shareProfit.getSellerPhone())) {
			log.warn("会员/商家手机号为空!略过该条更新数据!orderSn:"+shareProfit.getOrderSn());
			errors.add("会员/商家手机号不能为空!");
			//略过该条数据，因为没法 更新积分。
			return errors;
		}
		log.info("开始更新订单分润的各受益相关人所获积分到会员系统,orderSn:"+shareProfit.getOrderSn());
		boolean updateMemberScore = this.addConsumePoints(shareProfit.getPhone(), shareProfit.getPoint().toString(), ShareProfitConstants.DATA_SOURCE_O2O, shareProfit.getOrderSn());
		if(updateMemberScore){
			log.info("更新订单分润用户所获积分到会员系统成功,用户手机号:"+shareProfit.getPhone());
		}else{
			log.info("更新订单分润用户所获积分到会员系统失败,用户手机号:"+shareProfit.getPhone());
			errors.add("更新订单分润用户所获积分到会员系统失败,用户手机号:"+shareProfit.getPhone());
		}
		
		boolean updateSellerScore = this.addConsumePoints(shareProfit.getSellerPhone(), shareProfit.getSellerPoint().toString(), ShareProfitConstants.DATA_SOURCE_O2O, shareProfit.getOrderSn());
		if(updateSellerScore){
			log.info("更新订单分润商家所获积分到会员系统成功,商家手机号:"+shareProfit.getSellerPhone());
		}else{
			log.info("更新订单分润商家所获积分到会员系统失败,商家手机号:"+shareProfit.getSellerPhone());
			errors.add("更新订单分润商家所获积分到会员系统失败,商家手机号:"+shareProfit.getSellerPhone());
		}
		
		boolean updateOneScore = true;
		boolean updateTwoScore = true;
		if (!StringUtil.isEmpty(shareProfit.getBelongOnePhone())) {
			
			updateOneScore = this.addConsumePoints(shareProfit.getBelongOnePhone(), shareProfit.getBelongOnePoint().toString(), ShareProfitConstants.DATA_SOURCE_O2O, shareProfit.getOrderSn());
			
			if(updateOneScore){
				log.info("更新订单分润一级推荐人所获积分到会员系统成功,一级推荐人手机号:"+shareProfit.getBelongOnePhone());
			}else{
				log.info("更新订单分润一级推荐人所获积分到会员系统失败,一级推荐人手机号:"+shareProfit.getBelongOnePhone());
				errors.add("更新订单分润一级推荐人所获积分到会员系统失败,一级推荐人手机号:"+shareProfit.getBelongOnePhone());
			}
		
		}
		if (null != shareProfit.getBelongTwoPhone() && !"".equals(shareProfit.getBelongTwoPhone())) {
			updateTwoScore = this.addConsumePoints(shareProfit.getBelongTwoPhone(), shareProfit.getBelongTwoPoint().toString(), ShareProfitConstants.DATA_SOURCE_O2O, shareProfit.getOrderSn());
		
			if(updateTwoScore){
				log.info("更新订单分润二级推荐人所获积分到会员系统成功,二级推荐人手机号:"+shareProfit.getBelongTwoPhone());
			}else{
				log.info("更新订单分润二级推荐人所获积分到会员系统失败,二级推荐人手机号:"+shareProfit.getBelongTwoPhone());
				errors.add("更新订单分润二级推荐人所获积分到会员系统失败,二级推荐人手机号:"+shareProfit.getBelongTwoPhone());
			}
		
		}

		if (updateMemberScore && updateSellerScore && updateOneScore && updateTwoScore) {
			log.info("分润订单更积分成功!orderSn:"+shareProfit.getOrderSn());
		} else {
			log.error("updateScore 订单分润更新积分到会员系统失败!!orderSn:"+shareProfit.getOrderSn());
			log.error("OrderServiceImpl-->updateScore-->更新积分失败!商家/会员/推荐人积分更新失败!");
		}
			
		return errors;
	}


	@Async
	@Override
	public void updateReferrerCash() {
		
		final List<String> orderSnList=new ArrayList<>();
		final List<String> orderSnList4Err=new ArrayList<>();
		log.info("Update Cash Task:Current Date:" + DateUtil.getCurrentTime());
		
		List<EcmMzfShareProfit> spOrders = baseMapper.selectList(null, "ShareProfitMapper.getUpdateCashList");
		for (EcmMzfShareProfit shareProfit : spOrders) {
			if (!Strings.isNullOrEmpty(shareProfit.getBelongOnePhone())) {

				BigDecimal amount=shareProfit.getFirstReferrerCash();
				
				//调账的方向,“IN”：调增，“OUT”：调减
				String direction=amount.compareTo(BigDecimal.ZERO)>0?"IN":"OUT"; //amount>BigDecimal.ZERO时返回1,-1是小于,0是等于
				
				if("OUT".equals(direction)){
					amount=amount.abs();
				}

				MemberSystemDataContext ctx=new MemberSystemDataContext();
				ctx.setUserId(shareProfit.getBelongOnePhone());
				ctx.setSource("O2O");
				ctx.setOrderSn(shareProfit.getOrderSn());
				ctx.setAmount(amount);
				ctx.setDirection(direction);
				ctx.setTradeTime(shareProfit.getOrderDate());
				ctx.setTradeType("FJJL");
				ctx.setRemark(MemberSystemDataContext.REMARK_FOR_FJJL);

				boolean cashUpdated = this.updateAmout2MemberSystem(ctx);
				
				if (cashUpdated) {
					log.info("更新推荐人金额到会员系统成功!userId:{} for orderSn:{}",ctx.getUserId(),shareProfit.getOrderSn());
					orderSnList.add(shareProfit.getOrderSn());
				}else{
					log.error("updateAmout2MemberSystem(ctx) in MemberServiceImpl got error:更新一级推荐人1%金额到会员系统失败!userId:{},orderSn:{}",ctx.getUserId(),shareProfit.getOrderSn());
					orderSnList4Err.add(shareProfit.getOrderSn());
				}
	
			}
		}
		
		if(orderSnList!=null && !orderSnList.isEmpty()){
			boolean isCashStatusUpdated=true;
			try {
				isCashStatusUpdated=orderStatusService.batchUpdCashStatus(orderSnList);
				if(!isCashStatusUpdated){
					log.error("orderStatusService.batchUpdCashStatus() in updateReferrerCash() 失败!order size:{},orderSnList:{}",orderSnList.size(),Joiner.on(Constants.SEPARATOR_COMMA).skipNulls().join(orderSnList));
				}
			} catch (Exception e) {
				isCashStatusUpdated=false;
				log.error("orderStatusService.batchUpdCashStatus() in updateReferrerCash() 出现异常!order size:"+orderSnList.size()+",orderSnList:{},error:{}",Joiner.on(Constants.SEPARATOR_COMMA).skipNulls().join(orderSnList),e.getMessage());
			}
			if(!isCashStatusUpdated){
				String params="一级推荐人1%现金成功送出,但送现金成功状态更新到表 ecm_mzf_order_status表失败,需要手动更新,订单数:"+orderSnList.size();
				SmsReqDTO smsReqDTO = new SmsReqDTO(ShareProfitUtil.authorizedMap.get(ShareProfitUtil.SMS_PHONES),
						ShareProfitUtil.TEMPLATE_ID_O2O_1009,params,"");

				try {
					boolean flag = smsService.sendMessage(smsReqDTO);
					if(flag){
						log.info("一级推荐人1%现金成功送出,但送现金成功状态更新到表 ecm_mzf_order_status表失败,需要手动更新,发送短信通知成功,订单数:{}",orderSnList.size());
					}else{
						log.info("一级推荐人1%现金成功送出,但送现金成功状态更新到表 ecm_mzf_order_status表失败,需要手动更新,订单数:{}",orderSnList.size());
					}
				} catch (Exception e1) {
					log.error("一级推荐人1%现金成功送出,但送现金成功状态更新到表 ecm_mzf_order_status表失败,需要手动更新,订单数:{}",orderSnList.size(),e1);
				}
			
				
			}
			
		}
		
		if(orderSnList4Err!=null && !orderSnList4Err.isEmpty()){
			try {
				log.error("updateReferrerCash() in MemberServiceImpl got error:更新推荐人1%金额到会员系统失败!order size:{},orderSns:{}",orderSnList4Err.size(),Joiner.on(Constants.SEPARATOR_COMMA).skipNulls().join(orderSnList4Err));
				CreateBillLog cbl=new CreateBillLog("updateReferrerCash() in MemberServiceImpl got error:更新推荐人1%金额到会员系统失败!失败订单数量:{}:"+orderSnList4Err.size(),DateUtil.getCurrentTimeSec(),null);
				boolean isSuccess=shareProfitLogService.logCreateBillLog(cbl);
				if(!isSuccess){
					log.error("updateReferrerCash() in MemberServiceImpl:shareProfitLogService.logCreateBillLog()失败!");
				}
			} catch (Exception e) {
				log.error("shareProfitLogService.logCreateBillLog() 送积分errorLog!error:{}:",e.getMessage());
			}
			

		}
		
	
		if(orderSnList!=null && !orderSnList.isEmpty()){
			//inform o2o 结算状态...
			try {
				boolean isSuccess=o2oCallbackService.informSettlementStatus(orderSnList, ShareProfitConstants.O2O_SETTLEMENT_STATUS_CODE_CASH);		
				if(!isSuccess){
					CreateBillLog cbl=new CreateBillLog("通知O2O结算状态:更新一级推荐人1%金额到会员系统失败!失败订单数量:"+orderSnList.size(),DateUtil.getCurrentTimeSec(),null);
					shareProfitLogService.logCreateBillLog(cbl);
				}
			} catch (Exception e) {
				log.error("shareProfitLogService.logCreateBillLog() 通知O2O结算状态STATUS_CODE_CASH errorLog!error:{}",e.getMessage());
			}
			
		}

	}
	

 
	
}
