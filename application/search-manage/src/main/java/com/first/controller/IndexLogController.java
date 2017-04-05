package com.first.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.first.constant.SysConstant;
import com.first.page.PageView;
import com.first.page.QueryResult;
import com.first.services.IndexLogService;
import com.first.utility.StringUtil;

@Controller
@RequestMapping("indexLog")
public class IndexLogController {

	@Autowired
	private IndexLogService indexLogService;
	
	@RequestMapping(value = "queryIndexLogs")
	public ModelAndView queryIndexLogs(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String currentPage = request.getParameter("currentPage");
		int page = 1;
		if (!StringUtil.isEmptyByString(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		PageView pageView = new PageView(page);
		try {
			QueryResult result = indexLogService.queryIndexLogs(pageView);
			pageView.setQueryResult(result);
			mav.addObject("pageView", pageView);
			mav.setViewName("search/indexLogList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value = "deleteIndexLog")
	public ModelAndView deleteIndexLog(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String keyId = request.getParameter("logId");
		try {
			if (StringUtil.isEmptyByString(keyId)) {
				mav.addObject(SysConstant.RESULT_MSG, "缺少必要参数");
				return mav;
			}
			indexLogService.deleteIndexLogById(Integer.parseInt(keyId));
			mav.setViewName("redirect:/indexLog/queryIndexLogs.do?msg=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
