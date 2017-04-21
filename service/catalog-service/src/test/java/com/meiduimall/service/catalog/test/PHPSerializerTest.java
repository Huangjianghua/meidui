package com.meiduimall.service.catalog.test;

import java.util.HashMap;

import org.junit.Test;
import org.phprpc.util.AssocArray;
import org.phprpc.util.PHPSerializer;

public class PHPSerializerTest {

	@Test
	public void sysrate_dsr_serializer() throws Exception {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);
		map.put(4, 11);
		map.put(5, 118);
		AssocArray array = new AssocArray();
		array.putAll(map);
		PHPSerializer p = new PHPSerializer();
		p.setCharset("utf-8");
		byte[] bs = p.serialize(array);
		String result = new String(bs, "utf-8");
		System.out.println(result);
		String content = "a:5:{i:1;i:0;i:2;i:0;i:3;i:0;i:4;i:11;i:5;i:118;}";
		System.out.println(content.equals(result));
	}
}
