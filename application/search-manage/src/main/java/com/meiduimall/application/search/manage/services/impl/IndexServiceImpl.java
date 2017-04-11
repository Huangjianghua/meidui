package com.meiduimall.application.search.manage.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meiduimall.application.search.manage.IDao.IndexLogMapper;
import com.meiduimall.application.search.manage.IDao.SuggestWordMapper;
import com.meiduimall.application.search.manage.constant.SysConstant;
import com.meiduimall.application.search.manage.dao.CatIndexDao;
import com.meiduimall.application.search.manage.dao.ProductIndexDao;
import com.meiduimall.application.search.manage.dao.SuggestDao;
import com.meiduimall.application.search.manage.domain.Suggest;
import com.meiduimall.application.search.manage.mapper.CatMapper;
import com.meiduimall.application.search.manage.mapper.ItemMapper;
import com.meiduimall.application.search.manage.mapper.PropMapper;
import com.meiduimall.application.search.manage.page.PageView;
import com.meiduimall.application.search.manage.pojo.CatModel;
import com.meiduimall.application.search.manage.pojo.IndexLog;
import com.meiduimall.application.search.manage.pojo.ItemModel;
import com.meiduimall.application.search.manage.pojo.Props;
import com.meiduimall.application.search.manage.pojo.SuggestWord;
import com.meiduimall.application.search.manage.services.IndexService;
import com.meiduimall.application.search.manage.utility.Pinyin4jUtil;
import com.meiduimall.application.search.manage.utility.SolrIndexUtil;

@Service
public class IndexServiceImpl implements IndexService {

	private Logger log = Logger.getLogger(IndexServiceImpl.class);
	
	private Map<Integer, Props> propMaps = new HashMap<Integer, Props>();
	
	@Autowired
	private ProductIndexDao productIndexDao;
	
	@Autowired
	private SuggestDao suggestDao;
	
	@Autowired
	private ItemMapper itemMapper;
	
	/*@Autowired
	private SuggestWordMapper suggestWordMapper;*/

	@Autowired
	private CatIndexDao catIndexDao;
	
	@Autowired
	private CatMapper catMapper;
	
	@Autowired
	private PropMapper propMapper;
	
	/*@Autowired
	private IndexLogMapper indexLogMapper;*/
	
	private void addIndexLog(String remark) {
		try {
			IndexLog indexLogs = new IndexLog();
			indexLogs.setRemark(remark);
			indexLogs.setLogTime(new Date());
			//indexLogMapper.insertIndexLog(indexLogs);
		} catch (Exception e) {
			log.error("插入索引日志信息异常", e);
		}
	}
	
	@Override
	public Map<String, Object> addProductIndex(Integer pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		String itemId = "";
		try {
			// 更新之前先删除所有
			deleteProductIndexByQuery("*:*");
			long start = System.currentTimeMillis();
			propMaps = getPropMap();
			ItemModel im = new ItemModel();
			im.setPageSize(pageSize);
			// 获取所有商品记录数
			long totalCount = itemMapper.queryItemsCount(im);
			int totalPage = (int) Math.ceil(totalCount/(double)pageSize);
			int count = 0;
			for (int i = 1; i <= totalPage; i++) {
				im.setCurrentPage(i);
				// 查询商品信息
				List<ItemModel> items = itemMapper.queryItems(im);
				if (items != null) {
					List<SolrInputDocument> docs = new ArrayList<>();
					for (ItemModel itemModel : items) {
						SolrInputDocument doc = transformDoc(itemModel);
						if (doc == null) {
							continue;
						}
						docs.add(doc);
						itemId = itemModel.getItemId();
						count++;
					}
					productIndexDao.saveIndexes(docs);
				}
			}
			long end = System.currentTimeMillis();
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "索引商品信息成功! 共" + count + "条记录，耗时： " + (end - start)/1000.0 + " s";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "索引商品信息异常");
			String remark = "索引商品信息异常， 异常位于item_id=" + itemId;
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}
	
