package com.meiduimall.service.account.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.account.constant.ApiStatusConst;
import com.meiduimall.service.account.dao.BaseDao;
import com.meiduimall.service.account.model.MSMembersPaypwd;
import com.meiduimall.service.account.model.MSMembersPaypwdRecord;
import com.meiduimall.service.account.model.ResBodyData;
import com.meiduimall.service.account.service.PaypwdService;
import com.meiduimall.service.account.util.BCrypt;
import com.meiduimall.service.account.util.MD5Util;
import com.meiduimall.service.account.util.StringUtil;


@Service
public class PaypwdServiceImpl implements PaypwdService {
	
	private final static Logger logger=LoggerFactory.getLogger(PaypwdServiceImpl.class);
	
	@Autowired
	private  BaseDao  baseDao;

	@Override
	public ResBodyData validePaypwd(MSMembersPaypwd msMembersPaypwd) throws SystemException{
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

	@SuppressWarnings("unused")
	@Override
	public ResBodyData setPaypwd(MSMembersPaypwd msMembersPaypwd) throws SystemException{
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
}