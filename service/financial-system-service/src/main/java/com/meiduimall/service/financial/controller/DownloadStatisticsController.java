package com.meiduimall.service.financial.controller;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.service.DownloadStatisticsService;
import com.meiduimall.service.financial.util.HttpTooUtils;
import com.meiduimall.service.financial.util.Logger;

@RestController
@RequestMapping("/financial/financial-system-service/v1/statistics")
public class DownloadStatisticsController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private DownloadStatisticsService downloadStatisticsService;

	
	/**
	 * 插入下载渠道信息  
	 * 
	 * @param portal
	 *            渠道编号，必须要传
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResBodyData insertPortal(String portal) {
		try {
			Logger.info("插入下载渠道，渠道编号：%s", portal);

			if (StringUtils.isEmpty(portal)) {
				ResBodyData result = new ResBodyData();
				result.setStatus(BaseApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.REQUEST_PARAMS_ERROR));
				result.setData("{}");
				return result;
			}

			DownloadStatistics bean = new DownloadStatistics();
			bean.setPortal(Integer.parseInt(portal.trim()));

			// String ip = request.getRemoteAddr();
			String ip = HttpTooUtils.getIpAddr(request);
			bean.setIp(ip);

			String userAgent = request.getHeader("User-Agent");
			bean.setUserAgent(userAgent);

			bean.setRequestTime(new Date(System.currentTimeMillis()));
			return downloadStatisticsService.insert(bean);

		} catch (Exception e) {
			Logger.error("插入下载渠道，报异常：%s", e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData("{}");
			return result;
		}
	}

	/**
	 * 统计下载渠道信息
	 * 
	 * @param beginDate
	 *            开始时间，格式'2017-03-08 09:30:25' 或者 '2017-03-08'
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	@RequestMapping(value = "/query")
	public ResBodyData queryByDate(String beginDate, String endDate) {

		try {
			String ip = request.getRemoteAddr();
			Logger.info("查询下载渠道信息，请求IP：%s", ip);

			// 特殊处理--日期时间包含空格和冒号，需要进行URL编码和URL解码
			
			if (!StringUtils.isEmpty(beginDate)) {				
				beginDate = URLDecoder.decode(beginDate, "utf-8");
			}
			if (!StringUtils.isEmpty(beginDate)) {
				endDate = URLDecoder.decode(endDate, "utf-8");
			}

			return downloadStatisticsService.queryByDate(beginDate, endDate);

		} catch (Exception e) {
			Logger.error("查询下载渠道信息，报异常：%s", e);
			ResBodyData result = new ResBodyData();
			result.setStatus(BaseApiCode.OPERAT_FAIL);
			result.setMsg(BaseApiCode.getZhMsg(BaseApiCode.OPERAT_FAIL));
			result.setData("{}");
			return result;
		}
	}
}
