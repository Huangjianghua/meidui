package com.meiduimall.application.search.manage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.page.QueryResult;
import com.meiduimall.application.search.manage.pojo.Scanner;
import com.meiduimall.application.search.manage.services.IndexService;
import com.meiduimall.application.search.manage.services.ProductIndexService;
import com.meiduimall.application.search.manage.services.ScannerService;
import com.meiduimall.application.search.manage.utility.StringUtil;

@Controller
@RequestMapping("scanner")
public class ScannerController {

	private Logger log = Logger.getLogger(ScannerController.class);
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private ScannerService scannerService;
	
	@Autowired
	private ProductIndexService productIndexService;
	
	
	
	@RequestMapping(value = "queryWaitScanner")
	public ModelAndView queryWaitScanner(String currentPage) {
		
		ModelAndView mav = new ModelAndView();
		int page = 1;
		if (!StringUtil.isEmptyByString(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		PageView pageView = new PageView(page);
		List<Scanner> scanners = scannerService.queryScanners(pageView);
		long count = scannerService.queryScannerCount();
		QueryResult result = new QueryResult();
		result.setDateList(scanners);
		result.setTotalCount((int)count);
		pageView.setQueryResult(result);
		mav.addObject("pageView", pageView);
		mav.setViewName("search/scannerList");
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "manualUpdateAll")
	public ModelAndView manualUpdateAll() {
		ModelAndView mav = new ModelAndView("redirect:/scanner/queryWaitScanner.do");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("开始手动更新数据库和索引库未同步信息，开始时间： " + sdf.format(new Date()));
		int flagId = -1;
		int count = 0;
		String table = "";
		// 查询所有数据库商品ID
		List<Scanner> scanners = scannerService.queryScanners(null);
		// 已删除ID
		List<Integer> deletedIds = new ArrayList<Integer>();
		for (Scanner scanner : scanners) {
			flagId = scanner.getScanId();
			// 更新表：1、item，2、brand，3、cat，4、shop
			table = scanner.getTableName();
			Integer updateId = scanner.getUpdateId();
			String field = "";
			switch (table) {
			case "item":
				field = "itemId";
				break;
			case "brand":
				field = "brandId";
				break;
			case "cat":
				field = "catId";
				break;
			case "shop":
				field = "shopId";
				break;
				
			default:
				break;
			}
			// 将已更新内容ID添加到已删除集合中
			deletedIds.add(flagId);
			
			// 根据条件查询符合的索引信息
			List<String> idList = productIndexService.queryIndexByQuery(field + ":" + updateId);
			for (String itemId : idList) {
				if (itemId != null) {
					indexService.addProductIndexById(Integer.parseInt(itemId));
					//如果是类目同时修改类目索引
					if (table.equals("cat")){
						indexService.addCatlogIndexById(updateId);
					}
					count++;
				}
			}
		}
		if (flagId == -1) {
			return mav;
		}
		// 同步结束 删除已扫描记录
		int deleteNum = scannerService.deleteScanned(deletedIds);
		if (deleteNum > 0) {
			log.info("成功删除已扫描表，共操作" + count + "条记录,结束时间：" + sdf.format(new Date()));
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "manualUpdate")
	public ModelAndView manualUpdate(String id) {
		ModelAndView mav = new ModelAndView("redirect:/scanner/queryWaitScanner.do");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("开始手动更新数据库和索引库未同步信息，开始时间： " + sdf.format(new Date()));
		if (StringUtil.isEmptyByString(id)) {
			mav.addObject(SysConstant.RESULT_MSG, "id不能为空");
			log.error("手动更新数据库和索引库未同步信息记录ID为空");
			return mav;
		}
		int ids = Integer.parseInt(id);
		int count = 0;
		String table = "";
		// 查询所有数据库商品ID
		Scanner scanner = scannerService.queryScannerById(20);
		// 更新表：1、item，2、brand，3、cat，4、shop
		table = scanner.getTableName();
		Integer updateId = scanner.getUpdateId();
		String field = "";
		switch (table) {
		case "item":
			field = "itemId";
			break;
		case "brand":
			field = "brandId";
			break;
		case "cat":
			field = "catId";
			break;
		case "shop":
			field = "shopId";
			break;

		default:
			break;
		}
		// 根据条件查询符合的索引信息
		List<String> idList = productIndexService.queryIndexByQuery(field + ":" + updateId);
		for (String itemId : idList) {
			if (itemId != null) {
				indexService.addProductIndexById(Integer.parseInt(itemId));
				// 如果是类目同时修改类目索引
				if ("cat".equals(table)) {
					indexService.addCatlogIndexById(updateId);
				}
				count++;
			}
		}
		// 已删除ID
		List<Integer> deletedIds = new ArrayList<Integer>();
		deletedIds.add(ids);
		// 同步结束 删除已扫描记录
		int deleteNum = scannerService.deleteScanned(deletedIds);
		if (deleteNum > 0) {
			log.info("成功删除已扫描表，共操作" + count + "条记录,结束时间：" + sdf.format(new Date()));
		}
		return mav;
	}
	
}
