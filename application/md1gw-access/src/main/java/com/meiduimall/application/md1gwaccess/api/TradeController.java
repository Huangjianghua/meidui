package com.meiduimall.application.md1gwaccess.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.md1gwaccess.constant.ResponseBodyData;
import com.meiduimall.application.md1gwaccess.model.SystradeTrade;
import com.meiduimall.application.md1gwaccess.service.TradeService;
import com.meiduimall.application.md1gwaccess.util.Logger;

@RestController
@RequestMapping("/md1gwmall/md1gw_access/v1")
public class TradeController {

	@Autowired
	private TradeService tradeService;
	 
	/**
	 * 获取订单金额
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/gettrademoney")
	public ResponseBodyData getTradeMoney(@Param("tid") String tid) throws Exception{
		String result = null;
		List<SystradeTrade> tradeMoney = null;
		try {
			if(tid == null || "".equals(tid))
				return new ResponseBodyData(1, "tid为空!");
			else
				tradeService.getTradeMoney(tid);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("系统错误 %s", e.getMessage());
			result = "系统错误"+e.getMessage();
			return new ResponseBodyData(null, result);
		}
		return new ResponseBodyData(tradeMoney, "成功!");
		
	}
	
	/**
	 * 获取平台订单表信息
	 * @param platformId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/getptrade")
	public ResponseBodyData getpTrade(@Param("platformId") String platformId) throws Exception{
		String result = null;
		List<SystradeTrade> tradeMoney = null;
		try {
			if(platformId == null || "".equals(platformId))
				return new ResponseBodyData(1, "platformId为空!");
//			else
//				tradeMoney = tradeService.getpTrade(platformId);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("系统错误 %s", e.getMessage());
			result = "系统错误"+e.getMessage();
			return new ResponseBodyData(null, result);
		}
		return new ResponseBodyData(tradeMoney, "成功!");
		
	}
}
