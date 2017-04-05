package com.first.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.utility.LoadPropertyUtil;

/**
 * 跳转solr管理后台
 * @date 2016年8月10日
 */
@Controller
@RequestMapping("solrMgr")
public class SolrMgrController {
	
	private String solrUrl;
	
	public SolrMgrController() {
		try {
			solrUrl = LoadPropertyUtil.getProperty("config.properties", "search_server_url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("solrPage")
	public String solrPage(HttpServletRequest request) {
		request.setAttribute("url", solrUrl);
		return "search/solrMgr";
	}
}
