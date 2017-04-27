package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSConsumePointsDetailGet;
import com.meiduimall.service.account.service.MembersPointsOpService;
import com.meiduimall.service.account.util.StringUtil;


@Component
public class MembersPointsOpServiceImpl implements MembersPointsOpService {
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public JSONObject getMemberScoreByMemId(String memId, String type) throws Exception {
		JSONObject json = new JSONObject();
		Map<String, String> param = new HashMap<String, String>();
		int income = 0;
		int expenditure = 0;
		if (StringUtil.checkStr(memId)) {
			if (SysParamsConst.scoreOpType.containsKey(type)) {
				/*Logger.info("查询会员ID:" + memId + "的%s积分相关统计!", SysParamsConst.scoreOpType.get(type));*/
				param.put("memId", memId);
				param.put("type", type);
				List<MSConsumePointsDetailGet> scoreList = baseDao.selectList(param, "MSConsumePointsDetailMapper.getSocreCountByType");
				if (null != scoreList && scoreList.size() > 0) {
					for (MSConsumePointsDetailGet point : scoreList) {
						income += Integer.parseInt(point.getMcpIncome());
						expenditure += Integer.parseInt(point.getMcpExpenditure());
					}
					if (income <= 0 && expenditure > 0) {
						json.put(SysParamsConst.STATUS_CODE, "0");
						json.put(SysParamsConst.RESULT_MSG, "获取成功!");
						Map<String, String> resultMap = new HashMap<String, String>();
						resultMap.put("credit", "" + expenditure);
						json.put(SysParamsConst.RESULT, resultMap);
						/*Logger.info("会员ID:" + memId + "查询%s类型下的积分成功!", SysParamsConst.scoreOpType.get(type));*/
					} else if (income > 0 && expenditure <= 0) {
						json.put(SysParamsConst.STATUS_CODE, "0");
						json.put(SysParamsConst.RESULT_MSG, "获取成功!");
						Map<String, String> resultMap = new HashMap<String, String>();
						resultMap.put("credit", "" + income);
						json.put(SysParamsConst.RESULT, resultMap);
						/*Logger.info("会员ID:" + memId + "查询%s类型下的积分成功!", SysParamsConst.scoreOpType.get(type));*/
					} else {
						json.put(SysParamsConst.STATUS_CODE, "9999");
						json.put(SysParamsConst.RESULT_MSG, "服务器错误!");
						/*Logger.info("会员ID:" + memId + "查询%s类型下的数据收入及支出都存在大于0的值!", SysParamsConst.scoreOpType.get(type));*/
					}
				} else {
					json.put(SysParamsConst.STATUS_CODE, "0");
					json.put(SysParamsConst.RESULT_MSG, "获取成功!");
					Map<String, String> resultMap = new HashMap<String, String>();
					resultMap.put("credit", "0");
					json.put(SysParamsConst.RESULT, resultMap);
					/*Logger.info("会员ID:" + memId + "查询%s类型无数据,积分为0!", SysParamsConst.scoreOpType.get(type));*/
				}
			} else {
				json.put(SysParamsConst.STATUS_CODE, "9998");
				json.put(SysParamsConst.RESULT_MSG, "未知的积分操作类型!");
				/*Logger.info("未知的积分操作类型:%s", type);*/
			}
		} else {
			json.put(SysParamsConst.STATUS_CODE, "1020");
			json.put(SysParamsConst.RESULT_MSG, "当前会员ID:" + memId + "不存在!");
			/*Logger.info("当前会员ID:%s不存在!", memId);*/
		}
		return json;
	}
}