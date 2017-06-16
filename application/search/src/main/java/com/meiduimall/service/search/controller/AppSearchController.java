package com.meiduimall.service.search.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.search.constant.SearchApiCode;
import com.meiduimall.service.search.constant.SolrConstant;
import com.meiduimall.service.search.entity.QueryIndexResult;
import com.meiduimall.service.search.request.AppSearchParam;
import com.meiduimall.service.search.service.AppProductIndexService;
import com.meiduimall.service.search.util.StringUtil;

/**
 * Description:APP搜索
 * 
 * @author: jianhua.huang
 * @version: 2017年4月26日 下午2:05:27 0.1
 */
@RestController
public class AppSearchController {

	private static Logger logger = LoggerFactory.getLogger(AppSearchController.class);

	@Autowired
	private AppProductIndexService appProductIndexService;

	/**
	 * APP搜索
	 * 
	 * @param searchParam
	 * @return
	 * @author: jianhua.huang 2017年4月26日 下午2:05:07
	 */
	@RequestMapping(value = "/appSearch")
	public ResBodyData search(@Valid AppSearchParam searchParam) {
		ResBodyData res = new ResBodyData(1, "success");
		if (searchParam.getRows() == 0) {
			searchParam.setRows(SolrConstant.APP_PAGE_LIMIT_FORTY);
		}
		searchParam.setKeyword(searchParam.getKeyword().trim());
		QueryIndexResult qir = null;
		try {
			String page = searchParam.getPage();
			if (!StringUtil.checkNumber(page, "+")) {
				searchParam.setPage("1");
			}
			qir = appProductIndexService.query(searchParam);
			Long totalCount = qir.getTotalCount();
			if (totalCount == 0) {
				throw new ApiException(SearchApiCode.NO_DATA);
			}
			// 用户传递页码参数大于查询总页数时显示最后一页信息
			Integer totalPage = (int) Math.ceil(totalCount / (double) searchParam.getRows());
			if (Integer.parseInt(searchParam.getPage()) > totalPage) {
				searchParam.setPage(totalPage.toString());
				qir = appProductIndexService.query(searchParam);
			}
		} catch (Exception e) {
			logger.error("数据查询异常：{}", e);
			throw new ApiException(SearchApiCode.EXCEPTION_DATA_QUERY);
		}
		res.setData(qir);
		return res;
	}

}
