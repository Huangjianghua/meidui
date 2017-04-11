package com.meiduimall.application.search.manage.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.constant.SystemConfig;
import com.meiduimall.application.search.manage.dao.RunningAccountDao;
import com.meiduimall.application.search.manage.domain.RunningAccount;
import com.meiduimall.application.search.manage.utility.MD5Tool;
import com.meiduimall.application.search.manage.utility.SUtil;

/**
 * 流水帐底层数据操作接口
 * @author Liujun
 * @date 2016年4月12日
 */
@Repository("runningAccountDao")
public class RunningAccountDaoImpl extends BaseDaoImpl<RunningAccount> implements RunningAccountDao {
	
	/**
	 * 流水帐分页信息获取
	 * @param pageNo        页码
	 * @param pageSize      每页的大小
	 * @param paramMap		查询参数
	 * @return
	 * @throws Exception 
	 */
	public List<RunningAccount> getPaginByPort(Integer pageNo, Integer pageSize, 
			Map<String, String> paramMap) throws Exception {
		
		StringBuffer dataUrl = new StringBuffer();
//		dataUrl.append(LoadPropertyUtil.getPropertyValues("order_data_url"));
		dataUrl.append(SystemConfig.getInstance().configMap.get("order_data_url"));
		String pwd = SystemConfig.getInstance().configMap.get("firstprj_auth");
		String timeStamp = new Date().getTime() + "";
		String token = MD5Tool.MD5Encrypt(pwd + timeStamp);
		dataUrl.append("?token=").append(token)
			   .append("&timeStamp=").append(timeStamp);
		if(paramMap != null) {
			
			if(SUtil.isNotBlank(paramMap.get("packageIntegral"))) {
				dataUrl.append("&packageIntegral=").append(paramMap.get("packageIntegral"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("orderId"))) {
				dataUrl.append("&orderId=").append(paramMap.get("orderId"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("memId"))) {
				dataUrl.append("&memId=").append(paramMap.get("memId"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchMoney"))) {
				dataUrl.append("&mchMoney=").append(paramMap.get("mchMoney"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchBshopGiveIntegral"))) {
				dataUrl.append("&mchBshopGiveIntegral=").append(paramMap.get("mchBshopGiveIntegral"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchTotalIntegral"))) {
				dataUrl.append("&mchTotalIntegral=").append(paramMap.get("mchTotalIntegral"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("bcLevel"))) {
				dataUrl.append("&bcLevel=").append(paramMap.get("bcLevel"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchCurrentReturnedIntegral"))) {
				dataUrl.append("&mchCurrentReturnedIntegral=").append(paramMap.get("mchCurrentReturnedIntegral"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchNextReturnIntegral"))) {
				dataUrl.append("&mchNextReturnIntegral=").append(paramMap.get("mchNextReturnIntegral"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchReturnProfitIntegral"))) {
				dataUrl.append("&mchReturnProfitIntegral=").append(paramMap.get("mchReturnProfitIntegral"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchFunds"))) {
				dataUrl.append("&mchFunds=").append(paramMap.get("mchFunds"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchCurrentGetIntegral"))) {
				dataUrl.append("&mchCurrentGetIntegral=").append(paramMap.get("mchCurrentGetIntegral"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchOrginCategory"))) {
				dataUrl.append("&mchOrginCategory=").append(paramMap.get("mchOrginCategory"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchProductName"))) {
				dataUrl.append("&mchProductName=").append(paramMap.get("mchProductName"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchOrginType"))) {
				dataUrl.append("&mchOrginType=").append(paramMap.get("mchOrginType"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchSettingStatus"))) {
				dataUrl.append("&mchSettingStatus=").append(paramMap.get("mchSettingStatus"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchIssueStatus"))) {
				dataUrl.append("&mchIssueStatus=").append(paramMap.get("mchIssueStatus"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchPayType"))) {
				dataUrl.append("&mchPayType=").append(paramMap.get("mchPayType"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchStatus"))) {
				dataUrl.append("&mchStatus=").append(paramMap.get("mchStatus"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("mchConsumeCouponCount"))) {
				dataUrl.append("&mchConsumeCouponCount=").append(paramMap.get("mchConsumeCouponCount"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("startTime"))) {
				dataUrl.append("&startTime=").append(paramMap.get("startTime"));
			}
			
			if(SUtil.isNotBlank(paramMap.get("endTime"))) {
				dataUrl.append("&endTime=").append(paramMap.get("endTime"));
			}

		}
		String url = dataUrl.toString().replace(" ", "%20");
		return this.findPageByPort(url, pageNo, pageSize, RunningAccount.class);
	}
}
