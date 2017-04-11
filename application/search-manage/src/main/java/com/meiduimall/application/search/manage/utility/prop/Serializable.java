package com.meiduimall.application.search.manage.utility.prop;

public interface Serializable {

    byte[] serialize();
    void unserialize(byte[] ss);
    
}
