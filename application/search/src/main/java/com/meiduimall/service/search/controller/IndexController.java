package com.meiduimall.service.search.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.meiduimall.service.search.constant.SysConstant;
import com.meiduimall.service.search.request.IndexRequest;
import com.meiduimall.service.search.service.IndexService;

/**
 * 索引操作
 * 
 * @date 2016年5月4日
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private IndexService indexService;

	@RequestMapping(value = "indexOptView")
	public String indexOptView() {
		return "search/indexOpt";
	}

	/**
	 * 第一次将所有数据库信息增加到索引库
	 * 
	 * @param number
	 *            每页更新数量 
	 * @return
	 */
	@RequestMapping(value = "/updateIndex")
	public Object updateIndex(String number) {
		Map<String, Object> result = null;
		int num = SysConstant.PAGE_LIMIT_INDEX;
		if (!Strings.isNullOrEmpty(number)) {
			num = Integer.parseInt(number);
		}
		long start = System.currentTimeMillis();
		result = indexService.addSuggestIndex(num);
		result = indexService.addCatlogIndex(num);
		result = indexService.addProductIndex(num);
		long end = System.currentTimeMillis();
		result.put(SysConstant.RESULT_MSG, "初始化索引成功，共耗时：" + (end - start) / 1000.0 + "秒！");
		return result;
	}

	/**
	 * 类目索引
	 * 
	 * @param params
	 *            请求参数封装
	 * @return
	 */
	@RequestMapping(value = "catlogIndex", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public Object catlogIndex(@Validated IndexRequest params) {
		Map<String, Object> result = null;
		switch (params.getOpt()) {
		case "a":
			result = indexService.addCatlogIndex(params.getNumber());
			break;
		case "u":
			result = indexService.addCatlogIndexById(Integer.parseInt(params.getQ()));
			break;
		case "d":
			result = indexService.deleteCatlogIndexByItemId(params.getQ());
			break;
		case "dq":
			result = indexService.deleteCatlogIndexByQuery(params.getQ());
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 商品信息索引
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "productIndex", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object productIndex(@Validated IndexRequest request) {
		Map<String, Object> result = null;
		switch (request.getOpt()) {
		case "a":
			result = indexService.addProductIndex(request.getNumber());
			break;
		case "u":
			result = indexService.addProductIndexById(Integer.parseInt(request.getQ()));
			break;
		case "d":
			result = indexService.deleteProductIndexByItemId(request.getQ());
			break;
		case "dq":
			result = indexService.deleteProductIndexByQuery(request.getQ());
			break;

		default:
			break;
		}
		return result;
	}

	/**
	 * 提示信息索引
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "suggestIndex", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object suggestIndex(@Validated IndexRequest request) {
		Map<String, Object> result = null;
		switch (request.getOpt()) {
		case "a":
			result = indexService.addSuggestIndex(request.getNumber());
			break;
		case "u":
			result = indexService.addSuggestIndexById(request.getQ());// TODO
																			// 这里加了一个0
			break;
		case "ui":
			result = indexService.addSuggestIndexById(Integer.parseInt(request.getQ()));
			break;
		case "d":
			result = indexService.deleteSuggestIndexById(request.getQ());
			break;

		default:
			break;
		}
		return result;
	}

}
