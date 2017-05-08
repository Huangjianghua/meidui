package com.meiduimall.application.mall.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class SignUtil{
	
	
	
	
	
	
	
//	public static Map<String, Object> Tree(Map<String, Object> map){
//		 Map<String, String> map = new TreeMap<String, String>(
//               new Comparator<String>() {
//                   public int compare(String obj1, String obj2) {
//                       // 升序排序
//                       return obj1.compareTo(obj2);
//                   }
//               });
//	   
//	   Set<String> keySet = map.keySet();
//	   
//       Iterator<String> iter = keySet.iterator();
//       while (iter.hasNext()) {
//           String key = iter.next();
//           System.out.println(key + ":" + map.get(key));
//       }
//		 
//		return null;
//		
//	}
	 
	
	


	/**  
     * JSONObject排序  
     *   
     * @param obj  
     * @return  
     */  
    @SuppressWarnings("all")  
    public static JSONObject sortJsonObject(JSONObject obj) {  
        Map map = new TreeMap();  
        Iterator<String> it = obj.keys();  
        while (it.hasNext()) {  
            String key = it.next();  
            Object value = obj.get(key);  
            if (value instanceof JSONObject) {  
                // System.out.println(value + " is JSONObject");  
                map.put(key, sortJsonObject(JSONObject.fromObject(value)));  
            } else if (value instanceof JSONArray) {  
                // System.out.println(value + " is JSONArray");  
                map.put(key, sortJsonArray(JSONArray.fromObject(value)));  
            } else {  
                map.put(key, value);  
            }  
        }  
        return JSONObject.fromObject(map);  
    }  
    
    
    /**  
     * JSONArray排序  
     *   
     * @param array  
     * @return  
     */  
    @SuppressWarnings("all")  
    public static JSONArray sortJsonArray(JSONArray array) {  
        List list = new ArrayList();  
        int size = array.size();  
        for (int i = 0; i < size; i++) {  
            Object obj = array.get(i);  
            if (obj instanceof JSONObject) {  
                list.add(sortJsonObject(JSONObject.fromObject(obj)));  
            } else if (obj instanceof JSONArray) {  
                list.add(sortJsonArray(JSONArray.fromObject(obj)));  
            } else {  
                list.add(obj);  
            }  
        }  
  
        list.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));  
        return JSONArray.fromObject(list);  
    }  
	
	
    
    
}