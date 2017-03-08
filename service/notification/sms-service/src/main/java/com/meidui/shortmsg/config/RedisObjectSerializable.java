package com.meidui.shortmsg.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Title : RedisObjectSerializable 
 * Description : reids序列化
 * Created By : Kaixuan.Feng 
 * Creation Time : 2016年12月16日 下午2:46:55 
 * -------------------------
 * Modified By : 
 * Modification Time : 
 * Modify Content : 
 * -------------------------
 */
public class RedisObjectSerializable implements RedisSerializer<Object>{
	
	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();
	

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if (bytes != null && bytes.length > 0)
			return deserializer.convert(bytes);
		else
			return null;
	}

	@Override
	public byte[] serialize(Object object) throws SerializationException {
		if (object != null)
			return serializer.convert(object);
		else
			return new byte[0];
	}
}