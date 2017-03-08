package com.meiduimall.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.AccountOperateConst;
import com.meiduimall.constant.ApiStatusConst;
import com.meiduimall.constant.SysParaNameConst;
import com.meiduimall.dao.BaseDao;
import com.meiduimall.dto.MemberBasicInfoDTO;
import com.meiduimall.model.MSBusinessConfig;
import com.meiduimall.model.MSMemberAddresses;
import com.meiduimall.model.MemberAddressesGet;
import com.meiduimall.model.MemberAddressesSet;
import com.meiduimall.model.MemberGet;
import com.meiduimall.model.MemberSet;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.MembersBasicInforOpService;
import com.meiduimall.util.DESC;
import com.meiduimall.util.DateUtil;
import com.meiduimall.util.DoubleCalculate;
import com.meiduimall.util.Logger;
import com.meiduimall.util.StringUtil;

/**
 * 用户基本信息操作接口实现类
 * @author chencong
 *
 */
@Service
public class MembersBasicInforOpServiceImpl implements MembersBasicInforOpService {
	
	@Autowired
	BaseDao baseDao;

	/**
	 * 根据userid查询用户基本信息，包括积分
	 */
	@Override
	public Map<String, Object> getmemberbasicinfo(JSONObject jsonObject) {
		Map<String, Object> map=new HashMap<String, Object>();
		String mem_id=null;
		try {
			
			/*验证user_id合法性*/
			if (jsonObject.containsKey("mem_id")) {
				mem_id = jsonObject.getString("mem_id");
				if (StringUtil.isEmptyByString(mem_id))
					{
					map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USERNAME_ERROR);
					map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USERNAME_ERROR_C);
					return map;
				}
			}
			
