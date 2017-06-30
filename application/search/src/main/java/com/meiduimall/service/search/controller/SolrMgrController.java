package com.meiduimall.service.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转solr管理后台
 * 
 * @date 2016年8月10日
 */
@Controller
@RequestMapping("/solrMgr")
public class SolrMgrController {

	@Value("${search_server_url}")
	private String solrUrl;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/solrPage")
	public String solrPage() {
		request.setAttribute("url", solrUrl);
		return "search/solrMgr";
	}
}
