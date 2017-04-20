package com.meiduimall.application.mall.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.meiduimall.application.mall.exception.MallApiCode;
import com.meiduimall.application.mall.model.PaymentTrade;
import com.meiduimall.exception.ServiceException;

public class Operate {
	/**
     * 序列化方法
     * @throws IOException
     * @throws FileNotFoundException
     */
	public void serializable(PaymentTrade paymentTrade){
		FileOutputStream fileOutputStream = null;
    	try {
    		fileOutputStream = new FileOutputStream("a.txt");
			ObjectOutputStream outputStream=new ObjectOutputStream(fileOutputStream);
			outputStream.writeObject(paymentTrade); 
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			Logger.info("序列化异常:%s", e);
			throw new ServiceException(MallApiCode.SERIALIZABLE_EXCEPTION, MallApiCode.getZhMsg(MallApiCode.SERIALIZABLE_EXCEPTION));
		} catch (IOException e) {
			Logger.info("序列化异常:%s", e);
			throw new ServiceException(MallApiCode.SERIALIZABLE_EXCEPTION, MallApiCode.getZhMsg(MallApiCode.SERIALIZABLE_EXCEPTION));
		} 
    }
     
    /**
     * 反序列化的方法
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
	public PaymentTrade deSerializable(){
		ObjectInputStream ois = null;
    	try {
			FileInputStream fileInputStream = new FileInputStream("a.txt");
			ois=new ObjectInputStream(fileInputStream);
			fileInputStream.close();
			return (PaymentTrade) ois.readObject();
		} catch (Exception e) {
			Logger.info("反序列化异常:%s", e);
			throw new ServiceException(MallApiCode.DESERIALIZABLE_EXCEPTION, MallApiCode.getZhMsg(MallApiCode.DESERIALIZABLE_EXCEPTION));
		}  
    }
}
