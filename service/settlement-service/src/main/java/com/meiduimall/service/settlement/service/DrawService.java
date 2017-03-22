package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.model.EcmMzfDraw;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;


public interface DrawService {

	
	/**
	 * 获取区代、个代或商家可提现金额
	 * @param code
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> queryAccoutBalance(String code) throws Exception;

	/**
	 * 获取提现管理列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<EcmMzfDraw> queryDrawCash(Map<String, Object> params) throws Exception;
	
	/**
	 * 获取提现管理列表总数
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int getDrawCount(Map<String,Object> params) throws Exception;

	/**
	 * 根据提现编号获取提现详情
	 * @param drawCode
	 * @return
	 */
	EcmMzfDraw queryDrawCashById(String drawCode)throws Exception;

	/**
	 * 审核提现申请
	 * @param drawCode
	 * @return
	 */
	Map<String, Object> verifyDrawCashById(EcmMzfDraw ecmmzfdraw)throws Exception;

	/**
	 * 驳回提现申请接口
	 * @param drawCode
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> rejectDrawCashById(EcmMzfDraw ecmmzfdrawinput)throws Exception;

	/**
	 * 提现确认转账成功或失败接口（财务更改提现单状态）
	 * @param ecmMzfDraw
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> confirmDrawCashByIdByType(EcmMzfDraw ecmmzfdrawinput)throws Exception;
	
	/**
	 * 插入提现相关信息
	 * @param ecmMzfDraw
	 * @return
	 * @throws Exception
	 */
	public boolean insertDrawInfo(EcmMzfDraw ecmMzfDraw) throws Exception;
	
	/**
	 * 插入提现记录
	 * @param ecmMzfDraw
	 * @return
	 * @throws Exception
	 */
	public int insertDraw(EcmMzfDraw ecmMzfDraw) throws Exception;
 
	/**
	 * 插入提现流水
	 * @param ecmMzfDrawWater
	 * @return
	 * @throws Exception
	 */
	public int insertDrawWater(EcmMzfDrawWater ecmMzfDrawWater) throws Exception;
	
	/**
	 * 根据code、nowTime查询提现流水表ecm_mzf_draw_water中的总数，用于生成提现流水编号
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int getDrawWaterCount(Map<String, Object> params) throws Exception;
	

	/**
	 * 获取当天提现记录总数
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int getCountByCode(Map<String,Object> params) throws Exception;
}
