package com.meiduimall.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.Constants;
import com.meiduimall.constant.ApiStatusConst;
import com.meiduimall.constant.OauthConst;
import com.meiduimall.constant.SysEncrypConst;
import com.meiduimall.constant.SysParaNameConst;
import com.meiduimall.dao.BaseDao;
import com.meiduimall.dto.MemberBasicInfoDTO;
import com.meiduimall.model.*;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.MembersBasicOpService;
import com.meiduimall.service.ShortMessageService;
import com.meiduimall.util.DESC;
import com.meiduimall.util.DoubleCalculate;
import com.meiduimall.util.HttpClientUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.MD5Util;
import com.meiduimall.util.SerialStringUtil;
import com.meiduimall.util.StringUtil;
import com.meiduimall.util.SystemConfig;
import com.meiduimall.util.ToolsUtil;;
/**
 * 用户基本的操作实现类
 * @author chencong
 *
 */
@Component
public class MembersBasicOpServiceImpl implements MembersBasicOpService {

	@Autowired
	BaseDao baseDao;
	
	@Autowired
	ShortMessageService shortMessageService;
	
	@Override
	public Map<String, Object> handlesignout(JSONObject jsonObject) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();//返回结果
		String userid=jsonObject.getString("user_id");
		String memid=  baseDao.selectOne(DESC.encryption(userid),"MemberMapper.getMemIdByUserId");
		//如果不存在这个memid
		if(StringUtil.isEmptyByString(memid)){
			map.put("status_code", ApiStatusConst.USER_NOT_EXIST);
			map.put("result_msg", ApiStatusConst.USER_NOT_EXIST_C);
			return map;
		}
		try {
			JedisUtil rt=JedisUtil.getJedisInstance();
			if(rt.execExistsFromCache(memid))
			{
				String token=rt.execGetFromCache(memid);
				if(rt.execExistsFromCache(token))
				{
					if(rt.execDelToCache(memid))
					{
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
			String memid=null;
			String userid=null;
			String token=null;
			if(jsonObject.containsKey("user_id"))
			{
				userid=jsonObject.getString("user_id");
				memid=  baseDao.selectOne(DESC.encryption(userid),"MemberMapper.getMemIdByUserId");
				//如果不存在这个memid
				if(StringUtil.isEmptyByString(memid)){
					map.put("status_code", ApiStatusConst.USER_NOT_EXIST);
					map.put("result_msg", ApiStatusConst.USER_NOT_EXIST_C);
					return map;
				}	
			}
			if(jsonObject.containsKey("token"))
			{
				token=jsonObject.getString("token");
			}
			JedisUtil rt=JedisUtil.getJedisInstance();
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
			/**如果是put操作**/
			if("2".equals(type))
			{
				token=createToken(userid,System.currentTimeMillis());
				//把token存储到redis
				JedisUtil.getJedisInstance().execSetexToCache(token,Constants.REDIS_ONEMONTH*1,memid);
				//临时代码，兼容旧会员系统
				JedisUtil.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH*1,token);
				map.put("status_code","0");
				map.put("result_msg","success");
				Map<String, Object> result_map=new HashMap<>();
				result_map.put("token",token);
				result_map.put("memId",memid);
				map.put("result",JSON.toJSON(result_map));
			}
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
	
	/**
	 * 用户登录
	 */
	public Map<String, Object> login(JSONObject jsonObject) {
		Map<String, Object> map=new HashMap<String, Object>();
		
		//下面一行是临时代码，适配老APP
		map.put("bdhsign","memberdologin");
		
		String userid=null;//userid
		String password=null;//密码
		try {
			/*验证user_id合法性*/
			/*if (jsonObject.containsKey("user_id")) {
				userid = jsonObject.getString("user_id");
				if (StringUtil.isEmptyByString(userid)
						|| userid.length() > 100||
						(StringUtil.isEmailToRegex(userid)&&
						 (StringUtil.stringByFilter(userid).length() == userid.length()))
						) {
					map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
					map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);
					return map;
				}
			}*/
			
			//临时代码，适配老的APP
			if (jsonObject.containsKey("user_name")) {
				userid = jsonObject.getString("user_name");
				if (StringUtil.isEmptyByString(userid)
						|| userid.length() > 100||
						(StringUtil.isEmailToRegex(userid)&&
						 (StringUtil.stringByFilter(userid).length() == userid.length()))
						) {
					map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
					map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);
					return map;
				}
			}
			
			/*验证密码合法性*/
			/*if (jsonObject.containsKey("pass_word")) {
				password = jsonObject.getString("pass_word");
				if (password.length() != 32 && password.length() != 65) {
					map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PASSWORD_ERROR);
					map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PASSWORD_ERROR_C);
					return map;
				}
			}*/
			
			//临时代码，适配老的APP
			if (jsonObject.containsKey("password")) {
			password = jsonObject.getString("password");
			if (password.length() != 32 && password.length() != 65) {
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PASSWORD_ERROR);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PASSWORD_ERROR_C);
				return map;
			}
		}
			
			/*判断是否存在这个会员*/
			String   memid=  baseDao.selectOne(DESC.encryption(userid),"MemberMapper.getMemIdByUserId");
			if(StringUtil.isEmptyByString(memid)){
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USER_NOT_EXIST);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USER_NOT_EXIST_C);
				return map;
			}
			/*判断这个用户是否由于登录失败次数过多被锁定*/
			int maxCount = Integer.valueOf(SystemConfig.getInstance().configMap.get("Max_Login_Error_Counts"));
			//判断用户是否登录失败过
			StringBuffer lfkey = new StringBuffer();//生成存储会员登录失败记录的key
			lfkey.append(SysParaNameConst.REDISKEY_LoginFail);
			lfkey.append(OauthConst.CONNECTION_SYMBOL);
			lfkey.append(userid);
			//如果登录失败过
			if(JedisUtil.getJedisInstance().execExistsFromCache(lfkey.toString()))
			{
				//判断是否达到登录失败最大限制次数
				String count=JedisUtil.getJedisInstance().execGetFromCache(lfkey.toString());
				if(Integer.valueOf(count)>=maxCount)
				{
					map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.MEMBER_FORBIDDEN);
					map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.MEMBER_FORBIDDEN_C);
					return map;
				}
			}
			/*根据userid和密码查询会员信息*/
			String[] ps=password.split("-");
			Map<String, Object> upmap=new HashMap<>();
			upmap.put("userid",DESC.encryption(userid));
			//本部会员密码和美兑会员密码用"-"隔开，第一个是本部会员密码
			upmap.put("1gw_pwd",ps[0]);
			upmap.put("md_pwd",ps[0]);
			if(ps.length>2)
				upmap.put("md_pwd",ps[1]);
			MemberGet memberGet=baseDao.selectOne(upmap,"MemberMapper.getMemberByUserIdAndPassword");
			
			//初始化会员登录日志
			MSMemLoginLog ml = new MSMemLoginLog();
			ml.setMemId(memid);
			ml.setMllogAction(SysParaNameConst.LOGIN);
			ml.setMllogCreatedBy(memid);
			ml.setMllogCreatedDate(new Date());
			ml.setMllogId(UUID.randomUUID().toString());
			ml.setMllogIp(jsonObject.getString("ip"));
			ml.setMllogModule(SysParaNameConst.LOGIN_WB);
			ml.setMllogStatus(SysParaNameConst.LOGIN);
			
			if(memberGet!=null)
			{
				//如果该会员账号已被禁用
				if(memberGet.getDictMemStatus().equals(SysEncrypConst.MEMBER_FORBIDDEN_EN))
				{
					map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.MEMBER_FORBIDDEN);
					map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.MEMBER_FORBIDDEN_C);
					//保存登录失败日志，此操作不应影响上面的状态返回给客户端
					try {
						ml.setMllogContent("外部服务登录认证,当前用户" + userid + "被禁用,无法登录");
						ml.setMllogRemark("外部服务登录认证,当前用户" + userid + "被禁用,无法登录");
						baseDao.insert(ml,"MSMemLoginLogMapper.insertMemberLoginLog");
					} catch (Exception e) {
						Logger.error("用户%s登录日志保存失败",userid);
					}
					Logger.info("用户%s被禁用，无法登录！",userid);
					return map;
				}
				//生成token
				String token=createToken(userid,System.currentTimeMillis());
				//更新用户状态
				memberGet.setMemLicenseKey(token);
				if(null!=memberGet.getMemLoginTime())
				{
					//设置上一次登录时间
					memberGet.setPfLastLoginTime(memberGet.getMemLoginTime());
				}
				memberGet.setMemLoginTime(new Date());
				memberGet.setDictMemStatus(SysEncrypConst.MEMBER_STATUS_OK);
				int i=baseDao.update(memberGet,"MemberMapper.updateMemberStatusAfterLoginSuccess");
				
			/*	if(i>0)
				{
					//如果更新成功，就把token存储到redis，并设置失效时间三个月
					JedisUtil.getJedisInstance().execSetexToCache(token,Constants.REDIS_ONEMONTH*1,memid);
					//临时代码，兼容旧会员系统
					JedisUtil.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH*1,token);
				}*/
				
				//把token存储到redis，并设置失效时间三个月
				JedisUtil.getJedisInstance().execSetexToCache(token,Constants.REDIS_ONEMONTH*1,memid);
				//临时代码，兼容旧会员系统
				JedisUtil.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH*1,token);
				//设置返回结果
				map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SUCCESS);
				map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.SUCCESS_C);
				Map<String, Object> sonmap=new HashMap<>();
			/*	sonmap.put("user_id",memberGet.getMemLoginName());
				sonmap.put("memId",memberGet.getMemId());
				sonmap.put("token",token);
				if (memberGet.getMemIsAllowShop().equals(SysEncrypConst.MEMBERKAIDIAN_YES)) {
					sonmap.put("memberType", "1");//1表示商家登录，2表示普通会员登录
				}
				else if(memberGet.getMemIsAllowShop().equals(SysEncrypConst.MEMBERKAIDIAN_NO)) {
					sonmap.put("memberType", "2");
				}else{
					sonmap.put("memberType", "2");
				}*/
				
				//临时代码，适配老APP
				sonmap.put("user_name",memberGet.getMemLoginName());
				sonmap.put("nickname",memberGet.getMemNickName());
				sonmap.put("token",token);
				sonmap.put("portrait","");
				sonmap.put("ry_token","");
				map.put(SysParaNameConst.RESULT,sonmap);
				//保存登录失败日志，此操作不应影响上面的状态返回给客户端
				try {
					ml.setMllogContent("外部服务登录认证,当前用户" + userid + "登录成功");
					ml.setMllogRemark("外部服务登录认证,当前用户" + userid + "登录成功");
					baseDao.insert(ml,"MSMemLoginLogMapper.insertMemberLoginLog");
				} catch (Exception e) {
					Logger.error("用户%s登录日志保存失败",userid);
				}
				Logger.info("外部请求当前用户=" +userid+ "结束,登录成功");
				return map;
			}
			else
			{
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PASSWORD_OR_USERNAME_ERROR);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PASSWORD_OR_USERNAME_ERROR_C);
				//如果登录失败过
				if(JedisUtil.getJedisInstance().execExistsFromCache(lfkey.toString()))
				{
					//判断是否达到登录失败最大限制次数
					String count2=JedisUtil.getJedisInstance().execGetFromCache(lfkey.toString());
					int icount2=Integer.valueOf(count2);
					icount2++;
					//计算当前时间和0点0分还有多少秒，暂时60秒
					int second=60;
					//达到最大限制次数，凌晨解除锁定
					if(icount2>=maxCount)
					{					
						JedisUtil.getJedisInstance().execSetexToCache(lfkey.toString(),second,String.valueOf(count2));
						map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.MEMBER_FORBIDDEN);
						map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.MEMBER_FORBIDDEN_C);
						//保存登录失败日志，统计会员被锁定的次数，这两个操作不应影响上面的状态返回给客户端
						try {
							ml.setMllogContent("外部服务登录认证,当前用户" + userid + "被禁用,无法登录");
							ml.setMllogRemark("外部服务登录认证,当前用户" + userid + "被禁用,无法登录");
							baseDao.insert(ml,"MSMemLoginLogMapper.insertMemberLoginLog");
							//统计会员锁定次数
							if("0".equals(memberGet.getMemLockCount()))
							{
								memberGet.setMemLockCount(DESC.encryption("1",memberGet.getMemId()));
							}
							else
							{
								int newcount=Integer.valueOf(memberGet.getMemLockCount())+1;
								memberGet.setMemLockCount(DESC.encryption(String.valueOf(newcount),memberGet.getMemId()));
							}
							baseDao.update(memberGet,"MemberMapper.updateMemberLockStatusCount");
						} catch (Exception e) {
							Logger.error("用户%s登录日志保存或统计锁定次数失败",userid);
						}
						Logger.info("用户%s被禁用，无法登录！",userid);
						return map;
					}
					//如果没有达到，累加失败次数，但是到凌晨的时候会自动清除
					else
					{
						JedisUtil.getJedisInstance().execSetexToCache(lfkey.toString(),second,String.valueOf(count2));
					}
				}
				//如果没有登录失败过，就初始化登录失败次数为1,并设置失效时间为1天
				else
				{
					JedisUtil.getJedisInstance().execSetexToCache(lfkey.toString(),Constants.REDIS_ONEDAY,"1");
				}
				//保存登录失败日志，此操作不应影响上面的状态返回给客户端
				try {
					ml.setMllogContent("外部服务登录认证,当前用户" + userid + "用户名或密码输入错误,登录失败");
					ml.setMllogRemark("外部服务登录认证,当前用户" + userid + "用户名或密码输入错误，登录失败");
					baseDao.insert(ml,"MSMemLoginLogMapper.insertMemberLoginLog");
				} catch (Exception e) {
					Logger.error("用户%s登录日志保存失败",userid);
				}
				Logger.info("用户%s用户名或密码输入错误，登录失败",userid);
				return map;
			}
		} 
		catch (Exception e) {
			Logger.info("服务器错误%s",e.getMessage());
			map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SERVER_ERROR);
			map.put(SysParaNameConst.RESULT_MSG,e.getMessage());
		}
		return map;
	}
	
	/**用户 注册
	 * @throws Exception */
	public Map<String, Object> register(JSONObject jsonObject) {
		Map<String, Object> map=new HashMap<String, Object>();
		boolean open_Share=true;//是否开启分享，默认开启

		String ip = jsonObject.getString("ip");
		String phone = jsonObject.getString("phone");//用户手机号
		//如果没有登录名，就分配一个
		String user_id=null;
		if(jsonObject.containsKey("user_id"))
		{
			user_id=jsonObject.getString("user_id");
		}
		else
		{
			String username_01="";
			username_01 = "1gw_"+phone+"0";
			for (int i = 0; i < 10; i++) {
				if(i > 0){
					user_id = username_01+i;
				}else{
					user_id = username_01;
				}
			}
		}
		String valid_code = jsonObject.getString("valid_code");//验证码
		String pass_word = jsonObject.getString("pass_word");//MD5过的密码
		String share_man = jsonObject.getString("share_man");//推荐人user_id
		String sign_source = jsonObject.getString("sign_source");//注册来源
		String register_type = jsonObject.getString("register_type");//注册类型
		
		/**校验验证码格式*/
		if (StringUtil.isEmptyByString(valid_code) || 6 != valid_code.length()) {
			Logger.info("外部当前注册请求IP地址=" + ip + "结束,验证码错误");
			map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.VALIDATE_ERROR);
			map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.VALIDATE_ERROR_C);
			return map;
		}
		try {
			/**校验推荐人*/
			String share_id=null;//推荐人的memid
			if (!StringUtil.isEmptyByString(share_man)) {
				open_Share = false;
				if(user_id.equals(share_man))
				{
					Logger.info("当前注册请求IP地址=" + ip + "结束，推荐人不能是自己");
					map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SHARE_MAN_CANNOT_ITSELF);
					map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.SHARE_MAN_CANNOT_ITSELF_C);
					return map;
				}
				share_id = validShareMan(DESC.encryption(share_man));
				if (StringUtil.isEmptyByString(share_id)) {
					Logger.info("当前注册请求IP地址=" + ip + "结束，推荐人不存在");
					map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SHARE_MAN_NOT_EXIST);
					map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.SHARE_MAN_NOT_EXIST_C);
					return map;
				}
			} else {
				open_Share = true;
				share_man = SysParaNameConst.MD1GW_DEFAULT_SHARE_LOGIN_NAME;
				share_id = validShareMan(DESC.encryption(share_man));//系统默认推荐人
				Logger.info("外部当前注册请求IP地址=" + ip + "，系统默认推荐人为："+share_man);
			}
			/**校验注册验证码*/
			String rk=SysParaNameConst.REDISKEY_REGISTER_VALIDATE_CODE+phone;//注册验证码的key
			if(JedisUtil.getJedisInstance().execExistsFromCache(rk))
			{
				String code=JedisUtil.getJedisInstance().execGetFromCache(rk);
				if(!code.equals(valid_code))
				{
					Logger.info("当前注册请求IP地址=" + ip + "结束，验证码错误");
					map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.VALIDATE_ERROR);
					map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.VALIDATE_ERROR_C);
					return map;
				}
			}
			else
			{
				Logger.info("当前注册请求IP地址=" + ip + "结束，验证码不存在");
				map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.VALIDATE_ERROR);
				map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.VALIDATE_ERROR_C);
				return map;
			}
			/**校验手机号*/
			if(validUserId(DESC.encryption(phone)))
			{
				Logger.info("当前注册请求IP地址=" + ip + "结束，该手机号已经被注册");
				map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.PHONE_ALREADY_REGISTED);
				map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.PHONE_ALREADY_REGISTED_C);
				return map;
			}
			/**校验用户名*/
			if(validUserId(DESC.encryption(user_id)))
			{
				Logger.info("当前注册请求IP地址=" + ip + "结束，该用户名已经被注册");
				map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.LOGINNAME_ALREADY_REGISTED);
				map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.LOGINNAME_ALREADY_REGISTED_C);
				return map;
			}
			/**注册流程开始*/
			//1、生成会员基本信息
			MemberSet memberSet=new MemberSet();
			Date date = new Date();
			String memid=UUID.randomUUID().toString();
			memberSet.setMemId(memid);
			memberSet.setMemCreatedBy(memid);
			memberSet.setMemLoginName(user_id);
			memberSet.setMemLoginNameIsdefaultIschanged(jsonObject.containsKey("user_id")?"0_1":"1_0");//是否分配默认登录名
			memberSet.setMemOldPhone(phone);
			memberSet.setMemPhone(phone);
			memberSet.setMemLoginPwd(pass_word);
			memberSet.setMemNickName(phone);
			memberSet.setMemCreatedDate(date);
			memberSet.setMemCreatedCategory(1);// 创建
			memberSet.setDictMemStatus(SysEncrypConst.MEMBER_STATUS_OK);//会员账号状态
			memberSet.setMemParentId(share_id);//推荐人memid
			if ("O2O".equals(sign_source)) {
				memberSet.setMemSignSource("1");
			}
			if ("1gw".equals(sign_source)) {
				memberSet.setMemSignSource("3");
			}
			if ("1".equals(register_type)) {//商家
				memberSet.setMemIsAllowShop(SysEncrypConst.MEMBERKAIDIAN_YES);
			}
			if ("2".equals(register_type)) {//普通会员
				memberSet.setMemIsAllowShop(SysEncrypConst.MEMBERKAIDIAN_NO);
			}
			memberSet.setMemBasicAccountTotal("0");//基本账户总额
			memberSet.setMemIsAllActivated(true);//是否所有绑定
			memberSet.setMemLevelDictId(SysEncrypConst.MOREN_HUIYUAN_DENGJI);//会员初始等级
			memberSet.setMemIntegralConsumeCoupon("0");//消费券余额
			memberSet.setMemPreorderRight(SysEncrypConst.HUIYUANYUGOUQUAN_YES);//预购权
			if(open_Share){
				memberSet.setMemLoginNameIsdefaultIschanged("1_0");//是否分配默认推荐人
			}else{
				memberSet.setMemLoginNameIsdefaultIschanged("0_1");	
			}
			//2、生成会员地址信息
			MemberAddressesSet ms = new MemberAddressesSet();
			ms.setMemaId(UUID.randomUUID().toString());
			ms.setMemId(memid);
			ms.setMemaStatus("0");
			//3、生成会员账户信息
			MSMemberBasicAccount mb = new MSMemberBasicAccount();
			mb.setMbaId(UUID.randomUUID().toString());
			mb.setMemId(memid);
			mb.setMbaTotalQuantity("0");//基本账户总额
			mb.setMbaCreatedDate(new Date());
			//4、生成会员证件信息
			MSMemberCertificate mc = new MSMemberCertificate();
			mc.setMcerId(UUID.randomUUID().toString());
			mc.setMemId(memid);
			mc.setDictMcerId(SysEncrypConst.CERTIFICATE_SFZ);//会员证件类型
			mc.setMcStatus(SysEncrypConst.HUIYUANRENZHEN_WRZ);//认证状态，默认未认证
			//5、生成会员角色信息
			MSMemberRole mro = new MSMemberRole();
			mro.setMemId(memid);
			mro.setRoleId(SysEncrypConst.PUTONGHUIYUAN);
			//插入以上信息到数据库
			MSMemLoginLog ml = new MSMemLoginLog();
			if(insertMembserInfo(memberSet, ms, mb, mc, mro))
			{
				// 更新父类字符串 获取粉丝明细会用到
				/*baseDao.update(memberSet,"");*/
				//生成会员登录日志
				ml.setMemId(memid);
				ml.setMllogAction(SysParaNameConst.REGISERTER);
				ml.setMllogContent("外部接口" + phone + "注册成功");
				ml.setMllogCreatedBy(memid);
				ml.setMllogCreatedDate(new Date());
				ml.setMllogId(UUID.randomUUID().toString());
				ml.setMllogIp(jsonObject.getString("ip"));
				ml.setMllogModule(SysParaNameConst.LOGIN_WB);
				ml.setMllogRemark("外部接口" + phone + "注册成功");
				ml.setMllogStatus(SysParaNameConst.REGISERTER);
			
			/**发送注册成功短信*/
			
			/**新注册成功用户增加100积分*/
			MemberBasicInfoDTO member = baseDao.selectOne(memid,"MemberMapper.getMemberInfoByMemId");
			if(member != null){
				//订单编号
				String orderId = "1GW+" + member.getUser_id() + "+" + String.valueOf(System.currentTimeMillis()/1000L);
				//注册人增加100积分
				int i=addMDConsumePoints(member,SysParaNameConst.MD1GW_REGISTER_ADD_POINTS);
				if(i>0)
				{
					//写入增加积分日志
					addConsumePointDetail(memid,orderId,sign_source,SysParaNameConst.MD1GW_REGISTER_ADD_POINTS,SysParaNameConst.MD1GW_REGISTER_ADD_POINTS,phone,SysEncrypConst.POINTS_OPERATOR_TYPE_ZCZS);
					//推荐人增加100积分
					if((!StringUtil.isEmptyByString(share_id))){
						//如果推荐人不是美兑
						if (!SysParaNameConst.MD1GW_DEFAULT_SHARE_LOGIN_NAME.equals(share_man)) {
							MemberBasicInfoDTO member_shareman = baseDao.selectOne(share_id,"MemberMapper.getMemberInfoByMemId");
							//订单编号
							orderId = "1GW+" +share_man+ "+" + String.valueOf(System.currentTimeMillis()/1000L);
							//一级分享人增加100积分
							int i2=addMDConsumePoints(member_shareman,SysParaNameConst.MD1GW_REGISTER_ADD_POINTS);
							//写入增加积分日志
							addConsumePointDetail(share_id,orderId,sign_source,SysParaNameConst.MD1GW_REGISTER_ADD_POINTS,SysParaNameConst.MD1GW_REGISTER_ADD_POINTS,phone,SysEncrypConst.POINTS_OPERATOR_TYPE_YQZCZS);
							//发送分享人赠送积分短信
						}
					}
				}
			}
			//写入会员登录日志
			baseDao.insert(ml,"MSMemLoginLogMapper.insertMemberLoginLog");
			}
			
		} catch (Exception e) {
			Logger.error("注册请求发生错误："+e.getMessage());
			map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SERVER_ERROR);
			map.put(SysParaNameConst.RESULT_MSG,e.getMessage());
		}
		return map;
	}

	/**
	 * 用户退出
	 */
	public Map<String, Object> exit(JSONObject jsonObject) {
		return null;
	}

	/**
	 * APP打开时检查token是否有效
	 */
	@Override
	public Map<String, Object> checktoken(JSONObject jsonObject) {
		Map<String, Object> map=new HashMap<String, Object>();
		String token=null;//token
		try {
			if(!jsonObject.containsKey(OauthConst.TOKEN))
			{
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.TOKEN_ERROR);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.TOKEN_ERROR_C);
				return map;
			}
			//如果不存在token或者对应的值不对
			JedisUtil rt=JedisUtil.getJedisInstance();
			token=jsonObject.getString(OauthConst.TOKEN);
			if(!rt.execExistsFromCache(token))
			{
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.TOKEN_ERROR);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.TOKEN_ERROR_C);
				return map;
			}
			String memid=rt.execGetFromCache(token);
			if(StringUtil.isEmptyByString(memid))
			{
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.TOKEN_ERROR);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.TOKEN_ERROR_C);
				return map;
			}
			MemberBasicInfoDTO member=baseDao.selectOne(memid,"MemberMapper.getMemberInfoByMemId");
			if(member==null)
			{
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USER_NOT_EXIST);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USER_NOT_EXIST_C);
				return map;
			}
			Map<String, Object> result_map=new HashMap<>();
			result_map.put("user_id",member.getUser_id());
			map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.SUCCESS);
			map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.SUCCESS_C);
			map.put(SysParaNameConst.RESULT,result_map); 
		} catch (Exception e) {
			Logger.info("服务器错误%s",e.getMessage());
			map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SERVER_ERROR);
			map.put(SysParaNameConst.RESULT_MSG,e.getMessage());
		}
		return map;
	}
	
	/**
	 * 生成短信验证码
	 * @throws Exception 
	 */
	@Override
	public Map<String, Object> createValidateCode(JSONObject jsonObject) throws Exception {
		Map<String, Object> map=new HashMap<>();
		try {
			if(!jsonObject.containsKey("phone"))
			{
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PHONE_ERROR);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PHONE_ERROR_C);
				return map;
			}
			String type=jsonObject.getString("type");
			String url=SystemConfig.getInstance().configMap.get("Push_Service_Address");
			/*String code=ToolsUtil.getValidateCode();*/
			String code="123456";
			jsonObject.put(OauthConst.CLIENT_ID,"");//这个clientID是gateway分配给接入层的，不是接入层分配给前端APP的
			jsonObject.put(OauthConst.TIMESATAMP,System.currentTimeMillis());
			jsonObject.put("content",code);
			String sign=ToolsUtil.getSign(jsonObject);//注意此方法需要修改，缺参数
			jsonObject.put(OauthConst.SIGN,sign);
			Logger.info("请求获取验证码接口："+url+" 参数："+jsonObject.toString());
			/*String result=shortMessageService.getValidateCode(url,jsonObject.toString());
			JSONObject jresult=(JSONObject)JSONObject.parse(result);
			//上面需要判断短信是否发送成功
			if("0".contains(jresult.getString(SysParaNameConst.STATUS_CODE)))
			{
				switch (type) {
				case "1":
					JedisUtil.getJedisInstance().execSetexToCache(SysParaNameConst.REDISKEY_REGISTER_VALIDATE_CODE+jsonObject.containsKey("phone"),Constants.REDIS_ONEMONTH*3,code);
					break;

				default:
					break;
				}
			}*/
			switch (type) {
			case "1"://注册
				JedisUtil.getJedisInstance().execSetexToCache(SysParaNameConst.REDISKEY_REGISTER_VALIDATE_CODE+jsonObject.getString("phone"),Constants.REDIS_HALFMINUTE*2,code);
				break;
			case "6"://修改手机号
				JedisUtil.getJedisInstance().execSetexToCache(SysParaNameConst.UPDATE_PHONE_VALIDATE_CODE+jsonObject.getString("phone"),Constants.REDIS_HALFMINUTE*2,code);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			throw e;
		}
		map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.SUCCESS);
		map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.SUCCESS_C);
		
		/*if(!jsonObject.containsKey("phone"))
		{
			map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PHONE_ERROR);
			map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PHONE_ERROR_C);
			return map;
		}
		TaoBaoApiHelper helper = new TaoBaoApiHelper("http://gw.api.taobao.com/router/rest","23610521","1b6e3af2b15c01c4239e1d0f12481992");
		TaobaoHashMap map2 = new TaobaoHashMap();
		map.put("sms_type", CodeHelper.SMS_TYPE);
		map.put("sms_free_sign_name", CodeHelper.SMS_FREE_SIGN_NAME);
		map.put("sms_template_code", "SMS_13011423");
		map.put("rec_num",jsonObject.getString("phone"));																																															
		map.put("method",  CodeHelper.SEND_SMS_SERVICE_URL);
		map.put("sms_param", String.format(CodeHelper.CODE_PRODUCT, ToolsUtil.getValidateCode(),CodeHelper.SMS_FREE_SIGN_NAME));
		TaobaoResponse result=helper.execute(map2);*/
		return map;
	}
	
	/**校验是否有推荐人*/
	private final String  validShareMan(String share_man_id) throws Exception
	{
		String memid=null;
		try {
			memid=baseDao.selectOne(share_man_id,"MemberMapper.getMemIdByUserId");
		} catch (Exception e) {
			throw e;
		}
		return memid;
	}
	
	/**校验user_id是否已经被注册*/
	private final boolean validUserId(String phone) throws Exception
	{
		String memid=null;
		String pfid=null;
		try {
			memid=baseDao.selectOne(phone,"MemberMapper.getMemIdByUserId");//根据手机号查询会员表是否存在记录
			pfid=baseDao.selectOne(phone,"MSPlatFormsMapper.getPfRecordByUserId");//根据手机号查询是否是平台人员注册
			if(StringUtil.isEmptyByString(memid)&&StringUtil.isEmptyByString(pfid))
				return true;
		} catch (Exception e) {
			throw e;
		}
		return false;
	}
	
	/**插入会员基本信息以及相关联的信息*/
	@Transactional
	private final boolean insertMembserInfo(MemberSet memberSet,MemberAddressesSet memberAddressesSet,MSMemberBasicAccount account,MSMemberCertificate certificate,MSMemberRole role) throws Exception
	{
		try {
			int i1=baseDao.insert(memberSet,"");
			int i2=baseDao.insert(memberAddressesSet,"");
			int i3=baseDao.insert(account,"");
			int i4=baseDao.insert(certificate,"");
			int i5=baseDao.insert(role,"");
			if(i1>0&&i2>0&&i3>0&&i4>0&&i5>0)
			{
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}
	
	/**修改会员基本账户总额*/
	private final int addMDConsumePoints(MemberBasicInfoDTO member, String consumePoints) throws Exception {
		int returnBool =0;
		try {
			//增加基本账户总额
			double addAtq = DoubleCalculate.add(
					Double.valueOf(member.getTotalpoints()),
					Double.valueOf(consumePoints));
			//修改会员基本账户总额
			Map<String,Object> map=new HashMap<>();
			map.put("memId",member.getMemId());
			map.put("addAtq",Double.toString(addAtq)); 
			returnBool =baseDao.update(map,"MemberMapper.updateAccountPointsByMemId");
		} catch (Exception e) {
			throw e;
		}
		return returnBool;
	}
	
	/**增加积分明细*/
	private final int addConsumePointDetail(String memid,String orderid,String ordersource,String consumepoint,String balance,String phone,String operatetype) throws Exception
	{
		int i=0;
		try {
			Date nowDate = new Date(System.currentTimeMillis());
			MSConsumePointsDetail consumePointsDetail=new MSConsumePointsDetail();
			consumePointsDetail.setMcpId(UUID.randomUUID().toString());
			consumePointsDetail.setMemId(memid);
			consumePointsDetail.setMcpOrderId(orderid);
			consumePointsDetail.setMcpOrderSource(SerialStringUtil.getDictOrderSource(ordersource));
			consumePointsDetail.setMcpIncome("0");
			consumePointsDetail.setMcpExpenditure(consumepoint);
			consumePointsDetail.setMcpBalance(balance);
			consumePointsDetail.setMcpOperatorType(operatetype);
			consumePointsDetail.setMcpRemark(SerialStringUtil.getPointsRemark(operatetype,phone));
			consumePointsDetail.setMcpCreatedBy(memid);
			consumePointsDetail.setMcpUpdatedBy(memid);
			consumePointsDetail.setMcpCreatedDate(nowDate);
			consumePointsDetail.setMcpUpdatedDate(nowDate);
			i=baseDao.insert(consumePointsDetail,"MSConsumePointsDetailMapper.saveConsumePointsDetails");
		} catch (Exception e) {
			throw e;
		}
		return i;
	}
	
	
	/**
	 * 根据user_id和时间戳生成token
	 * @param username user_id
	 * @param timesatamp 时间戳
	 * @return
	 */
	private static String createToken(String username, Long timesatamp) {
		StringBuffer buffer = new StringBuffer();
		String token=null;
		try {
			buffer.append(username);
			buffer.append(OauthConst.CONNECTION_SYMBOL);
			buffer.append(timesatamp);
			token = MD5Util.MD5EncryptBy32(buffer.toString());
			Logger.info("token创建成功!");
		} catch (Exception e) {
			Logger.error("token创建失败: %s", e.getMessage());
		}
		return token;
	}


}
