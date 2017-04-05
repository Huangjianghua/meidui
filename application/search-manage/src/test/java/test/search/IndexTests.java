package test.search;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.services.IndexService;

public class IndexTests extends BaseTest {

	@Autowired
	private IndexService indexService;
	
	@Test
	public void testCat() {
		indexService.deleteCatlogIndexByQuery("*:*");
		indexService.addCatlogIndex(5000);
	}
	
	@Test
	public void testSuggest() {
		indexService.addSuggestIndexById("风扇");
	}
	
	@Test
	public void testIndex() {
		indexService.addProductIndex(10000);
	}
}
