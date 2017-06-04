package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.dto.ServiceToServiceDTO;
import com.meiduimall.service.account.service.AuthorizationPointsService;
import com.meiduimall.service.account.service.ConsumePointsDetailService;

/**
 * 类名:  AuthorizationPointsAction<br>
 * 描述:  会员积分相关外部接口控制类，与会员积分有关的接口使用此方法暴露给外部调用<br>
 * 创建时间: 2016-11-30
 */
@Component
public class AuthorizationPointsServiceImpl implements AuthorizationPointsService
{
	private final static Logger logger=LoggerFactory.getLogger(AuthorizationPointsServiceImpl.class);
	
	@Autowired
	private  BaseDao baseDao;
	@Autowired
	private  ConsumePointsDetailService   mSConsumePointsDetailService;
	@Override
	public String getPointsDetailOfToken(JSONObject jsonobj,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.info("进入services方法，开始处理业务.");
		JSONObject  jsonObject =  new JSONObject();
		/*jsonObject.put(SysConstant.STATUS_CODE, SysConstant.ZERO);
		jsonObject.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);*/
		String token = jsonobj.getString("token");    //用户标识
		String memid=RedisTemplate.getJedisInstance().execGetFromCache(token);
		String ordertype = jsonobj.getString("order_type");//业务类型
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("encryValue",memid);
		param.put("depytValue",memid);
		/*String  member = baseDao.selectOne(param, "MemberMapper.getMemIdAndPhoneByUserId");*/
		String member=null;
		if(member == null){
			/*jsonObject.put(SysConstant.STATUS_CODE, "1002");
			jsonObject.put(SysConstant.RESULT_MSG, "当前用户在会员系统不存在");*/
			return jsonObject.toString();
		}
		ServiceToServiceDTO dto = new ServiceToServiceDTO();
		//代码值转换
		/*jsonobj.put("many_dict_operator_type", SerialStringUtil.getDictManyOperatorType(ordertype,SysConstant.MANY_DATA_SPLIT_STR));*/
		/*dto.setBsId(member.getMemId());*/
		dto.setReqJsonObj(jsonobj);
		dto.reqJsonObjectToReqMap();
		mSConsumePointsDetailService.getConsumePointsDetail(dto);
		if(ServiceToServiceDTO.EXEC_SUCCESS_RESULT_DATA.equals(dto.getExecFlag())){
			jsonObject.put("total_page", dto.getResultInt());
			jsonObject.put("result",JSONArray.parseArray(dto.getResultStr()));
		}else{
			jsonObject.put("total_page", "0");
			jsonObject.put("result", new JSONArray());
		}
		
		logger.info("services处理完成，没有出现异常.");
		return jsonObject.toString();
	}

}
