package com.meiduimall.application.mall.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.meiduimall.application.mall.constant.MallApiCode;
import com.meiduimall.application.mall.pay.model.PaymentTrade;
import com.meiduimall.exception.ServiceException;

public class Operate {
	/**
     * 序列化方法
     * @throws IOException
     * @throws FileNotFoundException
     */
	@SuppressWarnings("resource")
	public void serializable(PaymentTrade paymentTrade){
		FileOutputStream fileOutputStream = null;
    	try {
    		fileOutputStream = new FileOutputStream("a.txt");
			ObjectOutputStream outputStream=new ObjectOutputStream(fileOutputStream);
			outputStream.writeObject(paymentTrade); 
			
		} catch (FileNotFoundException e) {
			Logger.error("序列化异常:%s", e);
			throw new ServiceException(MallApiCode.SERIALIZABLE_EXCEPTION, MallApiCode.getZhMsg(MallApiCode.SERIALIZABLE_EXCEPTION));
		} catch (IOException e) {
			Logger.error("序列化异常:%s", e);
			throw new ServiceException(MallApiCode.SERIALIZABLE_EXCEPTION, MallApiCode.getZhMsg(MallApiCode.SERIALIZABLE_EXCEPTION));
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					Logger.error("关闭流异常:%s", e);
				}
			}
		} 
    }
     
    /**
     * 反序列化的方法
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
	public PaymentTrade deSerializable(){
		FileInputStream fileInputStream = null;
    	try {
			fileInputStream = new FileInputStream("a.txt");
			ObjectInputStream ois=new ObjectInputStream(fileInputStream);
			return (PaymentTrade) ois.readObject();
		} catch (Exception e) {
			Logger.error("反序列化异常:%s", e);
			throw new ServiceException(MallApiCode.DESERIALIZABLE_EXCEPTION, MallApiCode.getZhMsg(MallApiCode.DESERIALIZABLE_EXCEPTION));
		}  finally {
			if(fileInputStream !=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					Logger.error("关闭流异常:%s", e);
				}
			}
		}
    }
}
