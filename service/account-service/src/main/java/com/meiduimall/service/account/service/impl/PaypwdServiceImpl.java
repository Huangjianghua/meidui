package com.meiduimall.service.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.exception.ServiceException;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.Constants;
import com.meiduimall.exception.ApiException;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.config.ServiceUrlProfileConfig;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.constant.SmsTemplateIDConst;
import com.meiduimall.service.account.constant.SmsTypeConst;
import com.meiduimall.service.account.constant.SysParamsConst;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMembersPaypwd;
import com.meiduimall.service.account.model.MSMembersPaypwdRecord;
import com.meiduimall.service.account.model.ResBodyData;
import com.meiduimall.service.account.model.request.RequestRetrievePaypwd;
import com.meiduimall.service.account.model.request.RequestUpdatePaypwd;
import com.meiduimall.service.account.service.PaypwdService;
import com.meiduimall.service.account.util.BCrypt;
import com.meiduimall.service.account.util.HttpUtils;
import com.meiduimall.service.account.util.MD5Util;
import com.meiduimall.service.account.util.StringUtil;


@Service
public class PaypwdServiceImpl implements PaypwdService {
	
	private final static Logger logger=LoggerFactory.getLogger(PaypwdServiceImpl.class);
	
	@Autowired
	private  BaseDao  baseDao;
	
	@Autowired
	private ServiceUrlProfileConfig serviceUrlProfileConfig;

	@Override
	public ResBodyData validePaypwd(MSMembersPaypwd msMembersPaypwd) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		String paypwd=msMembersPaypwd.getPay_pwd();
		
