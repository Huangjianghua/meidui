
package com.meidui.shortmsg.cache;

/**
 * @author Michael
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class CacheManager { 
   public static HashMap<Object, Object> cacheMap = new HashMap<Object, Object>();

   private CacheManager() { 
       super(); 
   } 
   

   private synchronized static CacheItem getCache(String key) { 
       return (CacheItem) cacheMap.get(key); 
   } 


   private synchronized static boolean hasCache(String key) { 
       return cacheMap.containsKey(key); 
   } 


   public synchronized static void clearAll() { 
       cacheMap.clear(); 
   } 

   public synchronized static void clearAll(String type) { 
       Iterator i = cacheMap.entrySet().iterator(); 
       String key; 
       ArrayList arr = new ArrayList(); 
       try { 
           while (i.hasNext()) { 
               java.util.Map.Entry entry = (java.util.Map.Entry) i.next(); 
               key = (String) entry.getKey(); 
               if (key.startsWith(type)) { 
                   arr.add(key); 
               } 
           } 
           for (int k = 0; k <arr.size(); k++) { 
               clearOnly(arr.get(k).toString()); 
           } 
       } catch (Exception ex) { 
           ex.printStackTrace(); 
       } 
   } 

   public synchronized static void clearOnly(String key) { 
       cacheMap.remove(key); 
   } 

   
   public synchronized static void put(String key, CacheItem obj) { 
       cacheMap.put(key, obj); 
   } 

   
   public static CacheItem get(String key) { 

       if (hasCache(key)) { 
    	   CacheItem cache = getCache(key); 
           if (cacheExpired(cache)) { //调用判断是否终止方法 
               cache.setExpired(true); 
           } 
           return cache; 
       }else 
           return null; 
   } 

  
   public static void put(String key, CacheItem obj, long dt,boolean expired) { 
	   CacheItem cache = new CacheItem(); 
       cache.setKey(key); 
       cache.setTimeOut(dt + System.currentTimeMillis()); //设置多久后更新缓存 
       cache.setValue(obj); 
       cache.setExpired(expired); //缓存默认载入时，终止状态为FALSE 
       cacheMap.put(key, cache); 
   } 
   
   public static void put(String key,CacheItem obj,long dt){ 
	   CacheItem cache = new CacheItem(); 
       cache.setKey(key); 
       cache.setTimeOut(dt+System.currentTimeMillis()); 
       cache.setValue(obj); 
       cache.setExpired(false); 
       cacheMap.put(key,cache); 
   } 


   public static boolean cacheExpired(CacheItem cache) { 
       if (null == cache) { 
           return false; 
       } 
       long nowDt = System.currentTimeMillis(); 
       long cacheDt = cache.getTimeOut(); 
       if (cacheDt <= 0||cacheDt>nowDt) {  
           return false; 
       } else { 
           return true; 
       } 
   } 

   
   public static int getCacheSize() { 
       return cacheMap.size(); 
   } 

   
   public static int getCacheSize(String type) { 
       int k = 0; 
       Iterator i = cacheMap.entrySet().iterator(); 
       String key; 
       try { 
           while (i.hasNext()) { 
               java.util.Map.Entry entry = (java.util.Map.Entry) i.next(); 
               key = (String) entry.getKey(); 
               if (key.indexOf(type) != -1) { //如果匹配则删除掉 
                   k++; 
               } 
           } 
       } catch (Exception ex) { 
           ex.printStackTrace(); 
       } 

       return k; 
   } 

 
   public static ArrayList getCacheAllkey() { 
       ArrayList a = new ArrayList(); 
       try { 
           Iterator i = cacheMap.entrySet().iterator(); 
           while (i.hasNext()) { 
               java.util.Map.Entry entry = (java.util.Map.Entry) i.next(); 
               a.add((String) entry.getKey()); 
           } 
       }
       catch (Exception ex) {
    	   throw new Exception(ex.getMessage());
       }
       finally { 
           return a; 
       } 
   } 

   
   public static ArrayList getCacheListkey(String type) { 
       ArrayList a = new ArrayList(); 
       String key; 
       try { 
           Iterator i = cacheMap.entrySet().iterator(); 
           while (i.hasNext()) { 
               java.util.Map.Entry entry = (java.util.Map.Entry) i.next(); 
               key = (String) entry.getKey(); 
               if (key.indexOf(type) != -1) { 
                   a.add(key); 
               } 
           } 
       } catch (Exception ex) {
    	   throw new Exception(ex.getMessage());
       } finally { 
           return a; 
       } 
   } 

} 

