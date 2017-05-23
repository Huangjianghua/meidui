package com.meiduimall.service.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.meiduimall.exception.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SmsTemplateIDConst;
import com.meiduimall.service.member.constant.SysEncrypParamsConst;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSAccount;
import com.meiduimall.service.member.model.MSConsumePointsDetail;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.MSMembersSet;
import com.meiduimall.service.member.model.MemberAddressesSet;
import com.meiduimall.service.member.model.request.RequestCheckValidateCode;
import com.meiduimall.service.member.model.request.RequestLogin;
import com.meiduimall.service.member.model.request.RequestRegister;
import com.meiduimall.service.member.model.request.RequestRegisterO2O;
import com.meiduimall.service.member.model.request.RequestSendSms;
import com.meiduimall.service.member.service.BasicOpService;
import com.meiduimall.service.member.service.ShareMenService;
import com.meiduimall.service.member.service.SmsService;
import com.meiduimall.service.member.service.UserInfoService;
import com.meiduimall.service.member.service.ValidateService;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.SerialStringUtil;
import com.meiduimall.service.member.util.StringUtil;
import com.meiduimall.service.member.util.ToolsUtil;


/**
 * 用户基本的操作实现类
 * @author chencong
 *
 */
@Service
public class BasicOpServiceImpl implements BasicOpService {
	
	private final static Logger logger=LoggerFactory.getLogger(BasicOpServiceImpl.class);

	@Autowired
	BaseDao baseDao;
	
	@Autowired
	ShareMenService shareMenService;
	
	@Autowired
	SmsService smsService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	ValidateService validateService;
	
	@Override
	public Map<String, Object> handlesignout(JSONObject jsonObject) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();//返回结果
		String userid=jsonObject.getString("user_id");
		MSMembersGet msMembersGet=null;
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("userid",DESC.encryption(userid));
		msMembersGet=  baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
		String memid=msMembersGet.getMemId();
		//如果不存在这个memid
		if(StringUtil.isEmptyByString(memid)){
			map.put("status_code", ApiStatusConst.MEMBER_NOT_EXIST);
			return map;
		}
		try {
			RedisTemplate rt=RedisTemplate.getJedisInstance();
			if(rt.execExistsFromCache(memid))
			{
				String token=rt.execGetFromCache(memid);
				if(rt.execExistsFromCache(token))
				{
					if(rt.execDelToCache(memid))
					{
						logger.info("要删除的token值：{}",token);
						if(rt.execDelToCache(token))
						{
							map.put("status_code","0");
							map.put("result_msg","success");
						}
						else
						{
							map.put("status_code","1111");
							map.put("result_msg","token"+token+" 删除这个key失败");
						}
					}
					else
					{
						map.put("status_code","1111");
						map.put("result_msg","memid:"+memid+" 删除这个key失败");
					}
				}
				else
				{
					map.put("status_code","1111");
					map.put("result_msg","token:"+token+" 不存在这个key");
				}
			}
			else
			{
				map.put("status_code","1111");
				map.put("result_msg","memid:"+memid+"不存在这个key");
			}
		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	
	@Override
	public Map<String, Object> getput(JSONObject jsonObject) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();//返回结果
		String type=null;//操作类型，1是get，2是put
		/**判断type参数是否存在**/
		if(!jsonObject.containsKey("type"))
		{
			map.put("status_code","2222");
			map.put("result_msg","type参数错误");
			return map;
		}
		type=jsonObject.getString("type");
		/**判断type是否是1或2或3,1表示get，2表示put，3表示通过token找userid专用**/
		if(!"1".equals(type)&&!"2".equals(type)&&!"3".equals(type))
		{
			map.put("status_code","2222");
			map.put("result_msg","type参数错误");
			return map;
		}
		else {
			/**通过userid获取memid并判断该memid是否存在**/
			MSMembersGet msMembersGet=null;
			String userid=null;
			String token=null;
			String memid=null;
			Map<String, Object> mapCondition=new HashMap<>();
			if(jsonObject.containsKey("user_id"))
			{				
				userid=jsonObject.getString("user_id");
				mapCondition.put("userid",DESC.encryption(userid));
				msMembersGet=  baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
				memid=msMembersGet.getMemId();
				//如果不存在这个memid
				if(StringUtil.isEmptyByString(memid)){
					map.put("status_code", ApiStatusConst.MEMBER_NOT_EXIST);
					return map;
				}	
			}
			if(jsonObject.containsKey("token"))
			{
				token=jsonObject.getString("token");
			}
			RedisTemplate rt=RedisTemplate.getJedisInstance();
			/**如果是get操作**/
			if("1".equals(type))
			{
				if(rt.execExistsFromCache(memid))
				{
					//通过memid(key)获取token
					token=rt.execGetFromCache(memid);
					map.put("status_code","0");
					map.put("result_msg","success");
					Map<String, Object> result_map=new HashMap<>();
					result_map.put("token",token);
					result_map.put("memId",memid);
					map.put("result",JSON.toJSON(result_map));
				}
				else
				{
					map.put("status_code","0");
					map.put("result_msg","redis不存在这个key:"+memid);
					Map<String, Object> result_map=new HashMap<>();
					result_map.put("token","");
					result_map.put("memId","");
					map.put("result",JSON.toJSON(result_map));
					return map;
				}
			}
			/**如果是put操作**//*
			if("2".equals(type))
			{
				token=ToolsUtil.createToken(userid);
				//把token存储到redis
				RedisTemplate.getJedisInstance().execSetexToCache(token,Constants.REDIS_ONEMONTH,memid);
				//临时代码，兼容旧会员系统
				RedisTemplate.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH,token);
				map.put("status_code","0");
				map.put("result_msg","success");
				Map<String, Object> result_map=new HashMap<>();
				result_map.put("token",token);
				result_map.put("memId",memid);
				map.put("result",JSON.toJSON(result_map));
			}*/
			/**如果是通过token获取user_id操作**/
			if("3".equals(type))
			{
				map.put("status_code","0");
				map.put("result_msg","success");
				Map<String, Object> result_map=new HashMap<>();
				if(rt.execExistsFromCache(token))
				{
					memid=rt.execGetFromCache(token);
					result_map.put("token",token);
					result_map.put("memId",memid);
				}
				else
				{
					result_map.put("token",token);
					result_map.put("memId","");
				}
				map.put("result",JSON.toJSON(result_map));
			}
		}
		return map;
	}
	