	@Override
	public Map<String, Object> addProductIndexById(Integer itemId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QueryResponse query = productIndexDao.query(new SolrQuery("itemId:" + itemId));
			if (!query.getResults().isEmpty()) {
				productIndexDao.deleteById(String.valueOf(itemId));
			}
			// 根据ID查询商品信息
			ItemModel itemModel = itemMapper.queryItemById(itemId);
			propMaps = getPropMap();
			if (itemModel != null) {
				long start = System.currentTimeMillis();
				productIndexDao.saveIndex(transformDoc(itemModel));
				long end = System.currentTimeMillis();
				result.put(SysConstant.STATUS_CODE, "0");
				result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
				String remark = "更新[item_id=" + itemId + "]索引信息成功! 耗时： " + (end - start)/1000.0 + " s";
				log.info(remark);
				addIndexLog(remark);
			}
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "索引单个商品信息异常");
			String remark = "更新[item_id=" + itemId + "]索引信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}
	
	private SolrInputDocument transformDoc(ItemModel itemModel) {
		return SolrIndexUtil.transform2Document(itemModel, propMaps);
	}
	
	private Map<Integer, Props> getPropMap() throws Exception {
		List<Props> props = propMapper.queryProps();
		Map<Integer, Props> propMap = new HashMap<Integer, Props>();
		for (Props prop : props) {
			propMap.put(prop.getPropValueId(), prop);
		}
		return propMap;
	}

	@Override
	public Map<String, Object> deleteProductIndexByItemId(String itemId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			productIndexDao.deleteById(itemId);
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "根据ID删除单个商品信息异常");
			String remark = "删除[itemId=" + itemId + "]索引信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}
	
