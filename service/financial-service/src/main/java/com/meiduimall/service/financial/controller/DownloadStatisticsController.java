package com.meiduimall.service.financial.controller;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.financial.constant.ServiceFinancialApiCode;
import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.service.DownloadStatisticsService;
import com.meiduimall.service.financial.util.HttpHeaderTools;

@RestController
@RequestMapping("/financial/financial-system-service/v1/statistics")
public class DownloadStatisticsController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(DownloadStatisticsController.class);

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
			logger.info("插入下载渠道，渠道编号：" + portal);

			if (StringUtils.isEmpty(portal)) {
				ResBodyData result = new ResBodyData();
				result.setStatus(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR);
				result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR));
				result.setData(new JSONObject());
				return result;
			}

			DownloadStatistics bean = new DownloadStatistics();
			bean.setPortal(Integer.parseInt(portal.trim()));

			String ip = HttpHeaderTools.getIpAddr(request);
			bean.setIp(ip);

			String userAgent = request.getHeader("User-Agent");
			bean.setUserAgent(userAgent);

			bean.setRequestTime(new Date(System.currentTimeMillis()));
			return downloadStatisticsService.insert(bean);

		} catch (Exception e) {
			logger.error("插入下载渠道，报异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(ServiceFinancialApiCode.OPERAT_FAIL);
			result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
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
			logger.info("查询下载渠道信息，请求IP：" + ip);

			// 特殊处理--日期时间包含空格和冒号，需要进行URL编码和URL解码

			if (!StringUtils.isEmpty(beginDate)) {
				beginDate = URLDecoder.decode(beginDate, "utf-8");
			}
			if (!StringUtils.isEmpty(endDate)) {
				endDate = URLDecoder.decode(endDate, "utf-8");
			}

			return downloadStatisticsService.queryByDate(beginDate, endDate);

		} catch (Exception e) {
			logger.error("查询下载渠道信息，报异常：" + e);
			ResBodyData result = new ResBodyData();
			result.setStatus(ServiceFinancialApiCode.OPERAT_FAIL);
			result.setMsg(ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.OPERAT_FAIL));
			result.setData(new JSONObject());
			return result;
		}
	}
}
