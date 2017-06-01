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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.MdSysException;
import com.meiduimall.service.account.config.ServiceUrlProfileConfig;
import com.meiduimall.service.account.constant.ConstApiStatus;
import com.meiduimall.service.account.constant.ConstSmsTemplateType;
import com.meiduimall.service.account.constant.ConstSmsValidateCodeType;
import com.meiduimall.service.account.constant.ConstSysParamsDefination;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMembersPaypwd;
import com.meiduimall.service.account.model.MSMembersPaypwdRecord;
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
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
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
					resBodyData.setStatus(ConstApiStatus.PAYPWD_NOT_RIGHT);
					resBodyData.setMsg(ConstApiStatus.getZhMsg(ConstApiStatus.PAYPWD_NOT_RIGHT));
				}
			}
			/**校验MD5密码*/
			else{
				if(!MD5Util.MD5EncryptBy32(paypwd).toLowerCase().equals(md5Paypwd)){
					resBodyData.setStatus(ConstApiStatus.PAYPWD_NOT_RIGHT);
					resBodyData.setMsg(ConstApiStatus.getZhMsg(ConstApiStatus.PAYPWD_NOT_RIGHT));
				}
			}
		}
		else{
			resBodyData.setStatus(ConstApiStatus.PAYPWD_NOT_EXIST);
			resBodyData.setMsg(ConstApiStatus.getZhMsg(ConstApiStatus.PAYPWD_NOT_EXIST));
		}
		return resBodyData;
	}

	@Override
	public ResBodyData setPaypwd(MSMembersPaypwd msMembersPaypwd) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
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
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,"该会员存在支付密码");
		int i = baseDao.selectOne(memId,"MSMembersPaypwdMapper.getIsExistPaypwdByMemId");
		if(i<1){
			resBodyData.setStatus(ConstApiStatus.PAYPWD_NOT_EXIST);
			resBodyData.setMsg(ConstApiStatus.getZhMsg(ConstApiStatus.PAYPWD_NOT_EXIST));
		}
		return resBodyData;
	}
	
	@Transactional
	@Override
	public ResBodyData updatePaypwd(RequestUpdatePaypwd requestUpdatePaypwd) throws MdSysException {
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		
		/**先验证旧支付密码*/
		MSMembersPaypwd msMembersPaypwd=new MSMembersPaypwd();
		msMembersPaypwd.setMemId(requestUpdatePaypwd.getMemId());
		msMembersPaypwd.setPay_pwd(requestUpdatePaypwd.getOld_pay_pwd());
		resBodyData=validePaypwd(msMembersPaypwd);
		if(resBodyData.getStatus()!=0){
			logger.warn("旧支付密码校验不通过");
			resBodyData.setStatus(ConstApiStatus.OLD_PAYPWD_NOT_RIGHT);
			resBodyData.setMsg(ConstApiStatus.getZhMsg(ConstApiStatus.OLD_PAYPWD_NOT_RIGHT));
		}
		logger.info("旧支付密码校验通过");
		
		/**设置支付密码*/
		this.setNewPaypwd(requestUpdatePaypwd.getMemId(),requestUpdatePaypwd.getNew_pay_pwd());		
		return resBodyData;
	}
	
	@Override


	public void retrievePaypwd(RequestRetrievePaypwd requestRetrievePaypwd) throws MdSysException{
		ResBodyData resBodyData=new ResBodyData(ConstApiStatus.SUCCESS,ConstApiStatus.getZhMsg(ConstApiStatus.SUCCESS));
		String memberServiceUrl=serviceUrlProfileConfig.getMemberServiceUrl()+"/v1/get_member_basic_info?memId="+requestRetrievePaypwd.getMemId();
		String smsServiceUrl=serviceUrlProfileConfig.getSmsServiceUrl()+"/v1/new/check_sms_verification_code";
		String memberBasicInfo=null;
		try {
			memberBasicInfo=HttpUtils.get(memberServiceUrl);
			resBodyData=JSON.parseObject(memberBasicInfo,ResBodyData.class);
		} catch (Exception e) {
			logger.error("找回支付密码>>调用账号服务>>获取会员基本信息API>>异常:{}",e.toString());
			throw new ServiceException(ConstApiStatus.RETRIEVE_PAYPWD_EXCEPTION);
		}
		if(resBodyData.getStatus()!=0){
			logger.warn("找回支付密码>>调用账号服务>>获取会员基本信息API>>失败:{}",resBodyData.toString());
			throw new ServiceException(ConstApiStatus.GET_MEMBER_BASIC_INFO_FAILED);
		}
		String phone=JSONObject.parseObject(resBodyData.getData().toString()).getString("phone");
		Map<String,String> mapFormData=new HashMap<>();
		mapFormData.put("phones",phone);
		mapFormData.put("templateId",ConstSmsTemplateType.getSmsTemplate(ConstSmsTemplateType.SEND_VALIDATE_CODE));
		mapFormData.put("verificationCode",requestRetrievePaypwd.getValidate_code());
		mapFormData.put("type",ConstSmsValidateCodeType.getSmsType(Constants.CONSTANT_INT_ONE));
		mapFormData.put("sysKey",ConstSysParamsDefination.SMS_SYSKEY);
		String smsResult=null;
		try {
			smsResult=HttpUtils.form(smsServiceUrl,mapFormData);
			resBodyData=JSON.parseObject(smsResult,ResBodyData.class);
			logger.warn("找回支付密码>>调用短信服务>>校验短信验证码API>>URL:{}  DATA:{}  RESULT:{}",smsServiceUrl,mapFormData.toString(),resBodyData.toString());
		} catch (Exception e) {
			logger.warn("找回支付密码>>短信服务>>校验短信验证码API>>异常:{}",resBodyData.toString());
			throw new ServiceException(ConstApiStatus.RETRIEVE_PAYPWD_EXCEPTION);
		}
		if(resBodyData.getStatus()!=0){
			logger.warn("找回支付密码>>调用短信服务>>校验短信验证码API>>不通过:{}",resBodyData.toString());
			throw new ServiceException(ConstApiStatus.VALIDATE_CODE_NOT_PASS);
		}
		MSMembersPaypwd msMembersPaypwd=new MSMembersPaypwd();
		msMembersPaypwd.setMemId(requestRetrievePaypwd.getMemId());
		msMembersPaypwd.setPay_pwd(requestRetrievePaypwd.getPay_pwd());
		setPaypwd(msMembersPaypwd);
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
			throw new ServiceException(ConstApiStatus.SET_PAYPWD_EXCEPTION);
		}
		logger.info("设置新支付密码成功");
	}

}