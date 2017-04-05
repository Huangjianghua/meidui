package com.first.utility;

public interface Serializable {

    byte[] serialize();
    void unserialize(byte[] ss);
    
}
