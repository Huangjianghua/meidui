package com.meiduimall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.constant.SysParaNameConst;
import com.meiduimall.dao.BaseDao;
import com.meiduimall.model.MemberGet;
import com.meiduimall.redis.util.JedisUtil;
import com.meiduimall.service.ShareMenAndFunsService;
import com.meiduimall.util.DESC;
import com.meiduimall.util.DateUtil;
import com.meiduimall.util.Logger;
import com.meiduimall.util.StringUtil;

/**
 * 推荐人和粉丝相关接口实现类
 * @author chencong
 *
 */
@Service
public class ShareMenAndFunsServiceImpl implements ShareMenAndFunsService{
	
	@Autowired
	private BaseDao baseDao;

	/**
	 * 查询二级推荐人
	 */
	@Override
	public JSONObject queryShareMan(String memId) throws Exception {
		JSONObject json = new JSONObject();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> param = new HashMap<String, String>();
		if (StringUtil.checkStr(memId)) {
			param.put("memId", "'" + memId + "'");
			MemberGet lv1 = baseDao.selectOne(param, "MemberMapper.getMemberByMemId");
			if (null != lv1 && StringUtil.checkStr(lv1.getMemId())) {
				Map<String, String> lv1ShareMember = new HashMap<String, String>();
				lv1ShareMember.put("level", "1");
				lv1ShareMember.put("login_name", StringUtil.checkStr(lv1.getMemLoginName()) == true ? lv1.getMemLoginName() : "");
				lv1ShareMember.put("mem_id", StringUtil.checkStr(lv1.getMemId()) == true ? lv1.getMemId() : "");
				lv1ShareMember.put("email", StringUtil.checkStr(lv1.getMemEmail()) == true ? lv1.getMemEmail() : "");
				lv1ShareMember.put("nick_name", StringUtil.checkStr(lv1.getMemNickName()) == true ? lv1.getMemNickName() : "");
				lv1ShareMember.put("mem_name", StringUtil.checkStr(lv1.getMemName()) == true ? lv1.getMemName() : "");
				lv1ShareMember.put("phone", StringUtil.checkStr(lv1.getMemPhone()) == true ? lv1.getMemPhone() : "");
				System.out.println(lv1.getMemLoginName());
				result.add(lv1ShareMember);
				Logger.info("查询出一级推荐人");
				param.clear();
				param.put("memId", "'" + lv1.getMemParentId() + "'");
				MemberGet lv2 = baseDao.selectOne(param, "MemberMapper.getMemIdByUserId");
				if (null != lv2 && StringUtil.checkStr(lv2.getMemId())) {
					Map<String, String> lv2ShareMember = new HashMap<String, String>();
					lv2ShareMember.put("level", "2");
					lv2ShareMember.put("login_name", StringUtil.checkStr(lv2.getMemLoginName()) == true ? lv2.getMemLoginName() : "");
					lv2ShareMember.put("mem_id", StringUtil.checkStr(lv2.getMemId()) == true ? lv2.getMemId() : "");
					lv2ShareMember.put("email", StringUtil.checkStr(lv2.getMemEmail()) == true ? lv2.getMemEmail() : "");
					lv2ShareMember.put("nick_name", StringUtil.checkStr(lv2.getMemNickName()) == true ? lv2.getMemNickName() : "");
					lv2ShareMember.put("mem_name", StringUtil.checkStr(lv2.getMemName()) == true ? lv2.getMemName() : "");
					lv2ShareMember.put("phone", StringUtil.checkStr(lv2.getMemPhone()) == true ? lv2.getMemPhone() : "");
					result.add(lv2ShareMember);
					Logger.info("查询出二级推荐人");
				}
				json.put(SysParaNameConst.STATUS_CODE, "0");
				json.put(SysParaNameConst.RESULT_MSG, "获取推荐人成功!");
				json.put(SysParaNameConst.RESULT, result);
			} else {
				json.put(SysParaNameConst.STATUS_CODE, "1020");
				json.put(SysParaNameConst.RESULT_MSG, "当前会员的推荐人不存在!");
				Logger.info("当前会员ID:%s的推荐人不存在!", memId);
			}
		} else {
			json.put(SysParaNameConst.STATUS_CODE, "1020");
			json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在!");
			Logger.info("当前会员ID:%s不存在!", memId);
		}
		return json;
	}

	
	/**
	 * 查询粉丝明细
	 */
	@Override
	public JSONObject queryFansDetailsList(String memId, String fansLv, int limit, int pageNum) throws Exception {
		JSONObject json = new JSONObject();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> param = new HashMap<String, String>();
		if (limit > 0 && pageNum > 0 && StringUtil.checkStr(fansLv) && ("1".equals(fansLv) || "2".equals(fansLv))) {
			int startIndex = (limit * pageNum) - limit;
			int endIndex = limit * pageNum;
			if (StringUtil.checkStr(memId)) {
				param.put("memId", "'" + memId + "'");
				MemberGet member = baseDao.selectOne(param, "MemberMapper.getMemberByMemId");
				if (null != member && StringUtil.checkStr(member.getMemParentValue3())) {
					String lv1Ids = "";
					String[] lv1FansId = member.getMemParentValue3().split("[,]");
					for (int i = 0; i < lv1FansId.length; i++) {
						lv1Ids += "'" + lv1FansId[i] + "'";
						if (i != (lv1FansId.length - 1))
							lv1Ids += ",";
					}
					if ("1".equals(fansLv)) {
						param.clear();
						param.put("memId", lv1Ids);
						param.put("limit", "LIMIT " + startIndex + ", " + endIndex);
						List<MemberGet> lv1FansInfo = baseDao.selectList(param, "MemberMapper.getMemberByMemId");
						Map<String, String> lv1MemberInfo = new HashMap<String, String>();
						for (MemberGet memberInfo : lv1FansInfo) {
							lv1MemberInfo.put("memCreatedDate", StringUtil.checkObj(memberInfo.getMemCreatedDate()) == true ? DateUtil.getDateFormat().format(memberInfo.getMemCreatedDate()) : "");
							lv1MemberInfo.put("memLoginName", StringUtil.checkStr(memberInfo.getMemLoginName()) == true ? memberInfo.getMemLoginName() : "");
							lv1MemberInfo.put("memNickName", StringUtil.checkStr(memberInfo.getMemNickName()) == true ? memberInfo.getMemNickName() : "");
							lv1MemberInfo.put("memPhone", StringUtil.checkStr(memberInfo.getMemPhone()) == true ? memberInfo.getMemPhone() : "");
							lv1MemberInfo.put("memPic", StringUtil.checkStr(memberInfo.getMemPic()) == true ? memberInfo.getMemPic() : "");
							result.add(lv1MemberInfo);
						}
						json.put(SysParaNameConst.STATUS_CODE, "0");
						json.put(SysParaNameConst.RESULT_MSG, "获取粉丝成功!");
						json.put("totalPages", "" + (lv1FansInfo.size() % limit == 0 ? lv1FansInfo.size() / limit : lv1FansInfo.size() / limit + 1));
						json.put(SysParaNameConst.RESULT, result);
						Logger.info("获取1级粉丝成功!");
					} else if ("2".equals(fansLv)) {
						param.clear();
						param.put("memId", lv1Ids);
						String lv2Ids = "";
						List<MemberGet> lv1FansInfo = baseDao.selectList(param, "MemberMapper.getMemberByMemId");
						for (MemberGet memberInfo : lv1FansInfo) {
							if (StringUtil.checkStr(memberInfo.getMemParentValue3())) {
								String[] lv2FansId = memberInfo.getMemParentValue3().split("[,]");
								for (int i = 0; i < lv2FansId.length; i++) {
									lv2Ids += "'" + lv2FansId[i] + "'";
									if (i != (lv2FansId.length - 1))
										lv2Ids += ",";
								}
							}
						}
						param.clear();
						if (StringUtil.checkStr(lv2Ids)) {
							param.put("memId", lv2Ids);
							param.put("limit", "LIMIT " + startIndex + ", " + endIndex);
							List<MemberGet> lv2FansInfo = baseDao.selectList(param, "MemberMapper.getMemberByMemId");
							Map<String, String> lv2MemberInfo = new HashMap<String, String>();
							for (MemberGet memberInfo : lv2FansInfo) {
								lv2MemberInfo.put("memCreatedDate", StringUtil.checkObj(memberInfo.getMemCreatedDate()) == true ? DateUtil.getDateFormat().format(memberInfo.getMemCreatedDate()) : "");
								lv2MemberInfo.put("memLoginName", StringUtil.checkStr(memberInfo.getMemLoginName()) == true ? memberInfo.getMemLoginName() : "");
								lv2MemberInfo.put("memNickName", StringUtil.checkStr(memberInfo.getMemNickName()) == true ? memberInfo.getMemNickName() : "");
								lv2MemberInfo.put("memPhone", StringUtil.checkStr(memberInfo.getMemPhone()) == true ? memberInfo.getMemPhone() : "");
								lv2MemberInfo.put("memPic", StringUtil.checkStr(memberInfo.getMemPic()) == true ? memberInfo.getMemPic() : "");
								result.add(lv2MemberInfo);
							}
							json.put(SysParaNameConst.STATUS_CODE, "0");
							json.put(SysParaNameConst.RESULT_MSG, "获取粉丝成功!");
							json.put("totalPages", "" + (lv2MemberInfo.size() % limit == 0 ? lv2MemberInfo.size() / limit : lv2MemberInfo.size() / limit + 1));
							json.put(SysParaNameConst.RESULT, result);
							Logger.info("获取2级粉丝成功!");
						} else {
							json.put(SysParaNameConst.STATUS_CODE, "0");
							json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在" + fansLv + "级粉丝!");
							Logger.info("当前会员ID:%s不存在" + fansLv + "级粉丝!", memId);
						}
					} else {
						json.put(SysParaNameConst.STATUS_CODE, "9998");
						json.put(SysParaNameConst.RESULT_MSG, "粉丝级别输入有误!仅支持2级!粉丝级别:" + fansLv);
						Logger.info("粉丝级别输入有误!fansLv=%s" + fansLv);
					}
					
				} else {
					json.put(SysParaNameConst.STATUS_CODE, "0");
					json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在" + fansLv + "级粉丝!");
					Logger.info("当前会员ID:%s不存在" + fansLv + "级粉丝!", memId);
				}
			} else {
				json.put(SysParaNameConst.STATUS_CODE, "1020");
				json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在!");
				Logger.info("当前会员ID:%s不存在!", memId);
			}
		} else {
			json.put(SysParaNameConst.STATUS_CODE, "9998");
			json.put(SysParaNameConst.RESULT_MSG, "参数有误!");
			Logger.info("参数有误!limit:" + limit + ";pageNum:" + pageNum + "; fansLv:" + fansLv);
		}
		return json;
	}

