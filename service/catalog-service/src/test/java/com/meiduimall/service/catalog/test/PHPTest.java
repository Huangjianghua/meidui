package com.meiduimall.service.catalog.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.phprpc.util.AssocArray;
import org.phprpc.util.Cast;
import org.phprpc.util.PHPSerializer;

public class PHPTest {

	@Test
	public void serializerTest1() throws Exception {
		List<String> list = new ArrayList<String>();
		String content = "a:3:{s:13:\"spec_value_id\";a:4:{i:46;s:3:\"450\";i:43;s:3:\"437\";i:44;s:3:\"440\";i:45;s:3:\"447\";}s:10:\"spec_value\";a:4:{i:46;s:6:\"贵州\";i:43;s:15:\"茅台葡萄酒\";i:44;s:5:\"12度\";i:45;s:6:\"干红\";}s:21:\"spec_private_value_id\";a:4:{i:46;s:0:\"\";i:43;s:0:\"\";i:44;s:0:\"\";i:45;s:0:\"\";}}";
		PHPSerializer p = new PHPSerializer();
		AssocArray array = (AssocArray) p.unserialize(content.getBytes());
		for (int i = 0; i < array.size(); i++) {
			String t = (String) Cast.cast(array.get(i), String.class);
			list.add(t);
		}
		System.out.println("----------");
		for (String str : list) {
			System.out.println(str);
		}
	}

	@Test
	public void serializerTest2() throws Exception {

		String content = "a:3:{s:13:\"spec_value_id\";a:4:{i:46;s:3:\"450\";i:43;s:3:\"437\";i:44;s:3:\"440\";i:45;s:3:\"447\";}s:10:\"spec_value\";a:4:{i:46;s:6:\"贵州\";i:43;s:15:\"茅台葡萄酒\";i:44;s:5:\"12度\";i:45;s:6:\"干红\";}s:21:\"spec_private_value_id\";a:4:{i:46;s:0:\"\";i:43;s:0:\"\";i:44;s:0:\"\";i:45;s:0:\"\";}}";
		PHPSerializer p = new PHPSerializer();
		p.setCharset("utf-8");
		String result = (String) p.unserialize(content.getBytes(), String.class);
		System.out.println("----------");
		System.out.println(result);
	}
}
