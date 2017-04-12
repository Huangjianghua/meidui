package com.meiduimall.application.search.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.application.search.constant.SolrConstant;
import com.meiduimall.application.search.pojo.AppSearchParam;
import com.meiduimall.application.search.pojo.QueryIndexResult;
import com.meiduimall.application.search.services.AppProductIndexService;
import com.meiduimall.application.search.utility.StringUtil;
import com.meiduimall.core.BaseApiCode;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.core.SearchApiCode;

/**
 * 搜索
 * @date 2016年4月22日
 */
@RestController
public class AppSearchController extends BaseController {

	private Logger log = LoggerFactory.getLogger(AppSearchController.class);
	
	@Autowired
	private AppProductIndexService appProductIndexService;
	

	/**
	 * 搜索
	 * @param request
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "appSearch")
	public ResBodyData search(AppSearchParam searchParam) {
		ResBodyData res=new ResBodyData(1, "success");
		if (searchParam.getRows() == 0){
			searchParam.setRows(SolrConstant.APP_PAGE_LIMIT_FORTY);
		}	
		if (searchParam.getKeyword() != null) {
			searchParam.setKeyword(searchParam.getKeyword().trim());
		}
		QueryIndexResult qir=null;
		try {
			String page = searchParam.getPage();
			if (!StringUtil.checkNumber(page, "+")) {
				searchParam.setPage("1");
			}
			qir = appProductIndexService.query(searchParam);
			Long totalCount = qir.getTotalCount();
			if (totalCount == 0) {
				return null;
			}
			// 用户传递页码参数大于查询总页数时显示最后一页信息
			Integer totalPage = (int) Math.ceil(totalCount/(double)searchParam.getRows());
			if (Integer.parseInt(searchParam.getPage()) > totalPage) {
				searchParam.setPage(totalPage.toString());
				qir = appProductIndexService.query(searchParam);
			}
		}catch (Exception e) {
			log.error("数据查询异常：{}",e);
			res.setStatus(SearchApiCode.EXCEPTION_DATA_QUERY);
			res.setMsg(BaseApiCode.getZhMsg(SearchApiCode.EXCEPTION_DATA_QUERY));
		}
		res.setData(qir);
		return res;
	}
	

}
