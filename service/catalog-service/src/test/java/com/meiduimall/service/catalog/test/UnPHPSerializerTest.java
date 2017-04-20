package com.meiduimall.service.catalog.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.phprpc.util.AssocArray;
import org.phprpc.util.PHPSerializer;

public class UnPHPSerializerTest {

	@Test
	public void serializerSKU1() throws Exception {
		// {spec_value_id={57=514}, spec_private_value_id={57=}, spec_value={57=默认}}
		String content = "a:3:{s:13:\"spec_value_id\";a:1:{i:57;s:3:"
				+ "\"514\";}s:10:\"spec_value\";a:1:{i:57;s:6:\"默认\";}s:"
				+ "21:\"spec_private_value_id\";a:1:{i:57;s:0:\"\";}}";
		PHPSerializer p = new PHPSerializer();
		
		AssocArray array = (AssocArray) p.unserialize(content.getBytes());
		
		@SuppressWarnings("unchecked")
		HashMap<String, AssocArray> map = array.toHashMap();
		System.out.println("map: " + map);
		for (Map.Entry<String, AssocArray> entry : map.entrySet()) {
			String key = entry.getKey();
			System.out.println("key: " + key);
			AssocArray value = entry.getValue();
			System.out.println("value: " + value);
			
			@SuppressWarnings("unchecked")
			HashMap<Integer, Object> map2 = value.toHashMap();
			System.out.println(map2);
			for (Map.Entry<Integer, Object> entry2 : map2.entrySet()) {
				Integer key2 = entry2.getKey();
				System.out.println("key2: " + key2);
				Object value2 = entry2.getValue();
				System.out.println("value2: " + value2);
				byte[] temp = (byte[]) value2;
				System.out.println("byte[]: " + Arrays.toString(temp));
				if(temp.length > 0){
					String str = new String(temp, "utf-8");
					System.out.println("str: " + str);
				}
			}
		}
	}
	
	@Test
	public void serializerItem2() throws Exception {
		
//		{
//			4={
//					64={spec_value_id=64, spec_value=1号自然棕色, private_spec_value_id=}, 
//					43={spec_value_id=43, spec_value=2号浅棕色, spec_private_value_id=[], private_spec_value_id=}
//			}, 
//			25={
//					245={spec_value_id=245, spec_value=CLIOCLIO珂莱欧, private_spec_value_id=}
//			}
//		}
		String content = "a:2:{i:4;a:2:{i:64;a:3:{s:13:\"spec_value_id\";s:2:\"64\";s:"
				+ "10:\"spec_value\";s:16:\"1号自然棕色\";s:21:\"private_spec_value_id\";s:"
				+ "0:\"\";}i:43;a:4:{s:21:\"private_spec_value_id\";s:0:\"\";s:10:\"spec_v"
				+ "alue\";s:13:\"2号浅棕色\";s:13:\"spec_value_id\";s:2:\"43\";s:21:\"spec_private"
				+ "_value_id\";a:0:{}}}i:25;a:1:{i:245;a:3:{s:13:\"spec_value_id\";s:3:\"245\";s:"
				+ "10:\"spec_value\";s:17:\"CLIOCLIO珂莱欧\";s:21:\"private_spec_value_id\";s:0:\"\";}}}";
//		{
//		58={517={spec_value_id=517, spec_value=700ml, private_spec_value_id=}}, 
//		43={437={spec_value_id=437, spec_value=轩尼诗, private_spec_value_id=}}, 
//		44={440={spec_value_id=440, spec_value=40-49度, private_spec_value_id=}}, 
//		45={447={spec_value_id=447, spec_value=清香, private_spec_value_id=}}, 
//		46={455={spec_value_id=455, spec_value=其他, private_spec_value_id=}}
//		}
//		String content = "a:5:{i:58;a:1:{i:517;a:3:{s:13:\"spec_value_id\";s:3:\"517\";s:10:\"spec_value\";s:5:\"700ml\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:43;a:1:{i:437;a:3:{s:13:\"spec_value_id\";s:3:\"437\";s:10:\"spec_value\";s:9:\"轩尼诗\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:46;a:1:{i:455;a:3:{s:13:\"spec_value_id\";s:3:\"455\";s:10:\"spec_value\";s:6:\"其他\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:44;a:1:{i:440;a:3:{s:13:\"spec_value_id\";s:3:\"440\";s:10:\"spec_value\";s:8:\"40-49度\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:45;a:1:{i:447;a:3:{s:13:\"spec_value_id\";s:3:\"447\";s:10:\"spec_value\";s:6:\"清香\";s:21:\"private_spec_value_id\";s:0:\"\";}}}";
		
//		{38={394={spec_value_id=394, spec_value=颜色可咨询客服, private_spec_value_id=}}}
//		String content = "a:1:{i:38;a:1:{i:394;a:3:{s:13:\"spec_value_id\";s:3:\"394\";s:10:\"spec_value\";s:21:\"颜色可咨询客服\";s:21:\"private_spec_value_id\";s:0:\"\";}}}";
		
		PHPSerializer p = new PHPSerializer();
		
		AssocArray array = (AssocArray) p.unserialize(content.getBytes());
		
		@SuppressWarnings("unchecked")
		HashMap<Integer, AssocArray> map = array.toHashMap();
		System.out.println("map: " + map);
		for (Map.Entry<Integer, AssocArray> entry : map.entrySet()) {
			Integer key = entry.getKey();
			System.out.println("key: " + key);
			AssocArray value = entry.getValue();
			System.out.println("value: " + value);
			
			@SuppressWarnings("unchecked")
			HashMap<Integer, AssocArray> map2 = value.toHashMap();
			System.out.println(map2);
			for (Map.Entry<Integer, AssocArray> entry2 : map2.entrySet()) {
				Integer key2 = entry2.getKey();
				System.out.println("key2: " + key2);
				AssocArray value2 = entry2.getValue();
				System.out.println("value2: " + value2);
				
				@SuppressWarnings("unchecked")
				HashMap<String, Object> map3 = value2.toHashMap();
				System.out.println(map3);
				for (Map.Entry<String, Object> entry3 : map3.entrySet()) {
					String key3 = entry3.getKey();
					System.out.println("key3: " + key3);
					Object value3 = entry3.getValue();
					System.out.println("value3: " + value3);
					byte[] temp = null;
					try {
						temp = (byte[]) value3;
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					System.out.println("byte[]: " + Arrays.toString(temp));
					if(temp != null && temp.length > 0){
						String str = new String(temp, "utf-8");
						System.out.println("str: " + str);
					}
				}
			}
		}
	}
	
	@Test
	public void sysrate_dsr_test() throws Exception {
		// {1=0, 2=0, 3=0, 4=11, 5=118}
		String content = "a:5:{i:1;i:0;i:2;i:0;i:3;i:0;i:4;i:11;i:5;i:118;}";
		PHPSerializer p = new PHPSerializer();
		AssocArray array = (AssocArray) p.unserialize(content.getBytes());
		
		@SuppressWarnings("unchecked")
		HashMap<Integer, Integer> map = array.toHashMap();
		System.out.println("map: " + map);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Integer key = entry.getKey();
			System.out.println("key: " + key);
			Integer value = entry.getValue();
			System.out.println("value: " + value);
		}
	}
}
