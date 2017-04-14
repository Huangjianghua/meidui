package com.meiduimall.core;


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
