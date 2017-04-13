package com.meiduimall.application.md1gwaccess.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.meiduimall.application.md1gwaccess.model.PaymentTrade;

public class Operate {
	/**
     * 序列化方法
     * @throws IOException
     * @throws FileNotFoundException
     */
    @SuppressWarnings("resource")
	public void serializable(PaymentTrade paymentTrade) throws FileNotFoundException, IOException{
        ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream("a.txt"));
        outputStream.writeObject(paymentTrade); 
    }
     
    /**
     * 反序列化的方法
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("resource")
	public PaymentTrade deSerializable() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("a.txt"));
        return (PaymentTrade) ois.readObject();
    }
}
