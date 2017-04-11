package com.meiduimall.application.search.manage.controller;

import java.util.ArrayList;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.oauth.OAuth;
import com.meiduimall.application.search.manage.oauth.OAuthAccessor;
import com.meiduimall.application.search.manage.oauth.OAuthConsumer;
import com.meiduimall.application.search.manage.oauth.OAuthMessage;
import com.meiduimall.application.search.manage.oauth.OAuth.Parameter;
import com.meiduimall.application.search.manage.oauth.provider.core.OAuthProvider;
import com.meiduimall.application.search.manage.services.IndexService;
import com.meiduimall.application.search.manage.utility.LoadPropertyUtil;
import com.meiduimall.application.search.manage.utility.StringUtil;

/**
 * 索引操作
 * @date 2016年5月4日
 */
@Controller
@RequestMapping("Authorized")
public class AuthorizationController extends BaseController {

	private Logger log = Logger.getLogger(AuthorizationController.class);
	
	// 更新产品索引URL
	private String updateProductIndexUrl = "";
	
	// 根据ID更新产品索引URL
	private String updateProductIndexByIdUrl = "";
	
	// 删除产品索引URL
	private String deleteProductIndexUrl = "";
	
	// 更新提示索引URL
	private String updateSuggestIndexUrl = "";
	
	// 根据ID更新提示索引URL
	private String updateSuggestIndexByIdUrl = "";
	
	// 删除提示索引URL
	private String deleteSuggestIndexUrl = "";
	
	// 更新类目索引URL
	private String updateCatlogIndexUrl = "";
	
	// 根据ID更新类目索引URL
	private String updateCatlogIndexByIdUrl = "";
	
	// 删除类目索引URL
	private String deleteCatlogIndexUrl = "";
	
	@Autowired
	private IndexService indexService;
	
	private void initUrl() {
		try {
			Map<String, String> propertyValues = LoadPropertyUtil.getPropertyValues("config.properties");
			if(updateProductIndexUrl.equals("")) 
				updateProductIndexUrl = propertyValues.get("update_product_index_url");
			if(updateProductIndexByIdUrl.equals(""))
				updateProductIndexByIdUrl = propertyValues.get("update_product_index_by_id_url");
			if(deleteProductIndexUrl.equals(""))
				deleteProductIndexUrl = propertyValues.get( "delete_product_index_by_id_url");
			if(updateSuggestIndexUrl.equals("")) 
				updateSuggestIndexUrl = propertyValues.get("update_product_index_url");
			if(updateSuggestIndexByIdUrl.equals(""))
				updateSuggestIndexByIdUrl = propertyValues.get("update_product_index_by_id_url");
			if(deleteSuggestIndexUrl.equals(""))
				deleteSuggestIndexUrl = propertyValues.get( "delete_product_index_by_id_url");
			if(updateCatlogIndexUrl.equals("")) 
				updateCatlogIndexUrl = propertyValues.get("update_product_index_url");
			if(updateCatlogIndexByIdUrl.equals(""))
				updateCatlogIndexByIdUrl = propertyValues.get("update_product_index_by_id_url");
			if(deleteCatlogIndexUrl.equals(""))
				deleteCatlogIndexUrl = propertyValues.get( "delete_product_index_by_id_url");
		} catch (Exception e) {
			log.info("配置文件读取异常");
			log.error("配置文件读取异常", e);
		}
	}
	

