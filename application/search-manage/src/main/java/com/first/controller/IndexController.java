package com.first.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first.constant.SysConstant;
import com.first.services.IndexService;
import com.first.utility.StringUtil;

/**
 * 索引操作
 * @date 2016年5月4日
 */
@Controller
@RequestMapping("index")
public class IndexController extends BaseController {
	
	@RequestMapping(value = "indexOptView", method = RequestMethod.GET)
	public String indexOptView() {
		return "search/indexOpt";
	}
	
	@Autowired
	private IndexService indexService;
	
	/**
	 * 第一次将所有数据库信息增加到索引库
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateIndex", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object updateIndex(HttpServletRequest request) {
		Map<String, Object> result = null;
		int number = SysConstant.PAGE_LIMIT_INDEX;
		String numStr = request.getParameter("number");
		if (!StringUtil.isEmptyByString(numStr)) {
			number = Integer.parseInt(numStr);
		}
		long start = System.currentTimeMillis();
		result = indexService.addSuggestIndex(number);
		result = indexService.addCatlogIndex(number);
		result = indexService.addProductIndex(number);
		long end = System.currentTimeMillis();
		result.put(SysConstant.RESULT_MSG, "初始化索引成功，共耗时：" + (end-start)/1000.0 + "秒！");
		return result;
	}
	
	/**
	 * 类目索引
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "catlogIndex", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object catlogIndex(HttpServletRequest request) {
		Map<String, Object> result = null;
		String opt = request.getParameter("opt");
		if (StringUtil.isEmptyByString(opt)) {
			result = new HashMap<String, Object>();
			result.put(SysConstant.STATUS_CODE, 0);
			result.put(SysConstant.RESULT_MSG, "操作类型不能为空");
			return result;
		}
		String q = request.getParameter("q");
		if (!"a".equals(opt) && StringUtil.isEmptyByString(q)) {
			result = new HashMap<String, Object>();
			result.put(SysConstant.STATUS_CODE, 0);
			result.put(SysConstant.RESULT_MSG, "操作条件不能为空");
			return result;
		}
		int number = SysConstant.PAGE_LIMIT_INDEX;
		String numStr = request.getParameter("number");
		if (!StringUtil.isEmptyByString(numStr)) {
			number = Integer.parseInt(numStr);
		}
		switch (opt) {
		case "a":
			result = indexService.addCatlogIndex(number);
			break;
		case "u":
			result = indexService.addCatlogIndexById(Integer.parseInt(q));
			break;
		case "d":
			result = indexService.deleteCatlogIndexByItemId(q);
			break;
		case "dq":
			result = indexService.deleteCatlogIndexByQuery(q);
			break;
			
		default:
			break;
		}
		return result;
	}
	
	/**
	 * 商品信息索引
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "productIndex", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object productIndex(HttpServletRequest request) {
		Map<String, Object> result = null;
		String opt = request.getParameter("opt");
		if (StringUtil.isEmptyByString(opt)) {
			result = new HashMap<String, Object>();
			result.put(SysConstant.STATUS_CODE, 0);
			result.put(SysConstant.RESULT_MSG, "操作类型不能为空");
			return result;
		}
		String q = request.getParameter("q");
		if (!"a".equals(opt) && StringUtil.isEmptyByString(q)) {
			result = new HashMap<String, Object>();
			result.put(SysConstant.STATUS_CODE, 0);
			result.put(SysConstant.RESULT_MSG, "操作条件不能为空");
			return result;
		}
		int number = SysConstant.PAGE_LIMIT_INDEX;
		String numStr = request.getParameter("number");
		if (!StringUtil.isEmptyByString(numStr)) {
			number = Integer.parseInt(numStr);
		}
		switch (opt) {
		case "a":
			result = indexService.addProductIndex(number);
			break;
		case "u":
			result = indexService.addProductIndexById(Integer.parseInt(q));
			break;
		case "d":
			result = indexService.deleteProductIndexByItemId(q);
			break;
		case "dq":
			result = indexService.deleteProductIndexByQuery(q);
			break;

		default:
			break;
		}
		return result;
	}
	
	/**
	 * 提示信息索引
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "suggestIndex", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object suggestIndex(HttpServletRequest request) throws Exception {
		Map<String, Object> result = null;
		String opt = request.getParameter("opt");
		if (StringUtil.isEmptyByString(opt)) {
			result = new HashMap<String, Object>();
			result.put(SysConstant.STATUS_CODE, 0);
			result.put(SysConstant.RESULT_MSG, "操作类型不能为空");
			return result;
		}
		String q = request.getParameter("q");
		if (!"a".equals(opt) && StringUtil.isEmptyByString(q)) {
			result = new HashMap<String, Object>();
			result.put(SysConstant.STATUS_CODE, 0);
			result.put(SysConstant.RESULT_MSG, "操作条件不能为空");
			return result;
		}
		int number = SysConstant.PAGE_LIMIT_INDEX;
		String numStr = request.getParameter("number");
		if (!StringUtil.isEmptyByString(numStr)) {
			number = Integer.parseInt(numStr);
		}
		switch (opt) {
		case "a":
			result = indexService.addSuggestIndex(number);
			break;
		case "u":
			result = indexService.addSuggestIndexById(q);
			break;
		case "ui":
			result = indexService.addSuggestIndexById(Integer.parseInt(q));
			break;
		case "d":
			result = indexService.deleteSuggestIndexById(q);
			break;
			
		default:
			break;
		}
		return result;
	}
	
}
