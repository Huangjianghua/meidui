package com.meiduimall.application.search.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.SuggestWord;
import com.meiduimall.application.search.manage.services.SuggestWordService;
import com.meiduimall.application.search.manage.utility.StringUtil;

@Controller
@RequestMapping("suggestWord")
public class SuggestWordController {
	
	
	private static Logger logger = LoggerFactory.getLogger(SuggestWordController.class);

	@Autowired
	private SuggestWordService suggestWordService;
	
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value = "querySuggestWord")
	public ModelAndView querySuggestWord() {
		ModelAndView mav = new ModelAndView();
		String currentPage = request.getParameter("currentPage");
		int page = 1;
		if (!StringUtil.isEmptyByString(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		PageView pageView = new PageView(page);
		List<SuggestWord> suggestWords = suggestWordService.querySuggestWords(pageView);
		long count = suggestWordService.querySuggestWordCount(null);
		QueryResult result = new QueryResult();
		result.setDateList(suggestWords);
		result.setTotalCount((int)count);
		pageView.setQueryResult(result);
		mav.addObject("pageView", pageView);
		mav.setViewName("search/suggestWordList");
		return mav;
	}
	
	@RequestMapping(value = "queryWord")
	public ModelAndView queryWord() {
		ModelAndView mav = new ModelAndView();
		String key = request.getParameter("key");
		PageView pageView = new PageView(1);
		List<SuggestWord> suggestWords = suggestWordService.fuzzyQuerySuggestWord(key);
		long count = suggestWordService.querySuggestWordCount(key);
		QueryResult result = new QueryResult();
		result.setDateList(suggestWords);
		result.setTotalCount((int)count);
		pageView.setQueryResult(result);
		mav.addObject("pageView", pageView);
		mav.setViewName("search/suggestWordList");
		return mav;
	}
	
	@RequestMapping(value = "updateSuggestWord")
	public ModelAndView updateSuggestWord() {
		ModelAndView mav = new ModelAndView();
		String kw = request.getParameter("key");
		try {
			if (StringUtil.isEmptyByString(kw)) {
				mav.addObject(SysConstant.RESULT_MSG, "缺少必要参数");
				return mav;
			}
			SuggestWord suggestWord = new SuggestWord();
			suggestWord.setKw(kw);
			suggestWord.setKwfreq(0);
			suggestWordService.addSuggestWord(suggestWord);
			mav.addObject("msg", 1);
			mav.setViewName("redirect:/suggestWord/querySuggestWord.do");
		} catch (Exception e) {
			logger.error("更新updateSuggestWord信息异常:{}",e);
		}
		return mav;
	}
	
	@RequestMapping(value = "deleteSuggestWord")
	public ModelAndView deleteSuggestWord() {
		ModelAndView mav = new ModelAndView();
		String keyId = request.getParameter("keyId");
		try {
			if (StringUtil.isEmptyByString(keyId)) {
				mav.addObject(SysConstant.RESULT_MSG, "缺少必要参数");
				return mav;
			}
			suggestWordService.deleteSuggestWordById(Integer.parseInt(keyId));
			mav.addObject("msg", 1);
			mav.setViewName("redirect:/suggestWord/querySuggestWord.do");
		} catch (Exception e) {
			logger.error("删除deleteSuggestWord信息异常:{}",e);
		}
		return mav;
	}
}
