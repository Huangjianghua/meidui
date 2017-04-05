package com.meiduimall.application.search.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.search.constant.SolrConstant;
import com.meiduimall.application.search.domain.Item;
import com.meiduimall.application.search.page.PageView;
import com.meiduimall.application.search.pojo.AppSearchParam;
import com.meiduimall.application.search.pojo.Brand;
import com.meiduimall.application.search.pojo.Cat;
import com.meiduimall.application.search.pojo.MeiduiPoint;
import com.meiduimall.application.search.pojo.NewestPrice;
import com.meiduimall.application.search.pojo.QueryIndexResult;
import com.meiduimall.application.search.pojo.SearchParam;
import com.meiduimall.application.search.pojo.WidgetResult;
import com.meiduimall.application.search.services.AppProductIndexService;
import com.meiduimall.application.search.services.MeiduiPointService;
import com.meiduimall.application.search.services.NewestPriceService;
import com.meiduimall.application.search.services.WidgetService;
import com.meiduimall.application.search.utility.StringUtil;

/**
 * 搜索
 * @date 2016年4月22日
 */
@Controller
public class AppSearchController extends BaseController {

	private Logger log = LoggerFactory.getLogger(AppSearchController.class);
	
	@Autowired
	private AppProductIndexService appProductIndexService;
	
	@Autowired
	private NewestPriceService newestPriceService;
	
	@Autowired
	private MeiduiPointService meiduiPointService;

	/**
	 * 搜索
	 * @param request
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "appSearch")
	public void search(HttpServletRequest request, HttpServletResponse response, AppSearchParam searchParam) {
		response.setCharacterEncoding("UTF-8");
		if (searchParam.getRows() == 0) 
			searchParam.setRows(SolrConstant.APP_PAGE_LIMIT_FORTY);
		String keyword = searchParam.getKeyword();
		if (keyword != null) {
			keyword = keyword.trim();
			searchParam.setKeyword(keyword);
		}
		PrintWriter out = null;
		try {
			QueryIndexResult results = getQueryResult(searchParam);
			JSONObject json = new JSONObject();
			json.put("status", "1");
			json.put("msg", "success");
			if (results != null) {
				json.put("data", results);
			} else {
				json.put("data", new JSONObject());
			}
			out = response.getWriter();
			out.print(json.toString());
		} catch (IOException e) {
			JSONObject json = new JSONObject();
			json.put("status", -1);
			json.put("msg", "数据查询异常!");
			e.printStackTrace();
			try {
				out = response.getWriter();
				out.println(json);
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				out.close();
			}
			
		}
	}
	
	/**
	 * 获取查询结果
	 * @param searchParam
	 * @return
	 */
	private QueryIndexResult getQueryResult(AppSearchParam searchParam) {
		try {
			String page = searchParam.getPage();
			// 处理用户传递非法页码信息
			if (!StringUtil.checkNumber(page, "+")) {
				searchParam.setPage("1");
			}
			QueryIndexResult qir = appProductIndexService.query(searchParam);
			
			Long totalCount = qir.getTotalCount();
			if (totalCount == 0) {
				return null;
			}
			// 用户传递页码参数大于查询总页数时显示最后一页信息
			Integer totalPage = (int) Math.ceil(totalCount/(double)searchParam.getRows());
			if (Integer.parseInt(page) > totalPage) {
				searchParam.setPage(totalPage.toString());
				qir = appProductIndexService.query(searchParam);
			}
			return qir;
		} catch (Exception e) {
			log.error("查询索引库信息异常，异常信息", e);
		}
		return null;
	}
}
