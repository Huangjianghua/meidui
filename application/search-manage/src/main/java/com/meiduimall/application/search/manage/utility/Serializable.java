package com.meiduimall.application.search.manage.utility;

public interface Serializable {

    byte[] serialize();
    void unserialize(byte[] ss);
    
}