	@Transactional
	public ResBodyData login(RequestLogin requestLogin) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		String userid=requestLogin.getUser_name();//用户名
		String password=requestLogin.getPassword();//密码
		String tokenKey=requestLogin.getTokenKey();
		
		Map<String, Object> mapCondition=new HashMap<>();//查询条件
		mapCondition.put("userid",DESC.encryption(userid));
		MSMembersGet msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");//根据userid判断该用户是否存在
		if(msMembersGet==null){
			throw new ServiceException(ApiStatusConst.MEMBER_NOT_EXIST);
		}
		String memLockCount=msMembersGet.getMemLockCount();//锁定次数密文
		String memLockCountPlained=msMembersGet.getMemLockCountPlained();//锁定次数明文
		String memId=msMembersGet.getMemId();
		/**判断这个用户是否由于登录失败次数过多被锁定*/
		String redisLoginFail=SysParamsConst.REDIS_LOGIN_LOCK+SysParamsConst.CONNECTION+msMembersGet.getMemId();
		if(RedisTemplate.getJedisInstance().execExistsFromCache(redisLoginFail)){
			String count=RedisTemplate.getJedisInstance().execGetFromCache(redisLoginFail);
			if(Integer.valueOf(count)>=SysParamsConst.MAX_LOGIN_FAIL_COUNT){
				logger.warn("该会员当天登录失败次数已达到5次，暂时被锁定 ");
				throw new ServiceException(ApiStatusConst.MEMBER_LOCK);
			}
		}
		