	@Override
	public Map<String, Object> deleteProductIndexByQuery(String query) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			productIndexDao.deleteByQuery(query);
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "删除[" + query + "]索引信息成功";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "根据条件删除单个商品信息异常");
			String remark = "删除[" + query + "]索引信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> addSuggestIndex(Integer pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = deleteAllSuggestIndex();
			// 获取字典总记录数
			long totalCount =0;// suggestWordMapper.querySuggestWordCount(null);
			PageView pageView = new PageView();
			pageView.setPageSize(pageSize);
			int totalPage = (int) Math.ceil(totalCount/(double)pageSize);
			long start = System.currentTimeMillis();
			for (int i = 1; i <= totalPage; i++) {
				pageView.setCurrentPage(i);
				List<Suggest> suggests = getSuggests(pageView);
				if (suggests != null) {
					//suggestDao.saveSuggestIndexes(suggests);
				}
			}
			long end = System.currentTimeMillis();
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "索引提示信息成功! 共" + totalCount + "条记录，耗时： " + (end - start)/1000.0 + " s";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "索引提示信息异常");
			String remark = "索引提示信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}
	
	/**
	 * 查询搜索字典内容
	 * @param pageView
	 * @return
	 */
	private List<Suggest> getSuggests(PageView pageView) {
		try {
			List<SuggestWord> suggestWords =null;// suggestWordMapper.querySuggestWords(pageView);
			List<Suggest> list = new ArrayList<>();
			for (SuggestWord suggestWord : suggestWords) {
				Suggest suggest = transformSuggest(suggestWord.getKw());
				suggest.setKwfreq(suggestWord.getKwfreq());
				list.add(suggest);
			}
			return list;
		} catch (Exception e) {
			log.error("查询数据库搜索字典信息异常，异常信息： ", e);
			return null;
		}
	}
	
	/**
	 * 将中文内容转换为Suggest对象
	 * @param kw
	 * @return
	 */
	private Suggest transformSuggest(String kw) {
		String qualified = Pinyin4jUtil.getPinyin(kw);
		String jianpin = Pinyin4jUtil.getPinyinShort(kw, false);
		List<String> pinyin = Arrays.asList(qualified.split(","));
		List<String> abbre = Arrays.asList(jianpin.split(","));
		Suggest suggest = new Suggest();
		suggest.setKw(kw);
		suggest.setPinyin(pinyin);
		suggest.setAbbre(abbre);
		suggest.setKwfreq(0);
		return new Suggest(kw, pinyin, abbre);
	}

	@Override
	public Map<String, Object> addSuggestIndexById(String key) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Suggest suggest = transformSuggest(key);
			long start = System.currentTimeMillis();
			//suggestDao.saveSuggestIndex(suggest);
			long end = System.currentTimeMillis();
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "更新[key=" + key + "]索引信息成功! 耗时： " + (end - start)/1000.0 + " s";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "索引提示信息异常");
			String remark = "更新[key=" + key + "]索引信息异常! ";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> addSuggestIndexById(int suggestId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SuggestWord suggestWord =null;// suggestWordMapper.querySuggestWordById(suggestId);
			Suggest suggest = transformSuggest(suggestWord.getKw());
			suggest.setKwfreq(suggestWord.getKwfreq());
			long start = System.currentTimeMillis();
			//suggestDao.saveSuggestIndex(suggest);
			long end = System.currentTimeMillis();
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "更新[suggestId=" + suggestId + "]索引信息成功! 耗时： " + (end - start)/1000.0 + " s";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "索引提示信息异常");
			String remark = "更新[suggestId=" + suggestId + "]索引信息异常! ";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteSuggestIndexById(String key) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//suggestDao.deleteSuggestById(key);
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "删除[key=" + key + "]索引信息成功! ";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "删除单个搜索提示信息异常");
			String remark = "删除[key=" + key + "]索引信息异常! ";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteSuggestIndexByQuery(String key) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//suggestDao.deleteSuggestByQuery("suggest:" + key);
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "删除[suggest=" + key + "]索引信息异常! ";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "根据条件删除搜索提示信息异常");
			String remark = "删除[suggest=" + key + "]索引信息异常! ";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteAllSuggestIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//suggestDao.deleteSuggestByQuery("*:*");
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "删除全部搜索提示信息成功! ";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "删除全部搜索提示信息异常");
			String remark = "删除全部搜索提示信息异常! ";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> addCatlogIndex(int pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			long totalCount = catMapper.queryCatsCount();
			PageView pageView = new PageView();
			pageView.setPageSize(pageSize);
			int totalPage = (int) Math.ceil(totalCount/(double)pageSize);
			long start = System.currentTimeMillis();
			for (int i = 1; i <= totalPage; i++) {
				pageView.setCurrentPage(i);
				List<CatModel> catModels = catMapper.queryCats(pageView);
				if (catModels != null) {
					catIndexDao.updateCatIndex(catModels);
				}
			}
			long end = System.currentTimeMillis();
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "索引类目信息成功! 共" + totalCount + "条记录，耗时： " + (end - start)/1000.0 + " s";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "索引类目信息异常");
			String remark = "索引类目信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> addCatlogIndexById(int catId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			long start = System.currentTimeMillis();
			CatModel catModel = catMapper.queryCatById(catId);
			catIndexDao.updateCatIndexById(catModel);
			long end = System.currentTimeMillis();
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "更新[cat_id=" + catId + "]索引信息成功! 耗时： " + (end - start)/1000.0 + " s";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "更新[cat_id=" + catId + "]索引类目信息异常");
			String remark = "更新[cat_id=" + catId + "]类目索引信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteCatlogIndexByItemId(String catId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			catIndexDao.deleteCatIndexById(catId);
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "删除[cat_id=" + catId + "]索引信息成功!";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "删除[cat_id=" + catId + "]类目信息索引异常");
			String remark = "删除[cat_id=" + catId + "]类目索引信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteCatlogIndexByQuery(String query) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			catIndexDao.deleteCatIndexByQuery(query);
			result.put(SysConstant.STATUS_CODE, "0");
			result.put(SysConstant.RESULT_MSG, SysConstant.SUCCESS);
			String remark = "删除条件[" + query + "]类目索引信息成功！";
			log.info(remark);
			addIndexLog(remark);
		} catch (Exception e) {
			result.put(SysConstant.STATUS_CODE, "9999");
			result.put(SysConstant.RESULT_MSG, "删除条件" + query + "类目信息索引异常");
			String remark = "删除条件[" + query + "]类目索引信息异常";
			log.error(remark, e);
			addIndexLog(remark);
		}
		return result;
	}

}
