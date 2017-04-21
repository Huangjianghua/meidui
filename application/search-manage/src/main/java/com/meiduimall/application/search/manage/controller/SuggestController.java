package com.meiduimall.application.search.manage.controller;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.application.search.manage.services.SuggestService;

/**
 * 输入提示
 * @date 2016年4月26日
 */
@Controller
public class SuggestController {

	private static Logger logger = LoggerFactory.getLogger(SuggestController.class);
	
	@Autowired
	private SuggestService suggestService;
	
	@RequestMapping(value = "suggest", method = RequestMethod.POST)
	@ResponseBody
	public List<String> suggest(String keyword) {
		try {
			if (keyword == null || "".equals(keyword)) {
				return new ArrayList<String>();
			}
			return suggestService.querySuggest(keyword, 10);
		} catch (Exception e) {
			logger.error("输入提示发生异常，异常信息： ", e);
			return new ArrayList<String>();
		}
	}
}
