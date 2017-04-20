package com.meiduimall.application.search.manage.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.meiduimall.application.search.manage.constant.SolrConstant;
import com.meiduimall.application.search.manage.dao.CatIndexDao;
import com.meiduimall.application.search.manage.dao.ProductIndexDao;
import com.meiduimall.application.search.manage.domain.Item;
import com.meiduimall.application.search.manage.pojo.Brand;
import com.meiduimall.application.search.manage.pojo.Cat;
import com.meiduimall.application.search.manage.pojo.CatModel;
import com.meiduimall.application.search.manage.pojo.PropValue;
import com.meiduimall.application.search.manage.pojo.Property;
import com.meiduimall.application.search.manage.pojo.QueryIndexResult;
import com.meiduimall.application.search.manage.pojo.SearchParam;
import com.meiduimall.application.search.manage.pojo.ShopInfo;
import com.meiduimall.application.search.manage.services.ProductIndexService;
import com.meiduimall.application.search.manage.utility.StringUtil;

@Service
public class ProductIndexServiceImpl implements ProductIndexService {

	@Resource
	private ProductIndexDao productIndexDao;
	
	@Resource
	private CatIndexDao catIndexDao;
	
	@Override
	public QueryIndexResult query(SearchParam searchParam) throws Exception {
		SolrQuery params = new SolrQuery();
		int rows = searchParam.getRows();
		params.setRows(rows);
		String keyword = searchParam.getKeyword();
		QueryResponse response = null;
		// 搜索词为空时查询所有
		if (keyword == null) {
			params.setQuery("*:*");
			response = getQueryResponse(params, searchParam);
			QueryIndexResult result = getResult(response);
			result.setStatus("a");
			return result;
		}
		CatModel catModel = catIndexDao.getCatModelByQuery(keyword);
		String cid = null;
		if (searchParam.getCid() != null) {
			cid = searchParam.getCid();
		} else if (catModel != null) {
			cid = String.valueOf(catModel.getCatId());
		}
		if (cid != null) {
			params.setQuery("catPath:" + cid);
			response = productIndexDao.query(params);
			// 查询返回类目结果
			if (!response.getResults().isEmpty()) {
				response = getQueryResponse(params, searchParam);
				QueryIndexResult result = getResult(response);
				result.setCatModel(catModel);
				result.setStatus("c");
				return result;
			}
		}
		// 选择查询全部结果
		if ("all".equals(searchParam.getSc_type())) {
			params.setQuery("text:" + keyword);
			response = getQueryResponse(params, searchParam);
			QueryIndexResult result = getResult(response);
			result.setStatus("t");
			return result;
		}
		params.setQuery("brand:" + keyword);
		response = productIndexDao.query(params);
		// 查询返回品牌结果
		if (!response.getResults().isEmpty()) {
			response = getQueryResponse(params, searchParam);
			QueryIndexResult result = getResult(response);
			result.setStatus("b");
			return result;
		}
		params.setQuery("shopName:" + keyword);
		response = productIndexDao.query(params);
		// 查询返回店铺结果
		if (!response.getResults().isEmpty()) {
			response = getQueryResponse(params, searchParam);
			QueryIndexResult result = getResult(response);
			result.setStatus("s");
			return result;
		}
		params.setQuery("text:" + keyword);
		response = getQueryResponse(params, searchParam);
		// 以上为查询到所有结果
		QueryIndexResult result = getResult(response);
		result.setStatus("t");
		return result;
	}
	
	/**
	 * 返回结果
	 * @param response
	 * @return
	 */
	private QueryIndexResult getResult(QueryResponse response) {
		// 返回分类字段信息
		List<Brand> brands = getBrandFacet(response);
		List<Cat> cats = getCatFacet(response);
		List<Property> properties = getPropFacet(response);
		List<ShopInfo> shopInfos = getShopFacet(response);
		
		// 返回商品信息
		List<Item> items = response.getBeans(Item.class);
		// 设置商品信息高亮字段
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
		
		// 返回总记录数
		long numFound = response.getResults().getNumFound();
		// 返回结果
		QueryIndexResult qir = new QueryIndexResult();
		qir.setTotalCount(numFound);
		qir.setShopInfos(shopInfos);
		qir.setBrands(brands);
		qir.setCats(cats);
		qir.setProperties(properties);
		qir.setDataList(items);
		
		return qir;
	}
	
