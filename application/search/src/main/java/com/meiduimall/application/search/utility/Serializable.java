package com.meiduimall.application.search.utility;

public interface Serializable {

    byte[] serialize();
    void unserialize(byte[] ss);
    
}
