/**
 * 
 */
package com.meiduimall.application.search.manage.cache;

import java.io.Serializable;

/**
 * @author Michael
 *
 */
@SuppressWarnings("serial")
public class CacheItem implements Serializable{

	private String key; 
    private Object value;
    private long timeOut;
    private boolean expired; 
    public CacheItem() { 
            super(); 
    } 

    public CacheItem(String key, Object value) { 
        this.key = key; 
        this.value = value; 
} 
    public CacheItem(String key, Object value, long timeOut, boolean expired) { 
            this.key = key; 
            this.value = value; 
            this.timeOut = timeOut; 
            this.expired = expired; 
    } 

    public String getKey() { 
            return key; 
    } 

    public long getTimeOut() { 
            return timeOut; 
    } 
    
    public Object getValue() { 
            return value; 
    } 

    public void setKey(String string) { 
            key = string; 
    } 

    public void setTimeOut(long l) { 
            timeOut = l; 
    } 

    public void setValue(Object object) { 
            value = object; 
    } 

    public boolean isExpired() { 
            return expired; 
    } 

    public void setExpired(boolean b) { 
            expired = b; 
    } 
}
