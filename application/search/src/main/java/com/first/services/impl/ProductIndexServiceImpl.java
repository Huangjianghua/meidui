package com.first.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.first.constant.SolrConstant;
import com.first.dao.CatIndexDao;
import com.first.dao.ProductIndexDao;
import com.first.domain.Item;
import com.first.pojo.Brand;
import com.first.pojo.Cat;
import com.first.pojo.CatModel;
import com.first.pojo.PropValue;
import com.first.pojo.Property;
import com.first.pojo.QueryIndexResult;
import com.first.pojo.SearchParam;
import com.first.pojo.ShopInfo;
import com.first.services.ProductIndexService;
import com.first.services.SuggestService;
import com.first.utility.StringUtil;

@Service
public class ProductIndexServiceImpl implements ProductIndexService {

	@Resource
	private ProductIndexDao productIndexDao;
	
	@Resource
	private SuggestService suggestService;
	
	@Resource
	private CatIndexDao catIndexDao;
	
	@Override
	public QueryIndexResult query(SearchParam searchParam) throws Exception {
		String keyword = searchParam.getKeyword();
		String brand = searchParam.getBrand();
		SolrQuery params = new SolrQuery();
		int rows = searchParam.getRows();
		params.setRows(rows);
		params.setFacet(true);
		params.setFacetMinCount(1);
		params.setFacetLimit(10);
		params.addFacetField("brandInfo");
		params.addFacetField("catInfo");
		
		QueryResponse response = null;
		List<Brand> brands = null;
		// 搜索词为空时查询所有
		if (keyword == null) {
			params.setQuery("*:*");
			response = productIndexDao.query(params);
			if (!response.getResults().isEmpty()) {
				response = getQueryResponse(params, searchParam);
				return getResult(response, "a");
			}
		}
		// 选择查询全部结果
		if ("all".equals(searchParam.getSc_type())) {
			String q = addBrandFilter(keyword, brand);
			response = getBaseResponse(q, params);
			if (!response.getResults().isEmpty()) {
				brands = getFacetQuery(keyword);
				response = getQueryResponse(params, searchParam);
				QueryIndexResult result = getResult(response, "at");
				brands = getBrandFacet(response);
				result.setBrands(brands);
				return result;
			}
		}
		
		String REGEX="^[a-z;]+$";
		Matcher m = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE).matcher(keyword);
		if (m.find()) {
			String querySuggest = suggestService.querySuggest(keyword);
			if (querySuggest != null) {
				keyword = querySuggest;
				searchParam.setKeyword(keyword);
			}
		}
		// 查询返回品牌结果
		String bq = "brand:" + keyword + " AND text:" + keyword;
		String query = addBrandFilter(bq, brand);
		response = getBaseResponse(query, params);
		if (!response.getResults().isEmpty()) {
			brands = getFacetQuery(bq);
			response = getQueryResponse(params, searchParam);
			QueryIndexResult result = getResult(response, "b");
			brands = getBrandFacet(response);
			result.setBrands(brands);
			return result;
		}
		// 查询获取类目索引信息
		CatModel catModel = catIndexDao.getCatModelByQuery("catName:" + keyword);
		if (catModel != null) {
			int cid = catModel.getCatId();
			String qr = addBrandFilter("catPath:" + cid, brand);
			response = getBaseResponse(qr, params);
			if (!response.getResults().isEmpty()) {
				brands = getFacetQuery("catPath:" + cid);
				response = getQueryResponse(params, searchParam);
				QueryIndexResult result = getResult(response, "c");
				result.setCatModel(catModel);
				result.setBrands(brands);
				return result;
			}
		}
		
		String catQuery = catQuery(keyword);
		String qr = addBrandFilter(catQuery, brand);
		response = getBaseResponse(qr, params);
		if (!response.getResults().isEmpty()) {
			brands = getFacetQuery(catQuery);
			response = getQueryResponse(params, searchParam);
			QueryIndexResult result = getResult(response, "gc");
			result.setBrands(brands);
			return result;
		}
		// 加空格多条件查询
		if (keyword.trim().contains(" ")) {
			String[] keys = keyword.trim().split(" ");
			String kw = "";
			String word = "";
			for (String key : keys) {
				params.setQuery("props_cp:" + key);
				response = productIndexDao.query(params);
				if (!response.getResults().isEmpty()) {
					kw += "props_cp:" + key + SolrConstant.AND_CHAR;
				} else {
					word += key + " ";
				}
			}
			String q = addBrandFilter(kw + word, brand);
			response = getBaseResponse(q, params);
			if (!response.getResults().isEmpty()) {
				brands = getFacetQuery(kw + word);
				response = getQueryResponse(params, searchParam);
				QueryIndexResult result = getResult(response, "p");
				brands = getBrandFacet(response);
				result.setBrands(brands);
				return result;
			}
		}

