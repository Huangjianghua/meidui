package com.first.controller;

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
import com.first.constant.SolrConstant;
import com.first.domain.Item;
import com.first.page.PageView;
import com.first.pojo.AppSearchParam;
import com.first.pojo.Brand;
import com.first.pojo.Cat;
import com.first.pojo.MeiduiPoint;
import com.first.pojo.NewestPrice;
import com.first.pojo.QueryIndexResult;
import com.first.pojo.SearchParam;
import com.first.pojo.WidgetResult;
import com.first.services.AppProductIndexService;
import com.first.services.MeiduiPointService;
import com.first.services.NewestPriceService;
import com.first.services.WidgetService;
import com.first.utility.PointCalculateUtil;
import com.first.utility.StringUtil;

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
			
//			// 获取所有商品ID
//			List<Item> dataList = qir.getDataList();
//			List<Integer> ids = new ArrayList<Integer>(SolrConstant.PAGE_LIMIT_FORTY);
//			for (Item item : dataList) {
//				String itemId = item.getItemId();
//				ids.add(Integer.parseInt(itemId));
//			}
//			// 根据ID集合查询可用积分数量及实时价格
//			LinkedList<NewestPrice> newestPrices = newestPriceService.getNewestPrices(ids);
//			List<Item> items = new ArrayList<Item>(SolrConstant.PAGE_LIMIT_FORTY);
//			// 临时表，用存储两个集合不同元素
//			LinkedList<Item> itemList = new LinkedList<Item>();
//			
//			MeiduiPoint meiduiPoint = meiduiPointService.queryMeiduiPoint();
//			
//			for (Item item : dataList) {
//				itemList.add(item);
//				Iterator<NewestPrice> iterator = newestPrices.iterator();
//				while (iterator.hasNext()) {
//					NewestPrice newestPrice = iterator.next();
//					if (Integer.parseInt(item.getItemId()) == newestPrice.getItemId()) {
//						double point = PointCalculateUtil.getPoint(meiduiPoint.getValue(), newestPrice.getPrice(), newestPrice.getCostPrice());
//						if (point == -1) {
//							item.setPoint(newestPrice.getPrice());
//						} else {
//							item.setPoint(point);
//						}
//						item.setPrice(newestPrice.getPrice());
//						items.add(item);
//						// 如sku价格存在则移除列表头元素
//						itemList.removeFirst();
//						// 价格列表中移除该商品信息
//						newestPrices.remove(newestPrice);
//						break;
//					}
//				}
//			}
//			if (!itemList.isEmpty()) {
//				items.addAll(itemList);
//			}
//			qir.setDataList(items);
			return qir;
		} catch (Exception e) {
			log.error("查询索引库信息异常，异常信息", e);
		}
		return null;
	}
}
