package com.meiduimall.application.search.manage.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.LogParam;
import com.meiduimall.application.search.manage.pojo.StatisticLog;
import com.meiduimall.application.search.manage.pojo.SuggestWord;
import com.meiduimall.application.search.manage.services.SearchLogService;
import com.meiduimall.application.search.manage.services.SuggestWordService;
import com.meiduimall.application.search.manage.utility.DateUtils;
import com.meiduimall.application.search.manage.utility.StringUtil;

@Controller
@RequestMapping("searchLog")
public class SearchLogController {
	
	@Autowired
	private SearchLogService searchLogService;
	
	@Autowired
	private SuggestWordService suggestWordService;
	
	private String startTime = "";
	
	
	@Autowired
	private  HttpServletRequest request;
	
	public void init() {
		// 默认查询近一周搜索日志
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		date = calendar.getTime();
		startTime = DateUtils.format(date);
	}

	@RequestMapping(value = "listSearchLog")
	public ModelAndView listSearchLog(LogParam logParam) {
		ModelAndView mav = new ModelAndView();
		String currentPage = request.getParameter("currentPage");
		int page = 1;
		if (!StringUtil.isEmptyByString(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		try {
			// 默认查询近一周搜索日志
			if (logParam.getStartTime() == null) {
				logParam.setStartTime(startTime);
			}
			QueryResult result = searchLogService.querySearchLogs(logParam);
			PageView pageView = new PageView(page);
			pageView.setQueryResult(result);
			// 回显参数
			mav.addObject("startTime", logParam.getStartTime());
			mav.addObject("endTime", logParam.getEndTime());
			mav.addObject("keyword", logParam.getKeyword());
			mav.addObject("pageView", pageView);
			mav.setViewName("search/searchLogList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value = "statisticSearchLog")
	public ModelAndView statisticSearchLog(LogParam logParam) {
		ModelAndView mav = new ModelAndView();
		String currentPage = request.getParameter("currentPage");
		int page = 1;
		if (!StringUtil.isEmptyByString(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		try {
			// 默认查询近一周搜索日志
			if (logParam.getStartTime() == null) {
				logParam.setStartTime(startTime);
			}
			QueryResult result = searchLogService.queryStatisticLogs(logParam);
			PageView pageView = new PageView(page);
			pageView.setQueryResult(result);
			// 回显参数
			mav.addObject("startTime", logParam.getStartTime());
			mav.addObject("endTime", logParam.getEndTime());
			mav.addObject("keyword", logParam.getKeyword());
			mav.addObject("pageView", pageView);
			mav.setViewName("search/statisticLogList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateSuggest")
	public String updateSuggest(LogParam logParam) {
		String url = "redirect:/searchLog/statisticSearchLog.do";
		try {
			QueryResult result = searchLogService.queryStatisticLogs(logParam);
			List data = result.getDateList();
			if (result != null && data != null && !data.isEmpty()) {
				StatisticLog log = (StatisticLog) data.get(0);
				String keyword = log.getKeyword();
				SuggestWord suggestWord = suggestWordService.querySuggestWordByKey(keyword);
				long num = log.getNum();
				// 提示词中有该词条则修改搜索次数
				if (suggestWord != null) {
					suggestWord.setKwfreq((int)num);
					int word = suggestWordService.updateSuggestWord(suggestWord);
					if (word > 0) {
						url += "?msg=1";
					}
				} else {
					// 提示词中没有有该词条则增加该词条
					suggestWord = new SuggestWord();
					suggestWord.setKw(keyword);
					suggestWord.setKwfreq((int)num);
					int word = suggestWordService.addSuggestWord(suggestWord);
					if (word > 0) {
						url += "?msg=1";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
