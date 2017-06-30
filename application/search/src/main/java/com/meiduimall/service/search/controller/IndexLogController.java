package com.meiduimall.service.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.meiduimall.service.search.constant.SysConstant;
import com.meiduimall.service.search.page.PageView;
import com.meiduimall.service.search.page.QueryResult;
import com.meiduimall.service.search.service.IndexLogService;

@Controller
@RequestMapping("/indexLog")
public class IndexLogController {

	private static Logger logger = LoggerFactory.getLogger(IndexLogController.class);

	@Autowired
	private IndexLogService indexLogService;

	@RequestMapping(value = "/queryIndexLogs")
	public ModelAndView queryIndexLogs(String currentPage) {
		ModelAndView mav = new ModelAndView();
		int page = 1;
		if (!Strings.isNullOrEmpty(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		PageView pageView = new PageView(page);
		QueryResult result = null;
		try {
			result = indexLogService.queryIndexLogs(pageView);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageView.setQueryResult(result);
		mav.addObject("pageView", pageView);
		mav.setViewName("search/indexLogList");
		return mav;
	}

	@RequestMapping(value = "/deleteIndexLog")
	public ModelAndView deleteIndexLog(String logId) {
		ModelAndView mav = new ModelAndView();
		if (Strings.isNullOrEmpty(logId)) {
			mav.addObject(SysConstant.RESULT_MSG, "缺少必要参数");
			return mav;
		}
		try {
			indexLogService.deleteIndexLogById(Integer.parseInt(logId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:/indexLog/queryIndexLogs.do?msg=1");
		return mav;
	}
}
