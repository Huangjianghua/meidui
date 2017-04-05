package test.search;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.first.mapper.PropMapper;
import com.first.pojo.Props;

public class TestProps extends BaseTest {

	@Autowired
	private PropMapper propMapper;
	
	@Test
	public void testQuery() throws Exception {
		List<Props> props = propMapper.queryProps();
		
		System.out.println(props);
	}
}
