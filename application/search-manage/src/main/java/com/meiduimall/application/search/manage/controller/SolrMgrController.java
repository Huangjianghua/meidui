package com.meiduimall.application.search.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meiduimall.application.search.manage.utility.LoadPropertyUtil;

/**
 * 跳转solr管理后台
 * @date 2016年8月10日
 */
@Controller
@RequestMapping("solrMgr")
public class SolrMgrController {
	
	private String solrUrl;
	
	@Autowired
	private HttpServletRequest request;
	
	public SolrMgrController() {
		try {
			solrUrl = LoadPropertyUtil.getProperty("config.properties", "search_server_url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("solrPage")
	public String solrPage() {
		request.setAttribute("url", solrUrl);
		return "search/solrMgr";
	}
}
