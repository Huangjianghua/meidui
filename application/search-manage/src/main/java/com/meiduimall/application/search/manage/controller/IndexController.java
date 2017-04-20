package com.meiduimall.application.search.manage.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.request.IndexRequest;
import com.meiduimall.application.search.manage.services.IndexService;
import com.meiduimall.application.search.manage.utility.StringUtil;

/**
 * 索引操作
 * @date 2016年5月4日
 */
@RestController("index")
public class IndexController {
	
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
	public Object updateIndex(String number) {
		Map<String, Object> result = null;
		int num = SysConstant.PAGE_LIMIT_INDEX;
		if (!StringUtil.isEmptyByString(number)) {
			num = Integer.parseInt(number);
		}
		long start = System.currentTimeMillis();
		result = indexService.addSuggestIndex(num);
		result = indexService.addCatlogIndex(num);
		result = indexService.addProductIndex(num);
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
	public Object catlogIndex(@Validated IndexRequest request) {
		Map<String, Object> result = null;
		switch (request.getOpt()) {
		case "a":
			result = indexService.addCatlogIndex(request.getNumber());
			break;
		case "u":
			result = indexService.addCatlogIndexById(Integer.parseInt(request.getQ()));
			break;
		case "d":
			result = indexService.deleteCatlogIndexByItemId(request.getQ());
			break;
		case "dq":
			result = indexService.deleteCatlogIndexByQuery(request.getQ());
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
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "suggestIndex", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public Object suggestIndex(@Validated IndexRequest request) throws Exception {
		Map<String, Object> result = null;
		switch (request.getOpt()) {
		case "a":
			result = indexService.addSuggestIndex(request.getNumber());
			break;
		case "u":
			result = indexService.addSuggestIndexById(request.getQ());
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
