package com.meiduimall.core;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: GateWayBuilder.java
 * Author:   陈建宇
 * Date:     2017年4月14日 下午12:20:10
 * Description: 访问微服务构造器
 */
public final class GateWayBuilder {
	
	private GateWayComponent component;

    private GateWayBuilder(){}

    public static GateWayBuilder newBuilder(GateWayRequest req){
    	GateWayBuilder builder = new GateWayBuilder();
        builder.component = new GateWayComponent(req);
        return builder;
    }
    
    public GateWayComponent build(){
        return component;
    }
}
