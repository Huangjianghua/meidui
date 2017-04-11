package com.meiduimall.application.search.manage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.constant.SolrConstant;
import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.pojo.Brand;
import com.meiduimall.application.search.manage.pojo.Cat;
import com.meiduimall.application.search.manage.pojo.QueryIndexResult;
import com.meiduimall.application.search.manage.pojo.SearchParam;
import com.meiduimall.application.search.manage.services.ProductIndexService;
import com.meiduimall.application.search.manage.utility.StringUtil;
import com.meiduimall.application.search.manage.utility.XFCUtil;

/**
 * 搜索
 * @date 2016年4月22日
 */
@Controller
public class SearchController extends BaseController {

	private Logger log = Logger.getLogger(SearchController.class);
	
	@Resource
	private ProductIndexService productIndexService;
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "search")
	public ModelAndView search(SearchParam searchParam) {
		ModelAndView model = new ModelAndView("index");
		String keyword = searchParam.getKeyword();
		String ip = getIpAddr(request);
		String platform = request.getParameter("platform");
		String page = searchParam.getPage();
		long totalCount = 0;
		
		model.addObject("page", page);
		model.addObject("keyword", keyword);
		model.addObject("orderBy", searchParam.getR_sort());
		model.addObject("xfc", XFCUtil.getxfc());
		
		if (StringUtil.isEmptyByString(keyword)) {
			addSearchLog(keyword, ip, totalCount, platform);
			return model;
		}
		// 处理用户传递非法页码信息
		if (!StringUtil.checkNumber(page, "+")) {
			searchParam.setPage("1");
		}
		
		QueryIndexResult results = getQueryResult(searchParam);
		if (results == null) {
			String sg = request.getParameter("sg1");
			if (sg == null || sg.equals("")) {
				return model;
			}
			searchParam.setKeyword(sg);
			results = getQueryResult(searchParam);
			if (results == null) {
				// 统计记录
				addSearchLog(keyword, ip, totalCount, platform);
				return model;
			}
			model.addObject("sg", sg);
		}
		totalCount = results.getTotalCount();
		model.addObject("results", results);
		
		// 统计记录
		addSearchLog(keyword, ip, totalCount, platform);
		
		// 参数回显示
		String brandId = searchParam.getBrand();
		if (brandId != null && brandId.trim().length() > 0) {
			List<Brand> brands = results.getBrands();
			for (Brand brand : brands) {
				if (brand.getBrandId().equals(brandId)) {
					model.addObject("brand", brand);
					break;
				}
			}
		}
		
		String catId = searchParam.getCid3();
		if (catId != null && catId.trim().length() > 0) {
			List<Cat> cats = results.getCats();
			for (Cat cat : cats) {
				if (cat.getCatId().equals(catId)) {
					model.addObject("cat", cat);
					break;
				}
			}
		}
		PageView pageView = new PageView(Integer.parseInt(page));
		pageView.setTotalCount((int) totalCount);
		pageView.setPageSize(SolrConstant.PAGE_LIMIT_THIRTY);
		model.addObject("pageView", pageView);
		return model;
	}
	
	private void addSearchLog(String keyword, String ip, long totalCount, String platform) {
		JSONObject obj = new JSONObject();
		obj.put("keyword", keyword);
		obj.put("ip", ip);
		obj.put("resultNum", totalCount);
		obj.put("platform", platform == null ? "0":platform);
		obj.put("searchTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		log.info(obj);
	}
	
	/**
	 * 获取查询结果
	 * @param searchParam
	 * @return
	 */
	private QueryIndexResult getQueryResult(SearchParam searchParam) {
		try {
			QueryIndexResult qir = productIndexService.query(searchParam);
			
			Long totalCount = qir.getTotalCount();
			if (totalCount == 0) {
				return null;
			}
			// 用户传递页码参数大于查询总页数时显示最后一页信息
			Integer totalPage = (int) Math.ceil(totalCount/(double)searchParam.getRows());
			String page = searchParam.getPage();
			if (Integer.parseInt(page) > totalPage) {
				searchParam.setPage(totalPage.toString());
				qir = productIndexService.query(searchParam);
			}
			return qir;
		} catch (Exception e) {
			log.error("查询索引库信息异常，异常信息： " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
