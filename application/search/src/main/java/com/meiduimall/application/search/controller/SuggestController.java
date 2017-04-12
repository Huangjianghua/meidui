package com.meiduimall.application.search.controller;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.meiduimall.application.search.services.SuggestService;
import com.meiduimall.application.search.utility.Pinyin4jUtil;

/**
 * 输入提示
 * @date 2016年4月26日
 */
@RestController
public class SuggestController {

	private Logger logger = Logger.getLogger(SuggestController.class);
	
	
	@Autowired
	private SuggestService suggestService;
	
	
	@RequestMapping(value = "suggest", method = RequestMethod.POST)
	public List<String> suggest(String keyword) {
		try {
			if (keyword == null || "".equals(keyword)) {
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