			/*根据userid查询会员信息*/
			MemberBasicInfoDTO mebmapping=baseDao.selectOne(mem_id,"MemberMapper.getMemberInfoByMemId");
			/*判断是否存在这个会员*/
			if(mebmapping==null)
			{
				map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.USER_NOT_EXIST);
				map.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.USER_NOT_EXIST_C);
				return map;
			}
			
			/*如果存在这个会员，取出memid和当前积分*/
			String memid=mebmapping.getMemId();
			String totalpoints=mebmapping.getTotalpoints();
			
			/*根据memid查询会员详细地址*/
			MSMemberAddresses addresses=baseDao.selectOne(memid,"MSMemberAddressesMapper.getMemberAddressByMemId");
			
			/*根据memid和账户类型查询会员账户余额*/
			Map<String,Object> aMap=new HashMap<>();
			aMap.put("memid",memid);
			aMap.put("type",AccountOperateConst.ACCOUNT_TYPE_MONEY);
			Double balance=baseDao.selectOne(aMap,"MSAccountMapper.getAccountInfoByMemId");
			
			/*查询积分消费比例配置信息*/
			Map<String, String> configMap=getIntegralInfoConfig();
			String db=configMap.get("21B260C3-A230-4601-8D62-420XF00BFB01");
			/*给映射类添加积分最大消费比例*/
			mebmapping.setOrder_percentage(db+"%");
			
			/*给映射类添加会员地址信息*/
			if(addresses!=null)
			{
				if(null != addresses.getCityIdProvince()
						&&	null != addresses.getCityIdCity()
						&&	null != addresses.getCityIdArea()){
						StringBuffer sb = new StringBuffer();
						sb.append(baseDao.selectOne(addresses.getCityIdProvince().toString(),"MSCityMapper.getAccountInfoByMemId").toString());
						sb.append(";");
						sb.append(baseDao.selectOne(addresses.getCityIdCity().toString(),"MSCityMapper.getAccountInfoByMemId").toString());
						sb.append(";");
						sb.append(baseDao.selectOne(addresses.getCityIdArea().toString(),"MSCityMapper.getAccountInfoByMemId").toString());
						mebmapping.setAddressAreas(sb.toString());
					}
					if(!StringUtil.isEmptyByString(addresses.getMemaDetails())){
						mebmapping.setAddress(addresses.getMemaDetails());
					}
			}
			
			/*给映射类添加美兑积分余额和账户余额*/
			mebmapping.setConsume_coupon("0");//消费券
			mebmapping.setShoppingCoupon("0");//购物券
			mebmapping.setBalance(new BigDecimal(balance == null?0:balance));//账户余额
			mebmapping.setConsume_points(String.valueOf(getConsumePoints(memid,totalpoints)));//美兑积分
			
			/*存入会员行为日志，暂时不做*/
		/*	try
			{
				MSMemLog log = new MSMemLog();
				log.setMlogAction(SysParaNameConst.QUERY_WB);
				log.setMlogCreatedBy(memid);
				log.setMlogCreatedDate(new Date());
				log.setMlogIp(ip);
				log.setMemId(memid);
				log.setMlogModule(SysEncrypConst.ERJI_MEMBER_MENU_ZHZL_JBXX);
				log.setMlogContent("外部接口获取会员基本信息");
				log.setMlogRemark("");
				baseDao.insert(log,"");
			}catch (Exception e) {
			  Logger.error("存入会员行为日志失败:{0}",e.getMessage());
			}*/
			map.put(SysParaNameConst.STATUS_CODE,ApiStatusConst.SUCCESS);
			map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.SUCCESS_C);
			map.put(SysParaNameConst.RESULT,mebmapping);
		} catch (Exception e) {
			map.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.SERVER_ERROR);
			map.put(SysParaNameConst.RESULT_MSG,ApiStatusConst.SERVER_ERROR_C);
		}
		return map;
	}
	
	/**
	 * 根据memid查询查询冻结解冻的积分总额然后和会员账户积分相加,得到积分总额
	 * @param memId
	 * @return
	 */
	public Double getConsumePoints(String memId,String internal) {
		/*当前积分余额 */
		Double realPoints = Double.valueOf("0");
		try{
			/*冻结解冻的积分总额*/
			String fzPoints =baseDao.selectOne(memId,"MSConsumePointsFreezeInfoMapper.getConsumePointsByMemId");
			realPoints = DoubleCalculate.add(Double.valueOf(fzPoints),
					Double.valueOf(internal));
		}catch(Exception e){
			Logger.error("查询汇总积分操作失败:{0}",e.getMessage());
		}
		return realPoints;
	}
	
	
	/**
	 * 查询系统配置字典
	 * @return
	 */
	private Map<String, String> getIntegralInfoConfig(){
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<MSBusinessConfig> list = baseDao.selectList(null,"MSBusinessConfigMapper.queryBusinessConfigListParent");
			for(MSBusinessConfig b : list){
				String bcId = b.getBcId();
				List<MSBusinessConfig> childList = baseDao.selectList(bcId,"MSBusinessConfigMapper.queryBusinessConfigListSon");
				for (MSBusinessConfig config : childList) {
					map.put(config.getBcId(), config.getBcValue1());
				}
			}
		} catch (Exception e) {
			Logger.error("查询系统配置字典失败:{0}",e.getMessage());
		}
		return map;
	}

	/**
	 * 修改会员手机号
	 */
	@Override
	public JSONObject updateMemberPhone(String token, String newPhone, String code, String password) throws Exception {
		JSONObject json = new JSONObject();
		Map<String, String> param = new HashMap<String, String>();
		String memId = JedisUtil.getJedisInstance().execGetFromCache(token);
		if (StringUtil.checkStr(memId)) {
			MemberGet lv1 = baseDao.selectOne(memId, "MemberMapper.getMembersInfoByMemId");
			String exist_code=JedisUtil.getJedisInstance().execGetFromCache(SysParaNameConst.UPDATE_PHONE_VALIDATE_CODE+newPhone);
			if (exist_code.equals(code)) {
				if (lv1.getMemLoginPwd().equals(password)) {
					if (StringUtil.isPhoneToRegex(newPhone)) {
						String valMemId = baseDao.selectOne(newPhone, "MemberMapper.getMemIdByUserId");
						if (!StringUtil.checkStr(valMemId)) {
							param.put("oldPhone", DESC.encryption(lv1.getMemPhone()));
							param.put("newPhone", DESC.encryption(newPhone));
							param.put("updateDate", DateUtil.format(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
							param.put("memId", memId);
							baseDao.selectOne(param, "MemberMapper.updateMemberPhoneByMemId");
							json.put(SysParaNameConst.STATUS_CODE, "0");
							json.put(SysParaNameConst.RESULT_MSG, "手机号码修改成功!");
							Logger.info("修改手机号码成功!");
						} else {
							json.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.UPDATE_NEWPHONE_ERROR);
							json.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.UPDATE_NEWPHONE_ERROR_C);
							Logger.info("新手机号码已被注册,注册人ID:%s!" + valMemId);
						}
					} else {
						json.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PHONE_ERROR);
						json.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PHONE_ERROR_C);
						Logger.info("手机号码:%s错误!", newPhone);
					}
				} else {
					json.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PASSWORD_ERROR);
					json.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PASSWORD_ERROR_C);
					Logger.info("修改手机号码:密码验证失败!");
				}
			} else {
				json.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.VAL_SMSMSG_ERROR);
				json.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.VAL_SMSMSG_ERROR_C);
				Logger.info("修改手机号码:短信校验码校验不匹配!");
			}
		} else {
			json.put(SysParaNameConst.STATUS_CODE, "1020");
			json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在!");
			Logger.info("当前会员ID:%s不存在!", memId);
		}
		return json;
	}

	@Override
	public JSONObject getMemberInfoByPhone(String phone) throws Exception{
		JSONObject json = new JSONObject();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = new HashMap<String, String>();
		if (StringUtil.checkStr(phone)) {
			if (StringUtil.isPhoneToRegex(phone)) {
				String memId = baseDao.selectOne(DESC.encryption(phone), "MemberMapper.getMemIdByUserId");
				if (StringUtil.checkStr(memId)) {
					MemberGet member = baseDao.selectOne(memId, "MemberMapper.getMembersInfoByMemId");
					resultMap.put("nick_name", StringUtil.checkStr(member.getMemNickName()) == true ? member.getMemNickName() : "");
					resultMap.put("phone", StringUtil.checkStr(member.getMemPhone()) == true ? member.getMemPhone() : "");
					resultMap.put("login_name", StringUtil.checkStr(member.getMemLoginName()) == true ? member.getMemLoginName() : "");
					resultMap.put("pic_url", StringUtil.checkStr(member.getMemPic()) == true ? member.getMemPic() : "");
					json.put(SysParaNameConst.STATUS_CODE, "0");
					json.put(SysParaNameConst.RESULT_MSG, "Success");
					result.add(resultMap);
					json.put(SysParaNameConst.RESULT, result);
				} else {
					json.put(SysParaNameConst.STATUS_CODE, "1020");
					json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在!");
					Logger.info("当前会员ID:%s不存在!", memId);
				}
			} else {
				json.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PHONE_ERROR);
				json.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PHONE_ERROR_C);
				Logger.info("手机号码:%s错误!", phone);
			}
		} else {
			json.put(SysParaNameConst.STATUS_CODE, ApiStatusConst.PHONE_ERROR);
			json.put(SysParaNameConst.RESULT_MSG, ApiStatusConst.PHONE_ERROR_C);
			Logger.info("手机号码:%s错误!", phone);
		}
		return json;
	}

	@Transactional
	@Override
	public JSONObject saveMemberBasicInfo(String token, String mem_sex, String mem_birthday, String mem_address_area, String mem_address, String mem_pic, String nick_name) throws Exception {
		JSONObject json = new JSONObject();
		String memId = JedisUtil.getJedisInstance().execGetFromCache(token);
		if (StringUtil.checkStr(memId)) {
			MemberSet member = new MemberSet();
			member.setMemId(memId);
			MemberAddressesSet address = new MemberAddressesSet();
			address.setMemId(memId);
			if (StringUtil.checkStr(mem_sex))
				member.setMemSex(mem_sex);
			if (StringUtil.checkStr(mem_birthday))
				member.setMemBirthday(new SimpleDateFormat(DateUtil.YYYY_MM_DD).parse(mem_birthday));
			if (StringUtil.checkStr(mem_pic))
				member.setMemPic(mem_pic);
			if (StringUtil.checkStr(nick_name))
				member.setMemNickName(nick_name);
			if (StringUtil.checkStr(mem_address_area) || StringUtil.checkStr(mem_address)) {
				String[] memAddress = mem_address_area.split("[;]");
				if (memAddress.length != 3) {
					json.put(SysParaNameConst.STATUS_CODE, "9998");
					json.put(SysParaNameConst.RESULT_MSG, "省市区参数有误!");
					Logger.info("修改会员信息省市区参数有误:%s!", mem_address_area);
					return json;
				} else {
					String province = baseDao.selectOne(memAddress[0], "MSCityMapper.getCityIdByAddressName");
					String city = baseDao.selectOne(memAddress[1], "MSCityMapper.getCityIdByAddressName");
					String area = baseDao.selectOne(memAddress[2], "MSCityMapper.getCityIdByAddressName");
					if (StringUtil.checkStr(province))
						address.setCityIdProvince(Integer.valueOf(province));
					if (StringUtil.checkStr(city))
						address.setCityIdCity(Integer.valueOf(city));
					if (StringUtil.checkStr(area))
						address.setCityIdArea(Integer.valueOf(area));
				}
				if (StringUtil.checkStr(mem_address))
					address.setMemaDetails(mem_address);
				MemberAddressesGet memberAddressInfo = baseDao.selectOne(memId, "MemberAddressesMapper.getMemberAddressInfoByMemId");
				if (StringUtil.checkObj(memberAddressInfo)) {
					baseDao.update(address, "MemberAddressesMapper.updateMemberAddressInfoByMemaId");
				} else {
					json.put(SysParaNameConst.STATUS_CODE, "9998");
					json.put(SysParaNameConst.RESULT_MSG, "该用户不存在相关地址的数据!");
					Logger.info("该用户不存在相关地址的数据");
					return json;
				}
			}
			baseDao.update(member, "MemberMapper.updateMemberInfoByMemId");
			json.put(SysParaNameConst.STATUS_CODE, "0");
			json.put(SysParaNameConst.RESULT_MSG, "Success");
		} else {
			json.put(SysParaNameConst.STATUS_CODE, "1020");
			json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在!");
			Logger.info("当前会员ID:%s不存在!", memId);
		}
		return json;
	}
}
