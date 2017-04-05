package com.first.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.first.page.PageView;
import com.first.page.QueryResult;
import com.first.system.domain.Systemlog;
import com.first.system.services.ISystemlogService;

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
