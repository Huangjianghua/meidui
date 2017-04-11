package com.meiduimall.application.search.manage.dao.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.manage.dao.CatIndexDao;
import com.meiduimall.application.search.manage.pojo.CatModel;

@Repository
public class CatIndexDaoImpl implements CatIndexDao {
	
	@Autowired
	private SolrClient cats;

	@Override
	public CatModel getCatModelByQuery(String q) throws Exception {
		SolrQuery params = new SolrQuery(q);
		QueryResponse response = cats.query(params);
		SolrDocumentList results = response.getResults();
		CatModel catModel = null;
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
		return catModel;
	}

	@Override
	public int updateCatIndex(List<CatModel> catModels) throws Exception {
		cats.addBeans(catModels);
		UpdateResponse commit = cats.commit();
		return commit.getStatus();
	}

	@Override
	public int updateCatIndexById(CatModel catModel) throws Exception {
		cats.addBean(catModel);
		UpdateResponse commit = cats.commit();
		return commit.getStatus();
	}

	@Override
	public int deleteCatIndexByQuery(String query) throws Exception {
		cats.deleteByQuery(query);
		UpdateResponse commit = cats.commit();
		return commit.getStatus();
	}

	@Override
	public int deleteCatIndexById(String id) throws Exception {
		cats.deleteById(id);
		UpdateResponse commit = cats.commit();
		return commit.getStatus();
	}

}
