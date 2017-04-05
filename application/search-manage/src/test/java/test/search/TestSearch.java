package test.search;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.domain.Item;
import com.first.pojo.QueryIndexResult;
import com.first.pojo.SearchParam;
import com.first.services.CatIndexService;
import com.first.services.IndexService;
import com.first.services.ItemService;
import com.first.services.ProductIndexService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml" })
public class TestSearch {

	@Autowired
	private IndexService indexService;
	
	@Autowired
	private ProductIndexService productIndexService;
	
	@Autowired
	private CatIndexService catIndexService;

	@Autowired
	private ItemService itemService;

	@Test
	public void testDelete() throws Exception {
		indexService.deleteProductIndexByQuery("*:*");
	}

	@Test
	public void testInsert() throws Exception {
		indexService.addProductIndex(500);
	}

	@Test
	public void testSearch() throws Exception {
		String keyword = "美的";
		SearchParam searchParam = new SearchParam();
		searchParam.setBrand("");
		searchParam.setCat("");
		searchParam.setPage("6");
		searchParam.setKeyword(keyword);
		int rows = 10;
		searchParam.setRows(rows);
		QueryIndexResult result = productIndexService.query(searchParam);
		List<Item> dataList = result.getDataList();
		System.out.println("found: " + result.getTotalCount());
		for (Item item : dataList) {
			System.out.println(item);
		}
	}

	@Test
	public void testSearchById() throws Exception {
		Item item = productIndexService.queryById("1927666");
		System.out.println(item);
	}

}