	/**
	 * 按照级别分别获取粉丝数量
	 */
	@Override
	public JSONObject queryFansNumber(String memId) throws Exception {
		JSONObject json = new JSONObject();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String> fansNumber = new HashMap<String, String>();
		if (StringUtil.checkStr(memId)) {
			param.put("memId", "'" + memId + "'");
			MemberGet member = baseDao.selectOne(param, "MemberMapper.getMemberByMemId");
			if (null != member && StringUtil.checkStr(member.getMemParentValue3())) {
				String lv1Ids = "";
				String[] lv1FansId = member.getMemParentValue3().split("[,]");
				for (int i = 0; i < lv1FansId.length; i++) {
					lv1Ids += "'" + lv1FansId[i] + "'";
					if (i != (lv1FansId.length - 1))
						lv1Ids += ",";
				}
				param.clear();
				param.put("memId", lv1Ids);
				int lv2FansNum = 0;
				List<MemberGet> lv1FansInfo = baseDao.selectList(param, "MemberMapper.getMemberByMemId");
				for (MemberGet memberInfo : lv1FansInfo) {
					if (StringUtil.checkStr(memberInfo.getMemParentValue3())) {
						String[] lv2FansId = memberInfo.getMemParentValue3().split("[,]");
						lv2FansNum += lv2FansId.length;
					}
				}
				json.put(SysParaNameConst.STATUS_CODE, "0");
				json.put(SysParaNameConst.RESULT_MSG, "获取粉丝数量成功!");
				fansNumber.put("level_1_count", "" + lv1FansId.length);
				fansNumber.put("level_2_count", "" + lv2FansNum);
				json.put(SysParaNameConst.RESULT, fansNumber);
				Logger.info("一级粉丝数量为:" + lv1FansId.length + ";二级粉丝数量为:" + lv2FansNum);
			} else {
				json.put(SysParaNameConst.STATUS_CODE, "0");
				json.put(SysParaNameConst.RESULT_MSG, "获取粉丝数量成功!");
				fansNumber.put("level_1_count", "0");
				fansNumber.put("level_2_count", "0");
				json.put(SysParaNameConst.RESULT, fansNumber);
				Logger.info("一级粉丝数量为:0;二级粉丝数量为:0");
			}
		} else {
			json.put(SysParaNameConst.STATUS_CODE, "1020");
			json.put(SysParaNameConst.RESULT_MSG, "当前会员不存在!");
			Logger.info("当前会员ID:%s不存在!", memId);
		}
		return json;
	}
}