	/**
	 * 将数据库类目信息全量增加到索引库
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateCatlogIndex", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object updateCatlogIndex(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("updateCatlogIndexUrl", updateCatlogIndexUrl);
		jsonObj.put("ip", ip);
		// 每次更新记录数
		String number = jsonObj.get("number") == null ? String.valueOf(SysConstant.PAGE_LIMIT_INDEX) : jsonObj.getString("number");	
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("number") != null && !"".equals(jsonObj.get("number"))) 
			list.add(new Parameter("number", number));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addCatlogIndex = indexService.addCatlogIndex(Integer.parseInt(number));
			result = JSONObject.fromObject(addCatlogIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("数据库类目信息全量增加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("数据库类目信息全量增加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 根据ID将类目信息添加到索引库
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateCatlogIndexById", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object updateCatlogIndexById(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("updateCatlogIndexByIdUrl", updateCatlogIndexByIdUrl);
		jsonObj.put("ip", ip);
		String cat_id = jsonObj.getString("cat_id");		// 类目ID
		if (StringUtil.isEmptyByString(cat_id)) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("id为空");
			log.error("根据ID将类目信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			return result;
		}
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("cat_id") != null && !"".equals(jsonObj.get("cat_id"))) 
			list.add(new Parameter("cat_id", cat_id));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addCatlogIndex = indexService.addCatlogIndexById(Integer.parseInt(cat_id));
			result = JSONObject.fromObject(addCatlogIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("根据ID将类目信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("根据ID将类目信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 根据ID删除类目索引信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteCatlogIndexById", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object deleteCatlogIndexByItemId(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("deleteCatlogIndexUrl", deleteCatlogIndexUrl);
		jsonObj.put("ip", ip);
		String cat_id = jsonObj.getString("cat_id");		// 类目ID
		if (StringUtil.isEmptyByString(cat_id)) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("id为空");
			log.error("根据ID删除类目索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			return result;
		}
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("cat_id") != null && !"".equals(jsonObj.get("cat_id"))) 
			list.add(new Parameter("cat_id", cat_id));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addCatlogIndex = indexService.deleteCatlogIndexByItemId(cat_id);
			result = JSONObject.fromObject(addCatlogIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("根据ID删除类目索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("根据ID删除类目索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	
	/**
	 * 将数据库商品信息全量增加到索引库
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateProductIndex", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object updateProductIndex(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("updateProductIndexUrl", updateProductIndexUrl);
		jsonObj.put("ip", ip);
		// 每次更新记录数
		String number = jsonObj.get("number") == null ? String.valueOf(SysConstant.PAGE_LIMIT_INDEX) : jsonObj.getString("number");	
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("number") != null && !"".equals(jsonObj.get("number"))) 
			list.add(new Parameter("number", number));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addProductIndex = indexService.addProductIndex(Integer.parseInt(number));
			result = JSONObject.fromObject(addProductIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("数据库商品信息全量增加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("数据库商品信息全量增加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 根据ID将商品信息添加到索引库
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateProductIndexById", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object updateProductIndexById(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("updateProductIndexByIdUrl", updateProductIndexByIdUrl);
		jsonObj.put("ip", ip);
		String item_id = jsonObj.getString("item_id");		// 产品ID
		if (StringUtil.isEmptyByString(item_id)) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("id为空");
			log.error("根据ID将商品信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			return result;
		}
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("item_id") != null && !"".equals(jsonObj.get("item_id"))) 
			list.add(new Parameter("item_id", item_id));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addProductIndex = indexService.addProductIndexById(Integer.parseInt(item_id));
			result = JSONObject.fromObject(addProductIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("根据ID将商品信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("根据ID将商品信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 根据ID删除商品索引信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteProductIndexById", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object deleteProductIndexByItemId(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("deleteProductIndexUrl", deleteProductIndexUrl);
		jsonObj.put("ip", ip);
		String item_id = jsonObj.getString("item_id");		// 产品ID
		if (StringUtil.isEmptyByString(item_id)) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("id为空");
			log.error("根据ID删除商品索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			return result;
		}
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("item_id") != null && !"".equals(jsonObj.get("item_id"))) 
			list.add(new Parameter("item_id", item_id));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addProductIndex = indexService.deleteProductIndexByItemId(item_id);
			result = JSONObject.fromObject(addProductIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("根据ID删除商品索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("根据ID删除商品索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 根据条件删除商品索引信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteProductIndexByQuery", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object deleteProductIndexByQuery(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("deleteProductIndexUrl", deleteProductIndexUrl);
		jsonObj.put("ip", ip);
		String query = jsonObj.getString("query");		// 产品ID
		if (StringUtil.isEmptyByString(query)) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("id为空");
			log.error("根据条件删除商品索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			return result;
		}
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("query") != null && !"".equals(jsonObj.get("query"))) 
			list.add(new Parameter("query", query));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addProductIndex = indexService.deleteProductIndexByQuery(query);
			result = JSONObject.fromObject(addProductIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("根据条件删除商品索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("根据条件删除商品索引信息操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 更新输入提示索引
	 * @param request 
	 * @return
	 */
	@RequestMapping(value = "updateSuggestIndex", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object addSuggestIndex(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("updateSuggestIndexUrl", updateSuggestIndexUrl);
		jsonObj.put("ip", ip);
		// 每次更新记录数
		String number = jsonObj.get("number") == null ? String.valueOf(SysConstant.PAGE_LIMIT_INDEX) : jsonObj.getString("number");	
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("number") != null && !"".equals(jsonObj.get("number"))) 
			list.add(new Parameter("number", number));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addProductIndex = indexService.addSuggestIndex(Integer.parseInt(number));
			result = JSONObject.fromObject(addProductIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("输入提示索引操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("输入提示索引操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 根据主键添加输入提示索引
	 * @param request 
	 * @return
	 */
	@RequestMapping(value = "updateSuggestIndexById", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object addSuggestIndexById(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("updateSuggestIndexByIdUrl", updateSuggestIndexByIdUrl);
		jsonObj.put("ip", ip);
		String key = jsonObj.getString("key");		// 提示词
		if (StringUtil.isEmptyByString(key)) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("id为空");
			log.error("将提示信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			return result;
		}
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("key") != null && !"".equals(jsonObj.get("key"))) 
			list.add(new Parameter("key", key));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addProductIndex = indexService.addSuggestIndexById(key);
			result = JSONObject.fromObject(addProductIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("将提示信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("将提示信息添加到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 删除输入提示索引
	 * @param request 
	 * @return
	 */
	@RequestMapping(value = "deleteSuggestIndexById", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object deleteSuggestIndexById(HttpServletRequest request) {
		initUrl();
		JSONObject jsonObj = super.getReqJSONBean(request);
		JSONObject result = new JSONObject();
		
		String ip = getIpAddr(request);
		jsonObj.put("updateSuggestIndexByIdUrl", updateSuggestIndexByIdUrl);
		jsonObj.put("ip", ip);
		String key = jsonObj.getString("key");		// 提示词
		if (StringUtil.isEmptyByString(key)) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("id为空");
			log.error("将提示信息删除到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			return result;
		}
		String url = request.getRequestURL().toString();
		List<Parameter> list = buildingBasicParameters(jsonObj);
		if(jsonObj.get("key") != null && !"".equals(jsonObj.get("key"))) 
			list.add(new Parameter("key", key));
		OAuthMessage requestMessage = null;
		try {
			requestMessage = new OAuthMessage("GET", url, list);
			String signature = requestMessage.getParameter(OAuth.OAUTH_SIGNATURE);
			OAuthConsumer consumer = OAuthProvider.getConsumer(requestMessage);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.setProperty(OAuth.OAUTH_SIGNATURE, signature);
			OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
			
			Map<String, Object> addProductIndex = indexService.deleteSuggestIndexById(key);
			result = JSONObject.fromObject(addProductIndex);
			return result;
		} catch(Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.info("将提示信息删除到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数");
			log.error("将提示信息删除到索引库操作的IP地址 " + ip + "结束，服务器错误，缺少认证参数或服务器错误统一返回此参数", e);
			return result;
		}
	}
	
	/**
	 * 根据条件删除输入提示索引
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "deleteSuggestIndexByQuery", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, Object> deleteSuggestIndexByQuery(String key) {
		return indexService.deleteSuggestIndexByQuery(key);
	}
	
	/**
	 * 删除所有输入提示索引
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "deleteAllSuggestIndex", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, Object> deleteAllSuggestIndex() {
		return indexService.deleteAllSuggestIndex();
	}
	
	
	/**
	 * building basic parameter for out request
	 * 
	 * @param secret
	 * @param key
	 * @param timeStamp
	 * @param nonce
	 * @param signature
	 * @param method
	 * @param version
	 * @return
	 */
	private List<OAuth.Parameter> buildingBasicParameters(JSONObject json) {
		List<OAuth.Parameter> list = new ArrayList<OAuth.Parameter>();

		list.add(new Parameter("oauth_signature_method", json.getString("oauth_signature_method")));
		list.add(new Parameter("oauth_accessor_secret", json.getString("oauth_accessor_secret")));
		list.add(new Parameter("oauth_consumer_key", json.getString("oauth_consumer_key")));
		list.add(new Parameter("oauth_timestamp", String.valueOf(json.getString("oauth_timestamp"))));
		list.add(new Parameter("oauth_nonce", String.valueOf(json.getString("oauth_nonce"))));
		list.add(new Parameter("oauth_version", json.getString("oauth_version")));
		list.add(new Parameter("oauth_signature", json.getString("oauth_signature")));

		return list;
	}
}
