package com.meiduimall.application.search.controller;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.application.search.constant.SolrConstant;
import com.meiduimall.application.search.page.PageView;
import com.meiduimall.application.search.pojo.Brand;
import com.meiduimall.application.search.pojo.Cat;
import com.meiduimall.application.search.pojo.QueryIndexResult;
import com.meiduimall.application.search.pojo.SearchParam;
import com.meiduimall.application.search.pojo.WidgetResult;
import com.meiduimall.application.search.services.ProductIndexService;
import com.meiduimall.application.search.services.WidgetService;
import com.meiduimall.application.search.utility.StringUtil;

/**
 * 搜索
 * @author:   jianhua.huang 
 * @version:  2017年4月26日 下午2:09:56 0.1 
 * Description:
 */
@Controller
public class SearchController extends BaseController {

	private Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private ProductIndexService productIndexService;
	@Autowired
	private WidgetService widgetService;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 列表
	 * @param searchParam
	 * @return
	 * @author: jianhua.huang  2017年4月26日 下午2:08:54
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(SearchParam searchParam) {
		ModelAndView model = new ModelAndView("list");
		try {
			Integer catId = searchParam.getCat_id();
			if (catId == null || "".equals(catId)) {
				return model;
			}
			String pageStr = request.getParameter("page");
			if (StringUtil.isEmptyByString(pageStr) || !StringUtil.checkNumber(pageStr, "+")) {
				searchParam.setPage("1");
			}
			
			String bid = searchParam.getB();
			String brand = searchParam.getBrand();
			// 已选择品牌
			Set<String> checked = new HashSet<String>();
			if (!StringUtil.isEmptyByString(brand)) {
				String[] brands = brand.split(",");
				List<String> list = Arrays.asList(brands);
				checked.addAll(list);
				if (!StringUtil.isEmptyByString(bid)) {
					// 操作类型	增加：add， 移除：rm
					String opt = searchParam.getOpt();
					checked.add(bid);
					StringBuffer sb = new StringBuffer();
					if ("rm".equals(opt)) {
						for (String b : brands) {
							if (bid.equals(b)) {
								checked.remove(bid);
							} else {
								sb.append(b).append(",");
							}
						}
						if (!StringUtil.isEmptyByString(sb.toString())) {
							brand = sb.deleteCharAt(sb.length() - 1).toString();
						} else {
							brand = null;
						}
					} else {
						for (String b : brands) {
							sb.append(b).append(",");
						}
						sb.append(bid);
						brand = sb.toString();
					}
					searchParam.setBrand(brand);
				} else {
					checked.add(brand);
				}
			} else {
				if (!StringUtil.isEmptyByString(bid)) {
					checked.add(bid);
					searchParam.setBrand(bid);
				}
			}
			model.addObject("brands", searchParam.getBrand());
			model.addObject("chkbrand", checked);
			
			String pid = request.getParameter("p");
			String prop = searchParam.getProp();
			if (!StringUtil.isEmptyByString(prop) && !StringUtil.isEmptyByString(pid)) {
				if (!prop.equals(pid)) {
					String[] props = prop.split(",");
					List<String> list = Arrays.asList(props);
					if (!list.contains(pid)) {
						searchParam.setBrand(prop + "," + pid);
					} else {
						StringBuffer sb = new StringBuffer();
						for (String str : list) {
							if (pid.equals(str)) {
								continue;
							}
							sb.append(str).append(",");
						}
						sb.deleteCharAt(sb.length() - 1);
						searchParam.setProp(sb.toString());
					}
				} else {
					searchParam.setProp(null);
				}
			} else if (StringUtil.isEmptyByString(prop) && !StringUtil.isEmptyByString(pid)) {
				searchParam.setProp(pid);
			}
			model.addObject("prop", searchParam.getProp());
			
			String r_sort = searchParam.getR_sort();
			if (!StringUtil.isEmptyByString(r_sort)) {
				model.addObject("orderBy", r_sort);
			}

			QueryIndexResult results = productIndexService.list(searchParam);
			model.addObject("results", results);
			model.addObject("widget", getWideget());
			model.addObject("list", "list");
			model.addObject("catId", catId);
			List<Brand> brands = results.getBrands();
			StringBuffer seo = new StringBuffer();
			for (int i = 0; i < brands.size(); i++) {
				if (i >= 8) {
					break;
				}
				seo.append(brands.get(i).getBrand()).append(",");
			}
			if (seo.length() > 0) {
				seo.deleteCharAt(seo.length() - 1);
				model.addObject("seo", seo);
			}
			
			int page = Integer.parseInt(searchParam.getPage());
			model.addObject("page", page);
			PageView pageView = new PageView(page);
			long totalCount = results.getTotalCount();
			pageView.setTotalCount((int) totalCount);
			pageView.setPageSize(SolrConstant.PAGE_LIMIT_FORTY);
			model.addObject("pageView", pageView);
		} catch (Exception e) {
			log.error("查询商品列表异常", e);
			return model;
		}
		return model;
	}