	/**
	 * 获取查询结果
	 * @param params		查询条件
	 * @param searchParam	查询参数
	 * @param rows			每页记录数
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
		String brand = searchParam.getBrand();
		if (!StringUtil.isEmptyByString(brand)) {
			params.addFilterQuery("brandId:" + brand);
		}
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
			String[] props = prop.split("_");
			if (props.length > 1) {
				params.addFilterQuery("attr_" + props[0] + ":" + props[1]);
			}
		}
		
		// 增加属性分类字段
		params = addPropField(response, params);
		// 设置默认分类条件
		params.setFacet(true);
		params.setFacetMinCount(1);
		params.setFacetLimit(10);
		params.addFacetField("catInfo");
		params.addFacetField("brandId");
		params.addFacetField("brandName");
		params.addFacetField("price");
		params.addFacetField("shopInfo");
		
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
			params.addSort("score", ORDER.desc);
			params.addSort("catParentName", ORDER.desc);
		}
		
		// 高亮
		params.setHighlight(true);
		params.addHighlightField("title");
		params.setHighlightSimplePre("<span class=\"highlight\">");
		params.setHighlightSimplePost("</span>");
		
		// 再次查询返回响应结果
		response = productIndexDao.query(params);
		return response;
	}
	
	/**
	 * 获取并添加动态属性字段条件
	 * @param response
	 * @param params
	 * @return
	 */
	private SolrQuery addPropField(QueryResponse response, SolrQuery params) {
		Set<String> fields = new HashSet<>();
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
			
			ShopInfo shopInfo = new ShopInfo();
			shopInfo.setShopId(infos[0]);
			shopInfo.setShopName(infos[1]);
			String shopDescript = infos[2];
			if (shopDescript.length() == 0) {
				shopDescript = "暂无简介~";
			}
			shopInfo.setShopDescript(shopDescript);
			shopInfo.setShopLogo(infos[3]);
			shopInfo.setTallyScore(infos[4]);
			shopInfo.setAttitudeScore(infos[5]);
			shopInfo.setDeliverySpeedScore(infos[6]);
			
			shopInfos.add(shopInfo);
		}
		return shopInfos;
	}
	
	private List<Brand> getBrandFacet(QueryResponse response) {
		// 获取品牌Facet字段
		FacetField brandIdField = response.getFacetField("brandId");
		FacetField brandField = response.getFacetField("brandName");
		List<Count> brandIdValues = brandIdField.getValues();
		List<Count> brandValues = brandField.getValues();
		List<Brand>	brands = new ArrayList<Brand>();
		for (int i = 0; i < brandValues.size(); i++) {
			Count idCount = brandIdValues.get(i);
			Count nameCount = brandValues.get(i);
			
			Brand brand = new Brand();
			brand.setBrandId(idCount.getName());
			brand.setBrand(nameCount.getName());
			brand.setCount(nameCount.getCount());
			brands.add(brand);
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
			Cat cat = new Cat();
			cat.setCatGrdParentId(names[0]);
			cat.setCatGrdParentName(names[1]);
			cat.setCatParentId(names[2]);
			cat.setCatParentName(names[3]);
			cat.setCatId(names[4]);
			cat.setCatName(names[5]);
			
			cats.add(cat);
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
					
					PropValue pv = new PropValue();
					pv.setPropValueId(propValues[0]);
					pv.setPropValue(propValues[1]);
					pv.setCount(count.getCount());
					propValueList.add(pv);
				}
				if (!propValueList.isEmpty()) {
					property.setProperties(propValueList);
					properties.add(property);
				}
			}
		}
		return properties;
	}

	@Override
	public Item queryById(String id) throws Exception {
		SolrQuery params = new SolrQuery();
		params.setQuery(SolrConstant.ITEM_ID + ":" + id);
		QueryResponse queryResponse = productIndexDao.query(params);
		List<Item> items = queryResponse.getBeans(Item.class);
		Item item = null;
		if (!items.isEmpty()) {
			item = items.get(0);
		}
		return item;
	}

	@Override
	public boolean isExists(Integer id) throws Exception {
		SolrQuery params = new SolrQuery();
		params.setQuery("itemId:" + id);
		QueryResponse queryResponse = productIndexDao.query(params);
		return queryResponse.getResults().getNumFound() > 0;
	}

	@Override
	public List<Integer> queryIds() throws Exception {
		SolrQuery params = new SolrQuery();
		params.setQuery("*:*");
		QueryResponse queryResponse = productIndexDao.query(params);
		SolrDocumentList results = queryResponse.getResults();
		int num = (int) (results.getNumFound() + 10);
		params.setRows(num);
		queryResponse = productIndexDao.query(params);
		results = queryResponse.getResults();
		List<Integer> ids = new ArrayList<Integer>();
		for (SolrDocument solrDocument : results) {
			String id = (String) solrDocument.getFieldValue("itemId");
			ids.add(Integer.parseInt(id));
		}
		return ids;
	}

	@Override
	public List<String> queryIndexByQuery(String query) throws Exception {
		SolrQuery params = new SolrQuery(query);
		QueryResponse queryResponse = productIndexDao.query(params);
		SolrDocumentList results = queryResponse.getResults();
		long numFound = results.getNumFound();
		// 如果数量多于10个则再次查询所有
		if (numFound > 10) {
			params.setRows((int)numFound);
			queryResponse = productIndexDao.query(params);
		}
		List<String> idList = new ArrayList<String>();
		for (SolrDocument solrDocument : results) {
			String itemId = (String) solrDocument.getFieldValue("itemId");
			idList.add(itemId);
		}
		return idList;
	}
	
}
