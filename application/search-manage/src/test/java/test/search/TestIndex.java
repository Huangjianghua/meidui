package test.search;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.services.IndexService;

public class TestIndex extends BaseTest {

	@Autowired
	private IndexService indexService;
	
	@Test
	public void testIn() {
		indexService.addProductIndex(5000);
//		indexService.addCatlogIndex(5000);
	}
	
	@Test
	public void testDel() {
		indexService.deleteProductIndexByQuery("*:*");
	}
}
