package com.meiduimall.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * ClienID和ScretyKey
 * @author chencong
 *
 */
public class ClientIdSecretKeyMapConst {
	
	public static Map<String, String> csk = new HashMap<String, String>();
	static {
		csk.put("o2o", "123456");
		csk.put("mallpc", "234567");
		csk.put("mallandroid", "345678");
		csk.put("mallios", "456789");
	}
}
