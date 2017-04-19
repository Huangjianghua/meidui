package com.meiduimall.application.search.manage.dao.impl;
import java.util.List;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.meiduimall.application.search.manage.dao.CatIndexDao;
import com.meiduimall.application.search.manage.pojo.CatModel;
import com.meiduimall.core.util.JsonUtils;

@Repository
public class CatIndexDaoImpl implements CatIndexDao {
	
	private static Logger logger = LoggerFactory.getLogger(CatIndexDaoImpl.class);
	
	@Autowired
	private SolrClient cats;

	@Override
	public CatModel getCatModelByQuery(String q) {
		SolrQuery params = new SolrQuery(q);
		QueryResponse response;
		CatModel catModel = null;
		try {
			response = cats.query(params);
			SolrDocumentList results = response.getResults();
			if (results.getNumFound() > 0) {
				SolrDocument doc = results.get(0);
				Integer catId = Integer.parseInt(doc.getFieldValue("catId").toString());
				String catName = (String) doc.getFieldValue("catName");
				Integer parentId = Integer.parseInt(doc.getFieldValue("parentId").toString());
				String parentName = (String) doc.getFieldValue("parentName");
				Integer level = Integer.parseInt(doc.getFieldValue("level").toString());
				
				catModel = new CatModel();
				catModel.setCatId(catId);
				catModel.setCatName(catName);
				catModel.setParentId(parentId);
				catModel.setParentName(parentName);
				catModel.setLevel(level);
			}
		} catch (Exception e) {
			logger.error("查询品类信息异常:{},查询条件:{}", e,q);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return catModel;
	}

	@Override
	public int updateCatIndex(List<CatModel> catModels) {
		UpdateResponse commit=new UpdateResponse();
		try {
			cats.addBeans(catModels);
			commit = cats.commit();
		} catch (Exception e) {
			logger.error("更新品类信息异常:{},更新数据:{}", e,JsonUtils.beanToJson(catModels));
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return commit.getStatus();
	}

	@Override
	public int updateCatIndexById(CatModel catModel) {
		
		UpdateResponse commit=new UpdateResponse();
		try {
			cats.addBean(catModel);
			commit = cats.commit();
		} catch (Exception e) {
			logger.error("通过id更新品类信息异常:{},更新数据:{}", e,JsonUtils.beanToJson(catModel));
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return commit.getStatus();
	}

	
	
	@Override
	public int deleteCatIndexByQuery(String query) {
		UpdateResponse commit=new UpdateResponse();
		try {
			cats.deleteByQuery(query);
			commit = cats.commit();
		} catch (Exception e) {
			logger.error("删除品类信息异常:{},删除条件:{}", e,query);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return commit.getStatus();
	}

	@Override
	public int deleteCatIndexById(String id)  {
		UpdateResponse commit=new UpdateResponse();
		try {
			cats.deleteById(id);
			commit = cats.commit();
		} catch (Exception e) {
			logger.error("通过id删除品类信息异常:{},删除id:{}", e,id);
			new SolrException(SolrException.ErrorCode.BAD_REQUEST,e);
		}
		return commit.getStatus();
	}

}
