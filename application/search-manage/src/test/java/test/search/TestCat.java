package test.search;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.page.PageView;
import com.first.pojo.CatModel;
import com.first.services.CatIndexService;
import com.first.services.CatService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-*.xml")
public class TestCat {
	
	private Logger log = Logger.getLogger(TestCat.class);

	@Autowired
	private CatService catService;
	
	@Autowired
	private CatIndexService catIndexService;
	
	@Autowired
	private SolrClient cats;
	
	@Test
	public void testQueryCat() throws Exception {
		List<CatModel> cats = catService.queryCats(new PageView(1));
		for (CatModel cat : cats) {
			System.out.println(cat);
		}
	}
	
	@Test
	public void testDelete() throws Exception {
		cats.deleteByQuery("*:*");
		cats.commit();
	}
	
	@Test
	public void testIndex() throws Exception {
		PageView p = new PageView(1);
		p.setPageSize(1);
		long start = System.currentTimeMillis();
		List<CatModel> models = catService.queryCats(null);
		cats.addBeans(models);
		cats.commit();
		long end = System.currentTimeMillis();
		log.info("index success cost: " + (end - start)/1000.0 + "s");
	}
	
	@Test
	public void testQuery() throws Exception {
		CatModel catModel = catIndexService.getCatModelByQuery("家用电器");
		if (catModel != null) {
			System.out.println(catModel);
		}
	}
	
}