		msMembersPaypwd.setPay_pwd(null);
		MSMembersPaypwd existPaypwd = baseDao.selectOne(msMembersPaypwd, "MSMembersPaypwdMapper.getPaypwdByMemIdAndPaypwd");
		if(existPaypwd!=null){
			String md5Paypwd=existPaypwd.getMd5Pwd();
			/**校验PHP迁移过来的原始密码*/
			if(StringUtil.isEmptyByString(md5Paypwd)){
				boolean blowPwd = BCrypt.checkpw(paypwd,existPaypwd.getPay_pwd());
				if(blowPwd){
					msMembersPaypwd.setMd5Pwd(MD5Util.MD5EncryptBy32(paypwd));
					baseDao.selectOne(msMembersPaypwd, "MSMembersPaypwdMapper.updatePaypwdByMemId");
				}
				else{
					resBodyData.setStatus(ApiStatusConst.PAYPWD_NOT_RIGHT);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.PAYPWD_NOT_RIGHT));
				}
			}
			/**校验MD5密码*/
			else{
				if(!MD5Util.MD5EncryptBy32(paypwd).toLowerCase().equals(md5Paypwd)){
					resBodyData.setStatus(ApiStatusConst.PAYPWD_NOT_RIGHT);
					resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.PAYPWD_NOT_RIGHT));
				}
			}
		}
		else{
			resBodyData.setStatus(ApiStatusConst.PAYPWD_NOT_EXIST);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.PAYPWD_NOT_EXIST));
		}
		return resBodyData;
	}

	@Override
	public ResBodyData setPaypwd(MSMembersPaypwd msMembersPaypwd) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		String memId=msMembersPaypwd.getMemId();
		String paypwd=msMembersPaypwd.getPay_pwd();
		
		msMembersPaypwd.setPay_pwd(null);
		MSMembersPaypwd existPaypwd= baseDao.selectOne(msMembersPaypwd, "MSMembersPaypwdMapper.getPaypwdByMemIdAndPaypwd");
		
		msMembersPaypwd.setMd5Pwd(MD5Util.MD5EncryptBy32(paypwd).toLowerCase());
		msMembersPaypwd.setUpdateDate(new Date());
		
		if (existPaypwd!=null){
			logger.info("当前会员支付密码信息查询结果：{}",existPaypwd.toString());
			baseDao.update(msMembersPaypwd, "MSMembersPaypwdMapper.updatePaypwdByMemId");
			logger.info("会员ID：{}存在原始支付密码记录,更新成功!",memId);
		}
		else{
			baseDao.insert(msMembersPaypwd,"MSMembersPaypwdMapper.insertPaypwd");
			logger.info("会员ID：{}不存在原始支付密码记录,插入成功!", memId);
		}
		/**记录日志*/
		operateRecord(memId, msMembersPaypwd.getMd5Pwd());
		return resBodyData;
	}	
	
	@Override
	public ResBodyData isExistPaypwd(String memId){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		int i = baseDao.selectOne(memId,"MSMembersPaypwdMapper.getIsExistPaypwdByMemId");
		if(i<1){
			resBodyData.setStatus(ApiStatusConst.PAYPWD_NOT_EXIST);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.PAYPWD_NOT_EXIST));
		}
		return resBodyData;
	}
	
	@Transactional
	@Override
	public ResBodyData updatePaypwd(RequestUpdatePaypwd requestUpdatePaypwd) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		
		/**先验证旧支付密码*/
		MSMembersPaypwd msMembersPaypwd=new MSMembersPaypwd();
		msMembersPaypwd.setMemId(requestUpdatePaypwd.getMemId());
		msMembersPaypwd.setPay_pwd(requestUpdatePaypwd.getOld_paypwd());
		resBodyData=validePaypwd(msMembersPaypwd);
		if(resBodyData.getStatus()!=0){
			logger.warn("旧支付密码校验不通过");
			resBodyData.setStatus(ApiStatusConst.OLD_PAYPWD_NOT_RIGHT);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.OLD_PAYPWD_NOT_RIGHT));
		}
		logger.info("旧支付密码校验通过");
		
		/**设置支付密码*/
		this.setNewPaypwd(requestUpdatePaypwd.getMemId(),requestUpdatePaypwd.getNew_paypwd());		
		return resBodyData;
	}
	
	@Override


	public ResBodyData retrievePaypwd(RequestRetrievePaypwd requestRetrievePaypwd){
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		String memberServiceUrl=serviceUrlProfileConfig.getMemberServiceUrl();
		String smsServiceUrl=serviceUrlProfileConfig.getSmsServiceUrl();
		try {
			//获取会员基本信息
			String memberBasicInfo=HttpUtils.get(memberServiceUrl+"/v1/get_member_basic_info?memId="+requestRetrievePaypwd.getMemId());
			resBodyData=JSONObject.parseObject(memberBasicInfo,ResBodyData.class);
			if(resBodyData.getStatus()!=0){
				logger.warn("获取会员基本信息失败:{}",resBodyData.toString());
				throw new ServiceException(ApiStatusConst.GET_MEMBER_BASIC_INFO_FAILED);
			}
			else{
				String phone=JSONObject.parseObject(resBodyData.getData().toString()).getString("phone");
				Map<String,String> mapFormData=new HashMap<>();
				mapFormData.put("phones",phone);
				mapFormData.put("templateId",SmsTemplateIDConst.getSmsTemplate(SmsTemplateIDConst.SEND_VALIDATE_CODE));
				mapFormData.put("verificationCode",requestRetrievePaypwd.getValidate_code());
				mapFormData.put("type",SmsTypeConst.getSmsType(Constants.CONSTANT_INT_ONE));
				mapFormData.put("sysKey",SysParamsConst.SMS_SYSKEY);
				String smsResult=HttpUtils.form(smsServiceUrl+"//new/check_sms_verification_code",mapFormData);
				resBodyData=JSONObject.parseObject(smsResult,ResBodyData.class);
				if(resBodyData.getStatus()!=0){
					logger.warn("找回支付密码>>校验短信验证码不通过:{}",resBodyData.toString());
					throw new ServiceException(ApiStatusConst.VALIDATE_CODE_NOT_PASS);
				}
				else{
					MSMembersPaypwd msMembersPaypwd=new MSMembersPaypwd();
					msMembersPaypwd.setMemId(requestRetrievePaypwd.getMemId());
					msMembersPaypwd.setPay_pwd(requestRetrievePaypwd.getPay_pwd());
					resBodyData=setPaypwd(msMembersPaypwd);
				}
			}
		} catch (Exception e) {
			logger.error("找回支付密码程序异常:{}",e.toString());
			throw new ServiceException(ApiStatusConst.RETRIEVE_PAYPWD_EXCEPTION);
		}
		return resBodyData;
	}
	
	/**
	 * 添加设置支付密码日志
	 * @param memId 会员ID
	 * @param pwd 支付密码（明文）
	 * @throws Exception
	 */
	private void operateRecord(String memId, String pwd){
		MSMembersPaypwdRecord operateRecord = baseDao.selectOne(memId, "MSMembersPaypwdMapper.getPaypwdRecordByMemId");
		if (StringUtil.checkObj(operateRecord)) {
			switch (operateRecord.getUpdateIndex()) {
				case 1:
					operateRecord.setUpdatePwd1(pwd);
					operateRecord.setUpdateIndex(operateRecord.getUpdateIndex() + 1);
					break;
				case 2:
					operateRecord.setUpdatePwd2(pwd);
					operateRecord.setUpdateIndex(operateRecord.getUpdateIndex() + 1);
					break;
				case 3:
					operateRecord.setUpdatePwd3(pwd);
					operateRecord.setUpdateIndex(operateRecord.getUpdateIndex() + 1);
					break;
				case 4:
					operateRecord.setUpdatePwd4(pwd);
					operateRecord.setUpdateIndex(operateRecord.getUpdateIndex() + 1);
					break;
				case 5:
					operateRecord.setUpdatePwd5(pwd);
					operateRecord.setUpdateIndex(1);
					break;
			}
			operateRecord.setUpdateDate(new Date());
			baseDao.update(operateRecord, "MSMembersPaypwdMapper.updatePaypwdRecordByMemId");
			logger.info("会员ID：{}存在原始支付密码操作记录,更新成功!", memId);
		} else {
			operateRecord = new MSMembersPaypwdRecord();
			operateRecord.setMemId(memId);
			operateRecord.setUpdateDate(new Date());
			operateRecord.setUpdatePwd1(pwd);
			operateRecord.setUpdateIndex(2);
			baseDao.insert(operateRecord, "MSMembersPaypwdMapper.insertPaypwdRecord");
			logger.info("会员ID：{}不存在原始支付密码操作记录,插入成功!", memId);
		}
	}

	/**
	 * 设置新支付密码
	 * @param memId 会员ID
	 * @param paypwd 新支付密码


	 * @throws SystemException 检查类型异常
	 */
	private void setNewPaypwd(String memId,String paypwd) throws MdSysException{

		MSMembersPaypwd msMembersPaypwd=new MSMembersPaypwd();
		msMembersPaypwd.setMemId(memId);
		msMembersPaypwd.setPay_pwd(paypwd);
		ResBodyData resBodyData=null;
		resBodyData = setPaypwd(msMembersPaypwd);
		if(resBodyData.getStatus()!=0){
			logger.warn("设置新支付密码失败");
			throw new ServiceException(ApiStatusConst.SET_PAYPWD_EXCEPTION);
		}
		logger.info("设置新支付密码成功");
	}

}