		// 查询返回结果
		query = addBrandFilter("shop_cp:" + keyword, brand);
		response = getBaseResponse(query, params);
		if (!response.getResults().isEmpty()) {
			brands = getFacetQuery("shop_cp:" + keyword);
			response = getQueryResponse(params, searchParam);
			QueryIndexResult result = getResult(response, "s");
			result.setBrands(brands);
			return result;
		}
		// 默认查询
		String q = "suggest:" + keyword;
		query = addBrandFilter(q, brand);
		response = getBaseResponse(query, params);
		brands = getFacetQuery(q);
		response = getQueryResponse(params, searchParam);
		QueryIndexResult result = getResult(response, "t");
		result.setBrands(brands);
		// 查询返回结果
		return result;
	}
	
	private String catQuery(String q) {
		StringBuilder query = new StringBuilder("(");
		try {
			// 查询获取类目索引信息
			List<CatModel> catModels = catIndexDao.getCatModels("catName_cp:" + q);
			for (CatModel cm : catModels) {
				query.append("catPath:").append(cm.getCatId()).append(SolrConstant.OR_CHAR);
			}
			if (catModels.size() > 0) {
				query = query.delete(query.length() - SolrConstant.OR_CHAR.length(), query.length() - 1).append(")");
			} else {
				query = query.deleteCharAt(query.length() - 1).append("cat:").append(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.toString();
	}
	
	private String addBrandFilter(String q, String brand) {
		if (StringUtil.isEmptyByString(brand)) {
			return q;
		}
		StringBuffer query = new StringBuffer();
		query.append(q).append(SolrConstant.AND_CHAR);
		String[] bids = brand.split(",");
		int len = bids.length;
		if (len == 1) {
			query.append("brandId:").append(bids[0]);
		} else if (len > 0) {
			for (int i = 0; i < len; i++) {
				if (i == 0) {
					query.append("(brandId:").append(bids[i]);
				} else if (i == len-1) {
					query.append(SolrConstant.OR_CHAR).append("brandId:").append(bids[i]).append(")");
				} else {
					query.append(SolrConstant.OR_CHAR).append("brandId:").append(bids[i]);
				}
			}
		}
		return query.toString();
	}
	
	private String addPropFilter(String q, String prop) {
		if (StringUtil.isEmptyByString(prop)) {
			return q;
		}
		StringBuffer query = new StringBuffer();
		query.append(q).append(SolrConstant.AND_CHAR);
		String[] pids = prop.split(",");
		int len = pids.length;
		if (len == 1) {
			String[] ps = prop.split("_");
			if (ps.length > 1) {
				query.append("attr_" + ps[0] + ":" + ps[1]);
			}
		} else if (len > 1) {
			for (int i = 0; i < len; i++) {
				String[] ps = pids[i].split("_");
				if (i == 0) {
					query.append("(attr_").append(ps[0]).append(":").append(ps[1]);
				} else if (i == len-1) {
					query.append(SolrConstant.OR_CHAR).append("attr_").append(ps[0]).append(":").append(ps[1]).append(")");
				} else {
					query.append(SolrConstant.OR_CHAR).append("attr_").append(ps[0]).append(":").append(ps[1]);
				}
			}
		}
		return query.toString();
	}
	
	@Override
	public List<Brand> getFacetQuery(String keyword) throws Exception {
		SolrQuery params = new SolrQuery(keyword);
		params.setFacet(true);
		params.setFacetMinCount(1);
		params.setFacetLimit(10);
		params.addFacetField("brandInfo");
		QueryResponse response = productIndexDao.query(params);
		return getBrandFacet(response);
	}
	
	/**
	 * 返回结果
	 * @param response
	 * @param status 
	 * @return
	 */
	private QueryIndexResult getResult(QueryResponse response, String status) {
		// 返回总记录数
		long numFound = response.getResults().getNumFound();
		// 返回结果
		QueryIndexResult qir = new QueryIndexResult();
		qir.setTotalCount(numFound);
		if (numFound == 0) {
			return qir;
		}
		// 返回分类字段信息
		List<Brand> brands = getBrandFacet(response);
		List<Cat> cats = getCatFacet(response);
		List<Property> properties = getPropFacet(response);
		List<ShopInfo> shopInfos = getShopFacet(response);
		
		// 返回商品信息
		List<Item> items = response.getBeans(Item.class);
		// 设置商品标题高亮
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		if (highlighting != null) {
			for (Item item : items) {
				item.setImgTitle(item.getTitle());
				Map<String, List<String>> hlmap = highlighting.get(item.getId());
				if (!hlmap.isEmpty()) {
					String hlString = hlmap.get("title").get(0);
					item.setTitle(hlString);
				}
			}
		}
		qir.setShopInfos(shopInfos);
		qir.setBrands(brands);
		qir.setCats(cats);
		qir.setProperties(properties);
		qir.setDataList(items);
		qir.setStatus(status);
		
		return qir;
	}
	
	private QueryResponse getBaseResponse(String query, SolrQuery params) throws Exception {
		params.setQuery(query);
		return productIndexDao.query(params);
	}
	
	/**
	 * 获取查询结果
	 * @param params		查询条件
	 * @param searchParam	查询参数
	 * @return
	 * @throws Exception
	 */
	private QueryResponse getQueryResponse(SolrQuery params, SearchParam searchParam) throws Exception {
		// 第一次查询获取默认分页匹配结果
		QueryResponse response = productIndexDao.query(params);
		SolrDocumentList results = response.getResults();
		long numFound = results.getNumFound();
		int rows = searchParam.getRows();
		if (numFound > rows) {
			params.setRows((int) numFound);
			// 如果匹配结果数量大于每次查询显示数量，开始第二次查询获取所有结果
			response = productIndexDao.query(params);
		}
		
		// 过滤查询条件
		String cid3 = searchParam.getCid3();
		if (!StringUtil.isEmptyByString(cid3)) {
			params.addFilterQuery("catId:" + cid3);
		}
		String price = searchParam.getPrice();
		if (!StringUtil.isEmptyByString(price)) {
			if (price.endsWith("gt")) {
				params.addFilterQuery("price:[" + price.replace("-", SolrConstant.TO_CHAR) + "*]");
			} else {
				params.addFilterQuery("price:[" + price.replace("-", SolrConstant.TO_CHAR) + "]");
			}
		}
		// 属性过滤查询
		String prop = searchParam.getProp();
		if (!StringUtil.isEmptyByString(prop)) {
			String[] props = prop.split(",");
			if (props.length > 1) {
				for (String p : props) {
					String[] ps = p.split("_");
					if (ps.length > 1) {
						params.addFilterQuery("attr_" + ps[0] + ":" + ps[1]);
					}
				}
			} else {
				String[] ps = prop.split("_");
				if (ps.length > 1) {
					params.addFilterQuery("attr_" + ps[0] + ":" + ps[1]);
				}
			}
		}
		
		// 增加属性分类字段
		params = addPropField(response, params);
		// 设置默认分类条件
		params.setFacet(true);
		params.setFacetMinCount(1);
		params.setFacetLimit(10);
		params.addFacetField("catInfo");
		params.addFacetField("shopInfo");
		params.addFacetField("price");
		
		// 分页查询
		int page = Integer.parseInt(searchParam.getPage());
		params.setStart((page - 1) * rows);
		params.setRows(rows);
		
		// 排序
		String r_sort = searchParam.getR_sort();
		if (r_sort != null && !"buy_count_desc".equals(r_sort)) {
			int spliteIndex = r_sort.lastIndexOf("_");
			String order = r_sort.substring(spliteIndex + 1);
			String field = r_sort.substring(0, spliteIndex);
			params.addSort(field, "asc".equals(order) ? ORDER.asc : ORDER.desc);
		} else {
			// 默认排序
			params.addSort("list_time", ORDER.desc);
			params.addSort("view_count", ORDER.desc);
			params.addSort("score", ORDER.desc);
		}
		
		// 高亮
		params.setHighlight(true);
		params.addHighlightField("title");
		params.setHighlightSimplePre("<span class=\"highlight\">");
		params.setHighlightSimplePost("</span>");
		
		// 再次查询返回响应结果
		return productIndexDao.query(params);
	}
	
	/**
	 * 获取并添加动态属性字段条件
	 * @param response
	 * @param params
	 * @return
	 */
	private SolrQuery addPropField(QueryResponse response, SolrQuery params) {
		Set<String> fields = new HashSet<String>();
		// 获取所有相对动态字段
		for (SolrDocument sd : response.getResults()) {
			Collection<String> fieldNames = sd.getFieldNames();
			for (String field : fieldNames) {
				if (field.startsWith("prop_")) {
					fields.add(field);
				}
			}
		} 
		// 添加动态查询字段Facet
		for (String field : fields) {
			params.addFacetField(field);
		}
		return params;
	}
	
	private List<ShopInfo> getShopFacet(QueryResponse response) {
		// 获取店铺Facet字段
		FacetField shopInfoField = response.getFacetField("shopInfo");
		List<Count> shopValues = shopInfoField.getValues();
		List<ShopInfo> shopInfos = new ArrayList<ShopInfo>();
		for (Count count : shopValues) {
			String info = count.getName();
			String[] infos = info.split("_");
			if (infos.length == 7) {
				ShopInfo shopInfo = new ShopInfo();
				shopInfo.setShopId(infos[0]);
				shopInfo.setShopName(infos[1]);
				String shopDescript = infos[2];
				shopInfo.setShopDescript(shopDescript);
				shopInfo.setShopLogo(infos[3]);
				shopInfo.setTallyScore(infos[4]);
				shopInfo.setAttitudeScore(infos[5]);
				shopInfo.setDeliverySpeedScore(infos[6]);
				
				shopInfos.add(shopInfo);
			}
		}
		return shopInfos;
	}
	
	private List<Brand> getBrandFacet(QueryResponse response) {
		// 获取品牌Facet字段
		FacetField brandField = response.getFacetField("brandInfo");
		List<Count> brandValues = brandField.getValues();
		List<Brand>	brands = new ArrayList<Brand>();
		for (Count count : brandValues) {
			String name = count.getName();
			String[] names = name.split("_");
			if (names.length == 2) {
				Brand brand = new Brand();
				brand.setBrandId(names[0]);
				brand.setBrand(names[1]);
				brand.setCount(count.getCount());
				brands.add(brand);
			}
		}
		return brands;
	}
	
	private List<Cat> getCatFacet(QueryResponse response) {
		// 获取类目Facet字段
		FacetField catInfoField = response.getFacetField("catInfo");
		List<Count> counts = catInfoField.getValues();
		List<Cat> cats = new ArrayList<Cat>();
		for (Count count : counts) {
			String name = count.getName();
			String[] names = name.split("_");
			if (names.length == 3) {
				Cat cat = new Cat();
				cat.setCatParentId(names[0]);
				cat.setCatId(names[1]);
				cat.setCatName(names[2]);
				
				cats.add(cat);
			}
		}
		return cats;
	}
	
	private List<Property> getPropFacet(QueryResponse response) {
		// 获取所有Facet字段
		List<FacetField> facetFields = response.getFacetFields();
		List<Property> properties = new ArrayList<Property>();
		for (FacetField field : facetFields) {
			// 获取属性名以及属性名ID
			String fieldName = field.getName();
			if (fieldName.startsWith("prop_")) {
				// 分割属性名
				String[] propNames = fieldName.split("_");
				Property property = new Property();
				property.setPropId(propNames[1]);
				property.setPropName(propNames[2]);
				
				// 获取属性值列表
				List<Count> values = field.getValues();
				List<PropValue> propValueList = new ArrayList<PropValue>();
				for (Count count : values) {
					// 分割属性值信息
					String name = count.getName();
					String[] propValues = name.split("_");
					if (propValues.length == 2) {
						PropValue pv = new PropValue();
						pv.setPropValueId(propValues[0]);
						pv.setPropValue(propValues[1]);
						pv.setCount(count.getCount());
						propValueList.add(pv);
					}
				}
				if (!propValueList.isEmpty()) {
					property.setPropertiesDetail(propValueList);
					properties.add(property);
				}
			}
		}
		return properties;
	}

	@Override
	public QueryIndexResult list(SearchParam searchParam) throws Exception {
		Integer catId = searchParam.getCat_id();
		String q = addBrandFilter("catPath:" + catId, searchParam.getBrand());
		String prop = searchParam.getProp();
		q = addPropFilter(q, prop);
		SolrQuery params = new SolrQuery(q);
		params.setStart((Integer.parseInt(searchParam.getPage()) - 1) * SolrConstant.PAGE_LIMIT_THIRTY);
		params.setRows(SolrConstant.PAGE_LIMIT_THIRTY);

		QueryResponse response = productIndexDao.query(params);
		// 增加属性分类字段
		params = addPropField(response, params);
		// 设置默认分类条件
		params.setFacet(true);
		params.setFacetMinCount(1);
		params.setFacetLimit(10);
		params.addFacetField("brandInfo");
		params.addFacetField("catInfo");
		params.addFacetField("shopInfo");
		params.addFacetField("price");
		// 默认排序
		params.addSort("score", ORDER.desc);
		params.addSort("list_time", ORDER.desc);
		params.addSort("view_count", ORDER.desc);
		response = productIndexDao.query(params);
		CatModel catModel = catIndexDao.getCatModelByQuery("catId:" + catId);
		QueryIndexResult result = getResult(response, "l");
		result.setCatModel(catModel);
		params.setQuery("catPath:" + catId);
		response = productIndexDao.query(params);
		QueryIndexResult qr = getResult(response, "l");
		result.setBrands(qr.getBrands());
		result.setCats(null);
		result.setProperties(qr.getProperties());
		return result;
	}
	
	@Override
	public Item queryById(String id) throws Exception {
		SolrQuery params = new SolrQuery();
		params.setQuery("itemId:" + id);
		QueryResponse queryResponse = productIndexDao.query(params);
		List<Item> items = queryResponse.getBeans(Item.class);
		Item item = null;
		if (items.size() > 0) {
			item = items.get(0);
		}
		return item;
	}
	
}