	/**
	 * 搜索
	 * @param searchParam
	 * @return
	 * @author: jianhua.huang  2017年4月26日 下午2:09:25
	 */
	@RequestMapping(value = "search")
	public ModelAndView search(SearchParam searchParam) {
		ModelAndView model = new ModelAndView("index");
		searchParam.setRows(SolrConstant.PAGE_LIMIT_FORTY);
		String keyword = searchParam.getKeyword();
		if (keyword != null) {
			keyword = keyword.trim();
			searchParam.setKeyword(keyword);
		}
		String ip = getIpAddr(request);
		String platform = request.getParameter("use_platform");
		String page = searchParam.getPage();
		String r_sort = searchParam.getR_sort();
		String bid = request.getParameter("b");
		String brand = searchParam.getBrand();
		if (!StringUtil.isEmptyByString(brand) && !StringUtil.isEmptyByString(bid)) {
			if (!brand.equals(bid)) {
				String[] brands = brand.split(",");
				List<String> list = Arrays.asList(brands);
				if (!list.contains(bid)) {
					searchParam.setBrand(brand + "," + bid);
				} else {
					StringBuffer sb = new StringBuffer();
					for (String str : list) {
						if (bid.equals(str)) {
							continue;
						}
						sb.append(str).append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					searchParam.setBrand(sb.toString());
				}
			} else {
				searchParam.setBrand(null);
			}
		} else if (StringUtil.isEmptyByString(brand) && !StringUtil.isEmptyByString(bid)) {
			searchParam.setBrand(bid);
		}
		model.addObject("brands", searchParam.getBrand());
		
		String pid = request.getParameter("p");
		String prop = searchParam.getProp();
		if (!StringUtil.isEmptyByString(prop) && !StringUtil.isEmptyByString(pid)) {
			if (!prop.equals(pid)) {
				String[] props = prop.split(",");
				List<String> list = Arrays.asList(props);
				if (!list.contains(pid)) {
					searchParam.setBrand(prop + "," + pid);
				} else {
					StringBuffer sb = new StringBuffer();
					for (String str : list) {
						if (pid.equals(str)) {
							continue;
						}
						sb.append(str).append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					searchParam.setProp(sb.toString());
				}
			} else {
				searchParam.setProp(null);
			}
		} else if (StringUtil.isEmptyByString(prop) && !StringUtil.isEmptyByString(pid)) {
			searchParam.setProp(pid);
		}
		model.addObject("prop", searchParam.getProp());
		long totalCount = 0;
		model.addObject("widget", getWideget());
		
		// 拼接返回URL
		StringBuffer url = request.getRequestURL();
		url.append("?");
		if (StringUtil.isEmptyByString(keyword)) {
			model.addObject("url", url.deleteCharAt(url.length()-1));
			// 记录搜索日志
			addSearchLog(keyword, ip, totalCount, platform);
			return model;
		} else {
			url.append("keyword=").append(keyword).append("&");
			model.addObject("keyword", keyword);
		}
		
		QueryIndexResult results = getQueryResult(searchParam);
		if (results == null) {
			// 记录搜索日志
			addSearchLog(keyword, ip, totalCount, platform);
			return model;
		}
		totalCount = results.getTotalCount();
		
		model.addObject("results", results);
		
		StringBuffer a = new StringBuffer(JSONObject.toJSONString(results));
		System.out.println(a);
		
		// 参数回显示
		if (!StringUtil.isEmptyByString(bid) || !StringUtil.isEmptyByString(brand)) {
			// 操作类型	增加：add， 移除：rm
			String opt = request.getParameter("opt");
			// 已选品牌ID
			Set<String> set = new HashSet<String>();
			if (!StringUtil.isEmptyByString(brand)) {
				String[] brands = brand.split(",");
				List<String> list = Arrays.asList(brands);
				set.addAll(list);
				set.add(bid);
				if ("add".equals(opt)) {
					for (String b : list) {
						url.append(b).append(",");
					}
				} else if ("rm".equals(opt)) {
					for (String b : list) {
						// 判断当前选择品牌是否已选择
						if (b.equals(bid)) {
							continue;
						}
					}
					set.remove(bid);
				}
			} else {
				set.add(bid);
			}
			model.addObject("chkbrand", set);
		}
		
		String catId = searchParam.getCid3();
		if (catId != null && catId.trim().length() > 0) {
			List<Cat> cats = results.getCats();
			for (Cat cat : cats) {
				if (cat.getCatId().equals(catId)) {
					url.append("cid3=").append(catId).append("&");
					model.addObject("cat", cat);
					break;
				}
			}
		}
		
		if (!StringUtil.isEmptyByString(r_sort)) {
			model.addObject("orderBy", searchParam.getR_sort());
		}
		if (!StringUtil.isEmptyByString(page) && Integer.parseInt(page) > 1) {
			model.addObject("page", page);
		}
		model.addObject("url", url.deleteCharAt(url.length()-1));
		
		List<Brand> brands = results.getBrands();
		StringBuffer seo = new StringBuffer();
		for (int i = 0; i < brands.size(); i++) {
			if (i >= 8) {
				break;
			}
			seo.append(brands.get(i).getBrand()).append(",");
		}
		if (seo.length() > 0) {
			seo.deleteCharAt(seo.length() - 1);
			model.addObject("seo", seo);
		}
		
		PageView pageView = new PageView(Integer.parseInt(page));
		pageView.setTotalCount((int) totalCount);
		pageView.setPageSize(SolrConstant.PAGE_LIMIT_FORTY);
		model.addObject("pageView", pageView);
		
		// 统计记录
		addSearchLog(keyword, ip, totalCount, results.getStatus());
		return model;
	}

	/**
	 * 增加搜索日志
	 * @param keyword
	 * @param ip
	 * @param totalCount
	 * @param type
	 * @return
	 */
	private void addSearchLog(String keyword, String ip, long totalCount, String type) {
		JSONObject obj = new JSONObject();
		obj.put("keyword", keyword);
		obj.put("ip", ip);
		obj.put("resultNum", totalCount);
		obj.put("type", type);
		obj.put("searchTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		log.info(obj.toString());
	}
	
	/**
	 * 获取查询结果
	 * @param searchParam
	 * @return
	 */
	private QueryIndexResult getQueryResult(SearchParam searchParam) {
		try {
			String page = searchParam.getPage();
			// 处理用户传递非法页码信息
			if (!StringUtil.checkNumber(page, "+")) {
				searchParam.setPage("1");
			}
			QueryIndexResult qir = productIndexService.query(searchParam);
			
			Long totalCount = qir.getTotalCount();
			if (totalCount == 0) {
				return null;
			}
			// 用户传递页码参数大于查询总页数时显示最后一页信息
			Integer totalPage = (int) Math.ceil(totalCount/(double)searchParam.getRows());
			if (Integer.parseInt(page) > totalPage) {
				searchParam.setPage(totalPage.toString());
				qir = productIndexService.query(searchParam);
			}
			return qir;
		} catch (Exception e) {
			log.error("查询索引库信息异常，异常信息", e);
		}
		return null;
	}

	/**
	 * 查询挂件信息
	 * @return
	 */
	private WidgetResult getWideget() {
		try {
			return widgetService.queryWidgets();
		} catch (Exception e) {
			log.error("查询挂件信息异常", e);
		}
		return null;
	}
}
