package com.meiduimall.service.settlement.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.common.ShareProfitConstants;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfShareProfit;
import com.meiduimall.service.settlement.model.EcmOrder;
import com.meiduimall.service.settlement.service.OrderService;
import com.meiduimall.service.settlement.task.AsyncTaskService;
import com.meiduimall.service.settlement.vo.ShareProfitVO;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: OrderController.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 订单分润和分润查询服务
 */
@RestController
@RequestMapping("/settlementservice/orderservice/v1")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AsyncTaskService asyncTaskService;


	/**
	 * 功能描述:  订单分润接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param ecmOrder
	 * return  ResBodyData
	 * throws Exception
	 */
	@RequestMapping(value="/shareprofit",method=RequestMethod.POST)
	public ResBodyData shareProfit(@Validated EcmOrder ecmOrder)throws Exception{
		
		long start=System.currentTimeMillis();
		log.info("share profit for order start:{}",start);
		
		Integer statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS;
		boolean isSuccess=true;
		//shareStatus:0:未分润;1已分润
		Integer shareStatus=1;
		String resultMsg="订单分润成功!";
		final List<String> errors=new ArrayList<String>();
		
		EcmMzfShareProfit shareProfit=null;
		try {
			boolean isExisted=orderService.checkShareProfitExisted(ecmOrder.getOrderSn());
			if(isExisted){
				resultMsg="该订单已经分润过啦！不能再重复分润!";
				statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
				return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
			}
			
			shareProfit=orderService.buildShareProfit(ecmOrder,errors);
		} catch (Exception e) {
			resultMsg="订单分润失败!";
			shareStatus=0;
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			log.error("buildShareProfit() for orderSn:{} in OrderController got error:{}",ecmOrder.getOrderSn(),e.getMessage());
			log.info("buildShareProfit() got exception:"+errors.toString());
			return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
		}
		
		if(errors!=null && !errors.isEmpty()){
			log.info("orderSn:{},分润数据有错误:{}",ecmOrder.getOrderSn(),errors.toString());
			resultMsg=errors.toString();
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			shareStatus=0;
			return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
		}
		
		//2.保存分润数据到DB
		if(shareProfit!=null){
			try{
				orderService.saveShareProfit(shareProfit);
			}catch(Exception e){
				isSuccess=false;
				log.error("saveShareProfit() for orderSn:{} got Exception:{}",shareProfit.getOrderSn(),e.getMessage(),e);
			}
		}else{
			isSuccess=false;
		}

		if(!isSuccess){
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			resultMsg="订单分润失败!";
			shareStatus=0;
		}else{
			//异步同步积分到会员系统
			asyncTaskService.updateScore2MemberSystem(shareProfit,ShareProfitConstants.SHARE_PROFIT_SOURCE_O2O,null);
		}
		
		long end=System.currentTimeMillis();
		log.info("share profit for order end:{}",end);
		log.info("total time(second) for shareprofit:{}", (end-start)/1000);
		return SettlementUtil.buildReponseData(ImmutableMap.of("orderSn", ecmOrder.getOrderSn(),"shareStatus",shareStatus), statusCode, resultMsg);
	}


	/**
	 * 功能描述:  根据订单号列表查询订单状态接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param orderSns
	 * return  ResBodyData
	 * throws Exception
	 */
	@PostMapping(value="/queryorderstatus")
	public ResBodyData queryOrderStatus(String[] orderSns) throws Exception{
		
		List<EcmMzfOrderStatus> queryorderstatus=new ArrayList<EcmMzfOrderStatus>();
		
		if(orderSns!=null && orderSns.length>0){
			queryorderstatus = orderService.queryOrderStatus(Arrays.asList(orderSns));
		}
		
		return SettlementUtil.buildReponseData(queryorderstatus, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "查询订单状态成功!");
		
	}
	
	/**
	 * 功能描述:同步订单审核状态接口(bill_status状态改为1, status_desc改为待结算)
	 * Author: 吴军
	 * Date:   2017年3月14日 下午3:38:26   
	 * param input
	 * return ResBodyData
	 * throws Exception
	 */
	@PostMapping(value="/syncverifystatus")
	public ResBodyData syncVerifyStatus(@Validated EcmMzfOrderStatus orderStatus) throws Exception{
		log.info("syncVerifyStatus() in the OrderController start--");
		
		int statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS;

		Boolean isSuccess=true;
		String msg="同步订单审核状态数据到结算系统成功!";
		try {
			isSuccess = orderService.syncVerifyStatus(orderStatus);
		} catch (Exception e) {
			isSuccess=false;
			log.error("同步订单审核状态接口 出错，orderService.syncVerifyStatus() for orderSn:{} got error:{}",orderStatus.getOrderSn(),e.getMessage(),e);
		}
		
		if(!isSuccess){
			msg="同步订单审核状态数据到结算系统失败!";
			statusCode=ShareProfitConstants.RESPONSE_STATUS_CODE_FAILURE;
			log.info(msg);
		}
			
		return SettlementUtil.buildReponseData("", statusCode, msg);
		
	}

	
	/**
	 * 功能描述:  根据订单号列表查询订单分润接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * param orderSns
	 * return  ResBodyData
	 * throws Exception
	 */
	@RequestMapping(value="/queryshareprofit",method=RequestMethod.POST)
	@ResponseBody
	public ResBodyData queryShareProfit(String[] orderSns) throws Exception{
		
		List<EcmMzfShareProfit> shareProfits=new ArrayList<EcmMzfShareProfit>();
		
		if(orderSns!=null && orderSns.length>0){
			shareProfits = orderService.queryShareProfit(Arrays.asList(orderSns));
		}

		return SettlementUtil.buildReponseData(shareProfits, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "成功!");

	}
	
	
	/**
	 * 功能描述:  根据区代/个代查询今日订单佣金和待结算金额接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26 
	 * param code
	 * param accountRoleType
	 * return ResBodyData
	 * throws Exception
	 */
	@RequestMapping(value="/queryprofitbyrole",method=RequestMethod.POST)
	@ResponseBody
	public ResBodyData queryProfitByRole(String code,Integer accountRoleType) throws Exception{
		
		ShareProfitVO shareProfitVO=orderService.queryProfitByRole(code,accountRoleType);
	
		return SettlementUtil.buildReponseData(shareProfitVO, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "成功!");

	}
	
	/**
	 * 功能描述:  根据流水编号查询分润数据接口 (已经合并到查询账单流水详情接口WaterController.querywaterbyid())
	 * Author: 许彦 雄
	 * param waterId
	 * param loginType
	 * param code
	 * param pageNumber
	 * param pageSize
	 * return ResBodyData
	 * throws Exception
	 */
	@RequestMapping(value="/queryprofitbywaterbytype",method=RequestMethod.POST)
	@ResponseBody
	public ResBodyData queryProfitByWaterByType(String waterId,Integer loginType,String code,Integer pageNumber,Integer pageSize) throws Exception{
		
		Integer count = orderService.queryProfitCountByWaterId(waterId);
		
		List<EcmMzfShareProfit> shareProfitList=orderService.queryProfitByWaterByType(waterId,loginType,code,pageNumber,pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shareProfitList", shareProfitList);
		map.put("total", count);
		
		return SettlementUtil.buildReponseData(map, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "查询账单流水详情数据成功!");

	}
	
	/**
	 * 功能描述:  根据代理或商家编号查询汇总分润数据接口
	 * Author: 许彦 雄
	 * Date:   2017年3月28日 下午14:41:02
	 * param codes
	 * param billStartDate
	 * param billEndDate
	 * return  ResBodyData
	 * throws Exception
	 */
	@RequestMapping(value="/querytotalprofit",method=RequestMethod.POST)
	public ResBodyData queryTotalProfit(String[] codes,Integer billStartDate,Integer billEndDate) throws Exception{
		
		List<ShareProfitVO> shareProfitVOs=new ArrayList<ShareProfitVO>();
		if(codes!=null && codes.length>0){
			shareProfitVOs= orderService.queryTotalProfit(Arrays.asList(codes),billStartDate,billEndDate);
		}

		return SettlementUtil.buildReponseData(shareProfitVOs, ShareProfitConstants.RESPONSE_STATUS_CODE_SUCCESS, "查询汇总分润数据成功!");

	}


}
