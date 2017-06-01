package com.meiduimall.service.member.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.member.config.ServiceUrlProfileConfig;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMemberAddresses;
import com.meiduimall.service.member.model.MSMemberMobileArea;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.MSMembersSet;
import com.meiduimall.service.member.model.MemberAddressesSet;
import com.meiduimall.service.member.model.MobileNumberInfo;
import com.meiduimall.service.member.model.request.RequestMobile;
import com.meiduimall.service.member.model.response.MemberMobileAreaDTO;
import com.meiduimall.service.member.model.response.ResponseMemberBasicInfo;
import com.meiduimall.service.member.service.AccountInfoService;
import com.meiduimall.service.member.service.PointsService;
import com.meiduimall.service.member.service.UserInfoService;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.DateUtil;
import com.meiduimall.service.member.util.DoubleCalculate;
import com.meiduimall.service.member.util.HttpUtils;
import com.meiduimall.service.member.util.StringUtil;

/**
 * 会员信息接口实现类
 * @author chencong
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private final static Logger logger=LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	BaseDao baseDao;
	
	@Autowired
	PointsService pointsService;
	
	@Autowired
	AccountInfoService moneyService;
	
	@Autowired
	ServiceUrlProfileConfig serviceUrlProfileConfig;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResBodyData getBasicInfoByMemId(String memId) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		//根据memId查询会员基本信息
		ResponseMemberBasicInfo memberBasicInfo=baseDao.selectOne(memId,"MSMembersMapper.getRespMemberBasicInfoByMemId");
		if(memberBasicInfo==null){
			throw new ServiceException(ApiStatusConst.MEMBER_NOT_EXIST);
		}
		memberBasicInfo.setPaypwd_isopen("Y".equals(memberBasicInfo.getPaypwd_isopen())?"1":"0");
		//根据memId查询会员详细地址
		MSMemberAddresses addresses=baseDao.selectOne(memId,"MSMemberAddressesMapper.getMemberAddressByMemId");
		if(addresses!=null){
			//添加省市区地址信息，分号隔开
			if(null!=addresses.getDictIdProvince()&&null!=addresses.getDictIdCity()&&null!=addresses.getDictIdArea()){
				StringBuffer addressShengShiQu = new StringBuffer();
				addressShengShiQu.append(baseDao.selectOne(addresses.getDictIdProvince().toString(),"MSCityMapper.getNameByNo").toString());
				addressShengShiQu.append(";");
				addressShengShiQu.append(baseDao.selectOne(addresses.getDictIdCity().toString(),"MSCityMapper.getNameByNo").toString());
				addressShengShiQu.append(";");
				addressShengShiQu.append(baseDao.selectOne(addresses.getDictIdArea().toString(),"MSCityMapper.getNameByNo").toString());
				memberBasicInfo.setMemAddressShengShiQu(addressShengShiQu.toString());
			}
		}
		//调用账户服务>根据memId查询是否存在支付密码
		ResBodyData result=restTemplate.getForEntity("http://ACCOUNT-SERVICE/v1/is_exist_paypwd?memId="+memId,ResBodyData.class).getBody();
		memberBasicInfo.setPaypwd_isset("1");
		if(!"0".equals(result.getStatus())){
			memberBasicInfo.setPaypwd_isset("0");
		}			
		//会员基本信息添加积分总额（包含冻结解冻的积分）和余额总额
		memberBasicInfo.setTotalmoney(moneyService.getTotalMoney(memId));
		memberBasicInfo.setTotalpoints(pointsService.getTotalPoints(memId,memberBasicInfo.getCurrentpoints()));
		
		resBodyData.setData(memberBasicInfo);
		return resBodyData;
	}
	

	@Override
	public void updateCurrentPointByMemId(String memId,String currentPoint,String addPoint) throws MdSysException {
		double finalPoint = DoubleCalculate.add(Double.valueOf(currentPoint),Double.valueOf(addPoint));
		MSMembersSet msMembersSet=new MSMembersSet();
		msMembersSet.setMemId(memId);
		msMembersSet.setMemBasicAccountTotalQuantity(String.valueOf(finalPoint));
		baseDao.update(msMembersSet,"MSMembersMapper.updateMemberBasicInfoByCondition");
	}


	@Override
	public JSONObject getMemberInfoByPhone(String phone) throws Exception{
		JSONObject json = new JSONObject();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = new HashMap<String, String>();
		if (StringUtil.checkStr(phone)) {
			if (StringUtil.isPhoneToRegex(phone)) {
				String memId = baseDao.selectOne(DESC.encryption(phone), "MSMembersMapper.getMemberInfoByCondition");
				if (StringUtil.checkStr(memId)) {
					MSMembersGet member = baseDao.selectOne(memId, "MSMembersMapper.getMembersInfoByMemId");
					resultMap.put("nick_name", StringUtil.checkStr(member.getMemNickName()) == true ? member.getMemNickName() : "");
					resultMap.put("phone", StringUtil.checkStr(member.getMemPhone()) == true ? member.getMemPhone() : "");
					resultMap.put("login_name", StringUtil.checkStr(member.getMemLoginName()) == true ? member.getMemLoginName() : "");
					resultMap.put("pic_url", StringUtil.checkStr(member.getMemPic()) == true ? member.getMemPic() : "");
					json.put(SysParamsConst.STATUS, "0");
					json.put(SysParamsConst.MSG, "Success");
					result.add(resultMap);
					json.put(SysParamsConst.DATA, result);
				} else {
					json.put(SysParamsConst.STATUS, "1020");
					json.put(SysParamsConst.MSG, "当前会员不存在!");
					logger.info("当前会员ID:{}不存在!", memId);
				}
			} else {
				json.put(SysParamsConst.STATUS,"999");
				logger.info("手机号码:{}错误!", phone);
			}
		} else {
			json.put(SysParamsConst.STATUS,"999");
			logger.info("手机号码:{}错误!", phone);
		}
		return json;
	}

	@Transactional
	@Override
	public JSONObject saveMemberBasicInfo(String token, String mem_sex, String mem_birthday, String mem_address_area, String mem_address, String mem_pic, String nick_name) throws Exception {
		JSONObject json = new JSONObject();
		String memId = RedisTemplate.getJedisInstance().execGetFromCache(token);
		if (StringUtil.checkStr(memId)) {
			MSMembersSet member = new MSMembersSet();
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
					json.put(SysParamsConst.STATUS, "9998");
					json.put(SysParamsConst.MSG, "省市区参数有误!");
					/*Logger.info("修改会员信息省市区参数有误:%s!", mem_address_area);*/
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
				/*MemberAddressesGet memberAddressInfo = baseDao.selectOne(memId, "MSMemberAddressesMapper.getMemberAddressByMemId");
				if (StringUtil.checkObj(memberAddressInfo)) {
					baseDao.update(address, "MSMemberAddressesMapper.updateMemberAddressInfoByMemaId");
				} else {
					json.put(SysParaNameConst.STATUS, "9998");
					json.put(SysParaNameConst.MSG, "该用户不存在相关地址的数据!");
					Logger.info("该用户不存在相关地址的数据");
					return json;
				}*/
			}
			baseDao.update(member, "MSMembersMapper.updateMemberInfoByMemId");
			json.put(SysParamsConst.STATUS, "0");
			json.put(SysParamsConst.MSG, "Success");
		} else {
			json.put(SysParamsConst.STATUS, "1020");
			json.put(SysParamsConst.MSG, "当前会员不存在!");
			logger.info("当前会员ID:{}不存在!", memId);
		}
		return json;
	}
	
	@Override
	public String getUserIdByMemId(String memId) throws MdSysException{
		Map<String, Object> mapCondition=new HashMap<>();
		mapCondition.put("memId",memId);
		MSMembersGet msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
		if(!StringUtils.isEmpty(msMembersGet.getMemLoginName())){
			return msMembersGet.getMemLoginName();
		}
		else if (!StringUtils.isEmpty(msMembersGet.getMemPhone())) {
			return msMembersGet.getMemPhone();
		}
		else{
			logger.info("memId：{}对应的手机号和登录名都为空",memId);
			throw new ServiceException(ApiStatusConst.ACCOUNT_EXCEPTION);
		}
	}


	@Override
	public ResBodyData recordArea(String memId, String phone) throws MdSysException{
		if(StringUtils.isEmpty(memId) || StringUtils.isEmpty(phone)){
			logger.info("必要参数不能为空!");
			return new ResBodyData(ApiStatusConst.REQUIRED_PARAM_EMPTY,ApiStatusConst.getZhMsg(ApiStatusConst.REQUIRED_PARAM_EMPTY));
		}
		
		MobileNumberInfo queryMobile = queryMobile(phone.substring(0, 7));
		if(StringUtils.isEmpty(queryMobile)){
			logger.info("手机前7位查询归属地为空!");
			return new ResBodyData(ApiStatusConst.QUERY_MOBILE_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.QUERY_MOBILE_EXCEPTION));
		}
		MSMemberMobileArea msMemberMobileArea = new MSMemberMobileArea();
		msMemberMobileArea.setMemId(memId);
		msMemberMobileArea.setPhone(phone);
		msMemberMobileArea.setProvinceName(queryMobile.getProvinceName());
		msMemberMobileArea.setCityName(queryMobile.getCityName());
		msMemberMobileArea.setSp(queryMobile.getTo());
		msMemberMobileArea.setCreateDate(new Date());
		MSMemberMobileArea mSMemberMobi = baseDao.selectOne(msMemberMobileArea, "MSMemberMobileAreaMapper.findMSMemberMobileArea");
		if(!StringUtils.isEmpty(mSMemberMobi)){
			logger.info("已经新增过: phone={},memId={} ", phone, memId);
			return new ResBodyData(ApiStatusConst.SUCCESS, "已经新增过phone="+phone+", memId="+memId);
		}else{
			baseDao.insert(msMemberMobileArea, "MSMemberMobileAreaMapper.insert");
		}
		return new ResBodyData(ApiStatusConst.SUCCESS, "成功");
	}


	
	@Override
	public ResBodyData updateMemberArea() {
		List<MemberMobileAreaDTO> memberMobileAreaDTO = null;
		List<MSMemberMobileArea> areas = new ArrayList<>(); 
		try {
		   memberMobileAreaDTO = baseDao.selectList(null, "MSMembersMapper.findNotInMemberMobileArea");
		   if(StringUtils.isEmpty(memberMobileAreaDTO)){
			   return new ResBodyData(ApiStatusConst.SUCCESS,"没有需要更新的会员");
		   }
		} catch (DaoException e) {
			 logger.error("查询不在会员手机归属地表异常: {}",e);
			 throw new ServiceException(ApiStatusConst.FIND_MEMBER_EXCEPTION);
		}	 
		    for (MemberMobileAreaDTO mmaDTO : memberMobileAreaDTO) {
		    	try {
					if(StringUtil.isPhoneToRegex(mmaDTO.getMemPhone())){
						String substr = mmaDTO.getMemPhone().substring(0,7);
						MobileNumberInfo mobNum = queryMobile(substr);
						if(!StringUtils.isEmpty(mobNum) || !StringUtils.isEmpty(mobNum.getCityName())){
							MSMemberMobileArea memMoArea = new MSMemberMobileArea();
							memMoArea.setMemId(mmaDTO.getMemId());
							memMoArea.setPhone(mmaDTO.getMemPhone());
							memMoArea.setProvinceName(mobNum.getProvinceName());
							memMoArea.setCityName(mobNum.getCityName());
							memMoArea.setSp(mobNum.getTo());
							memMoArea.setCreateDate(new Date());
							areas.add(memMoArea);
						}else{
							logger.info("没有查询手机前7位: {} 归属地",substr);
						}
					}
				} catch (MdSysException e) {
					logger.error("查询手机前7位确定归属地异常: {}",e);
					throw new ServiceException(ApiStatusConst.QUERY_MOBILE_EXCEPTION);
				}
			}
			try {
				logger.error("要插入会员手机归属地表的数据 : {}",areas);
				baseDao.insertBatch(areas, "MSMemberMobileAreaMapper.insertSelective");
			} catch (DaoException e) {
				logger.error("批量插入会员手机归属地表异常: {}",e);
				throw new ServiceException(ApiStatusConst.INSERT_SELECTIVE_EXCEPTION);
			}
		return new ResBodyData(ApiStatusConst.SUCCESS, "执行完成");
	}

	
	private MobileNumberInfo queryMobile(String substr){
	    return baseDao.selectOne(new RequestMobile(substr), "MobileNumberInfoMapper.queryMobile");
	}

	
}
