package com.meiduimall.service.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.meiduimall.service.search.service.SuggestService;
import com.meiduimall.service.search.util.Pinyin4jUtil;

/**
 * Description:输入提示
 * 
 * @author: jianhua.huang
 * @version: 2017年4月26日 下午2:11:00 0.1
 */
@RestController
public class SuggestController {

	private static Logger logger = LoggerFactory.getLogger(SuggestController.class);

	@Autowired
	private SuggestService suggestService;

	@RequestMapping(value = "/suggest", method = RequestMethod.POST)
	public List<String> suggest(String keyword) {
		try {
			if (Strings.isNullOrEmpty(keyword)) {
				return new ArrayList<String>();
			}
			keyword = Pinyin4jUtil.getPinyin(keyword);
			String[] keys = keyword.split(",");
			if (keys.length > 1) {
				keyword = keys[0];
			}
			return suggestService.querySuggest(keyword, 10);
		} catch (Exception e) {
			logger.error("输入提示发生异常，异常信息： ", e);
			return new ArrayList<String>();
		}
	}
}
