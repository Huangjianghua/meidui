package com.meiduimall.application.search.manage.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.ItemModel;
import com.meiduimall.application.search.manage.services.ItemService;
import com.meiduimall.application.search.manage.utility.StringUtil;

@Controller
@RequestMapping("item")
public class ItemController {

	private Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("queryItems")
	public String queryItems() {
		return "/search/itemList";
	}
	
	@RequestMapping("showListPage")
	public ModelAndView showListPage(HttpServletRequest request, ItemModel itemModel) {
		ModelAndView mav = this.getModel(request);
		try {
			QueryResult result = itemService.queryItems(itemModel);
			PageView pageView = new PageView(itemModel.getCurrentPage());
			itemModel.setOffset(pageView.getFirstIndex());
			pageView.setQueryResult(result);
			mav.setViewName("/search/itemList");
			mav.addObject("pageView", pageView);
		} catch (Exception e) {
			log.error("查询商品信息异常", e);
		}
		return mav;
	}
	
	private ModelAndView getModel(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (!parameterMap.isEmpty()) {
			Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
			for (Entry<String, String[]> entry : entrySet) {
				String key = entry.getKey();
				if (entry.getValue() != null && entry.getValue().length > 0 && !StringUtil.isEmptyByString(entry.getValue()[0])) {
					String value = entry.getValue()[0];
					// 查询条件及参数
					mav.addObject(key, value);
				}
			}
		}
		return mav;
	}
}
