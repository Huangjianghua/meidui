package com.meiduimall.application.search.manage.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.system.domain.Systemlog;
import com.meiduimall.application.search.manage.system.services.ISystemlogService;

@Controller
@RequestMapping("/log")
public class SystemLogController {
	@Resource
	private ISystemlogService  systemlogService;
	
	/**
	 * 显示系统登入日志
	 * @return
	 */
	@RequestMapping("/showListPage")
	public  ModelAndView  showListPage(Systemlog systemLog){
		ModelAndView mav = new ModelAndView();
		PageView pageView = new PageView(systemLog.getCurrentPage());
		systemLog.setOffset(pageView.getFirstIndex());
		QueryResult result =  systemlogService.selectPageList(systemLog);
		pageView.setQueryResult(result);
		mav.addObject("pageView", pageView);
		mav.addObject("systemlog", systemLog);
		mav.setViewName("/systemlog/loginlog");
		return  mav ;
	}
	
}
