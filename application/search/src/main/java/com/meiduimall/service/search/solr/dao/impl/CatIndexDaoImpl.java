package com.meiduimall.service.search.solr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meiduimall.service.search.entity.CatModel;
import com.meiduimall.service.search.solr.dao.CatIndexDao;

@Repository
public class CatIndexDaoImpl implements CatIndexDao {

	@Autowired
	private SolrClient cats;

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
