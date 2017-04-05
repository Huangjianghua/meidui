package test.search;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.mapper.ScannerMapper;
import com.first.pojo.Scanner;

public class TestScanner extends BaseTest {

	@Autowired
	private ScannerMapper scannerMapper;
	
	@Test
	public void testQuery() throws Exception {
		List<Scanner> scanners = scannerMapper.queryScanners(null);
		for (Scanner scanner : scanners) {
			System.out.println(scanner);
		}
		System.out.println(System.currentTimeMillis()/1000);
	}
}