		/**根据userid和密码查询会员信息*/
		String[] ps=password.split(SysParamsConst.MD_PASSWORD_SPLIT_STR);//1GW会员密码和美兑会员密码用"-"隔开，第一个是1GW会员密码
		mapCondition.put("ygw_pwd",ps[0]);
		mapCondition.put("md_pwd",ps[0]);
		if(ps.length>=2)
			mapCondition.put("md_pwd",ps[1]);
		msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
		MSMembersSet msMembersSet=new MSMembersSet();
		msMembersSet.setMemId(memId);
		if(msMembersGet!=null){
			logger.info("会员：{}用户名和密码输入正确",userid);
			if(msMembersGet.getDictMemStatus().equals(SysEncrypParamsConst.MEMBER_FORBIDDEN_EN)){//如果该会员账号已被禁用
				logger.info("会员{}被禁用，无法登录！",userid);
				throw new ServiceException(ApiStatusConst.MEMBER_FORBIDDEN);
			}
			String redisToken=ToolsUtil.createToken(userid,tokenKey);//生成token
			logger.info("token创建成功：{}",redisToken);
			
			/**更新会员相关信息*/
			msMembersSet.setMemLicenseKey(redisToken);
			if(null!=msMembersGet.getMemLoginTime()){
				msMembersSet.setPfLastLoginTime(msMembersGet.getMemLoginTime());//设置上一次登录时间
			}
			msMembersSet.setMemLoginTime(new Date());
		    baseDao.update(msMembersSet,"MSMembersMapper.updateMemberBasicInfoByCondition");
			
		    RedisTemplate.getJedisInstance().execSetexToCache(redisToken,Constants.REDIS_ONEMONTH,msMembersGet.getMemId());//把token存储到redis，并设置失效时间一个月
		    RedisTemplate.getJedisInstance().execSetexToCache(msMembersGet.getMemId(),Constants.REDIS_ONEMONTH,redisToken);//临时代码，兼容旧会员系统
			
			Map<String, Object> mapData=new HashMap<>();
			mapData.put("token",redisToken);
			mapData.put("user_name",msMembersGet.getMemLoginName());
			resBodyData.setData(mapData);
		}
		else{
			logger.info("会员：{}用户名或密码输入错误，登录失败",userid);
			int newLoginLockCount=Integer.valueOf(memLockCount)+1;
			int newLoginLockCountPlained=Integer.valueOf(memLockCountPlained)+1;
			if(RedisTemplate.getJedisInstance().execExistsFromCache(redisLoginFail)){//如果登录失败过
				String lockCount=RedisTemplate.getJedisInstance().execGetFromCache(redisLoginFail);//获取已登录失败的次数
				int newlockCount=Integer.valueOf(lockCount)+1;//登录失败次数+1
				int lockTime=ToolsUtil.getNowToTomorrowTimeSub(); //计算距离0点0分的秒数
				RedisTemplate.getJedisInstance().execSetexToCache(redisLoginFail,lockTime,String.valueOf(newlockCount));//更新失败次数，0点0分自动解除
			}
			else{//如果没有登录失败过，就设置一次
				RedisTemplate.getJedisInstance().execSetexToCache(redisLoginFail,Constants.REDIS_ONEDAY,SysParamsConst.REDIS_INIT_LOGIN_LOCK_COUNT);
			}
			msMembersSet.setMemLockCount(String.valueOf(newLoginLockCount));
			msMembersSet.setMemLockCountPlained(String.valueOf(newLoginLockCountPlained));
			baseDao.update(msMembersSet,"MSMembersMapper.updateMemberBasicInfoByCondition");
			resBodyData.setStatus(ApiStatusConst.PASSWORD_ERROR);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.PASSWORD_ERROR));
		}
		return resBodyData;
	}
	
	@Transactional
	@Override
	public ResBodyData register(RequestRegister model) throws MdSysException{
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		boolean open_default_share_man=false;//是否分配默认推荐人
		boolean open_default_login_name=false;//是否分配默认登录名
		String tokenKey=model.getTokenKey();//用于生成token的salt
		
		//校验该账号是否已注册
		validateService.checkUserIdExistsThrowable(model.getPhone());
		//校验注册来源是否合法
		validateService.checkRegisterSource(model.getSource());
		
		//分配默认登录名和默认推荐人
		if(StringUtils.isEmpty(model.getLogin_name())){
			open_default_login_name=true;
			model.setLogin_name(SysParamsConst.DEFAULT_LOGIN_NAME_PREFIX+model.getPhone()+String.valueOf(Constants.CONSTANT_INT_ZERO));
		}
		if(!StringUtils.isEmpty(model.getShare_man())){
			if(model.getLogin_name().equals(model.getShare_man())||model.getPhone().equals(model.getShare_man())){
				throw new ServiceException(ApiStatusConst.SHARE_MAN_CANNOT_IS_ITSELF);
			}
		}
		else {
			open_default_share_man=true;
			model.setShare_man(SysParamsConst.MD1GW_DEFAULT_SHARE_LOGIN_NAME);
		}
		//校验推荐人
		MSMembersGet shareManInfo=shareMenService.checkShareMan(model.getShare_man());
		
		//校验注册验证码
		RequestCheckValidateCode checkCode=new RequestCheckValidateCode();
		checkCode.setPhone(model.getPhone());
		checkCode.setType(Constants.CONSTANT_INT_TWO);
		checkCode.setValidate_code(model.getValidate_code());
		smsService.checkValidateCode(checkCode);
		
		//生成会员基本信息
		MSMembersSet memberSet=new MSMembersSet();
		Date date = new Date();
		String memid=UUID.randomUUID().toString();
		memberSet.setMemId(memid);
		memberSet.setMemCreatedBy(memid);
		memberSet.setMemLoginName(model.getLogin_name());
		memberSet.setMemLoginNameIsdefaultIschanged(open_default_login_name?"0_1":"1_0");
		memberSet.setMemOldPhone(model.getPhone());
		memberSet.setMemPhone(model.getPhone());
		memberSet.setMemLoginPwd(model.getPass_word());
		memberSet.setMemNickName(model.getPhone());
		memberSet.setMemCreatedDate(date);
		memberSet.setMemCreatedCategory(1);
		memberSet.setDictMemStatus(SysEncrypParamsConst.MEMBER_STATUS_OK);
		memberSet.setMemParentId(shareManInfo.getMemId());
		memberSet.setMemSignSource(String.valueOf(model.getSource()));//旧会员系统之前传的是O2O和1gw
		memberSet.setMemIsAllActivated(true);
		switch(model.getRole_type()){
		case "1":memberSet.setMemIsAllowShop(SysEncrypParamsConst.MEMBERKAIDIAN_YES);
		case "2":memberSet.setMemIsAllowShop(SysEncrypParamsConst.MEMBERKAIDIAN_NO);
		}
		memberSet.setMemBasicAccountTotal(String.valueOf(Constants.CONSTANT_INT_ZERO));
		memberSet.setMemParentIsdefaultIschanged(open_default_share_man?"0_1":"1_0");
		baseDao.insert(memberSet,"MSMembersMapper.insertMsMember");
		//生成会员地址信息
		MemberAddressesSet memberAddressesSet = new MemberAddressesSet();
		memberAddressesSet.setMemaId(UUID.randomUUID().toString());
		memberAddressesSet.setMemId(memid);
		memberAddressesSet.setMemaStatus(String.valueOf(Constants.CONSTANT_INT_ZERO));
		baseDao.insert(memberAddressesSet,"MSMemberAddressesMapper.addMemberAddressInfo");
		//生成会员余额账户信息
		MSAccount msAccount = new MSAccount();
		msAccount.setId(UUID.randomUUID().toString());
		msAccount.setMemId(memid);
		msAccount.setType(SysParamsConst.ACCOUNT_TYPE_MONEY);
		msAccount.setBalance(String.valueOf(Constants.CONSTANT_INT_ZERO));
		msAccount.setFreezeBalance(String.valueOf(Constants.CONSTANT_INT_ZERO));
		msAccount.setCreateDate(new Date());
		msAccount.setUpdateDate(new Date());
		baseDao.insert(msAccount,"MSMembersMapper.insertMsAccount");
		//生成会员证件信息
		/*MSMemberCertificate mc = new MSMemberCertificate();
		mc.setMcerId(UUID.randomUUID().toString());
		mc.setMemId(memid);
		mc.setDictMcerId(SysEncrypParamsConst.CERTIFICATE_SFZ);
		mc.setMcStatus(SysEncrypParamsConst.HUIYUANRENZHEN_WRZ);
		baseDao.insert(null,"");*/
		//生成会员角色信息
		/*MSMemberRole mro = new MSMemberRole();
		mro.setMemId(memid);
		mro.setRoleId(SysEncrypParamsConst.PUTONGHUIYUAN);
		baseDao.insert(null,"");*/
		
		//更新父类字符串，插入推荐人和粉丝的关联关系（后期数据库表结构改造后此处需修正）
		setShareMenAndFunsRelation(memberSet,shareManInfo);
	
		//新会员送100积分和推荐人获赠100积分写入积分流水
		userInfoService.updateCurrentPointByMemId(memid,String.valueOf(Constants.CONSTANT_INT_ZERO),SysParamsConst.MD1GW_REGISTER_ADD_POINTS);
		String orderId=SysParamsConst.DEFAULT_ORDERID_PREFIX+ model.getLogin_name()+SysParamsConst.ADD_SYMBOL+String.valueOf(System.currentTimeMillis()/1000L);
		insertConsumePointDetail(memid,orderId,String.valueOf(model.getSource()),SysParamsConst.MD1GW_REGISTER_ADD_POINTS,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,model.getPhone(),SysEncrypParamsConst.POINTS_OPERATOR_TYPE_ZCZS);
		if (!open_default_share_man) {
			userInfoService.updateCurrentPointByMemId(shareManInfo.getMemId(),shareManInfo.getMemBasicAccountTotalQuantity(),SysParamsConst.MD1GW_REGISTER_ADD_POINTS);//一级分享人增加100积分
			orderId=SysParamsConst.DEFAULT_ORDERID_PREFIX+ model.getShare_man()+SysParamsConst.ADD_SYMBOL+String.valueOf(System.currentTimeMillis()/1000L);
			insertConsumePointDetail(shareManInfo.getMemId(),orderId,String.valueOf(model.getSource()),SysParamsConst.MD1GW_REGISTER_ADD_POINTS,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,model.getPhone(),SysEncrypParamsConst.POINTS_OPERATOR_TYPE_YQZCZS);
		}
		
		//生成token并写入redis，失效时间一个月
		String redisToken=ToolsUtil.createToken(model.getPhone(),tokenKey);//生成token
		RedisTemplate.getJedisInstance().execSetexToCache(redisToken,Constants.REDIS_ONEMONTH,memid);
		RedisTemplate.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH,redisToken);//临时代码，兼容旧会员系统
		Map<String, Object> mapData=new HashMap<>();
		mapData.put("token",redisToken);
		resBodyData.setData(mapData);
		
		//给新会员发送注册成功短信
		RequestSendSms requestSendSms=new RequestSendSms();
		requestSendSms.setPhone(model.getPhone());
		requestSendSms.setTemplateId(SmsTemplateIDConst.getSmsTemplate(SmsTemplateIDConst.REGIST_SUCCESS));
		requestSendSms.setParams(model.getPhone());
		smsService.sendSms(requestSendSms);
		//给分享人发送获赠积分短信
		requestSendSms.setPhone(shareManInfo.getMemPhone());
		requestSendSms.setTemplateId(SmsTemplateIDConst.getSmsTemplate(SmsTemplateIDConst.GIVE_POINT));
		requestSendSms.setParams(shareManInfo.getMemPhone());
		smsService.sendSms(requestSendSms);
		
		return resBodyData;
	}
	
	@Transactional
	@Override
	public ResBodyData registerO2O(RequestRegisterO2O model) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		boolean open_default_share_man=false;//是否分配默认推荐人
		boolean open_default_login_name=false;//是否分配默认登录名
		String tokenKey=model.getTokenKey();
		
		//校验该账号是否已注册
		validateService.checkUserIdExistsThrowable(model.getPhone());
		
		MSMembersGet shareManInfo=shareMenService.checkShareMan(SysParamsConst.MD1GW_DEFAULT_SHARE_LOGIN_NAME);

		/**开始生成会员信息*/
		MSMembersSet memberSet=new MSMembersSet();//生成会员基本信息
		Date date = new Date();
		String memid=UUID.randomUUID().toString();
		memberSet.setMemId(memid);
		memberSet.setMemCreatedBy(memid);
		
		memberSet.setMemLoginName(SysParamsConst.DEFAULT_LOGIN_NAME_PREFIX+model.getPhone()+String.valueOf(Constants.CONSTANT_INT_ZERO));
		memberSet.setMemLoginNameIsdefaultIschanged(open_default_login_name?"0_1":"1_0");
		memberSet.setMemOldPhone(model.getPhone());
		memberSet.setMemPhone(model.getPhone());
		memberSet.setMemLoginPwd(model.getPass_word());
		memberSet.setMemNickName(model.getPhone());
		memberSet.setMemCreatedDate(date);
		memberSet.setMemCreatedCategory(1);
		memberSet.setDictMemStatus(SysEncrypParamsConst.MEMBER_STATUS_OK);
		memberSet.setMemParentId(shareManInfo.getMemId());
		memberSet.setMemSignSource("8");
		memberSet.setMemIsAllActivated(true);
		memberSet.setMemIsAllowShop(SysEncrypParamsConst.MEMBERKAIDIAN_NO);
		memberSet.setMemBasicAccountTotal(String.valueOf(Constants.CONSTANT_INT_ZERO));
		memberSet.setMemParentIsdefaultIschanged(open_default_share_man?"0_1":"1_0");
		baseDao.insert(memberSet,"MSMembersMapper.insertMsMember");
		
		MemberAddressesSet memberAddressesSet = new MemberAddressesSet();//生成会员地址信息
		memberAddressesSet.setMemaId(UUID.randomUUID().toString());
		memberAddressesSet.setMemId(memid);
		memberAddressesSet.setMemaStatus(String.valueOf(Constants.CONSTANT_INT_ZERO));
		baseDao.insert(memberAddressesSet,"MSMemberAddressesMapper.addMemberAddressInfo");
		
		MSAccount msAccount = new MSAccount();//生成会员余额账户信息
		msAccount.setId(UUID.randomUUID().toString());
		msAccount.setMemId(memid);
		msAccount.setType(SysParamsConst.ACCOUNT_TYPE_MONEY);
		msAccount.setBalance(String.valueOf(Constants.CONSTANT_INT_ZERO));
		msAccount.setFreezeBalance(String.valueOf(Constants.CONSTANT_INT_ZERO));
		msAccount.setCreateDate(new Date());
		msAccount.setUpdateDate(new Date());
		baseDao.insert(msAccount,"MSMembersMapper.insertMsAccount");
		
		/*MSMemberCertificate mc = new MSMemberCertificate();//生成会员证件信息
		mc.setMcerId(UUID.randomUUID().toString());
		mc.setMemId(memid);
		mc.setDictMcerId(SysEncrypParamsConst.CERTIFICATE_SFZ);
		mc.setMcStatus(SysEncrypParamsConst.HUIYUANRENZHEN_WRZ);
		baseDao.insert(null,"");*/
		
		/*MSMemberRole mro = new MSMemberRole();//生成会员角色信息
		mro.setMemId(memid);
		mro.setRoleId(SysEncrypParamsConst.PUTONGHUIYUAN);
		baseDao.insert(null,"");*/
		
		String redisToken=ToolsUtil.createToken(model.getPhone(),tokenKey);//生成token
		RedisTemplate.getJedisInstance().execSetexToCache(redisToken,Constants.REDIS_ONEMONTH,memid);//把token存储到redis，并设置失效时间一个月
		RedisTemplate.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH,redisToken);//临时代码，兼容旧会员系统
		Map<String, Object> mapData=new HashMap<>();
		mapData.put("token",redisToken);
		mapData.put("user_id",model.getPhone());
		resBodyData.setData(mapData);
	
		return resBodyData;
	}
	
	@Transactional
	@Override
	public ResBodyData registerScanCode(RequestRegister model) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		boolean open_default_share_man=false;//是否分配默认推荐人
		boolean open_default_login_name=false;//是否分配默认登录名
		String tokenKey=model.getTokenKey();
		validateService.checkUserIdExistsThrowable(model.getPhone());
		/**校验推荐人,没传就分配默认的*/
		if(!StringUtils.isEmpty(model.getShare_man())){
			if(model.getPhone().equals(model.getShare_man())){
				throw new ServiceException(ApiStatusConst.SHARE_MAN_CANNOT_IS_ITSELF);
			}
		}
		else {
			open_default_share_man=true;
			model.setShare_man(SysParamsConst.MD1GW_DEFAULT_SHARE_LOGIN_NAME);
		}
		MSMembersGet shareManInfo=shareMenService.checkShareMan(model.getShare_man());
		/**校验注册验证码*/
		RequestCheckValidateCode checkCode=new RequestCheckValidateCode();
		checkCode.setPhone(model.getPhone());
		checkCode.setType(Constants.CONSTANT_INT_TWO);
		checkCode.setValidate_code(model.getValidate_code());
		smsService.checkValidateCode(checkCode);
		/**开始生成会员信息*/
		MSMembersSet memberSet=new MSMembersSet();//生成会员基本信息
		Date date = new Date();
		String memid=UUID.randomUUID().toString();
		memberSet.setMemId(memid);
		memberSet.setMemCreatedBy(memid);
		memberSet.setMemLoginName(SysParamsConst.DEFAULT_LOGIN_NAME_PREFIX+model.getPhone()+String.valueOf(Constants.CONSTANT_INT_ZERO));
		memberSet.setMemLoginNameIsdefaultIschanged(open_default_login_name?"0_1":"1_0");
		memberSet.setMemOldPhone(model.getPhone());
		memberSet.setMemPhone(model.getPhone());
		memberSet.setMemLoginPwd(model.getPass_word());
		memberSet.setMemNickName(model.getPhone());
		memberSet.setMemIsAllActivated(true);
		memberSet.setMemCreatedDate(date);
		memberSet.setMemCreatedCategory(1);
		memberSet.setDictMemStatus(SysEncrypParamsConst.MEMBER_STATUS_OK);
		memberSet.setMemParentId(shareManInfo.getMemId());
		memberSet.setMemSignSource(String.valueOf(model.getSource()));
		switch(model.getRole_type()){
		case "1":memberSet.setMemIsAllowShop(SysEncrypParamsConst.MEMBERKAIDIAN_YES);
		case "2":memberSet.setMemIsAllowShop(SysEncrypParamsConst.MEMBERKAIDIAN_NO);
		}
		memberSet.setMemBasicAccountTotal(String.valueOf(Constants.CONSTANT_INT_ZERO));
		memberSet.setMemBasicAccountTotal(String.valueOf(Constants.CONSTANT_INT_ZERO));
		memberSet.setMemParentIsdefaultIschanged(open_default_share_man?"0_1":"1_0");
		baseDao.insert(memberSet,"MSMembersMapper.insertMsMember");
		
		MemberAddressesSet memberAddressesSet = new MemberAddressesSet();//生成会员地址信息
		memberAddressesSet.setMemaId(UUID.randomUUID().toString());
		memberAddressesSet.setMemId(memid);
		memberAddressesSet.setMemaStatus(String.valueOf(Constants.CONSTANT_INT_ZERO));
		baseDao.insert(memberAddressesSet,"MSMemberAddressesMapper.addMemberAddressInfo");
		
		MSAccount msAccount = new MSAccount();//生成会员余额账户信息
		msAccount.setId(UUID.randomUUID().toString());
		msAccount.setMemId(memid);
		msAccount.setType(SysParamsConst.ACCOUNT_TYPE_MONEY);
		msAccount.setBalance(String.valueOf(Constants.CONSTANT_INT_ZERO));
		msAccount.setFreezeBalance(String.valueOf(Constants.CONSTANT_INT_ZERO));
		msAccount.setCreateDate(new Date());
		msAccount.setUpdateDate(new Date());
		baseDao.insert(msAccount,"MSMembersMapper.insertMsAccount");
		
		/*MSMemberCertificate mc = new MSMemberCertificate();//生成会员证件信息
		mc.setMcerId(UUID.randomUUID().toString());
		mc.setMemId(memid);
		mc.setDictMcerId(SysEncrypParamsConst.CERTIFICATE_SFZ);
		mc.setMcStatus(SysEncrypParamsConst.HUIYUANRENZHEN_WRZ);
		baseDao.insert(null,"");*/
		
		/*MSMemberRole mro = new MSMemberRole();//生成会员角色信息
		mro.setMemId(memid);
		mro.setRoleId(SysEncrypParamsConst.PUTONGHUIYUAN);
		baseDao.insert(null,"");*/
		
		//更新父类字符串（获取粉丝明细会用到，后期数据库表结构改造后此处需修正）
	
		/**增加积分并写入积分流水*/
		userInfoService.updateCurrentPointByMemId(memid,String.valueOf(Constants.CONSTANT_INT_ZERO),SysParamsConst.MD1GW_REGISTER_ADD_POINTS);
		String orderId=SysParamsConst.DEFAULT_ORDERID_PREFIX+ model.getLogin_name()+SysParamsConst.ADD_SYMBOL+String.valueOf(System.currentTimeMillis()/1000L);
		insertConsumePointDetail(memid,orderId,String.valueOf(model.getSource()),SysParamsConst.MD1GW_REGISTER_ADD_POINTS,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,model.getPhone(),SysEncrypParamsConst.POINTS_OPERATOR_TYPE_ZCZS);
		if (!open_default_share_man) {//如果推荐人非系统默认
			userInfoService.updateCurrentPointByMemId(shareManInfo.getMemId(),shareManInfo.getMemBasicAccountTotalQuantity(),SysParamsConst.MD1GW_REGISTER_ADD_POINTS);//一级分享人增加100积分
			orderId=SysParamsConst.DEFAULT_ORDERID_PREFIX+ model.getShare_man()+SysParamsConst.ADD_SYMBOL+String.valueOf(System.currentTimeMillis()/1000L);
			insertConsumePointDetail(shareManInfo.getMemId(),orderId,String.valueOf(model.getSource()),SysParamsConst.MD1GW_REGISTER_ADD_POINTS,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,model.getPhone(),SysEncrypParamsConst.POINTS_OPERATOR_TYPE_YQZCZS);
		}
		
		String redisToken=ToolsUtil.createToken(model.getPhone(),tokenKey);//生成token
		RedisTemplate.getJedisInstance().execSetexToCache(redisToken,Constants.REDIS_ONEMONTH,memid);//把token存储到redis，并设置失效时间一个月
		RedisTemplate.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH,redisToken);//临时代码，兼容旧会员系统
		Map<String, Object> mapData=new HashMap<>();
		mapData.put("token",redisToken);
		resBodyData.setData(mapData);
		
		/**发送注册成功短信*/
		RequestSendSms requestSendSms=new RequestSendSms();
		requestSendSms.setPhone(model.getPhone());
		requestSendSms.setTemplateId(SmsTemplateIDConst.getSmsTemplate(SmsTemplateIDConst.REGIST_SCAN_CODE_SUCCESS));
		requestSendSms.setParams(model.getPhone());
		smsService.sendSms(requestSendSms);
		/**发送分享人赠送积分短信...*/
		requestSendSms.setPhone(shareManInfo.getMemPhone());
		requestSendSms.setTemplateId(SmsTemplateIDConst.getSmsTemplate(SmsTemplateIDConst.GIVE_POINT));
		requestSendSms.setParams(shareManInfo.getMemPhone());
		smsService.sendSms(requestSendSms);
		return resBodyData;
	}
	
	/**增加积分明细
	 * @throws MdSysException */
	private final void insertConsumePointDetail(String memid,String orderid,String ordersource,String consumepoint,String balance,String phone,String operatetype) throws MdSysException
	{
		Date nowDate = new Date(System.currentTimeMillis());
		MSConsumePointsDetail consumePointsDetail=new MSConsumePointsDetail();
		consumePointsDetail.setMcpId(UUID.randomUUID().toString());
		consumePointsDetail.setMemId(memid);
		consumePointsDetail.setMcpOrderId(orderid);
		consumePointsDetail.setMcpOrderSource(SerialStringUtil.getDictOrderSource(ordersource));
		consumePointsDetail.setMcpIncome(consumepoint);
		consumePointsDetail.setMcpExpenditure(String.valueOf(Constants.CONSTANT_INT_ZERO));
		consumePointsDetail.setMcpBalance(balance);
		consumePointsDetail.setMcpOperatorType(operatetype);
		consumePointsDetail.setMcpRemark(SerialStringUtil.getPointsRemark(operatetype,phone));
		consumePointsDetail.setMcpCreatedBy(memid);
		consumePointsDetail.setMcpUpdatedBy(memid);
		consumePointsDetail.setMcpCreatedDate(nowDate);
		consumePointsDetail.setMcpUpdatedDate(nowDate);
		baseDao.insert(consumePointsDetail,"MSConsumePointsDetailMapper.saveConsumePointsDetails");
	}
	
	/**
	 * 设置推荐人和粉丝的关联关系
	 * @param msMembersSet 会员信息
	 */
	private void setShareMenAndFunsRelation(MSMembersSet msMembersSet,MSMembersGet shareManInfo){
		// 定义一个集合，存放所有需要变更的会员
		ArrayList<MSMembersGet> groupMems = new ArrayList<MSMembersGet>();
		// 首先获取到注册会员的直接父节点A
		MSMembersGet parentMember = shareManInfo;
		// 给直接父节点A设置所有孩子
		String memParentValue2 = parentMember.getMemParentValue2();
		if(!StringUtil.isEmptyByString(memParentValue2)){
			parentMember.setMemParentValue2(msMembersSet.getMemId() + "," + memParentValue2);
		}else {
			parentMember.setMemParentValue2(msMembersSet.getMemId());
		}
		// 给直接父节点A增加亲儿子
		String memParentValue3 = parentMember.getMemParentValue3();
		if(!StringUtil.isEmptyByString(memParentValue3)){
			parentMember.setMemParentValue3(msMembersSet.getMemId() + "," + memParentValue3);
		}else {
			parentMember.setMemParentValue3(msMembersSet.getMemId());
		}
		groupMems.add(parentMember);
		
		// 再拿到根据父节点A 的parentValue1 拿到A 的所有父节点
		String parentValue1 = parentMember.getMemParentValue1();
		if(!StringUtil.isEmptyByString(parentValue1)){
			if(parentValue1.trim().endsWith(",")){
				parentValue1 = parentValue1.substring(0, parentValue1.length()-1);
			}
			String[] allParentId = parentValue1.split(",");
			
			// 给注册会员增加ParentValue1 的值
			msMembersSet.setMemParentValue1(shareManInfo.getMemId() + ","+parentValue1);
			// 给注册会员增加层级数
			msMembersSet.setMemGroupLevel(String.valueOf(allParentId.length + 2));
			
			// 更改A 的所有父节点的ParentValue2
			for (String everyParentId : allParentId) {
				if(!StringUtil.isEmptyByString(everyParentId)){
					Map<String,Object> mapCondition=new HashMap<>();
					mapCondition.put("memId",everyParentId);
					MSMembersGet everyParent = baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
					if(everyParent != null){
						String value2 = everyParent.getMemParentValue2();
						if(!StringUtil.isEmptyByString(value2)){
							everyParent.setMemParentValue2(msMembersSet.getMemId() + "," + value2);
						}else{
							everyParent.setMemParentValue2(msMembersSet.getMemId());
						}
						groupMems.add(everyParent);
					}
				}
			}
		}else{
			// 给注册会员增加ParentValue1 的值
			msMembersSet.setMemParentValue1(shareManInfo.getMemId());
			// 给注册会员增加层级数
			msMembersSet.setMemGroupLevel("2");
		}
		MSMembersGet newModel=new MSMembersGet();
		BeanUtils.copyProperties(msMembersSet,newModel);
		groupMems.add(newModel);
		for(MSMembersGet model:groupMems){
			baseDao.update(model,"MSMembersMapper.updateParentValue");
		}
	}

}
