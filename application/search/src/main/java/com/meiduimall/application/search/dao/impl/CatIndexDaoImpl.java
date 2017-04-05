package com.meiduimall.application.search.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meiduimall.application.search.dao.CatIndexDao;
import com.meiduimall.application.search.pojo.CatModel;

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
			catModel = doc2catModel(results.get(0));
		}
		return catModel;
	}
	
	@Override
	public List<CatModel> getCatModels(String q) throws Exception {
		SolrQuery params = new SolrQuery(q);
		QueryResponse response = cats.query(params);
		SolrDocumentList results = response.getResults();
		List<CatModel> catModels = new ArrayList<CatModel>();
		for (SolrDocument doc : results) {
			catModels.add(doc2catModel(doc));
		}
		return catModels;
	}
	
	private CatModel doc2catModel(SolrDocument doc) {
		Integer catId = Integer.parseInt(doc.getFieldValue("catId").toString());
		String catName = (String) doc.getFieldValue("catName");
		Integer parentId = Integer.parseInt(doc.getFieldValue("parentId").toString());
		String parentName = (String) doc.getFieldValue("parentName");
		Integer level = Integer.parseInt(doc.getFieldValue("level").toString());
		
		CatModel catModel = new CatModel();
		catModel.setCatId(catId);
		catModel.setCatName(catName);
		catModel.setParentId(parentId);
		catModel.setParentName(parentName);
		catModel.setLevel(level);
		
		return catModel;
	}

}
