package com.meiduimall.service.account.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.SysEncrypParamsConst;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.service.AccountServices;
import com.meiduimall.service.account.service.MDMallServices;
import com.meiduimall.service.account.service.MSConsumePointsDetailService;
import com.meiduimall.service.account.util.DESC;
import com.meiduimall.service.account.util.RequestURL;
import com.meiduimall.service.account.util.SerialStringUtil;
import com.meiduimall.service.account.util.StringUtil;
import com.meiduimall.service.account.util.SystemConfig;

/**
 * 美兑商城接口实现类
 * @author feixiaojie
 *
 */
@Component
public class MDMallServicesImpl implements MDMallServices {
	
	private final static Logger logger=LoggerFactory.getLogger(MDMallServicesImpl.class);

	@Autowired
	private  BaseDao  baseDao;
	@Autowired
	private  MSConsumePointsDetailService msConsumePointsDetailService;
	@Autowired
	private AccountServices accountServices;
	@Override
	public String addMallPoints(JSONObject jsonobj, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		result.put(SysParamsConst.STATUS_CODE, ApiStatusConst.SUCCESS);
		String token = jsonobj.getString("token");    //用户标识
		String memid=RedisTemplate.getJedisInstance().execGetFromCache(token);
		String loginname = jsonobj.getString("show_login_name");//用户名
		String password = jsonobj.getString("show_pass_word");//密码
		String topupnum = jsonobj.getString("topup_num");//充值数额
		String orderSource = jsonobj.getString("order_source"); //数据来源
		String ip = jsonobj.getString("ip");//访问IP
		logger.info("addMallPoints开始处理."+memid);
		/** 获取会员信息 */
		Map<String,String> param = new HashMap<String,String>();
		param.put("encryValue",DESC.encryption(memid));
		param.put("depytValue",memid);
		/*MemberGet member = baseDao.selectOne(param,"MemberMapper.getMemIdAndPhoneByUserId");*/
		String member=null;
		if(member == null){
			result.put(SysParamsConst.STATUS_CODE, "1002");
			result.put(SysParamsConst.RESULT_MSG, "当前用户在会员系统不存在");
			logger.warn("未获取到有效的会员信息，充值失败."+memid);
			return result.toString();
		}
		/** 调用美兑接口充值，扣除GCI钱包消费 */
		String orderId = "1GW+" + loginname + "+" + String.valueOf(System.currentTimeMillis()/1000L);
		JSONObject resultAddBank = invokForAddBank(request, ip,"memId", loginname, password, topupnum, orderId);
		if (!ApiStatusConst.SUCCESS.equals(resultAddBank
				.getString(SysParamsConst.STATUS_CODE))) {
			return resultAddBank.toString();
		}else{
			result = resultAddBank;
		}
		logger.info("调用美兑接口充值成功."+memid);

		/** 充值成功后回写会员信息表积分字段 */
		if(member != null){
			boolean resultBool  = addMDConsumePoints("memId",topupnum);
			if(!resultBool){
				/*result.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SERVER_ERROR);
				result.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.SERVER_ERROR_C);*/
				logger.info("addMallPoints处理完成，出现系统异常." +memid);
				return result.toString();
			} 
			//插入账户明细表
			msConsumePointsDetailService.saveAddConsumePoints(
					"memId", orderId,
					SerialStringUtil.getDictOrderSource(orderSource), topupnum,
					SysEncrypParamsConst.POINTS_OPERATOR_TYPE_CZ,"memId", 
					SerialStringUtil.getPointsRemark(SysEncrypParamsConst.POINTS_OPERATOR_TYPE_CZ,"phone"));
			logger.info("回写会员积分成功，积分数额：" + topupnum +memid);
		}
		logger.info("addMallPoints处理完成，没有异常."+memid);
		
		return result.toString();
	}
	/**
	 * 方法名: invokForAddBank<br>
	 * 描述:  调用美兑充值积分接口<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-19
	 * @param request
	 * @param userid
	 * @param password
	 * @param topupnum
	 * @param ip
	 * @param orderid
	 * @return
	 */
	@Override
	public JSONObject invokForAddBank(HttpServletRequest request, String ip, String memid, String userid,
			String password, String topupnum, String orderid) {
		JSONObject result = new JSONObject();
		result.put(SysParamsConst.STATUS_CODE,ApiStatusConst.SUCCESS);
		String appendMsg = "[" + orderid + "]"; 
		//调用接口参数
		String key = SystemConfig.configMap.get("MD_ADDBANK_KEY");
		JSONObject paramJson = new JSONObject();
		paramJson.put("orderNoid", orderid);
		paramJson.put("username", userid);
		paramJson.put("num", topupnum);
		paramJson.put("userpwd", password);
		/*paramJson.put("sign", SortUtil.ascSortMD5(paramJson,"",("&key="+key+"")).toLowerCase());*/
		logger.info("调用美兑充值接口，请求参数：" + paramJson.toString() + appendMsg);
		
		String  httpurl = RequestURL.getHttpsServer(
				SystemConfig.configMap.get("MD_ADDBANK_URL"), paramJson);
		//调用
		JSONObject resultAddBank = JSONObject.parseObject(httpurl);
		logger.info("调用美兑充值接口，返回参数：" + resultAddBank.toString() + appendMsg);
		
		if("false".equals(resultAddBank.getString("status"))){
			result.put(SysParamsConst.STATUS_CODE, resultAddBank.getString("code"));
			result.put(SysParamsConst.RESULT_MSG, resultAddBank.getString("error"));
		}
		return result;
	}
	@Override
	public boolean addMDConsumePoints(String memId, String consumePoints) throws Exception {
		/*MemberGet member = baseDao.selectOne(memId,"MemberMapper.getPhoneAndAccountScoreByMemId");*/
		String member=null;
		int resultRow = 0;
		//增加基本账户总额
		/*double addAtq = DoubleCalculate.add(
				Double.valueOf(member.getMemBasicAccountTotalQuantity()),
				Double.valueOf(consumePoints));*/
		//修改会员基本账户总额
		Map<String,Object> param = new HashMap<String,Object>();
		
		/*param.put("memId",member.getMemId());*/
		
		/*param.put("addAtq",DESC.encryption(Double.toString(addAtq), memId));*/
		
		resultRow = baseDao.update(param,"MemberMapper.updateAccountPointsByMemId");
		
		return resultRow>0?true:false;
	}
	public  static  String  randomData()
	   {
		   Set<Integer> m = new HashSet<Integer>();
		   int a;
	       do{
	        a = (int)(Math.random()*1000000);
	       }while(m.contains(a));
	       m.add(a);
	       return  a+"";
	   }
	@Override
	public String queryMallMoney(JSONObject obj, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject  result  = new JSONObject();
		result.put(SysParamsConst.STATUS_CODE,ApiStatusConst.SUCCESS);
		String oauthnonce =randomData(); //唯一标识
		String userid = obj.getString("token");    //用户标识
		userid=RedisTemplate.getJedisInstance().execGetFromCache(userid);
		String loginname = obj.getString("show_login_name");//用户名
		String password = obj.getString("show_pass_word");//密码
		String ip = obj.getString("ip"); //访问IP
		String appendMsg = "[" + oauthnonce + "]"; 
		logger.info("queryMallPoints开始处理."+appendMsg);
		
		/** 获取会员信息 */
		Map<String,String> param = new HashMap<String,String>();
		param.put("encryValue",DESC.encryption(userid));
		param.put("depytValue",DESC.deyption(DESC.encryption(userid)));
		/*MemberGet member = baseDao.selectOne(param,"MemberMapper.getMemIdAndPhoneByUserId");*/
		String member=null;
		if(member == null){
			result.put(SysParamsConst.STATUS_CODE, "1002");
			result.put(SysParamsConst.RESULT_MSG, "当前用户在会员系统不存在");
			logger.warn("未获取到有效的会员信息，查询失败."+appendMsg);
			return result.toString();
		}
		/** 调用美兑查询接口 */
		JSONObject resultGetBank = invokForGetBank(request, ip,
				"memId", loginname, password, oauthnonce);
		if (!ApiStatusConst.SUCCESS.equals(resultGetBank
				.getString(SysParamsConst.STATUS_CODE))) {
			return resultGetBank.toString();
		}else{
			result = resultGetBank;
		}
		logger.info("调用美兑查询接口成功."+appendMsg);
		logger.info("queryMallPoints处理完成，没有异常."+appendMsg);
		return result.toString();
	}
	/**
	 * 方法名: invokForGetBank<br>
	 * 描述:  调用美兑查询余额接口<br>
	 * 编写者:  admin <br>
	 * 创建时间: 2016-10-20
	 * @param request
	 * @param userid
	 * @param password
	 * @param oauthnonce
	 * @return
	 */
	@Override
	public JSONObject invokForGetBank(HttpServletRequest request, String ip, String memid, String userid,
			String password, String oauthnonce) throws Exception {
		JSONObject result = new JSONObject();
		result.put(SysParamsConst.STATUS_CODE,ApiStatusConst.SUCCESS);
		String appendMsg = "[" + oauthnonce + "]"; 
		//调用接口参数
		String key = SystemConfig.configMap.get("MD_GETBANK_KEY");
		JSONObject paramJson = new JSONObject();
		paramJson.put("username", userid);
		paramJson.put("userpwd", password);
		/*paramJson.put("sign", SortUtil.ascSortMD5(paramJson,"",("&key="+key)).toLowerCase());*/
		/*Logger.info("调用美兑查询接口，请求参数：" + paramJson.toString() + appendMsg);
		//调用接口
		JSONObject resultAddBank = JSONObject.parseObject(RequestURL.getHttpsServer(SystemConfig.configMap.get("MD_GETBANK_URL"), paramJson));
		Logger.info("调用美兑查询接口，返回参数：" + resultAddBank.toString() + appendMsg);
		if("false".equals(resultAddBank.getString("status"))){
			result.put(SysParamsConst.STATUS_CODE, resultAddBank.getString("code"));
			result.put(SysParamsConst.RESULT_MSG, resultAddBank.getString("error"));
		}else{
			//查询返回金额
			JSONObject  dataObject = new JSONObject();
			dataObject.put("money",resultAddBank.getString("money"));
			result.put("result", dataObject);
		}*/
		return result;
	}
	@Override
	public String meiduiIntegralTransfer(JSONObject obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		String token = obj.getString("token");        //转出用户登录名
		String memid=RedisTemplate.getJedisInstance().execGetFromCache(token);
		String intoUserId = obj.getString("into_user_id");        //转入用户登录名
		String integralQuantity = obj.getString("integral_quantity"); //转入用户登录名
		/** 关键数据校验开始 */
		if (StringUtil.isEmptyByString(intoUserId)
				&& !StringUtil.isPhoneToRegex(intoUserId)
				&& !StringUtil.isEmailToRegex(intoUserId)
				&& !(StringUtil.stringByFilter(intoUserId).length() == intoUserId.length())) {
			result.put(SysParamsConst.STATUS_CODE, 1013);
			result.put(SysParamsConst.RESULT_MSG, "转入积分的会员账户输入错误");
			logger.info("会员"+memid+"输入错误");
			return result.toString();
		}
		/** 获取转出会员信息 */
		Map<String,String> param = new HashMap<String,String>();
		param.put("encryValue",DESC.encryption(memid));
		param.put("depytValue",memid);
	/*	MemberGet member1 = baseDao.selectOne(param,"MemberMapper.getMemIdAndPhoneByUserId");
		if(member1 == null){
			result.put(SysParamsConst.STATUS_CODE, 1014);
			result.put(SysParamsConst.RESULT_MSG, "转出积分的用户在会员系统不存在");
			return result.toString();
		}
		if (StringUtil.isEmptyByString(intoUserId)
				&& !StringUtil.isPhoneToRegex(intoUserId)
				&& !StringUtil.isEmailToRegex(intoUserId)
				&& !(StringUtil.stringByFilter(intoUserId).length() == intoUserId.length())) {
			result.put(SysParamsConst.STATUS_CODE, 1015);
			result.put(SysParamsConst.RESULT_MSG, "转入积分的会员账户输入错误");
			Logger.info("会员"+intoUserId+"输入错误");
			return result.toString();
		}
		*//** 获取转入会员信息 *//*
		param  =  new HashMap<String,String>();
		param.put("encryValue",DESC.encryption(intoUserId));
		param.put("depytValue",DESC.deyption(DESC.encryption(intoUserId)));
		MemberGet member2 = baseDao.selectOne(param,"MemberMapper.getMemIdAndPhoneByUserId");
		if(member2 == null){
			result.put(SysParamsConst.STATUS_CODE, 1016);
			result.put(SysParamsConst.RESULT_MSG, "被转入积分的用户在会员系统不存在");
			Logger.warn("未获取到有效的会员信息，转账失败."+intoUserId);
			return result.toString();
		}
		if (StringUtil.isEmptyByString(integralQuantity) || !StringUtil.isNumeric(integralQuantity)) {
			result.put(SysParamsConst.STATUS_CODE, 1017);
			result.put(SysParamsConst.RESULT_MSG, "积分值不能为空或应为包含小数点的纯数字");
			Logger.info("积分值"+integralQuantity+"输入错误");
			return result.toString();
		}
		*//** 转出会员与转入会员相同 *//*
		if(member1.getMemId().equals(member2.getMemId())){
			result.put(SysParamsConst.STATUS_CODE, 1016);
			result.put(SysParamsConst.RESULT_MSG, "转出会员与转入会员相同，无需转账.");
			Logger.warn("转出会员与转入会员相同，转账失败.");
			return result.toString();
		}
		try{
			double nowpoints =Double.valueOf(member1.getMemBasicAccountTotalQuantity());
			double tfpoints = Double.valueOf(integralQuantity);
			if(nowpoints < 0 || (nowpoints - tfpoints) < 0 ){
				result.put(SysParamsConst.STATUS_CODE, "1015");
				result.put(SysParamsConst.RESULT_MSG, "转出积分失败，积分不足.");
				Logger.info("会员"+intoUserId+"转出积分大于剩余积分总额.");
				return result.toString();
			}
		}catch (Exception e) {
			// TODO: handle exception
			result.put(SysParamsConst.STATUS_CODE, "1015");
			result.put(SysParamsConst.RESULT_MSG, "转出积分错误，积分不足.");
			Logger.info("会员"+intoUserId+"转出积分计算出现错误，错误原因："+e.getMessage());
			return result.toString();
		}
		//生成编号
		String inMthId = UUID.randomUUID().toString();
		String outMthId = UUID.randomUUID().toString();
		//扣减转出用户的积分
		boolean isSuccess1 = accountServices.cutMDConsumePointsAndDetail(member1.getMemId(), integralQuantity,inMthId,SerialStringUtil.getDictOrderSource("app"), 
				SysEncrypParamsConst.POINTS_OPERATOR_TYPE_JFZC, member1.getMemId(), 
		 SerialStringUtil.getPointsRemark(SysEncrypParamsConst.POINTS_OPERATOR_TYPE_JFZC, member2.getMemPhone()));
		Logger.info("扣减用户名为："+memid+"的积分，扣减结果(isSuccess1)为：" + (isSuccess1 == true?"成功":"失败"));
		if(!isSuccess1){
			result.put(SysConstant.STATUS_CODE, 1019);
			result.put(SysConstant.RESULT_MSG,"转出积分失败");
			return result.toString();
		}
		//增加被转入的用户的积分
		boolean isSuccess2 = accountServices.addMDConsumePointsAndDetail(member2.getMemId(), integralQuantity,outMthId,SerialStringUtil.getDictOrderSource("app"), 
			ApplicationConstant.POINTS_OPERATOR_TYPE_JFZR, member2.getMemId(), 
			SerialStringUtil.getPointsRemark(ApplicationConstant.POINTS_OPERATOR_TYPE_JFZR, member1.getMemPhone()));
		Logger.info("被转入积分的用户名："+memid+"的积分，增加结果(isSuccess2)为：" + (isSuccess2 == true?"成功":"失败"));
		if(!isSuccess2){
			Logger.info("被转入积分的用户名："+memid+"的积分，增加结果(isSuccess2)为：" + (isSuccess2 == true?"成功":"失败"));
			throw new RuntimeException();
		}
		//插入积分传出记录
		MemberTransferHistory history = new MemberTransferHistory();
		history.setMthId(inMthId);
		history.setMemId(member1.getMemId());//转出积分的账号id
		history.setDictMthCategory("21B260C3-A230-4601-8D62-420OPT20AT24");//内部转账
		history.setDictMthStatus("21B260C3-A230-4601-8D62-420OPT20AT56"); //即时到账
		history.setMthNo(GenerateNumber.getSerialnumber());
		history.setMthQuantity(integralQuantity);
		history.setMthActualQuantity(integralQuantity);
		history.setMthCreatedBy(member1.getMemId());
		history.setMthCreatedDate(new Date());
		history.setMthRemark(member1.getMemLoginName()+"转"+ integralQuantity + "积分给登录名为："+intoUserId + "的会员");
		history.setInternalTransferType("21B260C3-A230-4601-8D62-420OPT20A219"); //转出
		history.setMthAccount(member1.getMemLoginName());//转出积分的账号
		history.setTransInMemberNickName(intoUserId);//被转入积分的账号
		baseDao.insert(history,"MemberTransferHistoryMapper.saveMemberTransferHistory");
		
		//插入积分传入记录
		MemberTransferHistory history2 = new MemberTransferHistory();
				//插入积分传出记录
				history2.setMthId(outMthId);
				history2.setMemId(member2.getMemId());//转出积分的账号id
				history2.setDictMthCategory("21B260C3-A230-4601-8D62-420OPT20AT24");//内部转账
				history2.setDictMthStatus("21B260C3-A230-4601-8D62-420OPT20AT56"); //即时到账
				history2.setMthNo(GenerateNumber.getSerialnumber());
				history2.setMthQuantity(integralQuantity);
				history2.setMthActualQuantity(integralQuantity);
				history2.setMthCreatedBy(member2.getMemId());
				history2.setMthCreatedDate(new Date());
				history2.setMthRemark(intoUserId+"接收登录名为："+member1.getMemLoginName() + "的会员转入的"+ integralQuantity + "积分");
				history2.setInternalTransferType("21B260C3-A230-4601-8D62-420OPT20A220"); //转入
				history2.setMthAccount(member1.getMemLoginName());//转出积分的账号
				history2.setTransInMemberNickName(intoUserId); //被转入积分的账号
				baseDao.insert(history2,"MemberTransferHistoryMapper.saveMemberTransferHistory");
				result.put(SysConstant.STATUS_CODE, SysConstant.ZERO);
				result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);*/
		return result.toString();
	}
}
