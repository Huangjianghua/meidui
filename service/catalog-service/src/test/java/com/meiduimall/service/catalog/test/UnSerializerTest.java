package com.meiduimall.service.catalog.test;

import org.junit.Test;

import com.meiduimall.service.catalog.util.PHPSerializer;

public class UnSerializerTest {

	@Test
	public void skuTest1() throws Exception {
		/** a:3:{s:13:"spec_value_id";a:1:{i:57;s:3:"514";}s:10:"spec_value";a:1:{i:57;s:6:"默认";}s:21:"spec_private_value_id";a:1:{i:57;s:0:"";}} */
		String content = "a:3:{s:13:\"spec_value_id\";a:1:{i:57;s:3:\"514\";}s:10:\"spec_value\";a:1:{i:57;s:6:\"默认\";}s:21:\"spec_private_value_id\";a:1:{i:57;s:0:\"\";}}";
		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();
		System.out.println("result:  " + result);
		// {spec_value_id={57=514}, spec_private_value_id={57=}, spec_value={57=默认}}
	}
	
	@Test
	public void itemTest2() throws Exception {
		/** a:5:{i:58;a:1:{i:517;a:3:{s:13:"spec_value_id";s:3:"517";s:10:"spec_value";s:5:"700ml";s:21:"private_spec_value_id";s:0:"";}}i:43;a:1:{i:437;a:3:{s:13:"spec_value_id";s:3:"437";s:10:"spec_value";s:9:"轩尼诗";s:21:"private_spec_value_id";s:0:"";}}i:46;a:1:{i:455;a:3:{s:13:"spec_value_id";s:3:"455";s:10:"spec_value";s:6:"其他";s:21:"private_spec_value_id";s:0:"";}}i:44;a:1:{i:440;a:3:{s:13:"spec_value_id";s:3:"440";s:10:"spec_value";s:8:"40-49度";s:21:"private_spec_value_id";s:0:"";}}i:45;a:1:{i:447;a:3:{s:13:"spec_value_id";s:3:"447";s:10:"spec_value";s:6:"清香";s:21:"private_spec_value_id";s:0:"";}}} */
		String content = "a:5:{i:58;a:1:{i:517;a:3:{s:13:\"spec_value_id\";s:3:\"517\";s:10:\"spec_value\";s:5:\"700ml\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:43;a:1:{i:437;a:3:{s:13:\"spec_value_id\";s:3:\"437\";s:10:\"spec_value\";s:9:\"轩尼诗\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:46;a:1:{i:455;a:3:{s:13:\"spec_value_id\";s:3:\"455\";s:10:\"spec_value\";s:6:\"其他\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:44;a:1:{i:440;a:3:{s:13:\"spec_value_id\";s:3:\"440\";s:10:\"spec_value\";s:8:\"40-49度\";s:21:\"private_spec_value_id\";s:0:\"\";}}i:45;a:1:{i:447;a:3:{s:13:\"spec_value_id\";s:3:\"447\";s:10:\"spec_value\";s:6:\"清香\";s:21:\"private_spec_value_id\";s:0:\"\";}}}";
		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();
		System.out.println("result:  " + result);
//		{
//			58={517={spec_value_id=517, spec_value=700ml, private_spec_value_id=}}, 
//			43={437={spec_value_id=437, spec_value=轩尼诗, private_spec_value_id=}}, 
//			44={440={spec_value_id=440, spec_value=40-49度, private_spec_value_id=}}, 
//			45={447={spec_value_id=447, spec_value=清香, private_spec_value_id=}}, 
//			46={455={spec_value_id=455, spec_value=其他, private_spec_value_id=}}
//		}
	}
	
	@Test
	public void itemTest3() throws Exception {
		/** a:1:{i:38;a:1:{i:394;a:3:{s:13:"spec_value_id";s:3:"394";s:10:"spec_value";s:21:"颜色可咨询客服";s:21:"private_spec_value_id";s:0:"";}}} */
		String content = "a:1:{i:38;a:1:{i:394;a:3:{s:13:\"spec_value_id\";s:3:\"394\";s:10:\"spec_value\";s:21:\"颜色可咨询客服\";s:21:\"private_spec_value_id\";s:0:\"\";}}}";
		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();
		System.out.println("result:  " + result);
		// {38={394={spec_value_id=394, spec_value=颜色可咨询客服, private_spec_value_id=}}}
	}
	
	@Test
	public void itemTest4() throws Exception {
		/** a:2:{i:4;a:2:{i:64;a:3:{s:13:"spec_value_id";s:2:"64";s:10:"spec_value";s:16:"1号自然棕色";s:21:"private_spec_value_id";s:0:"";}i:43;a:4:{s:21:"private_spec_value_id";s:0:"";s:10:"spec_value";s:13:"2号浅棕色";s:13:"spec_value_id";s:2:"43";s:21:"spec_private_value_id";a:0:{}}}i:25;a:1:{i:245;a:3:{s:13:"spec_value_id";s:3:"245";s:10:"spec_value";s:17:"CLIOCLIO珂莱欧";s:21:"private_spec_value_id";s:0:"";}}} */
		String content = "a:2:{i:4;a:2:{i:64;a:3:{s:13:\"spec_value_id\";s:2:\"64\";s:10:\"spec_value\";s:16:\"1号自然棕色\";s:21:\"private_spec_value_id\";s:0:\"\";}i:43;a:4:{s:21:\"private_spec_value_id\";s:0:\"\";s:10:\"spec_value\";s:13:\"2号浅棕色\";s:13:\"spec_value_id\";s:2:\"43\";s:21:\"spec_private_value_id\";a:0:{}}}i:25;a:1:{i:245;a:3:{s:13:\"spec_value_id\";s:3:\"245\";s:10:\"spec_value\";s:17:\"CLIOCLIO珂莱欧\";s:21:\"private_spec_value_id\";s:0:\"\";}}}";
		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();
		System.out.println("result:  " + result);
		// {4={64={spec_value_id=64, spec_value=1号自然棕色, private_spec_value_id=}, 43={spec_value_id=43, spec_value=2号浅棕色, spec_private_value_id=[], private_spec_value_id=}}, 25={245={spec_value_id=245, spec_value=CLIOCLIO珂莱欧, private_spec_value_id=}}}
	}
	
	@Test
	public void sysrate_dsr_test1() throws Exception {
		// {1=0, 2=0, 3=0, 4=11, 5=118}
		String content = "a:5:{i:1;i:0;i:2;i:0;i:3;i:0;i:4;i:11;i:5;i:118;}";
		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();
		System.out.println("result:  " + result);
	}
	
	@Test
	public void sysrate_dsr_test2() throws Exception {
		// {1=0, 2=0, 3=0, 4=6, 5=123}
		String content = "a:5:{i:1;i:0;i:2;i:0;i:3;i:0;i:4;i:6;i:5;i:123;}";
		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();
		System.out.println("result:  " + result);
	}
	
	@Test
	public void sysrate_dsr_test3() throws Exception {
		// {1=0, 2=0, 3=0, 4=16, 5=12}
		String content = "a:5:{i:1;i:0;i:2;i:0;i:3;i:0;i:4;i:16;i:5;i:12;}";
		Object obj = PHPSerializer.unserialize(content.getBytes("utf-8"), "utf-8");
		String result = obj.toString();
		System.out.println("result:  " + result);
	}
}
