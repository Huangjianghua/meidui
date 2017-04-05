package com.first.utility.prop;

public interface Serializable {

    byte[] serialize();
    void unserialize(byte[] ss);
    
}
